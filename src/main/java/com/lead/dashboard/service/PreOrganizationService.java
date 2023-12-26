package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

@Service
public interface PreOrganizationService {

	boolean createPreOrganizationStatus(Long organizationId);

	 boolean createPreProduct(Long organizationId);

}
