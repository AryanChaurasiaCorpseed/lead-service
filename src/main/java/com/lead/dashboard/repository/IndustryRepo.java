package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Industry;

public interface IndustryRepo extends JpaRepository<Industry, Long> {
}
