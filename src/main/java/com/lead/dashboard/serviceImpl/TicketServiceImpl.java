package com.lead.dashboard.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ticket;
import com.lead.dashboard.dto.CreateTicket;
import com.lead.dashboard.repository.TicketRepository;
import com.lead.dashboard.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	
	@Autowired
	TicketRepository ticketRepository;
	
	@Override
	public Ticket createTicket(CreateTicket createTicket) {
		// TODO Auto-generated method stub
		Ticket t = new Ticket();
		t.setSubject(createTicket.getSubject());
		t.setDescription(createTicket.getDescription());
		Ticket ticket = ticketRepository.save(t);
		
		return ticket;
	}

}
