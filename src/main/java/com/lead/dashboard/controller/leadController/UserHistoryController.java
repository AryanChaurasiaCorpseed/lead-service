package com.lead.dashboard.controller.leadController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.UserRecord;
import com.lead.dashboard.repository.UserHistoryRepo;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class UserHistoryController {

    @Autowired
    UserHistoryRepo userHistoryRepo;
     
	@GetMapping(UrlsMapping.GET_ALL_USER_HISTORY)
	public List<Map<String,Object>> getAllUserHistory(@RequestParam Long userId)
	{		
		    List<UserRecord> userHistory = userHistoryRepo.findAllByCurrentUserId(userId);
		    List<Map<String,Object>>result = new ArrayList<>();
		    for(UserRecord record:userHistory) {
		    	Map<String,Object>map = new HashMap<>();
		    	map.put("id", record.getId());
		    	map.put("event", record.getEvent());
		    	map.put("userName", record.getCurrentUser()!=null? record.getCurrentUser().getFullName():"NA");
		    	map.put("updatedBy", record.getUpdatedBy()!=null? record.getUpdatedBy().getFullName():"NA");
		    	result.add(map);

		    }
			return result;
	}
	
}
