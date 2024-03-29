
package com.lead.dashboard.controller.leadController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.repository.NodeRepository;
import com.lead.dashboard.repository.SubNodesRepository;
import com.lead.dashboard.repository.SuperSubNodesRepository;
import com.lead.dashboard.service.NodeService;
import com.lead.dashboard.util.UrlsMapping;
import com.lead.dashboard.domain.Nodes;
import com.lead.dashboard.domain.SubNodes;
import com.lead.dashboard.domain.SuperSubNodes;

import java.util.*;

@RestController
public class NodeController {

	@Autowired
	NodeRepository nodeRepository;
	
	@Autowired
	SuperSubNodesRepository superSubNodesRepository;
	@Autowired
	SubNodesRepository subNodesRepository;
	
	@Autowired
	NodeService nodeService;
	
//	@PutMapping(UrlsMapping.CREATE_NODE)
	public Nodes createNode()
	{
		Nodes n = new Nodes();
		n.setName("Sales");
		List<SubNodes>subNodeList1 = new ArrayList<>();
		SubNodes sn1 = new SubNodes();
		sn1.setName("inbox");
		subNodesRepository.save(sn1);
//
		SubNodes sn2 = new SubNodes();
		sn2.setName("lead");
		List<SuperSubNodes>superSubNodes1 = new ArrayList<>();

		SuperSubNodes ssn1 = new SuperSubNodes();
		ssn1.setName("add");
		SuperSubNodes ssn2 = new SuperSubNodes();
		ssn2.setName("edit");
		SuperSubNodes ssn3 = new SuperSubNodes();
		ssn3.setName("delete");
		superSubNodes1.add(ssn1);
		superSubNodes1.add(ssn2);
		superSubNodes1.add(ssn3);

		sn2.setSubNodeChild(superSubNodes1);
		subNodesRepository.save(sn2);
		SubNodes sn3 = new SubNodes();
		sn3.setName("opportunity");
		subNodesRepository.save(sn3);
//
		subNodeList1.add(sn1);
		subNodeList1.add(sn2);
		subNodeList1.add(sn3);
//
		n.setNodeChild(subNodeList1);
		nodeRepository.save(n);

		return n;
	}
	
//	@PutMapping(UrlsMapping.CREATE_NODE)
//	public Nodes createNode()
//	{
//		Nodes n = new Nodes();
//		n.setName("Sales");
//		 List<Nodes> l1List = new ArrayList<>();
//	//    child Node
//		Nodes n1 = new Nodes();
//		n1.setName("Inbox");
//		
//		 List<Nodes> ll1List = new ArrayList<>();
//
//		
//	    Nodes nn1 = new Nodes();
//	    nn1.setName("edit");
//	    Nodes nn2 = new Nodes();
//	    nn2.setName("delete");
//	    ll1List.add(nn1);
//	    ll1List.add(nn2);	    
//	    n1.setNodeChild(ll1List);
//
//		Nodes n2 = new Nodes();
//		n2.setName("Opportunity");
//		
//		Nodes n3 = new Nodes();
//		n3.setName("Lead");	
//	
//		l1List.add(n1);
//		
//		l1List.add(n2);
//		l1List.add(n3);
//		n. setNodeChild(l1List);
//		nodeRepository.save(n);
//		return n;
//		
//	}
	
	@GetMapping(UrlsMapping.GET_NODE)
	public ResponseEntity<List<Nodes>> getNode()
	{
		List<Nodes> nList = nodeRepository.findAll();
		return new ResponseEntity<>(nList,HttpStatus.OK);
	}
	
	@PostMapping(UrlsMapping.CREATE_NODE)
	public Nodes createNode(@RequestParam String name) {
		Nodes n=nodeService.createNode(name);
		return n;
	}
	
	@PutMapping(UrlsMapping.UPDATE_NODE)
	public Nodes updateNode(Long id,String name)
	{
		Nodes n=nodeService.updateNode(id,name);
		return n;
	}
	
	@PutMapping(UrlsMapping.DELETE_NODE)
	public Nodes deleteNode(Long id)
	{
		Nodes n=nodeService.deleteNode(id);
		return n;
	}
	
	//  = = = = = = = = = =  sub Nodes   = = = = = = = = = = = = = = = = = = = = 
	
	@GetMapping(UrlsMapping.GET_SUB_NODE)
	public ResponseEntity<List<Nodes>> getSubNode()
	{
		List<Nodes> nList = nodeRepository.findAll();
		return new ResponseEntity<>(nList,HttpStatus.OK);
	}
	
	@PostMapping(UrlsMapping.CREATE_SUB_NODE)
	public SubNodes createSubNode(@RequestParam String name,@RequestParam Long nodeId) {
		SubNodes n=nodeService.createSubNode(name,nodeId);
		return n;
	}
	
	@PutMapping(UrlsMapping.UPDATE_SUB_NODE)
	public SubNodes updateSubNode(Long id,String name)
	{
		SubNodes n=nodeService.updateSubNode(id,name);
		return n;
	}
	
	@PutMapping(UrlsMapping.DELETE_SUB_NODE)
	public SubNodes deleteSubNode(Long id)
	{
		SubNodes n=nodeService.deleteSubNode(id);
		return n;
	}
	
	//= = = = = = = = = = = = = super sub nodes = = = = = = = = = = = = = 
	
	@GetMapping(UrlsMapping.GET_SUPER_SUB_NODE)
	public ResponseEntity<List<Nodes>> getSuperSubNode()
	{
		List<Nodes> nList = nodeRepository.findAll();
		return new ResponseEntity<>(nList,HttpStatus.OK);
	}
	
	@PostMapping(UrlsMapping.CREATE_SUPER_SUB_NODE)
	public SuperSubNodes createSuperSubNode(@RequestParam String name,@RequestParam Long subNodeId) {
		SuperSubNodes n=nodeService.createSuperSubNode(name,subNodeId);
		return n;
	}
	
	@PutMapping(UrlsMapping.UPDATE_SUPER_SUB_NODE)
	public SuperSubNodes updateSuperSubNode(Long id,String name)
	{
		SuperSubNodes n=nodeService.updateSuperSubNode(id,name);
		return n;
	}
	
	@PutMapping(UrlsMapping.DELETE_SUPER_SUB_NODE)
	public SuperSubNodes deleteSuperSubNode(Long id)
	{
		SuperSubNodes n=nodeService.deleteSuperSubNode(id);
		return n;
	}
	
	
	
	
	
}
