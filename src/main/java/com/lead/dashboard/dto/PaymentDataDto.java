package com.lead.dashboard.dto;

import java.util.List;

import lombok.Data;

@Data
public class PaymentDataDto {

	
	List<String> transactionId;
	double profFees;
	double profGst;
	
	double govFees;
	double govGst;
	
	double ServiceFees;
	double serviceGst;
	
	double otherFees;
	double otherGst;
	double totalPayment;
	String type; //  1.initiated , 2.verified
	Long serviceId;
	Long leadId;
	boolean isMileStone;
	boolean isFully;
	boolean isPartial;
	List<String>doc;
	
	int docPercent;
	int fillingPercent;
	int liasPercent;
	int certPercent;
	int testPercent;
	
	boolean isGstApply;

	public List<String> getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(List<String> transactionId) {
		this.transactionId = transactionId;
	}

	public double getProfFees() {
		return profFees;
	}

	public void setProfFees(double profFees) {
		this.profFees = profFees;
	}

	public double getProfGst() {
		return profGst;
	}

	public void setProfGst(double profGst) {
		this.profGst = profGst;
	}

	public double getGovFees() {
		return govFees;
	}

	public void setGovFees(double govFees) {
		this.govFees = govFees;
	}

	public double getGovGst() {
		return govGst;
	}

	public void setGovGst(double govGst) {
		this.govGst = govGst;
	}

	public double getServiceFees() {
		return ServiceFees;
	}

	public void setServiceFees(double serviceFees) {
		ServiceFees = serviceFees;
	}

	public double getServiceGst() {
		return serviceGst;
	}

	public void setServiceGst(double serviceGst) {
		this.serviceGst = serviceGst;
	}

	public double getOtherFees() {
		return otherFees;
	}

	public void setOtherFees(double otherFees) {
		this.otherFees = otherFees;
	}

	public double getOtherGst() {
		return otherGst;
	}

	public void setOtherGst(double otherGst) {
		this.otherGst = otherGst;
	}

	public double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(double totalPayment) {
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

	public List<String> getDoc() {
		return doc;
	}

	public void setDoc(List<String> doc) {
		this.doc = doc;
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

	public boolean isGstApply() {
		return isGstApply;
	}

	public void setGstApply(boolean isGstApply) {
		this.isGstApply = isGstApply;
	}

	


}
