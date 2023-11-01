package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Ticket;


public interface TicketRepository extends JpaRepository<Ticket, Long> { 

}
