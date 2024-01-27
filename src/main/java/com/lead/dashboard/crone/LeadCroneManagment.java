package com.lead.dashboard.crone;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.TaskManagment;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.TaskManagmentRepository;
import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;
@Service

public class LeadCroneManagment {
	
	@Autowired
	TaskManagmentRepository taskManagmentRepository;
//	@Scheduled(cron = "0 * * ? * *", zone = "IST")

	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;
	
	@Autowired
	LeadRepository leadRepository;
	
	@Scheduled(cron = "0 * * ? * *", zone = "IST")
	@Transactional
	public void scheduleWithCron() {
	   System.out.println("Cron Job is running at : ");
	   Date currentDate = new Date();
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date1 = simpleDateFormat.format(currentDate);
		
	   List<TaskManagment>taskList=taskManagmentRepository.findAllByExpectedDateAndTaskStatusId(date1,1l);

	   List<Long>leadList=taskList.stream().map(i->i.getLeadId()).collect(Collectors.toList());

	   List<Lead> lead = leadRepository.findAllByIsDeletedAndIdIn(leadList, false);

	   Map<Long, Lead> mapOfLead = lead.stream().collect(Collectors.toMap(c->c.getId(),c->c));
	   System.out.println(mapOfLead);
	   for(TaskManagment taskManagment:taskList) {
		   taskManagment.setMissed(true);
		   Context context = new Context();
		   Lead l=mapOfLead.get(taskManagment.getLeadId());
		   l.setMissedTask(true);
		   leadRepository.save(l);
		   taskManagmentRepository.save(taskManagment);
	    	context.setVariable("user",l.getAssignee()!=null?l.getAssignee().getFullName():"NA");
	    	context.setVariable("leadName",l.getLeadName());
	    	context.setVariable("taskName",taskManagment.getName());
	    	context.setVariable("ecpectedDate",taskManagment.getExpectedDate());

	    	context.setVariable("leadId",taskManagment.getLeadId());
	    	String ccMail[]= {"aryan.chaurasia@corpseed.com"};
	    	String fromMail[]= {l.getAssignee().getEmail()};
	    	String subject="Urgent: Pending Task Notification";
	    	mailSendSerivceImpl.sendEmail(ccMail, fromMail,ccMail, subject,"Testing",context,"missedTask.html");

		   
	   }
	   
	}
}
