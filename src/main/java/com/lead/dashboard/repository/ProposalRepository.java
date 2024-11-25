package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.lead.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

	@Query(value = "SELECT * FROM proposal WHERE assignee_id=:userId", nativeQuery = true)
	List<Proposal> findAllByUserId(Long userId);
	
	@Query(value = "SELECT * FROM proposal WHERE assignee_id=:userId", nativeQuery = true)
	Page<Proposal> findAllByUserId(Long userId,Pageable pageable);
	
	@Query(value = "SELECT count(*) FROM proposal WHERE assignee_id=:userId", nativeQuery = true)
	long findCountByUserId(Long userId);

}
