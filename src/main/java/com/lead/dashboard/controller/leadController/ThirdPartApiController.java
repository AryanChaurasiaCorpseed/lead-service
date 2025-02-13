package com.lead.dashboard.controller.leadController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.config.ApiService;

import reactor.core.publisher.Mono;

@RestController
public class ThirdPartApiController {
	
	@Autowired
    private ApiService apiService;

    @GetMapping("leadService/api/v1/lead/getAllIvr")
    public String callApi() {
        String endpoint = "/data"; // Replace with the specific endpoint
        return apiService.callThirdPartyApiWithParamsAndHeaders();
    }

}
