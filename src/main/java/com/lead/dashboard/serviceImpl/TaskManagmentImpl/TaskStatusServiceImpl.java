package com.lead.dashboard.serviceImpl.TaskManagmentImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.TaskStatus;
import com.lead.dashboard.repository.TaskStatusRepository;
import com.lead.dashboard.service.taskManagmentService.TaskStatusService;

@Service
class TaskStatusServiceImpl implements TaskStatusService {

	
	@Autowired
	TaskStatusRepository taskStatusRepository;
	
	@Override
	public TaskStatus createStatus(String name, String description) {
		// TODO Auto-generated method stub
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setDescription(description);
		taskStatus.setName(name);
		taskStatus.setDeleted(false);
		TaskStatus status = taskStatusRepository.save(taskStatus);
       return status;
	}

	@Override
	public List<TaskStatus> getAllStatuses() {
		// TODO Auto-generated method stub
		List<TaskStatus>taskStatus=taskStatusRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		return taskStatus;
	}

	@Override
	public TaskStatus getTaskStatusById(Long id) {
		// TODO Auto-generated method stub
		Optional<TaskStatus> opTaskRepository = taskStatusRepository.findById(id);
		TaskStatus task = opTaskRepository!=null?opTaskRepository.get():null;
		return task;
	}

	@Override
	public TaskStatus updateStatus(Long id,String name, String description) {
		// TODO Auto-generated method stub
		Optional<TaskStatus> opTaskRepository = taskStatusRepository.findById(id);
		TaskStatus task = opTaskRepository!=null?opTaskRepository.get():null;
		if(task!=null) {
			task.setName(name);
			task.setDescription(description);
			taskStatusRepository.save(task);
		}
		return null;
	}

	@Override
	public Boolean deleteStatus(Long id) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		Optional<TaskStatus> opTaskRepository = taskStatusRepository.findById(id);
		TaskStatus task = opTaskRepository!=null?opTaskRepository.get():null;
		if(task!=null) {
			task.setDeleted(true);
			taskStatusRepository.save(task);
			flag=true;
		}
		return flag;
		
	}

}
