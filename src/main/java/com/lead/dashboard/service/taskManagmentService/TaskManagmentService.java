package com.lead.dashboard.service.taskManagmentService;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskManagment;
/*
@Author :-Aryan Chaurasia

*/
@Service
public interface TaskManagmentService {


	TaskManagment createTaskInLead(Long leadId, String name, String description, Long assigneeId, Long assignedById,Date expectedDate,Long statusId);

//	TaskManagment createTaskInLead(Long leadId, String name, String description, Long assigneeId, Long assignedById);

}
