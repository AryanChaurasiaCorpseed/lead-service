package com.lead.dashboard.dto;

import lombok.Data;

@Data
public class CreatePlans {
    String name ; 
	String Price;
	String Duration;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	

}
