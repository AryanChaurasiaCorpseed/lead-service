package com.lead.dashboard.dto;

import java.util.List;


public class CreatePlantSetup {
   
	Long id;
	List<Long>slugId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getSlugId() {
		return slugId;
	}
	public void setSlugId(List<Long> slugId) {
		this.slugId = slugId;
	}
	
	
}
