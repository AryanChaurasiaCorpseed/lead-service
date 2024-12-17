package com.lead.dashboard.serviceImpl.IvrServiceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.IvrData;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.service.IvrService.IvrService;
import com.lead.dashboard.repository.*;
@Service
public class IvrServiceImpl implements IvrService {

	@Autowired
	IvrDataRepository ivrDataRepository;

	@Autowired
	LeadRepository leadRepository;

	@Autowired 
	StatusRepository statusRepository;

	@Autowired
	UserRepo userRepo;

	@Override
	public List<Map<String ,Object>> getAllIvrData() {
		List<IvrData> ivrList = ivrDataRepository.findAll();
		List<Map<String ,Object>> res = new ArrayList<>();
		for(IvrData ivr:ivrList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", ivr.getId());
			map.put("callerNumber", ivr.getCallerNumber());
			map.put("callerName", ivr.getCallerNumber());
			map.put("agentName", ivr.getAgentName());
			map.put("agentNumber", ivr.getAgentNumber());
			map.put("callerStatus", ivr.getCallStatus());
			map.put("duration", ivr.getDuration());
			map.put("startTime", ivr.getStartTime());
			map.put("recordingUrls", ivr.getRecording());

			map.put("endTime", ivr.getEndTime());
			map.put("date", ivr.getDate());
			res.add(map);

		}
		return res;
	}


	@Override
	public IvrData createIvrData(String callerNumber, String agentName,String agentNumber, String startTime, String duration, String endTime, String callRecordingUrl) {
		IvrData ivrData = new IvrData();
		ivrData.setAgentName(agentName);
		ivrData.setAgentNumber(agentNumber);
		ivrData.setRecording(callRecordingUrl);
		ivrData.setCallerNumber(callerNumber);
		ivrData.setDuration(duration);
		SimpleDateFormat formatter=new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");  
		Date startDate=null;
		try {
			startDate=formatter.parse(startTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		Date endDate=null;
		System.out.println("date ..... "+new Date());
		try {
			endDate=formatter.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		ivrData.setStartTime(startDate);
		ivrData.setEndTime(endDate);

		//		ivrData.setStartTime(startTime);
		//		ivrData.setCallStatus(callRecordingUrl);
		ivrDataRepository.save(ivrData);
		try {
			createIvrLead(callerNumber,agentNumber,ivrData); 
		}catch(Exception e) {
			System.out.println(e.getMessage());

		}
		return ivrData;
	}

	public boolean createIvrLead(String callerNumber,String agentNumber,IvrData ivrData) {
		Lead lead = new Lead();
		lead.setMobileNo(callerNumber);
		Client c = new Client();
		c.setPrimary(true);
		c.setContactNo(callerNumber);
		List<Client>cList = new ArrayList<Client>();
		cList.add(c);
		
		User assignee=null;
		if(agentNumber!=null) {
			 assignee=userRepo.findByIvrAgentNo(agentNumber);
				lead.setAuto(false);
				lead.setIvrQuality(true);


		}else {
			 assignee=userRepo.findById(2l).get();
				lead.setAuto(true);
				lead.setIvrQuality(false);

		}
		lead.setAssignee(assignee);
		lead.setClients(cList);
		String leadName="IVR Call "+callerNumber;
		lead.setLeadName(leadName);
		lead.setCreateDate(new Date());
		lead.setSource("IVR");
		lead.setAuto(false);
		Status status = statusRepository.findAllByName("New");
		lead.setStatus(status);
		leadRepository.save(lead);
		ivrData.setLeadCreated(true);
		ivrDataRepository.save(ivrData);
		return true;
	}


	@Override
	public List<Map<String, Object>> getAllIvrDataWithPage(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<IvrData> ivrList = ivrDataRepository.findAll(pageable).getContent();
		List<Map<String ,Object>> res = new ArrayList<>();
		for(IvrData ivr:ivrList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", ivr.getId());
			map.put("callerNumber", ivr.getCallerNumber());
			map.put("callerName", ivr.getCallerNumber());
			map.put("agentName", ivr.getAgentName());
			map.put("agentNumber", ivr.getAgentNumber());
			map.put("callerStatus", ivr.getCallStatus());
			map.put("recordingUrls", ivr.getRecording());

			map.put("duration", ivr.getDuration());
			map.put("startTime", ivr.getStartTime());
			map.put("endTime", ivr.getEndTime());
			map.put("date", ivr.getDate());
			res.add(map);

		}
		return res;
	}


	@Override
	public int getAllIvrDataCount() {
		int ivrList = ivrDataRepository.findAllCount();

		
		return ivrList;
	}


}
