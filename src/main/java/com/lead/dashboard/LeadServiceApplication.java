package com.lead.dashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.lead.dashboard.util.Helper;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class LeadServiceApplication  implements CommandLineRunner {
	
	@Autowired
	private Helper helper;

	public static void main(String[] args) {
		SpringApplication.run(LeadServiceApplication.class, args);
	}
	
	   @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

	
	   
		@Override
		public void run(String... args) throws Exception {
			String filePath = "D:/crm_client.csv";
//			helper.processCsvFile(filePath);
		}
		

}
  


       



