package com.lead.dashboard.serviceImpl.IvrServiceimpl;

import java.util.ArrayList;
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
			map.put("caller", ivr.getCallerNumber());
			map.put("callerStatus", ivr.getCallStatus());
			map.put("duration", ivr.getDuration());
			map.put("date", ivr.getDate());
			res.add(map);
			
		}
		return res;
	}


	@Override
	public IvrData createIvrData(String agentNumber, String startTime, String duration,
			String callerId, String endTime, String callRecordingUrl) {
		IvrData ivrData = new IvrData();
		ivrData.setCallerNumber(callerId);
		ivrData.setAgentName(callRecordingUrl);
		ivrData.setAgentNumber(agentNumber);
		ivrData.setDuration(duration);
//		ivrData.setCallStatus(callRecordingUrl);
		ivrDataRepository.save(ivrData);
		return ivrData;
	}

}
