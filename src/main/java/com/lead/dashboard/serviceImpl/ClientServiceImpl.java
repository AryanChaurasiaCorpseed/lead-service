package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.UpdateClientDto;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.LeadHistoryRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	LeadRepository leadRepository;


	@Autowired
	LeadHistoryRepository leadHistoryRepository;

	@Autowired
	UserRepo userRepo;

	@Autowired
	ClientRepository clientRepository;
	@Override
	public void removeClientFromLead(Long leadId, Long clientId) {

		Lead lead = leadRepository.findById(leadId).orElse(null);
		if (lead!= null)
		{
			List<Client> clients = lead.getClients();
			clients.removeIf(client ->client.getId().equals(clientId));
			lead.setClients(clients);
			leadRepository.save(lead);
		}
	}

	private Client getClientById(Long id) {
		return clientRepository.getStatusById(id);
	}


	@Override
	public Client updateClientInfo(UpdateClientDto client) {
		Client clientDetails = getClientById(client.getId());

		User user=null;
		if(client.getUserId()!=null) {
			user=userRepo.findById(client.getUserId()).get();
		}
		if (clientDetails != null)
		{    
			if(!(clientDetails.getName().equals(client.getName()))) {
				updateClientNameHistory(user,clientDetails.getName(),client.getName(),client.getLeadId());
				clientDetails.setName(client.getName());	
			}
			if(!(clientDetails.getEmails().equals(client.getEmail()))) {
				updateClientEmailHistory(user,clientDetails.getEmails(),client.getEmail(),client.getLeadId());
				clientDetails.setEmails(client.getEmail());
			}
			if(!(clientDetails.getContactNo().equals(client.getContactNo()))) {
				updateClientContactHistory(user,clientDetails.getContactNo(),client.getContactNo(),client.getLeadId());
				clientDetails.setContactNo(client.getContactNo());

			}

			return clientRepository.save(clientDetails);
		}
		else return null;
	}

	public void updateClientNameHistory(User currentUser,String pName,String cName,Long leadId) {
		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setLeadId(leadId);
		leadHistory.setDescription("client name has been changed from "+pName+" to "+cName);
		leadHistory.setEventType("Client Name changed");
		leadHistory.setCreatedBy(currentUser);
		leadHistoryRepository.save(leadHistory);
	}

	public void updateClientEmailHistory(User currentUser,String pEmail,String cEmail,Long leadId) {
		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setDescription("client email has been changed from "+pEmail+" to "+cEmail);
		leadHistory.setEventType("Client email changed");
		leadHistory.setCreatedBy(currentUser);
		leadHistory.setLeadId(leadId);
		leadHistoryRepository.save(leadHistory);
	}
	public void updateClientContactHistory(User currentUser,String pContact,String cContact,Long leadId) {
		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setDescription("client contact has been changed from "+pContact+" to "+cContact);
		leadHistory.setEventType("Client contact changed");
		leadHistory.setCreatedBy(currentUser);
		leadHistory.setLeadId(leadId);
		leadHistoryRepository.save(leadHistory);
	}

	@Override
	public Lead createClientInLead(Long leadId, String name, String email, String contactNo,Long currentUserId) {

		Client client = new Client();
		client.setName(name);
		client.setEmails(email);
		client.setContactNo(contactNo);
		clientRepository.save(client);
		createClientHistory(leadId,name,currentUserId);
		Optional<Lead> opLead = leadRepository.findById(leadId);
		Lead lead = opLead.get();
		System.out.println(lead);

		if(lead.getClients()!=null) {

			List<Client> clientList = lead.getClients();
			clientList.add(client);
			lead.setClients(clientList);
			leadRepository.save(lead);
		}

		else {

			List<Client> clientList =new ArrayList<>();
			clientList.add(client);
			lead.setClients(clientList);
			leadRepository.save(lead);
		}

		return lead;   
	}
	
	public void  createClientHistory(Long leadId,String cName,Long currentUserId) {
		LeadHistory leadHistory = new LeadHistory();

		User user=null;
		if(currentUserId!=null) {
			user=userRepo.findById(currentUserId).get();
			leadHistory.setCreatedBy(user);
		}
		leadHistory.setCreateDate(new Date());
		leadHistory.setDescription(cName+" has been created .");
		leadHistory.setLeadId(leadId);
		leadHistory.setEventType("client creation");
		leadHistoryRepository.save(leadHistory);
	}

	@Override
	public List<Client> getAllClientInfo() {
		List<Client> client = clientRepository.findAll();
		return client;
	}


	//    @Override
	//    public Client updateClientInfo(Long clientID) {
	//
	//        Client clientInfo = leadRepository.getClientById(clientID);
	//        if (clientInfo != null) {
	//            clientInfo.setContactNo(clientInfo.getContactNo());
	//            clientInfo.setEmails(clientInfo.getEmails());
	//            clientInfo.setName(clientInfo.getName());
	//            return clientRepository.save(clientInfo);
	//        }
	//        else
	//            return null;
	//    }

	//    public Lead createClientInLead(Long leadId,String name ,String email,String contactNo) {
	//
	//        Client client = new Client();
	//        client.setName(name);
	//        client.setContactNo(contactNo);
	//        client.setEmails(email);
	////		clientRepository.save(client);
	//
	//        Optional<Lead> opLead = leadRepository.findById(leadId);
	//        Lead lead = opLead.get();
	//        System.out.println(lead);
	//
	//        if(lead.getClients()!=null) {
	//
	//            List<Client> clientList = lead.getClients();
	//            clientList.add(client);
	//            lead.setClients(clientList);
	//            leadRepository.save(lead);
	//        }else {
	//
	//            List<Client> clientList =new ArrayList<>();
	//            clientList.add(client);
	//            lead.setClients(clientList);
	//            leadRepository.save(lead);
	//        }
	//
	//        return lead;
	//
	//    }



}
