package com.lead.dashboard.serviceImpl.VendorServicesImpl;

import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.domain.vendor.VendorSubCategory;
import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.*;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.repository.VendorRepository.VendorCategoryRepo;
import com.lead.dashboard.repository.VendorRepository.VendorHistoryRepository;
import com.lead.dashboard.repository.VendorRepository.VendorRepository;
import com.lead.dashboard.repository.VendorRepository.VendorSubCategoryRepository;
import com.lead.dashboard.service.vendorServices.VendorService;
import com.lead.dashboard.serviceImpl.FileUploadServiceImpl;
import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public VendorResponse generateVendorRequest(VendorRequest vendorRequest, Long userId, Long leadId) {
        Optional<User> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            Vendor vendor = new Vendor();

            Optional<VendorCategory> vendorCategory = vendorCategoryRepository.findById(vendorRequest.getVendorCategoryId());

            Optional<VendorSubCategory> vendorSubCategory = vendorSubCategoryRepository.findById(vendorRequest.getSubVendorCategoryId());

            if (vendorCategory != null) {
                Optional<Lead> leadOpt = leadRepository.findById(leadId);
                if (leadOpt.isPresent()) {
                    Lead lead = leadOpt.get();

                    vendor.setUser(userDetails.get());
                    vendor.setRequirementDescription(vendorRequest.getDescription());
                    vendor.setClientEmailId(vendorRequest.getClientMailId());
                    vendor.setClientCompanyName(vendorRequest.getCompanyName());
                    vendor.setClientName(vendorRequest.getClientName());
                    vendor.setSalesAttachmentReferencePath(vendorRequest.getSaleTeamAttachmentReference());
                    vendor.setVendorCategory(vendorCategory.get());
                    vendor.setVendorSubCategory(vendorSubCategory.get());

                    vendor.setDisplay(true);
                    vendor.setAddedBy(userDetails.get().getId());
                    vendor.setClientMobileNumber(vendorRequest.getClientMobileNumber());
                    vendor.setLead(lead);
                    vendor.setCreateDate(new Date());
                    vendor.setUpdatedDate(new Date());
                    vendor.setStatus("Initial");
                    vendor.setDate(LocalDate.now());
                    vendor.setCurrentUpdatedDate(LocalDate.now());


                    vendor.setClientBudget(vendorRequest.getClientBudgetPrice());

                    Designation procurementManagerDesignation = designationRepo.findByName("Procurement Manager");
                    if (procurementManagerDesignation != null) {
                        User procurementManagerOpt = userRepository.findUserByDesignationName(procurementManagerDesignation.getName());
                        if (procurementManagerOpt != null) {
                            vendor.setAssignedUser(procurementManagerOpt);
                        } else {
                            assignAdminUser(vendor);
                        }
                    } else {
                        assignAdminUser(vendor);
                    }

                    vendor = vendorRepository.save(vendor);

                    VendorUpdateHistory vendorUpdate = new VendorUpdateHistory();
                    vendorUpdate.setVendor(vendor);
                    vendorUpdate.setRequestStatus("Initial");
                    vendorUpdate.setUpdateDate(new Date());
                    vendorUpdate.setUpdateDescription("Vendor request created");
                    vendorUpdate.setLead(lead);
                    vendorUpdate.setUpdatedBy(vendor.getUpdatedBy());
                    vendorUpdate.setCreateDate(new Date());
                    vendorUpdate.setDisplay(true);
                    vendorUpdate.setVendorCategory(vendorCategory.get());
                    vendorUpdate.setVendorSubCategory(vendorSubCategory.get());

                    vendor.setDate(LocalDate.now());
                    vendor.setCurrentUpdatedDate(LocalDate.now());

                    vendorUpdate.setBudgetPrice(vendorRequest.getClientBudgetPrice());

                    vendorUpdate.setRaisedBy(userDetails.get());
                    vendorUpdate.setUser(vendor.getAssignedUser());
                    vendorUpdate.setDate(LocalDate.now());
                    vendorUpdate.setCurrentUpdatedDate(LocalDate.now());


                    vendorHistoryRepository.save(vendorUpdate);

                    return new VendorResponse(vendor);
                } else {
                    throw new RuntimeException("Lead not found for ID: " + leadId);
                }
            } else {
                throw new RuntimeException("URLs not found for service name: " + vendorCategory.get().getVendorCategoryName());
            }
        } else {
            throw new RuntimeException("User not found for ID: " + userId);
        }
    }



    private void assignAdminUser(Vendor vendor) {
        List<User> adminUsers = userRepository.findByRoleAndIsNotDeleted("ADMIN");
        if (!adminUsers.isEmpty()) {
            vendor.setAssignedUser(adminUsers.get(0));
        } else {
            throw new RuntimeException("Admin user not found");
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
        boolean isProcurementManager = userDetails.getDesignation().equalsIgnoreCase("Procurement Manager");
        boolean isSalesDepartment = userDetails.getDepartment().equalsIgnoreCase("Sales");

        if (isAdmin || isProcurementManager) {
            // Fetch all vendor requests for admin or procurement manager
            vendors = vendorRepository.findAllVendorRequests(leadId);
        } else if (isSalesDepartment) {
            // Fetch vendor requests for users in the Sales department
            vendors = vendorRepository.findVendorRequestsBySalesUserAndLead(userId, leadId);
        } else {
            // Fetch vendor requests assigned to the user
            vendors = vendorRepository.findAllVendorRequestByAssignedUser(userId, leadId);
        }

        return vendors.stream().map(vendor -> {
            VendorResponse vendorResponse = new VendorResponse(vendor);

            // Fetch update history for each vendor
            List<VendorUpdateHistory> runningUpdates = vendorHistoryRepository.findByVendorIdAndIsDeletedFalse(vendor.getId());
            List<VendorUpdateHistoryResponse> updateHistoryResponses = runningUpdates.stream()
                    .map(VendorUpdateHistoryResponse::new)
                    .collect(Collectors.toList());

            vendorResponse.setUpdateHistory(updateHistoryResponses);

            return vendorResponse;
        }).collect(Collectors.toList());
    }



    public List<VendorResponse> updateVendorDetails(List<Long> vendorIdList, Long updatedById, Long assigneeToId) {
        // Fetch the user who updated the vendor (updatedById)
        User updatedByUser = userRepository.findById(updatedById)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + updatedById));

        // Fetch the assignee user (assigneeToId)
        User assignedToUser = userRepository.findById(assigneeToId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + assigneeToId));

        List<VendorResponse> responses = new ArrayList<>();

        for (Long vendorId : vendorIdList) {
            // Fetch the vendor
            Vendor vendor = vendorRepository.findById(vendorId)
                    .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

            // Set the updatedBy and assignedUser fields
            vendor.setUpdatedBy(updatedById);
            vendor.setAssignedUser(assignedToUser);
            // Update the updated date
            vendor.setUpdatedDate(new Date());
            vendor.setDate(LocalDate.now());
            vendor.setCurrentUpdatedDate(LocalDate.now());

            // Save the updated vendor
            vendorRepository.save(vendor);

            // Add the response with updated vendor data
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

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

//        UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorQuotationRequest.getServiceName());

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

        String[] mailCc = new String[]{mailSentBy.getEmail()};
//        String subject = "Quotation for - " + vendor.getUrlsManagment().getUrlsName();
        String subject = "Quotation for - " + vendor.getVendorCategory().getVendorCategoryName();

        String body = vendorQuotationRequest.getComment();


        try {
            mailSendSerivce.sendEmailWithAttachmentForVendor(mailTo, mailCc, subject, body, vendorQuotationRequest, mailSentBy);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }


        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setRaisedBy(mailSentBy);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setProposalSentStatus(true);
        vendorUpdateHistory.setRequestStatus(vendorQuotationRequest.getRequestStatus());
//        vendorUpdateHistory.setUrlsManagment(urlsManagment);
        vendorUpdateHistory.setUpdatedBy(user.getId());
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setQuotationAmount(vendorQuotationRequest.getQuotationAmount());
        vendorUpdateHistory.setQuotationFilePath(vendorQuotationRequest.getQuotationFilePath());
        vendorUpdateHistory.setRequestStatus("Quotation Sent");
        vendorUpdateHistory.setBudgetPrice(vendor.getClientBudget());
        vendorUpdateHistory.setCreateDate(new Date());
        vendorUpdateHistory.setUser(user);
        vendorUpdateHistory.setUpdateDescription(vendorQuotationRequest.getComment());
        vendorUpdateHistory.setMailTo(Arrays.asList(mailTo));  // Set mailTo
        vendorUpdateHistory.setMailCc(Arrays.asList(mailCc));
        vendorUpdateHistory.setDeleted(true);// Set mailCc as List<String>
        vendorUpdateHistory.setDate(LocalDate.now());
        vendorUpdateHistory.setCurrentUpdatedDate(LocalDate.now());

        vendorUpdateHistory.setVendorCategory(vendorCategory.get());
        vendorUpdateHistory.setVendorSubCategory(vendorSubCategory.get());



        vendorHistoryRepository.save(vendorUpdateHistory);

        vendor.setProposalSentStatus(true);
        vendor.setUpdatedDate(new Date());
        vendor.setSharePriceToClient(vendorUpdateHistory.getQuotationAmount());
        vendor.setVendorSharedPrice(vendorUpdateHistory.getExternalVendorPrice());
        vendor.setUpdatedBy(vendorUpdateHistory.getUpdatedBy());
        vendor.setDate(LocalDate.now());
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

//        UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorRequestUpdate.getServiceName());

        Optional<VendorCategory> vendorCategory = vendorCategoryRepository.findById(vendorRequestUpdate.getVendorCategoryId());

        Optional<VendorSubCategory> vendorSubCategory = vendorSubCategoryRepository.findById(vendorRequestUpdate.getSubVendorCategoryId());


        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();

        vendorUpdateHistory.setUpdateDescription(vendorRequestUpdate.getComment());
        vendorUpdateHistory.setUpdatedBy(userId);
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


        vendorUpdateHistory = vendorHistoryRepository.save(vendorUpdateHistory);

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
        vendorHistoryUpdated.setVendorSharedPrice(vendorUpdateHistory.getBudgetPrice().toString()); // Assuming this field maps to the budget price
        vendorHistoryUpdated.setProposalSentStatus(true); // Set this based on your business logic if needed


        return vendorHistoryUpdated;
    }



    @Override
    public Map<String, Object> findAllVendorRequest(Long userId, int page, int size) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        // Create a pageable object using zero-based page indexing
        Pageable pageable = PageRequest.of(page, size);

        // Fetch vendors with pagination from the repository
        Page<Vendor> vendorPage = vendorRepository.findAll(pageable);
        List<Vendor> vendorList = vendorPage.getContent(); // Get the current page content

        long totalCount = vendorRepository.count();


        List<VendorAllResponse> vendorResponseDTOList = new ArrayList<>();

        for (Vendor vendor : vendorList) {
            VendorAllResponse vendorResponseDTO = new VendorAllResponse();

            // Mapping Vendor details
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
//            vendorResponseDTO.setServiceName(vendor.getUrlsManagment().getUrlsName());
            vendorResponseDTO.setAssigneeId(vendor.getAssignedUser().getId());
            vendorResponseDTO.setVendorCategoryName(vendor.getVendorCategory().getVendorCategoryName());
            vendorResponseDTO.setVendorSubCategoryName(vendor.getVendorSubCategory().getVendorSubCategoryName());


            // Map vendor update history
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
                historyDTO.setVendorCategoryName(history.getVendorCategory().getVendorCategoryName());
                historyDTO.setVendorSubCategoryName(history.getVendorSubCategory().getVendorSubCategoryName());


                updateHistoryDTOList.add(historyDTO);
            }
            vendorResponseDTO.setVendorUpdateHistoryAllResponseList(updateHistoryDTOList);

            vendorResponseDTOList.add(vendorResponseDTO);
        }

        // Create response map with pagination details
        Map<String, Object> response = new HashMap<>();
        response.put("vendorsRequests", vendorResponseDTOList);
//        response.put("currentPage", vendorPage.getNumber());
        response.put("totalItems", totalCount);
//        response.put("totalPages", vendorPage.getTotalPages());

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
}
