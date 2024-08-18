package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.IndustryData;

public interface IndustryDataRepo extends JpaRepository<IndustryData, Long> {

}
