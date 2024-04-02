package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateClientDto;

@Service
public interface ClientService {

//    void removeClientFromLead(Long leadId, Long clientId);

    Client updateClientInfo(UpdateClientDto client);

    Lead createClientInLead(Long leadId, String name, String email, String contactNo,Long currentUserId);

    void removeClientFromLead(Long leadId, Long clientId,Long currentUserId);

	List<Client> getAllClientInfo();
}
