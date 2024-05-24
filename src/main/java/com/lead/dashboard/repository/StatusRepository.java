package com.lead.dashboard.repository;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.CreateLeadStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long>
{
    Status getStatusById(Long id);

	Status findAllByName(String status);

	
	@Query(value = "SELECT * FROM status_details sd WHERE sd.enable_auto_assign=:isAutoAssign and sd.delete_status =false", nativeQuery = true)
	List<Status> findByEnableAutoAssign(boolean isAutoAssign);
	@Query(value = "SELECT * FROM status_details sd WHERE sd.enable_auto_assign=:isAutoAssign", nativeQuery = true)
	List<Status> findAllByEnableAutoAssign(boolean isAutoAssign);

//	Status save(CreateLeadStatus status);

}
