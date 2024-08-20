package com.lead.dashboard.util;

import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.controller.companyController.CompanyFormController;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.repository.product.ProductRepo;
import com.lead.dashboard.serviceImpl.LeadServiceImpl;
import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import io.micrometer.core.instrument.MultiGauge;
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
    private CompanyFormController companyFormController;

    @Autowired
    LeadRepository leadRepository;
    @Autowired
    MailSendSerivceImpl mailSendSerivceImpl;
    @Autowired
    CommonServices commonServices;

    @Autowired
    SlugRepository slugRepository;
    @Autowired
    UserRepo userRepo;

    @Autowired
    LeadHistoryRepository leadHistoryRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ServiceDetailsRepository serviceDetailsRepository;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UrlsManagmentRepo urlsManagmentRepo;

    @Autowired
    LeadServiceImpl leadService;

    private Map<String, String[]> crmClientData = new HashMap<>();



    public void processLeadMigration(String leadMigrationFilePath, String crmClientFilePath) {
        try (FileInputStream leadFile = new FileInputStream(leadMigrationFilePath);
             XSSFWorkbook leadWorkbook = new XSSFWorkbook(leadFile)) {

            // Read the CRM client data from the CSV file
            readCsvFile(crmClientFilePath);

            Sheet leadSheet = leadWorkbook.getSheetAt(0);
            Iterator<Row> leadRowIterator = leadSheet.iterator();

            // Read the header row and determine the indices of the necessary columns
            Row leadHeaderRow = leadRowIterator.next();

            int leadNameIndex = -1;
            int customerNameIndex = -1;
            int customerPhoneIndex = -1;
            int customerEmailIndex = -1;
            int statusIndex = -1;
            int createdDateIndex = -1;
            int generatedByIndex = -1;
            int commentsIndex = -1;

            for (Cell cell : leadHeaderRow) {
                String columnName = cell.getStringCellValue().trim().toUpperCase();
                switch (columnName) {
                    case "LEAD_NAME":
                        leadNameIndex = cell.getColumnIndex();
                        break;
                    case "CUSTOMER_NAME":
                        customerNameIndex = cell.getColumnIndex();
                        break;
                    case "CUSTOMER_PHONE":
                        customerPhoneIndex = cell.getColumnIndex();
                        break;
                    case "CUSTOMER-EMAIL":
                        customerEmailIndex = cell.getColumnIndex();
                        break;
                    case "COMMENTS":
                        commentsIndex = cell.getColumnIndex();
                        break;
                    case "CREATED_DATE":
                        createdDateIndex = cell.getColumnIndex();
                        break;
                    case "GENERATED_BY":
                        generatedByIndex = cell.getColumnIndex();
                        break;
                    case "STATUS":
                        statusIndex = cell.getColumnIndex();
                        break;
                    default:
                        System.out.println("Unknown column: " + columnName);
                        break;
                }
            }

            if (leadNameIndex == -1 || customerNameIndex == -1 || customerPhoneIndex == -1 || customerEmailIndex == -1 || statusIndex == -1) {
                throw new IllegalArgumentException("One or more required columns not found");
            }

            while (leadRowIterator.hasNext()) {
                Row leadRow = leadRowIterator.next();
                String status = getCellValueAsString(leadRow.getCell(statusIndex)).trim();

                // Only process leads with a "Deal won" status
                if ("Deal won".equalsIgnoreCase(status)) {
                    String customerPhone = normalizePhoneNumber(getCellValueAsString(leadRow.getCell(customerPhoneIndex)).trim());
                    String customerEmail = getCellValueAsString(leadRow.getCell(customerEmailIndex)).trim().toLowerCase();

                    System.out.println("Searching for Phone: " + customerPhone + " and Email: " + customerEmail);

                    // Try to match the lead data with the CRM client data
                    String[] crmClientRow = crmClientData.get(customerPhone + "_" + customerEmail);

                    // Create LeadDTO and save the lead data
                    LeadDTO leadDTO = new LeadDTO();
                    leadDTO.setLeadName(getCellValueAsString(leadRow.getCell(leadNameIndex)));
                    leadDTO.setName(getCellValueAsString(leadRow.getCell(customerNameIndex)));
                    leadDTO.setMobileNo(customerPhone);
                    leadDTO.setEmail(customerEmail);
                    leadDTO.setLeadDescription(getCellValueAsString(leadRow.getCell(commentsIndex)));
                    leadDTO.setCreatedById(1L);
//                    leadDTO.setSource(getCellValueAsString(leadRow.getCell(generatedByIndex)));
                    leadDTO.setDisplayStatus(status);

                    Lead savedLead = leadService.createLeadViaSheet(leadDTO);
                    System.out.println("Lead created: " + savedLead);

                    if (crmClientRow != null) {
                        // Print the matching rows from both lead_migration and crm_client
                        System.out.println("Matched Lead Migration Row: " + getRowAsString(leadRow));
                        System.out.println("Matched CRM Client Row: " + String.join(", ", crmClientRow));

                        CreateFormDto companyRequestData = new CreateFormDto();

                        companyRequestData.setCompanyName(crmClientRow[0]);
                        companyRequestData.setIsPresent(true);
                        companyRequestData.setAssigneeId(1L);
                        companyRequestData.setUpdatedBy(1L);
                        companyRequestData.setLeadId(savedLead.getId());

                        CompanyForm savedCompanyForm = companyFormController.createCompanyForm(companyRequestData);
                        System.out.println("CompanyForm created: " + savedCompanyForm);
                    } else {
                        System.out.println("No matching CRM client found for Phone: " + customerPhone + " or Email: " + customerEmail);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing Excel file", e);
        }
    }


    private String normalizePhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("\\D+", "");

        if (phoneNumber.length() > 10) {
            if (phoneNumber.startsWith("91")) {
                return phoneNumber.substring(phoneNumber.length() - 10);
            }
        }


        return phoneNumber.length() == 10 ? phoneNumber : "";
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return sdf.format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }


    private String getRowAsString(Row row) {
        StringBuilder sb = new StringBuilder();
        for (Cell cell : row) {
            sb.append(getCellValueAsString(cell)).append(", ");
        }
        return sb.toString();
    }

    // Read CSV file and populate crmClientData map
    private void readCsvFile(String csvFilePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] header = csvReader.readNext(); // Read header
            if (header != null) {
                System.out.println("Header: " + String.join(", ", header));
            }

            String[] values;
            while ((values = csvReader.readNext()) != null) {
                String phone = values[headerIndex("cregcontmobile", header)];
                String email = values[headerIndex("cregcontemailid", header)];
                crmClientData.put(normalizePhoneNumber(phone) + "_" + email.toLowerCase(), values);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            // Handle exceptions
        }
    }

    private int headerIndex(String columnName, String[] header) {
        for (int i = 0; i < header.length; i++) {
            if (header[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Column not found: " + columnName);
    }

    public void savedRunningLead(String runningFile) {
        try (FileInputStream inputStream = new FileInputStream(runningFile);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header row
                }

                String leadName = dataFormatter.formatCellValue(row.getCell(0));
                String customerName = dataFormatter.formatCellValue(row.getCell(1));
                String phone = dataFormatter.formatCellValue(row.getCell(2));
                String customerEmail = dataFormatter.formatCellValue(row.getCell(3)); // Adjust index if needed
                String comments = dataFormatter.formatCellValue(row.getCell(4)); // Adjust index if needed

                // Handle startDate safely
                Date startDate = null;
                Cell startDateCell = row.getCell(5); // Adjust index if needed
                if (startDateCell != null) {
                    if (startDateCell.getCellType() == CellType.STRING) {
                        try {
                            // Parse string to date if necessary
                            String dateStr = startDateCell.getStringCellValue();
                            startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
                        } catch (ParseException e) {
                            System.out.println("Date parsing error: " + e.getMessage());
                        }
                    } else if (startDateCell.getCellType() == CellType.NUMERIC) {
                        startDate = startDateCell.getDateCellValue();
                    }
                }

                String generatedFrom = dataFormatter.formatCellValue(row.getCell(6)); // Adjust index if needed
                String userName = dataFormatter.formatCellValue(row.getCell(7)); // Adjust index if needed
                String userEmail = dataFormatter.formatCellValue(row.getCell(8)); // Adjust index if needed
                String statusName = dataFormatter.formatCellValue(row.getCell(9)); // Adjust index if needed

                // Normalize phone number
                if (phone.startsWith("91") && phone.length() > 10) {
                    phone = phone.substring(2);
                }

                Status status = statusRepository.findByName(statusName);

                if (status != null) {
                    User assignTo = userRepo.findByemail(userEmail);

                    if (assignTo != null) {
                        Lead lead = new Lead();
                        lead.setLeadName(leadName);
                        lead.setName(customerName);
                        lead.setMobileNo(phone);
                        lead.setEmail(customerEmail);
                        lead.setCreateDate(startDate);
                        lead.setDisplayStatus("1"); // Adjust based on your requirements
                        lead.setStatus(status);
                        lead.setSource(generatedFrom);
                        lead.setAssignee(assignTo);

                        leadRepository.save(lead);
                    } else {
                        System.out.println("User not found for email: " + userEmail);
                    }
                } else {
                    System.out.println("Status not found for: " + statusName);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


