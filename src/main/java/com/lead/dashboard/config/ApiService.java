package com.lead.dashboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lead.dashboard.controller.chatController.Person;
import com.lead.dashboard.dto.mapper.Meta;
import com.lead.dashboard.dto.mapper.Test;

import org.springframework.http.ResponseEntity;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import reactor.core.publisher.Mono;
//
//@Service
//public class ApiService {
//
//    @Autowired
//    WebClient.Builder webClientBuilder;
//
//    
//    public ApiService(WebClient.Builder webClientBuilder) {
//        this.webClientBuilder = webClientBuilder;
//    }
//
//    public Mono<String> getApiResponseWithToken() {
////        String url = "https://api.example.com/endpoint";  // Replace with actual API URL
//        
//        String url = UriComponentsBuilder.fromHttpUrl("https://sr.knowlarity.com/newsr/api/v1/call")
//                .queryParam("start_time__gt", "2025-01-12T00%3A00%3A00")
//                .queryParam("start_time__lt","2025-02-12T23%3A59%3A59")
//                .queryParam("order_by", "-start_time")
//                .queryParam("order_id", 0)
//                .queryParam("is_disposition", true)
//                .queryParam("get_total_count", false)
//                .queryParam("only_mobile_logs", false)
//                .queryParam("get_mobile_call_logs", false)
//                .queryParam("limit", 20)
//                .queryParam("offset", 0)
//
//                .toUriString();
//        
//        return webClientBuilder.build()
//                               .get()
//                               .uri(url)
//                               .header("authorization", "227c2db1-e9d5-49c4-afdf-b2c50b915e4e")
//                               .header("x-api-key", "QdQa83awS05tyB0KAVATX7tvm3WuBXz16QEluhix")  // Add the Authorization header
//                               .retrieve()
//                               .bodyToMono(String.class);  // Asynchronously handle the response
//    }
//    
//    
//    
//}
@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    public String callThirdPartyApiWithParamsAndHeaders() {
        // Set up the URL with query parameters
        String url = "https://sr.knowlarity.com/newsr/api/v1/call/?start_time__gt=2025-01-12&start_time__lt=2025-02-12";

        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "227c2db1-e9d5-49c4-afdf-b2c50b915e4e");
        headers.set("x-api-key", "QdQa83awS05tyB0KAVATX7tvm3WuBXz16QEluhix");
        headers.set("cache-control", "no-cache");

        // Create the HttpEntity with headers̥
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Call the third-party API using RestTemplate
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String temp = response.getBody();
        
        String jsonString =temp;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Test IvrDetailDto = objectMapper.readValue(jsonString, Test.class);
		    System.out.println("designation: " + IvrDetailDto.getMeta().getTotal_count());
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
        
        System.out.println(temp+"...temp");
        // Return the response body
        return response.getBody();
    }
}
