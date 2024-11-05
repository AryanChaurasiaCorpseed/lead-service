package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.lead.Proposal;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, Long> {

}
