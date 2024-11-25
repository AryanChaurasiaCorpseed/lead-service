package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.lead.Proposal;
import com.lead.dashboard.dto.CreateProposalDto;

@Service
public interface ProposalService {

	Boolean createProposal(CreateProposalDto createProposalDto) throws Exception;

	Proposal getProposalById(Long id);

	List<Proposal> getAllProposalByUserId(Long userId, int page, int size);
	
	long getAllProposalByUserIdCount(Long userId);


}
