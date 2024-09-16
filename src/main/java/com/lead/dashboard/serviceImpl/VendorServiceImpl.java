package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.Vendor;
import com.lead.dashboard.dto.request.VendorRequest;
import com.lead.dashboard.dto.response.VendorResponse;
import com.lead.dashboard.repository.UrlsManagmentRepo;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.VendorRepository;
import com.lead.dashboard.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public VendorResponse generateVendorRequest(VendorRequest vendorRequest, Long userId, MultipartFile files) {

        Optional<User> userDetails = userRepository.findById(userId);

        if (userDetails!=null) {
            Vendor vendor = new Vendor();

            if (vendorRequest != null) {

                Optional<UrlsManagment> urlsManagmentOpt = urlsManagmentRepo.findById(vendorRequest.getUrlId());

                if (urlsManagmentOpt!=null) {
                    UrlsManagment urlsManagment = urlsManagmentOpt.get();

                    vendor.setUser(userDetails.get());
                    vendor.setRequirementDescription(vendorRequest.getDescription());
                    vendor.setClientEmailId(vendorRequest.getConcernPersonMailId());
                    vendor.setClientCompanyName(vendorRequest.getCompanyName());
                    vendor.setContactPersonName(vendorRequest.getContactPersonName());
                    vendor.setVendorReferenceFile(vendorRequest.getVendorReferenceFile());
                    vendor.setUrlsManagment(urlsManagment);
                    vendor.setDisplay(true);
                    vendor.setAddedBy(userDetails.get().getId());
                    fileUploadService.uploadFilesData(files);
                    vendor.setStatus("Credit Request Initiated");


                    vendor.setCreateDate(new Date());
                    vendor.setUpdatedDate(new Date());

                    vendor = vendorRepository.save(vendor);

                    return new VendorResponse(vendor);
                } else {
                    throw new RuntimeException("Urls not found for ID: " + vendorRequest.getUrlId());
                }
            }
        } else {
            throw new RuntimeException("User not found for ID: " + userId);
        }

        return null;
    }

    @Override
    public List<VendorResponse> findVendorRequestsByUserId(Long userId) {
          User userDetails = userRepository.findByUserIdAndIsDeletedFalse(userId);

        if (userDetails!=null) {
            List<Vendor> vendors = vendorRepository.findAllVendorRequestByUserId(userDetails);
            return vendors.stream().map(VendorResponse::new).collect(Collectors.toList());
        } else {
            throw new RuntimeException("User not found for ID: " + userId);
        }
    }
}

