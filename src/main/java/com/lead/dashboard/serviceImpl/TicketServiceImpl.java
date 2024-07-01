package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.Ticket;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.dto.CreateTicket;
import com.lead.dashboard.repository.TicketRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired 
	UserRepo  userRepo;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;
	
	@Override
	public Ticket createTicket(CreateTicket createTicket) {
		// TODO Auto-generated method stub
		Ticket t = new Ticket();
		t.setSubject(createTicket.getSubject());
		t.setDescription(createTicket.getDescription());
		User createdBy = userRepo.findById(createTicket.getUserId()).get();
		t.setCreatedBy(createdBy);
		Ticket ticket = ticketRepository.save(t);
//	    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject,String text, Context context,String templateName) {
		String[] emailTo= {"aryan.chaurasia@corpseed.com"};
		String[] ccPersons= {createdBy.getEmail()};
		String subject=createTicket.getSubject();
		String text=createTicket.getDescription();
		Context context = new Context();
		context.setVariable("userName", "Aryan Chaurasia");		
		context.setVariable("comment", text);		

		mailSendSerivceImpl.sendEmail(emailTo, ccPersons, ccPersons, subject, text, context, "Ticket.html");
		return ticket;
	}

	@Override
	public List<Ticket> getAllTicket(Long userId) {
		List<Ticket>ticketList = new ArrayList();
		List<String> roleList = userRepo.findRoleNameById(userId);
		if(roleList.contains("ADMIN")) {
			ticketList=ticketRepository.findAll();
		}
		return ticketList;
	}

}
