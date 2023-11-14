package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Communication;

@Repository
public interface CommunicationRepository extends JpaRepository<Communication, Long> { 

}
