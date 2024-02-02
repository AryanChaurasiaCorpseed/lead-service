package com.lead.dashboard.controller.notificationController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Notification;
import com.lead.dashboard.service.NotificationService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping(UrlsMapping.GET_ALL_NOTIFICATION)
	public ResponseEntity <List<Notification>> getAllNotification(@RequestParam Long userId)
	{
		List<Notification> allnotification= notificationService.getAllNotification(userId);
		return new ResponseEntity<>(allnotification,HttpStatus.OK);
	}
	
	@GetMapping(UrlsMapping.GET_UNSEEN_COUNT)
	public ResponseEntity <Long> getUnseenCount(@RequestParam Long userId)
	{
		Long count= notificationService.getUnseenCount(userId);
		return new ResponseEntity<>(count,HttpStatus.OK);
	}
	
	@PutMapping(UrlsMapping.VIEW_NOTIFICATION)
	public ResponseEntity <Boolean> viewNotification(@RequestParam Long userId)
	{
		Boolean flag= notificationService.viewNotification(userId);
		return new ResponseEntity<>(flag,HttpStatus.OK);
	}
}
