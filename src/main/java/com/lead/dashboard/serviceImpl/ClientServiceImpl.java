package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    ClientRepository clientRepository;
    @Override
    public void removeClientFromLead(Long leadId, Long clientId) {

        Lead lead = leadRepository.findById(leadId).orElse(null);
        if (lead!= null)
        {
            List<Client> clients = lead.getClients();
            clients.removeIf(client ->client.getId().equals(clientId));


            leadRepository.save(lead);
        }
    }

    private Client getClientById(Long id) {
        return clientRepository.getStatusById(id);
    }


    @Override
    public Client updateClientInfo(Client client) {
        Client clientDetails = getClientById(client.getId());

        if (clientDetails != null)
        {
            clientDetails.setName(client.getName());
            clientDetails.setEmails(client.getEmails());
            clientDetails.setContactNo(client.getContactNo());

            return clientRepository.save(clientDetails);
        }
        else return null;
    }

    @Override
    public Lead createClientInLead(Long leadId, String name, String email, String contactNo) {

        Client client = new Client();
        client.setName(name);
        client.setEmails(email);
        client.setContactNo(contactNo);
        clientRepository.save(client);

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

        return lead;    }


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
