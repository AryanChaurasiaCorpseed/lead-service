package com.lead.dashboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorCategoryDTO {
    private Long id;
    private String vendorCategoryName;
    private boolean isEnable;
    private LocalDate date;
    private Long addedBy;
    private String addedByUserName;
	public VendorCategoryDTO(Long id, String vendorCategoryName, boolean isEnable, LocalDate date, Long addedBy,
			String addedByUserName) {
		super();
		this.id = id;
		this.vendorCategoryName = vendorCategoryName;
		this.isEnable = isEnable;
		this.date = date;
		this.addedBy = addedBy;
		this.addedByUserName = addedByUserName;
	}
    
    
}