package com.lead.dashboard.controller.companyController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.dto.UpdateCompanyFormDto;
import com.lead.dashboard.repository.CompanyFormRepo;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.ContactRepo;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.CompanyFormService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class CompanyFormController {

	//	@Autowired
	//	CompanyFormService companyFormService;

	@Autowired
	CompanyFormRepo companyFormRepo;

	@Autowired
	LeadRepository leadRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ContactRepo contactRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	ProjectRepository projectRepository;


	//	@PostMapping(UrlsMapping.CREATE_COMPANY_FORM)
	public CompanyForm createCompanyForm(@RequestBody CreateFormDto createFormDto)
	{				
		CompanyForm companyForm =  new CompanyForm();

		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());

		companyForm.setIsUnit(createFormDto.getIsUnit());
		companyForm.setUnitName(createFormDto.getUnitName());
		companyForm.setUnitId(createFormDto.getUnitId());

		companyForm.setAddress(createFormDto.getAddress());
		companyForm.setAssigneeId(createFormDto.getAssigneeId());

		companyForm.setCity(createFormDto.getCity());
		companyForm.setCompanyAge(createFormDto.getCompanyAge());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setCompanyId(createFormDto.getCompanyId());
		companyForm.setPrimaryPinCode(createFormDto.getPrimaryPinCode());
		companyForm.setCountry(createFormDto.getCountry());

		companyForm.setGstType(createFormDto.getGstType());
		companyForm.setGstDocuments(createFormDto.getGstDocuments());
		companyForm.setGstNo(createFormDto.getGstNo());
		companyForm.setCompanyAge(createFormDto.getCompanyAge());

		companyForm.setSAddress(createFormDto.getSAddress());
		companyForm.setSCity(createFormDto.getSCity());
		companyForm.setSState(createFormDto.getSState());
		companyForm.setSecondaryPinCode(createFormDto.getSecondaryPinCode());
		companyForm.setSCountry(createFormDto.getSCountry());

		companyForm.setStatus(createFormDto.getStatus());
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setIsUnit(createFormDto.getIsUnit());
		companyForm.setStatus("Initiated");
		companyForm.setPanNo(createFormDto.getPanNo());

		companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
		companyForm.setContactName(createFormDto.getContactName());
		companyForm.setContactEmails(createFormDto.getContactEmails());
		companyForm.setContactNo(createFormDto.getContactNo());
		companyForm.setContactWhatsappNo(createFormDto.getContactWhatsappNo());

		
		companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
		System.out.println(createFormDto.getSContactName());
		companyForm.setSContactName(createFormDto.getSContactName());
		System.out.println(createFormDto.getSContactEmails());

		companyForm.setSContactEmails(createFormDto.getSContactEmails());
		companyForm.setSContactNo(createFormDto.getSContactNo());
		companyForm.setSContactWhatsappNo(createFormDto.getSContactWhatsappNo());
		
		if(createFormDto.getUpdatedBy()!=null) {
			User user = userRepo.findById(createFormDto.getUpdatedBy()).get();
			companyForm.setUpdatedBy(user);
		}

		Lead lead = leadRepository.findById(createFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(createFormDto.getPanNo());
		companyForm.setState(createFormDto.getState());
		companyFormRepo.save(companyForm);
		return companyForm;
	}


	//	@PostMapping(UrlsMapping.CREATE_COMPANY_FORM)
	public CompanyForm createCompanyFormv2(@RequestBody CreateFormDto createFormDto)
	{				
		CompanyForm companyForm =  new CompanyForm();
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setAmount(createFormDto.getAmount());
		Company comp=null;
		if(createFormDto.getIsPresent()) {
			comp = companyRepository.findById(createFormDto.getCompanyId()).get();

		}
		companyForm.setCompanyId(createFormDto.getCompanyId());
		if(createFormDto.getIsPresent()&&comp!=null) { //if old company pick
			//        	 comp = companyRepository.findById(createFormDto.getCompanyId()).get();
			companyForm.setCompanyName(comp.getName());
			companyForm.setCompanyId(comp.getId());

		}else {
			companyForm.setIsPresent(createFormDto.getIsPresent());
			//			companyForm.setCompanyId(createFormDto.getCompanyId());
			companyForm.setCompanyName(createFormDto.getCompanyName());

		}
		companyForm.setIsUnit(createFormDto.getIsUnit());
		if(createFormDto.getIsUnit()) {//new unit add 
			System.out.println("aaaaaaaaaaaaaaaaaa");
			companyForm.setUnitName(createFormDto.getUnitName());
			companyForm.setAssigneeId(createFormDto.getAssigneeId());   
			companyForm.setCompanyAge(createFormDto.getCompanyAge());
			companyForm.setCompanyName(createFormDto.getCompanyName());

			companyForm.setGstType(createFormDto.getGstType());
			companyForm.setGstDocuments(createFormDto.getGstDocuments());
			companyForm.setGstNo(createFormDto.getGstNo());
			companyForm.setCompanyAge(createFormDto.getCompanyAge());

		}
		else {
			System.out.println("cccccccccccccccccc");

			if(!createFormDto.getIsPresent()) {

				System.out.println("bbbbbbbbbbbb");

				companyForm.setUnitName(createFormDto.getUnitName());
				companyForm.setAssigneeId(createFormDto.getAssigneeId());   
				companyForm.setCompanyAge(createFormDto.getCompanyAge());
				companyForm.setCompanyName(createFormDto.getCompanyName());

				companyForm.setGstType(createFormDto.getGstType());
				companyForm.setGstDocuments(createFormDto.getGstDocuments());
				companyForm.setGstNo(createFormDto.getGstNo());
				companyForm.setCompanyAge(createFormDto.getCompanyAge());  

			}else {
				Company unit = companyRepository.findById(createFormDto.getUnitId()).get();
				companyForm.setCompanyName(unit.getName());
				companyForm.setUnitName(unit.getName());
				companyForm.setUnitId(createFormDto.getUnitId());
				//			companyForm.setAssigneeId(unit.getAssignee());  //Add Assignee Name 
				companyForm.setCompanyAge(unit.getCompanyAge());

				companyForm.setGstType(unit.getGstType());
				companyForm.setGstDocuments(unit.getGstDocuments());
				companyForm.setGstNo(unit.getGstNo());
				companyForm.setCompanyAge(unit.getCompanyAge());

				if(!createFormDto.getIsPrimaryAddress()) {
					System.out.println("ddddddddddddddddddddddddd");

					companyForm.setIsPrimaryAddress(createFormDto.getIsPrimaryAddress());
					companyForm.setAddress(unit.getAddress());
					companyForm.setCity(unit.getCity());
					companyForm.setState(unit.getState());
					companyForm.setPrimaryPinCode(companyForm.getPrimaryPinCode());  //============== Pin Code not prsent===
					companyForm.setCountry(unit.getCountry());

				}
				if(!createFormDto.getIsSecondaryAddress()) {
					System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

					companyForm.setIsSecondaryAddress(createFormDto.getIsSecondaryAddress());
					companyForm.setSAddress(unit.getSAddress());
					companyForm.setSCity(unit.getSCity());
					companyForm.setSState(unit.getSState());
					companyForm.setSecondaryPinCode(companyForm.getSecondaryPinCode());  //============== Pin Code not prsent===
					companyForm.setSCountry(unit.getSCountry());

				}
			}

		}
		if(createFormDto.getIsPrimaryAddress()!=null &&createFormDto.getIsPrimaryAddress()) {
			System.out.println("ffffffffffffffffffffffffff");
			companyForm.setIsPrimaryAddress(createFormDto.getIsPrimaryAddress());

			companyForm.setAddress(createFormDto.getAddress());
			companyForm.setCity(createFormDto.getCity());
			companyForm.setState(createFormDto.getState());
			companyForm.setPrimaryPinCode(createFormDto.getPrimaryPinCode());
			companyForm.setCountry(createFormDto.getCountry());
		}

		if(createFormDto.getIsPrimaryAddress()!=null && createFormDto.getIsPrimaryAddress()) {
			System.out.println("ggggggggggggggggg");
			companyForm.setIsSecondaryAddress(createFormDto.getIsSecondaryAddress());
			companyForm.setSAddress(createFormDto.getSAddress());
			companyForm.setSCity(createFormDto.getSCity());
			companyForm.setSState(createFormDto.getSState());
			companyForm.setSecondaryPinCode(createFormDto.getSecondaryPinCode());
			companyForm.setSCountry(createFormDto.getSCountry());
		}


		if(createFormDto.isPrimaryContact()) {
			companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
			companyForm.setContactName(createFormDto.getContactName());
			companyForm.setContactEmails(createFormDto.getContactEmails());
			companyForm.setContactNo(createFormDto.getContactNo());
			companyForm.setContactWhatsappNo(createFormDto.getContactWhatsappNo());
		}else {
			Contact cont = contactRepo.findById(createFormDto.getContactId()).get();
			companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
			companyForm.setContactName(cont.getName());
			companyForm.setContactEmails(cont.getEmails());
			companyForm.setContactNo(cont.getContactNo());
			companyForm.setContactWhatsappNo(cont.getWhatsappNo());
		}

		if(createFormDto.isSecondaryContact()) {
			companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
			companyForm.setSContactName(createFormDto.getSContactName());
			companyForm.setSContactEmails(createFormDto.getSContactEmails());
			companyForm.setSContactNo(createFormDto.getSContactNo());
			companyForm.setSContactWhatsappNo(createFormDto.getSContactWhatsappNo());
		}else {
			Contact cont = contactRepo.findById(createFormDto.getSContactId()).get();
			companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
			companyForm.setSContactName(cont.getName());
			companyForm.setSContactEmails(cont.getEmails());
			companyForm.setSContactNo(cont.getContactNo());
			companyForm.setSContactWhatsappNo(cont.getWhatsappNo());
		}
		companyForm.setStatus("Initiated");
		companyForm.setPanNo(createFormDto.getPanNo());

		//		companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
		//		companyForm.setContactName(createFormDto.getContactName());
		//		companyForm.setContactEmails(createFormDto.getContactEmails());
		//		companyForm.setContactNo(createFormDto.getContactNo());
		//		companyForm.setContactWhatsappNo(createFormDto.getContactWhatsappNo());
		//
		//		companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
		//		System.out.println(createFormDto.getSContactName());
		//		companyForm.setSContactName(createFormDto.getSContactName());
		//		System.out.println(createFormDto.getSContactEmails());
		//
		//		companyForm.setSContactEmails(createFormDto.getSContactEmails());
		//		companyForm.setSContactNo(createFormDto.getSContactNo());
		//		  Form.setSContactWhatsappNo(createFormDto.getSContactWhatsappNo());
		if(createFormDto.getUpdatedBy()!=null) {
			User user = userRepo.findById(createFormDto.getUpdatedBy()).get();
			companyForm.setUpdatedBy(user);
		}

		Lead lead = leadRepository.findById(createFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(createFormDto.getPanNo());
		companyFormRepo.save(companyForm);
		return companyForm;
	}
	@GetMapping(UrlsMapping.GET_ALL_COMPANY_FORM)
	public List<Map<String,Object>> getAllCompanyForm(@RequestParam String status)
	{
		List<Map<String,Object>>result = new ArrayList<>();
		List<CompanyForm> compList = companyFormRepo.findAll();
		//		List<CompanyForm> companyList = companyFormRepo.findAllByStatus(status);
		for(CompanyForm c:compList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", c.getId());
			map.put("unitName", c.getUnitName());
			//			map.put("primaryAddress", c.get);
			map.put("companyName", c.getCompanyName());
			map.put("lead", c.getLead());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDocuments", c.getGstDocuments());
			map.put("companyAge", c.getCompanyAge());
			map.put("status", c.getStatus());
			map.put("updatedBy", c.getUpdatedBy());

			map.put("contactName", c.getContactName());
			map.put("contactNo", c.getContactNo());
			map.put("contactEmails", c.getContactEmails());
			map.put("contactWhatsappNo ", c.getContactWhatsappNo());

			map.put("secondaryContactName", c.getSContactName());
			map.put("secondaryContactNo", c.getSContactNo());
			map.put("secondaryContactEmails", c.getSContactEmails());
			map.put("secondaryContactWhatsappNo ", c.getSContactWhatsappNo());

			map.put("city", c.getCity());
			map.put("address", c.getAddress());
			map.put("state", c.getState());
			map.put("primaryPinCode", c.getPrimaryPinCode());
			map.put("country", c.getCountry());

			map.put("sCity", c.getSCity());
			map.put("sAddress", c.getSAddress());
			map.put("sState", c.getSState());
			map.put("secondaryPinCode", c.getSecondaryPinCode());
			map.put("sCountry", c.getSCountry());


			result.add(map);

		}
		return result;
	}

	//	@GetMapping(UrlsMapping.CHECK_COMPANY_EXIST)
	public Company checkCompanyExist(Long leadId)
	{
		Company company=null;
		Long compId = companyRepository.findCompanyIdByLeadId(leadId);
		if(compId!=null) {
			Optional<Company> comp = companyRepository.findById(compId);
			if(comp!=null && comp.get()!=null) {
				company=comp.get();
			}
		}

		return company;
	}
	@GetMapping(UrlsMapping.CHECK_COMPANY_EXIST)
	public Company checkCompanyExistv2(Long leadId)
	{
		Company company=null;

		Long compId = companyRepository.findCompanyIdByLeadId(leadId);
		if(compId!=null) {
			Optional<Company> comp = companyRepository.findById(compId);
			if(comp!=null && comp.get()!=null) {
				company=comp.get();
			}
		}else {
			Lead lead = leadRepository.findById(leadId).get();
			if(lead.getEmail()!=null&& (!lead.getEmail().equals("NA"))) {
				boolean flag=checkEmailInCompany(lead.getEmail());
				System.out.println("testing java    cddc  cd"+flag+lead.getEmail());
				if(flag) {
					Long cId=companyRepository.findByPrimaryEmails(lead.getEmail()); 
					System.out.println(cId);
					if(cId!=null) {
						company=companyRepository.findById(cId).get();

					}
				}else {
					List<Lead> leadList = leadRepository.findAllByEmailAndMobile(lead.getEmail(), lead.getMobileNo());

					if(leadList!=null && leadList.size()>0){
						Long comp = companyRepository.findCompanyIdByLeadId(leadList.get(0).getId());
						if(comp!=null) {
							Optional<Company> cop = companyRepository.findById(comp);
							if(cop!=null && cop.get()!=null) {
								company=cop.get();
							}
						}
					}

				}
			}

		}

		return company;
	}
	//	@PutMapping(UrlsMapping.UPDATE_COMPANY_FORM_STATUS)
	public Boolean AccountApprovalOnInvoice(String status,Long id,Long currentUserId){
		CompanyForm companyForm = companyFormRepo.findById(id).get();
		Boolean flag=false;
		if("approved".equals(status)) {
			if(companyForm.getIsPresent()) {
				Company parentCompany = companyRepository.findById(companyForm.getCompanyId()).get();
				if(companyForm.getIsUnit()) {
					Company unit = companyRepository.findById(id).get();

					List<Lead> leadList = unit.getCompanyLead();
					leadList.add(companyForm.getLead());
					unit.setCompanyLead(leadList);

					unit.setStatus("open");
					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					User assignee = unit.getAssignee();

					p.setAssignee(assignee);
					//Contact
					Contact c = new Contact();
					c.setName(companyForm.getContactName());
					c.setContactNo(companyForm.getContactNo());
					c.setEmails(companyForm.getContactEmails());
					c.setWhatsappNo(companyForm.getContactWhatsappNo());
					c.setDeleteStatus(false);
					contactRepo.save(c);

					unit.setPrimaryContact(c);
					// SecondaryContact
					Contact sc = new Contact();
					sc.setName(companyForm.getSContactName());
					sc.setContactNo(companyForm.getSContactNo());
					sc.setEmails(companyForm.getSContactEmails());
					sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
					sc.setDeleteStatus(false);
					contactRepo.save(sc);



					p.setStatus("initiated");
					p.setCreateDate(new Date());
					List<Project> projectList = unit.getCompanyProject();
					projectList.add(p);
					unit.setCompanyProject(projectList);

					unit.setSecondaryContact(sc);

					if(currentUserId!=null) {
						User user = userRepo.findById(currentUserId).get();
						companyForm.setUpdatedBy(user);
					}
					p.setCompany(unit);
					companyRepository.save(unit);
					companyForm.setStatus(status);
					companyFormRepo.save(companyForm);
					flag=true;

				}else {
					Company unit = new Company();
					unit.setName(companyForm.getCompanyName());
					//					company.setAssignee(assignee);
					unit.setCompanyAge(companyForm.getCompanyAge());
					unit.setStatus("open");


					unit.setGstNo(companyForm.getGstNo());
					unit.setGstType(companyForm.getGstType());
					unit.setGstDocuments(companyForm.getGstDocuments());
					unit.setCompanyAge(companyForm.getCompanyAge());

					unit.setAddress(companyForm.getAddress());
					unit.setCity(companyForm.getCity());
					unit.setState(companyForm.getState());
					unit.setCountry(companyForm.getCountry());

					unit.setSAddress(companyForm.getSAddress());
					unit.setSCity(companyForm.getSCity());
					unit.setSState(companyForm.getSState());
					unit.setSCountry(companyForm.getSCountry());
					if(currentUserId!=null) {
						User user = userRepo.findById(currentUserId).get();
						companyForm.setUpdatedBy(user);
					}

					Contact c = new Contact();
					c.setName(companyForm.getContactName());
					c.setContactNo(companyForm.getContactNo());
					c.setEmails(companyForm.getContactEmails());
					c.setWhatsappNo(companyForm.getContactWhatsappNo());
					c.setDeleteStatus(false);
					contactRepo.save(c);
					unit.setPrimaryContact(c);

					// SecondaryContact̥
					Contact sc = new Contact();
					sc.setName(companyForm.getSContactName());
					sc.setContactNo(companyForm.getSContactNo());
					sc.setEmails(companyForm.getSContactEmails());
					sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
					sc.setDeleteStatus(false);
					contactRepo.save(sc);

					unit.setSecondaryContact(sc);

					Lead lead = companyForm.getLead();
					List<Lead>leadList =new ArrayList<>();
					leadList.add(lead);
					unit.setCompanyLead(leadList);
					unit.setParent(false);
					unit.setParent(parentCompany);

					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					User assignee = unit.getAssignee();

					p.setAssignee(assignee);
					p.setStatus("initiated");
					p.setCreateDate(new Date());
					List<Project> projectList = new ArrayList<>();
					//					List<Project> projectList = unit.getCompanyProject();
					if(unit.getCompanyProject()!=null) {
						projectList.addAll(unit.getCompanyProject());
					}
					projectList.add(p);
					unit.setCompanyProject(projectList);
					//					p.setCompany(unit);
					companyRepository.save(unit);
					companyForm.setStatus(status);
					companyFormRepo.save(companyForm);
					flag=true;

				}


			}else {
				User assignee = userRepo.findById(companyForm.getAssigneeId()).get();

				Company company = new Company();
				company.setName(companyForm.getCompanyName());
				company.setAssignee(assignee);

				company.setGstNo(companyForm.getGstNo());
				company.setGstType(companyForm.getGstType());
				company.setGstDocuments(companyForm.getGstDocuments());		
				company.setCompanyAge(companyForm.getCompanyAge());
				company.setAssignee(assignee);
				company.setStatus("open");
				Lead lead = companyForm.getLead();
				List<Lead>leadList = new ArrayList<>();
				if(lead!=null) {
					leadList.add(lead);

				}
				company.setCompanyLead(leadList);
				company.setParent(true);
				if(currentUserId!=null) {
					User user = userRepo.findById(currentUserId).get();
					companyForm.setUpdatedBy(user);
				}

				//company
				Contact c=null;
				if(!companyForm.isPrimaryContact()){
					c=contactRepo.findById(companyForm.getContactId()).get();
				}else {
					c = new Contact();
					c.setName(companyForm.getContactName());
					c.setContactNo(companyForm.getContactNo());
					c.setEmails(companyForm.getContactEmails());
					c.setWhatsappNo(companyForm.getContactWhatsappNo());
					c.setDeleteStatus(false);
					contactRepo.save(c);

				}

				Contact sc=null;
				if(!companyForm.isSecondaryContact()){
					sc=contactRepo.findById(companyForm.getSContactId()).get();
				}else {
					sc = new Contact();

					sc.setName(companyForm.getSContactName());
					sc.setContactNo(companyForm.getSContactNo());
					sc.setEmails(companyForm.getSContactEmails());
					sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
					sc.setDeleteStatus(false);
					contactRepo.save(sc);

				}
				company.setPrimaryContact(c);
				company.setSecondaryContact(sc);



				//Assignee

				Project p = new Project();
				p.setName(companyForm.getLead().getName());
				p.setLead(companyForm.getLead());

				p.setAssignee(assignee);
				p.setStatus("initiated");
				p.setCreateDate(new Date());
				List<Project> projectList =new ArrayList<>();
				projectList.add(p);
				company.setCompanyProject(projectList);
				//				p.setCompany(unit);
				companyRepository.save(company);
				companyForm.setStatus(status);
				companyFormRepo.save(companyForm);
				flag=true;

			}

		}else {
			companyForm.setStatus(status);
			companyFormRepo.save(companyForm);
			flag=true;

		}
		return flag;
	}

	//	@PutMapping(UrlsMapping.UPDATE_COMPANY_FORM_STATUS)
	public Boolean AccountApprovalOnInvoiceV2(String status,Long id,Long currentUserId){
		CompanyForm companyForm = companyFormRepo.findById(id).get();
		Boolean flag=false;
		if("approved".equals(status)) {
			if(companyForm.getIsPresent()) {
				Company parentCompany = companyRepository.findById(companyForm.getCompanyId()).get();
				if(companyForm.getIsUnit()) {
					Company unit = companyRepository.findById(id).get();

					List<Lead> leadList = unit.getCompanyLead();
					leadList.add(companyForm.getLead());
					unit.setCompanyLead(leadList);

					unit.setStatus("open");

					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					User assignee = unit.getAssignee();
					p.setAssignee(assignee);

					//Contact
					Contact c=null;
					if(companyForm.isPrimaryContact()) {
						c = new Contact();

						c.setName(companyForm.getContactName());
						c.setContactNo(companyForm.getContactNo());
						c.setEmails(companyForm.getContactEmails());
						c.setWhatsappNo(companyForm.getContactWhatsappNo());
						c.setDeleteStatus(false);
						contactRepo.save(c);
					}else {
						c=contactRepo.findById(companyForm.getContactId()).get();
					}	

					unit.setPrimaryContact(c);

					// SecondaryContact
					Contact sc=null;
					if(companyForm.isSecondaryContact()) {
						sc = new Contact();
						sc.setName(companyForm.getSContactName());
						sc.setContactNo(companyForm.getSContactNo());
						sc.setEmails(companyForm.getSContactEmails());
						sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
						sc.setDeleteStatus(false);
						contactRepo.save(sc);

					}else {
						sc=contactRepo.findById(companyForm.getSContactId()).get();
					}
					unit.setSecondaryContact(sc);

					//ADDRESS


					p.setStatus("initiated");
					p.setCreateDate(new Date());
					List<Project> projectList = unit.getCompanyProject();
					projectList.add(p);
					unit.setCompanyProject(projectList);
					if(currentUserId!=null) {
						User user = userRepo.findById(currentUserId).get();
						companyForm.setUpdatedBy(user);
					}
					p.setCompany(unit);
					companyRepository.save(unit);
					companyForm.setStatus(status);
					companyFormRepo.save(companyForm);
					flag=true;

				}else {
					Company unit = new Company();
					unit.setName(companyForm.getCompanyName());
					//					company.setAssignee(assignee);
					unit.setCompanyAge(companyForm.getCompanyAge());
					unit.setStatus("open");


					unit.setGstNo(companyForm.getGstNo());
					unit.setGstType(companyForm.getGstType());
					unit.setGstDocuments(companyForm.getGstDocuments());
					unit.setCompanyAge(companyForm.getCompanyAge());

					unit.setAddress(companyForm.getAddress());
					unit.setCity(companyForm.getCity());
					unit.setState(companyForm.getState());
					unit.setCountry(companyForm.getCountry());

					unit.setSAddress(companyForm.getSAddress());
					unit.setSCity(companyForm.getSCity());
					unit.setSState(companyForm.getSState());
					unit.setSCountry(companyForm.getSCountry());
					if(currentUserId!=null) {
						User user = userRepo.findById(currentUserId).get();
						companyForm.setUpdatedBy(user);
					}

					//Contact
					Contact c=null;
					if(companyForm.isPrimaryContact()) {
						c = new Contact();

						c.setName(companyForm.getContactName());
						c.setContactNo(companyForm.getContactNo());
						c.setEmails(companyForm.getContactEmails());
						c.setWhatsappNo(companyForm.getContactWhatsappNo());
						c.setDeleteStatus(false);
						contactRepo.save(c);
					}else {
						c=contactRepo.findById(companyForm.getContactId()).get();
					}	
					unit.setPrimaryContact(c);

					// SecondaryContact
					Contact sc=null;
					if(companyForm.isSecondaryContact()) {
						sc = new Contact();
						sc.setName(companyForm.getSContactName());
						sc.setContactNo(companyForm.getSContactNo());
						sc.setEmails(companyForm.getSContactEmails());
						sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
						sc.setDeleteStatus(false);
						contactRepo.save(sc);

					}else {
						sc=contactRepo.findById(companyForm.getSContactId()).get();
					}
					unit.setSecondaryContact(sc);


					Lead lead = companyForm.getLead();
					List<Lead>leadList =new ArrayList<>();
					leadList.add(lead);
					unit.setCompanyLead(leadList);
					unit.setParent(false);
					unit.setParent(parentCompany);

					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					User assignee = unit.getAssignee();

					p.setAssignee(assignee);
					p.setStatus("initiated");
					p.setCreateDate(new Date());
					List<Project> projectList = new ArrayList<>();
					//					List<Project> projectList = unit.getCompanyProject();
					if(unit.getCompanyProject()!=null) {
						projectList.addAll(unit.getCompanyProject());
					}
					projectList.add(p);
					unit.setCompanyProject(projectList);
					//					p.setCompany(unit);
					companyRepository.save(unit);
					companyForm.setStatus(status);
					companyFormRepo.save(companyForm);
					flag=true;

				}


			}else {
				User assignee = userRepo.findById(companyForm.getAssigneeId()).get();

				Company company = new Company();
				company.setName(companyForm.getCompanyName());
				company.setAssignee(assignee);

				company.setGstNo(companyForm.getGstNo());
				company.setGstType(companyForm.getGstType());
				company.setGstDocuments(companyForm.getGstDocuments());		
				company.setCompanyAge(companyForm.getCompanyAge());
				company.setAssignee(assignee);
				company.setStatus("open");
				Lead lead = companyForm.getLead();
				List<Lead>leadList = new ArrayList<>();
				if(lead!=null) {
					leadList.add(lead);

				}
				company.setCompanyLead(leadList);
				company.setParent(true);
				if(currentUserId!=null) {
					User user = userRepo.findById(currentUserId).get();
					companyForm.setUpdatedBy(user);
				}

				//company
				Contact c=null;
				if(!companyForm.isPrimaryContact()){
					c=contactRepo.findById(companyForm.getContactId()).get();
				}else {
					c = new Contact();
					c.setName(companyForm.getContactName());
					c.setContactNo(companyForm.getContactNo());
					c.setEmails(companyForm.getContactEmails());
					c.setWhatsappNo(companyForm.getContactWhatsappNo());
					c.setDeleteStatus(false);
					contactRepo.save(c);

				}

				Contact sc=null;
				if(!companyForm.isSecondaryContact()){
					sc=contactRepo.findById(companyForm.getSContactId()).get();
				}else {
					sc = new Contact();

					sc.setName(companyForm.getSContactName());
					sc.setContactNo(companyForm.getSContactNo());
					sc.setEmails(companyForm.getSContactEmails());
					sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
					sc.setDeleteStatus(false);
					contactRepo.save(sc);

				}
				company.setPrimaryContact(c);
				company.setSecondaryContact(sc);



				//Assignee

				Project p = new Project();
				p.setName(companyForm.getLead().getName());
				p.setLead(companyForm.getLead());

				p.setAssignee(assignee);
				p.setStatus("initiated");
				p.setCreateDate(new Date());
				List<Project> projectList =new ArrayList<>();
				projectList.add(p);
				company.setCompanyProject(projectList);
				//				p.setCompany(unit);
				companyRepository.save(company);
				companyForm.setStatus(status);
				companyFormRepo.save(companyForm);
				flag=true;

			}

		}else {
			companyForm.setStatus(status);
			companyFormRepo.save(companyForm);
			flag=true;

		}
		return flag;
	}



	@GetMapping(UrlsMapping.GET_ALL_COMPANY_FORM_BY_STATUS_V2)
	public List<Map<String,Object>> getAllCompanyFormByStatusV2(@RequestParam String status,@RequestParam Long userId)
	{
		List<Map<String,Object>>result = new ArrayList<>();
		//		List<CompanyForm> compList = companyFormRepo.findAll();
		Optional<User> user = userRepo.findById(userId);
		List<CompanyForm> compList  = new ArrayList<>();
		if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
			compList = companyFormRepo.findAllByStatus(status);
		}else {
			compList = companyFormRepo.findAllByStatusAndassigneeId(status,userId);
		}
		//		
		for(CompanyForm c:compList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", c.getId());
			map.put("unitName", c.getUnitName());
			//			map.put("primaryAddress", c.get);
			map.put("companyName", c.getCompanyName());
			map.put("lead", c.getLead());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDocuments", c.getGstDocuments());
			map.put("companyAge", c.getCompanyAge());
			map.put("status", c.getStatus());
			map.put("updatedBy", c.getUpdatedBy());

			map.put("contactName", c.getContactName());
			map.put("contactNo", c.getContactNo());
			map.put("contactEmails", c.getContactEmails());
			map.put("contactWhatsappNo ", c.getContactWhatsappNo());

			map.put("secondaryContactName", c.getSContactName());
			map.put("secondaryContactNo", c.getSContactNo());
			map.put("secondaryContactEmails", c.getSContactEmails());
			map.put("secondaryContactWhatsappNo ", c.getSContactWhatsappNo());

			map.put("city", c.getCity());
			map.put("address", c.getAddress());
			map.put("state", c.getState());
			map.put("primaryPinCode", c.getPrimaryPinCode());
			map.put("country", c.getCountry());

			map.put("sCity", c.getSCity());
			map.put("sAddress", c.getSAddress());
			map.put("sState", c.getSState());
			map.put("secondaryPinCode", c.getSecondaryPinCode());
			map.put("sCountry", c.getSCountry());


			result.add(map);

		}
		return result;
	}

	@GetMapping(UrlsMapping.CHECK_EMAIL_IN_COMPANY)
	public boolean checkEmailInCompany(@RequestParam String email)
	{
		boolean flag=false;
		String s1=breakString(email);
		List<String>s=companyRepository.findAllEmail();
		List<String>domain = new ArrayList<>();
		for(String data:s) {
			if(data!=null) {
				String d=breakString(data);
				System.out.println(d+"...d");
				if(d!=null) {
					domain.add(d);
				}
			}
		}
		if(domain.contains(s1)) {
			flag=true;
		}
		return flag;

	}
	public String breakString( String email) {
		String[] result = email.split("@");
		if(result.length>1) {
			String s=result[1];

			String[] res=s.split("\\.");
			return res[0];
		}
		return null;


	}



	@PutMapping(UrlsMapping.UPDATE_COMPANY_FORM)
	public CompanyForm updateCompanyForm(@RequestBody UpdateCompanyFormDto UpdateCompanyFormDto)
	{

		CompanyForm companyForm =  companyFormRepo.findById(UpdateCompanyFormDto.getCompanyFormId()).get();

		companyForm.setIsPresent(UpdateCompanyFormDto.getIsPresent());
		companyForm.setCompanyName(UpdateCompanyFormDto.getCompanyName());
		companyForm.setCompanyId(UpdateCompanyFormDto.getCompanyId());
		companyForm.setComment(UpdateCompanyFormDto.getComment());
		companyForm.setAmount(UpdateCompanyFormDto.getAmount());

		companyForm.setIsUnit(UpdateCompanyFormDto.getIsUnit());
		companyForm.setUnitName(UpdateCompanyFormDto.getUnitName());
		companyForm.setUnitId(UpdateCompanyFormDto.getUnitId());

		companyForm.setAddress(UpdateCompanyFormDto.getAddress());
		companyForm.setAssigneeId(UpdateCompanyFormDto.getAssigneeId());

		companyForm.setCity(UpdateCompanyFormDto.getCity());
		companyForm.setCompanyAge(UpdateCompanyFormDto.getCompanyAge());
		companyForm.setCompanyName(UpdateCompanyFormDto.getCompanyName());
		companyForm.setCompanyId(UpdateCompanyFormDto.getCompanyId());
		companyForm.setPrimaryPinCode(UpdateCompanyFormDto.getPrimaryPinCode());
		companyForm.setCountry(UpdateCompanyFormDto.getCountry());

		companyForm.setGstType(UpdateCompanyFormDto.getGstType());
		companyForm.setGstDocuments(UpdateCompanyFormDto.getGstDocuments());
		companyForm.setGstNo(UpdateCompanyFormDto.getGstNo());
		companyForm.setCompanyAge(UpdateCompanyFormDto.getGstNo());

		companyForm.setSAddress(UpdateCompanyFormDto.getSAddress());
		companyForm.setSCity(UpdateCompanyFormDto.getSCity());
		companyForm.setSState(UpdateCompanyFormDto.getSState());
		companyForm.setSecondaryPinCode(UpdateCompanyFormDto.getSecondaryPinCode());
		companyForm.setSCountry(UpdateCompanyFormDto.getSCountry());

		companyForm.setStatus(UpdateCompanyFormDto.getStatus());
		companyForm.setIsPresent(UpdateCompanyFormDto.getIsPresent());
		companyForm.setIsUnit(UpdateCompanyFormDto.getIsUnit());
		companyForm.setStatus("Initiated");
		companyForm.setPanNo(UpdateCompanyFormDto.getPanNo());

		companyForm.setPrimaryContact(UpdateCompanyFormDto.isPrimaryContact());
		companyForm.setContactName(UpdateCompanyFormDto.getContactName());
		companyForm.setContactEmails(UpdateCompanyFormDto.getContactEmails());
		companyForm.setContactNo(UpdateCompanyFormDto.getContactNo());
		companyForm.setContactWhatsappNo(UpdateCompanyFormDto.getContactWhatsappNo());
		companyForm.setPrimaryDesignation(UpdateCompanyFormDto.getPrimaryDesignation());
		
		companyForm.setSecondaryContact(UpdateCompanyFormDto.isSecondaryContact());
		System.out.println(UpdateCompanyFormDto.getSContactName());
		companyForm.setSContactName(UpdateCompanyFormDto.getSContactName());
		System.out.println(UpdateCompanyFormDto.getSContactEmails());

		companyForm.setSContactEmails(UpdateCompanyFormDto.getSContactEmails());
		companyForm.setSContactNo(UpdateCompanyFormDto.getSContactNo());
		companyForm.setSContactWhatsappNo(UpdateCompanyFormDto.getSContactWhatsappNo());
		companyForm.setSecondaryDesignation(UpdateCompanyFormDto.getSecondaryDesignation());
		if(UpdateCompanyFormDto.getUpdatedBy()!=null) {
			User user = userRepo.findById(UpdateCompanyFormDto.getUpdatedBy()).get();
			companyForm.setUpdatedBy(user);
		}

		Lead lead = leadRepository.findById(UpdateCompanyFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(UpdateCompanyFormDto.getPanNo());
		companyForm.setState(UpdateCompanyFormDto.getState());
		companyFormRepo.save(companyForm);
		return companyForm;

	}

	@GetMapping(UrlsMapping.GET_SINGLE_COMPANY_FORM)
	public CompanyForm getAllCompanyForm(@RequestParam Long id)
	{
		CompanyForm comp = companyFormRepo.findById(id).get();
		return comp;
	}

	@GetMapping(UrlsMapping.GET_ALL_COMPANY_FORM_BY_STATUS)
	public List<Map<String,Object>> getAllCompanyFormByStatus(@RequestParam String status,@RequestParam Long userId, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{
 		List<Map<String,Object>>result = new ArrayList<>();
		//		List<CompanyForm> compList = companyFormRepo.findAll();
		Optional<User> user = userRepo.findById(userId);
		String dep = user.get()!=null?user.get().getDepartment():"NA";
		List<CompanyForm> compList  = new ArrayList<>();
		int total=0;
		if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
			//			compList = companyFormRepo.findAllByStatus(status);

			Pageable pageable = PageRequest.of(page-1, size);
			Page<CompanyForm> comp = companyFormRepo.findAllByStatus(status,pageable);
			compList=comp.getContent();
			total = companyFormRepo.findAllCountByStatus(status);



		}else if("Accounts".equals(dep)) {
			Pageable pageable = PageRequest.of(page-1, size);
			Page<CompanyForm> comp = companyFormRepo.findAllByStatus(status,pageable);
			compList=comp.getContent();
			total = companyFormRepo.findAllCountByStatus(status);

		}

		else {
			Pageable pageable = PageRequest.of(page-1, size);			
			Page<CompanyForm> comp = companyFormRepo.findAllByStatusAndassigneeId(status,userId,pageable);
			compList=comp.getContent();
			total = companyFormRepo.findAllCountByStatusAndassigneeId(status,userId);
			//			compList = companyFormRepo.findAllByStatusAndassigneeId(status,userId);
		}
		//		
		for(CompanyForm c:compList) {
			Map<String,Object>map = new HashMap<>();
			map.put("totalLeadFor", total);
			map.put("id", c.getId());
			map.put("unitName", c.getUnitName());
			//			map.put("primaryAddress", c.get);
			map.put("companyName", c.getCompanyName());
			map.put("lead", c.getLead());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDocuments", c.getGstDocuments());
			map.put("companyAge", c.getCompanyAge());
			map.put("status", c.getStatus());
			map.put("updatedBy", c.getUpdatedBy());
			map.put("contactName", c.getContactName());
			map.put("contactNo", c.getContactNo());
			map.put("contactEmails", c.getContactEmails());
			map.put("contactWhatsappNo ", c.getContactWhatsappNo());
			map.put("primaryDesignation", c.getPrimaryDesignation());

			map.put("comment", c.getComment());
			map.put("amount", c.getAmount());
			map.put("secondaryContactName", c.getSContactName());
			map.put("secondaryContactNo", c.getSContactNo());
			map.put("secondaryContactEmails", c.getSContactEmails());
			map.put("secondaryContactWhatsappNo ", c.getSContactWhatsappNo());
			map.put("secondaryDesignation", c.getSecondaryDesignation());

			map.put("panNo", c.getPanNo());

			map.put("city", c.getCity());
			map.put("address", c.getAddress());
			map.put("state", c.getState());
			map.put("primaryPinCode", c.getPrimaryPinCode());
			map.put("country", c.getCountry());

			map.put("sCity", c.getSCity());
			map.put("sAddress", c.getSAddress());
			map.put("sState", c.getSState());
			map.put("secondaryPinCode", c.getSecondaryPinCode());
			map.put("sCountry", c.getSCountry());


			result.add(map);

		}
		return result;
	}













	@PostMapping(UrlsMapping.CREATE_COMPANY_FORM)
	public CompanyForm createCompanyFormv3(@RequestBody CreateFormDto createFormDto)
	{				
		CompanyForm companyForm =  new CompanyForm();
		companyForm.setIsPresent(createFormDto.getIsPresent());
		companyForm.setPanNo(createFormDto.getPanNo());
		companyForm.setCompanyName(createFormDto.getCompanyName());
		companyForm.setAmount(createFormDto.getAmount());
		Company comp=null;
		if(createFormDto.getIsPresent()) {
			comp = companyRepository.findById(createFormDto.getCompanyId()).get();

		}

		companyForm.setCompanyId(createFormDto.getCompanyId());
		if(createFormDto.getIsPresent()&&comp!=null) { //if old company pick
			companyForm.setCompanyName(comp.getName());
			companyForm.setCompanyId(comp.getId());

		}else {
			companyForm.setIsPresent(createFormDto.getIsPresent());
			companyForm.setCompanyId(createFormDto.getCompanyId());
			companyForm.setCompanyName(createFormDto.getCompanyName());

		}
		System.out.println("3333333333333333333333333333333333333333333333333333333");

		companyForm.setIsUnit(createFormDto.getIsUnit());
		if(!createFormDto.getIsPresent()) {//new COMPANY
			System.out.println("aaaaaaaaaaaaaaaaaa");
			companyForm.setUnitName(createFormDto.getUnitName());
			companyForm.setAssigneeId(createFormDto.getAssigneeId());   
			companyForm.setCompanyAge(createFormDto.getCompanyAge());
			companyForm.setCompanyName(createFormDto.getCompanyName());

			companyForm.setGstType(createFormDto.getGstType());
			companyForm.setGstDocuments(createFormDto.getGstDocuments());
			companyForm.setGstNo(createFormDto.getGstNo());
			companyForm.setCompanyAge(createFormDto.getCompanyAge());
			companyForm.setPanNo(createFormDto.getPanNo());
			System.out.println("44444444444444444444444444444444444444444444444444444444444444");

		}
		else {
			System.out.println("cccccccccccccccccc");

			if(!createFormDto.getIsUnit()) {
				System.out.println("55555555555555555555555555555555555555555555555555555555555555");

				System.out.println("bbbbbbbbbbbb");
				companyForm.setUnitName(createFormDto.getUnitName());
				Long assigneeId = createFormDto.getAssigneeId()!=null?createFormDto.getAssigneeId():comp.getAssignee().getId();
				companyForm.setAssigneeId(assigneeId);   
				companyForm.setCompanyAge(createFormDto.getCompanyAge());
//				companyForm.setCompanyName(createFormDto.getCompanyName());
				companyForm.setCompanyName(createFormDto.getUnitName());  // company name
				
				companyForm.setGstType(createFormDto.getGstType());
				companyForm.setGstDocuments(createFormDto.getGstDocuments());
				companyForm.setGstNo(createFormDto.getGstNo());
				companyForm.setCompanyAge(createFormDto.getCompanyAge());  


			}else {
				System.out.println("6666666666666666666666666666666666666666666666666666666666666666666");

				Company unit = companyRepository.findById(createFormDto.getUnitId()).get();
				companyForm.setCompanyName(unit.getName());
				companyForm.setUnitName(unit.getName());
				companyForm.setUnitId(createFormDto.getUnitId());
				//			companyForm.setAssigneeId(unit.getAssignee());  //Add Assignee Name 
				companyForm.setCompanyAge(unit.getCompanyAge());
				companyForm.setAssigneeId(unit.getAssignee().getId());
				companyForm.setGstType(unit.getGstType());
				companyForm.setGstDocuments(unit.getGstDocuments());
				companyForm.setGstNo(unit.getGstNo());
				companyForm.setCompanyAge(unit.getCompanyAge());

				if(!createFormDto.getIsPrimaryAddress()) {
					System.out.println("ddddddddddddddddddddddddd");
					System.out.println("77777777777777777777777777777777777777777777777777777777777777777777");

					companyForm.setIsPrimaryAddress(createFormDto.getIsPrimaryAddress());
					companyForm.setAddress(unit.getAddress());
					companyForm.setCity(unit.getCity());
					companyForm.setState(unit.getState());
					companyForm.setPrimaryPinCode(companyForm.getPrimaryPinCode());  //============== Pin Code not prsent===
					companyForm.setCountry(unit.getCountry());

				}
				if(!createFormDto.getIsSecondaryAddress()) {
					System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
					System.out.println("888888888888888888888888888888888888888888888888888");

					companyForm.setIsSecondaryAddress(createFormDto.getIsSecondaryAddress());
					companyForm.setSAddress(unit.getSAddress());
					companyForm.setSCity(unit.getSCity());
					companyForm.setSState(unit.getSState());
					companyForm.setSecondaryPinCode(companyForm.getSecondaryPinCode());  //============== Pin Code not prsent===
					companyForm.setSCountry(unit.getSCountry());

				}
			}

		}
		if(createFormDto.getIsPrimaryAddress()) {
			System.out.println("ffffffffffffffffffffffffff");
			companyForm.setIsPrimaryAddress(createFormDto.getIsPrimaryAddress());

			companyForm.setAddress(createFormDto.getAddress());
			companyForm.setCity(createFormDto.getCity());
			companyForm.setState(createFormDto.getState());
			companyForm.setPrimaryPinCode(createFormDto.getPrimaryPinCode());
			companyForm.setCountry(createFormDto.getCountry());
		}

		if(createFormDto.getIsPrimaryAddress()) {
			System.out.println("ggggggggggggggggg");
			companyForm.setIsSecondaryAddress(createFormDto.getIsSecondaryAddress());
			companyForm.setSAddress(createFormDto.getSAddress());
			companyForm.setSCity(createFormDto.getSCity());
			companyForm.setSState(createFormDto.getSState());
			companyForm.setSecondaryPinCode(createFormDto.getSecondaryPinCode());
			companyForm.setSCountry(createFormDto.getSCountry());
		}


		if(createFormDto.isPrimaryContact()) {
			System.out.println("9999999999999999999999999999999999999999999999999999999");

			companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
			companyForm.setContactName(createFormDto.getContactName());
			companyForm.setContactEmails(createFormDto.getContactEmails());
			companyForm.setContactNo(createFormDto.getContactNo());
			companyForm.setContactWhatsappNo(createFormDto.getContactWhatsappNo());
	        companyForm.setPrimaryDesignation(createFormDto.getPrimaryDesignation());

		}else {
			System.out.println("101010101010100101010101001010101010100101001101010010101010");

			Contact cont = contactRepo.findById(createFormDto.getContactId()).get();
			companyForm.setPrimaryContact(createFormDto.isPrimaryContact());
			companyForm.setContactName(cont.getName());
			companyForm.setContactEmails(cont.getEmails());
			companyForm.setContactNo(cont.getContactNo());
			companyForm.setContactWhatsappNo(cont.getWhatsappNo());
	        companyForm.setPrimaryDesignation(cont.getDesignation());

		}

		if(createFormDto.isSecondaryContact()) {
			System.out.println("10aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

			companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
			companyForm.setSContactName(createFormDto.getSContactName());
			companyForm.setSContactEmails(createFormDto.getSContactEmails());
			companyForm.setSContactNo(createFormDto.getSContactNo());
			companyForm.setSContactWhatsappNo(createFormDto.getSContactWhatsappNo());
			companyForm.setSecondaryDesignation(createFormDto.getSecondaryDesignation());

		}else {
			System.out.println("10bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

			Contact cont = contactRepo.findById(createFormDto.getSContactId()).get();
			companyForm.setSecondaryContact(createFormDto.isSecondaryContact());
			companyForm.setSContactName(cont.getName());
			companyForm.setSContactEmails(cont.getEmails());
			companyForm.setSContactNo(cont.getContactNo());
			companyForm.setSContactWhatsappNo(cont.getWhatsappNo());
			companyForm.setSecondaryDesignation(cont.getDesignation());

		}
		companyForm.setStatus("Initiated");
		companyForm.setPanNo(createFormDto.getPanNo());
		System.out.println("10ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc");

		if(createFormDto.getUpdatedBy()!=null) {
			System.out.println("10eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

			User user = userRepo.findById(createFormDto.getUpdatedBy()).get();
			companyForm.setUpdatedBy(user);
		}
		System.out.println("10ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");

		Lead lead = leadRepository.findById(createFormDto.getLeadId()).get();
		companyForm.setLead(lead);
		companyForm.setPanNo(createFormDto.getPanNo());
		companyFormRepo.save(companyForm);
		return companyForm;
	}


	@PutMapping(UrlsMapping.UPDATE_COMPANY_FORM_STATUS)
	public Boolean AccountApprovalOnInvoiceV3(String status,Long id,Long currentUserId){
		CompanyForm companyForm = companyFormRepo.findById(id).get();
		Boolean flag=false;
		if("approved".equals(status)) {
			if(!companyForm.isProjectCreated()) {
				if(companyForm.getIsPresent()) {
					Company parentCompany = companyRepository.findById(companyForm.getCompanyId()).get();
					if(companyForm.getIsUnit()) {
						Company unit = companyRepository.findById(id).get();

						List<Lead> leadList = unit.getCompanyLead();
						leadList.add(companyForm.getLead());
						unit.setCompanyLead(leadList);

						unit.setStatus("open");

						Project p = new Project();

						p.setName(companyForm.getLead().getName());
						p.setLead(companyForm.getLead());
						User assignee = unit.getAssignee();
						p.setAssignee(assignee);
						p.setAmount(companyForm.getAmount());
						//address in project
						if(companyForm.getIsPrimaryAddress()) {
							p.setAddress(companyForm.getAddress());
							p.setCity(companyForm.getCity());
							p.setState(companyForm.getState());
							p.setCountry(companyForm.getCountry());
							p.setPrimaryPinCode(companyForm.getPrimaryPinCode());
						}else{
							p.setAddress(unit.getAddress());
							p.setCity(unit.getCity());
							p.setState(unit.getState());
							p.setCountry(unit.getCountry());
							p.setPrimaryPinCode(unit.getPrimaryPinCode());
						}
						if(companyForm.getIsSecondaryAddress()) {
							p.setSAddress(companyForm.getSAddress());
							p.setSCity(companyForm.getSCity());
							p.setSState(companyForm.getSState());
							p.setSCountry(companyForm.getSCountry());
							p.setSecondaryPinCode(companyForm.getSecondaryPinCode());
						}else {
							p.setSAddress(unit.getSAddress());
							p.setSCity(unit.getSCity());
							p.setSState(unit.getSState());
							p.setSCountry(unit.getSCountry());
							p.setSecondaryPinCode(unit.getSecondaryPinCode());
						}

						//Contact
						Contact c=null;
						if(companyForm.isPrimaryContact() ||companyForm.getContactId()==null) {
							c = new Contact();

							c.setName(companyForm.getContactName());
							c.setContactNo(companyForm.getContactNo());
							c.setEmails(companyForm.getContactEmails());
							c.setWhatsappNo(companyForm.getContactWhatsappNo());
							c.setDesignation(companyForm.getPrimaryDesignation());
							c.setDeleteStatus(false);
							contactRepo.save(c);
						}else {
							c=contactRepo.findById(companyForm.getContactId()).get();
						}	

						unit.setPrimaryContact(c);

						// SecondaryContact
						Contact sc=null;
						if(companyForm.isSecondaryContact()) {
							sc = new Contact();
							sc.setName(companyForm.getSContactName());
							sc.setContactNo(companyForm.getSContactNo());
							sc.setEmails(companyForm.getSContactEmails());
							sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
							sc.setDesignation(companyForm.getSecondaryDesignation());
							sc.setDeleteStatus(false);
							contactRepo.save(sc);

						}else {
							sc=contactRepo.findById(companyForm.getSContactId()).get();
						}
						unit.setSecondaryContact(sc);

						//ADDRESS
						p.setSAddress(companyForm.getSAddress());
						p.setSCity(companyForm.getSCity());
						p.setSState(companyForm.getSState());
						p.setSCountry(companyForm.getSCountry());
						p.setSecondaryPinCode(companyForm.getSecondaryPinCode());

						p.setSAddress(companyForm.getSAddress());
						p.setSCity(companyForm.getSCity());
						p.setSState(companyForm.getSState());
						p.setSCountry(companyForm.getSCountry());
						p.setSecondaryPinCode(companyForm.getSecondaryPinCode());

						p.setStatus("initiated");
						p.setAmount(companyForm.getAmount());

						p.setCreateDate(new Date());
						projectRepository.save(p);
						List<Project> projectList = unit.getCompanyProject();
						projectList.add(p);
						unit.setCompanyProject(projectList);
						if(currentUserId!=null) {
							User user = userRepo.findById(currentUserId).get();
							companyForm.setUpdatedBy(user);
						}
						p.setCompany(unit);

						companyRepository.save(unit);
						companyForm.setStatus(status);
						companyForm.setProjectCreated(true);
						companyForm.setApproved(true);
						companyFormRepo.save(companyForm);
						flag=true;

					}else {
						Company unit = new Company();
						unit.setName(companyForm.getCompanyName());
						//					company.setAssignee(assignee);
						unit.setCompanyAge(companyForm.getCompanyAge());
						unit.setStatus("open");


						unit.setGstNo(companyForm.getGstNo());
						unit.setGstType(companyForm.getGstType());
						unit.setGstDocuments(companyForm.getGstDocuments());
						unit.setCompanyAge(companyForm.getCompanyAge());

						unit.setAddress(companyForm.getAddress());
						unit.setCity(companyForm.getCity());
						unit.setState(companyForm.getState());
						unit.setCountry(companyForm.getCountry());

						unit.setSAddress(companyForm.getSAddress());
						unit.setSCity(companyForm.getSCity());
						unit.setSState(companyForm.getSState());
						unit.setSCountry(companyForm.getSCountry());
						if(currentUserId!=null) {
							User user = userRepo.findById(currentUserId).get();
							companyForm.setUpdatedBy(user);
						}

						//Contact
						Contact c=null;
						if(companyForm.isPrimaryContact()||companyForm.getContactId()==null) {
							c = new Contact();

							c.setName(companyForm.getContactName());
							c.setContactNo(companyForm.getContactNo());
							c.setEmails(companyForm.getContactEmails());
							c.setWhatsappNo(companyForm.getContactWhatsappNo());
							c.setDeleteStatus(false);
							contactRepo.save(c);
						}else {
							c=contactRepo.findById(companyForm.getContactId()).get();
						}	
						unit.setPrimaryContact(c);
                        
						// SecondaryContact
						Contact sc=null;
						if(companyForm.isSecondaryContact()) {
							sc = new Contact();
							sc.setName(companyForm.getSContactName());
							sc.setContactNo(companyForm.getSContactNo());
							sc.setEmails(companyForm.getSContactEmails());
							sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
							sc.setDeleteStatus(false);
							contactRepo.save(sc);

						}else {
							sc=contactRepo.findById(companyForm.getSContactId()).get();
						}
						unit.setSecondaryContact(sc);


						Lead lead = companyForm.getLead();
						List<Lead>leadList =new ArrayList<>();
						leadList.add(lead);
						unit.setCompanyLead(leadList);
						unit.setParent(false);
						unit.setParent(parentCompany);

						Project p = new Project();
						p.setName(companyForm.getLead().getName());
						p.setLead(companyForm.getLead());
						p.setAmount(companyForm.getAmount());

						//ADDRESS
						p.setSAddress(companyForm.getSAddress());
						p.setSCity(companyForm.getSCity());
						p.setSState(companyForm.getSState());
						p.setSCountry(companyForm.getSCountry());
						p.setSecondaryPinCode(companyForm.getSecondaryPinCode());

						p.setSAddress(companyForm.getSAddress());
						p.setSCity(companyForm.getSCity());
						p.setSState(companyForm.getSState());
						p.setSCountry(companyForm.getSCountry());
						p.setSecondaryPinCode(companyForm.getSecondaryPinCode());
                        
						p.setContact(c);

						User assignee = unit.getAssignee();



						p.setAssignee(assignee);
						p.setStatus("initiated");
						p.setCreateDate(new Date());
						projectRepository.save(p);
						List<Project> projectList = new ArrayList<>();
						//					List<Project> projectList = unit.getCompanyProject();
						if(unit.getCompanyProject()!=null) {
							projectList.addAll(unit.getCompanyProject());
						}
						projectList.add(p);
						unit.setCompanyProject(projectList);
						//					p.setCompany(unit);
						companyRepository.save(unit);
						companyForm.setStatus(status);
						companyForm.setProjectCreated(true);
						companyForm.setApproved(true);

						companyFormRepo.save(companyForm);
						flag=true;

					}


				}else {
					User assignee = userRepo.findById(companyForm.getAssigneeId()).get();

					Company company = new Company();
					company.setName(companyForm.getCompanyName());
					company.setAssignee(assignee);

					company.setGstNo(companyForm.getGstNo());
					company.setGstType(companyForm.getGstType());
					company.setGstDocuments(companyForm.getGstDocuments());		
					company.setCompanyAge(companyForm.getCompanyAge());
					company.setAssignee(assignee);
					company.setStatus("open");

					Lead lead = companyForm.getLead();
					List<Lead>leadList = new ArrayList<>();
					if(lead!=null) {
						leadList.add(lead);

					}
					company.setCompanyLead(leadList);
					company.setParent(true);
					if(currentUserId!=null) {
						User user = userRepo.findById(currentUserId).get();
						companyForm.setUpdatedBy(user);
					}

					//company
					Contact c=null;
					if((!companyForm.isPrimaryContact()) &&companyForm.getContactId()!=null){
						c=contactRepo.findById(companyForm.getContactId()).get();
					}else {
						c = new Contact();
						c.setName(companyForm.getContactName());
						c.setContactNo(companyForm.getContactNo());
						c.setEmails(companyForm.getContactEmails());
						c.setWhatsappNo(companyForm.getContactWhatsappNo());
						c.setDeleteStatus(false);
						contactRepo.save(c);

					}

					Contact sc=null;
					if(!companyForm.isSecondaryContact() &&companyForm.getSContactId()!=null){
						sc=contactRepo.findById(companyForm.getSContactId()).get();
					}else {
						sc = new Contact();

						sc.setName(companyForm.getSContactName());
						sc.setContactNo(companyForm.getSContactNo());
						sc.setEmails(companyForm.getSContactEmails());
						sc.setWhatsappNo(companyForm.getSContactWhatsappNo());
						sc.setDeleteStatus(false);
						contactRepo.save(sc);

					}
					company.setPrimaryContact(c);
					company.setSecondaryContact(sc);

					//Address
					company.setAddress(companyForm.getAddress());
					company.setCity(companyForm.getCity());
					company.setState(companyForm.getState());
					company.setCountry(companyForm.getCountry());

					company.setSAddress(companyForm.getSAddress());
					company.setSCity(companyForm.getSCity());
					company.setSState(companyForm.getSState());
					company.setSCountry(companyForm.getSCountry());

					//Assignee

					Project p = new Project();
					p.setName(companyForm.getLead().getName());
					p.setLead(companyForm.getLead());
					p.setAmount(companyForm.getAmount());

					//ADDRESS
					p.setSAddress(companyForm.getSAddress());
					p.setSCity(companyForm.getSCity());
					p.setSState(companyForm.getSState());
					p.setSCountry(companyForm.getSCountry());
					p.setSecondaryPinCode(companyForm.getSecondaryPinCode());

					p.setSAddress(companyForm.getSAddress());
					p.setSCity(companyForm.getSCity());
					p.setSState(companyForm.getSState());
					p.setSCountry(companyForm.getSCountry());
					p.setSecondaryPinCode(companyForm.getSecondaryPinCode());

					p.setAssignee(assignee);
					p.setStatus("initiated");
					p.setCreateDate(new Date());
					projectRepository.save(p);

					List<Project> projectList =new ArrayList<>();
					projectList.add(p);
					company.setCompanyProject(projectList);
					//				p.setCompany(unit);
					companyRepository.save(company);
					companyForm.setStatus(status);
					companyForm.setApproved(true);
					companyFormRepo.save(companyForm);
					flag=true;

				}
			}else {
				companyForm.setApproved(true);
				companyForm.setStatus(status);
				companyFormRepo.save(companyForm);
				flag=true;
			}

		}else {
			companyForm.setStatus(status);
			companyForm.setApproved(false);
			companyFormRepo.save(companyForm);
			flag=true;

		}
		return flag;
	}


	@GetMapping(UrlsMapping.GET_BY_COMPANY_FORM)
	public ResponseEntity<List<Map<String, Object>>> searchCompanyFormDataByStatus(
			@RequestParam String searchNameAndGSt,
			@RequestParam Long userId,
			@RequestParam String status,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		try {
			Optional<User> user = userRepo.findById(userId);
			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			String dep = user.get().getDepartment();
			List<CompanyForm> compList = new ArrayList<>();
			Pageable pageable = PageRequest.of(page, size);

			if (user.get().getRole().contains("ADMIN") || "Accounts".equals(dep)) {
				Page<CompanyForm> companyForms = companyFormRepo.findByCompanyNameOrGstNoAndStatus(searchNameAndGSt, status, pageable);
				compList = companyForms.getContent();
			}
			if (compList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			List<Map<String, Object>> result = new ArrayList<>();
			for (CompanyForm c : compList) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", c.getId());
				map.put("unitName", c.getUnitName());
				map.put("companyName", c.getCompanyName());
				map.put("lead", c.getLead());
				map.put("gstNo", c.getGstNo());
				map.put("gstType", c.getGstType());
				map.put("gstDocuments", c.getGstDocuments());
				map.put("companyAge", c.getCompanyAge());
				map.put("status", c.getStatus());
				map.put("updatedBy", c.getUpdatedBy());
				map.put("contactName", c.getContactName());
				map.put("contactNo", c.getContactNo());
				map.put("contactEmails", c.getContactEmails());
				map.put("contactWhatsappNo", c.getContactWhatsappNo());
				map.put("secondaryContactName", c.getSContactName());
				map.put("secondaryContactNo", c.getSContactNo());
				map.put("secondaryContactEmails", c.getSContactEmails());
				map.put("secondaryContactWhatsappNo", c.getSContactWhatsappNo());
				map.put("city", c.getCity());
				map.put("address", c.getAddress());
				map.put("state", c.getState());
				map.put("primaryPinCode", c.getPrimaryPinCode());
				map.put("country", c.getCountry());
				map.put("sCity", c.getSCity());
				map.put("sAddress", c.getSAddress());
				map.put("sState", c.getSState());
				map.put("secondaryPinCode", c.getSecondaryPinCode());
				map.put("sCountry", c.getSCountry());

				result.add(map);
			}

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(UrlsMapping.GET_COMPANY_COMMENT)
	public String getCompanyComment(@RequestParam Long companyFormId){
		String comment=companyFormRepo.findCommentById(companyFormId);
		return comment;
	}


	@PutMapping(UrlsMapping.ADD_COMMENT)
	public Boolean addComment(@RequestParam Long companyFormId,String comment){
		Boolean flag=false;
		CompanyForm companyForm = companyFormRepo.findById(companyFormId).get();
		companyForm.setComment(comment);
		companyFormRepo.save(companyForm);
		flag=true;
		return flag;
	}



}
