package com.lead.dashboard.serviceImpl.inboxServiceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.CommunicationRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.inboxService.InboxService;

@Service
public class InboxServiceImpl implements InboxService{


	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CommunicationRepository communicationRepository ;
	@Override
	public List<Map<String ,Object>>getAllInboxData(Long userId) {
		List<Map<String ,Object>>inboxList = new ArrayList<>();
		List<Lead> leadList = new ArrayList<>();
		//		List<Lead> leadList = leadRepository.findAll();//.stream().map(i->i.getClients()).collect(Collectors.toList());
		Optional<User> user = userRepo.findById(userId);
		if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
			leadList=leadRepository.findAllByIsDeleted(false);
		}else {
			leadList= leadRepository.findAllByAssignee(userId);
		}
		for(Lead l:leadList) {
			Map<String,Object>map = new HashMap<>();
			map.put("name", l.getLeadName());
			List<Communication>commList = new ArrayList<>();	
			List<Client> client = l.getClients();
			for(Client c :client) {
				commList.addAll(c.getCommunication());
			}
			Date latestMessageDate;
			String message="";
			commList.stream().sorted(Comparator.comparing(Communication::getSendDate)).collect(Collectors.toList());
			if(commList.size()!=0) {
				message=commList.get(commList.size()-1).getMessage();
				latestMessageDate=commList.get(commList.size()-1).getSendDate();
				map.put("latestDate", latestMessageDate);
			}
			long count = commList.stream().filter(i->i.getIsView().equals(false)).count();
			map.put("count", count);
			map.put("leadId", l.getId());
			map.put("leadName", l.getName());
			map.put("comment", message);
			map.put("status", l.getStatus());
			map.put("type", l.getSource());
			map.put("assignee", l.getAssignee());		
			inboxList.add(map);
		}
		return inboxList;
	}
	
	
	@Override
	public boolean editView(Long leadId) {
		// TODO Auto-generated method stub
		boolean flag=false;
		Optional<Lead> leadOp = leadRepository.findById(leadId);
		if(leadOp!=null &&leadOp.get()!=null) {
			Lead lead = leadOp.get();
			List<Client> clientList = lead.getClients();
			for(Client c:clientList) {
				List<Communication> listCommunication = c.getCommunication();
				for(Communication cl:listCommunication) {
					cl.setIsView(true);
					communicationRepository.save(cl);
					flag=true;
				}
			}		
		}
		return flag;
	}

}
