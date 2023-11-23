package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Nodes;
import com.lead.dashboard.domain.SubNodes;
import com.lead.dashboard.domain.SuperSubNodes;

@Service
public interface NodeService {

	Nodes createNode(String name);

	Nodes updateNode(Long id, String name);

	Nodes deleteNode(Long id);

//	SubNodes createSubNode(String name);

	SubNodes updateSubNode(Long id, String name);

	SubNodes deleteSubNode(Long id);

	SuperSubNodes updateSuperSubNode(Long id, String name);

	SuperSubNodes deleteSuperSubNode(Long id);

	SubNodes createSubNode(String name, Long id);

	SuperSubNodes createSuperSubNode(String name, Long subNodeId);

}
