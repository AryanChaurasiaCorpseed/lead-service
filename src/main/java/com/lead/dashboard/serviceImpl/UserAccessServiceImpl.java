package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.domain.Nodes;
import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.SubNodes;
import com.lead.dashboard.domain.SuperSubNodes;
import com.lead.dashboard.repository.NodeRepository;
import com.lead.dashboard.repository.RoleRepository;
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
	RoleRepository roleRepository;

	@Autowired
	SuperSubNodesRepository superSubNodesRepository;
	@Autowired
	SubNodesRepository subNodesRepository;

	@Autowired
	NodeService nodeService;

	//	@GetMapping(UrlsMapping.CREATE_USER_ACCESS)
	//	public ResponseEntity<List<Nodes>> getNode()
	//	{
	//		List<Nodes> nList = nodeRepository.findAll();
	//		return new ResponseEntity<>(nList,HttpStatus.OK);
	//	}
	//	
	//	@PostMapping(UrlsMapping.CREATE_NODE)
	//	public Nodes createNode(@RequestParam String name) {
	//		Nodes n=nodeService.createNode(name);
	//		return n;
	//	}



	@Override
	public boolean createUserAccess(Long roleId) {
		// TODO Auto-generated method stub
		Role role = new Role();
		role.setName("Testing");
		return false;
	}

	@Override
	public List<Map<String,Object>> getUserAccess(Long roleId) {
		// TODO Auto-generated method stub
		Role role = roleRepository.findById(roleId).get();
		List<String> accessList = role.getAccessedNode();
		List<Nodes> nList = nodeRepository.findAll();
		List<Map<String,Object>>nodeList = new ArrayList<>();
		for(Nodes n:nList) {

			String parent =n.getName();
			String parentTemp =parent; 
			Map<String,Object>mapNode = new HashMap<>();
			List<Map<String,Object>>subNodeList = new ArrayList<>();
			
			if(accessList.contains(parent)) {

				for(SubNodes sn: n.getNodeChild()) {

					String child =parentTemp+sn.getName();
					String tempChild=child;
					Map<String,Object>mapSubNode = new HashMap<>();
					List<Map<String,Object>>superSubNodeList = new ArrayList<>();
					
					if(accessList.contains(child)) {
						
						for(SuperSubNodes ssn:sn.getSubNodeChild()) {
							String subChild=child+ssn.getName();
							Map<String,Object>mapSuperSubNode = new HashMap<>();
							System.out.println(subChild);
							if(accessList.contains(subChild)) {
								System.out.println(mapSuperSubNode+"  is equal to  "+accessList.contains(subChild));
								mapSuperSubNode.put("id", ssn.getId());
								mapSuperSubNode.put("name", ssn.getName());
								superSubNodeList.add(mapSuperSubNode);	
							}

						}
						mapSubNode.put("id", sn.getId());
						mapSubNode.put("name",sn.getName());
						mapSubNode.put("superSubNode", superSubNodeList);
						subNodeList.add(mapSubNode);
					}				
				}
				mapNode.put("id", n.getId());
				mapNode.put("name", n.getName());

				mapNode.put("subNode", subNodeList);
				nodeList.add(mapNode);
			}
		}

		return nodeList;
	}


}
