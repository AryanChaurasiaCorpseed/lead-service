package com.lead.dashboard.service.vendorServices;



import java.util.List;
import java.util.Map;

import com.lead.dashboard.domain.vendor.VendorCategory;
import com.lead.dashboard.dto.request.VendorCategoryDTO;

public interface VendorCategoryService {

    VendorCategory createVendorCategory(Long userId, String categoryName);

    VendorCategory updateVendorCategory(Long userId, Long categoryId, String newCategoryName);


//    VendorCategory getVendorCategoryById(Long categoryId);

    List<VendorCategoryDTO> getAllVendorCategories(int page, int size);

    Map<String,Object> getVendorCategoryWithSubCategories(Long categoryId);
}
