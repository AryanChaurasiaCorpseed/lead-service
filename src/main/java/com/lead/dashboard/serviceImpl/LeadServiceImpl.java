package com.lead.dashboard.serviceImpl;


import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.dto.AddProductInLead;
import com.lead.dashboard.dto.AllLeadFilter;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.DeleteMultiLeadDto;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.dto.UpdateLeadDto;
import com.lead.dashboard.dto.UpdateMultiLeadAssignee;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadHistoryRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.product.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.service.LeadService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


	public Lead createLead(LeadDTO leadDTO) {
		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(leadDTO.getEmail(),leadDTO.getMobileNo());
		Lead lead = new Lead();


		if(leadList!=null && leadList.size()!=0) {
			int size = leadList.size();
			lead.setLeadName("(Duplicate - "+size+" )"+leadDTO.getLeadName());
		}else {
			lead.setLeadName(leadDTO.getLeadName());
		}

		lead.setName(leadDTO.getClientName());
		//		lead.setLeadName(leadDTO.getLeadName());
		lead.setLeadDescription(leadDTO.getLeadDescription());
		lead.setMobileNo(leadDTO.getMobileNo());
		lead.setEmail(leadDTO.getEmail());
		lead.setUrls(leadDTO.getUrls());
		lead.setCreateDate(new Date());		
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
		client.setName(leadDTO.getClientName());
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
					return leadRepository.findAllByIsDeleted(false);
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

	public Map<String,Object> getSingleLeadDataV2(Long leadId) {
		// TODO Auto-generated method stub
		Optional<Lead> opLead = leadRepository.findById(leadId);
		boolean flag=false;
		Map<String,Object>map = new HashMap<>();
		Lead lead = new Lead();
		if(opLead!=null && opLead.get()!=null)
		{
			lead=opLead.get();
			map.put("leadId", lead.getId());
			map.put("leadName", lead.getLeadName());
			map.put("categoryId", lead.getCategoryId());
			map.put("city", lead.getCity());
			map.put("assigne", lead.getAssignee());
			map.put("displayStatus", lead.getDisplayStatus());
			map.put("description", lead.getLeadDescription());
			map.put("email", lead.getEmail());
			map.put("ipAddress", lead.getIpAddress());
			map.put("remarks", lead.getRemarks());
			map.put("status", lead.getStatus());

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

			map.put("clients", listOfMap);

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
		leadHistory.setDescription("'Lead Assignee' has been changed from "+assignee+" to "+user.getFullName());
		leadHistory.setLeadId(lead.getId());

		if(updatedById!=null) {
			User createdBy = userRepo.findById(updatedById).get();
			leadHistory.setCreatedBy(createdBy); 
		}
		leadHistory.setCreateDate(new Date());
		lead.setAssignee(user);
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
				leadList= leadRepository.findAllByAssigneeAndStatusIdInAndIsDeletedAndInBetweenDate(userId,statusIds,flag,startDate,endDate);
			}
		}else {
			if(user.get()!=null &&user.get().getRole().contains("ADMIN")) {
				if(userList!=null &&userList.size()!=0) {
					leadList= leadRepository.findAllByStatusIdInAndAssigneeIdInAndIsDeleted(statusIds,userList,flag);

				}else {
					leadList= leadRepository.findAllByStatusIdInAndIsDeleted(statusIds,flag);

				}

			}else {
				leadList= leadRepository.findAllByAssigneeAndStatusIdInAndIsDeleted(userId,statusIds,flag);
			}
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
				l.setStatus(status);
			}
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
	

}
