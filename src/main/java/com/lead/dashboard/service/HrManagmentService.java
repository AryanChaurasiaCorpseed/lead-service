package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.User;

@Service
public interface HrManagmentService {


	List<User> getUserApprovalHr(Long userId);

	Boolean approvedUserByHr(Long currentUserId, Long userId,boolean flag);

}
