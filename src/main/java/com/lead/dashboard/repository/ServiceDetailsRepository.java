package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.ServiceDetails;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Long> {

	@Query(value = "SELECT * FROM service_details sd where sd.lead_id=:leadId", nativeQuery = true)
	ServiceDetails findByLeadId(Long leadId);
	
	@Query(value = "SELECT * FROM service_details sd where sd.assignee_id=:userId", nativeQuery = true)
	List<ServiceDetails> findAllByAssigneeId(Long userId);
	
	@Query(value = "SELECT count(*) FROM service_details sd", nativeQuery = true)
	long findAllCount();
	
	@Query(value = "SELECT count(*) FROM service_details sd where sd.assignee_id=:userId", nativeQuery = true)
	long findAllCountByAssigneeId(Long userId);
	
	@Query(value = "SELECT * FROM service_details sd where sd.assignee_id=:userId", nativeQuery = true)
	Page<ServiceDetails> findAllByAssigneeId(Long userId,Pageable pageable);
	
	@Query(value = "SELECT * FROM service_details sd where sd.status=:status", nativeQuery = true)
	List<ServiceDetails> findAllByStatus(String status);

}
