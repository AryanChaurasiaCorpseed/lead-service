package com.lead.dashboard.serviceImpl.VendorServicesImpl;

import com.lead.dashboard.config.AwsConfig;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.vendor.*;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.*;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.repository.VendorRepository.*;
import com.lead.dashboard.service.vendorServices.VendorService;
import com.lead.dashboard.serviceImpl.FileUploadServiceImpl;
import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;



@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private UserRepo userRepository;
//
//    @Autowired
//    private UrlsManagmentRepo urlsManagmentRepo;

 @Autowired
 private VendorRepository vendorRepository;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private VendorHistoryRepository vendorHistoryRepository;

    @Autowired
    private DesignationRepo designationRepo;

    @Autowired
    private MailSendSerivceImpl mailSendSerivce;

    @Autowired
    private VendorSubCategoryRepository vendorSubCategoryRepository;

    @Autowired
    private VendorCategoryRepo vendorCategoryRepository;

    @Autowired
    private UserVendorRequestCountRepository userVendorRequestCountRepository;

    @Autowired
    private AwsConfig awsConfig;



    @Override
    public VendorResponse generateVendorRequest(VendorRequest vendorRequest, Long userId, Long leadId) {

        Optional<User> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            Vendor vendor = new Vendor();

            VendorCategory vendorCategory = vendorCategoryRepository.findById(vendorRequest.getVendorCategoryId())
                    .orElseThrow(() -> new RuntimeException("Vendor Category not found"));

            VendorSubCategory vendorSubCategory = vendorSubCategoryRepository.findById(vendorRequest.getSubVendorCategoryId())
                    .orElseThrow(() -> new RuntimeException("Vendor SubCategory not found"));

            List<User> alignedUserListForSubCategory = vendorSubCategory.getAssignedUsers();
            User assignedUser;

            if (alignedUserListForSubCategory == null || alignedUserListForSubCategory.isEmpty()) {
                assignedUser = assignAdminUser();
            } else {
                int nextUserIndex = (vendorSubCategory.getLastAssignedUserIndex() + 1) % alignedUserListForSubCategory.size();
                assignedUser = alignedUserListForSubCategory.get(nextUserIndex);
                vendorSubCategory.setLastAssignedUserIndex(nextUserIndex);
                vendorSubCategoryRepository.save(vendorSubCategory);
            }

            vendor.setAssignedUser(assignedUser);
            vendor.setUser(userDetails.get());
            vendor.setRequirementDescription(vendorRequest.getDescription());
            vendor.setClientEmailId(vendorRequest.getClientMailId());
            vendor.setClientCompanyName(vendorRequest.getCompanyName());
            vendor.setClientName(vendorRequest.getClientName());
            vendor.setClientMobileNumber(vendorRequest.getClientMobileNumber());
            vendor.setVendorCategory(vendorCategory);
            vendor.setVendorSubCategory(vendorSubCategory);
            vendor.setDisplay(true);
            vendor.setAddedBy(userDetails.get());
            vendor.setLead(leadRepository.findById(leadId).orElseThrow(() -> new RuntimeException("Lead not found")));
            vendor.setCreateDate(new Date());
            vendor.setUpdatedDate(new Date());
            vendor.setStatus("Initial");
            vendor.setDate(LocalDate.now());
            vendor.setCurrentUpdatedDate(LocalDate.now());
            vendor.setClientBudget(vendorRequest.getClientBudgetPrice());

            vendor.setSalesAttachmentReferencePath(vendorRequest.getSalesAttachmentReferencePath());

            List<String> imageNames = vendorRequest.getSalesAttachmentReferencePath().stream()
                    .map(path -> path.substring(path.lastIndexOf('/') + 1))
                    .collect(Collectors.toList());
            vendor.setSalesAttachmentImage(imageNames);

            vendor = vendorRepository.save(vendor);

            Optional<UserVendorRequestCount> userVendorRequestCountOpt = userVendorRequestCountRepository
                    .findByUserAndVendorCategoryAndVendorSubCategory(assignedUser, vendorCategory, vendorSubCategory);

            UserVendorRequestCount userVendorRequestCount;
            if (userVendorRequestCountOpt.isPresent()) {
                userVendorRequestCount = userVendorRequestCountOpt.get();
                userVendorRequestCount.setRequestCount(userVendorRequestCount.getRequestCount() + 1);
            } else {
                userVendorRequestCount = new UserVendorRequestCount();
                userVendorRequestCount.setUser(assignedUser);
                userVendorRequestCount.setVendorCategory(vendorCategory);
                userVendorRequestCount.setVendorSubCategory(vendorSubCategory);
                userVendorRequestCount.setRequestCount(1);
                userVendorRequestCount.setDate(LocalDate.now());
                userVendorRequestCount.setCreatedAt(new Date());
            }
            userVendorRequestCount.setUpdatedAt(new Date());
            userVendorRequestCountRepository.save(userVendorRequestCount);

            VendorUpdateHistory vendorUpdate = new VendorUpdateHistory();
            vendorUpdate.setVendor(vendor);
            vendorUpdate.setRequestStatus("Initial");
            vendorUpdate.setUpdateDate(new Date());
            vendorUpdate.setUpdateDescription("Vendor request created");
            vendorUpdate.setLead(vendor.getLead());
            vendorUpdate.setUpdatedBy(vendor.getUpdatedBy());
            vendorUpdate.setCreateDate(new Date());
            vendorUpdate.setDisplay(true);
            vendorUpdate.setVendorCategory(vendorCategory);
            vendorUpdate.setVendorSubCategory(vendorSubCategory);
            vendorUpdate.setRaisedBy(userDetails.get());
            vendorUpdate.setUser(vendor.getAssignedUser());
            vendorUpdate.setDate(LocalDate.now());
            vendorUpdate.setCurrentUpdatedDate(LocalDate.now());
            vendorUpdate.setBudgetPrice(vendor.getClientBudget());
            vendorHistoryRepository.save(vendorUpdate);

            return new VendorResponse(vendor);
        } else {
            throw new RuntimeException("User not found for ID: " + userId);
        }
    }



    private User assignAdminUser() {
        List<User> adminUsers = userRepository.findByRoleAndIsNotDeleted("ADMIN");
        if (!adminUsers.isEmpty()) {
            return adminUsers.get(0);
        } else {
            throw new RuntimeException("No ADMIN user found");
        }
    }

    @Override
    public List<VendorResponse> findVendorRequestsByUserId(Long userId, Long leadId) {
        User userDetails = userRepository.findByUserIdAndIsDeletedFalse(userId);
        if (userDetails == null) {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

        List<Vendor> vendors;

        boolean isAdmin = userDetails.getRole().contains("ADMIN");
        boolean isSalesDepartment = userDetails.getDepartment().equalsIgnoreCase("Sales");

        if (isAdmin) {
            vendors = vendorRepository.findAllVendorRequests(leadId);
        } else if (isSalesDepartment) {
            vendors = vendorRepository.findVendorRequestsBySalesUserAndLead(userId, leadId);
        } else {
            vendors = vendorRepository.findAllVendorRequestByAssignedUser(userId, leadId);
        }

        String s3BaseUrl = awsConfig.getS3BaseUrl();

        return vendors.stream().map(vendor -> {
            VendorResponse vendorResponse = new VendorResponse(vendor);


            // Initialize fullImagePath as an empty list
            List<String> fullImagePath = new ArrayList<>();

            if (vendor.getSalesAttachmentImage() != null && !vendor.getSalesAttachmentImage().isEmpty()) {
                for (String salesAttachmentImage : vendor.getSalesAttachmentImage()) {
                    fullImagePath.add(s3BaseUrl + salesAttachmentImage);
                }
            }
            vendorResponse.setSalesAttachmentImage(fullImagePath);

            List<VendorUpdateHistory> runningUpdates = vendorHistoryRepository.findByVendorIdAndIsDeletedFalse(vendor.getId());
            List<VendorUpdateHistoryResponse> updateHistoryResponses = runningUpdates.stream()
                    .map(VendorUpdateHistoryResponse::new)
                    .collect(Collectors.toList());

            vendorResponse.setUpdateHistory(updateHistoryResponses);

            return vendorResponse;
        }).collect(Collectors.toList());
    }



    public List<VendorResponse> updateVendorDetails(List<Long> vendorIdList, Long updatedById, Long assigneeToId) {
        User updatedByUser = userRepository.findById(updatedById)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + updatedById));

        User assignedToUser = userRepository.findById(assigneeToId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + assigneeToId));

        List<VendorResponse> responses = new ArrayList<>();

        for (Long vendorId : vendorIdList) {
            // Fetch the vendor
            Vendor vendor = vendorRepository.findById(vendorId)
                    .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

            vendor.setUpdatedBy(updatedByUser);
            vendor.setAssignedUser(assignedToUser);
            vendor.setUpdatedDate(new Date());
            vendor.setDate(LocalDate.now());
            vendor.setCurrentUpdatedDate(LocalDate.now());

            vendorRepository.save(vendor);

            responses.add(new VendorResponse(vendor));
        }

        return responses;
    }


    @Override
    public VendorResponse sendQuotation(VendorQuotationRequest vendorQuotationRequest, Long userId, Long leadId, Long vendorRequestId) {
        Vendor vendor = vendorRepository.findById(vendorRequestId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        User createdByUser = vendor.getUser(); // Assuming this field stores the user who created the vendor
        String createdByEmail = createdByUser.getEmail();

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

        Optional<VendorCategory> vendorCategory = vendorCategoryRepository.findById(vendorQuotationRequest.getVendorCategoryId());
        Optional<VendorSubCategory> vendorSubCategory = vendorSubCategoryRepository.findById(vendorQuotationRequest.getSubVendorCategoryId());

        User mailSentBy = user;
        String clientEmailId = vendorQuotationRequest.getClientMailId();
        String additionalEmailId = vendorQuotationRequest.getAdditionalMailId();

        List<String> mailToList = new ArrayList<>();
        mailToList.add(clientEmailId);
        if (additionalEmailId != null && !additionalEmailId.isEmpty()) {
            mailToList.add(additionalEmailId);
        }
        String[] mailTo = mailToList.toArray(new String[0]);

        String[] mailCc = new String[]{mailSentBy.getEmail(), createdByEmail};


        if (vendorQuotationRequest.getAgreementName() != null && !vendorQuotationRequest.getAgreementName().isEmpty() &&
                vendorQuotationRequest.getAgreementWithClientDocumentPath() != null && !vendorQuotationRequest.getAgreementWithClientDocumentPath().isEmpty()) {

            String agreementSubject = "Agreement for - " + vendor.getVendorCategory().getVendorCategoryName();

            try {
                String[] agreementMailCc = new String[]{mailSentBy.getEmail()};

                mailSendSerivce.sendEmailWithAgreementToClient(
                        mailTo,
                        agreementMailCc,
                        agreementSubject,
                        vendorQuotationRequest,
                        mailSentBy,
                        vendorCategory);
            } catch (Exception e) {
                throw new RuntimeException("Failed to send agreement email: " + e.getMessage());
            }
        } else {
            System.out.println("Agreement email not sent as agreementName or agreementWithClientDocumentPath is missing.");
        }

        if (vendorQuotationRequest.getResearchName() != null && !vendorQuotationRequest.getResearchName().isEmpty() &&
                vendorQuotationRequest.getResearchDocumentName() != null && !vendorQuotationRequest.getResearchDocumentName().isEmpty()) {

            // Fetch the list of users based on designation and department
            List<User> userList = userRepository.findByDesignationAndDepartment("Operations", "Operations Managers");


            List<String> emailList= userList.stream().map(User::getEmail).collect(Collectors.toList());

            String[] operationEmailId= emailList.toArray(new String[0]);

            String researchSubject = "Research for - " + vendor.getVendorCategory().getVendorCategoryName();

            try {
                String[] sentByVendorInCC = new String[]{mailSentBy.getEmail()};

                // Send the email
                mailSendSerivce.sendEmailForResearch(
                        operationEmailId, // Pass the list of emails as mailTo
                        researchSubject,
                        sentByVendorInCC,
                        vendorCategory
                );
            } catch (Exception e) {
                throw new RuntimeException("Failed to send Research email: " + e.getMessage());
            }
        } else {
            System.out.println("Research email not sent as  researchDocumentName is missing.");
        }


        String subject = "Quotation for - " + vendor.getVendorCategory().getVendorCategoryName();

        try {
            mailSendSerivce.sendEmailWithAttachmentForVendor(mailTo, mailCc, subject,vendorQuotationRequest,
                    mailSentBy, vendorCategory, vendorSubCategory);


        } catch (Exception e) {
            throw new RuntimeException("Failed to send quotation email: " + e.getMessage());
        }
        String agreementImages = vendorQuotationRequest.getAgreementWithClientDocumentPath().
                substring(vendorQuotationRequest.getAgreementWithClientDocumentPath().lastIndexOf("/") + 1);

        // Update vendor and history
        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setRaisedBy(mailSentBy);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setProposalSentStatus(true);
        vendorUpdateHistory.setRequestStatus(vendorQuotationRequest.getRequestStatus());
        vendorUpdateHistory.setUpdatedBy(user);
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setQuotationAmount(vendorQuotationRequest.getQuotationAmount());
        vendorUpdateHistory.setQuotationFilePath(vendorQuotationRequest.getQuotationFilePath());
        vendorUpdateHistory.setRequestStatus("Quotation Sent");
        vendorUpdateHistory.setBudgetPrice(vendor.getClientBudget());
        vendorUpdateHistory.setCreateDate(new Date());
        vendorUpdateHistory.setUser(user);
        vendorUpdateHistory.setUpdateDescription(vendorQuotationRequest.getComment());
        vendorUpdateHistory.setMailTo(Arrays.asList(mailTo));
        vendorUpdateHistory.setMailCc(Arrays.asList(mailCc));
        vendorUpdateHistory.setDeleted(false);
        vendorUpdateHistory.setDate(LocalDate.now());
        vendorUpdateHistory.setCurrentUpdatedDate(LocalDate.now());
        vendorUpdateHistory.setAgreementName(vendorQuotationRequest.getAgreementName());
        vendorUpdateHistory.setAgreementName(agreementImages);

        vendorUpdateHistory.setVendorCategory(vendorCategory.orElse(null));
        vendorUpdateHistory.setVendorSubCategory(vendorSubCategory.orElse(null));

        vendorHistoryRepository.save(vendorUpdateHistory);

        vendor.setProposalSentStatus(true);
        vendor.setUpdatedDate(new Date());
        vendor.setSharePriceToClient(vendorUpdateHistory.getQuotationAmount());
        vendor.setUpdatedBy(vendorUpdateHistory.getUpdatedBy());
        vendor.setDate(LocalDate.now());
        vendor.setStatus("Quotation Sent");
        vendor.setCurrentUpdatedDate(LocalDate.now());
        vendorRepository.save(vendor);

        VendorResponse response = new VendorResponse(vendor);

        List<VendorUpdateHistoryResponse> historyResponses = vendorHistoryRepository.findById(vendor.getId())
                .stream()
                .map(VendorUpdateHistoryResponse::new)
                .collect(Collectors.toList());

        response.setUpdateHistory(historyResponses);

        return response;
    }

    @Override
    public VendorHistoryUpdated updateVendorHistory(VendorRequestUpdate vendorRequestUpdate, Long leadId, Long userId, Long vendorId) {

        if (vendorRequestUpdate == null) {
            throw new IllegalArgumentException("Request cannot be null.");
        }
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null.");
        }
        if (leadId == null) {
            throw new IllegalArgumentException("Lead ID cannot be null.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

        if (vendor.getStatus() == vendorRequestUpdate.getRequestStatus()) {
            throw new IllegalArgumentException("Duplicate status is not allowed.");
        }


//        UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorRequestUpdate.getServiceName());


        Optional<VendorCategory> vendorCategory = vendorCategoryRepository.findById(vendorRequestUpdate.getVendorCategoryId());

        Optional<VendorSubCategory> vendorSubCategory = vendorSubCategoryRepository.findById(vendorRequestUpdate.getSubVendorCategoryId());


        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();

        vendorUpdateHistory.setUpdateDescription(vendorRequestUpdate.getComment());
        vendorUpdateHistory.setUpdatedBy(user);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setUser(user);
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setBudgetPrice(vendor.getClientBudget());
        vendorUpdateHistory.setCreateDate(new Date());
        vendorUpdateHistory.setDisplay(true);
        vendorUpdateHistory.setRaisedBy(vendor.getUser());
//        vendorUpdateHistory.setUrlsManagment(vendor.getUrlsManagment());
        vendorUpdateHistory.setExternalVendorPrice(vendorRequestUpdate.getExternalVendorPrice());
        vendorUpdateHistory.setInternalVendorPrices(vendorRequestUpdate.getInternalVendorPrices());
        vendorUpdateHistory.setExternalVendorFilePath(vendorRequestUpdate.getExternalVendorFilePath());
        vendorUpdateHistory.setInternalVendorFilePath(vendorRequestUpdate.getInternalVendorFilePath());
        vendorUpdateHistory.setQuotationAmount(vendorRequestUpdate.getQuotationAmount());
        vendorUpdateHistory.setQuotationFilePath(vendorRequestUpdate.getQuotationFilePath());
        vendorUpdateHistory.setRequestStatus(vendorRequestUpdate.getRequestStatus());
        vendorUpdateHistory.setDate(LocalDate.now());
        vendorUpdateHistory.setCurrentUpdatedDate(LocalDate.now());
        vendorUpdateHistory.setVendorCategory(vendorCategory.get());
        vendorUpdateHistory.setVendorSubCategory(vendorSubCategory.get());
        vendorUpdateHistory.setUpdatedName(user.getFullName());



        vendorUpdateHistory = vendorHistoryRepository.save(vendorUpdateHistory);


        vendor.setStatus(vendorRequestUpdate.getRequestStatus());
        vendor.setUpdatedBy(user);
        vendor.setCurrentUpdatedDate(LocalDate.now());

        vendorRepository.save(vendor);



        VendorHistoryUpdated vendorHistoryUpdated = new VendorHistoryUpdated();
        vendorHistoryUpdated.setId(vendorUpdateHistory.getId());
        vendorHistoryUpdated.setUpdateDescription(vendorUpdateHistory.getUpdateDescription());
        vendorHistoryUpdated.setStatus(vendorUpdateHistory.getRequestStatus());
        vendorHistoryUpdated.setExternalVendorPrice(vendorUpdateHistory.getExternalVendorPrice());
        vendorHistoryUpdated.setInternalVendorPrices(vendorUpdateHistory.getInternalVendorPrices());
        vendorHistoryUpdated.setExternalVendorFilePath(vendorUpdateHistory.getExternalVendorFilePath());
        vendorHistoryUpdated.setInternalVendorFilePath(vendorUpdateHistory.getInternalVendorFilePath());
        vendorHistoryUpdated.setQuotationAmount(vendorUpdateHistory.getQuotationAmount());
        vendorHistoryUpdated.setQuotationFilePath(vendorUpdateHistory.getQuotationFilePath());
        vendorHistoryUpdated.setUpdateDate(vendorUpdateHistory.getUpdateDate());
        vendorHistoryUpdated.setVendorSharedPrice(vendorUpdateHistory.getExternalVendorPrice());
        vendorHistoryUpdated.setProposalSentStatus(vendor.isProposalSentStatus());


        return vendorHistoryUpdated;
    }



    @Override
    public Map<String, Object> findAllVendorRequest(Long userId, int page, int size) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));


        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Vendor> vendorPage;

        boolean isAdmin = user.getUserRole().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase("ADMIN"));

        if (isAdmin) {
            vendorPage = vendorRepository.findAllVendors(pageable);  // Admin query
        } else {
            vendorPage = vendorRepository.findVendorsByAssignedUser(user, pageable);  // Non-admin query
        }

        List<Vendor> vendorList = vendorPage.getContent();
        long totalCount = vendorPage.getTotalElements();

        List<VendorAllResponse> vendorResponseDTOList = new ArrayList<>();

        for (Vendor vendor : vendorList) {
            VendorAllResponse vendorResponseDTO = new VendorAllResponse();
            vendorResponseDTO.setId(vendor.getId());
            vendorResponseDTO.setClientEmailId(vendor.getClientEmailId());
            vendorResponseDTO.setClientCompanyName(vendor.getClientCompanyName());
            vendorResponseDTO.setClientName(vendor.getClientName());
            vendorResponseDTO.setClientMobileNumber(vendor.getClientMobileNumber());
            vendorResponseDTO.setRequirementDescription(vendor.getRequirementDescription());
            vendorResponseDTO.setStatus(vendor.getStatus());
            vendorResponseDTO.setProposalSentStatus(vendor.isProposalSentStatus());
            vendorResponseDTO.setLeadId(vendor.getLead().getId());
            vendorResponseDTO.setLeadName(vendor.getLead().getLeadName());
            vendorResponseDTO.setBudgetPrice(vendor.getClientBudget());
            vendorResponseDTO.setReceivedDate(vendor.getDate());
            vendorResponseDTO.setSubCategoryTatDays(vendor.getVendorSubCategory().getVendorCompletionTat());

            LocalDate requestCreatedDate = vendor.getDate();
            int vendorTat = vendor.getVendorSubCategory().getVendorCompletionTat();


            VendorUpdateHistory finishedUpdate = vendor.getVendorUpdateHistory()
                    .stream()
                    .filter(history -> "Finished".equalsIgnoreCase(history.getRequestStatus()) || "Quotation Sent".equalsIgnoreCase(history.getRequestStatus()))
                    .max(Comparator.comparing(VendorUpdateHistory::getDate))
                    .orElse(null);

            if (finishedUpdate != null) {
                LocalDate completedDate = finishedUpdate.getDate();
                vendorResponseDTO.setCompletedDate(completedDate);
                int daysTaken = (int) ChronoUnit.DAYS.between(requestCreatedDate, completedDate);
                vendorResponseDTO.setCompletionDays(daysTaken);
                vendorResponseDTO.setTatDaysLeft(Math.max(vendorTat - daysTaken, 0));
                vendorResponseDTO.setOverDueTat(Math.max(daysTaken - vendorTat, 0));
            } else {
                int daysElapsed = (int) ChronoUnit.DAYS.between(requestCreatedDate, LocalDate.now());
                vendorResponseDTO.setTatDaysLeft(Math.max(vendorTat - daysElapsed, 0));
                vendorResponseDTO.setOverDueTat(0);
            }

            vendorResponseDTO.setAssigneeId(vendor.getAssignedUser().getId());
            vendorResponseDTO.setAssigneeName(vendor.getAssignedUser().getFullName());
            vendorResponseDTO.setVendorCategoryName(
                    vendor.getVendorCategory() != null ? vendor.getVendorCategory().getVendorCategoryName() : null
            );
            vendorResponseDTO.setVendorCategoryId(
                    vendor.getVendorCategory() != null ? vendor.getVendorCategory().getId() : null
            );
            vendorResponseDTO.setVendorSubCategoryId(
                    vendor.getVendorSubCategory() != null ? vendor.getVendorSubCategory().getId() : null
            );
            vendorResponseDTO.setVendorSubCategoryName(
                    vendor.getVendorSubCategory() != null ? vendor.getVendorSubCategory().getVendorSubCategoryName() : null
            );
            vendorResponseDTO.setRaiseBy(vendor.getUser().getFullName());
            vendorResponseDTO.setView(vendor.isView());
            vendorResponseDTO.setViewedBy(
                    vendor.getViewedBy() != null ? vendor.getViewedBy().getFullName() : null
            );

            List<String> fullImagePaths = new ArrayList<>();
            for (String imagePath : vendor.getSalesAttachmentImage()) {
                fullImagePaths.add(awsConfig.getS3BaseUrl() + imagePath);
            }
            vendorResponseDTO.setSalesAttachmentImage(fullImagePaths);


            List<VendorUpdateHistoryAllResponse> updateHistoryDTOList = new ArrayList<>();
            for (VendorUpdateHistory history : vendor.getVendorUpdateHistory()) {
                VendorUpdateHistoryAllResponse historyDTO = new VendorUpdateHistoryAllResponse();
                historyDTO.setId(history.getId());
                historyDTO.setRequestStatus(history.getRequestStatus());
                historyDTO.setUpdateDescription(history.getUpdateDescription());
                historyDTO.setUpdateDate(history.getUpdateDate());
                historyDTO.setBudgetPrice(history.getBudgetPrice());
                historyDTO.setProposalSentStatus(history.isProposalSentStatus());
                historyDTO.setQuotationAmount(history.getQuotationAmount());
                historyDTO.setAgreement(awsConfig.getS3BaseUrl()+history.getAgreementName());

                historyDTO.setVendorCategoryName(
                        history.getVendorCategory() != null ? history.getVendorCategory().getVendorCategoryName() : null
                );
                historyDTO.setVendorSubCategoryName(
                        history.getVendorSubCategory() != null ? history.getVendorSubCategory().getVendorSubCategoryName() : null
                );

                updateHistoryDTOList.add(historyDTO);
            }
            vendorResponseDTO.setVendorUpdateHistoryAllResponseList(updateHistoryDTOList);

            vendorResponseDTOList.add(vendorResponseDTO);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("vendorsRequests", vendorResponseDTOList);
        response.put("totalItems", totalCount);

        return response;
    }



    public List<VendorUpdateHistory> fetchUpdatedhistory(Long userId, Long leadId, Long vendorRequestId) {

        Vendor vendor = vendorRepository.findById(vendorRequestId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));



        List<VendorUpdateHistory> updateHistoryPage = vendorHistoryRepository.findUpdateHistoryByLeadAndVendor(leadId, vendorRequestId);

        return updateHistoryPage;
    }

    @Override
    public Map<String, Object> findAllVendorRequestOfUser(Long userId, int page, int size) {

        User user = userRepository.findByUserIdAndIsDeletedFalse(userId);
        if (user == null) {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Vendor> vendorRequests = vendorRepository.findByUser(user, pageable);

        long totalCount = vendorRequests.getTotalElements();
        List<VendorAllRequestOfUser> responseList = new ArrayList<>();

        if (vendorRequests.hasContent()) {
            responseList = vendorRequests.stream()
                    .map(vendor -> {
                        VendorAllRequestOfUser response = new VendorAllRequestOfUser();
                        response.setVendorRequestId(vendor.getId());
                        response.setBudgetPrice(vendor.getClientBudget());
                        response.setLeadId(vendor.getLead().getId());
                        response.setLeadName(vendor.getLead().getLeadName());
                        response.setCategoryName(vendor.getVendorCategory().getVendorCategoryName());
                        response.setCategoryId(vendor.getVendorCategory().getId());
                        response.setSubCategoryName(vendor.getVendorSubCategory().getVendorSubCategoryName());
                        response.setSubCategoryId(vendor.getVendorSubCategory().getId());
                        response.setClientName(vendor.getClientName());
                        response.setCompanyName(vendor.getClientCompanyName());
                        response.setRequirementDescription(vendor.getRequirementDescription());
                        response.setClientEmail(vendor.getClientEmailId());
                        response.setClientNumber(vendor.getClientMobileNumber());
                        response.setAssigneeId(vendor.getAssignedUser().getId());
                        response.setAssigneeName(vendor.getAssignedUser().getFullName());

                        List<String> fullImagePaths = new ArrayList<>();
                        if (vendor.getSalesAttachmentImage() != null && !vendor.getSalesAttachmentImage().isEmpty()) {
                            for (String image : vendor.getSalesAttachmentImage()) {
                                fullImagePaths.add(awsConfig.getS3BaseUrl() + image);
                            }
                        }
                        response.setSalesAttachmentImage(fullImagePaths);

                        response.setView(vendor.isView());
                        response.setViewedBy(vendor.getViewedBy()!=null ? vendor.getViewedBy().getFullName() : null  );
                        vendor.getVendorUpdateHistory().stream()
                                .max(Comparator.comparing(VendorUpdateHistory::getUpdateDate))
                                .ifPresent(latestUpdate -> response.setCurrentStatus(latestUpdate.getRequestStatus()));

                        response.setDate(vendor.getDate());
                        return response;
                    })
                    .collect(Collectors.toList());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("vendorRequests", responseList);
        response.put("totalCount", totalCount);

        return response;
    }

    @Override
    public boolean markVendorAsViewed(Long id, Long userId) {

        User user = userRepository.findByUserIdAndIsDeletedFalse(userId);
        if (user == null) {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        Optional<Vendor> vendorOptional = vendorRepository.findById(id);

        if (vendorOptional.isPresent()) {
            Vendor vendor = vendorOptional.get();
            vendor.setView(true);
            vendor.setViewedBy(user);
            vendor.setViewedAt(new Date());
            vendor.setViewedBy(user);
            vendorRepository.save(vendor);
            return true;
        }
        return false;
    }


    @Override
    public boolean  cancelRequest(Long vendorRequestId, Long userId,String cancelReason) {

        User user = userRepository.findByUserIdAndIsDeletedFalse(userId);
        if (user == null) {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        Optional<Vendor> vendorOptional = vendorRepository.findById(vendorRequestId);

        if (vendorOptional.isPresent() &&vendorOptional!=null) {
            Vendor vendor = vendorOptional.get();

            vendor.setStatus("Cancel");
            vendor.setCancelledAt(new Date());
            vendor.setCancelledBy(userId);
            vendor.setVendorCategory(vendorOptional.get().getVendorCategory());
            vendor.setVendorSubCategory(vendorOptional.get().getVendorSubCategory());
            vendor.setLead(vendorOptional.get().getLead());
            vendor.setUpdatedDate(new Date());
            vendor.setUpdatedBy(user);

            vendorRepository.save(vendor);

            VendorUpdateHistory history = new VendorUpdateHistory();
            history.setVendor(vendor);
            history.setRequestStatus("Cancel");
            history.setUpdateDescription(cancelReason);
            history.setUpdateDate(new Date());
            history.setUpdatedBy(user);
            history.setUpdatedName(user.getFullName());
            history.setCancelledBy(user);
            history.setCancelledAt(new Date());
            history.setCreateDate(new Date());
            history.setDate(LocalDate.now());
            history.setLead(vendorOptional.get().getLead());
            history.setUpdateDate(new Date());
            vendorHistoryRepository.save(history);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public Map<String, Object> fetchVendorReport(Long userIdBy, String status, LocalDate startDate, LocalDate endDate, List<Long> userId) {
        User userDetails = userRepository.findByUserIdAndIsDeletedFalse(userIdBy);
        if (userDetails == null) {
            throw new RuntimeException("User not found for ID: " + userIdBy);
        }

        List<VendorReportResponse> vendorReportResponses = new ArrayList<>();
        boolean isAdmin = userDetails.getRole().contains("ADMIN");

        if (isAdmin) {
            List<Vendor> vendorList = vendorRepository.findAllVendorRequestByDate(startDate, endDate);

            for (Vendor vendor : vendorList) {
                String latestStatus = status;
                if (latestStatus == null) {
                    VendorUpdateHistory latestUpdate = vendor.getVendorUpdateHistory()
                            .stream()
                            .max(Comparator.comparing(VendorUpdateHistory::getUpdateDate))
                            .orElse(null);
                    if (latestUpdate != null) {
                        latestStatus = latestUpdate.getRequestStatus();
                    }
                }

                if (latestStatus != null && latestStatus.equalsIgnoreCase(vendor.getStatus())) {
                    vendorReportResponses.add(prepareVendorReportResponse(vendor, userDetails, startDate, endDate));
                }
            }
        } else {
            // Fetch vendor requests only assigned to the non-admin user
            List<Vendor> vendorList = vendorRepository.findAllByAssignedUserAndDateRange(userIdBy, startDate, endDate);

            for (Vendor vendor : vendorList) {
                String latestStatus = status;
                if (latestStatus == null) {
                    VendorUpdateHistory latestUpdate = vendor.getVendorUpdateHistory()
                            .stream()
                            .max(Comparator.comparing(VendorUpdateHistory::getUpdateDate))
                            .orElse(null);
                    if (latestUpdate != null) {
                        latestStatus = latestUpdate.getRequestStatus();
                    }
                }

                if (latestStatus != null && latestStatus.equalsIgnoreCase(vendor.getStatus())) {
                    vendorReportResponses.add(prepareVendorReportResponse(vendor, userDetails, startDate, endDate));
                }
            }
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("vendorReports", vendorReportResponses);
        return responseMap;
    }

    private VendorReportResponse prepareVendorReportResponse(Vendor vendor, User userDetails, LocalDate startDate, LocalDate endDate) {
        VendorReportResponse response = new VendorReportResponse();
        response.setId(vendor.getId());
        response.setGenerateByPersonName(userDetails.getFullName());
        response.setAssignedByPersonName(vendor.getAssignedUser() != null ? vendor.getAssignedUser().getFullName() : null);
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        response.setClientName(vendor.getClientName());
        response.setCurrentStatus(vendor.getStatus());
        response.setSubCategoryName(vendor.getVendorSubCategory() != null ? vendor.getVendorSubCategory().getVendorSubCategoryName() : null);

        int vendorTat = vendor.getVendorSubCategory().getVendorCompletionTat();
        response.setVendorCompletionTat(vendorTat);
        response.setVendorCategoryResearchTat(vendor.getVendorSubCategory().getVendorCategoryResearchTat());

        LocalDate requestCreatedDate = vendor.getDate();
        VendorUpdateHistory finishedUpdate = vendor.getVendorUpdateHistory()
                .stream()
                .filter(history -> "Finished".equalsIgnoreCase(history.getRequestStatus()) || "Quotation Sent".equalsIgnoreCase(history.getRequestStatus()))
                .max(Comparator.comparing(VendorUpdateHistory::getDate))
                .orElse(null);

        if (finishedUpdate != null) {
            LocalDate completedDate = finishedUpdate.getDate();
            response.setCompletionDate(completedDate);
            int daysTaken = (int) ChronoUnit.DAYS.between(requestCreatedDate, completedDate);
            response.setCompletionDays(daysTaken);
            response.setTatDaysLeft(Math.max(vendorTat - daysTaken, 0));
            response.setOverDueTat(Math.max(daysTaken - vendorTat, 0));
        } else {
            int daysElapsed = (int) ChronoUnit.DAYS.between(requestCreatedDate, LocalDate.now());
            response.setTatDaysLeft(Math.max(vendorTat - daysElapsed, 0));
            response.setOverDueTat(0);
        }

        return response;
    }

    public Map<String, Object> searchVendors(Long userId, String searchInput, int page, int size) {

//        User userDetails = userRepository.findByUserIdAndIsDeletedFalse(userId);
//
//        if (userDetails == null) {
//            throw new RuntimeException("User not found for ID: " + userId);
//        }
//
//        Pageable pageable = PageRequest.of(page - 1, size);
//
//        Page<Vendor> vendorPage;
//        boolean isAdmin = userDetails.getUserRole().stream()
//                .anyMatch(role -> role.getName().equalsIgnoreCase("ADMIN"));
//
//        // Fetch vendors based on role
//        if (isAdmin) {
//            vendorPage = vendorRepository.searchVendors(searchInput, pageable);
//        } else {
//            vendorPage = vendorRepository.searchVendorsByUser(userDetails, pageable, searchInput);
//        }
//
//        // Prepare response
//        Map<String, Object> response = new HashMap<>();
//        response.put("vendors", vendorPage.getContent());
//        response.put("currentPage", vendorPage.getNumber() + 1);
//        response.put("totalItems", vendorPage.getTotalElements());
//        response.put("totalPages", vendorPage.getTotalPages());
//
//        return response;

        return  null ;
    }


}

