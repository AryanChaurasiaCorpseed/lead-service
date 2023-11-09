package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.LeadHistory;

public interface LeadHistoryRepository extends JpaRepository<LeadHistory, Long> {

}
