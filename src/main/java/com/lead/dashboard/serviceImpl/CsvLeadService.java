package com.lead.dashboard.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.lead.dashboard.config.AwsConfig;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.UserRecord;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.repository.ClientRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.RemarkRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;
import com.sun.mail.iap.ResponseInputStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CsvLeadService {

	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	UserRepo userRepo;

	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	RemarkRepository remarkRepository;

	@Autowired
	ClientRepository  clientRepository;

	@Autowired
	private AwsConfig awsConfig;

	@Value("${aws.s3.bucket-name}")
	private String awsBucketName;

	@Value("${aws.accessKey}")
	private String accessKey;

	@Value("${aws.secretKey}")
	private String secretKey;

	@Value("${aws_path}")
	private String s3BaseUrl;

	@Autowired
	private AmazonS3 amazonS3Client; //

	public List<Lead> processCsvFileFromPath(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			return parseCsv(reader);
		} catch (IOException e) {
			throw new RuntimeException("Error reading local CSV file: " + e.getMessage());
		}
	}

	public List<Lead> processCsvFileFromS3(String s3Url) {
		try {
			if (!s3Url.startsWith(s3BaseUrl)) {
				throw new RuntimeException("Invalid S3 URL: " + s3Url);
			}

			String fileKey = s3Url.replace(s3BaseUrl, "");

			S3Object s3Object = amazonS3Client.getObject(awsBucketName, fileKey);

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8))) {
				return parseCsv(reader);
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading CSV file from S3: " + e.getMessage());
		}
	}

	private List<Lead> parseCsv(BufferedReader reader) throws IOException {
		List<Lead> leads = new ArrayList<>();
		CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

		for (CSVRecord record : csvParser) {
			Lead lead = new Lead();

			lead.setName(record.get("Client Name"));
			lead.setLeadName(record.get("Lead Name"));
			lead.setLeadDescription(record.get("Lead Description"));
			lead.setMobileNo(record.get("Mobile No"));
			lead.setEmail(record.get("Email"));
			lead.setSource(record.get("Source"));
			List<Client>cList=new ArrayList<>();
			if(record.get("Mobile No")!=null || record.get("Email")!=null){
				Client c1=new Client();
				c1.setContactNo(record.get("Mobile No"));
				c1.setEmails(record.get("Email"));
				c1.setName(record.get("Client Name"));
				c1.setPrimary(true);
				clientRepository.save(c1);
				cList.add(c1);
			}

			if((record.get("Smobile No")!=null && record.get("Smobile No").equals("")) && ( record.get("Semail")!=null && (!record.get("Semail").equals("")))) {
				Client c2 = new Client();
				c2.setContactNo(record.get("Smobile No"));
				c2.setEmails(record.get("Semail"));
				c2.setPrimary(false);
				clientRepository.save(c2);
				cList.add(c2);
				lead.setClients(cList);
			}
			String stage=record.get("stage");
			if(stage!=null) {
				Status status = statusRepository.findByName(stage);
				if(status!=null) {
					lead.setStatus(status);
				}else {
					status = statusRepository.findByName("New");
					lead.setStatus(status);
				}
			}else {
				Status status = statusRepository.findByName("New");
				lead.setStatus(status);
			}
			String comment=record.get("comment");
			if(comment!=null && (!comment.equals(""))) {
				User a = userRepo.findByemail("navjot.singh@corpseed.com");
                List<Remark>remarks=new ArrayList<>();
				Remark remark=new Remark();
				remark.setMessage(comment);
				remark.setLatestUpdated(new Date());
				remark.setUpdatedBy(a);
				remark.setType("Other");
				remarkRepository.save(remark);
				remarks.add(remark);
				lead.setRemarks(remarks);
			}
			//            Status status = statusRepository.findByName("New");
			String assigneeEmail = record.get("assignee")!=null?record.get("assignee").toString():null;
			if(assigneeEmail!=null) {
				User assignee = userRepo.findByemail(assigneeEmail);
				if(assignee!=null) {
					lead.setAssignee(assignee);

				}else {

					User a = userRepo.findByemail("navjot.singh@corpseed.com");


					lead.setAssignee(a);
				}
			}else {
				User a = userRepo.findByemail("navjot.singh@corpseed.com");
				lead.setAssignee(a);
			}
			String cStatus = record.get("cStatus");
			String company = record.get("company");
			lead.setUrls(company+" "+cStatus);
			lead.setPrimaryAddress(record.get("Primary Address"));
			lead.setCity(record.get("City"));
			lead.setCategoryId(record.get("Category Id"));
			lead.setServiceId(record.get("Service Id"));
			lead.setIndustryId(record.get("Industry Id"));
			lead.setIpAddress(record.get("IP Address"));
			leadRepository.save(lead);
			leads.add(lead);
		}

		return leads;
	}
}
