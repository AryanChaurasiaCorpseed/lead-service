package com.lead.dashboard.controller.taskManagmentController;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.dto.CreateTask;
import com.lead.dashboard.service.taskManagmentService.TaskManagmentService;

/*
@Author :-Aryan Chaurasia

*/
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/")
public class TaskManagmentController {
	
	@Autowired
	TaskManagmentService taskManagmentService;
	
	@PostMapping("api/v1/task/createTask")
	public TaskManagment createTaskInLead(@RequestBody CreateTask createTask) {
		TaskManagment task=taskManagmentService.createTaskInLead(createTask.getLeadId() ,createTask.getName(),createTask.getDescription(),createTask.getExpectedDate(),createTask.getStatusId());
		return task;
	}
	
	@GetMapping("api/v1/task/getAllTaskByAssignee")
	public List<Map<String, Object>> getAllTaskByAssignee(@RequestParam Long assigneeId) {
		List<Map<String, Object>> task=taskManagmentService.getAllTaskByAssignee(assigneeId);
		return task;
	}
	
	@PutMapping("api/v1/task/updateAssigneTask")
	public TaskManagment updateAssigneTask(@RequestParam Long taskId,@RequestParam Long newAssigneId) {
		TaskManagment task=taskManagmentService.updateAssigneTask(taskId,newAssigneId);
		return task;
	}
	
	@PutMapping("api/v1/task/updateTaskStatus")
	public Boolean updateTaskStatus(@RequestParam Long taskId,@RequestParam Long statusId) {
		Boolean task=taskManagmentService.updateTaskStatus(taskId,statusId);
		return task;
	}
	
	@GetMapping("api/v1/task/getAllTaskByLead")
	public List<TaskManagment> getAllTaskByLead(@RequestParam Long leadId) {
		List<TaskManagment> task=taskManagmentService.getAllTaskByLead(leadId);
		return task;
	}
	

}
