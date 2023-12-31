package com.lead.dashboard.controller.ticketController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Ticket;
import com.lead.dashboard.dto.CreateTicket;
import com.lead.dashboard.service.TicketService;

@RestController
public class TicketController {
        
	@Autowired
	TicketService ticketService;
	
	
	@PostMapping("leadService/api/v1/createTicket")
	public Ticket createTicket(@RequestBody CreateTicket createTicket){
			Ticket ticket =ticketService.createTicket(createTicket);
			return ticket;
	}
	
}
