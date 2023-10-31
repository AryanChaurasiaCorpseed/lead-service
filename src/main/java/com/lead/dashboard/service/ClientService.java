package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateClientDto;

@Service
public interface ClientService {

//    void removeClientFromLead(Long leadId, Long clientId);

    Client updateClientInfo(UpdateClientDto client);

    Lead createClientInLead(Long leadId, String name, String email, String contactNo);

    void removeClientFromLead(Long leadId, Long clientId);
}
