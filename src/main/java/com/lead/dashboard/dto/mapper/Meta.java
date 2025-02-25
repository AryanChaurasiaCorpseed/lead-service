package com.lead.dashboard.dto.mapper;

public class Meta {
    String previous;
    String total_count;
    String next;
    int limit;
    int offset;
	public String getPrevious() {
		return previous;
	}
	public String getTotal_count() {
		return total_count;
	}
	public String getNext() {
		return next;
	}
	public int getLimit() {
		return limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public void setTotal_count(String total_count) {
		this.total_count = total_count;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
    
}
