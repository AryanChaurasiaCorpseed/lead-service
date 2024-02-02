package com.lead.dashboard.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Notification;
import com.lead.dashboard.repository.NotificationRepository;
import com.lead.dashboard.service.NotificationService;

@Service
public class NotificationServiceImpl  implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public List<Notification> getAllNotification(Long userId) {
		List<Notification> notifList = notificationRepository.findAll();
		return notifList;
	}

	@Override
	public Boolean viewNotification(Long userId) {
		Boolean flag=false;
		List<Notification> notifList = notificationRepository.findAllByUserIdAndIsView(userId, false);
		for(Notification n:notifList) {
			n.setView(true);
			notificationRepository.save(n);
			flag=true;
		}
		return flag;
	}

	@Override
	public Long getUnseenCount(Long userId) {
		return notificationRepository.findAll().stream().filter(i->!(i.isView())).count();
		
	}
	

}
