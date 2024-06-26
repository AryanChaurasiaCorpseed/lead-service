package com.lead.dashboard.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;

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
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByAssigneeAndIsDeleted(Long userId,boolean b);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE  el.status_id=:statusId and el.is_deleted =:b and el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByAssigneeAndStatusAndIsDeleted(Long userId,Long statusId,boolean b);
	
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b", nativeQuery = true)
	List<Lead> findAllByIsDeleted(boolean b);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id=:statusId and el.is_deleted =:b", nativeQuery = true)
	List<Lead> findAllByStatusAndIsDeleted(Long statusId,boolean b); 
	@Query(value = "SELECT * FROM erp_leads el WHERE el.email =:email or el.client_mob_no =:mobileNo", nativeQuery = true)
	List<Lead> findAllByEmailAndMobile(String email, String mobileNo);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.client_mob_no =:mobileNo", nativeQuery = true)
	List<Lead> findAllByMobile(String mobileNo);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.assignee_id in(:userId) and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByAssigneeAndInBetweenDate(Long userId,String d1,String d2);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.assignee_id =:userId ORDER BY create_date DESC  LIMIT 5", nativeQuery = true)
	List<Lead> findAllByAssigneeLatest(Long userId);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByIsDeletedAndInBetweenDate(boolean b,String d1,String d2);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userId)and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByAssigneeAndIsDeletedAndInBetweenDate(Long userId,boolean b,String d1,String d2);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id=:statusId and el.is_deleted =:b and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByStatusAndIsDeletedAndInBetweenDate(Long statusId,boolean b,String d1,String d2); 
	
	@Query(value = "SELECT * FROM erp_leads el WHERE  el.status_id=:statusId and el.is_deleted =:b and el.assignee_id in(:userId) and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByAssigneeAndStatusAndIsDeletedAndInBetweenDate(Long userId,Long statusId,boolean b,String d1,String d2);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.id in(:id)", nativeQuery = true)
	List<Lead> findAllByIsDeletedAndIdIn(List<Long> id,boolean b);
	//=== changes in Lead 
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and create_date BETWEEN :d1 AND :d2 and  el.assignee_id in(:userIds)", nativeQuery = true)
	List<Lead> findAllByIsDeletedAndInBetweenDateAndAssigneeIdIn(boolean b,String d1,String d2,List<Long>userIds);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userIds)", nativeQuery = true)
	List<Lead> findAllByIsDeleted(boolean b,List<Long>userIds);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndIsDeletedAndInBetweenDate(List<Long> statusIds,boolean b,String d1,String d2); 

	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and create_date BETWEEN :d1 AND :d2 and el.assignee_id in(:userIds)", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(List<Long> statusIds,boolean b,String d1,String d2,List<Long>userIds); 

	@Query(value = "SELECT * FROM erp_leads el WHERE  el.status_id in(:statusId) and el.is_deleted =:b and el.assignee_id in(:userId) and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByAssigneeAndStatusIdInAndIsDeletedAndInBetweenDate(Long userId,List<Long> statusId,boolean b,String d1,String d2);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE  el.status_id in(:statusId) and el.is_deleted =:b and el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByAssigneeAndStatusIdInAndIsDeleted(Long userId,List<Long> statusId,boolean b);
	
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndIsDeleted(List<Long> statusIds,boolean b); 
	
	//====Auto
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and el.auto=:auto", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndIsDeletedAndAuto(List<Long> statusIds,boolean b,boolean auto); 
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndAssigneeIdInAndIsDeleted(List<Long> statusIds,List<Long>userId,boolean b); 
    
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id =:statusId and el.is_deleted =:b", nativeQuery = true)
	List<Lead> findAllByStatusIdAndIsDeleted(Long statusId,boolean b);

	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id =:statusId and el.is_deleted =:b and el.assignee_id in(:userId)", nativeQuery = true)
	List<Lead> findAllByStatusIdAndAssigneeAndIsDeleted(Long statusId,Long userId,boolean b);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userId) and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
	List<Lead> findAllByAssigneeAndStatusIdInAndIsDeletedAndInBetweenDate(Long userId,boolean b,String d1,String d2);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id =:statusId and el.auto =:auto", nativeQuery = true)
	List<Lead> findAllByStatusIdAndAuto(long statusId, boolean auto);
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and el.assignee_id in(:userId) and el.original_name in(:originalName) and el.auto=true", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndAssigneeIdInAndOriginalNameInAndIsDeleted(List<Long> statusIds,List<Long>userId,List<String>originalName,boolean b);
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =false and el.original_name in(:originalName)", nativeQuery = true)
	List<Lead> findAllByStatusIdInAndOriginalName(List<Long>statusIds,String originalName);
	
	@Query(value = "SELECT count(id) FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =false and assignee_id =:assigneeId", nativeQuery = true)
	int findCountByAssigneeId(Long assigneeId, List<Long> statusIds);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.id in(:leadIds) and el.is_deleted =false", nativeQuery = true)
	List<Lead> findAllByIdIn(List<Long> leadIds); 
	
	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =false and assignee_id in(:assigneeIds)", nativeQuery = true)
	List<Lead> findAllByAssigneeIdInAndStatusIdIn(List<Long> assigneeIds,List<Long> statusIds);
}
