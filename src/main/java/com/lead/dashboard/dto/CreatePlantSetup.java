package com.lead.dashboard.dto;

import java.util.List;

public class CreatePlantSetup {
	
	boolean flag;
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
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
    
    
}
