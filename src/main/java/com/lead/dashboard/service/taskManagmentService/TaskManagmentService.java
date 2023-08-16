package com.lead.dashboard.service.taskManagmentService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskManagment;
/*
@Author :-Aryan Chaurasia

*/
@Service
public interface TaskManagmentService {


	TaskManagment createTaskInLead(Long leadId, String name, String description, Long assigneeId, Long assignedById,Date expectedDate,Long statusId);

	List<Map<String, Object>> getAllTaskByAssignee(Long assigneeId);

//	TaskManagment createTaskInLead(Long leadId, String name, String description, Long assigneeId, Long assignedById);

}
