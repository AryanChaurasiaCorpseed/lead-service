package com.lead.dashboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorReportResponse {

    private Long id ;

    private String generateByPersonName;

    private String assignedToPersonName;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalDate completionDate;

    private String clientName;

	private String clientCompany;

	private String clientMobileNumber;

    private String currentStatus;

	private String categoryName;

    private String subCategoryName;

    private int vendorCategoryResearchTat = 0;

    private int vendorCompletionTat =0;

    private int completionDays;

    private int tatDaysLeft;

    private int overDueTat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenerateByPersonName() {
		return generateByPersonName;
	}

	public void setGenerateByPersonName(String generateByPersonName) {
		this.generateByPersonName = generateByPersonName;
	}


	public String getAssignedToPersonName() {
		return assignedToPersonName;
	}

	public void setAssignedToPersonName(String assignedToPersonName) {
		this.assignedToPersonName = assignedToPersonName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public int getVendorCategoryResearchTat() {
		return vendorCategoryResearchTat;
	}

	public void setVendorCategoryResearchTat(int vendorCategoryResearchTat) {
		this.vendorCategoryResearchTat = vendorCategoryResearchTat;
	}

	public int getVendorCompletionTat() {
		return vendorCompletionTat;
	}

	public void setVendorCompletionTat(int vendorCompletionTat) {
		this.vendorCompletionTat = vendorCompletionTat;
	}

	public int getCompletionDays() {
		return completionDays;
	}

	public void setCompletionDays(int completionDays) {
		this.completionDays = completionDays;
	}

	public int getTatDaysLeft() {
		return tatDaysLeft;
	}

	public void setTatDaysLeft(int tatDaysLeft) {
		this.tatDaysLeft = tatDaysLeft;
	}

	public int getOverDueTat() {
		return overDueTat;
	}

	public void setOverDueTat(int overDueTat) {
		this.overDueTat = overDueTat;
	}

	public String getClientMobileNumber() {
		return clientMobileNumber;
	}

	public void setClientMobileNumber(String clientMobileNumber) {
		this.clientMobileNumber = clientMobileNumber;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getClientCompany() {
		return clientCompany;
	}

	public void setClientCompany(String clientCompany) {
		this.clientCompany = clientCompany;
	}
}
