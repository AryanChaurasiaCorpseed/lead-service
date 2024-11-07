package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Stages;

public interface StageRepository extends JpaRepository<Stages, Long> {

}
