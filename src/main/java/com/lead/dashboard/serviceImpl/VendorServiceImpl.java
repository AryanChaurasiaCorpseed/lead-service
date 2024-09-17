package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Designation;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
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

                    Optional<Designation> procurementManagerDesignation = designationRepo.findByName("Procurement Manager");
                    if (procurementManagerDesignation!=null) {
                        Optional<User> procurementManagerOpt = userRepository.findByUserDesignation(procurementManagerDesignation.get());
                        if (procurementManagerOpt!=null ) {
                            vendor.setAssignedUser(procurementManagerOpt.get());
                        } else {
                            Optional<User> adminUserOpt = userRepository.findByRole("ADMIN");
                            if (adminUserOpt.isPresent()) {
                                vendor.setAssignedUser(adminUserOpt.get());
                            } else {
                                throw new RuntimeException("Admin user not found");
                            }
                        }
                    } else {
                        Optional<User> adminUserOpt = userRepository.findByRole("ADMIN");
                        if (adminUserOpt.isPresent()) {
                            vendor.setAssignedUser(adminUserOpt.get());
                        } else {
                            throw new RuntimeException("Admin user not found");
                        }
                    }

                    vendor = vendorRepository.save(vendor);

                    VendorUpdateHistory vendorUpdate = new VendorUpdateHistory();
                    vendorUpdate.setVendor(vendor);
                    vendorUpdate.setRequestStatus("Vendor request created");
                    vendorUpdate.setUpdateDate(new Date());
                    vendorUpdate.setUpdateDescription("Initial request");
                    vendorUpdate.setLead(lead);
                    vendorUpdate.setAddedBy(vendor.getAddedBy());
                    vendorUpdate.setUpdatedBy(vendor.getUpdatedBy());
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

    @Override
    public List<VendorResponse> findVendorRequestsByUserId(Long userId, Long leadId) {
        User userDetails = userRepository.findByUserIdAndIsDeletedFalse(userId);
        if (userDetails == null) {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        Lead lead = leadRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found for ID: " + leadId));

        List<Vendor> vendors;

        if (userDetails.getRole().contains("ADMIN")) {
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

        vendor.setUrlsManagment(urlsManagment);
        vendor.setRequirementDescription(vendorRequest.getDescription());
        vendor.setClientEmailId(vendorRequest.getConcernPersonMailId());
        vendor.setClientCompanyName(vendorRequest.getCompanyName());
        vendor.setContactPersonName(vendorRequest.getContactPersonName());
        vendor.setUpdatedDate(new Date());

        vendorRepository.save(vendor);

        VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
        vendorUpdateHistory.setVendor(vendor);
        vendorUpdateHistory.setRequestStatus(vendorUpdateHistory.getRequestStatus());
        vendorUpdateHistory.setUpdateDate(new Date());
        vendorUpdateHistory.setLead(lead);
        vendorUpdateHistory.setRequestStatus(vendor.getStatus());
        vendorUpdateHistory.setBudgetPrice(vendor.getBudgetPrice());
        vendorUpdateHistory.setVendorSharedPrice(vendor.getVendorSharedPrice());
        vendorUpdateHistory.setAddedBy(userId);
        vendorUpdateHistory.setUpdatedBy(userId);
        vendorUpdateHistory.setCreateDate(new Date());

        vendorHistoryRepository.save(vendorUpdateHistory);

        return new VendorResponse(vendor);
    }



}

