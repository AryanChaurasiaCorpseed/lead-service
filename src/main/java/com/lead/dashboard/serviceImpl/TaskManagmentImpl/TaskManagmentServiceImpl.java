package com.lead.dashboard.serviceImpl.TaskManagmentImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.TaskManagmentRepository;
import com.lead.dashboard.repository.TaskStatusRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.taskManagmentService.TaskManagmentService;
/*
@Author :-Aryan Chaurasia
*/
@Service
public class TaskManagmentServiceImpl implements TaskManagmentService {
	
	@Autowired
	TaskManagmentRepository taskManagmentRepository;
	
	@Autowired
	TaskStatusRepository taskStatusRepository;
	
	@Autowired
	LeadRepository leadRepository;

	@Autowired
	UserRepo userRepo;
	@Override	
	public TaskManagment createTaskInLead(Long leadId,String name, String description, Long assigneeId, Long assignedById,Date expectedDate,Long statusId) {
		// TODO Auto-generated method stub
		
		TaskManagment taskManagment = new TaskManagment();
		taskManagment.setName(description);
		taskManagment.setAssigne(userRepo.findById(assigneeId).get());
		taskManagment.setAssignedBy(userRepo.findById(assignedById).get());
		taskManagment.setDescription(description);
		taskManagment.setAssignedDate(new Date());
		taskManagment.setLeadId(leadId);
		taskManagment.setExpectedDate(expectedDate);  
		taskManagment.setTaskStatus(taskStatusRepository.findById(statusId).get());		//----------date according to user
		taskManagmentRepository.save(taskManagment);
	
		return taskManagment;
	}
	@Override
	public List<Map<String, Object>> getAllTaskByAssignee(Long assigneeId) {
		// TODO Auto-generated method stub
		Map<String, Object>result = new HashMap<>();
		List<Map<String, Object>>res = new ArrayList<>();
		List<TaskManagment> tasks = taskManagmentRepository.findByAssigneeId(assigneeId);
		for(TaskManagment t:tasks) {
			result.put("id", t.getId());
			result.put("name", t.getName());
			result.put("description", t.getDescription());
			result.put("assigneeId",t.getAssigne().getId());
			result.put("assigneeName", t.getAssigne().getFullName());
			result.put("assignedById",t.getAssignedBy().getId());
			result.put("assignedByName", t.getAssignedBy().getFullName());
			result.put("expectedDate", t.getExpectedDate());
			result.put("name", t.getName());
			res.add(result);

		}
		
		
		return res;

	}
	@Override
	public TaskManagment updateAssigneTask(Long taskId, Long newAssigneId) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(newAssigneId).get();
		Optional<TaskManagment> opTask = taskManagmentRepository.findById(taskId);
		TaskManagment task=null;
		if(opTask!=null && opTask.get()!=null) {
			task = opTask.get();
			task.setAssigne(user);
			taskManagmentRepository.save(task);
		}
		return task;
	}
	@Override
	public List<TaskManagment> getAllTaskByLead(Long leadId) {
		// TODO Auto-generated method stub
		List<TaskManagment> taskList=taskManagmentRepository.findAllByLeadId(leadId);
		return taskList;
	}

}
