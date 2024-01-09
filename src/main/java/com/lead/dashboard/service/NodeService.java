package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Nodes;
import com.lead.dashboard.domain.SubNodes;
import com.lead.dashboard.domain.SuperSubNodes;
import com.lead.dashboard.dto.CreateNode;
import com.lead.dashboard.dto.CreateSubNode;
import com.lead.dashboard.dto.CreateSuperSubNode;
import com.lead.dashboard.dto.UpdateNode;
import com.lead.dashboard.dto.UpdateSubNode;
import com.lead.dashboard.dto.UpdateSuperSubNode;

@Service
public interface NodeService {

	Nodes createNode(CreateNode createNode);

	Nodes updateNode(UpdateNode updateNode);

	Nodes deleteNode(Long id);

	SubNodes updateSubNode(UpdateSubNode updateSubNode);

	SubNodes deleteSubNode(Long id);

	SuperSubNodes updateSuperSubNode(UpdateSuperSubNode updateSuperSubNode);

	SuperSubNodes deleteSuperSubNode(Long id);

	SubNodes createSubNode(CreateSubNode createSubNode);

	SuperSubNodes createSuperSubNode(CreateSuperSubNode createSuperSubNode);


}
