package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.lead.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

	@Query(value = "SELECT * FROM proposal WHERE assignee_id=:userId", nativeQuery = true)
	List<Proposal> findAllByUserId(Long userId);

}
