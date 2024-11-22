package com.lead.dashboard.service;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.lead.Proposal;
import com.lead.dashboard.dto.CreateProposalDto;

@Service
public interface ProposalService {

	Boolean createProposal(CreateProposalDto createProposalDto) throws Exception;

	Proposal getProposalById(Long id);

}
