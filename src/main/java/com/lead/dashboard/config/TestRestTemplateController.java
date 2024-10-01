package com.lead.dashboard.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lead.dashboard.domain.product.Product;

@RestController
public class TestRestTemplateController {

	
	   @Autowired
	   RestTemplate restTemplate;

	   @RequestMapping(value = "/template/products")
	   public String getProductList() {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8085/master/business-activity/test", HttpMethod.GET, entity, String.class).getBody();
	   }
	   
	   @RequestMapping(value = "/template/products/{id}", method = RequestMethod.DELETE)
	   public String deleteProduct(@PathVariable("id") String id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Product> entity = new HttpEntity<Product>(headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/products/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	   }
}
