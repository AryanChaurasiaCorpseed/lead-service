package com.lead.dashboard.service.vendorServices;

import com.lead.dashboard.domain.vendor.VendorSubCategory;
import com.lead.dashboard.dto.request.VendorSubCategoryDTO;
import com.lead.dashboard.dto.request.VendorSubCategoryRequest;

import java.util.List;

public interface VendorSubCategoryService {


    VendorSubCategory getVendorSubCategoryById(Long subCategoryId);


    VendorSubCategory createSubVendorCategory(Long userId, VendorSubCategoryRequest vendorSubCategoryRequest);

    VendorSubCategory updateVendorSubCategory(Long userId, Long categoryId, Long subCategoryId, String newSubCategoryName);

    List<VendorSubCategoryDTO> getAllVendorSubCategories(int page, int size);

    VendorSubCategory assignUsersToSubCategory(Long subCategoryId, List<Long> userIds);
}
