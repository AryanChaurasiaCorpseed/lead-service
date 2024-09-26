package com.lead.dashboard.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Notification;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.NotificationRepository;
import com.lead.dashboard.service.NotificationService;

@Service
public class NotificationServiceImpl  implements NotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	
	@Override
	public List<Notification> getAllNotification(Long userId) {
		List<Notification> notifList = notificationRepository.findAllByUserId(userId);
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
		return notificationRepository.findAllByUserIdAndIsView(userId, false).stream().count();
		
	}
	
	public   void addNotification(User user ,Lead lead ,User assignBy) {
		   Notification notification = new Notification();
		   notification.setUser(user);
//		   notification.setMessage("This is to remind you about activity "+taskManagment.getName()+" is pending");
		   notification.setMessage("This lead '"+lead.getLeadName()+"' has been assigned to you by "+assignBy.getFullName());
		   notification.setLeadId(lead.getId());
		   notification.setType("lead");
		   notification.setView(false);
		   notification.setNotifyDate(new Date());
		   notificationRepository.save(notification);
	}


}
