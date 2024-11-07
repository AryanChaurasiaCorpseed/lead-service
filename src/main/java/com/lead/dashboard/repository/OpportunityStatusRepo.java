package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.opportunity.OpportunityStatus;

@Repository
public interface OpportunityStatusRepo extends JpaRepository<OpportunityStatus,Long> {

}
