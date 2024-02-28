package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Ticket;
import com.lead.dashboard.dto.CreateTicket;

@Service
public interface TicketService {

	Ticket createTicket(CreateTicket createTicket);

	List<Ticket> getAllTicket(Long userId);

}
