package com.lead.dashboard.dto.response;

import com.lead.dashboard.domain.vendor.VendorUpdateHistory;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendorHistoryUpdated {

    private Long id;
    private String updateDescription;
    private String status;
    private String vendorSharedPrice;
    private boolean proposalSentStatus;
    private Date updateDate;

    private String externalVendorPrice;

    private String externalVendorFilePath;

    private String internalVendorPrices;

    private String internalVendorFilePath;

    private String quotationAmount;

    private String quotationFilePath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpdateDescription() {
		return updateDescription;
	}

	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendorSharedPrice() {
		return vendorSharedPrice;
	}

	public void setVendorSharedPrice(String vendorSharedPrice) {
		this.vendorSharedPrice = vendorSharedPrice;
	}

	public boolean isProposalSentStatus() {
		return proposalSentStatus;
	}

	public void setProposalSentStatus(boolean proposalSentStatus) {
		this.proposalSentStatus = proposalSentStatus;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getExternalVendorPrice() {
		return externalVendorPrice;
	}

	public void setExternalVendorPrice(String externalVendorPrice) {
		this.externalVendorPrice = externalVendorPrice;
	}

	public String getExternalVendorFilePath() {
		return externalVendorFilePath;
	}

	public void setExternalVendorFilePath(String externalVendorFilePath) {
		this.externalVendorFilePath = externalVendorFilePath;
	}

	public String getInternalVendorPrices() {
		return internalVendorPrices;
	}

	public void setInternalVendorPrices(String internalVendorPrices) {
		this.internalVendorPrices = internalVendorPrices;
	}

	public String getInternalVendorFilePath() {
		return internalVendorFilePath;
	}

	public void setInternalVendorFilePath(String internalVendorFilePath) {
		this.internalVendorFilePath = internalVendorFilePath;
	}

	public String getQuotationAmount() {
		return quotationAmount;
	}

	public void setQuotationAmount(String quotationAmount) {
		this.quotationAmount = quotationAmount;
	}

	public String getQuotationFilePath() {
		return quotationFilePath;
	}

	public void setQuotationFilePath(String quotationFilePath) {
		this.quotationFilePath = quotationFilePath;
	}


    

}
