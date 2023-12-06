package com.lead.dashboard.repository;

import java.util.List;
import java.util.Optional;

import com.lead.dashboard.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.lead.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
             
	Optional<Lead> findById(Long id);
	Client getClientById(Long clientID);

	List<Lead> findAllByDisplayStatusAndIsDeleted(String number, boolean b);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByAssignee(Long userId);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE  el.status_id=:statusId and el.is_deleted =:b and el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByAssigneeAndStatusAndIsDeleted(Long userId,Long statusId,boolean b);
	
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b", nativeQuery = true)
	List<Lead> findAllByIsDeleted(boolean b);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id=:statusId and el.is_deleted =:b", nativeQuery = true)
	List<Lead> findAllByStatusAndIsDeleted(Long statusId,boolean b); 
	@Query(value = "SELECT * FROM erp_leads el WHERE el.email =:email or el.client_mob_no =:mobileNo", nativeQuery = true)
	List<Lead> findAllByEmailAndMobile(String email, String mobileNo);

}
