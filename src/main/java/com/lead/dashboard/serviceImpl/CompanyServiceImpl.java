package com.lead.dashboard.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.ConsultantByCompany;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.UpdateCompanyDto;
import com.lead.dashboard.repository.CompanyHistoryRepo;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.ConsultantByCompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	CompanyHistoryRepo companyHistoryRepo;

	@Autowired
	LeadRepository leadRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ConsultantByCompanyRepository consultantByCompanyRepository;
	
	
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	@Override
	public Company createCompany(CompanyDto companyDto)  {
		Company companyExist = companyRepository.findByName(companyDto.getName());
		if(companyExist ==null) {

			Company company = new Company();
			company.setName(companyDto.getName());
			company.setCompanyAge(companyDto.getCompanyAge());
			company.setGstNo(companyDto.getGstNo());
			company.setPanNo(companyDto.getPanNo());
			company.setAddress(companyDto.getAddress());
			company.setCity(companyDto.getCity());
			company.setCountry(companyDto.getCountry());
			company.setState(companyDto.getState());

			List<Lead> leadList=leadRepository.findAllByIdIn(companyDto.getLeadId());

			company.setCompanyLead(leadList);
			companyDto.getAssigneeId();
			if(companyDto.isParent()) {
				Company parent = companyRepository.findById(companyDto.getParentId()).get();
				company.setParent(parent);
				company.setParent(companyDto.isParent());
				User user = userRepo.findById(companyDto.getAssigneeId()).get();
				company.setAssignee(user);
				companyRepository.save(company);

			}else {
				company.setParent(companyDto.isParent());
				//				 Company parent = companyRepository.findById(companyDto.getParentId()).get();
				//				company.setParent(parent);
				companyRepository.save(company);

			}
			//		companyRepository.save(company);

			return company;
		}else {
			String name=companyExist.getAssignee()!=null?companyExist.getAssignee().getFullName():"NA";
			//			throw new Exception("Already Exist . "+name+" are working on it");
			return null;
		}
	}

	@Override
	public Company getCompany(Long companyId) {
		// TODO Auto-generated method stub
		Optional<Company> company = companyRepository.findById(companyId);
		return company.get();
	}

	@Override
	public List<Map<String,Object>> getAllCompany(Long userId) {
		Optional<User> userOp = userRepo.findById(userId);
		List<Company> companyList = new ArrayList<>();
		User user = userOp.get();
		List<String> roleList = user.getRole();
		if(roleList.contains("ADMIN")) {
			companyList = companyRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());

		}else {
			if(user.isManager()) {
				List<Long>userList = new ArrayList<>();
				userList.add(userId);
				List<Long> uList = userServiceImpl.getUserManager(userId);
				userList.addAll(uList);
				companyList =companyRepository.findAllByAssigneeIdIn(userList);

			}else {

				companyList =companyRepository.findByAssigneeId(userId);

			}
		}
		//		List<Company> companyList = companyRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		List<Map<String,Object>>res = new ArrayList<>();
		for(Company c:companyList) {
			Map<String,Object>result = new HashMap<>();

			result.put("companyId", c.getId());
			result.put("companyName", c.getName());
			result.put("country", c.getCountry());
			result.put("gstNo", c.getGstNo());
			result.put("gstType", c.getGstType());
			result.put("gstDoc", c.getGstDocuments());


			result.put("assignee", c.getAssignee());

			result.put("address", c.getAddress());
			result.put("city", c.getCity());
			result.put("state", c.getState());
			result.put("country", c.getCountry());

			result.put("secAddress", c.getSAddress());
			result.put("secCity", c.getSCity());
			result.put("secState", c.getSState());
			result.put("seCountry", c.getSCountry());

			result.put("primaryContact", c.getPrimaryContact());
			result.put("secondaryContact", c.getSecondaryContact());


			List<Lead> lList = c.getCompanyLead();
			List<Map<String,Object>>leadList = new ArrayList<>();
			for(Lead l:lList) {
				Map<String,Object>lMap = new HashMap<>();
				lMap.put("leadId", l.getId());
				lMap.put("leadNameame", l.getLeadName());
				leadList.add(lMap);

			}
			result.put("lead", leadList);

			List<Project> pList = c.getCompanyProject();
			List<Map<String,Object>>projectList = new ArrayList<>();
			for(Project p:pList) {
				Map<String,Object>pMap = new HashMap<>();
				pMap.put("projectId", p.getId());
				pMap.put("projectName", p.getName());
				projectList.add(pMap);				
			}
			result.put("project", projectList);
			res.add(result);

		}
		return res;
	}


	public List<Map<String,Object>> getAllCompanyV2(Long userId, Long filterUserId, int page, int size) {
		Optional<User> userOp = userRepo.findById(userId);
		List<Company> companyList = new ArrayList<>();
		User user = userOp.get();
		Pageable pageable = PageRequest.of(page, size);
		List<String> roleList = user.getRole();
		int total=0;
		if(filterUserId==null||filterUserId==0) {

			if(roleList.contains("ADMIN")) {
				companyList=companyRepository.findAll(pageable).getContent();
				total=companyRepository.findAll().size();

			}else {
				if(user.isManager()) {
					List<Long>userList = new ArrayList<>();
					userList.add(userId);
					List<Long> uList = userServiceImpl.getUserManager(userId);
					userList.addAll(uList);
					companyList =companyRepository.findAllByAssigneeIdIn(userList,pageable).getContent();
					total =companyRepository.findAllCountByAssigneeIdIn(userList);

				}else {

					companyList =companyRepository.findByAssigneeId(userId,pageable).getContent();
					total =companyRepository.findCountByAssigneeId(userId);

				}
			}
		}else {
			if(roleList.contains("ADMIN")) {
				List<Long>userIds = new ArrayList<>();
				userIds.add(filterUserId);
				companyList =companyRepository.findAllByAssigneeIdIn(userIds,pageable).getContent();
				total =companyRepository.findAllCountByAssigneeIdIn(userIds);

			}else {
				if(user.isManager()) {
					List<Long>userList = new ArrayList<>();
					userList.add(userId);
					List<Long> uList = userServiceImpl.getUserManager(userId);
					userList.addAll(uList);
					companyList =companyRepository.findAllByAssigneeIdIn(userList,pageable).getContent();
					total =companyRepository.findAllCountByAssigneeIdIn(userList);


				}else {

					companyList =companyRepository.findByAssigneeId(userId,pageable).getContent();
					total =companyRepository.findCountByAssigneeId(userId);

				}
			}
		}
		List<Map<String,Object>>res = new ArrayList<>();
		for(Company c:companyList) {
		
			Map<String,Object>result = new HashMap<>();
			result.put("total", total);

			result.put("companyId", c.getId());
			result.put("companyName", c.getName());
			result.put("country", c.getCountry());
			result.put("gstNo", c.getGstNo());
			result.put("gstType", c.getGstType());
			result.put("gstDoc", c.getGstDocuments());
			result.put("tempAssignee", c.getTempAssignee());


			result.put("assignee", c.getAssignee());

			result.put("address", c.getAddress());
			result.put("city", c.getCity());
			result.put("state", c.getState());
			result.put("country", c.getCountry());

			result.put("secAddress", c.getSAddress());
			result.put("secCity", c.getSCity());
			result.put("secState", c.getSState());
			result.put("seCountry", c.getSCountry());

			result.put("primaryContact", c.getPrimaryContact());
			result.put("secondaryContact", c.getSecondaryContact());


			List<Lead> lList = c.getCompanyLead();
			List<Map<String,Object>>leadList = new ArrayList<>();
			for(Lead l:lList) {
				Map<String,Object>lMap = new HashMap<>();
				lMap.put("leadId", l.getId());
				lMap.put("leadNameame", l.getLeadName());
				leadList.add(lMap);

			}
			result.put("lead", leadList);

			List<Project> pList = c.getCompanyProject();
			List<Map<String,Object>>projectList = new ArrayList<>();
			for(Project p:pList) {
				Map<String,Object>pMap = new HashMap<>();
				pMap.put("projectId", p.getId());
				pMap.put("projectName", p.getName());
				projectList.add(pMap);				
			}
			result.put("project", projectList);
			res.add(result);

		}
		
		return res;
	}

	@Override
	public boolean deleteCompany(Long companyId) {
		// TODO Auto-generated method stub
		Optional<Company> companyOp = companyRepository.findById(companyId);
		boolean flag=false;
		if(companyOp.get()!=null) {
			Company company = companyOp.get();
			company.setDeleted(true);
			companyRepository.save(company);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> getAllProjectByCompany(Long companyId) {
		Optional<Company> companyOp = companyRepository.findById(companyId);
		List<Map<String, Object>> result = new ArrayList<>();
		if(companyOp.get()!=null) {
			Company company = companyOp.get();
			List<Project>projectList= company.getCompanyProject();
			for(Project p:projectList) {
				Map<String, Object> map = new HashMap<>();
				map.put("projectId", p.getId());
				map.put("projectName", p.getName());
				map.put("assignee", p.getAssignee());
				map.put("product", p.getProduct());
				map.put("status", p.getStatus());
				map.put("client", company.getPrimaryContact());
				map.put("companyName", company.getName());
				map.put("createDate", p.getCreateDate()); 
				map.put("pAddress", p.getAddress());
				map.put("pCity", p.getCity());
				map.put("pState", p.getState());
				map.put("pCountry", p.getCountry());
				map.put("pPinCode", p.getPrimaryPinCode());

				map.put("sAddress", p.getSAddress());
				map.put("sCity", p.getSCity());
				map.put("sState", p.getSState());
				map.put("sCountry", p.getSCountry());
				map.put("sPinCode", p.getSecondaryPinCode());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getAllLeadByCompany(Long companyId) {

		Optional<Company> companyOp = companyRepository.findById(companyId);
		List<Map<String, Object>> result = new ArrayList<>();
		if(companyOp.get()!=null) {
			Company company = companyOp.get();
			List<Lead>leadList= company.getCompanyLead();
			for(Lead l:leadList) {
				Map<String, Object> map = new HashMap<>();
				map.put("leadId", l.getId());
				map.put("leadName", l.getLeadName());
				map.put("assignee", l.getAssignee());
				map.put("client", l.getName());
				map.put("ipAddress", l.getIpAddress());
				map.put("originalName", l.getOriginalName());
				map.put("categoryId", l.getCategoryId());
				map.put("city", l.getCity());
				map.put("assigne", l.getAssignee());
				map.put("displayStatus", l.getDisplayStatus());
				map.put("description", l.getLeadDescription());
				map.put("email", l.getEmail());
				map.put("ipAddress", l.getIpAddress());
				map.put("remarks", l.getRemarks());
				map.put("status", l.getStatus());
				map.put("isBacklog", l.isBacklogTask());

				result.add(map);
			}
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getAllParentCompany() {
		List<Company>companies=companyRepository.findAllByIsParent();
		List<Map<String, Object>> result = new ArrayList<>();
		for(Company c:companies) {
			Map<String, Object>map = new HashMap<>();
			map.put("value", c.getId());
			map.put("label", c.getName());
			map.put("isParent", c.isParent());
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> getAllCompanyUnit(Long id) {
		List<Company>comp=companyRepository.findAllCompanyUnitByCompanyId(id);
		List<Map<String,Object>>res = new ArrayList<>();
		for(Company c:comp) {
			Map<String,Object> map = new HashMap<>();
			map.put("id", c.getId());
			map.put("companyName", c.getName());
			//			map.put("companyId", c.getId());
			map.put("country", c.getCountry());
			map.put("gstNo", c.getGstNo());
			map.put("gstType", c.getGstType());
			map.put("gstDoc", c.getGstDocuments());


			map.put("assignee", c.getAssignee());

			map.put("address", c.getAddress());
			map.put("city", c.getCity());
			map.put("state", c.getState());
			map.put("country", c.getCountry());

			map.put("secAddress", c.getSAddress());
			map.put("secCity", c.getSCity());
			map.put("secState", c.getSState());
			map.put("seCountry", c.getSCountry());

			map.put("primaryContact", c.getPrimaryContact());
			map.put("secondaryContact", c.getSecondaryContact());
			res.add(map);
		}
		return res;
	}

	@Override
	public List<Company> getCompanyByGst(String gst) {
		//		List<Company>companyList=companyRepository.findByGst(gst);
		List<Company>companyList=new ArrayList<>();
		return companyList;
	}

	@Override
	public Map<String, Object> getCompanyById(Long id) {
		Company comp = companyRepository.findById(id).get();
		Map<String, Object> res = new HashMap<>();
		res.put("id", comp.getId());
		res.put("name", comp.getName());
		res.put("status", comp.getStatus());


		res.put("gstType", comp.getGstType());
		res.put("gstNo", comp.getGstNo());
		res.put("gstDoc", comp.getGstDocuments());

		res.put("address", comp.getAddress());
		res.put("city", comp.getCity());
		res.put("state", comp.getState());
		res.put("country", comp.getCountry());


		res.put("status", comp.getStatus());
		res.put("email", comp.getPrimaryContact());
		res.put("assigneeId", comp.getAssignee()!=null?comp.getAssignee().getId():"NA");
		res.put("assigneeName",  comp.getAssignee()!=null?comp.getAssignee().getFullName():"NA");
		res.put("assigneeRole",  comp.getAssignee()!=null?comp.getAssignee().getRole():"NA");
		res.put("assigneeEmail",  comp.getAssignee()!=null?comp.getAssignee().getEmail():"NA");


		return res;
	}

	@Override
	public boolean updateCompanyAssignee(Long companyId, Long assigneeId,Long currentUserId) {
		Company comp = companyRepository.findById(companyId).get();
		User assignee = userRepo.findById(assigneeId).get();
		User prevAssignee=comp.getAssignee();
		
		comp.setAssignee(assignee);
		companyRepository.save(comp);
		createHistory(assignee,prevAssignee,currentUserId,comp);
		List<Company> unit = companyRepository.findAllCompanyUnitByCompanyId(companyId);
		List<String> emailList = unit.stream().map(i->i.getPrimaryContact().getEmails()).collect(Collectors.toList());
		
		List<String> ccEmailList = unit.stream().map(i->i.getPrimaryContact().getEmails()).collect(Collectors.toList());
        System.out.println(emailList);
		Context context = new Context();
		context.setVariable("companyName", comp.getName());
		context.setVariable("clientName", comp.getPrimaryContact().getName());
		String subject="Introduction as your Key Account Manager for Compliance and Safety";
		context.setVariable("userName", assignee.getFullName());
		context.setVariable("userDesignation", assignee.getDesignation());
		context.setVariable("userEmail", assignee.getEmail());
		context.setVariable("userContNumber", "7651959792");
//		String[] ccPerson= {"aryan.chaurasia@corpseed.com"};
//		String[] toMail= {"aryan.chaurasia@corpseed.com"};
//		mailSendSerivceImpl.sendEmail(toMail, subject,"test",context,"companyAssignee.html");
		
		String[] ccPerson= ccEmailList.toArray(new String[ccEmailList.size()]);
		String[] toMail=emailList.toArray(new String[emailList.size()]);
		System.out.println("ccPerson  . . "+ccPerson.length);
		System.out.println("toPerson  . . "+toMail.length);

//		mailSendSerivceImpl.sendEmail(toMail,ccPerson, subject,context,"companyAssignee.html");
	    
		return true;
	}
//	public void sendCompanyAssigneeMail(User Assignee) {
//		Context context1 = new Context();
//		context1.setVariable("userName", "Aryan Chaurasia");
//		context1.setVariable("user", u.getFullName());
//		String subject="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
//		context1.setVariable("email", u.getEmail());
//		context1.setVariable("Rurl", feedbackStatusURLs);
//		context1.setVariable("currentYear", LocalDateTime.now().getYear());
//		String subject1="Corpseed pvt ltd send a request for adding on team please go and set password and accept";
//		String[] ccPerson= {u.getEmail()};
//		String[] toMail= {manager.getEmail()};
//		mailSendSerivceImpl.sendEmail(toMail, subject,"test",context1,"createUserManager.html");
//	
//	}

	
	
	public void createHistory(User postAssignee,User prevAssignee,Long currentUserId,Company comp) {
		CompanyDataHistory compHistory = new CompanyDataHistory();
		compHistory.setCompany(comp);
		compHistory.setCreateDate(new Date());
		
		User user = userRepo.findById(currentUserId).get();	
		String fullName=user!=null?user.getFullName():"NA";
		compHistory.setCreatedBy(user);
		
		String description="company assignee has been change from "+prevAssignee!=null?prevAssignee.getFullName():"NA"+" to "
				+ ""+postAssignee.getFullName()+" by "+fullName;
		compHistory.setDescription(description);
		compHistory.setEventType("Assignee has been changed");
		companyHistoryRepo.save(compHistory);
	}

	public List<Map<String, Object>> searchCompanyByNameAndGST(String searchNameAndGST, Long userId) {
		User user = userRepo.findByUserIdAndIsDeletedFalse(userId);

		if (user == null) {
			return new ArrayList<>();
		}

		List<Company> companyList = new ArrayList<>();

		if (user.getRole().contains("ADMIN")) {
			companyList = companyRepository.findByNameOrGST(searchNameAndGST);
		} else {
			if (searchNameAndGST != null && !searchNameAndGST.isEmpty()) {
				companyList = companyRepository.findAllByCompanyNameAndGSTNumber(searchNameAndGST, userId);
			} else {
				return new ArrayList<>();
			}
		}

		List<Map<String, Object>> res = new ArrayList<>();
		for (Company c : companyList) {
			Map<String, Object> result = new HashMap<>();
			result.put("companyId", c.getId());
			result.put("companyName", c.getName());
			result.put("country", c.getCountry());
			result.put("gstNo", c.getGstNo());
			result.put("gstType", c.getGstType());
			result.put("gstDoc", c.getGstDocuments());
			result.put("assignee", c.getAssignee());
			result.put("address", c.getAddress());
			result.put("city", c.getCity());
			result.put("state", c.getState());
			result.put("country", c.getCountry());
			result.put("secAddress", c.getSAddress());
			result.put("secCity", c.getSCity());
			result.put("secState", c.getSState());
			result.put("seCountry", c.getSCountry());
			result.put("primaryContact", c.getPrimaryContact());
			result.put("secondaryContact", c.getSecondaryContact());

			List<Lead> lList = c.getCompanyLead();
			List<Map<String, Object>> leadList = new ArrayList<>();
			for (Lead l : lList) {
				Map<String, Object> lMap = new HashMap<>();
				lMap.put("leadId", l.getId());
				lMap.put("leadNameame", l.getLeadName());
				leadList.add(lMap);
			}
			result.put("lead", leadList);

			List<Project> pList = c.getCompanyProject();
			List<Map<String, Object>> projectList = new ArrayList<>();
			for (Project p : pList) {
				Map<String, Object> pMap = new HashMap<>();
				pMap.put("projectId", p.getId());
				pMap.put("projectName", p.getName());
				projectList.add(pMap);
			}
			result.put("project", projectList);

			res.add(result);
		}

		return res;
	}

	@Override
	public boolean updateMultiCompanyAssignee(UpdateCompanyDto updateCompanyDto) {
		Boolean flag=false;
		Optional<User> userOp = userRepo.findById(updateCompanyDto.getCurrentUserId());
		if(userOp!=null && userOp.get()!=null) {
			User user = userOp.get();
			List<String> role = user.getUserRole().stream().map(i->i.getName()).collect(Collectors.toList());
			if(role.contains("ADMIN")) {
				User assignee = userRepo.findById(updateCompanyDto.getAssigneeId()).get();
				
				List<Company>comp=companyRepository.findByIdIn(updateCompanyDto.getCompanyId());
				for(Company c:comp) {
					User prevAssignee=c.getAssignee();
					c.setAssignee(assignee);
				    companyRepository.save(c);
					createHistory(assignee,prevAssignee,updateCompanyDto.getCurrentUserId(),c);
					//--===
					List<Company> unit = companyRepository.findAllCompanyUnitByCompanyId(c.getId());
					List<String> emailList = unit.stream().map(i->i.getPrimaryContact().getEmails()).collect(Collectors.toList());
					List<String> ccEmailList = unit.stream().map(i->i.getPrimaryContact().getEmails()).collect(Collectors.toList());
					System.out.println(emailList);
					Context context = new Context();
					context.setVariable("companyName", c.getName());
					context.setVariable("clientName", c.getPrimaryContact().getName());
					String subject="Introduction as your Key Account Manager for Compliance and Safety";
					context.setVariable("userName", assignee.getFullName());
					context.setVariable("userDesignation", assignee.getDesignation());
					context.setVariable("userEmail", assignee.getEmail());
					context.setVariable("userContNumber", "7651959792");
//					String[] ccPerson= {"aryan.chaurasia@corpseed.com"};
//					String[] toMail= {"aryan.chaurasia@corpseed.com"};
            
					String[] ccPerson= ccEmailList.toArray(new String[ccEmailList.size()]);
					String[] toMail=emailList.toArray(new String[emailList.size()]);
					System.out.println("ccPerson  . . "+ccPerson.length);
					System.out.println("toPerson  . . "+toMail.length);

//					mailSendSerivceImpl.sendEmail(toMail,ccPerson, subject,context,"companyAssignee.html");
				    flag=true;
				}
				
			}
		}
		return flag;
	}
	@Override
	public boolean updateMultiCompanyTempAssignee(UpdateCompanyDto updateCompanyDto) {
		Boolean flag=false;
		Optional<User> userOp = userRepo.findById(updateCompanyDto.getCurrentUserId());
		if(userOp!=null && userOp.get()!=null) {
			User user = userOp.get();
			List<String> role = user.getUserRole().stream().map(i->i.getName()).collect(Collectors.toList());
			if(role.contains("ADMIN")) {
				User assignee = userRepo.findById(updateCompanyDto.getAssigneeId()).get();
				
				List<Company>comp=companyRepository.findByIdIn(updateCompanyDto.getCompanyId());
				for(Company c:comp) {
					c.setTempAssignee(assignee);
				    companyRepository.save(c);					
				}
				
			}
		}
		return flag;
	}

	
	public List<Map<String,Object>> getAllTempCompany(Long userId, Long filterUserId, int page, int size) {
		Optional<User> userOp = userRepo.findById(userId);
		List<Company> companyList = new ArrayList<>();
		User user = userOp.get();
		Pageable pageable = PageRequest.of(page, size);
		List<String> roleList = user.getRole();
		int total=0;
		if(filterUserId==null||filterUserId==0) {

			if(roleList.contains("ADMIN")) {
				companyList=companyRepository.findAll(pageable).getContent();
				total=companyRepository.findAll().size();

			}else {
				if(user.isManager()) {
					List<Long>userList = new ArrayList<>();
					userList.add(userId);
					List<Long> uList = userServiceImpl.getUserManager(userId);
					userList.addAll(uList);
					companyList =companyRepository.findAllByTempAssigneeIdIn(userList,pageable).getContent();
					total =companyRepository.findAllCountByTempAssigneeIdIn(userList);

				}else {

					companyList =companyRepository.findByTempAssigneeId(userId,pageable).getContent();
					total =companyRepository.findCountByTempAssigneeId(userId);

				}
			}
		}else {
			if(roleList.contains("ADMIN")) {
				List<Long>userIds = new ArrayList<>();
				userIds.add(filterUserId);
				companyList =companyRepository.findAllByTempAssigneeIdIn(userIds,pageable).getContent();
				total =companyRepository.findAllCountByTempAssigneeIdIn(userIds);

			}else {
				if(user.isManager()) {
					List<Long>userList = new ArrayList<>();
					userList.add(userId);
					List<Long> uList = userServiceImpl.getUserManager(userId);
					userList.addAll(uList);
					companyList =companyRepository.findAllByTempAssigneeIdIn(userList,pageable).getContent();
					total =companyRepository.findAllCountByTempAssigneeIdIn(userList);


				}else {

					companyList =companyRepository.findByTempAssigneeId(userId,pageable).getContent();
					total =companyRepository.findCountByTempAssigneeId(userId);

				}
			}
		}
		List<Map<String,Object>>res = new ArrayList<>();
		for(Company c:companyList) {
		
			Map<String,Object>result = new HashMap<>();
			result.put("total", total);

			result.put("companyId", c.getId());
			result.put("companyName", c.getName());
			result.put("country", c.getCountry());
			result.put("gstNo", c.getGstNo());
			result.put("gstType", c.getGstType());
			result.put("gstDoc", c.getGstDocuments());

			result.put("tempAssignee", c.getTempAssignee());

			result.put("assignee", c.getAssignee());

			result.put("address", c.getAddress());
			result.put("city", c.getCity());
			result.put("state", c.getState());
			result.put("country", c.getCountry());

			result.put("secAddress", c.getSAddress());
			result.put("secCity", c.getSCity());
			result.put("secState", c.getSState());
			result.put("seCountry", c.getSCountry());

			result.put("primaryContact", c.getPrimaryContact());
			result.put("secondaryContact", c.getSecondaryContact());


			List<Lead> lList = c.getCompanyLead();
			List<Map<String,Object>>leadList = new ArrayList<>();
			for(Lead l:lList) {
				Map<String,Object>lMap = new HashMap<>();
				lMap.put("leadId", l.getId());
				lMap.put("leadNameame", l.getLeadName());
				leadList.add(lMap);

			}
			result.put("lead", leadList);

			List<Project> pList = c.getCompanyProject();
			List<Map<String,Object>>projectList = new ArrayList<>();
			for(Project p:pList) {
				Map<String,Object>pMap = new HashMap<>();
				pMap.put("projectId", p.getId());
				pMap.put("projectName", p.getName());
				projectList.add(pMap);				
			}
			result.put("project", projectList);
			res.add(result);

		}
		
		return res;
	}

	@Override
	public boolean deleteTempAssignee(UpdateCompanyDto updateCompanyDto) {
		Boolean flag=false;
		Optional<User> userOp = userRepo.findById(updateCompanyDto.getCurrentUserId());
		if(userOp!=null && userOp.get()!=null) {
			User user = userOp.get();
			List<String> role = user.getUserRole().stream().map(i->i.getName()).collect(Collectors.toList());
			if(role.contains("ADMIN")) {
				User assignee = userRepo.findById(updateCompanyDto.getAssigneeId()).get();
				
				List<Company>comp=companyRepository.findByIdIn(updateCompanyDto.getCompanyId());
				for(Company c:comp) {
					c.setTempAssignee(null);
					companyRepository.save(c);
					flag=true;
				}
			}
		}
		return flag;
	}

	@Override
	public List<Map<String, Object>> getAllConsultantByCompany(Long userId,int page, int size) {
		Optional<User> userOp = userRepo.findById(userId);
		List<Map<String,Object>>arr = new ArrayList<>();
		if(userOp!=null && userOp.get()!=null) {
			User user = userOp.get();
			if(user!=null && user.getRole().contains("ADMIN")) {
				List<ConsultantByCompany> consultList = consultantByCompanyRepository.findAll();
			    for(ConsultantByCompany c:consultList) {
			    	Map<String,Object>map=new HashMap<>();
			    	map.put("id", c.getId());
			    	map.put("originalName", c.getName());
			    	map.put("originalContact", c.getOriginalContact());
			    	map.put("originalEmail", c.getOriginalEmail());
			    	arr.add(map);
			    }
			}
			
		}
		return arr;
	}


	@Override
	public int getAllConsultantByCompanyCount(Long userId) {
		Optional<User> userOp = userRepo.findById(userId);
		int consultList=0;
		List<Map<String,Object>>arr = new ArrayList<>();
		if(userOp!=null && userOp.get()!=null) {
			User user = userOp.get();
			if(user!=null && user.getRole().contains("ADMIN")) {
				  consultList = consultantByCompanyRepository.findAll().size();

			}
		}
		return consultList;
	}

	@Override
	public List<Map<String, Object>> companySearchByGstAndContactDetails(String searchNameAndGSt, Long userId ,String fieldSearch) {
		List<Object[]> comp=new ArrayList<>();
		if(fieldSearch.equals("companyName")) {
			 comp = companyRepository.findByNameLike(searchNameAndGSt);
		}else if(fieldSearch.equals("gstNumber")) {
			comp = companyRepository.findByGstNumberLike(searchNameAndGSt);
		}else if(fieldSearch.equals("contactNumber")) {
			comp = companyRepository.findByContactNoLike(searchNameAndGSt);
//			comp =companyRepository.findByIdIn(compIds);
		}else if(fieldSearch.equals("contactEmail")) {
			comp =companyRepository.findByEmailLike(searchNameAndGSt);
//			comp =companyRepository.findByIdIn(compIds);
		}else {
			comp = companyRepository.findByNameOrGSTLike(searchNameAndGSt);

		}
		
		Set<Object[]>compSet = new HashSet<>(comp);
		List<Map<String, Object>> res = new ArrayList<>();
		for (Object[] c : compSet) {
			Map<String, Object> result = new HashMap<>();
			result.put("companyId", c[0]);
			result.put("companyName", c[1]);
			result.put("assigneeId", c[2]);
			result.put("assigneeName", c[3]);

			res.add(result);
		}

		return res;
	
	}

}
