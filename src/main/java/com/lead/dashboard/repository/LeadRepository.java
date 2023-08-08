package com.lead.dashboard.repository;

import java.util.List;
import java.util.Optional;

import com.lead.dashboard.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
             
	Optional<Lead> findById(Long id);
	Client getClientById(Long clientID);

	List<Lead> findAllByDisplayStatusAndIsDeleted(String number, boolean b);
}
