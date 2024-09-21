package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.serviceImpl.CompanyDataHistory;

@Repository
public interface CompanyHistoryRepo extends JpaRepository<CompanyDataHistory, Long> {

}
