package com.lead.dashboard.service.taskManagmentService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.dto.UpdateTaskDto;
/*
@Author :-Aryan Chaurasia

*/
@Service
public interface TaskManagmentService {


	TaskManagment createTaskInLead(Long leadId, String name, String description,Date expectedDate,Long statusId,Long assignById);

	List<Map<String, Object>> getAllTaskByAssignee(Long assigneeId);

	TaskManagment updateAssigneTask(Long taskId, Long newAssigneId);

	List<TaskManagment> getAllTaskByLead(Long leadId);

	Boolean updateTaskStatus(Long taskId, Long statusId);

	Boolean updateTaskData(UpdateTaskDto updateTaskDto);

//	TaskManagment createTaskInLead(Long leadId, String name, String description, Long assigneeId, Long assignedById);

}
