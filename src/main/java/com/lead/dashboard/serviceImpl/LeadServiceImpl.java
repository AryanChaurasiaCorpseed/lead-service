package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.controller.companyController.CompanyFormController;
import com.lead.dashboard.controller.leadController.UpdateLeadOriginal;
import com.lead.dashboard.dto.AddProductInLead;
import com.lead.dashboard.dto.AllLeadFilter;
import com.lead.dashboard.dto.ChildLeadDto;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.DeleteMultiLeadDto;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.dto.UpdateMultiLeadAssignee;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadHistoryRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.product.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.SlugRepository;
import com.lead.dashboard.service.LeadService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LeadServiceImpl implements LeadService  {

	@Autowired
	LeadRepository leadRepository;
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;
	@Autowired
	CommonServices commonServices;

	@Autowired
	CompanyFormController companyFormController;

	@Autowired
	SlugRepository slugRepository;
	@Autowired
	UserRepo userRepo;

	@Autowired
	LeadHistoryRepository leadHistoryRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	UrlsManagmentRepo urlsManagmentRepo;


	public Lead createLead(LeadDTO leadDTO) {
		String email=leadDTO.getEmail();
		if(email!=null && email.equals("NA")) {
			email=null;
		}		
		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());
		Lead lead = new Lead();
		if(leadList!=null && leadList.size()!=0) {
			int size = leadList.size();
			lead.setLeadName("(Duplicate - "+size+" )"+leadDTO.getLeadName());
		}else {
			lead.setLeadName(leadDTO.getLeadName());
		}
		lead.setOriginalName(leadDTO.getLeadName());
		lead.setName(leadDTO.getName());
		//		lead.setLeadName(leadDTO.getLeadName());
		lead.setLeadDescription(leadDTO.getLeadDescription());
		lead.setMobileNo(leadDTO.getMobileNo());
		lead.setEmail(leadDTO.getEmail());
		lead.setUrls(leadDTO.getUrls());
		lead.setAuto(true);
		lead.setIsUrlsChecked(true);
		lead.setCreateDate(new Date());		
		lead.setView(false);
		lead.setLastUpdated(new Date());
		User createdBy=null;
		if(leadDTO.getCreatedById()!=null) {
			createdBy= userRepo.findById(leadDTO.getCreatedById()).get();
			lead.setCreatedBy(createdBy);
		}

		lead.setLatestStatusChangeDate(leadDTO.getLatestStatusChangeDate());
		if(leadDTO.getAssigneeId()!=null) {
			Optional<User> user = userRepo.findById(leadDTO.getAssigneeId());
			lead.setAssignee(user.get());
		}

		Client client = new Client();
		client.setName(leadDTO.getName());
		client.setContactNo(leadDTO.getMobileNo());
		client.setEmails(leadDTO.getEmail());
		client.setServiceDetails(null);
		if(leadDTO.getProductId()!=null) {
			ServiceDetails service = new ServiceDetails();

			Product product = productRepo.findById(leadDTO.getProductId()).get();
			service.setName(product.getProductName());
			service.setProduct(product);
			serviceDetailsRepository.save(service);
			List<ServiceDetails>sList = new ArrayList<>();
			sList.add(service);
			client.setServiceDetails(sList);
		}
		clientRepository.save(client);
		List<Client>cList = new ArrayList<>();
		cList.add(client);
		lead.setClients(cList);
		lead.setSource(leadDTO.getSource());
		lead.setPrimaryAddress(leadDTO.getPrimaryAddress());
		lead.setDeleted(leadDTO.isDeleted());
		Status status = statusRepository.findAllByName("New");
		if(status!=null) {
			lead.setStatus(status);
		}
		lead.setCity(leadDTO.getCity());
		lead.setCategoryId(leadDTO.getCategoryId());
		lead.setServiceId(leadDTO.getServiceId());
		lead.setIndustryId(leadDTO.getIndustryId());
		lead.setIpAddress(leadDTO.getIpAddress());
		lead.setDisplayStatus(leadDTO.getDisplayStatus());
		lead.setWhatsAppStatus(leadDTO.getWhatsAppStatus());
		lead.setUuid(commonServices.getUuid());
		leadRepository.save(lead);
		createLeadHistory(lead,createdBy);
		return lead;
	}
	public Lead createLeadV2(LeadDTO leadDTO) {
		String email=leadDTO.getEmail();
		if(email!=null && email.equals("NA")) {
			email=null;
		}		
		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());
		Lead lead = new Lead();
		System.out.println("ttttt");
		String leadName=leadDTO.getLeadName();
		if(leadName!=null && leadName.equals("NA")) {
			leadName=null;
		}

		//check lead is existing or not
		if(leadList!=null && leadList.size()!=0) {
			//check company
			Long companyId=companyRepository.findCompanyIdByLeadId(leadList.get(0).getId());
			//			System.out.println(companyId);
			if(companyId!=null) {
				// check company status if open
				Company company = companyRepository.findById(companyId).get();
				String companyStatus = company.getStatus();
				List<Project> l = isLeadOpen(company,leadDTO.getLeadName());
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+l);
				if(l.size()==0) {
					System.out.println("bbbbbbbbbbbbbbbbbbbbbb");

					lead=leadCreation(lead,leadDTO);
				}
			}else {
				// means is lead is in bad fit
				//				System.out.println("cccccccccccccccccccccccccccccccccccccccccc");

				lead=leadList.get(0);
				String lStatus =lead.getStatus()!=null?lead.getStatus().getName():"NA";
				if("Bad Fit".equals(lead.getStatus().getName())) {
					//					lead.setBacklogTask(true);
					//					lead.setCount(leadList.size()+1);
					//					lead.setName(leadDTO.getLeadName());
					//					lead.setOriginalName(leadDTO.getLeadName());
					//					Status status = statusRepository.findAllByName("New");
					//					lead.setStatus(status);
					//					lead.setAuto(false);
					//					leadRepository.save(lead);//also create history
					leadDTO.setCount(leadList.size()+1);
					lead=leadCreation(lead,leadDTO);
				}else {
					List<String>status = new ArrayList<>();
					status.add("New");
					status.add("Follow Up");
					status.add("Proposal Sent");
					status.add("Hot Leads");
					status.add("Awaiting Documents");
					status.add("Awaiting Payment");

					if(leadName!=null && leadName.equals(lead.getName())&& (status.contains(lead.getStatus().getName()))) {
						//						System.out.println("TEST . . . . 1  .  "+leadList);
						//						int leadSize = lead.getCount()+1;
						//						lead.setCount(leadSize);
						//						leadRepository.save(lead);//also create history
						lead=leadCreation(lead,leadDTO);



					}else {
						System.out.println("TEST . . . . 2");

						leadDTO.setCount(leadList.size()+1);
						lead=leadCreation(lead,leadDTO);
					}

				}

			}

		}else {   
			System.out.println("ddddddddddddddddddddddddddddddddddddd");

			lead=leadCreation(lead,leadDTO);
		}
		return lead;
	}
	public Lead createLeadV3(LeadDTO leadDTO) {
		String email=leadDTO.getEmail();
		if(email!=null && email.equals("NA")) {
			email=null;
		}		
		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());
		Lead lead = new Lead();
		System.out.println("ttttt.."+leadList.size()+"....."+leadList.get(0).getLeadName());

		//check lead is existing or not
		if(leadList!=null && leadList.size()!=0) {
			//check company
			Long companyId=companyRepository.findCompanyIdByLeadId(leadList.get(0).getId());
			System.out.println(companyId);
			if(companyId!=null) {
				// check company status if open
				Company company = companyRepository.findById(companyId).get();
				String companyStatus = company.getStatus();
				List<Project> l = isLeadOpen(company,leadDTO.getLeadName());
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+l);
				if(l.size()==0) {
					System.out.println("bbbbbbbbbbbbbbbbbbbbbb");

					lead=leadCreation(lead,leadDTO);
				}
			}else {
				// means is lead is in bad fit
				//				System.out.println("cccccccccccccccccccccccccccccccccccccccccc");

				lead=leadList.get(0);
				lead.setBacklogTask(true);
				lead.setName(leadDTO.getLeadName());
				lead.setOriginalName(leadDTO.getLeadName());
				Status status = statusRepository.findAllByName("New");
				lead.setStatus(status);
				lead.setAuto(false);
				leadRepository.save(lead);//also create history

			}

		}else {   
			System.out.println("ddddddddddddddddddddddddddddddddddddd");

			lead=leadCreation(lead,leadDTO);
		}
		return lead;
	}

	public List<Project> isLeadOpen(Company company,String serviceName) {
		List<Project> projectList = company.getCompanyProject();
		List<Lead> leadList = company.getCompanyLead().stream().distinct().collect(Collectors.toList());
		System.out.println("=======================================================");
		System.out.println(leadList.size());
		//		System.out.println(leadList.get(0).getId());
		//		System.out.println(leadList.get(1).getId());

		List<Project> result = new ArrayList<>();
		Map<Long,Lead>leadMap=leadList.stream().collect(Collectors.toMap(i->i.getId(), i->i));
		for(Project p:projectList) {
			if("Open".equalsIgnoreCase(p.getStatus()) && p.getName().equals(serviceName)) {
				result.add(p);
				Lead l=leadMap.get(p.getLead().getId());
				if(l!=null) {
					int count = l.getCount();
					count=count==0?count+2:count++;
					l.setCount(count);
				}
				leadRepository.save(l);
			}
		}
		return result;
	}

	private String extractSlugFromUrl(String url) {
		try {
			return url.substring(url.lastIndexOf('/') + 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Lead leadCreation(Lead lead,LeadDTO leadDTO) {
		//		if(leadList!=null && leadList.size()!=0) {
		//			int size = leadList.size();
		//			lead.setLeadName("(Duplicate - "+size+" )"+leadDTO.getLeadName());
		//		}else {
		//			lead.setLeadName(leadDTO.getLeadName());
		//		}
		
		if ("Missed OTP".equals(leadDTO.getLeadName()) && leadDTO.getUrls() != null) {
			String urls = leadDTO.getUrls();
			String extractedSlug = extractSlugFromUrl(urls);
			if (extractedSlug != null && !extractedSlug.isEmpty()) {
				lead.setLeadName("Missed OTP - " + extractedSlug);
			} else {
				lead.setLeadName(leadDTO.getLeadName()); 
			}
		} else {
			lead.setLeadName(leadDTO.getLeadName());
		}
		lead.setLeadName(leadDTO.getLeadName());
		System.out.println("Aryan12........");
		System.out.println(leadDTO.getSource());
		if("Corpseed Website".equals(leadDTO.getSource())) {
			Long sId=slugRepository.findIdByName(leadDTO.getLeadName());
			System.out.println("sId....."+sId);
			String urlsName=urlsManagmentRepo.findNameBySlugId(sId);
			System.out.println(urlsName+".........................Aryan chaurasia");
			lead.setOriginalName(urlsName);
			System.out.println(urlsName+".........................Aryan ");


		}
		//		lead.setOriginalName(leadDTO.getLeadName());
		lead.setName(leadDTO.getName());
		//		lead.setLeadName(leadDTO.getLeadName());
		lead.setLeadDescription(leadDTO.getLeadDescription());
		lead.setMobileNo(leadDTO.getMobileNo());
		lead.setEmail(leadDTO.getEmail());
		lead.setUrls(leadDTO.getUrls());
		lead.setAuto(true);
		lead.setIsUrlsChecked(true);
		if(leadDTO.getCount()!=0) {
			lead.setCount(leadDTO.getCount());
		}
		lead.setCreateDate(new Date());		
		lead.setView(false);
		lead.setLastUpdated(new Date());
		User createdBy=null;
		System.out.println("Aryan13........");

		if(leadDTO.getCreatedById()!=null) {
			createdBy= userRepo.findById(leadDTO.getCreatedById()).get();
			lead.setCreatedBy(createdBy);
		}

		lead.setLatestStatusChangeDate(leadDTO.getLatestStatusChangeDate());
		if(leadDTO.getAssigneeId()!=null) {
			Optional<User> user = userRepo.findById(leadDTO.getAssigneeId());
			lead.setAssignee(user.get());
		}else {
			if(leadDTO.getCreatedById()!=null) {
				Optional<User> user = userRepo.findById(leadDTO.getCreatedById());
				lead.setAssignee(user.get());
			}else {
				Optional<User> user = userRepo.findById(3l);


				lead.setAssignee(user.get());
			}

		}
		System.out.println("Aryan14........");

		Client client = new Client();
		client.setName(leadDTO.getName());
		client.setContactNo(leadDTO.getMobileNo());
		client.setEmails(leadDTO.getEmail());
		client.setServiceDetails(null);
		if(leadDTO.getProductId()!=null) {
			ServiceDetails service = new ServiceDetails();

			Product product = productRepo.findById(leadDTO.getProductId()).get();
			service.setName(product.getProductName());
			service.setProduct(product);
			serviceDetailsRepository.save(service);
			List<ServiceDetails>sList = new ArrayList<>();
			sList.add(service);
			client.setServiceDetails(sList);
		}         System.out.println("Aryan15........");


		clientRepository.save(client);
		List<Client>cList = new ArrayList<>();
		cList.add(client);
		lead.setClients(cList);
		lead.setSource(leadDTO.getSource());
		lead.setPrimaryAddress(leadDTO.getPrimaryAddress());
		lead.setDeleted(leadDTO.isDeleted());
		Status status = statusRepository.findAllByName("New");
		if(status!=null) {
			lead.setStatus(status);
		}
		System.out.println("Aryan16........");
		lead.setAuto(true);
		lead.setCity(leadDTO.getCity());
		lead.setCategoryId(leadDTO.getCategoryId());
		lead.setServiceId(leadDTO.getServiceId());
		lead.setIndustryId(leadDTO.getIndustryId());
		lead.setIpAddress(leadDTO.getIpAddress());
		lead.setDisplayStatus(leadDTO.getDisplayStatus());
		lead.setWhatsAppStatus(leadDTO.getWhatsAppStatus());
		lead.setUuid(commonServices.getUuid());

		System.out.println("Aryan17........");

		leadRepository.save(lead);
		System.out.println("Aryan18........");
		if(leadDTO.getCompanyId()!=null) {
			Company comp = companyRepository.findById(leadDTO.getCompanyId()).get();
			List<Lead> compLead = comp.getCompanyLead();
			compLead.add(lead);
			companyRepository.save(comp);

		}

		createLeadHistory(lead,createdBy);
		return lead;
	}
	public boolean isLeadValidation(LeadDTO leadDTO,List<Lead>leadList) {
		Long companyId=null;
		Lead leadData =null;
		if(companyId!=null) {
			Optional<Company> company = companyRepository.findById(companyId);
			User assignee = company.get().getAssignee();
		}else {
			boolean isBadFit=false;
			for(Lead lead:leadList) {
				if(lead.getStatus().getName().equals("Bad Fit")) {
					leadData=lead;
					isBadFit=true;
					break;
				}
			}
			if(isBadFit) {
				// assign to quality team  
			}else {

			}
		}
		return true;
	}

	public LeadHistory createLeadHistory(Lead lead,User user) {
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setCreateDate(new Date());
		String createdBy =user!=null?user.getFullName():"Corpseed HO";
		leadHistory.setEventType("Lead has been created");
		leadHistory.setDescription("lead has been created by "+createdBy);
		if(user!=null) {
			leadHistory.setCreatedBy(user); 
		}
		leadHistory.setLeadId(lead.getId());
		leadHistoryRepository.save(leadHistory);
		return leadHistory;
	}


	public List<Lead> getAllActiveCustomerLeadV3(AllLeadFilter allLeadFilter, int page, int size) {
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long uId=allLeadFilter.getUserId();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		Optional<User> user = userRepo.findById(uId);
		Pageable pageable = PageRequest.of(page, size);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			//			String startDate = convertLongToStringDateFormat(toDate);
			//			String endDate = convertLongToStringDateFormat(fromDate);
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					return leadRepository.findAllByIsDeletedAndInBetweenDateAndAssigneeIdIn(false,startDate,endDate,userList, pageable).getContent();

				}else {
					return leadRepository.findAllByIsDeletedAndInBetweenDate(false,startDate,endDate,pageable).getContent();

				}
				//				return leadRepository.findAllByIsDeletedAndInBetweenDate(false,startDate,endDate);
			}else {
				return leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(uId, false,startDate,endDate,pageable).getContent();
			}
		}else {

			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				//				return leadRepository.findAllByIsDeleted(false);
				if(userList!=null &&userList.size()!=0) {
					return leadRepository.findAllByIsDeleted(false,userList,pageable).getContent();
				}else {
					return leadRepository.findAllByStatusIdAndIsDeleted(1l,false, pageable).getContent();
				}
			}else {
				return leadRepository.findAllByAssigneeAndIsDeleted(uId, false, pageable).getContent();
			}


		}

	}
	
	public Integer getAllActiveCustomerLeadCount(AllLeadFilter allLeadFilter) {
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long uId=allLeadFilter.getUserId();
		List<Lead>leadList = new ArrayList<>();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		Optional<User> user = userRepo.findById(uId);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			//			String startDate = convertLongToStringDateFormat(toDate);
			//			String endDate = convertLongToStringDateFormat(fromDate);
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByIsDeletedAndInBetweenDateAndAssigneeIdIn(false,startDate,endDate,userList);
				}else {
					leadList= leadRepository.findAllByIsDeletedAndInBetweenDate(false,startDate,endDate);

				}
				//				return leadRepository.findAllByIsDeletedAndInBetweenDate(false,startDate,endDate);
			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(uId, false,startDate,endDate);
			}
		}else {

			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				//				return leadRepository.findAllByIsDeleted(false);
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByIsDeleted(false,userList);
				}else {
					leadList= leadRepository.findAllByStatusIdAndIsDeleted(1l,false);
				}
			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeleted(uId, false);
			}


		}
		int count=0;
		if(leadList!=null) {
			count=leadList.size();
		}
		return count;

	}

	@Override
	//	public List<Lead> getAllActiveCustomerLead(Long uId,String toDate,String fromDate) {
	public List<Lead> getAllActiveCustomerLead(AllLeadFilter allLeadFilter) {
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long uId=allLeadFilter.getUserId();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		Optional<User> user = userRepo.findById(uId);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			//			String startDate = convertLongToStringDateFormat(toDate);
			//			String endDate = convertLongToStringDateFormat(fromDate);
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					return leadRepository.findAllByIsDeletedAndInBetweenDateAndAssigneeIdIn(false,startDate,endDate,userList);
				}else {
					return leadRepository.findAllByIsDeletedAndInBetweenDate(false,startDate,endDate);

				}
				//				return leadRepository.findAllByIsDeletedAndInBetweenDate(false,startDate,endDate);
			}else {
				return leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(uId, false,startDate,endDate);
			}
		}else {

			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				//				return leadRepository.findAllByIsDeleted(false);
				if(userList!=null &&userList.size()!=0) {
					return leadRepository.findAllByIsDeleted(false,userList);
				}else {
					return leadRepository.findAllByStatusIdAndIsDeleted(1l,false);
				}
			}else {
				return leadRepository.findAllByAssigneeAndIsDeleted(uId, false);
			}


		}

	}
	public String convertLongToStringDateFormat(Long date) {
		Date startDate = new Date(date);
		//		String pattern = "yyyy-MM-dd HH:mm:ss"; //Format of String
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date1 = simpleDateFormat.format(startDate);
		return date1;
	}
	public Lead updateLeadData(UpdateLeadDto updateLeadDto) {
		System.out.println(updateLeadDto.getId());
		//		Optional<Status> statusData = statusRepository.findById(statusId);
		Optional<Lead> lead = leadRepository.findById(updateLeadDto.getId());
		System.out.println(lead);
		if(!lead.isEmpty()&&lead!=null) {
			leadHistory(lead.get(), updateLeadDto);

			Lead leadData = lead.get();
			leadData.setLeadName(updateLeadDto.getLeadName());
			leadData.setEmail(updateLeadDto.getEmail());
			leadData.setMobileNo(updateLeadDto.getMobileNo());
			leadData.setName(updateLeadDto.getName());
			leadData.setLeadDescription(updateLeadDto.getLeadDescription());
			leadData.setUuid(updateLeadDto.getUuid());
			if(updateLeadDto.getAssigneeId()!=null) {
				Optional<User> user = userRepo.findById(updateLeadDto.getAssigneeId());
				leadData.setAssignee(user.get());
			}

			leadData.setLastUpdated(updateLeadDto.getLastUpdated());
			leadData.setCategoryId(updateLeadDto.getCategoryId());
			leadData.setUrls(updateLeadDto.getUrls());
			leadData.setLatestStatusChangeDate(updateLeadDto.getLatestStatusChangeDate());
			Lead updatedoneLead = leadRepository.save(leadData);

			return updatedoneLead;

		}


		return null;
	}

	public void leadHistory(Lead lead,UpdateLeadDto updateLeadDto) {
		User user=null;
		Long userId=updateLeadDto.getUserId();
		if(userId!=null) {
			user = userRepo.findById(userId).get();
		}

		if(!lead.getLeadName().equals(updateLeadDto.getLeadName())) {
			LeadHistory leadHistory= new LeadHistory();
			leadHistory.setEventType("The 'Lead Name' field was modified");
			leadHistory.setDescription("changes in lead name from "+lead.getLeadName()+" -> "+updateLeadDto.getLeadName());
			leadHistory.setLeadId(updateLeadDto.getId());
			leadHistory.setCreatedBy(user); 
			leadHistory.setCreateDate(new Date());
			leadHistoryRepository.save(leadHistory);

		}
		if(!lead.getStatus().getId().equals(updateLeadDto.getStatusId())) {
			LeadHistory leadHistory= new LeadHistory();
			leadHistory.setEventType("Change the field 'Stage'");
			Optional<Status> status = statusRepository.findById(updateLeadDto.getId());
			leadHistory.setDescription(lead.getStatus().getName()+" -> "+status.get()!=null?status.get().getName():"NA");
			leadHistory.setLeadId(updateLeadDto.getId());
			leadHistory.setCreatedBy(user);
			leadHistory.setCreateDate(new Date());
			leadHistoryRepository.save(leadHistory);

		}
		if(!lead.getMobileNo().equals(updateLeadDto.getMobileNo())) {
			LeadHistory leadHistory= new LeadHistory();
			leadHistory.setEventType("the 'Mobile Number'  field was modified");
			leadHistory.setDescription(lead.getMobileNo()+" -> "+updateLeadDto.getMobileNo());
			leadHistory.setLeadId(updateLeadDto.getId());
			leadHistory.setCreatedBy(user);
			leadHistory.setCreateDate(new Date());
			leadHistoryRepository.save(leadHistory);

		}
		if(lead.isDeleted()!=updateLeadDto.isDeleted()) {
			LeadHistory leadHistory= new LeadHistory();
			leadHistory.setEventType("lead was deleted");
			leadHistory.setDescription(lead.getLeadName()+" has been deleted ");
			leadHistory.setLeadId(updateLeadDto.getId());
			leadHistory.setCreatedBy(user);
			leadHistory.setCreateDate(new Date());
			leadHistoryRepository.save(leadHistory);

		}
		if(!lead.getEmail().equals(updateLeadDto.getEmail())) {
			LeadHistory leadHistory= new LeadHistory();
			leadHistory.setEventType("the 'Email'  field was modified");
			leadHistory.setDescription(lead.getEmail()+" -> "+updateLeadDto.getEmail());
			leadHistory.setLeadId(updateLeadDto.getId());
			leadHistory.setCreatedBy(user);
			leadHistory.setCreateDate(new Date());
			leadHistoryRepository.save(leadHistory);

		}
	}


	@Override
	public boolean deleteLead(Long leadId,Long userId) {

		Optional<Lead> opLead = leadRepository.findById(leadId);
		boolean flag=false;
		User user=null;
		if(userId!=null) {
			user = userRepo.findById(userId).get();
		}
		if(opLead!=null && opLead.get()!=null)
		{
			Lead lead = opLead.get();
			LeadHistory leadHistory= new LeadHistory();
			leadHistory.setEventType("lead was deleted");
			leadHistory.setDescription(lead.getLeadName()+" has been deleted ");
			leadHistory.setLeadId(lead.getId());
			leadHistory.setCreatedBy(user); 
			leadHistory.setCreateDate(new Date());

			lead.setDisplayStatus("2");
			lead.setDeleted(true);
			flag=true;
			leadRepository.save(lead);
			leadHistoryRepository.save(leadHistory);

		}
		return flag;
	}



	@Override
	public Lead getSingleLeadData(Long leadId) {
		// TODO Auto-generated method stub
		Optional<Lead> opLead = leadRepository.findById(leadId);
		boolean flag=false;
		Map<String,Object>map = new HashMap<>();
		Lead lead = new Lead();
		if(opLead!=null && opLead.get()!=null)
		{
			lead=opLead.get();
		}
		return lead;
	}

	public Map<String,Object> getSingleLeadDataV2(Long leadId,Long currentUserId) {
		// TODO Auto-generated method stub
		Optional<Lead> opLead = leadRepository.findById(leadId);
		User user = userRepo.findById(currentUserId).get();
		boolean flag=false;
		Map<String,Object>map = new HashMap<>();
		Lead lead = new Lead();
		if(opLead!=null && opLead.get()!=null)
		{   

			if(user.getRole().contains("ADMIN")||currentUserId.equals(opLead.get().getAssignee().getId())) {
				lead=opLead.get();
				map.put("leadId", lead.getId());
				map.put("leadName", lead.getLeadName());
				map.put("originalName", lead.getOriginalName());
				map.put("categoryId", lead.getCategoryId());
				map.put("city", lead.getCity());
				map.put("assigne", lead.getAssignee());
				map.put("displayStatus", lead.getDisplayStatus());
				map.put("description", lead.getLeadDescription());
				map.put("email", lead.getEmail());
				map.put("ipAddress", lead.getIpAddress());
				map.put("remarks", lead.getRemarks());
				map.put("status", lead.getStatus());
				map.put("isBacklog", lead.isBacklogTask());
				map.put("count", lead.getCount());
				map.put("count", lead.getCount());
				map.put("source", lead.getSource());
				map.put("urls", lead.getUrls());

				//			map.put(null, lead.getClients().stream().map(i->i.g).collect(Collectors.toList()));
				List<Client> clientList = lead.getClients();
				List<Map<String,Object>>listOfMap = new ArrayList<>();
				List<ServiceDetails>serviceList = new ArrayList<>();
				for(Client c:clientList) {
					Map<String,Object>clientMap = new HashMap<>();
					clientMap.put("clientId", c.getId());
					clientMap.put("clientName", c.getName());
					clientMap.put("email", c.getEmails());
					clientMap.put("clientCommunication", c.getCommunication().stream().filter(i->i.isDeleted().equals("false")).collect(Collectors.toList()));
					//				clientMap.put("serviceDetails", c.getServiceDetails().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList()));
					List<ServiceDetails> s = c.getServiceDetails().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
					serviceList.addAll(s);
					clientMap.put("contactNo", c.getContactNo());
					listOfMap.add(clientMap);
				}
				map.put("serviceDetails",serviceList);
				
				map.put("childLead",lead.getChildLead());

				map.put("clients", listOfMap);
			}

		}
		return map;
	}

	@Override
	public Lead createEstimate(CreateServiceDetails createservicedetails) {

		ServiceDetails service= new ServiceDetails();
		service.setName(createservicedetails.getName());
		service.setCompany(createservicedetails.getCompany());
		service.setConsultingSale(null); //========= for verification
		service.setContact(createservicedetails.getContact());
		service.setEstimateData(createservicedetails.getEstimateData());
		service.setInvoiceNote(createservicedetails.getInvoiceNote());
		//		service.setOpportunities(null);   //========= for verification
		service.setOrderNumber(createservicedetails.getOrderNumber());
		service.setProductType(createservicedetails.getProductType());
		service.setPurchaseDate(createservicedetails.getPurchaseDate());
		service.setRemarksForOption(createservicedetails.getRemarksForOption());

		service.setGovermentfees(createservicedetails.getGovermentfees());
		service.setGovermentCode(createservicedetails.getGovermentCode());
		service.setGovermentGst(createservicedetails.getGovermentGst());
		service.setProfessionalFees(createservicedetails.getProfessionalFees());
		service.setProfessionalCode(createservicedetails.getProfessionalCode());

		service.setProfesionalGst(createservicedetails.getProfesionalGst());
		service.setServiceCharge(createservicedetails.getServiceCharge());
		service.setServiceCode(createservicedetails.getServiceCode());
		service.setServiceGst(createservicedetails.getServiceGst());
		service.setOtherFees(createservicedetails.getOtherFees());
		service.setOtherCode(createservicedetails.getOtherCode());
		service.setOtherGst(createservicedetails.getOtherGst());

		Lead lead = leadRepository.findById(createservicedetails.getLeadId()).get();
		Company  company = null;
		if(createservicedetails.getCompanyId()!=null) {
			company=companyRepository.findById(createservicedetails.getCompanyId()).get();
		}else {
			company = new Company();
			company.setAddress(createservicedetails.getAddress());
			company.setCity(createservicedetails.getCity());
			company.setCountry(createservicedetails.getCountry());
			company.setGstNo(createservicedetails.getGstNo());
			company.setName(createservicedetails.getCompany());
			company.setPanNo(createservicedetails.getPanNo());
			company.setState(createservicedetails.getState());
			company.setCompanyAge(createservicedetails.getCompanyAge());
			companyRepository.save(company);
		}
		service.setCompanies(company);
		Client c = null;
		List<ServiceDetails> serviceList  = new ArrayList<>();
		Client client=null;
		if(createservicedetails.getClientId()!=null) {
			long cId[] = new long[1];
			cId[0] = createservicedetails.getClientId();

			Optional<Client> opClient = lead.getClients().stream().filter(i->i.getId().equals(cId[0])).findFirst();

			if(opClient!=null) {
				client=opClient.get();
				serviceList = client.getServiceDetails();

				String sName[] = new String[1];
				sName[0]=createservicedetails.getName();

				List<ServiceDetails> checkService = serviceList.stream().filter(i->i.getName().equals(sName[0])).collect(Collectors.toList());
				//check its 
				if(checkService!=null && checkService.size()!=0) {
					ServiceDetails services = checkService.get(0);
					services.setName(createservicedetails.getName());
					services.setCompany(createservicedetails.getCompany());
					services.setConsultingSale(null); //========= for verification
					services.setContact(createservicedetails.getContact());
					services.setEstimateData(createservicedetails.getEstimateData());
					services.setInvoiceNote(createservicedetails.getInvoiceNote());
					//					service.setOpportunities(null);   //========= for verification
					services.setOrderNumber(createservicedetails.getOrderNumber());
					services.setProductType(createservicedetails.getProductType());
					services.setPurchaseDate(createservicedetails.getPurchaseDate());
					services.setRemarksForOption(createservicedetails.getRemarksForOption());
					services.setCompanies(company);

					service.setGovermentfees(createservicedetails.getGovermentfees());
					service.setGovermentCode(createservicedetails.getGovermentCode());
					service.setGovermentGst(createservicedetails.getGovermentGst());
					service.setProfessionalFees(createservicedetails.getProfessionalFees());
					service.setProfessionalCode(createservicedetails.getProfessionalCode());

					service.setProfesionalGst(createservicedetails.getProfesionalGst());
					service.setServiceCharge(createservicedetails.getServiceCharge());
					service.setServiceCode(createservicedetails.getServiceCode());
					service.setServiceGst(createservicedetails.getServiceGst());
					service.setOtherFees(createservicedetails.getOtherFees());
					service.setOtherCode(createservicedetails.getOtherCode());
					service.setOtherGst(createservicedetails.getOtherGst());

					ServiceDetails serviceDetails = serviceDetailsRepository.save(services);
					//					serviceList.add(serviceDetails);
					//					client.setServiceDetails(serviceList);
					//					 c=clientRepository.save(client);

				}else {
					System.out.println("eeeeeeeeeeeeeeee");

					ServiceDetails serviceDetails = serviceDetailsRepository.save(service);
					serviceList.add(serviceDetails);
					client.setServiceDetails(serviceList);
					//					 c=clientRepository.save(client);

				}
				//				client.setServiceDetails(serviceList);
				//				 c=clientRepository.save(client);

			}else {
				System.out.println("ffffffffffffffffff");

				client = new Client();
				client.setName(createservicedetails.getClientName());
				client.setEmails(createservicedetails.getEmail());
				client.setContactNo(createservicedetails.getContactNo());
				client.setDeleteStatus(false);
				List<ServiceDetails> sList  = new ArrayList<>();
				ServiceDetails serviceDetails = serviceDetailsRepository.save(service);

				sList.add(serviceDetails);
				serviceList=sList;
				client.setServiceDetails(serviceList);  
				//				 c=clientRepository.save(client);
			}
		}else {
			System.out.println("hhhhhhhhhhhhhhhh");

			client = new Client();
			client.setName(createservicedetails.getClientName());
			client.setEmails(createservicedetails.getEmail());
			client.setContactNo(createservicedetails.getContactNo());
			client.setDeleteStatus(false);
			List<ServiceDetails> sList  = new ArrayList<>();
			ServiceDetails serviceDetails = serviceDetailsRepository.save(service);

			sList.add(serviceDetails);
			serviceList=sList;
			client.setServiceDetails(serviceList); 
			c=clientRepository.save(client);
		}
		if(lead.getClients()!=null && lead.getClients().size()!=0) {
			lead.getClients().add(c);
			leadRepository.save(lead);
		}
		//		 sendEmail(String subject,String text, Context context,String templateName) {
		sendEstimateMail(createservicedetails,lead, "this mail for estimate");
		return lead;


	}

	private void sendEstimateMail(CreateServiceDetails createservicedetails,Lead lead,String subject) {
		//		List<String> emailList = lead.getClients().stream().map(i->i.getEmails()).collect(Collectors.toList());
		//		String[] emailTo= (String[]) emailList.toArray();
		String[] emailTo= {"aryan.chaurasia@corpseed.com"};


		String[] ccPersons= {"aryan.chaurasia@corpseed.com"};
		String[] bccPersons= {"aryan.chaurasia@corpseed.com"};

		//		String[] ccPersons=(String[]) createservicedetails.getCc().toArray();
		//		String[] bccPersons=(String[]) createservicedetails.getCc().toArray();

		Context context = new Context();
		context.setVariable("urls", "http://corpseed.com");
		mailSendSerivceImpl.sendEmail(emailTo, ccPersons, bccPersons, subject, "for testing Text", context, "sendEstimateMail.html");
		//	    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject,String text, Context context,String templateName) {

	}

	@Override
	public Lead updateAssignee(Long leadId, Long userId, Long updatedById) {
		// TODO Auto-generated method stub
		User user = userRepo.findById(userId).get();

		Lead lead = leadRepository.findById(leadId).get();
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("lead assignee change");
		String assignee = lead.getAssignee()!=null?lead.getAssignee().getFullName():"NA";
		leadHistory.setPrevUser(lead.getAssignee());
		leadHistory.setDescription("'Lead Assignee' has been changed from "+assignee+" to "+user.getFullName());
		leadHistory.setLeadId(lead.getId());

		if(updatedById!=null) {
			User createdBy = userRepo.findById(updatedById).get();
			leadHistory.setCreatedBy(createdBy); 
		}
		leadHistory.setCreateDate(new Date());
		lead.setAssignee(user);
		lead.setView(false);
		leadHistoryRepository.save(leadHistory);
		leadRepository.save(lead);
		leadHistoryRepository.save(leadHistory);
		return lead;
	}
	//	public LeadHistory createViewHistory(Lead lead,User user) {
	//		LeadHistory leadHistory= new LeadHistory();
	//		leadHistory.setCreateDate(new Date());
	//		leadHistory.setEventType("View");
	//		leadHistory.setDescription("View");
	//		leadHistory.setCreatedBy(user); 
	//		leadHistory.setLeadId(lead.getId());
	//		leadHistoryRepository.save(leadHistory);
	//
	//		return leadHistory;
	//	}


	@Override
	public Lead createProductInLead(AddProductInLead addProductInLead) throws Exception  {
		Product product = productRepo.findById(addProductInLead.getProductId()).get();
		Lead lead = leadRepository.findById(addProductInLead.getLeadId()).get();
		List<Client> clientList = lead.getClients();
		Client client = clientList.stream().findFirst().get();
		List<ServiceDetails> serviceList = client.getServiceDetails().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());

		long isPrsent = serviceList.stream().filter(i->i.getName().equals(product.getProductName())).count();
		System.out.println("isPrsent value .. "+isPrsent);
		if(isPrsent!=0) {
			throw new Exception("Product already Exist ..!");

		}else {
			ServiceDetails serviceDetails = new ServiceDetails();
			serviceDetails.setProduct(product);
			serviceDetails.setName(product.getProductName());
			serviceDetails.setServiceName(addProductInLead.getServiceName());
			serviceDetails.setDeleted(false);
			ServiceDetails service = serviceDetailsRepository.save(serviceDetails);
			serviceList.add(service);
			client.setServiceDetails(serviceList);
			clientRepository.save(client);
		}
		return lead;
	}

	@Override
	public Lead updateLeadName(String newLeadName, Long leadId,Long userId) {
		Lead lead = leadRepository.findById(leadId).get();

		String name=lead.getLeadName();
		lead.setLeadName(newLeadName);
		lead.setLastUpdated(new Date());
		Long slugId = slugRepository.findIdByName(newLeadName);
		String urlsName=urlsManagmentRepo.findNameBySlugId(slugId);
		if(urlsName!=null) {
			lead.setQualityWorked(true);
			lead.setOriginalName(urlsName);
		}else {
			lead.setOriginalName("NA");

		}
		leadRepository.save(lead);
		leadHistory(name,newLeadName,lead,userId);

		return lead;
	}


	public LeadHistory leadHistory(String name,String updateName,Lead lead,Long userId) {
		LeadHistory leadHistory= new LeadHistory();
		if(!name.equals(updateName)) {
			leadHistory.setCreateDate(new Date());
			leadHistory.setEventType("The 'Lead Name' field was modified");
			leadHistory.setDescription("changes in Lead Name from "+name+" -> "+updateName);
			if(userId!=null) {
				Optional<User> user = userRepo.findById(userId);
				leadHistory.setCreatedBy(user.get()); 
			}

		}

		leadHistory.setLeadId(lead.getId());

		leadHistoryRepository.save(leadHistory);

		return leadHistory;
	}


	@Override
	public boolean deleteProductInLead(Long leadId,Long serviceId,Long userId) {
		boolean flag =false;
		Optional<ServiceDetails> sList = serviceDetailsRepository.findById(serviceId);
		ServiceDetails service = sList.get();
		service.setDeleted(true);
		serviceDetailsRepository.save(service);
		flag=true;
		return flag;
	}

	@Override
	public List<ServiceDetails> getAllEstimate() {
		List<ServiceDetails>estimates=serviceDetailsRepository.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		return estimates;
	}

	@Override
	public ServiceDetails getEstimate(Long estimateId) {
		ServiceDetails service=serviceDetailsRepository.findById(estimateId).get();
		return service;
	}



	public List<Lead> getAllLeadV3(AllLeadFilter allLeadFilter ,int page, int size) {

		//		boolean flag=type.equalsIgnoreCase("inActive")?true:false;
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long userId=allLeadFilter.getUserId();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		List<Long>statusIds=allLeadFilter.getStatusId();
		boolean flag =false;
		List<Lead>leadList = new ArrayList<>();
		Optional<User> user = userRepo.findById(userId);
		Pageable pageable = PageRequest.of(page, size);
		//		Page<CompanyForm> comp = companyFormRepo.findAllByStatusAndassigneeId(status,userId,pageable);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {

				//				leadList= leadRepository.findAllByStatusAndIsDeletedAndInBetweenDate(statusId,flag,startDate,endDate);
				if(userList!=null &&userList.size()!=0) {

					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(statusIds,flag,startDate,endDate,userList,pageable).getContent();

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDate(statusIds,flag,startDate,endDate,pageable).getContent();

				}
			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(userId,flag,startDate,endDate,pageable).getContent();
			}
		}else {
			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndAssigneeIdInAndIsDeleted(statusIds,userList,flag,pageable).getContent();
				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeleted(statusIds,flag,pageable).getContent();
				}

			}else {

				leadList= leadRepository.findAllByAssigneeAndStatusIdInAndIsDeleted(userId,statusIds,flag,pageable).getContent();
			}
		}

		return leadList;

	}
	
	public Integer getAllLeadCount(AllLeadFilter allLeadFilter) {

		//		boolean flag=type.equalsIgnoreCase("inActive")?true:false;
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long userId=allLeadFilter.getUserId();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		List<Long>statusIds=allLeadFilter.getStatusId();
		boolean flag =false;
		List<Lead>leadList = new ArrayList<>();
		Optional<User> user = userRepo.findById(userId);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {

				//				leadList= leadRepository.findAllByStatusAndIsDeletedAndInBetweenDate(statusId,flag,startDate,endDate);
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(statusIds,flag,startDate,endDate,userList);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDate(statusIds,flag,startDate,endDate);

				}
			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(userId,flag,startDate,endDate);
			}
		}else {
			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndAssigneeIdInAndIsDeleted(statusIds,userList,flag);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeleted(statusIds,flag);

				}

			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeleted(userId,flag);
			}
		}

		return leadList.size();

	}

	@Override
	//	public List<Lead> getAllLead(Long userId, Long statusId,String toDate,String fromDate) {
	public List<Lead> getAllLead(AllLeadFilter allLeadFilter) {

		//		boolean flag=type.equalsIgnoreCase("inActive")?true:false;
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long userId=allLeadFilter.getUserId();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		List<Long>statusIds=allLeadFilter.getStatusId();
		boolean flag =false;
		List<Lead>leadList = new ArrayList<>();
		Optional<User> user = userRepo.findById(userId);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {

				//				leadList= leadRepository.findAllByStatusAndIsDeletedAndInBetweenDate(statusId,flag,startDate,endDate);
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(statusIds,flag,startDate,endDate,userList);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDate(statusIds,flag,startDate,endDate);

				}
			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(userId,flag,startDate,endDate);
			}
		}else {
			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndAssigneeIdInAndIsDeleted(statusIds,userList,flag);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeleted(statusIds,flag);

				}

			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeleted(userId,flag);
			}
		}

		return leadList;

	}
	public List<Lead> getAllLeadV2(AllLeadFilter allLeadFilter) {

		//		boolean flag=type.equalsIgnoreCase("inActive")?true:false;
		String toDate=allLeadFilter.getToDate();
		String fromDate=allLeadFilter.getFromDate();
		Long userId=allLeadFilter.getUserId();
		List<Long>userList=allLeadFilter.getUserIdFilter();
		List<Long>statusIds=allLeadFilter.getStatusId();
		boolean flag =false;
		List<Lead>leadList = new ArrayList<>();
		Optional<User> user = userRepo.findById(userId);
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;
			System.out.println(startDate+"  - - - - - ---- - - - - - "+endDate);
			if(user.get()!=null && user.get().getRole().contains("ADMIN")) {

				//				leadList= leadRepository.findAllByStatusAndIsDeletedAndInBetweenDate(statusId,flag,startDate,endDate);
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDateAndAssigneeIdIn(statusIds,flag,startDate,endDate,userList);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeletedAndInBetweenDate(statusIds,flag,startDate,endDate);

				}
			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeletedAndInBetweenDate(userId,flag,startDate,endDate);
			}
		}else {
			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndAssigneeIdInAndIsDeleted(statusIds,userList,flag);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeleted(statusIds,flag);

				}

			}else {
				leadList= leadRepository.findAllByAssigneeAndIsDeleted(userId,flag);
			}
		}
		List<Map<String,Object>>result = new ArrayList<>();

		for(Lead l:leadList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", l.getId());
			map.put("leadName", l.getLeadName());
			map.put("clientName", l.getName());
			map.put("clientEmail", l.getEmail());
			map.put("mobileNo", l.getMobileNo());
			map.put("originalName", l.getOriginalName());
			map.put("status", l.getStatus());
			map.put("createdBy", l.getCreatedBy());
			map.put("missedTaskName", l.getMissedTaskName());
			map.put("missedTaskStatus", l.getMissedTaskStatus());
			map.put("missedTaskCreatedBy", l.getMissedTaskCretedBy());
			map.put("missedTaskDate", l.getMissedTaskDate());


		}
		return leadList;

	}
	@Override
	public List<Lead> getAllDeleteLead(Long uId) {

		Optional<User> user = userRepo.findById(uId);
		if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
			return leadRepository.findAllByIsDeleted(true);
		}else {
			return leadRepository.findAllByAssigneeAndIsDeleted(uId, true);
		}

	}

	@Override
	public Boolean viewHistory(Long userId, Long leadId) {
		Boolean flag=false;
		Lead lead = leadRepository.findById(leadId).get();
		User user = userRepo.findById(userId).get();
		LeadHistory leadHisrtory = createViewHistory(lead,user);
		lead.setView(true);
		leadRepository.save(lead);
		if(leadHisrtory!=null) {
			flag=true;
		}
		return flag;
	}

	public LeadHistory createViewHistory(Lead lead,User user) {
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setEventType("View");
		leadHistory.setDescription("View");
		leadHistory.setCreatedBy(user); 
		leadHistory.setLeadId(lead.getId());
		leadHistoryRepository.save(leadHistory);

		return leadHistory;
	}

	@Override
	public Boolean updateMultiLeadAssigne(UpdateMultiLeadAssignee updateMultiLeadAssignee) {
		Boolean flag=false;
		List<Status>filterStatus=statusRepository.findAllByEnableAutoAssign(true);
		Status status = null;
		if(updateMultiLeadAssignee.getStatusId()!=null) {
			status=statusRepository.findById(updateMultiLeadAssignee.getStatusId()).get();
		}
		User assigne=null;
		if(updateMultiLeadAssignee.getAssigneId()!=null && updateMultiLeadAssignee.getAssigneId()!=0) {
			assigne = userRepo.findById(updateMultiLeadAssignee.getAssigneId()).get();
		}
		User updatedBy=null;
		if(updateMultiLeadAssignee.getUpdatedById()!=null) {
			updatedBy = userRepo.findById(updateMultiLeadAssignee.getUpdatedById()).get();
		}
		List<Lead> leadList = leadRepository.findAllById(updateMultiLeadAssignee.getLeadIds());
		for(Lead l:leadList) {
			User prevAssignee = l.getAssignee();
			Status prevStatus=l.getStatus();
			if(assigne!=null) {
				l.setAssignee(assigne);
			}
			if(updateMultiLeadAssignee.getStatusId()!=null && status!=null) {
				if(filterStatus!=null && filterStatus.contains(status)) {
					l.setAuto(true);
				}
				l.setStatus(status);
			}
			l.setView(false);
			leadRepository.save(l);
			if(updateMultiLeadAssignee.getAssigneId()!=null && assigne!=null&&updateMultiLeadAssignee.getAssigneId()!=0) {
				multiLeadAssigneeHistory(l.getId(),prevAssignee,assigne,updatedBy);
				System.out.println("In Lead");
			}
			if(updateMultiLeadAssignee.getStatusId()!=null && status!=null) {
				multiLeadStatusHistory(l.getId(),prevStatus,status,updatedBy);			
			}
			flag=true;
		}
		return flag;
	}

	public Boolean multiLeadAssigneeHistory(Long leadId,User prevAssignee,User assigne,User updatedBy) {
		Boolean flag=false;
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("lead assignee change");
		String pAssignee = prevAssignee!=null?prevAssignee.getFullName():"NA";
		String currAssignee = assigne!=null?assigne.getFullName():"NA";
		leadHistory.setPrevUser(prevAssignee);
		leadHistory.setDescription("'Lead Assignee' has been changed from "+pAssignee+" to "+currAssignee);
		leadHistory.setLeadId(leadId);

		if(updatedBy!=null) {
			leadHistory.setCreatedBy(updatedBy); 
		}
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
		flag=true;
		return flag;
	}

	public Boolean multiLeadStatusHistory(Long leadId,Status prevStatus,Status status,User updatedBy) {
		Boolean flag=false;
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("Change the field 'Stage'");
		String pStatus = prevStatus!=null?prevStatus.getName():"NA";
		String cStatus = status!=null?status.getName():"NA";
		leadHistory.setDescription(pStatus+" -> "+cStatus);
		leadHistory.setLeadId(leadId);
		leadHistory.setCreatedBy(updatedBy);
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
		flag=true;
		return flag;
	}

	@Override
	public Boolean deleteMultiLead(DeleteMultiLeadDto deleteMultiLeadDto) {
		Boolean flag=false;   
		List<Lead>leadList=leadRepository.findAllByIsDeletedAndIdIn(deleteMultiLeadDto.getLeadId(), false);
		for(Lead lead:leadList) {
			lead.setDeleted(true);
			leadRepository.save(lead);
			flag=true;
		}
		return flag;
	}

	@Override
	public Boolean updateStatusAndAutoSame(Long leadId, Long updatedById, boolean isNotAutoSame, String statusName) {
		Boolean flag=false;
		Optional<Lead> leadOp = leadRepository.findById(leadId);
		Status status = statusRepository.findByName(statusName);
		User u = userRepo.findById(updatedById).get();
		//		Status status = statusRepository.findById(statusId).get();
		if(leadOp!=null && leadOp.get()!=null) {
			Lead lead = leadOp.get();
			Status prevStatus = lead.getStatus();
			lead.setStatus(status);
			lead.setNotAssignSame(isNotAutoSame);
			lead.setAuto(true);
			leadRepository.save(lead );
			Status currStatus = lead.getStatus();
			multiLeadStatusHistoryAuto(leadId, prevStatus,currStatus, u,isNotAutoSame);

			flag=true;

		}

		return flag;
	}


	public Boolean multiLeadStatusHistoryAuto(Long leadId,Status prevStatus,Status status,User updatedBy,Boolean isNotAutoSame) {
		Boolean flag=false;
		LeadHistory leadHistory= new LeadHistory();
		leadHistory.setEventType("Change the field 'Stage' and auto is "+isNotAutoSame);
		String pStatus = prevStatus!=null?prevStatus.getName():"NA";
		String cStatus = status!=null?status.getName():"NA";
		leadHistory.setDescription(pStatus+" -> "+cStatus);
		leadHistory.setLeadId(leadId);
		leadHistory.setCreatedBy(updatedBy);
		leadHistory.setCreateDate(new Date());
		leadHistoryRepository.save(leadHistory);
		flag=true;
		return flag;
	}


	public Lead assignBadFitToQuality(Lead lead) {
		List<User> qualityList = userRepo.findAllByIsDeletedAndIsQuality(true);
		qualityList.stream().sorted(Comparator.comparing(User::getLockerSize).reversed()).collect(Collectors.toList());
		Optional<User> u = qualityList.stream().findFirst();
		if(u!=null && u.isPresent()){
			User user=u.get();
			lead.setAssignee(user);
			leadRepository.save(lead);
		}
		return lead;
	}
	@Override
	public Boolean updateLeadOriginalName(UpdateLeadOriginal updateLeadOriginal) {
		Boolean b=false;
		User user = userRepo.findById(updateLeadOriginal.getCurrentUserId()).get();
		Lead lead = leadRepository.findById(updateLeadOriginal.getLeadId()).get();
		if(user.getRole().contains("ADMIN")) {
			lead.setOriginalName(updateLeadOriginal.getOriginalName());	
			b=true;
		}else{
			if(lead.getSource().equals("IVR")) {
				if(lead.getOriginalName()==null) {
					lead.setOriginalName(updateLeadOriginal.getOriginalName());
					lead.setLeadName(updateLeadOriginal.getOriginalName());
					lead.setQualityWorked(true);
					b=true;
				}
			}
		}
		leadRepository.save(lead);
		return b;
	}
	@Override
	public List<Map<String, Object>> getAllLeadNameAndId() {
		List<Lead>lead=leadRepository.findAllByIsDeleted(false);
		List<Map<String,Object>>result = new ArrayList<>();
		for(Lead l:lead) {
			Map<String,Object>map = new HashMap<>();
			map.put("value",l.getId());
			map.put("label",l.getLeadName());
			result.add(map);

		}
		return result;
	}
	@Override
	public Boolean updateHelper(Long userId, Long leadId) {
		Lead lead = leadRepository.findById(leadId).get();
		Boolean flag=false;
		if(userId!=null) {
			User user = userRepo.findById(userId).get();
			lead.setHelpUser(user);
			lead.setHelper(true);
			flag=true;
		}else {
			lead.setHelpUser(null);
			lead.setHelper(false);
			flag=true;
		}
		leadRepository.save(lead);
		return flag;
	}
	@Override
	public Boolean leadAssignSamePerson(Long leadId) {
		Boolean flag=false;
		Company company = companyFormController.checkCompanyExistv2(leadId);
		if(company!=null) {
			User assignee = company.getAssignee();
			Lead lead = leadRepository.findById(leadId).get();
			lead.setAssignee(assignee);
			leadRepository.save(lead);
			flag=true;
		}

		return flag;
	}

	//	@Scheduled(cron = "0 * * ? * *", zone = "IST")
	public void createIvrLead(Long leadId) {


	}

	//Only For Bad Fit data
	public Lead createBadFitLead(LeadDTO leadDTO) {
		if (leadDTO == null) {
			throw new IllegalArgumentException("LeadDTO cannot be null");
		}

		if (leadDTO.getName() == null || leadDTO.getLeadName() == null || leadDTO.getMobileNo() == null) {
			throw new IllegalArgumentException("Required fields are missing in LeadDTO");
		}

		String email = leadDTO.getEmail();
		if (email != null && email.equals("NA")) {
			email = null;
		}

		List<Lead> leadList = leadRepository.findAllByEmailAndMobile(email, leadDTO.getMobileNo());
		if (leadList != null && !leadList.isEmpty()) {
			int size = leadList.size();
			leadDTO.setLeadName("(Duplicate - " + size + " )" + leadDTO.getLeadName());
		}

		Lead lead = new Lead();
		lead.setOriginalName(leadDTO.getLeadName());
		lead.setName(leadDTO.getName());
		lead.setLeadName(leadDTO.getLeadName());
		lead.setLeadDescription("");
		lead.setMobileNo(leadDTO.getMobileNo());
		lead.setEmail(email);
		lead.setUrls("");
		lead.setAuto(false);
		lead.setIsUrlsChecked(true);
		lead.setCreateDate(new Date()); //
		lead.setView(false);
		lead.setLastUpdated(new Date());
		lead.setLatestStatusChangeDate(leadDTO.getCreateDate());
		lead.setCity("");
		lead.setSource("Corpseed Website");
		lead.setCategoryId("");
		lead.setServiceId("");
		lead.setIndustryId("");
		lead.setIpAddress("");
		lead.setDisplayStatus("1");
		lead.setWhatsAppStatus(0);
		lead.setUuid(commonServices.getUuid());
		//		lead.setAssignee();

		Status status = statusRepository.findByStatusName("Bad fit");
		if (status == null) {
			throw new IllegalStateException("Status Bad fit not found in the database");
		}
		lead.setStatus(status);

		return leadRepository.save(lead);
	}
	public Lead createLeadViaSheet(LeadDTO leadDTO) {
		if (leadDTO == null) {
			throw new IllegalArgumentException("LeadDTO cannot be null");
		}

		if (leadDTO.getName() == null || leadDTO.getLeadName() == null || leadDTO.getMobileNo() == null) {
			throw new IllegalArgumentException("Required fields are missing in LeadDTO");
		}

		String email = leadDTO.getEmail();
		if (email != null && email.equals("NA")) {
			email = null;
		}

		List<Lead> leadList = leadRepository.findAllByEmailAndMobile(email, leadDTO.getMobileNo());
		if (leadList != null && !leadList.isEmpty()) {
			int size = leadList.size();
			leadDTO.setLeadName("(Duplicate - " + size + " )" + leadDTO.getLeadName());
		}

		Lead lead = new Lead();
		lead.setOriginalName(leadDTO.getLeadName());
		lead.setName(leadDTO.getName());
		lead.setLeadName(leadDTO.getLeadName());
		lead.setLeadDescription(leadDTO.getLeadDescription());
		lead.setMobileNo(leadDTO.getMobileNo());
		lead.setEmail(email);
		lead.setUrls(leadDTO.getUrls());
		lead.setAuto(true);
		lead.setIsUrlsChecked(true);
		lead.setCreateDate(new Date());
		lead.setView(false);
		lead.setLastUpdated(new Date());
		lead.setLatestStatusChangeDate(leadDTO.getLatestStatusChangeDate());
		lead.setCity(leadDTO.getCity());
		lead.setSource("Corpseed Website");
		lead.setCategoryId(leadDTO.getCategoryId());
		lead.setServiceId(leadDTO.getServiceId());
		lead.setIndustryId(leadDTO.getIndustryId());
		lead.setIpAddress(leadDTO.getIpAddress());
		lead.setDisplayStatus(leadDTO.getDisplayStatus());
		lead.setWhatsAppStatus(leadDTO.getWhatsAppStatus());
		lead.setUuid(commonServices.getUuid());

		Status status = statusRepository.findByStatusName("New");
		System.out.println("Status "+ status);
		if (status == null) {
			throw new IllegalStateException("Status 'New' not found in the database");
		}
		lead.setStatus(status);

		// Save the lead
		return leadRepository.save(lead);
	}
	@Override
	public Boolean updateLeadDescription(Long leadId, String desc) {
		boolean flag = false;
		Lead lead = leadRepository.findById(leadId).get();
		lead.setLeadDescription(desc);
		leadRepository.save(lead);
		flag=true;
		return flag;
	}


	public List<Lead> searchLeads(String searchParam, Long userId) {
		User user = userRepo.findByUserIdAndIsDeletedFalse(userId);

		if (user != null) {

			if (user.getRole().contains("ADMIN")) {
				if (isNumeric(searchParam)) {
					searchParam = searchParam.replaceAll("[^\\d]", "");
				 return leadRepository.findAllByMobileNo(searchParam);
				} else if (isEmail(searchParam)) {
					return leadRepository.findAllByEmail(searchParam);
				} else {
					return new ArrayList<>();
				}
			}

			else {
				if (isNumeric(searchParam)) {
					searchParam = searchParam.replaceAll("[^\\d]", "");

					return leadRepository.findAllByMobileNoAndAssignee(searchParam, userId);

				} else if (isEmail(searchParam)) {

					return leadRepository.findAllByEmailAndAssignee(searchParam, userId);
				} else {
					return new ArrayList<>();
				}
			}

		}

		return new ArrayList<>();
	}

	private boolean isNumeric(String str) {
		return str != null && str.matches("\\d+");
	}

	private boolean isEmail(String str) {
		return str != null && str.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
	}

	private List<Lead> filterLead(List<String>statusList,List<Lead>leadList) {
		List<Lead>lList = new ArrayList<>();
		for(Lead l:leadList) {
			if(statusList.contains(l.getStatus().getName())) {
				lList.add(l);
			}
		}
		return lList;
	}

	public Lead createLeadV2New(LeadDTO leadDTO) {
		String email=leadDTO.getEmail();
		if(email!=null && email.equals("NA")) {
			email=null;
		}		
		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());
		System.out.println();
		if(leadDTO.getName().equals("IVR Call")) {
			leadDTO.setSource("IVR");
		}
		Lead lead = new Lead();
		String leadName=leadDTO.getLeadName();
		if(leadName!=null && leadName.equals("NA")) {
			leadName=null;
		}
		
		//check lead is existing or not
		if(leadList!=null && leadList.size()!=0) {
			//check company
			Long companyId=companyRepository.findCompanyIdByLeadId(leadList.get(0).getId());
			//			System.out.println(companyId);

			if(companyId!=null) {
				// check company status if open
				Company company = companyRepository.findById(companyId).get();
				String companyStatus = company.getStatus();
				List<Project> l = isLeadOpen(company,leadDTO.getLeadName());
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+l);
				if(l.size()==0) {

					lead=leadCreation(lead,leadDTO);
				}
			}else {

				// means is lead is in bad fit
				//				System.out.println("cccccccccccccccccccccccccccccccccccccccccc");

				//				lead=leadList.stre
				List<Lead> leadLists = leadList.stream().filter(i->i.getStatus().getName().equals("Bad Fit")).collect(Collectors.toList());

				if(leadLists.size()>0) {

					Lead l = leadLists.get(0);
					l.setBacklogTask(true);
					l.setCount(leadList.size()+1);
					l.setName(leadDTO.getLeadName());
					l.setOriginalName(leadDTO.getLeadName());
					Status status = statusRepository.findAllByName("New");
					l.setStatus(status);
					l.setAuto(false);
					leadRepository.save(l); //also create history
				}else {

					List<String>status = new ArrayList<>();
					status.add("New");
					status.add("Follow Up");
					status.add("Proposal Sent");
					status.add("Hot Leads");
					status.add("Awaiting Documents");
					status.add("Awaiting Payment");

					//					List<Lead> lList = leadList.stream().filter(i->(!(i.getStatus().getName().equals("Bad Fit")))).collect(Collectors.toList());
					List<Lead> lList=filterLead(status,leadList);
					if(lList.size()>0) {

						Lead l = lList.get(lList.size()-1);
						System.out.println("current name "+l.getName());
						System.out.println("previous name "+leadName);

						if(leadName!=null && leadName.equals(l.getLeadName())) {

							int actualCount=l.getCount()+1;
							l.setCount(actualCount);
							leadRepository.save(l);

						}else {

							if(leadDTO.getSource().equals("IVR")) {
								int actualCount=l.getCount()+1;
								l.setCount(actualCount);
								leadRepository.save(l);

							}else{
								String prevSource = l.getSource();
								String currSource = leadDTO.getSource();
								if("IVR".equals(prevSource) && "Corpseed Website".equals(currSource) &&(!l.isQualityWorked())) {
									int actualCount=l.getCount()+1;
									l.setCount(actualCount);
									l.setOriginalName(leadDTO.getLeadName());
									leadRepository.save(l);
								}else {
									lead=leadCreation(lead,leadDTO);
								}
							}
						}
					}else {
						lead=leadCreation(lead,leadDTO);
					}


				}

			}

		}else {   

			lead=leadCreation(lead,leadDTO);
		}
		return lead;
	}
	@Override
	public Boolean addChildLead(ChildLeadDto childLeadDto) {
		Boolean flag=false;
		Lead lead = leadRepository.findById(childLeadDto.getLeadId()).get();
		List<String> serviceList = childLeadDto.getServiceName();
		List<Lead>leadList = new ArrayList<>();
		for(String s:serviceList) {
			Lead l=new Lead();
			l.setLeadName(s);
			l.setAuto(false);
			l.setAssignee(lead.getAssignee());
			l.setOriginalName(s);
			Long slugId = slugRepository.findIdByName(s);
			String urlsName=urlsManagmentRepo.findNameBySlugId(slugId);
			if(urlsName!=null) {
				lead.setQualityWorked(true);
				lead.setOriginalName(urlsName);
			}else {
				lead.setOriginalName("NA");

			}
//			l.setClients(lead.getClients());
			leadRepository.save(l);
			leadList.add(l);
		}
		List<Lead> lList = lead.getChildLead();
		lList.addAll(leadList);
		lead.setChildLead(lList);
		leadRepository.save(lead);
		flag=true;
		return flag;
	}

}
