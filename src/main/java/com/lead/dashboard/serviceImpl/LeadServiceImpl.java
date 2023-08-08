package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.service.LeadService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadServiceImpl implements LeadService  {

    @Autowired
	LeadRepository leadRepository;

	@Autowired
	CommonServices commonServices;

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
	public List<Lead> getAllActiveCustomerLead() {


		return leadRepository.findAllByDisplayStatusAndIsDeleted("1",true);
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
			leadData.setLastUpdated(updateLeadDto.getLastUpdated());
			leadData.setCategoryId(updateLeadDto.getCategoryId());
			leadData.setUrls(updateLeadDto.getUrls());
			leadData.setLatestStatusChangeDate(updateLeadDto.getLatestStatusChangeDate());
	        Lead updatedoneLead = leadRepository.save(leadData);
			return updatedoneLead;

		}
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

//	public Lead createClientInLead(Long leadId,String name ,String email,String contactNo) {
//
//
//    }
//


	}
