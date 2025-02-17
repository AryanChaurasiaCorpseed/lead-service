package com.lead.dashboard.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lead.dashboard.controller.chatController.Person;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.IvrData;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.mapper.Meta;
import com.lead.dashboard.dto.mapper.Objects;
import com.lead.dashboard.dto.mapper.Test;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.IvrDataRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;

import org.springframework.http.ResponseEntity;

@Service
public class ApiService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	StatusRepository statusRepository;
	
	
	@Autowired
	LeadRepository leadRepository;

	@Autowired
	UserRepo userRepo;

	@Autowired
	IvrDataRepository ivrDataRepository;
	
	@Autowired
	ClientRepository clientRepository;

	public String callThirdPartyApiWithParamsAndHeaders() {
		// Set up the URL with query parameters
		String url = "https://sr.knowlarity.com/newsr/api/v1/call/?start_time__gt=2025-01-12&start_time__lt=2025-02-12";

		// Set up the headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "227c2db1-e9d5-49c4-afdf-b2c50b915e4e");
		headers.set("x-api-key", "QdQa83awS05tyB0KAVATX7tvm3WuBXz16QEluhix");
		headers.set("cache-control", "no-cache");

		// Create the HttpEntity with headers̥
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Call the third-party API using RestTemplate
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		String temp = response.getBody();

		String jsonString =temp;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Test IvrDetailDto = objectMapper.readValue(jsonString, Test.class);
			System.out.println("designation: " + IvrDetailDto.getMeta().getTotal_count());
			Boolean flag = createIvrData(IvrDetailDto);
			System.out.println("test...."+flag);

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(temp+"...temp");
		// Return the response body
		return response.getBody();
	}

	public Date convertStringToDate(String dateString) throws Exception {

		// Define the date format pattern to match the string
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssXXX");

		// Parse the string to Date
		Date date = format.parse(dateString);

		return date;
	}

	public Boolean createIvrData(Test ivrDetailDto) {
		Boolean flag=false;
		List<Objects> allData = ivrDetailDto.getObjects();
		List<IvrData>ivrDataList=new ArrayList<>();
		for(Objects a:allData) {
			List<Long>ids=ivrDataRepository.findByCallerNumberAndStartTime(a.getCallerph(),a.getStart_time().toString());
			if(ids!=null && ids.size()>0) {

				IvrData ivrData=new IvrData();
				ivrData.setCallerNumber(a.getCallerph());
				String designation = a.getDestination();
				if(designation.equals("Call Missed")) {
					ivrData.setCallStatus("missed");
				}else if(designation.equals("Welcome Sound")) {
					ivrData.setCallStatus("welcomeSound");
				}else {
					ivrData.setCallStatus("recived");
					ivrData.setAgentNumber(designation);
					ivrData.setRecording(a.getFilepath());
					User user=userRepo.findByIvrAgentNo(designation);
					if(user!=null) {
						ivrData.setAgentName(user.getEmail());
					}
				}
				ivrData.setDuration(a.getDuration()+"");

				ivrData.setFlag(true);  //true means not lead created now
				if(a.getStart_time()!=null) {
					try {
						ivrData.setStartTime(convertStringToDate(a.getStart_time()));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				ivrDataRepository.save(ivrData);
				ivrDataList.add(ivrData);
				createLeadList(ivrDataList);
				flag=true;
			}

		}
		return flag;

	}
	public Boolean createLeadList(List<IvrData>ivrDataList) {
		for(IvrData i:ivrDataList) {
			if(i.isFlag()) {
			Lead l = new Lead();
			l.setName(null);
			
			User user = userRepo.findByemail(i.getAgentName());
			if(user!=null) {
				l.setAssignee(user);
				l.setAuto(false);
				
			}else {
				l.setAuto(true);
				
			}
			l.setLeadName("IVR CALL "+i.getCallerNumber());
			l.setMobileNo(i.getCallerNumber());
			Client c=new Client();
			c.setContactNo(i.getCallerNumber());
			c.setPrimary(true);
			clientRepository.save(c);
			List<Client>cList=new ArrayList<>();
			cList.add(c);
			l.setClients(cList);
			l.setSource("IVR");
			Status status = statusRepository.findByName("New");
			l.setStatus(status);
			leadRepository.save(l);
			i.setFlag(false);
			ivrDataRepository.save(i);
			}
			
		}
		return true;   
	}

}
