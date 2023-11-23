package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Company;


public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	

}
