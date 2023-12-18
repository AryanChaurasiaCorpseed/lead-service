package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Organization.Organization;
import com.lead.dashboard.dto.CreateOrganization;

@Service
public interface OrganizationService {

	Organization createOrganization(CreateOrganization createOrganization);

	Organization getOrganization(Long orgId);

}
