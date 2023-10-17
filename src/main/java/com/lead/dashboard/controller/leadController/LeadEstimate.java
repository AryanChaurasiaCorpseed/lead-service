package com.lead.dashboard.controller.leadController;


import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeadEstimate {

    @Autowired
    private LeadService leadservice;

    @PostMapping("leadService/api/v1/lead/createEstimate")
    public Lead createEstimate(@RequestBody CreateServiceDetails createServiceDetails)
    {
    	Lead res=leadservice.createEstimate(createServiceDetails);
        return res;
    }
}

