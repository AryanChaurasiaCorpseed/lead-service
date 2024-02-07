package com.lead.dashboard.serviceImpl.TaskManagmentImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.domain.TaskStatus;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateTaskDto;
import com.lead.dashboard.repository.LeadHistoryRepository;
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
	LeadHistoryRepository leadHistoryRepository;
	
	@Autowired
	TaskStatusRepository taskStatusRepository;
	
	@Autowired
	LeadRepository leadRepository;

	@Autowired
	UserRepo userRepo;
	@Override	
	public TaskManagment createTaskInLead(Long leadId,String name, String description,Date expectedDate,Long statusId,Long assignedById) {
		
		TaskManagment taskManagment = new TaskManagment();
		taskManagment.setName(name);
//		taskManagment.setAssigne(userRepo.findById(assigneeId).get());
		taskManagment.setAssignedBy(userRepo.findById(assignedById).get());
		taskManagment.setDescription(description);
		taskManagment.setAssignedDate(new Date());
		taskManagment.setLeadId(leadId);
		taskManagment.setExpectedDate(convertTime(expectedDate));  
		System.out.println(convertTime(expectedDate));
		taskManagment.setTaskStatus(taskStatusRepository.findById(statusId).get());		//----------date according to user
		taskManagmentRepository.save(taskManagment);
	
		return taskManagment;
	}
	public Date convertTime(Date date) {
		 Calendar calendar=Calendar.getInstance();
	     calendar.setTime(date);
	     calendar.add(Calendar.HOUR,-5);
	     calendar.add(Calendar.MINUTE,-30);
		return calendar.getTime();
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
//			result.put("assigneeId",t.getAssigne().getId());
//			result.put("assigneeName", t.getAssigne().getFullName());
			result.put("assignedById",t.getAssignedBy().getId());
//			result.put("assignedByName", t.getAssignedBy().getFullName());
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
//			task.setAssigne(user);
			taskManagmentRepository.save(task);
		}
		return task;
	}
	@Override
	public List<TaskManagment> getAllTaskByLead(Long leadId) {
		// TODO Auto-generated method stub
		List<TaskManagment> taskList=taskManagmentRepository.findAllByLeadId(leadId).stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		return taskList;
	}
	@Override
	public Boolean updateTaskStatus(Long taskId, Long statusId) {
			Boolean flag=false;
		Optional<TaskManagment> opTask = taskManagmentRepository.findById(taskId);
		if(opTask!=null && opTask.isPresent() && opTask.get()!=null) {
			TaskManagment task = opTask.get();
			TaskStatus status = taskStatusRepository.findById(statusId).get();
			task.setTaskStatus(status);
			task.setMissed(false);
			taskManagmentRepository.save(task);
			List<Lead>checkTask= taskManagmentRepository.findByIsMissedAndLeadId(true,task.getLeadId());
			if(checkTask!=null && checkTask.size()==0) {
				Lead lead = leadRepository.findById(statusId).get();
				lead.setMissedTask(false);
                leadRepository.save(lead);
                flag=true;
			}	
		}
		return flag;
	}
	@Override
	public Boolean updateTaskData(UpdateTaskDto updateTaskDto) {
		Boolean flag=false;
		TaskManagment opTask = taskManagmentRepository.findById(updateTaskDto.getTaskId()).get();
		Optional<User> currentUser = userRepo.findById(updateTaskDto.getCurrentUserId());
		if(opTask!=null) {
			if(!(opTask.getName().equals(updateTaskDto.getName()))) {
				String taskName = opTask.getName();
				opTask.setName(updateTaskDto.getName());
				updateTaskName(taskName,updateTaskDto.getName(),currentUser,updateTaskDto.getLeadId());
			}
			if(!(opTask.getTaskStatus().getId().equals(updateTaskDto.getStatusId()))) {
				TaskStatus tStatus = taskStatusRepository.findById(updateTaskDto.getStatusId()).get();
				updateTaskStatus(opTask.getTaskStatus().getName(),tStatus.getName(),currentUser,updateTaskDto.getLeadId());
				opTask.setTaskStatus(tStatus);
				if(tStatus.getName().equals("Done")) {
					opTask.setMissed(false);
					checkAndUpdateMissed(updateTaskDto.getLeadId());
				}
			}
			Date expectedDate = convertTime(updateTaskDto.getExpectedDate());
			if(!(opTask.getExpectedDate().equals(expectedDate))) {
				opTask.setExpectedDate(expectedDate);
				expectedDateHistory(opTask.getExpectedDate(),expectedDate,currentUser,updateTaskDto.getLeadId());
			}
			taskManagmentRepository.save(opTask);
			flag=true;
		}
		return flag;
	}
	
	private void expectedDateHistory(Date prevDate, Date currDate, Optional<User> currentUser,Long leadId) {
        
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("Task date updated");
		leadHistory.setLeadId(leadId);
		if(currentUser!=null) {
			leadHistory.setCreatedBy(currentUser.get());
		}
		leadHistory.setDescription("Date has been Updated from "+prevDate+" to "+currDate);
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
	}
	private void updateTaskStatus(String prevStatus, String currStatus, Optional<User> currentUser,Long leadId) {
        if(!(prevStatus.equals(currStatus))) {
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("Task name update");
		leadHistory.setLeadId(leadId);
		if(currentUser!=null) {
			leadHistory.setCreatedBy(currentUser.get());
		}
		leadHistory.setDescription("Status has been Updated from "+prevStatus+" to "+currStatus);
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
        }
	}
	private void updateTaskName(String taskName, String name, Optional<User> currentUser,Long leadId) {
        
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("Task name update");
		leadHistory.setLeadId(leadId);
		if(currentUser!=null) {
			leadHistory.setCreatedBy(currentUser.get());
		}
		leadHistory.setDescription("Task Name has been Updated from "+taskName+" to "+name);
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
	}
