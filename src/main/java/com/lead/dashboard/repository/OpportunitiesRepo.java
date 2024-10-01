package com.lead.dashboard.repository;

import com.lead.dashboard.domain.opportunity.Opportunities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunitiesRepo extends JpaRepository<Opportunities,Long> {
}
