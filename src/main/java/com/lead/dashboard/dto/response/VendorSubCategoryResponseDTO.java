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
public class VendorSubCategoryResponseDTO {

    private Long id;
    private String vendorSubCategoryName;
    private List<VendorUserDTO> assignedUsers;
}
