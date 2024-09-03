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

    @Transactional
    public void lead_migration(String crmClientFilePath, String projectsFilePath) {
        try {
            List<Map<String, String>> crmClientData = readCsvFile(crmClientFilePath);
            List<Map<String, String>> projectSheetData = readProjectFile(projectsFilePath);

            for (Map<String, String> crmClientRow : crmClientData) {
                String crmClientName = crmClientRow.get("cregname");
                Company m = companyRepository.findByName(crmClientName);

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
                    System.out.println("Project....................+");

                    Company comp = companyRepository.findByName(projectName);
//                    System.out.println("Comppppppppppppppppppppppppppppppp+"+comp.getName());

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

//                        Set<Lead>lSet = new HashSet<>(comp.getCompanyLead());
//                        lSet.add(savedLead);
                        if (comp.getCompanyLead() != null) {
                            Set<Lead> lSet = new HashSet<>(comp.getCompanyLead());
                            lSet.add(savedLead);
                            List<Lead> leadList = new ArrayList<>(lSet);
                            comp.setCompanyLead(leadList);

                        } else {

                        }

                        List<Lead> leadList = new ArrayList<>();
                        leadList.add(savedLead);

                        System.out.println("Lead Datat  ...=============================       " + comp.getName());
                        comp.setCompanyLead(leadList);


                        companyRepository.save(comp);
                        System.out.println("Lead created: " + savedLead);
                        System.out.println("Lead Datat  ...======Test=======================       " + comp.getName());

                        String projectNumber = projectRow.get("Project_No");
//                        Project existingProject = projectRepository.findByProjectNo(projectNumber);
                        List<Project> existingProject = projectRepository.findAllByProjectNo(projectNumber);
                        if (existingProject != null && existingProject.size() == 0) {
                            Project project = new Project();
                            project.setCompany(existingCompany);
                            project.setProjectNo(projectNumber);
                            project.setName(projectRow.get("Service_Name"));
                            project.setLead(savedLead);

                            Project savedProjectData = projectRepository.save(project);

//                            List<Project>pList = new ArrayList<>();
//                            pList.add(savedProjectData);
                            if (comp.getCompanyProject() != null) {
                                Set<Project> pset = new HashSet<>(comp.getCompanyProject());
                                pset.add(savedProjectData);
                                List<Project> pList = new ArrayList<>(pset);
                                comp.setCompanyProject(pList);

                            } else {
                                List<Project> pList = new ArrayList<>();
                                pList.add(savedProjectData);
                                comp.setCompanyProject(pList);

                            }
//                            Set<Project>pset = new HashSet<>(comp.getCompanyProject());
//                            pset.add(savedProjectData);
//                            List<Project>pList = new ArrayList<>(pset);

//                            comp.setCompanyProject(pList);
                            companyRepository.save(comp);
//                            System.out.println("Project created: " + savedProjectData);
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


    @Transactional
    public void lead_migrationV2(String crmClientFilePath, String projectsFilePath) {
        try {
            List<Map<String, String>> crmClientData = readCsvFile(crmClientFilePath);
            List<Map<String, String>> projectSheetData = readProjectFile(projectsFilePath);

            System.out.println(projectSheetData.size());
            for (Map<String, String> projectRow : projectSheetData) {

                String projectName = projectRow.get("Company");
                System.out.println("Project....................+");

                Company comp = companyRepository.findByName(projectName);
                if (comp != null) {

                    if (projectName == null || projectName.trim().isEmpty()) {
                        System.out.println("Warning: Project name is null or empty.");
                        continue;
                    }

                    if (true) {
                        String status = "Deal won";

                        if (projectRow.get("Service_Name") == null || projectRow.get("Contact_Mobile_First") == null ||
                                projectRow.get("Contact_Email_First") == null) {
                            System.out.println("Warning: Incomplete project data for project " + projectRow.get("Project_No"));
                            continue;
                        }

                        LeadDTO leadDTO = new LeadDTO();
                        leadDTO.setLeadName(projectRow.get("Service_Name"));
//                        leadDTO.setName(crmClientRow.get("cregcontfirstname") + " " + crmClientRow.get("cregcontlastname"));
                        leadDTO.setMobileNo(projectRow.get("Contact_Mobile_First"));
                        leadDTO.setEmail(projectRow.get("Contact_Email_First"));
                        leadDTO.setLeadDescription(projectRow.get("Service_Name"));
                        leadDTO.setCreatedById(1L);
                        leadDTO.setDisplayStatus(status);
                        leadDTO.setSource("Corpseed HO");
                        Lead savedLead = leadService.createLeadViaSheet(leadDTO);

                        if (comp != null && comp.getCompanyLead() != null) {
                            Set<Lead> lSet = new HashSet<>(comp.getCompanyLead());
                            lSet.add(savedLead);
                            List<Lead> leadList = new ArrayList<>(lSet);
                            comp.setCompanyLead(leadList);

                        } else {

                        }

                        List<Lead> leadList = new ArrayList<>();
                        leadList.add(savedLead);

                        System.out.println("Lead Datat  ...=============================       " + comp.getName());
                        comp.setCompanyLead(leadList);


                        companyRepository.save(comp);
                        System.out.println("Lead created: " + savedLead);
                        System.out.println("Lead Datat  ...======Test=======================       " + comp.getName());

                        String projectNumber = projectRow.get("Project_No");
//                        Project existingProject = projectRepository.findByProjectNo(projectNumber);
                        List<Project> existingProject = projectRepository.findAllByProjectNo(projectNumber);
                        if (existingProject != null && existingProject.size() == 0) {
                            Project project = new Project();
//                            project.setCompany(existingCompany);
                            project.setProjectNo(projectNumber);
                            project.setName(projectRow.get("Service_Name"));
                            project.setLead(savedLead);

                            Project savedProjectData = projectRepository.save(project);

//                            List<Project>pList = new ArrayList<>();
//                            pList.add(savedProjectData);
                            if (comp.getCompanyProject() != null) {
                                Set<Project> pset = new HashSet<>(comp.getCompanyProject());
                                pset.add(savedProjectData);
                                List<Project> pList = new ArrayList<>(pset);
                                comp.setCompanyProject(pList);

                            } else {
                                List<Project> pList = new ArrayList<>();
                                pList.add(savedProjectData);
                                comp.setCompanyProject(pList);

                            }

                            companyRepository.save(comp);
                        } else {
                            System.out.println("Warning: Project with number " + projectNumber + " already exists.");
                        }
                    } else {
                        System.out.println("Warning: Project name does not match CRM client name.");
                    }
                }
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing files", e);
        }
    }

    public void companyAssignee(String companyAssignee) {
        try {
            List<Map<String, String>> projectSheetData = readProjectFile(companyAssignee);

            if (projectSheetData == null) {
                throw new RuntimeException("No data found in project file");
            }

            for (Map<String, String> companyRow : projectSheetData) {
                String companyName = companyRow.get("name");
                Company company = companyRepository.findByName(companyName);

                if (company != null) {
                    String assigneeEmail = companyRow.get("sales person");

                    if (assigneeEmail != null && !assigneeEmail.isEmpty()) {
                        User assignee = userRepo.findByemail(assigneeEmail);

                        if (assignee != null) {
                            company.setAssignee(assignee);
                            companyRepository.save(company);
                        } else {
                            System.err.println("No user found with email: " + assigneeEmail);
                        }
                    } else {
                        System.err.println("Assignee email is missing or empty for company: " + companyName);
                    }
                } else {
                    System.err.println("No company found with name: " + companyName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing files", e);
        }
    }


    public void beforeCompany(String before2023) {
        try {
            List<Map<String, String>> leadCompanyData = readProjectFile(before2023);

            if (leadCompanyData == null) {
                throw new RuntimeException("Lead company data is null");
            }

            for (Map<String, String> companyRow : leadCompanyData) {
                String companyName = companyRow.get("cregname");
                Company company = companyRepository.findByName(companyName);

                if (company == null) {
                    String assigneeEmail = companyRow.get("assign_to");

                    if (assigneeEmail != null && !assigneeEmail.isEmpty()) {
                        User assignee = userRepo.findByemail(assigneeEmail);

                        LeadDTO leadDTO = new LeadDTO();

                        leadDTO.setLeadName(companyRow.get("name"));
                        leadDTO.setName(companyRow.get("cregcontfirstname"));
                        leadDTO.setMobileNo(companyRow.get("cregcontmobile"));
                        leadDTO.setEmail(companyRow.get("cregcontemailid"));
                        leadDTO.setPrimaryAddress(companyRow.get("cregaddress"));
                        leadDTO.setCity(companyRow.get("creglocation"));
                        leadDTO.setSource("Corpseed HO");

                        if (assignee != null) {
                            leadDTO.setAssigneeId(assignee.getId());
                        }

                        Lead savedLead = leadService.createLeadV2(leadDTO);

                        Company newCompany = new Company();
                        newCompany.setName(companyName);
                        newCompany.setAddress(companyRow.get("cregaddress"));
                        newCompany.setState(companyRow.get("creglocation"));

                        Contact primaryContact = new Contact();
                        primaryContact.setName(companyRow.get("cregcontfirstname") + " " + companyRow.get("cregcontlastname"));
                        primaryContact.setEmails(companyRow.get("cregcontemailid"));
                        primaryContact.setContactNo(companyRow.get("cregcontmobile"));

                        Contact savedContact = contactRepo.save(primaryContact);

                        newCompany.setPanNo(companyRow.get("cregpan"));
                        newCompany.setGstNo(companyRow.get("creggstin"));
                        newCompany.setCountry(companyRow.get("cregcountry"));
                        newCompany.setState(companyRow.get("cregstate"));
                        newCompany.setPrimaryContact(savedContact);
                        newCompany.setAssignee(assignee);

                        newCompany.setCompanyLead(List.of(savedLead));

                        companyRepository.save(newCompany);

                        System.out.println("Company and lead created successfully: " + companyName);
                    } else {
                        System.err.println("Assignee email is missing or empty for company: " + companyName);
                    }
                } else {
                    System.out.println("Company already exists: " + companyName + ". Skipping lead creation.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing files", e);
        }
    }


}

