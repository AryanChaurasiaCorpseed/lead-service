package com.lead.dashboard.dto;

import lombok.Data;

@Data
public class PaymentDataDto {

	
	String transactionId;
	Long profFees;
	Long govFees;
	Long ServiceFees;
	Long otherFees;
	Long totalPayment;
	String type; //  1.initiated , 2.verified
	Long serviceId;
	Long leadId;
	boolean isMileStone;
	boolean isFully;
	boolean isPartial;
	
	int docPercent;
	int fillingPercent;
	int liasPercent;
	int certPercent;
	int testPercent;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Long getProfFees() {
		return profFees;
	}
	public void setProfFees(Long profFees) {
		this.profFees = profFees;
	}
	public Long getGovFees() {
		return govFees;
	}
	public void setGovFees(Long govFees) {
		this.govFees = govFees;
	}
	public Long getServiceFees() {
		return ServiceFees;
	}
	public void setServiceFees(Long serviceFees) {
		ServiceFees = serviceFees;
	}
	public Long getOtherFees() {
		return otherFees;
	}
	public void setOtherFees(Long otherFees) {
		this.otherFees = otherFees;
	}
	public Long getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(Long totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Long getLeadId() {
		return leadId;
	}
	public void setLeadId(Long leadId) {
		this.leadId = leadId;
	}
	public boolean isMileStone() {
		return isMileStone;
	}
	public void setMileStone(boolean isMileStone) {
		this.isMileStone = isMileStone;
	}
	public boolean isFully() {
		return isFully;
	}
	public void setFully(boolean isFully) {
		this.isFully = isFully;
	}
	public boolean isPartial() {
		return isPartial;
	}
	public void setPartial(boolean isPartial) {
		this.isPartial = isPartial;
	}
	public int getDocPercent() {
		return docPercent;
	}
	public void setDocPercent(int docPercent) {
		this.docPercent = docPercent;
	}
	public int getFillingPercent() {
		return fillingPercent;
	}
	public void setFillingPercent(int fillingPercent) {
		this.fillingPercent = fillingPercent;
	}
	public int getLiasPercent() {
		return liasPercent;
	}
	public void setLiasPercent(int liasPercent) {
		this.liasPercent = liasPercent;
	}
	public int getCertPercent() {
		return certPercent;
	}
	public void setCertPercent(int certPercent) {
		this.certPercent = certPercent;
	}
	public int getTestPercent() {
		return testPercent;
	}
	public void setTestPercent(int testPercent) {
		this.testPercent = testPercent;
	}
	

}
