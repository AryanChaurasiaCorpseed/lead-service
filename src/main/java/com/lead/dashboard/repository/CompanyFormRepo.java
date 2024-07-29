package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.CompanyForm;

@Repository
public interface CompanyFormRepo  extends JpaRepository<CompanyForm, Long> {

	CompanyForm findByLeadId(Long leadId);

	@Query(value = "SELECT * FROM company_form WHERE status=:status", nativeQuery = true)
	List<CompanyForm> findAllByStatus(String status );

}
