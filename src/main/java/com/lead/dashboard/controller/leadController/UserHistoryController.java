package com.lead.dashboard.controller.leadController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.repository.LeadHistoryRepo;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class UserHistoryController {

    @Autowired
    LeadHistoryRepo leadHistoryRepo;
     
	@PostMapping(UrlsMapping.GET_ALL_USER_HISTORY)
	public List<LeadHistory> getAllUserHistory(@RequestParam Long userId)
	{		
		   List<LeadHistory> leadHistory = leadHistoryRepo.findAll();
			return leadHistory;
	}
	
}
