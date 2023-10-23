package com.lead.dashboard.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;



public class CreateLeadStatus {

	    String name;

	     String description;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
}
