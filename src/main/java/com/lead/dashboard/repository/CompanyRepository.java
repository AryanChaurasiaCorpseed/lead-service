package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.lead.Lead;


public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query(value = "SELECT c.id FROM company c left join company_lead cl on c.id=cl.company_id where cl.company_lead_id=:leadId", nativeQuery = true)
	Long findCompanyIdByLeadId(Long leadId);
	
	

}
