package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.print.DocFlavor.SERVICE_FORMATTED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.ConsultantByCompany;
import com.lead.dashboard.domain.Contact;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.product.Product;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.EditEstimate;
import com.lead.dashboard.dto.EditEstimateAddress;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.ConsultantByCompanyRepository;
import com.lead.dashboard.repository.ContactRepo;
import com.lead.dashboard.repository.LeadHistoryRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ServiceDetailsRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.service.EstimateService;
@Service
public class EstimateServiceImpl implements EstimateService
{

	@Autowired
	ProductRepo productRepo;
	@Autowired
	LeadRepository leadRepository;
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	@Autowired
	ContactRepo contactRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;

	@Autowired
	ConsultantByCompanyRepository consultantByCompanyRepository;

	@Autowired
	ClientRepository clientRepository;

	//	@Override
	//	public Lead createEstimateV2(CreateServiceDetails createservicedetails) {
	//		
	//		ServiceDetails service= new ServiceDetails();
	//		service.setName(createservicedetails.getName());
	//		service.setCompany(createservicedetails.getCompany());
	//		service.setConsultingSale(null); //========= for verification
	//		service.setContact(createservicedetails.getContact());
	//		service.setEstimateData(createservicedetails.getEstimateData());
	//		service.setInvoiceNote(createservicedetails.getInvoiceNote());
	//		//		service.setOpportunities(null);   //========= for verification
	//		service.setOrderNumber(createservicedetails.getOrderNumber());
	//		service.setProductType(createservicedetails.getProductType());
	//		service.setPurchaseDate(createservicedetails.getPurchaseDate());
	//		service.setRemarksForOption(createservicedetails.getRemarksForOption());
	//
	//		service.setGovermentfees(createservicedetails.getGovermentfees());
	//		service.setGovermentCode(createservicedetails.getGovermentCode());
	//		service.setGovermentGst(createservicedetails.getGovermentGst());
	//		service.setProfessionalFees(createservicedetails.getProfessionalFees());
	//		service.setProfessionalCode(createservicedetails.getProfessionalCode());
	//
	//		service.setProfesionalGst(createservicedetails.getProfesionalGst());
	//		service.setServiceCharge(createservicedetails.getServiceCharge());
	//		service.setServiceCode(createservicedetails.getServiceCode());
	//		service.setServiceGst(createservicedetails.getServiceGst());
	//		service.setOtherFees(createservicedetails.getOtherFees());
	//		service.setOtherCode(createservicedetails.getOtherCode());
	//		service.setOtherGst(createservicedetails.getOtherGst());
	//		
	//		Lead lead = leadRepository.findById(createservicedetails.getLeadId()).get();
	////		Company  company = null;
	////		if(createservicedetails.getCompanyId()!=null) {
	////			company=companyRepository.findById(createservicedetails.getCompanyId()).get();
	////		}else {
	////			company = new Company();
	////			company.setAddress(createservicedetails.getAddress());
	////			company.setCity(createservicedetails.getCity());
	////			company.setCountry(createservicedetails.getCountry());
	////			company.setGstNo(createservicedetails.getGstNo());
	////			company.setName(createservicedetails.getCompany());
	////			company.setPanNo(createservicedetails.getPanNo());
	////			company.setState(createservicedetails.getState());
	////			company.setCompanyAge(createservicedetails.getCompanyAge());
	////			companyRepository.save(company);
	////		}
	//		
	//		
	//		service.setCompanies(company);
	//		
	//		Client c = null;
	//		List<ServiceDetails> serviceList  = new ArrayList<>();
	//		Client client=null;
	//		System.out.println("aaaaaaaaaaaaaa");
	//		if(createservicedetails.getClientId()!=null) {
	//			long cId[] = new long[1];
	//			cId[0] = createservicedetails.getClientId();
	//
	//			Optional<Client> opClient = lead.getClients().stream().filter(i->i.getId().equals(cId[0])).findFirst();
	//			System.out.println("bbbbbbbbbbbbb"+opClient);
	//
	//			if(opClient!=null) {
	//				client=opClient.get();
	//				serviceList = client.getServiceDetails();
	//				System.out.println("ccccccccccccc");
	//
	//				String sName[] = new String[1];
	//				sName[0]=createservicedetails.getName();
	//
	//				List<ServiceDetails> checkService = serviceList.stream().filter(i->i.getName().equals(sName[0])).collect(Collectors.toList());
	//				//check its 
	//				if(checkService!=null && checkService.size()!=0) {
	//					System.out.println("dddddddddddddddd");
	//
	//					ServiceDetails services = checkService.get(0);
	//					services.setName(createservicedetails.getName());
	//					services.setCompany(createservicedetails.getCompany());
	//					services.setConsultingSale(null); //========= for verification
	//					services.setContact(createservicedetails.getContact());
	//					services.setEstimateData(createservicedetails.getEstimateData());
	//					services.setInvoiceNote(createservicedetails.getInvoiceNote());
	//					//					service.setOpportunities(null);   //========= for verification
	//					services.setOrderNumber(createservicedetails.getOrderNumber());
	//					services.setProductType(createservicedetails.getProductType());
	//					services.setPurchaseDate(createservicedetails.getPurchaseDate());
	//					services.setRemarksForOption(createservicedetails.getRemarksForOption());
	//					services.setCompanies(company);
	//					
	//					service.setGovermentfees(createservicedetails.getGovermentfees());
	//					service.setGovermentCode(createservicedetails.getGovermentCode());
	//					service.setGovermentGst(createservicedetails.getGovermentGst());
	//					service.setProfessionalFees(createservicedetails.getProfessionalFees());
	//					service.setProfessionalCode(createservicedetails.getProfessionalCode());
	//
	//					service.setProfesionalGst(createservicedetails.getProfesionalGst());
	//					service.setServiceCharge(createservicedetails.getServiceCharge());
	//					service.setServiceCode(createservicedetails.getServiceCode());
	//					service.setServiceGst(createservicedetails.getServiceGst());
	//					service.setOtherFees(createservicedetails.getOtherFees());
	//					service.setOtherCode(createservicedetails.getOtherCode());
	//					service.setOtherGst(createservicedetails.getOtherGst());
	//
	//					ServiceDetails serviceDetails = serviceDetailsRepository.save(services);
	//					//					serviceList.add(serviceDetails);
	//					//					client.setServiceDetails(serviceList);
	//					//					 c=clientRepository.save(client);
	//
	//				}else {
	//					System.out.println("eeeeeeeeeeeeeeee");
	//
	//					ServiceDetails serviceDetails = serviceDetailsRepository.save(service);
	//					serviceList.add(serviceDetails);
	//					client.setServiceDetails(serviceList);
	//					//					 c=clientRepository.save(client);
	//
	//				}
	//				//				client.setServiceDetails(serviceList);
	//				//				 c=clientRepository.save(client);
	//
	//			}else {
	//				System.out.println("ffffffffffffffffff");
	//
	//				client = new Client();
	//				client.setName(createservicedetails.getClientName());
	//				client.setEmails(createservicedetails.getEmail());
	//				client.setContactNo(createservicedetails.getContactNo());
	//				client.setDeleteStatus(false);
	//				List<ServiceDetails> sList  = new ArrayList<>();
	//				ServiceDetails serviceDetails = serviceDetailsRepository.save(service);
	//
	//				sList.add(serviceDetails);
	//				serviceList=sList;
	//				client.setServiceDetails(serviceList);  
	//				//				 c=clientRepository.save(client);
	//			}
	//		}else {
	//			System.out.println("hhhhhhhhhhhhhhhh");
	//
	//			client = new Client();
	//			client.setName(createservicedetails.getClientName());
	//			client.setEmails(createservicedetails.getEmail());
	//			client.setContactNo(createservicedetails.getContactNo());
	//			client.setDeleteStatus(false);
	//			List<ServiceDetails> sList  = new ArrayList<>();
	//			ServiceDetails serviceDetails = serviceDetailsRepository.save(service);
	//
	//			sList.add(serviceDetails);
	//			serviceList=sList;
	//			client.setServiceDetails(serviceList); 
	//			c=clientRepository.save(client);
	//		}
	//		if(lead.getClients()!=null && lead.getClients().size()!=0) {
	//			lead.getClients().add(c);
	//			leadRepository.save(lead);
	//		}
	//		//		 sendEmail(String subject,String text, Context context,String templateName) {
	//		sendEstimateMail(createservicedetails,lead, "this mail for estimate");
	//		return lead;
	//
	//
	//	}


