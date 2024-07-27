package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.CompanyForm;

@Repository
public interface CompanyFormRepo  extends JpaRepository<CompanyForm, Long> {

	CompanyForm findByLeadId(Long leadId);

}
