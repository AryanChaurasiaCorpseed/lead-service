package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

@Service
public interface CommunicationService {
	public boolean mailsCommunication(String mailTo,String mailCc,String subject,String desc,Long leadId,Long clientId,boolean isSendBy);

	public boolean deleteMailCommunication(Long communicationId); 


}
