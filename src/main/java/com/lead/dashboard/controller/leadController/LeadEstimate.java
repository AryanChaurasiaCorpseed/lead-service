package com.lead.dashboard.controller.leadController;


import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.service.LeadService;
import com.lead.dashboard.util.UrlsMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeadEstimate {

    @Autowired
    private LeadService leadservice;

	@PostMapping(UrlsMapping.CREATE_ESTIMATE)
    public Lead createEstimate(@RequestBody CreateServiceDetails createServiceDetails)
    {
    	Lead res=leadservice.createEstimate(createServiceDetails);
        return res;
    }
    @GetMapping(UrlsMapping.GET_ALL_ESTIMATE)
    public List<ServiceDetails> getAllEstimate()
    {
    	List<ServiceDetails> res=leadservice.getAllEstimate();
        return res;
    }
    
    @GetMapping(UrlsMapping.GET_ESTIMATE)
    public ServiceDetails getEstimate(@RequestParam Long  estimateId)
    {
    	ServiceDetails res=leadservice.getEstimate(estimateId);
        return res;
    }

}

