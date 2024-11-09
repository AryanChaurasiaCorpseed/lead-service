package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Notification;

@Service
public interface NotificationService {

	List<Notification> getAllNotification(Long userId);

	Boolean viewNotification(Long userId);

	Long getUnseenCount(Long userId);
   
}
