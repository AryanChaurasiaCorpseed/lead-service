package com.lead.dashboard.controller.leadController;


import com.lead.dashboard.domain.ServiceDetails;
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

    @PostMapping("/v1/lead/createEstimate")
    public ServiceDetails createEstimate(@RequestBody CreateServiceDetails createServiceDetails)
    {
        ServiceDetails res=leadservice.createEstimate(createServiceDetails);
        return res;
    }
}

