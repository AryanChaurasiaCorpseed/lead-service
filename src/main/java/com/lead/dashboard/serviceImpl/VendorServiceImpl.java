package com.lead.dashboard.serviceImpl;

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
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public VendorResponse generateVendorRequest(VendorRequest vendorRequest, Long userId, Long leadId) {

        Optional<User> userDetails = userRepository.findById(userId);

        if (userDetails!=null) {
            Vendor vendor = new Vendor();

            if (vendorRequest != null) {
              UrlsManagment urlsManagmentOpt = urlsManagmentRepo.findByUrlsName(vendorRequest.getServiceName());

                if (urlsManagmentOpt!=null) {


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

//                        fileUploadService.uploadFilesData(files);

                        vendor.setLead(lead);

                        vendor.setCreateDate(new Date());
                        vendor.setUpdatedDate(new Date());

                        vendor = vendorRepository.save(vendor);

                        VendorUpdateHistory vendorUpdate = new VendorUpdateHistory();
                        vendorUpdate.setVendor(vendor);
                        vendorUpdate.setUpdateDescription("Vendor request created");
                        vendorUpdate.setUpdateDate(new Date());
                        vendorHistoryRepository.save(vendorUpdate);

                        return new VendorResponse(vendor);
                    } else {
                        throw new RuntimeException("Lead not found for ID: " + leadId);
                    }
                } else {
                    throw new RuntimeException("Urls not found for ID: " + vendorRequest.getServiceName());
                }
            }
        } else {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        return null;
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
    public VendorResponse updateVendorRequest(VendorRequest vendorRequest, Long vendorId, Long userId) {

        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found for ID: " + vendorId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));

        if (!vendor.getUser().getId().equals(userId) && !user.getRole().contains("ADMIN")) {
            throw new RuntimeException("User is not authorized to update this vendor request");
        }

        if (vendorRequest != null) {
            UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(vendorRequest.getServiceName());


            vendor.setUrlsManagment(urlsManagment);
            vendor.setRequirementDescription(vendorRequest.getDescription());
            vendor.setClientEmailId(vendorRequest.getConcernPersonMailId());
            vendor.setClientCompanyName(vendorRequest.getCompanyName());
            vendor.setContactPersonName(vendorRequest.getContactPersonName());
            vendor.setUpdatedDate(new Date());



            vendor = vendorRepository.save(vendor);

            VendorUpdateHistory vendorUpdateHistory = new VendorUpdateHistory();
            vendorUpdateHistory.setVendor(vendor);
            vendorUpdateHistory.setUpdateDescription("Vendor request updated");
            vendorUpdateHistory.setUpdateDate(new Date());
            vendorUpdateHistory.setLead(vendor.getLead());
            vendorUpdateHistory.setRequestStatus(vendor.getStatus());
            vendorUpdateHistory.setBudgetPrice(vendor.getBudgetPrice());
            vendorUpdateHistory.setVendorSharedPrice(vendor.getVendorSharedPrice());
            vendorUpdateHistory.setAddedBy(userId);
            vendorUpdateHistory.setUpdatedBy(userId);
            vendorUpdateHistory.setCreateDate(new Date());
            vendorHistoryRepository.save(vendorUpdateHistory);


            return new VendorResponse(vendor);
        } else {
            throw new RuntimeException("Vendor request data is invalid");
        }
    }


}

