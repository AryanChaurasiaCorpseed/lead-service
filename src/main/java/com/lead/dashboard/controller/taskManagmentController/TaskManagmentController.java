package com.lead.dashboard.controller.taskManagmentController;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.service.taskManagmentService.TaskManagmentService;

/*
@Author :-Aryan Chaurasia

*/
@RestController
@RequestMapping("/leadService/")
public class TaskManagmentController {
	
	@Autowired
	TaskManagmentService taskManagmentService;
	
	@PostMapping("api/v1/createStatus")
	public TaskManagment createTaskInLead(@RequestParam Long leadId,@RequestParam String name,@RequestParam String description,@RequestParam Long assigneeId,@RequestParam Long assignedById,@RequestParam Date expectedDate,@RequestParam Long statusId) {
		TaskManagment task=taskManagmentService.createTaskInLead(leadId ,name,description,assigneeId,assignedById,expectedDate,statusId);
		return task;
	}
	
	@GetMapping("api/v1/getAllTaskByAssignee")
	public List<Map<String, Object>> getAllTaskByAssignee(@RequestParam Long assigneeId) {
		List<Map<String, Object>> task=taskManagmentService.getAllTaskByAssignee(assigneeId);
		return task;
	}
	

}
