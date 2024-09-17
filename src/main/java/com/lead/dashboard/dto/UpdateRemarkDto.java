package com.lead.dashboard.dto;

import java.util.List;

public class UpdateRemarkDto {
	
	
	Long remarkId;
	Long userId;
	String message;
	List<String> file;
	String type;
	public Long getRemarkId() {
		return remarkId;
	}
	public void setRemarkId(Long remarkId) {
		this.remarkId = remarkId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getFile() {
		return file;
	}
	public void setFile(List<String> file) {
		this.file = file;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
