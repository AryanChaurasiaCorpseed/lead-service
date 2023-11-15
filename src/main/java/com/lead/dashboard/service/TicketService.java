package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ticket;
import com.lead.dashboard.dto.CreateTicket;

@Service
public interface TicketService {

	Ticket createTicket(CreateTicket createTicket);

}
