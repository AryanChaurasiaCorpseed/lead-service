package com.lead.dashboard.controller.clientController;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateClient;
import com.lead.dashboard.dto.UpdateClientDto;
import com.lead.dashboard.service.LeadService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/")

public class ClientController {

	@Autowired
	LeadService leadservice;
	@Autowired
	ClientService clientService;
	
	
	@GetMapping("/test")
	public String  testMicroservices() {
		return "this is a person by client";
	}

	@PostMapping("api/v1/client/createClient")
	public Lead  createClientInLead(@RequestBody CreateClient createClient) {
		Lead  clientCreated =clientService.createClientInLead(createClient.getLeadId(), createClient.getName() , createClient.getEmail(), createClient.getContactNo());
		return clientCreated;
	}

	@DeleteMapping("api/v1/client/deleteClient")
	public ResponseEntity<String> deleteClientFromLead(@RequestParam Long leadId, @RequestParam Long clientId)
	{
		clientService.removeClientFromLead(leadId,clientId);
		return ResponseEntity.ok("Client has been removed from the lead enquiry");
	}

	@PutMapping("api/v1/client/updateClientInfo")
	public ResponseEntity<Client> upddateClientInfo(@RequestBody UpdateClientDto client)
	{
		if(client!=null)
		{
			Client updatedDeatils =clientService.updateClientInfo(client);
			return ResponseEntity.ok(updatedDeatils);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}



}
