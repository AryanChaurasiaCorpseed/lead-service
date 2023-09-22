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
	
	@GetMapping("/v1/lead/getAllInboxData")
	public List<Map<String ,Object>>getAllInboxData(){
		return inboxService.getAllInboxData();
	}

}
