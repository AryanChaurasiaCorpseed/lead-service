package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.lead.Lead;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	@Query(value = "SELECT c.id FROM company c left join company_lead cl on c.id=cl.company_id where cl.company_lead_id=:leadId LIMIT 1", nativeQuery = true)
	Long findCompanyIdByLeadId(Long leadId);

	@Query(value = "SELECT * FROM company c where c.assignee_id in(:userList)", nativeQuery = true)
	List<Company> findAllByAssigneeIdIn(List<Long> userList);

	@Query(value = "SELECT * FROM company c where c.assignee_id=:userId", nativeQuery = true)
	List<Company> findByAssigneeId(Long userId);
	
	@Query(value = "SELECT * FROM company c where c.is_parent=false", nativeQuery = true)
	List<Company> findAllByIsParent();
	
	@Query(value = "SELECT * FROM company c where c.name=:name", nativeQuery = true)
	Company findByName(String name);

	@Query(value = "SELECT * FROM company c where c.id=:id || c.parent_id=:id", nativeQuery = true)
	List<Company> findAllCompanyUnitByCompanyId(Long id);
	
	
	@Query(value = "SELECT * FROM company c where c.gst_no=:gst", nativeQuery = true)
	List<Company> findByGst(String gst);

	@Query(value = "SELECT ct.emails FROM company c left join contact ct on ct.id=c.id ", nativeQuery = true)
	List<String> findAllEmail();

}
