package com.lead.dashboard.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name="COMPLIANCE-MASTER-SERVICE", url="http://localhost:8085")
public interface SecurityFeignClient {

	@GetMapping("/master/business-activity/test")
	public String test();
 

}
