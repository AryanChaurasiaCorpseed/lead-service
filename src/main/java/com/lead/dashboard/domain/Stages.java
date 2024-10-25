package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
public class Stages {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	
	String name;
	String noOfDays;
	int stageNo;
	int transferPercent;
	int pricePercent;
	
	boolean isDeleted;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getStageNo() {
		return stageNo;
	}
	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}
	public int getTransferPercent() {
		return transferPercent;
	}
	public void setTransferPercent(int transferPercent) {
		this.transferPercent = transferPercent;
	}
	public int getPricePercent() {
		return pricePercent;
	}
	public void setPricePercent(int pricePercent) {
		this.pricePercent = pricePercent;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
