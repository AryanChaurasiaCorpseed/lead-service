package com.lead.dashboard.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Proposal;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateProposalDto;
import com.lead.dashboard.repository.ContactRepo;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProposalRepository;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService{

	@Autowired
	LeadRepository leadRepository;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	ProposalRepository proposalRepository;

	@Autowired
	ContactRepo contactRepo;

	@Override
	public Boolean createProposal(CreateProposalDto createservicedetails) throws Exception {

		Boolean flag=false;
		Lead lead = leadRepository.findById(createservicedetails.getLeadId()).get();

		Proposal proposal = lead.getProposal();

		if(proposal==null) {
			Product product = productRepo.findById(createservicedetails.getProductId()).get();
			proposal=new Proposal();
			proposal.setCreateDate(new Date());
			proposal.setAddress(null);
			proposal.setProductName(product.getProductName());
			Contact contact = contactRepo.findById(createservicedetails.getPrimaryContact()).get();
			proposal.setPrimaryContact(contact);
			Contact secondaryContact = contactRepo.findById(createservicedetails.getSecondaryContact()).get();
			proposal.setSecondaryContact(secondaryContact);
			Long assigneeId = createservicedetails.getAssigneeId();
			// company
			proposal.setIsPresent(createservicedetails.getIsPresent());
			proposal.setCompanyName(createservicedetails.getCompanyName());
			proposal.setCompanyId(createservicedetails.getCompanyId());
			proposal.setUnit(createservicedetails.isUnit());
			proposal.setUnitName(createservicedetails.getUnitName());
			proposal.setUnitId(createservicedetails.getUnitId());
			proposal.setPanNo(createservicedetails.getPanNo());
			proposal.setGstNo(createservicedetails.getGstNo());

			proposal.setGstType(createservicedetails.getGstType());
			proposal.setGstDocuments(createservicedetails.getGstDocuments());
			proposal.setCompanyAge(createservicedetails.getCompanyAge());
			proposal.setGovermentfees(createservicedetails.getGovermentfees());
			proposal.setGovermentCode(createservicedetails.getGovermentCode());
			proposal.setGovermentGst(createservicedetails.getGovermentGst());
			proposal.setProfessionalFees(createservicedetails.getProfessionalFees());
			proposal.setProfessionalCode(createservicedetails.getProfessionalCode());
			proposal.setProfesionalGst(createservicedetails.getProfesionalGst());
			proposal.setServiceCharge(createservicedetails.getServiceCharge());
			proposal.setServiceCode(createservicedetails.getServiceCode());
			proposal.setServiceGst(createservicedetails.getServiceGst());
			proposal.setOtherFees(createservicedetails.getOtherFees());
			proposal.setOtherCode(createservicedetails.getOtherCode());
			proposal.setOtherGst(createservicedetails.getOtherGst());
			proposalRepository.save(proposal);
			lead.setProposal(proposal);
			leadRepository.save(lead);
			flag=true;

		}else {
			if(proposal.isDeleted()){
				Product product = productRepo.findById(createservicedetails.getProductId()).get();
				proposal=new Proposal();
				proposal.setCreateDate(new Date());
				proposal.setAddress(null);
				proposal.setProductName(product.getProductName());
				Contact contact = contactRepo.findById(createservicedetails.getPrimaryContact()).get();
				proposal.setPrimaryContact(contact);
				Contact secondaryContact = contactRepo.findById(createservicedetails.getSecondaryContact()).get();
				proposal.setSecondaryContact(secondaryContact);
				// company
				proposal.setIsPresent(createservicedetails.getIsPresent());
				proposal.setCompanyName(createservicedetails.getCompanyName());
				proposal.setCompanyId(createservicedetails.getCompanyId());
				proposal.setUnit(createservicedetails.isUnit());
				proposal.setUnitName(createservicedetails.getUnitName());
				proposal.setUnitId(createservicedetails.getUnitId());
				proposal.setPanNo(createservicedetails.getPanNo());
				proposal.setGstNo(createservicedetails.getGstNo());

				proposal.setGstType(createservicedetails.getGstType());
				proposal.setGstDocuments(createservicedetails.getGstDocuments());
				proposal.setCompanyAge(createservicedetails.getCompanyAge());
				proposal.setGovermentfees(createservicedetails.getGovermentfees());
				proposal.setGovermentCode(createservicedetails.getGovermentCode());
				proposal.setGovermentGst(createservicedetails.getGovermentGst());
				proposal.setProfessionalFees(createservicedetails.getProfessionalFees());
				proposal.setProfessionalCode(createservicedetails.getProfessionalCode());
				proposal.setProfesionalGst(createservicedetails.getProfesionalGst());
				proposal.setServiceCharge(createservicedetails.getServiceCharge());
				proposal.setServiceCode(createservicedetails.getServiceCode());
				proposal.setServiceGst(createservicedetails.getServiceGst());
				proposal.setOtherFees(createservicedetails.getOtherFees());
				proposal.setOtherCode(createservicedetails.getOtherCode());
				proposal.setOtherGst(createservicedetails.getOtherGst());
				proposalRepository.save(proposal);
				lead.setProposal(proposal);
				leadRepository.save(lead);
				flag=true;

			}else {
				throw new Exception("Already exist please edit in existing Proposal");

			}
		}
		return flag;

	}

	@Override
	public Proposal getProposalById(Long id) {
		Proposal p=proposalRepository.findById(id).get();
		return p;
	}

	@Override
	public List<Proposal> getAllProposalByUserId(Long userId, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Proposal>proposalList= proposalRepository.findAllByUserId(userId,pageable).getContent();
		
		return proposalList;
	}

	@Override
	public long getAllProposalByUserIdCount(Long userId) {
		long proposalCount= proposalRepository.findCountByUserId(userId);
		return proposalCount;
	}
}
