package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.ClientDesignation;
import com.lead.dashboard.repository.ClientDesignationRepo;
import com.lead.dashboard.service.ClientDesignationService;

@Service
public class ClientDesignationServiceImpl implements ClientDesignationService{

	
	@Autowired
	ClientDesignationRepo clientDesignationRepo;
	
	@Override
	public Boolean createClientDesignation(String name) {
		Boolean flag=false;
		ClientDesignation clientDesignation =  new ClientDesignation();
		clientDesignation.setName(name);
		clientDesignationRepo.save(clientDesignation);
		flag=true;
		return flag;
	}

	@Override
	public List<ClientDesignation> getAllClientDesignation() {
		List<ClientDesignation> clientList = clientDesignationRepo.findAll();
		return clientList;
	}

	@Override
	public Boolean deleteClientDesignation(Long id) {
		Boolean flag=false;
		ClientDesignation client = clientDesignationRepo.findById(id).get();
//		client.setIsDeleted(true);
		clientDesignationRepo.save(client);
		flag=true;
		return flag;
	}

	@Override
	public ClientDesignation getClientDesignationById(Long id) {
		ClientDesignation client = clientDesignationRepo.findById(id).get();	
		return client;
	}

	@Override
	public Boolean updateClientDesignation(Long id, String name) {
		Boolean flag=false;
		ClientDesignation client = clientDesignationRepo.findById(id).get();
		client.setName(name);
		clientDesignationRepo.save(client);
		flag=true;
		return flag;
	}

}
