package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
import com.lead.dashboard.dto.request.VendorEditRequest;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.response.VendorResponse;
import com.lead.dashboard.dto.response.VendorUpdateHistoryResponse;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                    vendor.setClientEmailId(vendorRequest.getConcernPersonMailId());
                    vendor.setClientCompanyName(vendorRequest.getCompanyName());
                    vendor.setContactPersonName(vendorRequest.getContactPersonName());
                    vendor.setVendorReferenceFile(vendorRequest.getVendorReferenceFile());
                    vendor.setUrlsManagment(urlsManagmentOpt);
                    vendor.setDisplay(true);
                    vendor.setAddedBy(userDetails.get().getId());
                    vendor.setContactNumber(vendorRequest.getContactNumber());
                    vendor.setLead(lead);
                    vendor.setCreateDate(new Date());
                    vendor.setUpdatedDate(new Date());
                    vendor.setStatus("Initial");

                    vendor.setBudgetPrice(vendorRequest.getBudgetPrice());
                    vendor.setVendorSharedPrice(vendorRequest.getVendorSharedPrice());

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
                    vendorUpdate.setAddedBy(vendor.getAddedBy());
                    vendorUpdate.setUpdatedBy(vendor.getUpdatedBy());
                    vendorUpdate.setCreateDate(new Date());
                    vendorUpdate.setDisplay(true);

                    vendorUpdate.setBudgetPrice(vendorRequest.getBudgetPrice());
                    vendorUpdate.setVendorSharedPrice(vendorRequest.getVendorSharedPrice());

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



    @Override
    public VendorResponse updateVendorRequest(VendorRequest vendorRequest, Long vendorId, Long userId, Long leadId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

        if (vendorRequest == null) {
            throw new IllegalArgumentException("Vendor request data is invalid");
        }

        UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorRequest.getServiceName());
        if (urlsManagment == null) {
            throw new IllegalArgumentException("Service name not found");
        }


        vendor.setQuotationAmount(vendorRequest.getQuotationAmount());
        vendor.setProposalSentStatus(vendorRequest.isProposalSentStatus());

        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setRequestStatus(vendorRequest.getStatus());
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setBudgetPrice(vendor.getBudgetPrice());
        vendorUpdateHistory.setVendorSharedPrice(vendor.getVendorSharedPrice());
        vendorUpdateHistory.setAddedBy(userId);
        vendorUpdateHistory.setUpdatedBy(userId);
        vendorUpdateHistory.setDisplay(true);
        vendorUpdateHistory.setCreateDate(new Date());

        vendorUpdateHistory.setQuotationAttachmentPath(vendor.getQuotationAttachmentPath());
        vendorUpdateHistory.setQuotationAmount(vendor.getQuotationAmount());
        vendorUpdateHistory.setProposalSentStatus(vendor.isProposalSentStatus());

        vendorHistoryRepository.save(vendorUpdateHistory);


        return new VendorResponse(vendor);
    }


    public VendorResponse updateVendorDetails(VendorEditRequest vendorEditRequest, Long vendorId, Long userId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        vendor.setClientCompanyName(vendorEditRequest.getClientCompanyName());
        vendor.setClientEmailId(vendorEditRequest.getClientEmailId());
        vendor.setContactPersonName(vendorEditRequest.getContactPersonName());
        vendor.setContactNumber(vendorEditRequest.getContactNumber());
        vendor.setUpdatedBy(userId);
        vendor.setUpdatedDate(new Date());

        vendorRepository.save(vendor);

        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setLead(vendor.getLead());
        vendorUpdateHistory.setUser(user);
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setAddedBy(userId);
        vendorUpdateHistory.setUpdatedBy(userId);
        vendorUpdateHistory.setDisplay(true);
//        vendorUpdateHistory.setc(vendorRequest.getClientCompanyName());
//        vendorUpdateHistory.set(vendorRequest.getClientEmailId());
//        vendorUpdateHistory.setContactPersonName(vendorRequest.getContactPersonName());
//        vendorUpdateHistory.setContactNumber(vendorRequest.getContactNumber());

        // Save the update history
        vendorHistoryRepository.save(vendorUpdateHistory);

        return new VendorResponse(vendor);
    }



}

