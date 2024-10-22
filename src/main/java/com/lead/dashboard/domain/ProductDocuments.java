package com.lead.dashboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ProductDocuments {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	Long id;
	String name;
	String description;
	String type;
	Boolean isDeleted;
}
