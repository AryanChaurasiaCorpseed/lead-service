package com.lead.dashboard.util;

import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.controller.companyController.CompanyController;
import com.lead.dashboard.domain.*;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.serviceImpl.LeadServiceImpl;
import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class Helper {

    @Autowired
    private CompanyController companyController;

    @Autowired
    LeadRepository leadRepository;
    @Autowired
    MailSendSerivceImpl mailSendSerivceImpl;
    @Autowired
    CommonServices commonServices;

    @Autowired
    SlugRepository slugRepository;
    @Autowired

    ContactRepo contactRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    LeadHistoryRepository leadHistoryRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ServiceDetailsRepository serviceDetailsRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    CompanyFormRepo companyFormRepo;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UrlsManagmentRepo urlsManagmentRepo;

    @Autowired
    LeadServiceImpl leadService;


    private List<Map<String, String>> readProjectFile(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            Row headerRow = rowIterator.next();
            Map<Integer, String> headers = new HashMap<>();
            for (Cell cell : headerRow) {
                headers.put(cell.getColumnIndex(), cell.getStringCellValue());
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, String> rowData = new HashMap<>();
                for (Cell cell : row) {
                    String header = headers.get(cell.getColumnIndex());
                    String value = getCellValueAsString(cell);
                    rowData.put(header, value);
                }
                data.add(rowData);
            }
        }
        return data;
    }

    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Convert numeric value to string without scientific notation
                    BigDecimal numericValue = BigDecimal.valueOf(cell.getNumericCellValue());
                    return numericValue.toPlainString();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                switch (cell.getCachedFormulaResultType()) {
                    case STRING:
                        return cell.getStringCellValue();
                    case NUMERIC:
                        BigDecimal numericValue = BigDecimal.valueOf(cell.getNumericCellValue());
                        return numericValue.toPlainString();
                    default:
                        return "";
                }
            default:
                return "";
        }
    }


    public void updateCompanyMail(String filePath) {
        try {
            List<Map<String, String>> companyData = readProjectFile(filePath);

            for (Map<String, String> row : companyData) {
                String companyName = row.get("cregname").trim();
                String emailId = row.get("cregcontemailid").trim();

                if (companyName.isEmpty() || emailId.isEmpty()) {
                    continue;
                }
                Company company = companyRepository.findByName(companyName);
                if (company != null) {
                    Contact primaryContact = company.getPrimaryContact();
                    if (primaryContact != null) {
                        primaryContact.setEmails(emailId);
                        contactRepo.save(primaryContact);
                    }


                    System.out.println("Updated company: " + companyName + " with email: " + emailId);
                } else {
                    System.out.println("Company not found: " + companyName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


