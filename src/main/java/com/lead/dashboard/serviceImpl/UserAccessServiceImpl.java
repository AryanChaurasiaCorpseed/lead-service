package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.domain.Nodes;
import com.lead.dashboard.repository.NodeRepository;
import com.lead.dashboard.repository.SubNodesRepository;
import com.lead.dashboard.repository.SuperSubNodesRepository;
import com.lead.dashboard.service.NodeService;
import com.lead.dashboard.service.UserAccessService;
import com.lead.dashboard.util.UrlsMapping;

@Service
public class UserAccessServiceImpl implements UserAccessService{
	
	@Autowired
	NodeRepository nodeRepository;
	
	@Autowired
	SuperSubNodesRepository superSubNodesRepository;
	@Autowired
	SubNodesRepository subNodesRepository;
	
	@Autowired
	NodeService nodeService;
	
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
	

}
