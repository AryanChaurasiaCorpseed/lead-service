package com.lead.dashboard.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.LeadDto;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lead.dashboard.domain.Lead;
//import com.lead.dashboard.repository.LeadRepository;
//
@Service
public class LeadService {
	@Autowired
	LeadRepository leadRepository;
	@Autowired
	UserRepo UserRepo;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	ClientRepository clientRepository;
	

	public  Lead createEnquiryLead(String name,String mobNo,String desc) {
		Lead l = new Lead();
		l.setLeadDescription(desc);
		l.setLeadName(name);
		return leadRepository.save(l);
		
	}


	public List<Lead> getLead() {
		// TODO Auto-generated method stub
		List<Lead> listLead = leadRepository.findAll();
		return listLead;
	}
	public Lead getSingleLead(Long id) {
		// TODO Auto-generated method stub
		Optional<Lead> listLead = leadRepository.findById(id);
		return listLead.get();
	}

	

	public  Lead createEnquiryLead(LeadDto leadDto) {
		Lead l = new Lead();
		Optional<User> opUser = UserRepo.findById(leadDto.getUserId());
		l.setLeadName(leadDto.getLeadName());
		l.setLeadDescription(leadDto.getLeadDescription());
        l.setContacts(leadDto.getContacts());
        l.setCreateDate(new Date());
        l.setLastUpdated(leadDto.getLastUpdated());
        Status status=statusRepository.findAllByName(leadDto.getStatus());
        if(opUser!=null && opUser.get()!=null) {
             l.setUpdatedBy(opUser.get());
             l.setCreatedBy(opUser.get());

        }
        l.setStatus(status);
        l.setLatestStatusChangeDate(new Date());
        l.setPrimaryAddress(leadDto.getPrimaryAddress());
         l.setSource(leadDto.getSource());
         l.setUrls(leadDto.getUrls());
         return leadRepository.save(l);
		
	}
	public Lead updateLeadData(UpdateLeadDto updateLeadDto) {
		Optional<Lead> lead = leadRepository.findById(updateLeadDto.getId());  
		Lead updatedLead  = new Lead();
		
		if(lead!=null) {
			Lead l = lead.get();
			Optional<Status> opStatus = statusRepository.findById(updateLeadDto.getStatusId());
			Status status = opStatus.get();
			if(status!=null) {
				l.setStatus(status);
			}
			l.setLeadName(updateLeadDto.getLeadName());
			l.setLeadDescription(updateLeadDto.getLeadDescription());
	        l.setContacts(updateLeadDto.getContacts());
	        l.setLastUpdated(new Date());
	        l.setLatestStatusChangeDate(updateLeadDto.getLatestStatusChangeDate());
	        l.setPrimaryAddress(updateLeadDto.getPrimaryAddress());
	        l.setSource(updateLeadDto.getSource());
	        l.setUrls(updateLeadDto.getUrls());
	        updatedLead = leadRepository.save(l);
		}
        return updatedLead;
	}
	

	public boolean deleteLeadData(Long leadId) {
		Optional<Lead> opLead = leadRepository.findById(leadId); 
		boolean flag=false;
		if(opLead!=null && opLead.get()!=null) {
			Lead lead = opLead.get();
			lead.setDeleted(true);
			flag=true;
			leadRepository.save(lead);
		}
		return flag;
	}
	
//	String name;
//	String emails;
//	String contactNo;
	
	public Lead createClientInLead(Long leadId,String name ,String email,String contactNo) {
		Client client = new Client();
		client.setName(name);
		client.setContactNo(contactNo);
		client.setEmails(email);
//		clientRepository.save(client);
		Optional<Lead> opLead = leadRepository.findById(leadId);
		Lead lead = opLead.get();
		if(lead.getClients()!=null) {
			List<Client> clientList = lead.getClients();
			clientList.add(client);
			lead.setClients(clientList);
			leadRepository.save(lead);
		}else {
			List<Client> clientList =new ArrayList<>();
			clientList.add(client);
			lead.setClients(clientList);
			leadRepository.save(lead);
		}
		
		return lead;
		
	}




}
