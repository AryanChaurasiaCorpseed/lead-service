package com.lead.dashboard.controller.leadController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.ClientDesignation;
import com.lead.dashboard.dto.CreateProposalDto;
import com.lead.dashboard.service.ClientDesignationService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ClientDesignationController {

	@Autowired
	ClientDesignationService clientDesignationService;
	
	
	
	
	@PostMapping(UrlsMapping.CREATE_CLIENT_DESIGNATION)
    public Boolean createClientDesignation(@RequestParam String name)
    {
    	Boolean res=clientDesignationService.createClientDesignation(name);
        return res;
    }
	
	@GetMapping(UrlsMapping.GET_ALL_CLIENT_DESIGNATION)
    public List<ClientDesignation> getAllClientDesignation() throws Exception
    {
		List<ClientDesignation> res=clientDesignationService.getAllClientDesignation();
        return res;
    }
	

	@DeleteMapping(UrlsMapping.DELETE_CLIENT_DESIGNATION)
    public Boolean deleteClientDesignation(@RequestParam Long id)
    {
    	Boolean res=clientDesignationService.deleteClientDesignation(id);
        return res;
    }
	
	
	
	@GetMapping(UrlsMapping.GET_CLIENT_DESIGNATION_BY_ID)
    public ClientDesignation getClientDesignationById(@RequestParam Long id) throws Exception
    {
		ClientDesignation res=clientDesignationService.getClientDesignationById(id);
        return res;
    }
	
	
	@PutMapping(UrlsMapping.UPDATE_CLIENT_DESIGNATION)
    public Boolean updateClientDesignation(@RequestParam Long id,@RequestParam String name) throws Exception
    {
		Boolean res=clientDesignationService.updateClientDesignation(id,name);
        return res;
    }
	
}
