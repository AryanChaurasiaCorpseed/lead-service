package com.lead.dashboard.service.IvrService;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.IvrData;
@Service 
public interface IvrService {

	List<Map<String ,Object>> getAllIvrData();

	IvrData createIvrData(String aggentNumber, String startTime, String destination, String callerId,
			String endTime, String callRecordingUrl);

}
