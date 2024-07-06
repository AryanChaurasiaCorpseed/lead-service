package com.lead.dashboard.serviceImpl.IvrServiceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.IvrData;
import com.lead.dashboard.service.IvrService.IvrService;
import com.lead.dashboard.repository.*;
@Service
public class IvrServiceImpl implements IvrService {

	@Autowired
	IvrDataRepository ivrDataRepository;
	
	
	@Override
	public List<Map<String ,Object>> getAllIvrData() {
		List<IvrData> ivrList = ivrDataRepository.findAll();
		 List<Map<String ,Object>> res = new ArrayList<>();
		for(IvrData ivr:ivrList) {
			Map<String,Object>map = new HashMap<>();
			map.put("id", ivr.getId());
			map.put("callerNumber", ivr.getCallerNumber());
			map.put("callerName", ivr.getCallerNumber());
			map.put("agentName", ivr.getCallerNumber());
			map.put("callerStatus", ivr.getCallStatus());
			map.put("duration", ivr.getDuration());
			map.put("startTime", ivr.getStartTime());
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
		return ivrData;
	}

}
