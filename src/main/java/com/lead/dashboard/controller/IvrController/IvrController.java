package com.lead.dashboard.controller.IvrController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.IvrData;
import com.lead.dashboard.service.IvrService.IvrService;
import com.lead.dashboard.util.UrlsMapping;
@RestController
public class IvrController {
	
	@Autowired
	IvrService ivrService;
	
	
	
	@GetMapping(UrlsMapping.GET_ALL_IVR_DATA)
	public List<Map<String ,Object>> getAllIvrData()
	{
		List<Map<String ,Object>> ivrList=ivrService.getAllIvrData();
		
		return ivrList;		 

	}
	@GetMapping(UrlsMapping.CREATE_IVR_DATA)
	public IvrData createIvrData(String aggentNumber,String startTime,String destination,String callerId,String endTime,String callRecordingUrl)
	{
		IvrData ivrData=ivrService.createIvrData(aggentNumber,startTime,destination,callerId,endTime,callRecordingUrl);
		
		return ivrData ;		 

	}
}
