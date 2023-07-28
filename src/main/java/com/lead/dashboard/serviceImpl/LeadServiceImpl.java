package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.dto.LeadDto;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.service.LeadService;

public class LeadServiceImpl  {
	@Autowired
	LeadRepository leadRepository;
	

	public  Lead createEnquiryLead(String name,String mobNo,String desc) {
		Lead l = new Lead();
		l.setLeadDescription(desc);
		l.setLeadName(name);
		return leadRepository.save(l);
		
	}
	
	public  Lead createEnquiryLead(LeadDto leadDto) {
		Lead l = new Lead();
		l.setLeadName(leadDto.getLeadName());
		l.setLeadDescription(leadDto.getLeadDescription());
        l.setContacts(leadDto.getContacts());
        l.setCreateDate(leadDto.getCreateDate());
        l.setLastUpdated(leadDto.getLastUpdated());
        l.setLatestStatusChangeDate(leadDto.getLatestStatusChangeDate());
        l.setPrimaryAddress(leadDto.getPrimaryAddress());
         l.setSource(leadDto.getSource());
         l.setUrls(leadDto.getUrls());
         return leadRepository.save(l);
		
	}


	public List<Lead> getLead() {
		List<Lead> listLead = leadRepository.findAll();
		return listLead;
	}
	public Lead getSingleLead(Long id) {
		Optional<Lead> listLead = leadRepository.findById(id);
		return listLead.get();
	}
	
}
