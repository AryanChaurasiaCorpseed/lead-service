package com.lead.dashboard.serviceImpl.TaskManagmentImpl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.repository.TaskManagmentRepository;
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
	UserRepo userRepo;
	@Override
	
	public TaskManagment createTaskInLead(Long leadId,String name, String description, Long assigneeId, Long assignedById,Date expectedDate) {
		// TODO Auto-generated method stub
		TaskManagment taskManagment = new TaskManagment();
		taskManagment.setName(description);
		taskManagment.setAssigne(userRepo.findById(assigneeId).get());
		taskManagment.setAssignedBy(userRepo.findById(assignedById).get());
		taskManagment.setDescription(description);
		taskManagment.setAssignedDate(new Date());
		taskManagment.setExpectedDate(expectedDate);           //----------date according to user
		taskManagmentRepository.save(taskManagment);
	
		return taskManagment;
	}

}
