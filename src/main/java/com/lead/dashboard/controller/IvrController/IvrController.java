package com.lead.dashboard.controller.IvrController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public IvrData createIvrData(@RequestParam String callerNumber,@RequestParam String agentName,@RequestParam String aggentNumber,@RequestParam String startTime,@RequestParam String duration,@RequestParam String endTime,@RequestParam String callRecordingUrl)
	{
		IvrData ivrData=ivrService.createIvrData(callerNumber,agentName,aggentNumber,startTime,duration,endTime,callRecordingUrl);
		
		return ivrData ;

	}
	
	@GetMapping(UrlsMapping.GET_ALL_IVR_DATA_WITH_PAGE)
	public List<Map<String ,Object>> getAllIvrDataWithPage(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size)
	{
		List<Map<String ,Object>> ivrList=ivrService.getAllIvrDataWithPage(page-1,size);
		
		return ivrList;

	}
	
	@GetMapping(UrlsMapping.GET_ALL_IVR_DATA_COUNT)
	public int getAllIvrDataCount()
	{
		int ivrList=ivrService.getAllIvrDataCount();
		
		return ivrList;

	}
}
