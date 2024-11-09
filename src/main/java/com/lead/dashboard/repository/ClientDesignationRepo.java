package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.ClientDesignation;

@Repository
public interface ClientDesignationRepo extends JpaRepository<ClientDesignation, Long> {

}
