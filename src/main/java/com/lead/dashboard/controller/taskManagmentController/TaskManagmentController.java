package com.lead.dashboard.controller.taskManagmentController;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.service.taskManagmentService.TaskManagmentService;

/*
@Author :-Aryan Chaurasia

*/
@RestController
public class TaskManagmentController {
	
	@Autowired
	TaskManagmentService taskManagmentService;
	
	@GetMapping("/")
	public TaskManagment createTaskInLead(@RequestParam Long leadId,@RequestParam String name,@RequestParam String description,@RequestParam Long assigneeId,@RequestParam Long assignedById,@RequestParam Date expectedDate) {
		TaskManagment task=taskManagmentService.createTaskInLead(leadId ,name,description,assigneeId,assignedById,expectedDate);
		return task;
	}
	

}