	@Override
	public Boolean createEstimate(CreateServiceDetails createservicedetails) throws Exception {
		Boolean flag=false;
		Lead lead = leadRepository.findById(createservicedetails.getLeadId()).get();
		ServiceDetails serviceDetails = lead.getServiceDetails();
		System.out.println(serviceDetails+"  . ...service Details");
		if(serviceDetails==null) {
			Product product = productRepo.findById(createservicedetails.getProductId()).get();
			serviceDetails=new ServiceDetails();
			serviceDetails.setCreateDate(new Date());
			serviceDetails.setProduct(product);
			serviceDetails.setAddress(null);
			serviceDetails.setProductName(product.getProductName());
			Contact contact = contactRepo.findById(createservicedetails.getPrimaryContact()).get();
			serviceDetails.setPrimaryContact(contact);
			Contact secondaryContact = contactRepo.findById(createservicedetails.getSecondaryContact()).get();
			serviceDetails.setSecondaryContact(secondaryContact);
			Long assigneeId = createservicedetails.getAssigneeId();
			if(assigneeId!=null) {
				User assignee = userRepo.findById(assigneeId).get();
				serviceDetails.setAssignee(assignee);
			}
			// company
			if(createservicedetails.isConsultant()) {
				ConsultantByCompany consultantByCompany =new ConsultantByCompany();
				consultantByCompany.setName(createservicedetails.getOriginalCompanyName());
				consultantByCompany.setOriginalContact(createservicedetails.getOriginalContact());
				consultantByCompany.setOriginalEmail(createservicedetails.getOriginalEmail());
				consultantByCompany.setAddress(createservicedetails.getOriginalAddress());
				consultantByCompanyRepository.save(consultantByCompany);
				serviceDetails.setConsultantByCompany(consultantByCompany);

			}

			serviceDetails.setIsPresent(createservicedetails.getIsPresent());
			serviceDetails.setCompanyName(createservicedetails.getCompanyName());
			serviceDetails.setCompanyId(createservicedetails.getCompanyId());
			serviceDetails.setUnit(createservicedetails.isUnit());
			serviceDetails.setUnitName(createservicedetails.getUnitName());
			serviceDetails.setUnitId(createservicedetails.getUnitId());
			serviceDetails.setPanNo(createservicedetails.getPanNo());
			serviceDetails.setGstNo(createservicedetails.getGstNo());
			serviceDetails.setLeadId(createservicedetails.getLeadId());
			serviceDetails.setGstType(createservicedetails.getGstType());
			serviceDetails.setGstDocuments(createservicedetails.getGstDocuments());
			serviceDetails.setCompanyAge(createservicedetails.getCompanyAge());
			serviceDetails.setGovermentfees(createservicedetails.getGovermentfees());
			serviceDetails.setGovermentCode(createservicedetails.getGovermentCode());
			serviceDetails.setGovermentGst(createservicedetails.getGovermentGst());
			serviceDetails.setProfessionalFees(createservicedetails.getProfessionalFees());
			serviceDetails.setProfessionalCode(createservicedetails.getProfessionalCode());
			serviceDetails.setProfesionalGst(createservicedetails.getProfesionalGst());
			serviceDetails.setServiceCharge(createservicedetails.getServiceCharge());
			serviceDetails.setServiceCode(createservicedetails.getServiceCode());
			serviceDetails.setServiceGst(createservicedetails.getServiceGst());
			serviceDetails.setOtherFees(createservicedetails.getOtherFees());
			serviceDetails.setOtherCode(createservicedetails.getOtherCode());
			serviceDetails.setOtherGst(createservicedetails.getOtherGst());

			serviceDetails.setAddress(createservicedetails.getAddress());
			serviceDetails.setCity(createservicedetails.getCity());
			serviceDetails.setPrimaryPinCode(createservicedetails.getPrimaryPinCode());
			serviceDetails.setState(createservicedetails.getState());
			serviceDetails.setCountry(createservicedetails.getCountry());

			serviceDetails.setSecondaryAddress(createservicedetails.getSecondaryAddress());
			serviceDetails.setSecondaryCity(createservicedetails.getSecondaryCity()); 
			serviceDetails.setSecondaryPinCode(createservicedetails.getSecondaryPinCode());
			serviceDetails.setSecondaryState(createservicedetails.getSecondaryState());
			serviceDetails.setSecondaryCountry(createservicedetails.getSecondaryCountry());

			serviceDetails.setInvoiceNote(createservicedetails.getInvoiceNote());
			serviceDetails.setOrderNumber(createservicedetails.getOrderNumber());
			serviceDetails.setPurchaseDate(createservicedetails.getPurchaseDate());//CURRENT DATE 
			serviceDetails.setEstimateData(createservicedetails.getEstimateDate());
			serviceDetails.setRemarksForOption(createservicedetails.getRemarksForOption());
			serviceDetails.setCc(createservicedetails.getCc());


			serviceDetailsRepository.save(serviceDetails);
			lead.setServiceDetails(serviceDetails);
			leadRepository.save(lead);
			flag=true;

		}else {
			if(serviceDetails.isDeleted()){
				Product product = productRepo.findById(createservicedetails.getProductId()).get();

				serviceDetails=new ServiceDetails();
				serviceDetails.setCreateDate(new Date());
				serviceDetails.setAddress(null);
				serviceDetails.setProductName(product.getProductName());
				serviceDetails.setProduct(product);

				Contact contact = contactRepo.findById(createservicedetails.getPrimaryContact()).get();
				serviceDetails.setPrimaryContact(contact);
				Contact secondaryContact = contactRepo.findById(createservicedetails.getSecondaryContact()).get();
				serviceDetails.setSecondaryContact(secondaryContact);
				Long assigneeId = createservicedetails.getAssigneeId();
				if(assigneeId!=null) {
					User assignee = userRepo.findById(assigneeId).get();
					serviceDetails.setAssignee(assignee);
				}
				// company
				serviceDetails.setIsPresent(createservicedetails.getIsPresent());
				serviceDetails.setCompanyName(createservicedetails.getCompanyName());
				serviceDetails.setCompanyId(createservicedetails.getCompanyId());
				serviceDetails.setUnit(createservicedetails.isUnit());
				serviceDetails.setUnitName(createservicedetails.getUnitName());
				serviceDetails.setUnitId(createservicedetails.getUnitId());
				serviceDetails.setPanNo(createservicedetails.getPanNo());
				serviceDetails.setGstNo(createservicedetails.getGstNo());
				if(createservicedetails.isConsultant()) {
					ConsultantByCompany consultantByCompany =new ConsultantByCompany();
					consultantByCompany.setName(createservicedetails.getOriginalCompanyName());
					consultantByCompany.setOriginalContact(createservicedetails.getOriginalContact());
					consultantByCompany.setOriginalEmail(createservicedetails.getOriginalEmail());
					consultantByCompany.setAddress(createservicedetails.getAddress());
					consultantByCompanyRepository.save(consultantByCompany);
					serviceDetails.setConsultantByCompany(consultantByCompany);

				}
				serviceDetails.setGstType(createservicedetails.getGstType());
				serviceDetails.setGstDocuments(createservicedetails.getGstDocuments());
				serviceDetails.setCompanyAge(createservicedetails.getCompanyAge());
				serviceDetails.setGovermentfees(createservicedetails.getGovermentfees());
				serviceDetails.setGovermentCode(createservicedetails.getGovermentCode());
				serviceDetails.setGovermentGst(createservicedetails.getGovermentGst());
				serviceDetails.setProfessionalFees(createservicedetails.getProfessionalFees());
				serviceDetails.setProfessionalCode(createservicedetails.getProfessionalCode());
				serviceDetails.setProfesionalGst(createservicedetails.getProfesionalGst());
				serviceDetails.setServiceCharge(createservicedetails.getServiceCharge());
				serviceDetails.setServiceCode(createservicedetails.getServiceCode());
				serviceDetails.setServiceGst(createservicedetails.getServiceGst());
				serviceDetails.setOtherFees(createservicedetails.getOtherFees());
				serviceDetails.setOtherCode(createservicedetails.getOtherCode());
				serviceDetails.setOtherGst(createservicedetails.getOtherGst());

				serviceDetails.setAddress(createservicedetails.getAddress());
				serviceDetails.setCity(createservicedetails.getCity());
				serviceDetails.setPrimaryPinCode(createservicedetails.getPrimaryPinCode());
				serviceDetails.setState(createservicedetails.getState());
				serviceDetails.setCountry(createservicedetails.getCountry());

				serviceDetails.setSecondaryAddress(createservicedetails.getSecondaryAddress());
				serviceDetails.setSecondaryCity(createservicedetails.getSecondaryCity()); 
				serviceDetails.setSecondaryPinCode(createservicedetails.getSecondaryPinCode());
				serviceDetails.setSecondaryState(createservicedetails.getSecondaryState());
				serviceDetails.setSecondaryCountry(createservicedetails.getSecondaryCountry());

				serviceDetails.setInvoiceNote(createservicedetails.getInvoiceNote());
				serviceDetails.setOrderNumber(createservicedetails.getOrderNumber());
				serviceDetails.setPurchaseDate(createservicedetails.getPurchaseDate());//CURRENT DATE 
				serviceDetails.setEstimateData(createservicedetails.getEstimateDate());
				serviceDetails.setRemarksForOption(createservicedetails.getRemarksForOption());
				serviceDetails.setCc(createservicedetails.getCc());

				serviceDetailsRepository.save(serviceDetails);
				lead.setServiceDetails(serviceDetails);
				leadRepository.save(lead);

			}else {
				throw new Exception("Already exist please edit in existing estimate");

			}
		}

		return flag;

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
		context.setVariable("companyName", "INDIAN INDIA PRIVATE LIMITED");

		context.setVariable("totalAmount", "79001");

		context.setVariable("estimateUrl", "http://corpseed.com");
		context.setVariable("estimateNo", "ESTD0626");
		context.setVariable("estimateDate", "23-10-2023");

		//		mailSendSerivceImpl.sendEmail(emailTo, ccPersons, bccPersons, subject, "for testing Text", context, "newEstimateMail.html");
		//	    public void sendEmail(String[] emailTo, String[] ccPersons, String[] bccPersons,String subject,String text, Context context,String templateName) {

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
	public Boolean editEstimateInvoice(EditEstimate editEstimate) {
		Boolean flag=false;
		Product product = productRepo.findById(editEstimate.getProductId()).get();
		ServiceDetails serviceDetails = new ServiceDetails();
		serviceDetails.setCreateDate(new Date());
		serviceDetails.setProduct(product);

		serviceDetails.setAddress(null);
		serviceDetails.setProductName(product.getProductName());
		Contact contact = contactRepo.findById(editEstimate.getPrimaryContact()).get();
		serviceDetails.setPrimaryContact(contact);
		Contact secondaryContact = contactRepo.findById(editEstimate.getSecondaryContact()).get();
		serviceDetails.setSecondaryContact(secondaryContact);

		Long assigneeId = editEstimate.getAssigneeId();
		if(assigneeId!=null) {
			User assignee = userRepo.findById(assigneeId).get();
			serviceDetails.setAssignee(assignee);
		}

		// company
		serviceDetails.setIsPresent(editEstimate.getIsPresent());
		serviceDetails.setCompanyName(editEstimate.getCompanyName());
		serviceDetails.setCompanyId(editEstimate.getCompanyId());
		serviceDetails.setUnit(editEstimate.isUnit());
		serviceDetails.setUnitName(editEstimate.getUnitName());
		serviceDetails.setUnitId(editEstimate.getUnitId());
		serviceDetails.setPanNo(editEstimate.getPanNo());
		serviceDetails.setGstNo(editEstimate.getGstNo());
		serviceDetails.setLeadId(editEstimate.getLeadId());
		serviceDetails.setGstType(editEstimate.getGstType());
		if(editEstimate.isConsultant()) {
			ConsultantByCompany consultantByCompany =new ConsultantByCompany();
			consultantByCompany.setName(editEstimate.getOriginalCompanyName());
			consultantByCompany.setOriginalContact(editEstimate.getOriginalContact());
			consultantByCompany.setOriginalEmail(editEstimate.getOriginalEmail());
			consultantByCompany.setAddress(editEstimate.getOriginalAddress());
			consultantByCompanyRepository.save(consultantByCompany);
			serviceDetails.setConsultantByCompany(consultantByCompany);

		}
		serviceDetails.setGstDocuments(editEstimate.getGstDocuments());
		serviceDetails.setCompanyAge(editEstimate.getCompanyAge());
		serviceDetails.setGovermentfees(editEstimate.getGovermentfees());
		serviceDetails.setGovermentCode(editEstimate.getGovermentCode());
		serviceDetails.setGovermentGst(editEstimate.getGovermentGst());
		serviceDetails.setProfessionalFees(editEstimate.getProfessionalFees());
		serviceDetails.setProfessionalCode(editEstimate.getProfessionalCode());
		serviceDetails.setProfesionalGst(editEstimate.getProfesionalGst());
		serviceDetails.setServiceCharge(editEstimate.getServiceCharge());
		serviceDetails.setServiceCode(editEstimate.getServiceCode());
		serviceDetails.setServiceGst(editEstimate.getServiceGst());
		serviceDetails.setOtherFees(editEstimate.getOtherFees());
		serviceDetails.setOtherCode(editEstimate.getOtherCode());
		serviceDetails.setOtherGst(editEstimate.getOtherGst());

		serviceDetails.setAddress(editEstimate.getAddress());
		serviceDetails.setCity(editEstimate.getCity());
		serviceDetails.setPrimaryPinCode(editEstimate.getPrimaryPinCode());
		serviceDetails.setState(editEstimate.getState());
		serviceDetails.setCountry(editEstimate.getCountry());

		serviceDetails.setSecondaryAddress(editEstimate.getSecondaryAddress());
		serviceDetails.setSecondaryCity(editEstimate.getSecondaryCity()); 
		serviceDetails.setSecondaryPinCode(editEstimate.getSecondaryPinCode());
		serviceDetails.setSecondaryState(editEstimate.getSecondaryState());
		serviceDetails.setSecondaryCountry(editEstimate.getSecondaryCountry());

		serviceDetails.setInvoiceNote(editEstimate.getInvoiceNote());
		serviceDetails.setOrderNumber(editEstimate.getOrderNumber());
		serviceDetails.setPurchaseDate(editEstimate.getPurchaseDate());//CURRENT DATE 
		serviceDetails.setEstimateData(editEstimate.getEstimateDate());
		serviceDetails.setRemarksForOption(editEstimate.getRemarksForOption());
		serviceDetails.setCc(editEstimate.getCc());

		serviceDetailsRepository.save(serviceDetails);

		flag=true;



		return flag;
	}

	@Override
	public ServiceDetails editEstimateAddress(EditEstimateAddress editEstimateAddress) {
		ServiceDetails service=serviceDetailsRepository.findById(editEstimateAddress.getServiceId()).get();
		Company company = new Company();
		company.setAddress(editEstimateAddress.getAddress());
		company.setCity(editEstimateAddress.getCity());
		company.setCountry(editEstimateAddress.getCountry());
		company.setGstNo(editEstimateAddress.getGstNo());
		company.setName(editEstimateAddress.getName());
		company.setPanNo(editEstimateAddress.getPanNo());
		company.setState(editEstimateAddress.getState());
		company.setCompanyAge(editEstimateAddress.getCompanyAge());
		companyRepository.save(company);
		//		service.setCompanies(company);
		serviceDetailsRepository.save(service);

		return service;
	}

	@Override
	public Map<String,Object> getEstimateByLeadId(Long leadId) {
		ServiceDetails s=serviceDetailsRepository.findByLeadId(leadId);

		Map<String,Object>m=new HashMap<>();
		m.put("id", s.getId());
		m.put("productName", s.getProductName());
		m.put("address", s.getAddress());
		m.put("city", s.getCity());
		m.put("companyAge", s.getCompanyAge());
		m.put("company", s.getCompanyId());
		m.put("companyName", s.getCompanyName());
		m.put("consultingSales", s.getConsultingSale());
		m.put("country", s.getCountry());
		m.put("documents", s.getDocuments());
		m.put("product", s.getProduct());

		m.put("govermentCode", s.getGovermentCode());
		m.put("govermentFees", s.getGovermentfees());
		m.put("govermentGst", s.getGovermentGst());
		m.put("gstDocuments", s.getGstDocuments());

		m.put("gstNo", s.getGstNo());
		m.put("gstType", s.getGstType());
		m.put("invoiceNote", s.getInvoiceNote());
		m.put("isPrimaryAddress", s.getIsPrimaryAddress());
		m.put("isSecondaryAddress", s.getIsSecondaryAddress());
		m.put("leadId", s.getLeadId());
		m.put("orderNumber", s.getOrderNumber());
		m.put("purchaseDate", s.getPurchaseDate());

		m.put("otherCode", s.getOtherCode());
		m.put("otherGst", s.getOtherGst());
		m.put("otherFees", s.getOtherFees());

		m.put("panNo", s.getPanNo());
		m.put("primaryPinCode", s.getPrimaryPinCode());
		m.put("primaryTitle", s.getPrimaryTitle());

		m.put("serviceCode", s.getServiceCode());
		m.put("serviceGst", s.getServiceGst());
		m.put("serviceCharge", s.getServiceCharge());

		m.put("address", s.getAddress());
		m.put("primaryCity", s.getCity());
		m.put("primaryPinCode", s.getPrimaryPinCode());
		m.put("primaryState", s.getState());
		m.put("primaryCountry", s.getCountry());

		m.put("secondaryAddress", s.getSecondaryAddress());
		m.put("secondaryCity", s.getSecondaryCity());
		m.put("secondaryPinCode", s.getSecondaryPinCode());
		m.put("secondaryState", s.getSecondaryState());
		m.put("secondaryCountry", s.getSecondaryCountry());

		m.put("state", s.getState());
		m.put("status", s.getStatus());
		m.put("unitId", s.getUnitId());
		m.put("unitName", s.getUnitName());
		m.put("assigneeId", s.getAssignee());
		m.put("ccMail", s.getCc());
		m.put("createDate", s.getCreateDate());
		m.put("estimateDate", s.getEstimateData())
		;
		m.put("profesionalGst", s.getProfesionalGst());
		m.put("profesionalCode", s.getProfessionalCode());
		m.put("professionalFees", s.getProfessionalFees());

		m.put("primaryContact", s.getPrimaryContact());

		m.put("primaryPinCode", s.getPrimaryPinCode());
		m.put("primaryContact", s.getPrimaryContact());
		m.put("isUnit", s.isUnit());
		m.put("secondaryContact", s.getSecondaryContact());
		m.put("isSecondary", s.getIsSecondaryAddress());

		m.put("secondaryAddress", s.getSecondaryAddress());
		m.put("secondaryCity", s.getSecondaryCity());
		m.put("secondaryPinCode", s.getSecondaryPinCode());
		m.put("secondaryState", s.getSecondaryState());
		m.put("isUnit", s.isUnit());
		m.put("secondaryContact", s.getSecondaryContact());
		m.put("isSecondary", s.getIsSecondaryAddress());
		m.put("consultantByCompany", s.getConsultantByCompany());

		m.put("getRemarkForOperation", s.getRemarksForOption());
		int totalAmount = s.getGovermentfees()+s.getProfessionalFees()+s.getOtherFees();
		m.put("totalAmount", totalAmount);



		return m;
	}

	//	public List<ServiceDetails> getEstimateByUserId(Long userId, int page, int size) {
	//		
	//		Optional<User> user = userRepo.findById(userId);
	//		List<ServiceDetails>arrList = new ArrayList<>();
	//		Pageable pageable = PageRequest.of(page, size);
	//
	//		if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
	//			arrList=serviceDetailsRepository.findAll(pageable).getContent();
	//		}else {
	//			arrList=serviceDetailsRepository.findAllByAssigneeId(userId,pageable).getContent();
	//		}
	//		
	//		return arrList;
	//	}

	@Override
	public List<Map<String,Object>> getEstimateByUserId(Long userId, int page, int size) {
		Optional<User> user = userRepo.findById(userId);
		List<ServiceDetails>arrList = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, size);
		if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
			arrList=serviceDetailsRepository.findAll(pageable).getContent();
		}else {
			arrList=serviceDetailsRepository.findAllByAssigneeId(userId,pageable).getContent();
		}
		List<Map<String,Object>>res=new ArrayList<>();
		for(ServiceDetails s:arrList) {
			Map<String,Object>m=new HashMap<>();
			m.put("id", s.getId());
			m.put("productName", s.getProductName());
			m.put("address", s.getAddress());
			m.put("city", s.getCity());
			m.put("companyAge", s.getCompanyAge());
			m.put("company", s.getCompanyId());
			m.put("companyName", s.getCompanyName());
			m.put("consultingSales", s.getConsultingSale());
			m.put("country", s.getCountry());
			m.put("documents", s.getDocuments());
			m.put("purchaseDate", s.getPurchaseDate());

			m.put("govermentCode", s.getGovermentCode());
			m.put("govermentFees", s.getGovermentfees());
			m.put("govermentGst", s.getGovermentGst());
			m.put("gstDocuments", s.getGstDocuments());

			m.put("gstNo", s.getGstNo());
			m.put("gstType", s.getGstType());
			m.put("invoiceNote", s.getInvoiceNote());
			m.put("isPrimaryAddress", s.getIsPrimaryAddress());
			m.put("isSecondaryAddress", s.getIsSecondaryAddress());
			m.put("leadId", s.getLeadId());
			m.put("orderNumber", s.getOrderNumber());

			m.put("otherCode", s.getOtherCode());
			m.put("otherGst", s.getOtherGst());
			m.put("otherFees", s.getOtherFees());

			m.put("panNo", s.getPanNo());
			m.put("primaryPinCode", s.getPrimaryPinCode());
			m.put("primaryTitle", s.getPrimaryTitle());

			m.put("serviceCode", s.getServiceCode());
			m.put("serviceGst", s.getServiceGst());
			m.put("serviceCharge", s.getServiceCharge());

			m.put("state", s.getState());
			m.put("status", s.getStatus());
			m.put("unitId", s.getUnitId());
			m.put("unitName", s.getUnitName());
			m.put("assigneeId", s.getAssignee());  
			m.put("ccMail", s.getCc());
			m.put("createDate", s.getCreateDate());
			m.put("estimateDate", s.getEstimateData())
			;
			m.put("profesionalGst", s.getProfesionalGst());
			m.put("profesionalCode", s.getProfessionalCode());
			m.put("professionalFees", s.getProfessionalFees());

			m.put("primaryContact", s.getPrimaryContact());

			m.put("primaryPinCode", s.getPrimaryPinCode());
			m.put("primaryContact", s.getPrimaryContact());

			m.put("secondaryAddress", s.getSecondaryAddress());
			m.put("secondaryCity", s.getSecondaryCity());
			m.put("secondaryPinCode", s.getSecondaryPinCode());
			m.put("secondaryState", s.getSecondaryState());
			m.put("isUnit", s.isUnit());
			m.put("secondaryContact", s.getSecondaryContact());
			m.put("isSecondary", s.getIsSecondaryAddress());
			int totalAmount = s.getGovermentfees()+s.getProfessionalFees()+s.getOtherFees();
			m.put("totalAmount", totalAmount);
			m.put("consultantByCompany", s.getConsultantByCompany());
			res.add(m);

		}

