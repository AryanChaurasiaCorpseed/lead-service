package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.dto.CreateServiceDetails;

import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.service.LeadService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService  {

	@Autowired
	LeadRepository leadRepository;
	@Autowired
	CommonServices commonServices;
	@Autowired
	UserRepo userRepo;

	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	ClientRepository clientRepository;


	public Lead createLead(LeadDTO leadDTO) {
		Lead lead = new Lead();

		lead.setName(leadDTO.getName());
		lead.setLeadName(leadDTO.getLeadName());
		lead.setLeadDescription(leadDTO.getLeadDescription());
		lead.setMobileNo(leadDTO.getMobileNo());
		lead.setEmail(leadDTO.getEmail());
		lead.setUrls(leadDTO.getUrls());
		lead.setCreateDate(leadDTO.getCreateDate());
		lead.setLastUpdated(leadDTO.getLastUpdated());
		lead.setLatestStatusChangeDate(leadDTO.getLatestStatusChangeDate());
		if(leadDTO.getAssigneeId()!=null) {
			Optional<User> user = userRepo.findById(leadDTO.getAssigneeId());
			lead.setAssignee(user.get());
		}

		lead.setSource(leadDTO.getSource());
		lead.setPrimaryAddress(leadDTO.getPrimaryAddress());
		lead.setDeleted(leadDTO.isDeleted());
		lead.setCity(leadDTO.getCity());
		lead.setCategoryId(leadDTO.getCategoryId());
		lead.setServiceId(leadDTO.getServiceId());
		lead.setIndustryId(leadDTO.getIndustryId());
		lead.setIpAddress(leadDTO.getIpAddress());
		lead.setDisplayStatus(leadDTO.getDisplayStatus());
		lead.setWhatsAppStatus(leadDTO.getWhatsAppStatus());
		lead.setUuid(commonServices.getUuid());

		return leadRepository.save(lead);
	}

	@Override
	public List<Lead> getAllActiveCustomerLead(Long uId) {
          
		Optional<User> user = userRepo.findById(uId);
		if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
			return leadRepository.findAllByIsDeleted(false);
		}else {
			return leadRepository.findAllByAssignee(uId);
		}

	}

	public Lead updateLeadData(UpdateLeadDto updateLeadDto) {
		System.out.println(updateLeadDto.getId());
		//		Optional<Status> statusData = statusRepository.findById(statusId);
		Optional<Lead> lead = leadRepository.findById(updateLeadDto.getId());
		System.out.println(lead);
		if(lead!=null) {
			Lead leadData = lead.get();
			leadData.setLeadName(updateLeadDto.getLeadName());
			leadData.setEmail(updateLeadDto.getEmail());
			leadData.setMobileNo(updateLeadDto.getMobileNo());
			leadData.setName(updateLeadDto.getName());
			leadData.setLeadDescription(updateLeadDto.getLeadDescription());
			leadData.setUuid(updateLeadDto.getUuid());
			if(updateLeadDto.getAssigneeId()!=null) {
				Optional<User> user = userRepo.findById(updateLeadDto.getAssigneeId());
				leadData.setAssignee(user.get());
			}

			leadData.setLastUpdated(updateLeadDto.getLastUpdated());
			leadData.setCategoryId(updateLeadDto.getCategoryId());
			leadData.setUrls(updateLeadDto.getUrls());
			leadData.setLatestStatusChangeDate(updateLeadDto.getLatestStatusChangeDate());
			Lead updatedoneLead = leadRepository.save(leadData);
			return updatedoneLead;

		}
		
//		Lead.builder()
//		.name(updateLeadDto.getLeadName())
//		.email(updateLeadDto.getEmail())
//		.build();
		
		return null;
	}

	@Override
	public boolean deleteLead(Long leadId) {

		Optional<Lead> opLead = leadRepository.findById(leadId);
		boolean flag=false;
		if(opLead!=null && opLead.get()!=null)
		{
			Lead lead = opLead.get();
			lead.setDisplayStatus("2");
			lead.setDeleted(false);
			flag=true;
			leadRepository.save(lead);
		}
		return flag;
	}

	@Override
	public Lead getSingleLeadData(Long leadId) {
		// TODO Auto-generated method stub
		Optional<Lead> opLead = leadRepository.findById(leadId);
		boolean flag=false;
		Lead lead = new Lead();
		if(opLead!=null && opLead.get()!=null)
		{
			lead=opLead.get();
		}
		return lead;
	}

	@Override
	public ServiceDetails createEstimate(CreateServiceDetails createservicedetails) {
		
		ServiceDetails service= new ServiceDetails();
		service.setName(createservicedetails.getName());
		service.setCompany(createservicedetails.getCompany());
		service.setConsultingSale(null); //========= for verification
		service.setContact(createservicedetails.getContact());
		service.setEstimateData(createservicedetails.getEstimateData());
		service.setInvoiceNote(createservicedetails.getInvoiceNote());
		service.setOpportunities(null);   //========= for verification
		service.setOrderNumber(createservicedetails.getOrderNumber());
		service.setProductType(createservicedetails.getProductType());
		service.setPurchaseDate(createservicedetails.getPurchaseDate());
		service.setRemarksForOption(createservicedetails.getRemarksForOption());
		ServiceDetails serviceDetails = serviceDetailsRepository.save(service);
		Lead lead = leadRepository.findById(createservicedetails.getLeadId()).get();
		Client c = null;
		List<ServiceDetails> serviceList  = new ArrayList<>();
		Client client=null;
		if(createservicedetails.getClientId()!=null) {
			long cId[] = new long[1];
			cId[0] = createservicedetails.getClientId();
			Optional<Client> opClient = lead.getClients().stream().filter(i->i.getId().equals(cId[0])).findFirst();
			if(opClient!=null && opClient.get()!=null) {
				client=opClient.get();
				serviceList = client.getServiceDetails();
				client.setServiceDetails(serviceList);
				 c=clientRepository.save(client);

			}else {
				client = new Client();
				client.setName(createservicedetails.getClientName());
				client.setEmails(createservicedetails.getEmail());
				client.setContactNo(createservicedetails.getContactNo());
				client.setDeleteStatus(false);
				List<ServiceDetails> sList  = new ArrayList<>();
				sList.add(serviceDetails);
				serviceList=sList;
				client.setServiceDetails(serviceList);  
				 c=clientRepository.save(client);
			}
		}else {
			client = new Client();
			client.setName(createservicedetails.getClientName());
			client.setEmails(createservicedetails.getEmail());
			client.setContactNo(createservicedetails.getContactNo());
			client.setDeleteStatus(false);
			List<ServiceDetails> sList  = new ArrayList<>();
			sList.add(serviceDetails);
			serviceList=sList;
			client.setServiceDetails(serviceList); 
			 c=clientRepository.save(client);
		}
		if(lead.getClients()!=null && lead.getClients().size()!=0) {
			lead.getClients().add(c);
			leadRepository.save(lead);
		}
		return serviceDetails;


	}

	@Override
	public Lead updateAssignee(Long leadId, Long userId) {
		// TODO Auto-generated method stub
	    User user = userRepo.findById(userId).get();
		Lead lead = leadRepository.findById(leadId).get();
        lead.setAssignee(user);
        leadRepository.save(lead);
		return lead;
	}



}
