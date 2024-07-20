package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Designation;

@Service
public interface DesignationService {

	List<Designation> getAllDesignation();

	Designation createDesignation(String name);

}
