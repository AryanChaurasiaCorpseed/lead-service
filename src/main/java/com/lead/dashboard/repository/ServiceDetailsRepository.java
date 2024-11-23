package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.ServiceDetails;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Long> {

	@Query(value = "SELECT * FROM service_details sd where sd.lead_id=:leadId", nativeQuery = true)
	ServiceDetails findByLeadId(Long leadId);

}
