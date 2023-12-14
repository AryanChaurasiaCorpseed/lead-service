package com.lead.dashboard.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.serviceImpl.UserAccessServiceImpl;

@Service
public interface UserAccessService {

	boolean createUserAccess(Long roleId);

	List<Map<String,Object>> getUserAccess(Long roleId);

	Map<String, Object> getSubNodeByNodeAndRole(Long roleId, Long nodeId);

	Map<String, Object> getSuperSubNodeBySubNode(Long roleId,Long nodeId,Long subNodeId);

}
