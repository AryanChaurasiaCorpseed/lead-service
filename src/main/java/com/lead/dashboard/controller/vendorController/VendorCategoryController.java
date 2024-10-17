package com.lead.dashboard.controller.vendorController;

import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.dto.request.VendorCategoryDTO;
import com.lead.dashboard.service.vendorServices.VendorCategoryService;
import com.lead.dashboard.util.UrlsMapping;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class VendorCategoryController {

    @Autowired
    private VendorCategoryService vendorCategoryService;

    @PostMapping(value = UrlsMapping.CREATE_VENDOR_CATEGORY)
    public ResponseEntity<Object> createVendorCategory(@RequestParam Long userId, @RequestParam String categoryName) {
        try {
            VendorCategory createdCategory = vendorCategoryService.createVendorCategory(userId, categoryName);
            return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = UrlsMapping.UPDATE_VENDOR_CATEGORY)
    public ResponseEntity<Object> updateVendorCategory(@RequestParam Long userId, @RequestParam Long categoryId,
                                                       @RequestParam String newCategoryName) {
        try {
            VendorCategory updatedCategory = vendorCategoryService.updateVendorCategory(userId, categoryId, newCategoryName);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = UrlsMapping.GET_VENDOR_CATEGORY)
    public ResponseEntity<?> getVendorCategory(@RequestParam Long categoryId) {
        try {
            Map<String, Object> vendorCategory = vendorCategoryService.getVendorCategoryWithSubCategories(categoryId);
            return new ResponseEntity<>(vendorCategory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = UrlsMapping.GET_ALL_VENDOR_CATEGORIES)
    public ResponseEntity<Object> getAllVendorCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<VendorCategoryDTO> vendorCategoryDTOs = vendorCategoryService.getAllVendorCategories(page, size);
            return new ResponseEntity<>(vendorCategoryDTOs, HttpStatus.OK);
        } catch (Exception e) {
            String msg = e.getMessage();
            return new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }








}
