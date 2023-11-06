package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.opportunity.OpportunityStatus;

public interface OpportunityStatusRepo extends JpaRepository<OpportunityStatus,Long> {

}
