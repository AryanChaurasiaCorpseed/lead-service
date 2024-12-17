package com.lead.dashboard.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Proposal;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateProposalDto;
import com.lead.dashboard.dto.EditProposalDto;
import com.lead.dashboard.repository.ContactRepo;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProposalRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.ProposalService;

@Service
public class ProposalServiceImpl implements ProposalService{

	@Autowired
	LeadRepository leadRepository;

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	@Autowired
	ProposalRepository proposalRepository;

	@Autowired
	ContactRepo contactRepo;
	
	@Autowired
	UserRepo userRepo;

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
			if(assigneeId!=null) {
				User assignee = userRepo.findById(assigneeId).get();
				proposal.setAssignee(assignee);
			}
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
			
			
			proposal.setAddress(createservicedetails.getAddress());
			proposal.setCity(createservicedetails.getCity());
			proposal.setPrimaryPinCode(createservicedetails.getPrimaryPinCode());
			proposal.setState(createservicedetails.getState());
			proposal.setCountry(createservicedetails.getCountry());
			 
			proposal.setSecondaryAddress(createservicedetails.getSecondaryAddress());
			proposal.setSecondaryCity(createservicedetails.getSecondaryCity()); 
			proposal.setSecondaryPinCode(createservicedetails.getSecondaryPinCode());
			proposal.setSecondaryState(createservicedetails.getSecondaryState());
			proposal.setSecondaryCountry(createservicedetails.getSecondaryCountry());
			
			
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
				Long assigneeId = createservicedetails.getAssigneeId();
				// company
				if(assigneeId!=null) {
					User assignee = userRepo.findById(assigneeId).get();
					proposal.setAssignee(assignee);
				}
				
				// company
				proposal.setIsPresent(createservicedetails.getIsPresent());
				proposal.setCompanyName(createservicedetails.getCompanyName());
				proposal.setCompanyId(createservicedetails.getCompanyId());
				proposal.setUnit(createservicedetails.isUnit());
				proposal.setUnitName(createservicedetails.getUnitName());
				proposal.setUnitId(createservicedetails.getUnitId());
				proposal.setPanNo(createservicedetails.getPanNo());
				proposal.setGstNo(createservicedetails.getGstNo());
				
				proposal.setAddress(createservicedetails.getAddress());
				proposal.setCity(createservicedetails.getCity());
				proposal.setPrimaryPinCode(createservicedetails.getPrimaryPinCode());
				proposal.setState(createservicedetails.getState());
				proposal.setCountry(createservicedetails.getCountry());
				 
				proposal.setSecondaryAddress(createservicedetails.getSecondaryAddress());
				proposal.setSecondaryCity(createservicedetails.getSecondaryCity()); 
				proposal.setSecondaryPinCode(createservicedetails.getSecondaryPinCode());
				proposal.setSecondaryState(createservicedetails.getSecondaryState());
				proposal.setSecondaryCountry(createservicedetails.getSecondaryCountry());

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
	public  void sendEstimate(Long pId) {
		Proposal p = proposalRepository.findById(pId).get();
		String[] emailTo= {"aryan.chaurasia@corpseed.com"};
		User u=p.getAssignee();
		String feedbackStatusURL = "https://erp.corpseed.com/erp/setpassword/"+u.getId();
        String email=u!=null?u.getFullName():"NA";
		Context context = new Context();
		context.setVariable("userName", "Aryan Chaurasia");
		context.setVariable("user", u.getFullName());

		context.setVariable("email", email);
		context.setVariable("Rurl", feedbackStatusURL);
		context.setVariable("currentYear", LocalDateTime.now().getYear());
		String subject="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
		String text="CLICK ON THIS link and set password";
		String[] ccPersons= {email};
		mailSendSerivceImpl.sendEmail(emailTo, ccPersons,ccPersons, subject,text,context,"newUserCreate.html");
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

	@Override
	public Boolean editProposal(EditProposalDto editProposalDto) {

		Boolean flag=false;

		Proposal proposal =proposalRepository.findById(null).get();
		proposal.setAddress(null);
		if(editProposalDto.getProductId()!=null) {
			Product product = productRepo.findById(editProposalDto.getProductId()).get();
			proposal.setProductName(product.getProductName());
		}
		Contact contact = contactRepo.findById(editProposalDto.getPrimaryContact()).get();
		proposal.setPrimaryContact(contact);
		Contact secondaryContact = contactRepo.findById(editProposalDto.getSecondaryContact()).get();
		proposal.setSecondaryContact(secondaryContact);
		Long assigneeId = editProposalDto.getAssigneeId();
		// company
		if(assigneeId!=null) {
			User assignee = userRepo.findById(assigneeId).get();
			proposal.setAssignee(assignee);
		}
		proposal.setAssignee(null);
		proposal.setIsPresent(editProposalDto.getIsPresent());
		proposal.setCompanyName(editProposalDto.getCompanyName());
		proposal.setCompanyId(editProposalDto.getCompanyId());
		proposal.setUnit(editProposalDto.isUnit());
		proposal.setUnitName(editProposalDto.getUnitName());
		proposal.setUnitId(editProposalDto.getUnitId());
		proposal.setPanNo(editProposalDto.getPanNo());
		proposal.setGstNo(editProposalDto.getGstNo());
		
		proposal.setAddress(editProposalDto.getAddress());
		proposal.setCity(editProposalDto.getCity());
		proposal.setPrimaryPinCode(editProposalDto.getPrimaryPinCode());
		proposal.setState(editProposalDto.getState());
		proposal.setCountry(editProposalDto.getCountry());
		 
		proposal.setSecondaryAddress(editProposalDto.getSecondaryAddress());
		proposal.setSecondaryCity(editProposalDto.getSecondaryCity()); 
		proposal.setSecondaryPinCode(editProposalDto.getSecondaryPinCode());
		proposal.setSecondaryState(editProposalDto.getSecondaryState());
		proposal.setSecondaryCountry(editProposalDto.getSecondaryCountry());

		proposal.setGstType(editProposalDto.getGstType());
		proposal.setGstDocuments(editProposalDto.getGstDocuments());
		proposal.setCompanyAge(editProposalDto.getCompanyAge());
		proposal.setGovermentfees(editProposalDto.getGovermentfees());
		proposal.setGovermentCode(editProposalDto.getGovermentCode());
		proposal.setGovermentGst(editProposalDto.getGovermentGst());
		proposal.setProfessionalFees(editProposalDto.getProfessionalFees());
		proposal.setProfessionalCode(editProposalDto.getProfessionalCode());
		proposal.setProfesionalGst(editProposalDto.getProfesionalGst());
		proposal.setServiceCharge(editProposalDto.getServiceCharge());
		proposal.setServiceCode(editProposalDto.getServiceCode());
		proposal.setServiceGst(editProposalDto.getServiceGst());
		proposal.setOtherFees(editProposalDto.getOtherFees());
		proposal.setOtherCode(editProposalDto.getOtherCode());
		proposal.setOtherGst(editProposalDto.getOtherGst());
		proposalRepository.save(proposal);

		flag=true;

		return flag;

	}
	@Override
	public Proposal getAllProposalByLeadId(Long leadId) {
//		leadRepository.findProposalIdByLead
		return null;
	}
}
