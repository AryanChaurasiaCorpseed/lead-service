package com.lead.dashboard.util;

import com.lead.dashboard.config.CommonServices;
import com.lead.dashboard.controller.companyController.CompanyFormController;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.dto.CreateFormDto;
import com.lead.dashboard.repository.*;
import com.lead.dashboard.repository.product.ProductRepo;
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

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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




//    public void processCsvFile(String filePath) {
//        try (FileReader filereader = new FileReader(filePath);
//             CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(new CSVParser()).build()) {
//
//            String[] nextLine;
//            while ((nextLine = csvReader.readNext()) != null) {
//                CreateFormDto companyRequestData = new CreateFormDto();
//                companyRequestData.setCompanyName(nextLine[0]);
//                companyRequestData.setAddress(nextLine[1]);
//                companyRequestData.setCity(nextLine[2]);
//                companyRequestData.setContactName(nextLine[3] + nextLine[4]);
//                companyRequestData.setContactEmails(nextLine[5]);
//                companyRequestData.setContactNo(nextLine[6]);
//                companyRequestData.setPanNo(nextLine[7]);
//                companyRequestData.setGstNo(nextLine[8]);
//                companyRequestData.setAddedByInOldCrm(nextLine[9]);
//                companyRequestData.setState(nextLine[10]);
//                companyRequestData.setCountry(nextLine[11]);
//                companyRequestData.setIsPresent(true);
//                companyRequestData.setAssigneeId(1L);
//                companyRequestData.setUpdatedBy(1L);
//                companyRequestData.setLeadId(1L);
//
//                CompanyForm savedData= companyFormController.createCompanyForm(companyRequestData);
//
//                System.out.println(savedData+ "Saved Succesfully");
//            }
//
//        } catch (IOException | CsvValidationException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Error processing CSV file", e);
//        }
//    }


    public void processLeadMigration(String filePath, String crmClientFilePath) {
        Set<String> phoneNumbers = readPhoneNumbersFromCsv(crmClientFilePath);



        try (FileInputStream file = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {  // XSSFWorkbook implements Closeable

            Sheet sheet = workbook.getSheetAt(0); // Get the first sheet
            Iterator<Row> rowIterator = sheet.iterator();

            Row headerRow = rowIterator.next();  // Read the header line

            int leadNameIndex = -1;
            int customerNameIndex = -1;
            int customerPhoneIndex = -1;
            int customerEmailIndex = -1;
            int statusIndex = -1;

            for (Cell cell : headerRow) {
                String columnName = cell.getStringCellValue();
                switch (columnName.toUpperCase()) {
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
                }
            }

            if (leadNameIndex == -1 || customerNameIndex == -1 || customerPhoneIndex == -1 || customerEmailIndex == -1 || statusIndex == -1) {
                throw new IllegalArgumentException("One or more required columns not found");
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String status = getCellValueAsString(row.getCell(statusIndex));
                if ("Deal won".equalsIgnoreCase(status)) {
                    String customerPhone = getCellValueAsString(row.getCell(customerPhoneIndex));
                    String customerEmail = getCellValueAsString(row.getCell(customerEmailIndex));

                    if (phoneNumbers.contains(customerPhone)) {
                        System.out.println("Customer Phone: " + customerPhone);
                        System.out.println("Customer Email: " + customerEmail);
                    } else {
                        System.out.println("Phone number not found: " + customerPhone);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing Excel file", e);
        }
    }

    private Set<String> readPhoneNumbersFromCsv(String filePath) {
        Set<String> phoneNumbers = new HashSet<>();
        try (FileReader fileReader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).build()) {

            String[] header = csvReader.readNext(); // Read header
            int phoneIndex = -1;

            if (header != null) {
                for (int i = 0; i < header.length; i++) {
                    if ("cregcontmobile".equalsIgnoreCase(header[i])) {
                        phoneIndex = i;
                        break;
                    }
                }
            }

            if (phoneIndex == -1) {
                throw new IllegalArgumentException("Column 'cregcontmobile' not found in CSV");
            }

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length > phoneIndex) {
                    String phoneNumber = nextLine[phoneIndex];
                    if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
                        phoneNumbers.add(phoneNumber.trim());
                    }
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing CSV file", e);
        }
        return phoneNumbers;
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
                    String numericValue = String.valueOf(cell.getNumericCellValue());
                    if (numericValue.contains("E")) {
                        BigDecimal bd = new BigDecimal(numericValue);
                        return bd.toPlainString();
                    }
                    return numericValue;
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }



}