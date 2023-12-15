package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Organization.Plans;


public interface PlansRepository extends JpaRepository<Plans,Long> {

}
