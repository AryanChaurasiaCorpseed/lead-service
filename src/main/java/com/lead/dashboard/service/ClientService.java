package com.lead.dashboard.service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Lead;

public interface ClientService {

//    void removeClientFromLead(Long leadId, Long clientId);

    Client updateClientInfo(Client client);

    Lead createClientInLead(Long leadId, String name, String email, String contactNo);

    void removeClientFromLead(Long leadId, Long clientId);
}
