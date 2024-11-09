package com.lead.dashboard.serviceImpl.VendorServicesImpl;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.dto.request.VendorCategoryDTO;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.VendorRepository.VendorCategoryRepo;
import com.lead.dashboard.service.vendorServices.VendorCategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendorCatServiceImpl implements VendorCategoryService {

    @Autowired
    private VendorCategoryRepo vendorCategoryRepo;

    @Autowired
    UserRepo userRepo;



    @Override
    public VendorCategory createVendorCategory(Long userId, String categoryName) {
        Optional<User> user = userRepo.findById(userId);
        VendorCategory vendorCategory = new VendorCategory();

        if (user.isPresent()) {
            vendorCategory.setVendorCategoryName(categoryName);
            vendorCategory.setAddedBy(user.get().getId());
            vendorCategory.setDeleted(false);
            vendorCategory.setDate(LocalDate.now());
            vendorCategory.setCreatedAt(new Date());
            vendorCategory.setUpdatedAt(new Date());
            vendorCategory.setUpdatedBy(user.get().getId());
            vendorCategory.setEnable(true);
        }

        return vendorCategoryRepo.save(vendorCategory);
    }


    @Override
    public VendorCategory updateVendorCategory(Long userId, Long categoryId, String newCategoryName) {
        Optional<VendorCategory> existingCategoryOpt = vendorCategoryRepo.findById(categoryId);

        if (!existingCategoryOpt.isPresent()) {
            throw new EntityNotFoundException("Vendor category not found with id: " + categoryId);
        }

        VendorCategory existingCategory = existingCategoryOpt.get();

        existingCategory.setVendorCategoryName(newCategoryName);
        existingCategory.setUpdatedBy(userId);
        existingCategory.setUpdatedAt(new Date());

        return vendorCategoryRepo.save(existingCategory);
    }

    public Map<String, Object> getVendorCategoryWithSubCategories(Long categoryId) {
        List<Object[]> result = vendorCategoryRepo.findCategoryWithSubCategoriesAndUsers(categoryId);

        Map<String, Object> response = new HashMap<>();

        if (result == null || result.isEmpty()) {
            response.put("categoryId", categoryId);
            response.put("categoryName", "");
            response.put("subCategories", new ArrayList<>());
            return response;
        }

        response.put("categoryId", result.get(0)[0]);
        response.put("categoryName", result.get(0)[1]);

        Map<Long, Map<String, Object>> subCategoryMap = new HashMap<>();

        for (Object[] row : result) {
            Long subCategoryId = (Long) row[2];
            String subCategoryName = (String) row[3];

            // If either vendorCategoryResearchTat or vendorCompletionTat is null, skip adding this subcategory
            if (row[4] == null || row[5] == null) {
                continue; // Skip this row
            }

            int vendorCategoryResearchTat = (Integer) row[4];
            int vendorCompletionTat = (Integer) row[5];

            if (!subCategoryMap.containsKey(subCategoryId)) {
                Map<String, Object> subCategory = new HashMap<>();
                subCategory.put("subCategoryId", subCategoryId);
                subCategory.put("subCategoryName", subCategoryName);
                subCategory.put("vendorCategoryResearchTat", vendorCategoryResearchTat);
                subCategory.put("vendorCompletionTat", vendorCompletionTat);
                subCategory.put("assignedUsers", new ArrayList<Map<String, Object>>());

                subCategoryMap.put(subCategoryId, subCategory);
            }

            if (row[6] != null) {
                Map<String, Object> user = new HashMap<>();
                user.put("userId", row[6]);
                user.put("userName", row[7]);
                user.put("userEmail", row[8]);

                List<Map<String, Object>> assignedUsers = (List<Map<String, Object>>) subCategoryMap.get(subCategoryId).get("assignedUsers");
                assignedUsers.add(user);
            }
        }

        response.put("subCategories", new ArrayList<>(subCategoryMap.values()));
        return response;
    }



    @Override
    public List<VendorCategoryDTO> getAllVendorCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        List<VendorCategory> vendorCategories = vendorCategoryRepo.findEnabledAndNotDeleted(pageable);

        Map<Long, String> userMap = new HashMap<>();

        List<Long> userIds = vendorCategories.stream()
                .map(VendorCategory::getAddedBy)
                .distinct()
                .collect(Collectors.toList());

        List<User> users = userRepo.findAllById(userIds);

        for (User user : users) {
            userMap.put(user.getId(), user.getFullName());
        }

        List<VendorCategoryDTO> vendorCategoryDTOS = vendorCategories.stream()
                .map(vendorCategory -> new VendorCategoryDTO(
                        vendorCategory.getId(),
                        vendorCategory.getVendorCategoryName(),
                        vendorCategory.isEnable(),
                        vendorCategory.getDate(),
                        vendorCategory.getAddedBy(),
                        userMap.get(vendorCategory.getAddedBy())
                ))
                .collect(Collectors.toList());

        return vendorCategoryDTOS;
    }










}
