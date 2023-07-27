package com.lead.dashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.repository.LeadRepository;


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
	
	/*
	 * static List<Lead> Leads = new ArrayList<Lead>(); static long id = 0;
	 * 
	 * public List<Lead> findAll() { return Leads; }
	 * 
	 * public List<Lead> findByTitleContaining(String title) { return
	 * Leads.stream().filter(Lead -> Lead.getTitle().contains(title)).toList(); }
	 * 
	 * public Lead findById(long id) { return Leads.stream().filter(Lead -> id ==
	 * Lead.getId()).findAny().orElse(null); }
	 * 
	 * public Lead save(Lead Lead) { // update Lead if (Lead.getId() != 0) { long
	 * _id = Lead.getId();
	 * 
	 * for (int idx = 0; idx < Leads.size(); idx++) if (_id ==
	 * Leads.get(idx).getId()) { Leads.set(idx, Lead); break; }
	 * 
	 * return Lead; }
	 * 
	 * // create new Lead Lead.setId(++id); Leads.add(Lead); return Lead; }
	 * 
	 * public void deleteById(long id) { Leads.removeIf(Lead -> id == Lead.getId());
	 * }
	 * 
	 * public void deleteAll() { Leads.removeAll(Leads); }
	 * 
	 * public List<Lead> findByPublished(boolean isPublished) { return
	 * Leads.stream().filter(Lead -> isPublished == Lead.isPublished()).toList(); }
	 */
//	public Lead getAllData(Long id){
//		Optional<Lead> lead = leadRepository.findById(id);
//		return lead.get();
//		
//	}

}
