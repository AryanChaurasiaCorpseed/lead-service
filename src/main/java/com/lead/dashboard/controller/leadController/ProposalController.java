package com.lead.dashboard.controller.leadController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.lead.Proposal;
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
	
	@GetMapping(UrlsMapping.GET_PROPOSAL_BY_ID)
    public Proposal getProposalById(@RequestParam Long id)
    {
    	Proposal res=proposalService.getProposalById(id);
        return res;
    }
	
	@GetMapping(UrlsMapping.GET_ALL_PROPOSAL_BY_USER_ID)
    public List<Proposal> getAllProposalByUserId(@RequestParam Long userId)
    {
		List<Proposal> res=proposalService.getAllProposalByUserId(userId);
        return res;
    }
}
