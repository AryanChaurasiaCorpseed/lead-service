package com.lead.dashboard.feignClient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "global-feign-client", url = "https://record.corpseed.com/api")
public interface GlobalFeignClient {

    @GetMapping("/checkUserPresence")
    Boolean checkUserPresence(@RequestParam String email);
}
