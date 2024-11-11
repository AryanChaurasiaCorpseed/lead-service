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
 
     

//	@Override
//	public void run(String... args) throws Exception {̥
//		String filePath = "D:/Lead_Migration.xlsx";
//		String filePath1 = "D:/crm_client.csv";

//		String running = "D:/running_lead.xlsx";
//		String projectsFilePath = "D:/projects.xlsx";
//
//
//		helper.processLeadMigration(filePath,filePath1,projectsFilePath);
//
////		helper.savedRunningLead(running);
//	}̥


//	@Override
//	public void run(String... args) throws Exception {
//////		                                                                       
//		String filePath1 = "D:/crm_client.csv";
//		String running = "D:/running_lead.xlsx";
//		String projectsFilePath = "D:/projects.xlsx";
//		String badFit="D:/bad_fit.xlsx";
//̥
//		
//   
//		
//		helper.lead_migration(filePath1,projectsFilePath);
//
//		helper.savedRunningLead(running);
//
//		helper.processBadFitLeads(badFit);
//	}
	
//	@Override
//	public void run(String... args) throws Exception {
//		String file1 = "D:/test.xlsx";
//    
//		 
////		helper.updateCompanyMail(file1);
//	}
	
	
	@Override
	public void run(String... args) throws Exception {
////		                                                                       
		String filePath1 = "D:/crm_clienttest.csv";
//		 String filePath1 = "D:/clienttest.xlsx";

//		String running = "D:/running_lead.xlsx";
		String projectsFilePath = "D:/LeadDataUpload.xlsx";
//		String badFit="D:/bad_fit.xlsx";

		System.out.println("Test............."); 
		String categoryData = "D:/categoryData.xlsx";

//		 helper.lead_migration(filePath1,projectsFilePath);
		
//		 helper.lead_migrationV5(projectsFilePath);

//		helper.lead_migration(filePath1,projectsFilePath);̥

//		helper.savedRunningLead(running);
//
//		helper.industryV1(categoryData);
//		helper.industryV2(categoryData);
//		helper.industryV3(categoryData);
//		helper.industryV4(categoryData);
		

	}

}
  

       



