package com.lead.dashboard.controller.vendorController;


import com.lead.dashboard.domain.vendor.VendorSubCategory;
import com.lead.dashboard.dto.request.VendorSubCategoryDTO;
import com.lead.dashboard.dto.request.VendorSubCategoryRequest;
import com.lead.dashboard.service.vendorServices.VendorSubCategoryService;
import com.lead.dashboard.util.UrlsMapping;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class VendorSubCategoryController {


    @Autowired
    private VendorSubCategoryService vendorSubCategoryService;


    @PostMapping(value = UrlsMapping.CREATE_VENDOR_SUB_CATEGORY)
    public ResponseEntity<Object> createVendorCategory(@RequestParam Long userId, @RequestBody VendorSubCategoryRequest vendorSubCategoryRequest) {
        try {
            VendorSubCategory createdSubCategory = vendorSubCategoryService.createSubVendorCategory(userId, vendorSubCategoryRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vendor sub-category created successfully.");
            response.put("subCategoryId", createdSubCategory.getId());
            response.put("subCategoryName", createdSubCategory.getVendorSubCategoryName());
            response.put("createdAt", createdSubCategory.getCreatedAt());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = UrlsMapping.UPDATE_VENDOR_SUB_CATEGORY)
    public ResponseEntity<Object> updateVendorCategory(@RequestParam Long userId, @RequestParam Long categoryId,
                                                       @RequestParam Long subCategoryId, @RequestParam String newSubCategoryName,
                                                       @RequestParam int vendorCategoryResearchTat , @RequestParam int vendorCompletionTat) {
        try {
            VendorSubCategory updateSubVendorCategory = vendorSubCategoryService.updateVendorSubCategory(userId, categoryId, subCategoryId, newSubCategoryName,vendorCategoryResearchTat,vendorCompletionTat);
            return new ResponseEntity<>(updateSubVendorCategory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = UrlsMapping.GET_VENDOR_SUB_CATEGORY)
    public ResponseEntity<Object> getVendorCategory(@RequestParam Long subCategoryId) {
        try {
            VendorSubCategory vendorCategory = vendorSubCategoryService.getVendorSubCategoryById(subCategoryId);
            return new ResponseEntity<>(vendorCategory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = UrlsMapping.GET_ALL_VENDOR_SUB_CATEGORIES)
    public ResponseEntity<Object> getAllVendorCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<VendorSubCategoryDTO> vendorSubCategories = vendorSubCategoryService.getAllVendorSubCategories(page, size);
            return new ResponseEntity<>(vendorSubCategories, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = UrlsMapping.ASSIGN_USERS_TO_SUB_CATEGORY)
    public ResponseEntity<Object> assignUsersToSubCategory(@RequestParam Long subCategoryId, @RequestBody List<Long> userIds) {
        try {
            VendorSubCategory updatedSubCategory = vendorSubCategoryService.assignUsersToSubCategory(subCategoryId, userIds);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Users assigned to sub-category successfully.");
            response.put("subCategoryId", updatedSubCategory.getId());
            response.put("subCategoryName", updatedSubCategory.getVendorSubCategoryName());

            List<Map<String, String>> usersList = updatedSubCategory.getAssignedUsers().stream()
                    .map(user -> {
                        Map<String, String> userInfo = new HashMap<>();
                        userInfo.put("fullName", user.getFullName());
                        userInfo.put("email", user.getEmail());
                        return userInfo;
                    })
                    .collect(Collectors.toList());
            response.put("assignedUsers", usersList);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
