package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.EditEstimate;
import com.lead.dashboard.dto.EditEstimateAddress;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.CompanyRepository;
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
	LeadRepository leadRepository;
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ServiceDetailsRepository serviceDetailsRepository;



	@Autowired
	ClientRepository clientRepository;

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
		System.out.println("aaaaaaaaaaaaaa");
		if(createservicedetails.getClientId()!=null) {
			long cId[] = new long[1];
			cId[0] = createservicedetails.getClientId();

			Optional<Client> opClient = lead.getClients().stream().filter(i->i.getId().equals(cId[0])).findFirst();
			System.out.println("bbbbbbbbbbbbb"+opClient);

			if(opClient!=null) {
				client=opClient.get();
				serviceList = client.getServiceDetails();
				System.out.println("ccccccccccccc");

				String sName[] = new String[1];
				sName[0]=createservicedetails.getName();

				List<ServiceDetails> checkService = serviceList.stream().filter(i->i.getName().equals(sName[0])).collect(Collectors.toList());
				//check its 
				if(checkService!=null && checkService.size()!=0) {
					System.out.println("dddddddddddddddd");

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
		context.setVariable("companyName", "INDIAN INDIA PRIVATE LIMITED");
		
		context.setVariable("totalAmount", "79001");

		context.setVariable("estimateUrl", "http://corpseed.com");
		context.setVariable("estimateNo", "ESTD0626");
		context.setVariable("estimateDate", "23-10-2023");

		mailSendSerivceImpl.sendEmail(emailTo, ccPersons, bccPersons, subject, "for testing Text", context, "newEstimateMail.html");
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
	public ServiceDetails editEstimateInvoice(EditEstimate editEstimate) {
		ServiceDetails service=serviceDetailsRepository.findById(editEstimate.getServiceDetailsId()).get();
		service.setGovermentfees(editEstimate.getGovermentfees());
		service.setGovermentCode(editEstimate.getGovermentCode());
		service.setGovermentGst(editEstimate.getGovermentGst());
		service.setProfessionalFees(editEstimate.getProfessionalFees());
		service.setProfessionalCode(editEstimate.getProfessionalCode());
		service.setProfesionalGst(editEstimate.getProfesionalGst());
		service.setServiceCharge(editEstimate.getServiceCharge());
		service.setServiceCode(editEstimate.getServiceCode());
		service.setServiceGst(editEstimate.getServiceGst());
		service.setOtherFees(editEstimate.getOtherFees());
		service.setOtherCode(editEstimate.getOtherCode());
		service.setOtherGst(editEstimate.getOtherGst());
		serviceDetailsRepository.save(service);
		
		
		return service;
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
		service.setCompanies(company);
		serviceDetailsRepository.save(service);

		return service;
	}

}
