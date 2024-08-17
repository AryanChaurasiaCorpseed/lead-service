package com.lead.dashboard.util;

import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.controller.companyController.CompanyFormController;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.dto.CreateFormDto;
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


            readCsvFile(crmClientFilePath);


            Sheet leadSheet = leadWorkbook.getSheetAt(0);
            Iterator<Row> leadRowIterator = leadSheet.iterator();


            Row leadHeaderRow = leadRowIterator.next();

            int leadNameIndex = -1;
            int customerNameIndex = -1;
            int customerPhoneIndex = -1;
            int customerEmailIndex = -1;
            int statusIndex = -1;


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
                    if (crmClientRow != null) {
                        // Print the matching rows from both lead_migration and crm_client
                        System.out.println("Matched Lead Migration Row: " + getRowAsString(leadRow));
                        System.out.println("Matched CRM Client Row: " + String.join(", ", crmClientRow));
                    } else {
                        System.out.println("No match found for Phone: " + customerPhone + " or Email: " + customerEmail);
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

}