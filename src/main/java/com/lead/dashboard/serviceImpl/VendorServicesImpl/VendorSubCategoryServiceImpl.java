package com.lead.dashboard.serviceImpl.VendorServicesImpl;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.domain.vendor.VendorSubCategory;
import com.lead.dashboard.dto.request.VendorSubCategoryDTO;
import com.lead.dashboard.dto.request.VendorSubCategoryRequest;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.VendorRepository.VendorCategoryRepo;
import com.lead.dashboard.repository.VendorRepository.VendorSubCategoryRepository;
import com.lead.dashboard.service.vendorServices.VendorSubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorSubCategoryServiceImpl implements VendorSubCategoryService {

    @Autowired
    private VendorSubCategoryRepository vendorSubCategoryRepository;

    @Autowired
    private VendorCategoryRepo vendorCategoryRepository;

    @Autowired
    private UserRepo userRepository;

    @Override
    public VendorSubCategory createSubVendorCategory(Long userId, VendorSubCategoryRequest vendorSubCategoryRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        VendorCategory vendorCategory = vendorCategoryRepository.findById(vendorSubCategoryRequest.getVendorCategoryId())
                .orElseThrow(() -> new RuntimeException("Vendor Category not found"));

        VendorSubCategory vendorSubCategory = new VendorSubCategory();
        vendorSubCategory.setVendorSubCategoryName(vendorSubCategoryRequest.getSubCategoryName());
        vendorSubCategory.setVendorCategory(vendorCategory);
        vendorSubCategory.setEnable(true);
        vendorSubCategory.setAddedBy(userId);
        vendorSubCategory.setUpdatedBy(userId);
        vendorSubCategory.setCreatedAt(new Date());
        vendorSubCategory.setDeleted(false);
        vendorSubCategory.setDate(LocalDate.now());

        vendorSubCategory.setVendorCategoryResearchTat(vendorSubCategoryRequest.getVendorCategoryResearchTat());
        vendorSubCategory.setVendorCompletionTat(vendorSubCategoryRequest.getVendorCompletionTat());

        return vendorSubCategoryRepository.save(vendorSubCategory);
    }

    @Override
    public VendorSubCategory updateVendorSubCategory(Long userId, Long categoryId, Long subCategoryId, String newSubCategoryName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<VendorCategory> vendorCategoryOpt = vendorCategoryRepository.findById(categoryId);
        if (!vendorCategoryOpt.isPresent()) {
            throw new RuntimeException("Vendor Category not found");
        }

        VendorSubCategory vendorSubCategory = vendorSubCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Vendor SubCategory not found"));

        vendorSubCategory.setVendorSubCategoryName(newSubCategoryName);
        vendorSubCategory.setUpdatedBy(userId);
        vendorSubCategory.setUpdatedAt(new Date());

        return vendorSubCategoryRepository.save(vendorSubCategory);
    }




    @Override
    public VendorSubCategory getVendorSubCategoryById(Long subCategoryId) {
        return vendorSubCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Vendor SubCategory not found"));
    }

    @Override
    public List<VendorSubCategoryDTO> getAllVendorSubCategories(int page, int size) {
        PageRequest pageable = PageRequest.of(page - 1, size);

        return vendorSubCategoryRepository.findAllActive(pageable)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private VendorSubCategoryDTO convertToDto(VendorSubCategory subCategory) {
        VendorSubCategoryDTO dto = new VendorSubCategoryDTO();
        dto.setId(subCategory.getId());
        dto.setVendorSubCategoryName(subCategory.getVendorSubCategoryName());
        dto.setVendorCategoryName(subCategory.getVendorCategory().getVendorCategoryName());
        dto.setAddedBy(subCategory.getAddedBy());
        dto.setCreatedAt(subCategory.getCreatedAt());
        dto.setVendorCategoryResearchTat(subCategory.getVendorCategoryResearchTat());
        dto.setVendorCompletionTat(subCategory.getVendorCompletionTat());
        dto.setDate(subCategory.getDate());

        return dto;
    }

    @Override
    public VendorSubCategory assignUsersToSubCategory(Long subCategoryId, List<Long> userIds) {
        VendorSubCategory vendorSubCategory = vendorSubCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Vendor SubCategory not found"));

        List<User> users = userRepository.findAllById(userIds);
        if (users.isEmpty()) {
            throw new RuntimeException("No valid users found with provided IDs");
        }

        vendorSubCategory.setAssignedUsers(users);
        vendorSubCategory.setUpdatedAt(new Date()); // Update timestamp
        return vendorSubCategoryRepository.save(vendorSubCategory);
    }







}