//	public Boolean checkAndUpdateMissed(Long leadId) {
//		Boolean flag=false;
//		Lead lead = leadRepository.findById(leadId).get();
//		lead.setMissedTask(false);
//		lead.setMissedTaskCretedBy(null);
//		lead.setMissedTaskDate(null);
//		lead.setMissedTaskName(null);
//		lead.setMissedTaskStatus(null);
//		leadRepository.save(lead);
//		flag=true;
//		return flag;
//	}
	public Boolean checkAndUpdateMissed(Long leadId) {
		List<TaskManagment> taskList = taskManagmentRepository.findAllByLeadIdAndTaskStatusIdAndIsMissed(leadId, 1l);
		Boolean flag=false;
		Lead lead = leadRepository.findById(leadId).get();
		if(taskList!=null &&taskList.size()!=0&&taskList.size()!=1) {
			TaskManagment taskManagment = taskList.get(taskList.size()-2);
			lead.setMissedTask(true);
			lead.setMissedTaskCretedBy(taskManagment.getAssignedBy().getFullName());
			lead.setMissedTaskDate(taskManagment.getExpectedDate());
			lead.setMissedTaskName(taskManagment.getName());
			lead.setMissedTaskStatus(taskManagment.getTaskStatus().getName());
		}else {
			lead.setMissedTask(false);
			lead.setMissedTaskCretedBy(null);
			lead.setMissedTaskDate(null);
			lead.setMissedTaskName(null);
			lead.setMissedTaskStatus(null);
		}
		leadRepository.save(lead);
		flag=true;
		return flag;
	}
	@Override
	public Boolean deleteTaskById(Long taskId, Long currentUserId) {
		Boolean flag=false;
		TaskManagment opTask = taskManagmentRepository.findById(taskId).get();
		opTask.setDeleted(true);
		taskManagmentRepository.save(opTask);
		deleteTaskHistory(opTask,currentUserId,opTask.getLeadId());
		flag=true;
		return flag;
	}
	
	public Boolean deleteTaskHistory(TaskManagment opTask,Long currentUserId,Long leadId) {
		Boolean flag=false;
		User user = userRepo.findById(currentUserId).get();
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("Task Deletion");
		leadHistory.setLeadId(leadId);
		leadHistory.setCreatedBy(user);
		leadHistory.setDescription(opTask.getName()+" has been deleted by "+user!=null?user.getFullName():"NA");
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
		flag=true;
		return flag;
	}

}
