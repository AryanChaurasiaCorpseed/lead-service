package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import com.lead.dashboard.dto.request.VendorEditRequest;
import com.lead.dashboard.dto.request.VendorQuotationRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.request.VendorRequestUpdate;
import com.lead.dashboard.dto.response.*;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UrlsManagmentRepo urlsManagmentRepo;

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

    @Override
    public VendorResponse generateVendorRequest(VendorRequest vendorRequest, Long userId, Long leadId) {
        Optional<User> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            Vendor vendor = new Vendor();
            UrlsManagment urlsManagmentOpt = urlsManagmentRepo.findByUrlsName(vendorRequest.getServiceName());

            if (urlsManagmentOpt != null) {
                Optional<Lead> leadOpt = leadRepository.findById(leadId);
                if (leadOpt.isPresent()) {
                    Lead lead = leadOpt.get();

                    vendor.setUser(userDetails.get());
                    vendor.setRequirementDescription(vendorRequest.getDescription());
                    vendor.setClientEmailId(vendorRequest.getClientMailId());
                    vendor.setClientCompanyName(vendorRequest.getCompanyName());
                    vendor.setClientName(vendorRequest.getClientName());
                    vendor.setSalesAttachmentReferencePath(vendorRequest.getSaleTeamAttachmentReference());
                    vendor.setUrlsManagment(urlsManagmentOpt);
                    vendor.setDisplay(true);
                    vendor.setAddedBy(userDetails.get().getId());
                    vendor.setClientMobileNumber(vendorRequest.getClientMobileNumber());
                    vendor.setLead(lead);
                    vendor.setCreateDate(new Date());
                    vendor.setUpdatedDate(new Date());
                    vendor.setStatus("Initial");


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
                    vendorUpdate.setUrlsManagment(urlsManagmentOpt);

                    vendorUpdate.setBudgetPrice(vendorRequest.getClientBudgetPrice());

                    vendorUpdate.setRaisedBy(userDetails.get());
                    vendorUpdate.setUser(vendor.getAssignedUser());

                    vendorHistoryRepository.save(vendorUpdate);

                    return new VendorResponse(vendor);
                } else {
                    throw new RuntimeException("Lead not found for ID: " + leadId);
                }
            } else {
                throw new RuntimeException("URLs not found for service name: " + vendorRequest.getServiceName());
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

        if (isAdmin || isProcurementManager) {
            vendors = vendorRepository.findAllVendorRequests(leadId);
        } else {
            vendors = vendorRepository.findAllVendorRequestByUserId(userId, leadId);
        }

        return vendors.stream().map(vendor -> {
            VendorResponse vendorResponse = new VendorResponse(vendor);

            List<VendorUpdateHistory> runningUpdates = vendorHistoryRepository.findByVendorIdAndIsDeletedFalse(vendor.getId());
            List<VendorUpdateHistoryResponse> updateHistoryResponses = runningUpdates.stream()
                    .map(VendorUpdateHistoryResponse::new)
                    .collect(Collectors.toList());

            vendorResponse.setUpdateHistory(updateHistoryResponses);

            return vendorResponse;
        }).collect(Collectors.toList());
    }





    public VendorResponse updateVendorDetails(VendorEditRequest vendorEditRequest, Long vendorId, Long userId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        vendor.setClientCompanyName(vendorEditRequest.getClientCompanyName());
        vendor.setClientEmailId(vendorEditRequest.getClientEmailId());
        vendor.setClientName(vendorEditRequest.getContactPersonName());
        vendor.setClientMobileNumber(vendorEditRequest.getContactNumber());
        vendor.setUpdatedBy(userId);
        vendor.setUpdatedDate(new Date());

        vendorRepository.save(vendor);

        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setLead(vendor.getLead());
        vendorUpdateHistory.setUser(user);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setUpdatedBy(userId);
        vendorUpdateHistory.setDisplay(true);

        // Save the update history
        vendorHistoryRepository.save(vendorUpdateHistory);

        return new VendorResponse(vendor);
    }


    @Override
    public VendorResponse sendQuotation(VendorQuotationRequest vendorQuotationRequest, Long userId, Long leadId, Long vendorRequestId) {
        Vendor vendor = vendorRepository.findById(vendorRequestId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

        UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorQuotationRequest.getServiceName());

        User raisedBy = vendor.getUser();
        String clientEmailId = vendorQuotationRequest.getClientMailId();
        String additionalEmailId = vendorQuotationRequest.getAdditionalMailId();

        List<String> mailToList = new ArrayList<>();
        mailToList.add(clientEmailId);
        if (additionalEmailId != null && !additionalEmailId.isEmpty()) {
            mailToList.add(additionalEmailId);
        }
        String[] mailTo = mailToList.toArray(new String[0]);

        String[] mailCc = new String[]{raisedBy.getEmail()};
        String subject = "Quotation for - " + vendor.getUrlsManagment().getUrlsName();
        String body = vendorQuotationRequest.getComment();


        try {
            mailSendSerivce.sendEmailWithAttachmentForVendor(mailTo, mailCc, subject, body, vendorQuotationRequest, raisedBy);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }


        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setRaisedBy(raisedBy);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setProposalSentStatus(true);
        vendorUpdateHistory.setRequestStatus(vendorQuotationRequest.getRequestStatus());
        vendorUpdateHistory.setUrlsManagment(urlsManagment);
        vendorUpdateHistory.setUpdatedBy(user.getId());
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setQuotationAmount(vendorQuotationRequest.getQuotationAmount());
        vendorUpdateHistory.setQuotationFilePath(vendorQuotationRequest.getQuotationFilePath());

        vendorHistoryRepository.save(vendorUpdateHistory);

        vendor.setProposalSentStatus(true);
        vendor.setUpdatedDate(new Date());
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

        UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorRequestUpdate.getServiceName());

        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();

        vendorUpdateHistory.setRequestStatus(vendorRequestUpdate.getStatus());
        vendorUpdateHistory.setUpdateDescription(vendorRequestUpdate.getDescription());
        vendorUpdateHistory.setUpdatedBy(userId);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setUser(user);
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setBudgetPrice(vendor.getClientBudget());
        vendorUpdateHistory.setCreateDate(new Date());
        vendorUpdateHistory.setDisplay(true);
        vendorUpdateHistory.setRaisedBy(vendor.getUser());
        vendorUpdateHistory.setUrlsManagment(vendor.getUrlsManagment());
        vendorUpdateHistory.setExternalVendorPrice(vendorRequestUpdate.getExternalVendorPrice());
        vendorUpdateHistory.setInternalVendorPrices(vendorRequestUpdate.getInternalVendorPrices());
        vendorUpdateHistory.setExternalVendorFilePath(vendorRequestUpdate.getExternalVendorFilePath());
        vendorUpdateHistory.setInternalVendorFilePath(vendorRequestUpdate.getInternalVendorFilePath());
        vendorUpdateHistory.setQuotationAmount(vendorRequestUpdate.getQuotationAmount());
        vendorUpdateHistory.setQuotationFilePath(vendorRequestUpdate.getQuotationFilePath());

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
    public List<VendorAllResponse> findAllVendorRequest(Long userId, int page, int size) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        // Create a pageable object using zero-based page indexing
        Pageable pageable = PageRequest.of(page, size); // no need to subtract 1

        // Fetch vendors with pagination from the repository
        Page<Vendor> vendorPage = vendorRepository.findAll(pageable);
        List<Vendor> vendorList = vendorPage.getContent(); // Get the current page content

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
            vendorResponseDTO.setServiceName(vendor.getUrlsManagment().getUrlsName());

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

                updateHistoryDTOList.add(historyDTO);
            }
            vendorResponseDTO.setVendorUpdateHistoryAllResponseList(updateHistoryDTOList);

            vendorResponseDTOList.add(vendorResponseDTO);
        }

        return vendorResponseDTOList;
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

