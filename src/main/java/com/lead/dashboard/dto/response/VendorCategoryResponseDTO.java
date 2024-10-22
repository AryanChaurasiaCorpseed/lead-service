package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorCategoryResponseDTO {

    private Long id;
    private String vendorCategoryName;
//    private List<VendorSubCategory> vendorSubCategories;
}
