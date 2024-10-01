package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.ServiceDetails;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Long> {

}
