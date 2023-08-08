package com.lead.dashboard.service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LeadService {

    Lead createLead(LeadDTO leadDTO);

    List<Lead> getAllActiveCustomerLead();

    Lead updateLeadData(UpdateLeadDto updateLeadDto);

    boolean deleteLead(Long leadId);



//	@Autowired
//	LeadRepository leadRepository;
//	@Autowired
//	UserRepo UserRepo;
//
//	@Autowired
//	StatusRepository statusRepository;
//
//	@Autowired
//	ClientRepository clientRepository;


//	public  Lead createEnquiryLead(String name,String mobNo,String desc) {
//		Lead l = new Lead();
//		l.setLeadDescription(desc);
//		l.setLeadName(name);
//		return leadRepository.save(l);
//
//	}
//
//
//	public List<Lead> getLead() {
//		// TODO Auto-generated method stub
//		List<Lead> listLead = leadRepository.findAll();
//		return listLead;
//	}
//	public Lead getSingleLead(Long id) {
//		// TODO Auto-generated method stub
//		Optional<Lead> listLead = leadRepository.findById(id);
//		return listLead.get();
//	}
//
//
//
//	public  Lead createEnquiryLead(LeadDto leadDto) {
//		Lead l = new Lead();
//		Optional<User> opUser = UserRepo.findById(leadDto.getUserId());
//		l.setLeadName(leadDto.getLeadName());
//		l.setLeadDescription(leadDto.getLeadDescription());
//        l.setContacts(leadDto.getContacts());
//        l.setCreateDate(new Date());
//        l.setLastUpdated(leadDto.getLastUpdated());
//        Status status=statusRepository.findAllByName(leadDto.getStatus());
//        if(opUser!=null && opUser.get()!=null) {
//             l.setUpdatedBy(opUser.get());
//             l.setCreatedBy(opUser.get());
//
//        }
//        l.setStatus(status);
//        l.setLatestStatusChangeDate(new Date());
//        l.setPrimaryAddress(leadDto.getPrimaryAddress());
//         l.setSource(leadDto.getSource());
//         l.setUrls(leadDto.getUrls());
//         return leadRepository.save(l);
//
//	}




//
//
//	public boolean deleteLeadData(Long leadId) {
//		Optional<Lead> opLead = leadRepository.findById(leadId);
//		boolean flag=false;
//		if(opLead!=null && opLead.get()!=null) {
//			Lead lead = opLead.get();
//			lead.setDeleted(true);
//			flag=true;
//			leadRepository.save(lead);
//		}
//		return flag;
//	}
//
////	String name;
////	String emails;
////	String contactNo;
//


}