		return res;
	}

	@Override
	public long getEstimateByUserIdCount(Long userId) {
		Optional<User> user = userRepo.findById(userId);
		long count=0;
		if(user.get()!=null && user.get().getRole().contains("ADMIN")) {
			count=serviceDetailsRepository.findAllCount();
		}else {
			count=serviceDetailsRepository.findAllCountByAssigneeId(userId);
		}
		return count;
	}

	@Override
	public Map<String, Object> getEstimateById(Long estimateId) {
		ServiceDetails s = serviceDetailsRepository.findById(estimateId).get();
		Map<String,Object>m=new HashMap<>();
		m.put("id", s.getId());
		m.put("productName", s.getProductName());
		m.put("address", s.getAddress());
		m.put("city", s.getCity());
		m.put("companyAge", s.getCompanyAge());
		m.put("company", s.getCompanyId());
		m.put("companyName", s.getCompanyName());
		m.put("consultingSales", s.getConsultingSale());
		m.put("country", s.getCountry());
		m.put("documents", s.getDocuments());
		m.put("purchaseDate", s.getPurchaseDate());

		m.put("govermentCode", s.getGovermentCode());
		m.put("govermentFees", s.getGovermentfees());
		m.put("govermentGst", s.getGovermentGst());
		m.put("gstDocuments", s.getGstDocuments());

		m.put("gstNo", s.getGstNo());
		m.put("gstType", s.getGstType());
		m.put("invoiceNote", s.getInvoiceNote());
		m.put("isPrimaryAddress", s.getIsPrimaryAddress());
		m.put("isSecondaryAddress", s.getIsSecondaryAddress());
		m.put("leadId", s.getLeadId());
		m.put("orderNumber", s.getOrderNumber());

		m.put("otherCode", s.getOtherCode());
		m.put("otherGst", s.getOtherGst());
		m.put("otherFees", s.getOtherFees());

		m.put("panNo", s.getPanNo());
		m.put("primaryPinCode", s.getPrimaryPinCode());
		m.put("primaryTitle", s.getPrimaryTitle());

		m.put("serviceCode", s.getServiceCode());
		m.put("serviceGst", s.getServiceGst());
		m.put("serviceCharge", s.getServiceCharge());

		m.put("state", s.getState());
		m.put("status", s.getStatus());
		m.put("unitId", s.getUnitId());
		m.put("unitName", s.getUnitName());
		m.put("assigneeId", s.getAssignee());  
		m.put("assigneeIds", s.getAssignee().getId());  

		m.put("ccMail", s.getCc());
		m.put("createDate", s.getCreateDate());
		m.put("estimateDate", s.getEstimateData())
		;
		m.put("profesionalGst", s.getProfesionalGst());
		m.put("profesionalCode", s.getProfessionalCode());
		m.put("professionalFees", s.getProfessionalFees());

		m.put("primaryContact", s.getPrimaryContact());

		m.put("primaryContactId", s.getPrimaryContact().getId());
		m.put("primaryContactTitle", s.getPrimaryContact().getTitle());
		m.put("primaryContactName", s.getPrimaryContact().getName());
		m.put("primaryContactEmails", s.getPrimaryContact().getEmails());
		m.put("primaryContactDesignation", s.getPrimaryContact().getDesignation());
		m.put("primaryContactContactNo", s.getPrimaryContact().getContactNo());

		m.put("secondaryContactId", s.getSecondaryContact().getId());
		m.put("secondaryContactTitle", s.getSecondaryContact().getTitle());
		m.put("secondaryContactName", s.getSecondaryContact().getName());
		m.put("secondaryContactEmails", s.getSecondaryContact().getEmails());
		m.put("secondaryContactDesignation", s.getSecondaryContact().getDesignation());
		m.put("secondaryContactContactNo", s.getSecondaryContact().getContactNo());


		m.put("primaryPinCode", s.getPrimaryPinCode());
		m.put("primaryContact", s.getPrimaryContact());

		m.put("secondaryAddress", s.getSecondaryAddress());
		m.put("secondaryCity", s.getSecondaryCity());
		m.put("secondaryPinCode", s.getSecondaryPinCode());
		m.put("secondaryState", s.getSecondaryState());
		m.put("secondaryCountry", s.getSecondaryCountry());
		m.put("remarkForOperation", s.getRemarksForOption());

		m.put("isUnit", s.isUnit());
		m.put("secondaryContact", s.getSecondaryContact());
		m.put("isSecondary", s.getIsSecondaryAddress());
		int totalAmount = s.getGovermentfees()+s.getProfessionalFees()+s.getOtherFees();
		System.out.println("total amount tttttt"+totalAmount);
		m.put("totalAmount", totalAmount);
		m.put("consultantByCompany", s.getConsultantByCompany());
		return m;
	}

	@Override
	public List<Map<String, Object>> getEstimateByStatus(String status,int page,int size,Long userId) {
		List<Map<String,Object>>sList = new ArrayList<>();
		List<ServiceDetails>serviceList=new ArrayList<>();
		Pageable pageable = PageRequest.of(page, size);
		User user = userRepo.findById(userId).get();
		List<String> role = user.getUserRole().stream().map(i->i.getName()).collect(Collectors.toList());
		if(role.contains("ADMIN")||role.contains("ACCOUNT")) {
			if(status.equals("All")) {
				serviceList=serviceDetailsRepository.findAll(pageable).getContent();
			}else {
				serviceList=serviceDetailsRepository.findAllByStatus(status,pageable).getContent();
			}
		}
		for(ServiceDetails s:serviceList) {
			Map<String,Object>m=new HashMap<>();
			m.put("id", s.getId());
			m.put("productName", s.getProductName());
			m.put("address", s.getAddress());
			m.put("city", s.getCity());
			m.put("companyAge", s.getCompanyAge());
			m.put("company", s.getCompanyId());
			m.put("companyName", s.getCompanyName());
			m.put("consultingSales", s.getConsultingSale());
			m.put("country", s.getCountry());
			m.put("documents", s.getDocuments());
			m.put("purchaseDate", s.getPurchaseDate());

			m.put("govermentCode", s.getGovermentCode());
			m.put("govermentFees", s.getGovermentfees());
			m.put("govermentGst", s.getGovermentGst());
			m.put("gstDocuments", s.getGstDocuments());

			m.put("gstNo", s.getGstNo());
			m.put("gstType", s.getGstType());
			m.put("invoiceNote", s.getInvoiceNote());
			m.put("isPrimaryAddress", s.getIsPrimaryAddress());
			m.put("isSecondaryAddress", s.getIsSecondaryAddress());
			m.put("leadId", s.getLeadId());
			m.put("orderNumber", s.getOrderNumber());

			m.put("otherCode", s.getOtherCode());
			m.put("otherGst", s.getOtherGst());
			m.put("otherFees", s.getOtherFees());

			m.put("panNo", s.getPanNo());
			m.put("primaryPinCode", s.getPrimaryPinCode());
			m.put("primaryTitle", s.getPrimaryTitle());

			m.put("serviceCode", s.getServiceCode());
			m.put("serviceGst", s.getServiceGst());
			m.put("serviceCharge", s.getServiceCharge());

			m.put("state", s.getState());
			m.put("status", s.getStatus());
			m.put("unitId", s.getUnitId());
			m.put("unitName", s.getUnitName());
			m.put("assigneeId", s.getAssignee());  
			m.put("assigneeIds", s.getAssignee().getId());  

			m.put("ccMail", s.getCc());
			m.put("createDate", s.getCreateDate());
			m.put("estimateDate", s.getEstimateData())
			;
			m.put("profesionalGst", s.getProfesionalGst());
			m.put("profesionalCode", s.getProfessionalCode());
			m.put("professionalFees", s.getProfessionalFees());

			m.put("primaryContact", s.getPrimaryContact());

			m.put("primaryContactId", s.getPrimaryContact().getId());
			m.put("primaryContactTitle", s.getPrimaryContact().getTitle());
			m.put("primaryContactName", s.getPrimaryContact().getName());
			m.put("primaryContactEmails", s.getPrimaryContact().getEmails());
			m.put("primaryContactDesignation", s.getPrimaryContact().getDesignation());
			m.put("primaryContactContactNo", s.getPrimaryContact().getContactNo());

			m.put("secondaryContactId", s.getSecondaryContact().getId());
			m.put("secondaryContactTitle", s.getSecondaryContact().getTitle());
			m.put("secondaryContactName", s.getSecondaryContact().getName());
			m.put("secondaryContactEmails", s.getSecondaryContact().getEmails());
			m.put("secondaryContactDesignation", s.getSecondaryContact().getDesignation());
			m.put("secondaryContactContactNo", s.getSecondaryContact().getContactNo());


			m.put("primaryPinCode", s.getPrimaryPinCode());
			m.put("primaryContact", s.getPrimaryContact());

			m.put("secondaryAddress", s.getSecondaryAddress());
			m.put("secondaryCity", s.getSecondaryCity());
			m.put("secondaryPinCode", s.getSecondaryPinCode());
			m.put("secondaryState", s.getSecondaryState());
			m.put("secondaryCountry", s.getSecondaryCountry());
			m.put("remarkForOperation", s.getRemarksForOption());

			m.put("isUnit", s.isUnit());
			m.put("secondaryContact", s.getSecondaryContact());
			m.put("isSecondary", s.getIsSecondaryAddress());
			int totalAmount = s.getGovermentfees()+s.getProfessionalFees()+s.getOtherFees();
			System.out.println("total amount tttttt"+totalAmount);
			m.put("totalAmount", totalAmount);
			m.put("consultantByCompany", s.getConsultantByCompany());
			sList.add(m);
		}
		return sList;
	}

	@Override
	public Boolean approvedEstimate(String status,Long estimateId,Long userId) {
		Boolean flag=false;
		User user = userRepo.findById(userId).get();
		List<String> role = user.getUserRole().stream().map(i->i.getName()).collect(Collectors.toList());
		if(role.contains("ADMIN")||role.contains("ACCOUNT")) {
			ServiceDetails service = serviceDetailsRepository.findById(estimateId).get();
			service.setStatus(status);
			service.setUpdateDate(new Date());
			service.setUpdatedBy(user);
			serviceDetailsRepository.save(service);
			flag=true;
		}
		return flag;
	}

}
