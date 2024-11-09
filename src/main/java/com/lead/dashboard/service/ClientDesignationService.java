package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.ClientDesignation;

@Service
public interface ClientDesignationService {

	Boolean createClientDesignation(String name);

	List<ClientDesignation> getAllClientDesignation();

	Boolean deleteClientDesignation(Long id);

	ClientDesignation getClientDesignationById(Long id);

	Boolean updateClientDesignation(Long id, String name);

}
