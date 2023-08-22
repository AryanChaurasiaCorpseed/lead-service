package com.lead.dashboard.controller.clientController;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.service.LeadService;

@RestController
public class ClientController {
	
	  @Autowired
	  LeadService leadservice;

	  @Autowired
	  ClientService clientService;

	  @PostMapping("/v1/lead/createClient")
	  public Lead  createClientInLead(@RequestParam Long leadId,@RequestParam String name,@RequestParam String contactNo,@RequestParam String email) {


		  Lead  clientCreated =clientService.createClientInLead(leadId, name , email, contactNo);
		 return clientCreated;
	  }

	@DeleteMapping("api/v1/deleteClient")
	public ResponseEntity<String> deleteClientFromLead(@RequestParam Long leadId, @RequestParam Long clientId)
	{

		clientService.removeClientFromLead(leadId,clientId);
        return ResponseEntity.ok("Client has been removed from the lead enquiry");

	}

	@PutMapping("api/v1/updateClientInfo")
	public ResponseEntity<Client> upddateClientInfo(@RequestBody Client client)
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
