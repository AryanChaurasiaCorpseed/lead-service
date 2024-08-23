package com.lead.dashboard.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lead.dashboard.domain.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@Data
public class SubSubIndustry {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
    Long id ;
	String name;
	
		@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinTable(name="sub_sub_industry_industry_data",joinColumns = {@JoinColumn(name="sub_sub_industry_id",referencedColumnName="id",nullable=true)},
			inverseJoinColumns = {@JoinColumn(name="sub_sub_industry_industry_data_id"
					+ "",referencedColumnName = "id",nullable=true,unique=false)})
	List<IndustryData>IndustryDataList;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<IndustryData> getIndustryDataList() {
			return IndustryDataList;
		}

		public void setIndustryDataList(List<IndustryData> industryDataList) {
			IndustryDataList = industryDataList;
		}

		

}
