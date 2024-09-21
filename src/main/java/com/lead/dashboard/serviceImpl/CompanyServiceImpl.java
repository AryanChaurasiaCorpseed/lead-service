package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.UpdateCompanyDto;
import com.lead.dashboard.repository.CompanyRepository;
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
	LeadRepository leadRepository;

	@Autowired
	UserServiceImpl userServiceImpl;

	@Autowired
	UserRepo userRepo;

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
		if(filterUserId==null||filterUserId==0) {

			if(roleList.contains("ADMIN")) {
				companyList=companyRepository.findAll(pageable).getContent();
			}else {
				if(user.isManager()) {
					List<Long>userList = new ArrayList<>();
					userList.add(userId);
					List<Long> uList = userServiceImpl.getUserManager(userId);
					userList.addAll(uList);
					companyList =companyRepository.findAllByAssigneeIdIn(userList,pageable).getContent();

				}else {

					companyList =companyRepository.findByAssigneeId(userId,pageable).getContent();

				}
			}
		}else {
			if(roleList.contains("ADMIN")) {
				List<Long>userIds = new ArrayList<>();
				userIds.add(filterUserId);
				companyList =companyRepository.findAllByAssigneeIdIn(userIds,pageable).getContent();
			}else {
				if(user.isManager()) {
					List<Long>userList = new ArrayList<>();
					userList.add(userId);
					List<Long> uList = userServiceImpl.getUserManager(userId);
					userList.addAll(uList);
					companyList =companyRepository.findAllByAssigneeIdIn(userList,pageable).getContent();

				}else {

					companyList =companyRepository.findByAssigneeId(userId,pageable).getContent();

				}
			}
		}
		List<Map<String,Object>>res = new ArrayList<>();
		System.out.println("test .. . . ");
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
	public boolean updateCompanyAssignee(Long companyId, Long assigneeId) {
		Company comp = companyRepository.findById(companyId).get();

		User assignee = userRepo.findById(assigneeId).get();
		comp.setAssignee(assignee);
		companyRepository.save(comp);
		return true;
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
					c.setAssignee(assignee);
				    companyRepository.save(c);
				    flag=true;
				}
				
			}
		}
		return flag;
	}


}
