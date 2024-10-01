package com.lead.dashboard.service.taskManagmentService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskStatus;

@Service
public interface TaskStatusService {

	TaskStatus createStatus(String name, String description);

	List<TaskStatus> getAllStatuses();

	TaskStatus getTaskStatusById(Long id);

	TaskStatus updateStatus(Long id,String name, String description);

	Boolean deleteStatus(Long id);

}
