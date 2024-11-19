package com.lead.dashboard.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;


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

	@Query(value = "SELECT count(*) FROM erp_leads el WHERE el.is_deleted =:b", nativeQuery = true)
	long findCountByIsDeleted(boolean b);

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

	// ===============================================      pagination=====================================



	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and create_date BETWEEN :d1 AND :d2 and el.assignee_id in(:userIds) ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(List<Long> statusIds,boolean b,String d1,String d2,List<Long>userIds,Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and create_date BETWEEN :d1 AND :d2 ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByStatusIdInAndIsDeletedAndInBetweenDate(List<Long> statusIds,boolean b,String d1,String d2,Pageable pageable); 

	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userId)and create_date BETWEEN :d1 AND :d2 ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByAssigneeAndIsDeletedAndInBetweenDate(Long userId,boolean b,String d1,String d2, Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b and el.assignee_id in(:userId) ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByStatusIdInAndAssigneeIdInAndIsDeleted(List<Long> statusIds,List<Long>userId,boolean b, Pageable pageable); 


	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id in(:statusIds) and el.is_deleted =:b ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByStatusIdInAndIsDeleted(List<Long> statusIds,boolean b,Pageable pageable); 

	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userId) ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByAssigneeAndIsDeleted(Long userId,boolean b,Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.status_id in(:statusIds) and el.assignee_id in(:userId) ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByAssigneeAndStatusIdInAndIsDeleted(Long userId,List<Long> statusIds,boolean b,Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and create_date BETWEEN :d1 AND :d2 and  el.assignee_id in(:userIds) ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByIsDeletedAndInBetweenDateAndAssigneeIdIn(boolean b,String d1,String d2,List<Long>userIds,Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and create_date BETWEEN :d1 AND :d2 ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByIsDeletedAndInBetweenDate(boolean b,String d1,String d2,Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.is_deleted =:b and el.assignee_id in(:userIds) ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByIsDeleted(boolean b,List<Long>userIds,Pageable pageable);

	@Query(value = "SELECT * FROM erp_leads el WHERE el.status_id =:statusId and el.is_deleted =:b ORDER BY id desc", nativeQuery = true)
	Page<Lead> findAllByStatusIdAndIsDeleted(Long statusId,boolean b,Pageable pageable);

	// =========================== optimize =========
	@Query(value = "SELECT l.id,l.lead_name,l.count,l.client_mob_no as client_contact_no,l.email as client_email,sd.status_name ,u.full_name as assignee_name,u.email as assignee_email,l.create_date,l.source,l.is_reopen_by_quality ,ua.email as reopen_by_quality  FROM finalerp.erp_leads l left join status_details sd on sd.id=l.status_id left join user u on u.id=l.assignee_id  left join user ua on ua.id=l.reopen_marked_by_id WHERE l.status_id in(:statusIds) and l.is_deleted =:b and create_date BETWEEN :d1 AND :d2 and l.assignee_id in(:userIds) ORDER BY id desc", nativeQuery = true)
	List<Object[]> findByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(List<Long> statusIds,boolean b,String d1,String d2,List<Long>userIds);

	@Query(value = "SELECT l.id,l.lead_name,l.count,l.client_mob_no as client_contact_no,l.email as client_email,sd.status_name ,u.full_name as assignee_name,u.email as assignee_email,l.create_date,l.source,l.is_reopen_by_quality ,ua.email as reopen_by_quality  FROM finalerp.erp_leads l left join status_details sd on sd.id=l.status_id left join user u on u.id=l.assignee_id  left join user ua on ua.id=l.reopen_marked_by_id WHERE l.is_deleted =:b and l.assignee_id in(:userId)and l.create_date BETWEEN :d1 AND :d2 ORDER BY id desc", nativeQuery = true)
	List<Object[]> findByAssigneeAndIsDeletedAndInBetweenDate(Long userId,boolean b,String d1,String d2);
	
	@Query(value = "SELECT l.id,l.lead_name,l.count,l.client_mob_no as client_contact_no,l.email as client_email,sd.status_name ,u.full_name as assignee_name,u.email as assignee_email,l.create_date,l.source,l.is_reopen_by_quality ,ua.email as reopen_by_quality  FROM finalerp.erp_leads l left join status_details sd on sd.id=l.status_id left join user u on u.id=l.assignee_id  left join user ua on ua.id=l.reopen_marked_by_id  WHERE l.status_id in(:statusIds) and l.is_deleted =:b and l.assignee_id in(:userId) ORDER BY id desc", nativeQuery = true)
	List<Object[]> findByStatusIdInAndAssigneeIdInAndIsDeleted(List<Long> statusIds,List<Long>userId,boolean b); 
	
	@Query(value = "SELECT l.id,l.lead_name,l.count,l.client_mob_no as client_contact_no,l.email as client_email,sd.status_name ,u.full_name as assignee_name,u.email as assignee_email,l.create_date,l.source,l.is_reopen_by_quality ,ua.email as reopen_by_quality  FROM finalerp.erp_leads l left join status_details sd on sd.id=l.status_id left join user u on u.id=l.assignee_id  left join user ua on ua.id=l.reopen_marked_by_id WHERE l.status_id in(:statusIds) and l.is_deleted =:b ORDER BY id desc", nativeQuery = true)
	List<Object[]> findByStatusIdInAndIsDeleted(List<Long> statusIds,boolean b);
	
	@Query(value = "SELECT l.id,l.lead_name,l.count,l.client_mob_no as client_contact_no,l.email as client_email,sd.status_name ,u.full_name as assignee_name,u.email as assignee_email,l.create_date,l.source,l.is_reopen_by_quality ,ua.email as reopen_by_quality  FROM finalerp.erp_leads l left join status_details sd on sd.id=l.status_id left join user u on u.id=l.assignee_id  left join user ua on ua.id=l.reopen_marked_by_id  WHERE l.is_deleted =:b and l.status_id in(:statusIds) and l.assignee_id in(:userId) ORDER BY id desc", nativeQuery = true)
	List<Object[]> findByAssigneeAndStatusIdInAndIsDeleted(Long userId,List<Long> statusIds,boolean b);
	
	@Query(value = "SELECT l.id,l.lead_name,l.count,l.client_mob_no as client_contact_no,l.email as client_email,sd.status_name ,u.full_name as assignee_name,u.email as assignee_email,l.create_date,l.source,l.is_reopen_by_quality ,ua.email as reopen_by_quality  FROM finalerp.erp_leads l left join status_details sd on sd.id=l.status_id left join user u on u.id=l.assignee_id  left join user ua on ua.id=l.reopen_marked_by_id WHERE l.status_id in(:statusIds) and l.is_deleted =:b and l.create_date BETWEEN :d1 AND :d2 ORDER BY id desc", nativeQuery = true)
	List<Object[]> findByStatusIdInAndIsDeletedAndInBetweenDate(List<Long> statusIds,boolean b,String d1,String d2); 


	//====================== end =============================================
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


	@Query("SELECT l FROM Lead l WHERE l.mobileNo LIKE %:mobileNo% AND l.isDeleted =false")
	List<Lead> findAllByMobileNo(@Param("mobileNo") String mobileNo);

	@Query("SELECT l FROM Lead l WHERE l.email LIKE %:email% AND l.isDeleted =false")
	List<Lead> findAllByEmail(@Param("email") String email);

	@Query("SELECT l FROM Lead l WHERE LOWER(l.leadName) LIKE LOWER(CONCAT('%', :leadName, '%'))")
	List<Lead> findAllByLeadNameContaining(@Param("leadName") String leadName);
	//	@Query(value = "SELECT * FROM erp_leads l WHERE l.client_mob_no = :searchParam AND l.assignee_id = :userId", nativeQuery = true)
	//	List<Lead> findAllByMobileNoAndAssignee(@Param("searchParam") String searchParam, @Param("userId") Long userId);
	//
	//
	//	@Query(value = "SELECT * FROM erp_leads l WHERE l.email = :searchParam AND l.assignee_id = :userId", nativeQuery = true)
	//	List<Lead> findAllByEmailAndAssignee(@Param("searchParam") String searchParam, @Param("userId") Long userId);
	//	
	@Query(value = "SELECT * FROM erp_leads l WHERE l.client_mob_no LIKE %:searchParam% AND l.assignee_id = :userId", nativeQuery = true)
	List<Lead> findAllByMobileNoAndAssignee(@Param("searchParam") String searchParam, @Param("userId") Long userId);

	@Query(value = "SELECT * FROM erp_leads l WHERE l.email LIKE %:searchParam%  AND l.assignee_id = :userId", nativeQuery = true)
	List<Lead> findAllByEmailAndAssignee(@Param("searchParam") String searchParam, @Param("userId") Long userId);


    @Query(value = "SELECT l.id,l.lead_name,l.create_date FROM erp_leads l WHERE create_date BETWEEN :d1 AND :d2", nativeQuery = true)
 	 List<Object[]> findIdAndNameAndCreateDateByInBetweenDate(String d1,String d2);
 	 
     @Query(value = "SELECT count(*) FROM erp_leads l WHERE status_id=:statusId and source=:source and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
  	 Long findCountBySourceAndInBetweenDate(Long statusId,String source,String d1,String d2);

     @Query(value = "SELECT count(*) FROM erp_leads l WHERE status_id=:statusId and is_reopen_by_quality=true and create_date BETWEEN :d1 AND :d2", nativeQuery = true)
  	 Long findCountByIsReopenByQualityAndSourceAndInBetweenDate(Long statusId,String d1,String d2);
     
     @Query(value = "SELECT count(*) FROM erp_leads l WHERE status_id=:statusId and source=:source", nativeQuery = true)
  	 Long findCountBySource(Long statusId,String source);
     
     @Query(value = "SELECT count(*) FROM erp_leads l WHERE is_reopen_by_quality=true", nativeQuery = true)
  	 Long findCountByIsReopenByQuality(Long statusId);
     

}
