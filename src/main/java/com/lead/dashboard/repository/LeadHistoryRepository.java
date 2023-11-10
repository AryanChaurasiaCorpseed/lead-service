package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.LeadHistory;

@Repository
public interface LeadHistoryRepository extends JpaRepository<LeadHistory, Long> {

	@Query(value = "SELECT * FROM lead_history lh WHERE lh.lead_id =:leadId", nativeQuery = true)
	List<LeadHistory> findByLeadId(Long leadId);

}
