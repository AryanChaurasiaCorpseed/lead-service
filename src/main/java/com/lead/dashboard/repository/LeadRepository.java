package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead,Long>{

	

}
