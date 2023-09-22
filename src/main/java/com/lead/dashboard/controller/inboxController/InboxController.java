package com.lead.dashboard.controller.inboxController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.service.inboxService.InboxService;

@RestController
public class InboxController {
	
	@Autowired
	InboxService inboxService;
	
	@GetMapping("/api/v1/inbox/getAllInboxData")
	public List<Map<String ,Object>>getAllInboxData(){
		return inboxService.getAllInboxData();
	}
	@GetMapping("/api/v1/inbox/editView")
	public boolean editView(){
		return inboxService.editView();
	}

}
