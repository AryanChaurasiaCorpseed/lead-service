package com.lead.dashboard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.Ticket;
import com.lead.dashboard.dto.CreateTicket;
import com.lead.dashboard.repository.TicketRepository;
import com.lead.dashboard.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	
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
		Ticket ticket = ticketRepository.save(t);
//	    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject,String text, Context context,String templateName) {
		String[] emailTo= {"aryan.chaurasia@corpseed.com"};
		String[] ccPersons= {"rahul.jain@corpseed.com"};
		String subject=createTicket.getSubject();
		String text=createTicket.getDescription();
		Context context = new Context();
		context.setVariable("userName", "Aryan Chaurasia");		
		context.setVariable("comment", text);		

		mailSendSerivceImpl.sendEmail(emailTo, ccPersons, ccPersons, subject, text, context, "Ticket.html");
		return ticket;
	}

}
