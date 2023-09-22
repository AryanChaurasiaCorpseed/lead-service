package com.lead.dashboard.serviceImpl.inboxServiceimpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.service.inboxService.InboxService;

@Service
public class InboxServiceImpl implements InboxService{

	
	@Autowired
	private LeadRepository leadRepository;
	@Override
	public List<Map<String ,Object>> getAllInboxData() {
		List<Map<String ,Object>>inboxList = new ArrayList<>();
		List<Lead> leadList = leadRepository.findAll();//.stream().map(i->i.getClients()).collect(Collectors.toList());
		for(Lead l:leadList) {
			Map<String,Object>map = new HashMap<>();
			map.put("name", l.getLeadName());
			List<Communication>commList = new ArrayList<>();
			
			List<Client> client = l.getClients();
			for(Client c :client) {
				commList.addAll(c.getCommunication());
			}
			long count = commList.stream().filter(i->i.getIsView().equals(false)).count();
		// count isView Communication
			map.put("count", count);
			map.put("clientData", client);
			map.put("status", l.getStatus());
			map.put("type", l.getSource());
			map.put("assignee", l.getAssignee());		
			inboxList.add(map);
		}
		return inboxList;
		
	}

}
