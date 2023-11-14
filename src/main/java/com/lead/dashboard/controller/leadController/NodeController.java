package com.lead.dashboard.controller.leadController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.repository.NodeRepository;
import com.lead.dashboard.util.UrlsMapping;
import com.lead.dashboard.domain.Nodes;
import java.util.*;

@RestController
public class NodeController {

	@Autowired
	NodeRepository nodeRepository;
	
	
	@PutMapping(UrlsMapping.CREATE_NODE)
	public Nodes createNode()
	{
		Nodes n = new Nodes();
		n.setName("Sales");
		 List<Nodes> l1List = new ArrayList<>();
	//    child Node
		Nodes n1 = new Nodes();
		n1.setName("Inbox");
		
		 List<Nodes> ll1List = new ArrayList<>();

		
	    Nodes nn1 = new Nodes();
	    nn1.setName("edit");
	    Nodes nn2 = new Nodes();
	    nn2.setName("delete");
	    ll1List.add(nn1);
	    ll1List.add(nn2);	    
	    n1.setNodeChild(ll1List);

		Nodes n2 = new Nodes();
		n2.setName("Opportunity");
		
		Nodes n3 = new Nodes();
		n3.setName("Lead");	
	
		l1List.add(n1);
		
		l1List.add(n2);
		l1List.add(n3);
		n. setNodeChild(l1List);
		nodeRepository.save(n);
		return n;
		
	}
	
	@PutMapping(UrlsMapping.GET_NODE)
	public ResponseEntity<List<Nodes>> getNode()
	{
		List<Nodes> nList = nodeRepository.findAll();
		return new ResponseEntity<>(nList,HttpStatus.OK);
	}
	
}
