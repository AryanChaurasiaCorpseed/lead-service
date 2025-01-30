package com.lead.dashboard.serviceImpl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.lead.dashboard.config.AwsConfig;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.StatusRepository;
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
import java.util.List;

@Service
public class CsvLeadService {

    @Autowired
    private LeadRepository leadRepository;
    
    @Autowired
    StatusRepository statusRepository;

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
            Status status = statusRepository.findByName("New");
            lead.setStatus(status);
            
            lead.setPrimaryAddress(record.get("Primary Address"));
            lead.setCity(record.get("City"));
            lead.setCategoryId(record.get("Category Id"));
            lead.setServiceId(record.get("Service Id"));
            lead.setIndustryId(record.get("Industry Id"));
            lead.setIpAddress(record.get("IP Address"));

            leads.add(lead);
        }

        return leads;
    }
}
