package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Organization.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
