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


    public void lead_migration(String crmClientFilePath, String projectsFilePath) {
        try {
            List<Map<String, String>> crmClientData = readCsvFile(crmClientFilePath);
            List<Map<String, String>> projectSheetData = readProjectFile(projectsFilePath);

            for (Map<String, String> crmClientRow : crmClientData) {
                String crmClientName = crmClientRow.get("cregname");

                if (crmClientName == null || crmClientName.trim().isEmpty()) {
                    System.out.println("Warning: CRM client name is null or empty.");
                    continue;
                }

                Company existingCompany = companyRepository.findByName(crmClientName);

                if (existingCompany == null) {
                    if (crmClientRow.get("cregcontfirstname") == null || crmClientRow.get("cregcontlastname") == null ||
                            crmClientRow.get("cregcontemailid") == null || crmClientRow.get("cregcontmobile") == null) {
                        System.out.println("Warning: Incomplete CRM client data for " + crmClientName);
                        continue;
                    }

                    Contact primaryContact = new Contact();
                    primaryContact.setName(crmClientRow.get("cregcontfirstname") + " " + crmClientRow.get("cregcontlastname"));
                    primaryContact.setEmails(crmClientRow.get("cregcontemailid"));
                    primaryContact.setContactNo(crmClientRow.get("cregcontmobile"));

                    Contact savedContact = contactRepo.save(primaryContact);

                    String assigneeIdentifier = crmClientRow.get("assign_to");

                    User assignee = userRepo.findByemail(assigneeIdentifier);

                    Company company = new Company();
                    company.setName(crmClientName);
                    company.setAddress(crmClientRow.get("cregaddress"));
                    company.setCity(crmClientRow.get("creglocation"));
                    company.setPrimaryContact(savedContact);
                    company.setPanNo(crmClientRow.get("cregpan"));
                    company.setGstNo(crmClientRow.get("creggstin"));
                    company.setState(crmClientRow.get("cregstate"));
                    company.setCountry(crmClientRow.get("cregcountry"));

                    if (assignee != null) {
                        company.setAssignee(assignee);
                    } else {
                        System.out.println("Warning: User with identifier " + assigneeIdentifier + " not found.");
                    }
                    existingCompany = companyRepository.save(company);
                    System.out.println("Company created: " + existingCompany);
                }

                for (Map<String, String> projectRow : projectSheetData) {
                    String projectName = projectRow.get("Company");

                    if (projectName == null || projectName.trim().isEmpty()) {
                        System.out.println("Warning: Project name is null or empty.");
                        continue;
                    }

                    if (projectName.equalsIgnoreCase(crmClientName)) {
                        String status = "Deal won";

                        if (projectRow.get("Service_Name") == null || projectRow.get("Contact_Mobile_First") == null ||
                                projectRow.get("Contact_Email_First") == null) {
                            System.out.println("Warning: Incomplete project data for project " + projectRow.get("Project_No"));
                            continue;
                        }

                        LeadDTO leadDTO = new LeadDTO();
                        leadDTO.setLeadName(projectRow.get("Service_Name"));
                        leadDTO.setName(crmClientRow.get("cregcontfirstname") + " " + crmClientRow.get("cregcontlastname"));
                        leadDTO.setMobileNo(projectRow.get("Contact_Mobile_First"));
                        leadDTO.setEmail(projectRow.get("Contact_Email_First"));
                        leadDTO.setLeadDescription(projectRow.get("Service_Name"));
                        leadDTO.setCreatedById(1L);
                        leadDTO.setDisplayStatus(status);
                        leadDTO.setSource("Corpseed HO");

                        Lead savedLead = leadService.createLeadViaSheet(leadDTO);
                        System.out.println("Lead created: " + savedLead);

                        String projectNumber = projectRow.get("Project_No");
                        Project existingProject = projectRepository.findByProjectNo(projectNumber);

                        if (existingProject == null) {
                            Project project = new Project();
                            project.setCompany(existingCompany);
                            project.setProjectNo(projectNumber);
                            project.setName(projectRow.get("Service_Name"));
                            project.setLead(savedLead);

                            Project savedProjectData = projectRepository.save(project);
                            System.out.println("Project created: " + savedProjectData);
                        } else {
                            System.out.println("Warning: Project with number " + projectNumber + " already exists.");
                        }
                    } else {
                        System.out.println("Warning: Project name does not match CRM client name.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing files", e);
        }
    }


    public List<Map<String, String>> readCsvFile(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] headers = reader.readNext();
            if (headers == null) {
                throw new IOException("Empty CSV file");
            }
            String[] values;
            while ((values = reader.readNext()) != null) {
                Map<String, String> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], i < values.length ? values[i] : "");
                }
                data.add(row);
            }
        } catch (CsvValidationException e) {
            throw new IOException("CSV validation error", e);
        }
        return data;
    }

    private List<Map<String, String>> readProjectFile(String filePath) throws IOException {
        List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            // Read header row
            Row headerRow = rowIterator.next();
            Map<Integer, String> headers = new HashMap<>();
            for (Cell cell : headerRow) {
                headers.put(cell.getColumnIndex(), cell.getStringCellValue());
            }

            // Read data rows
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


    public void processBadFitLeads(String filePath) {
        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                LeadDTO leadDTO = new LeadDTO();
                leadDTO.setLeadName(getCellValue(row.getCell(1)));
                leadDTO.setName(getCellValue(row.getCell(2)));
                leadDTO.setCreateDate(parseDate(getCellValue(row.getCell(3)))); // Start date

                String mobileNo = getCellValue(row.getCell(4));
                leadDTO.setMobileNo(formatMobileNumber(mobileNo));

                String email = getCellValue(row.getCell(5));
                leadDTO.setEmail(email.isEmpty() ? null : email);

                leadService.createBadFitLead(leadDTO);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()); // Format date as needed
                } else {
                    // Convert numeric cell value to string to avoid scientific notation
                    return String.format("%.0f", cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String formatMobileNumber(String mobileNo) {

        mobileNo = mobileNo.replaceAll("[^\\d]", "");

        // Check if the length is more than 10 digits and starts with country code
        if (mobileNo.length() > 10 && mobileNo.startsWith("91")) {
            mobileNo = mobileNo.substring(2); // Remove country code '91'
        }

        // Ensure the mobile number is exactly 10 digits
        return mobileNo.length() > 10 ? mobileNo.substring(0, 10) : (mobileNo.length() == 10 ? mobileNo : "");
    }

}


