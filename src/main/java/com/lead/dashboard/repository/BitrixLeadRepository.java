package com.lead.dashboard.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.BitrixLead;

public interface BitrixLeadRepository extends JpaRepository<BitrixLead, Serializable> {
	
}
