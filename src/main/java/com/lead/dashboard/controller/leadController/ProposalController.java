package com.lead.dashboard.controller.leadController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.dto.CreateProposalDto;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.service.ProposalService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class ProposalController {

	@Autowired
	 ProposalService proposalService;

    
	@PostMapping(UrlsMapping.CREATE_PROPOSAL)
    public Boolean createProposal(@RequestBody CreateProposalDto createProposalDto) throws Exception
    {
    	Boolean res=proposalService.createProposal(createProposalDto);
        return res;
    }
}
