package com.lead.dashboard.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Communication;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CommunicationRepository;
import com.lead.dashboard.service.CommunicationService;

@Service
public class CommunicationServiceImpl implements CommunicationService{

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CommunicationRepository communicationRepository ;
	
	
	public boolean mailsCommunication(String mailTo,String mailCc,String subject,String desc,Long leadId,Long clientId) {
		Optional<Client> opClient = clientRepository.findById(clientId);
		Communication c = new Communication();
		c.setType("mails");
		c.setSendDate(new Date());
		c.setMailTo(mailTo);
		c.setMailCc(mailCc);
		c.setSubject(subject);
		c.setMessage(desc);
		c.setDeleted(false);
		 Communication comm = communicationRepository.save(c);
		boolean flag=false;
		
		
		Client client=null;
		if(opClient!=null && opClient.get()!=null) {
			client=opClient.get();
			if(client.getCommunication()!=null) {
				List<Communication> com = client.getCommunication();
				com.add(comm);
				client.setCommunication(com);
				clientRepository.save(client);
				flag=true;
			}
		}
		
		
		return flag;
	}


	@Override
	public boolean deleteMailCommunication(Long communicationId) {
		boolean flag=false;
		Optional<Communication>comm=communicationRepository.findById(communicationId);
		if(comm!=null && comm.get()!=null) {
			Communication communication = comm.get();
			communication.setDeleted(true);
			communicationRepository.save(communication);
			flag=true;
			
		}
		return false;
	}
}
