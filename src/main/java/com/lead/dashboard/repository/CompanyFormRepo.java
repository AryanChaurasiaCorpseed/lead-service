package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.Slug;

@Repository
public interface CompanyFormRepo  extends JpaRepository<CompanyForm, Long> {

	CompanyForm findByLeadId(Long leadId);

	@Query(value = "SELECT * FROM company_form WHERE status=:status", nativeQuery = true)
	List<CompanyForm> findAllByStatus(String status );
	
	@Query(value = "SELECT * FROM company_form WHERE status=:status", nativeQuery = true)
	Page<CompanyForm> findAllByStatus(String status,Pageable pageable);

	
	@Query(value = "SELECT * FROM company_form WHERE status=:status and assignee_id=:assigneeId", nativeQuery = true)
	List<CompanyForm> findAllByStatusAndassigneeId(String status,Long assigneeId);
	
	@Query(value = "SELECT * FROM company_form WHERE status=:status and assignee_id=:assigneeId", nativeQuery = true)
	Page<CompanyForm> findAllByStatusAndassigneeId(String status,Long assigneeId,Pageable pageable);

	@Query(value = "SELECT c FROM CompanyForm c WHERE (c.companyName LIKE %:searchTerm% OR c.gstNo LIKE %:searchTerm%) AND c.status = :status")
	Page<CompanyForm> findByCompanyNameOrGstNoAndStatus(@Param("searchTerm") String searchTerm, @Param("status") String status, Pageable pageable);

	@Query(value = "SELECT comment FROM company_form WHERE id=:companyFormId", nativeQuery = true)
	String findCommentById(Long companyFormId);


}
