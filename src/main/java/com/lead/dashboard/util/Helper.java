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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
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
    private ProductRepo productRepo;

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

    private Map<String, String[]> crmClientData = new HashMap<>();



    public void processLeadMigration(String leadMigrationFilePath, String crmClientFilePath, String projects) {
        try (FileInputStream leadFile = new FileInputStream(leadMigrationFilePath);
             XSSFWorkbook leadWorkbook = new XSSFWorkbook(leadFile)) {

            // Read the CRM client data from the CSV file
            readCsvFile(crmClientFilePath);
            processProjectsFile(projects);


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

                if ("Deal won".equalsIgnoreCase(status)) {
                    String customerPhone = normalizePhoneNumber(getCellValueAsString(leadRow.getCell(customerPhoneIndex)).trim());
                    String customerEmail = getCellValueAsString(leadRow.getCell(customerEmailIndex)).trim().toLowerCase();

                    System.out.println("Searching for Phone: " + customerPhone + " and Email: " + customerEmail);

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

                        String companyName = crmClientRow[0];
                        Company existingCompanyOpt = companyRepository.findByName(companyName);

                        if (existingCompanyOpt!=null) {
                            System.out.println("Company with name " + companyName + " already exists. Skipping save.");
                        } else {
                            System.out.println("Matched Lead Migration Row: " + getRowAsString(leadRow));
                            System.out.println("Matched CRM Client Row: " + String.join(", ", crmClientRow));

                            Contact primaryContact = new Contact();
                            primaryContact.setName(crmClientRow[3] + " " + crmClientRow[4]);
                            primaryContact.setEmails(crmClientRow[6]);
                            primaryContact.setContactNo(crmClientRow[7]);

                            Contact savedContact = contactRepo.save(primaryContact);

                            Company company = new Company();
                            company.setName(companyName);
                            company.setAddress(crmClientRow[1]);
                            company.setCity(crmClientRow[2]);
                            company.setPrimaryContact(savedContact);

                            company.setPanNo(crmClientRow[8]);
                            company.setGstNo(crmClientRow[9]);
                            company.setState(crmClientRow[11]);
                            company.setCountry(crmClientRow[12]);

                            List<Lead> leads = new ArrayList<>();
                            leads.add(savedLead);
                            company.setCompanyLead(leads);

                            processProjectsFile(projects);


                            Company savedCompanyForm = companyRepository.save(company);

                            if (savedCompanyForm != null)
                            {
                                processProjectsFile(projects);

                                Project project = new Project();
//
//                                project.setCompany(savedCompanyForm);
                            }



                            System.out.println("CompanyForm created: " + savedCompanyForm);
                        }
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

    private void processProjectsFile(String projectsFilePath) {
        try (FileInputStream projectFile = new FileInputStream(projectsFilePath);
             XSSFWorkbook projectWorkbook = new XSSFWorkbook(projectFile)) {

            Sheet projectSheet = projectWorkbook.getSheetAt(0);
            Iterator<Row> projectRowIterator = projectSheet.iterator();

            // Read the header row
            Row projectHeaderRow = projectRowIterator.next();

            // Debugging: Print header columns to ensure correct reading
            System.out.println("Project Header Columns:");
            for (Cell cell : projectHeaderRow) {
                System.out.print(cell.getStringCellValue() + " | ");
            }
            System.out.println();

            // Iterate over each row in the project sheet
            while (projectRowIterator.hasNext()) {
                Row projectRow = projectRowIterator.next();

                // Debugging: Print the row number
                System.out.println("Processing Row: " + projectRow.getRowNum());

                String soldDate = getCellValueAsString(projectRow.getCell(0)).trim();
                String invoice = getCellValueAsString(projectRow.getCell(1)).trim();
                String estimateNo = getCellValueAsString(projectRow.getCell(2)).trim();
                String projectNo = getCellValueAsString(projectRow.getCell(3)).trim();
                String serviceName = getCellValueAsString(projectRow.getCell(4)).trim();
                String companyName = getCellValueAsString(projectRow.getCell(5)).trim();
                String contactName = getCellValueAsString(projectRow.getCell(6)).trim();
                String contactEmail = getCellValueAsString(projectRow.getCell(7)).trim();
                String contactMobileFirst = getCellValueAsString(projectRow.getCell(8)).trim();
                String contactMobileSecond = getCellValueAsString(projectRow.getCell(9)).trim();
                String soldBy = getCellValueAsString(projectRow.getCell(10)).trim();
                String deliveryDate = getCellValueAsString(projectRow.getCell(11)).trim();
                String gst = getCellValueAsString(projectRow.getCell(12)).trim();
                String address = getCellValueAsString(projectRow.getCell(13)).trim();

                // Print the read values
                System.out.println("Sold Date: " + soldDate + ", Invoice: " + invoice + ", Estimate No: " + estimateNo +
                        ", Project No: " + projectNo + ", Service Name: " + serviceName + ", Company: " + companyName +
                        ", Contact Name: " + contactName + ", Contact Email: " + contactEmail + ", Contact Mobile First: " + contactMobileFirst +
                        ", Contact Mobile Second: " + contactMobileSecond + ", Sold By: " + soldBy + ", Delivery Date: " + deliveryDate +
                        ", GST: " + gst + ", Address: " + address);
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing projects Excel file", e);
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

//    public void savedRunningLead(String runningFile) {
//        try (FileInputStream inputStream = new FileInputStream(runningFile);
//             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            DataFormatter dataFormatter = new DataFormatter();
//
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) {
//                    continue; // Skip header row
//                }
//
//                String leadName = dataFormatter.formatCellValue(row.getCell(0));
//                String customerName = dataFormatter.formatCellValue(row.getCell(1));
//                String phone = dataFormatter.formatCellValue(row.getCell(2));
//                String customerEmail = dataFormatter.formatCellValue(row.getCell(3)); // Adjust index if needed
//                String comments = dataFormatter.formatCellValue(row.getCell(4)); // Adjust index if needed
//
//                // Handle startDate safely
//                Date startDate = null;
//                Cell startDateCell = row.getCell(5); // Adjust index if needed
//                if (startDateCell != null) {
//                    if (startDateCell.getCellType() == CellType.STRING) {
//                        try {
//                            // Parse string to date if necessary
//                            String dateStr = startDateCell.getStringCellValue();
//                            startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
//                        } catch (ParseException e) {
//                            System.out.println("Date parsing error: " + e.getMessage());
//                        }
//                    } else if (startDateCell.getCellType() == CellType.NUMERIC) {
//                        startDate = startDateCell.getDateCellValue();
//                    }
//                }
//
//                String generatedFrom = dataFormatter.formatCellValue(row.getCell(6)); // Adjust index if needed
//                String userName = dataFormatter.formatCellValue(row.getCell(7)); // Adjust index if needed
//                String userEmail = dataFormatter.formatCellValue(row.getCell(8)); // Adjust index if needed
//                String statusName = dataFormatter.formatCellValue(row.getCell(9)); // Adjust index if needed
//
//                // Normalize phone number
//                if (phone.startsWith("91") && phone.length() > 10) {
//                    phone = phone.substring(2);
//                }
//
//                Status status = statusRepository.findByName(statusName);
//
//                if (status != null) {
//                    User assignTo = userRepo.findByemail(userEmail);
//
//                    if (assignTo != null) {
//                        Lead lead = new Lead();
//                        lead.setLeadName(leadName);
//                        lead.setName(customerName);
//                        lead.setMobileNo(phone);
//                        lead.setEmail(customerEmail);
//                        lead.setCreateDate(startDate);
//                        lead.setDisplayStatus("1"); // Adjust based on your requirements
//                        lead.setStatus(status);
//                        lead.setSource(generatedFrom);
//                        lead.setAssignee(assignTo);
//
//                        leadRepository.save(lead);
//                    } else {
//                        System.out.println("User not found for email: " + userEmail);
//                    }
//                } else {
//                    System.out.println("Status not found for: " + statusName);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}


