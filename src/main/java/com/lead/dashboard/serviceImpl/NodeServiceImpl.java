package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Nodes;
import com.lead.dashboard.domain.SubNodes;
import com.lead.dashboard.domain.SuperSubNodes;
import com.lead.dashboard.repository.NodeRepository;
import com.lead.dashboard.repository.SubNodesRepository;
import com.lead.dashboard.repository.SuperSubNodesRepository;
import com.lead.dashboard.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService
{
	
	@Autowired
	NodeRepository nodeRepository;
	
	@Autowired
	SuperSubNodesRepository superSubNodesRepository;
	@Autowired
	SubNodesRepository subNodesRepository;

	public Nodes createNode()
	{
		Nodes n = new Nodes();
		n.setName("Sales");
		List<SubNodes>subNodeList1 = new ArrayList<>();
		SubNodes sn1 = new SubNodes();
		sn1.setName("inbox");
		subNodesRepository.save(sn1);
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

		subNodeList1.add(sn1);
		subNodeList1.add(sn2);
		subNodeList1.add(sn3);
		n.setNodeChild(subNodeList1);
		nodeRepository.save(n);

		return n;
	}

	@Override
	public Nodes createNode(String name) {
        Nodes n = new Nodes();
        n.setName(name);
        nodeRepository.save(n);
        return n;
	}

	@Override
	public Nodes updateNode(Long id, String name) {
		// TODO Auto-generated method stub
		Optional<Nodes> nodeOp = nodeRepository.findById(id);
		Nodes node = null;
		if(nodeOp!=null&&nodeOp.get()!=null) {
			node=nodeOp.get();
			node.setName(name);
			nodeRepository.save(node);
			
		}
		return node;
	}

	@Override
	public Nodes deleteNode(Long id) {
		Optional<Nodes> nodeOp = nodeRepository.findById(id);
		Nodes node = null;
		if(nodeOp!=null&&nodeOp.get()!=null) {
			node=nodeOp.get();
			node.setDeleted(true);
			nodeRepository.save(node);
			
		}
		return node;
	}

	@Override
	public SubNodes createSubNode(String name, Long nodeId) {
		// TODO Auto-generated method stub\
		Optional<Nodes> nodeOp = nodeRepository.findById(nodeId);
		List<SubNodes>subList = new ArrayList<>();
        Nodes node = nodeOp.get();
		SubNodes s = new SubNodes();
		s.setName(name);
		subNodesRepository.save(s);
		subList.add(s);
		if(node!=null && node.getNodeChild()!=null) {
			subList.addAll(node.getNodeChild());

		}
		node.setNodeChild(subList);
		nodeRepository.save(node);
		return s;
	}

	@Override
	public SubNodes updateSubNode(Long id, String name) {
		// TODO Auto-generated method stub
		
		Optional<SubNodes> nodeOp = subNodesRepository.findById(id);
		SubNodes subNode = null;
		if(nodeOp!=null&&nodeOp.get()!=null) {
			subNode=nodeOp.get();
			subNode.setName(name);
			subNodesRepository.save(subNode);
			
		}
		return subNode;
		
	}

	@Override
	public SubNodes deleteSubNode(Long id) {
		// TODO Auto-generated method stub
		Optional<SubNodes> nodeOp = subNodesRepository.findById(id);
		SubNodes subNode = null;
		if(nodeOp!=null&&nodeOp.get()!=null) {
			subNode=nodeOp.get();
			subNode.setDeleted(true);
			subNodesRepository.save(subNode);
			
		}
		return subNode;
	}

	@Override
	public SuperSubNodes updateSuperSubNode(Long id, String name) {
		// TODO Auto-generated method stub
		Optional<SuperSubNodes> ssnOp = superSubNodesRepository.findById(id);
		SuperSubNodes ssn =null;
		if(ssnOp!=null&&ssnOp.get()!=null) {
			ssn = ssnOp.get();
			ssn.setName(name);
			superSubNodesRepository.save(ssn);
			
		}
		
		return ssn; 
	}

	@Override
	public SuperSubNodes createSuperSubNode(String name,Long subNodeId) {
		// TODO Auto-generated method stub
		Optional<SubNodes> subNodeOp = subNodesRepository.findById(subNodeId);
		SubNodes subNode = subNodeOp.get();
		List<SuperSubNodes>ssnList = new ArrayList<>();
		if(subNode!=null && subNode.getSubNodeChild()!=null) {
			subNode.getSubNodeChild();
			ssnList.addAll(subNode.getSubNodeChild());
		}
		SuperSubNodes superSubNodes = new SuperSubNodes();
		superSubNodes.setName(name);
		superSubNodesRepository.save(superSubNodes);
		ssnList.add(superSubNodes);
		subNode.setSubNodeChild(ssnList);
		subNode.setSubNodeChild(ssnList);
		subNodesRepository.save(subNode);
		return superSubNodes;
	}

	@Override
	public SuperSubNodes deleteSuperSubNode(Long id) {
		Optional<SuperSubNodes> ssnOp = superSubNodesRepository.findById(id);
		SuperSubNodes ssn =null;
		if(ssnOp!=null&&ssnOp.get()!=null) {
			ssn = ssnOp.get();
			ssn.setDeleted(true);
			superSubNodesRepository.save(ssn);
			
		}
		
		return ssn; 
	} 
	
}
