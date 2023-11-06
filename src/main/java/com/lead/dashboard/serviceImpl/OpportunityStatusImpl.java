package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.opportunity.OpportunityStatus;
import com.lead.dashboard.repository.OpportunityStatusRepo;
import com.lead.dashboard.service.OpportunityStatusService;

public class OpportunityStatusImpl implements OpportunityStatusService{

	@Autowired
	OpportunityStatusRepo opportunityStatusRepo;
	@Override
	public OpportunityStatus createStatus(String name) {
		OpportunityStatus opportunityStatus = new OpportunityStatus();
		opportunityStatus.setName(name);
		opportunityStatus.setDelete(false);
		opportunityStatusRepo.save(opportunityStatus);
		return opportunityStatus;
	}

	@Override
	public List<OpportunityStatus> getAllStatuses() {
		// TODO Auto-generated method stub
		List<OpportunityStatus>oppList=opportunityStatusRepo.findAll().stream().collect(Collectors.toList());
		return oppList;
	}

	@Override
	public OpportunityStatus getStatusById(Long id) {
		// TODO Auto-generated method stub
		Optional<OpportunityStatus> opp = opportunityStatusRepo.findById(id);
		OpportunityStatus opportunityStatus = opp!=null?opp.get():null;
		return opportunityStatus;
	}

	@Override
	public OpportunityStatus updateStatus(OpportunityStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStatus(Long id) {
		// TODO Auto-generated method stub
		Optional<OpportunityStatus> opp = opportunityStatusRepo.findById(id);
		OpportunityStatus opportunityStatus = opp!=null?opp.get():null;
		if(opportunityStatus!=null) {
			opportunityStatus.setDelete(true);
		}
	}

}
