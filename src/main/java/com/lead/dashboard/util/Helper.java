package com.lead.dashboard.util;

import com.lead.dashboard.controller.companyController.CompanyFormController;
import com.lead.dashboard.domain.CompanyForm;
import com.lead.dashboard.dto.CompanyDto;
import com.lead.dashboard.dto.CreateFormDto;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class Helper {

    @Autowired
    private CompanyFormController companyFormController;

    public void processCsvFile(String filePath) {
        try (FileReader filereader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(new CSVParser()).build()) {

            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                CreateFormDto companyRequestData = new CreateFormDto();
                companyRequestData.setCompanyName(nextLine[0]);
                companyRequestData.setAddress(nextLine[1]);
                companyRequestData.setCity(nextLine[2]);
                companyRequestData.setContactName(nextLine[3] + nextLine[4]);
                companyRequestData.setContactEmails(nextLine[5]);
                companyRequestData.setContactNo(nextLine[6]);
                companyRequestData.setPanNo(nextLine[7]);
                companyRequestData.setGstNo(nextLine[8]);
                companyRequestData.setAddedByInOldCrm(nextLine[9]);
                companyRequestData.setState(nextLine[10]);
                companyRequestData.setCountry(nextLine[11]);
                companyRequestData.setIsPresent(true);
                companyRequestData.setAssigneeId(1L);
                companyRequestData.setUpdatedBy(1L);
                companyRequestData.setLeadId(1L);

                CompanyForm savedData= companyFormController.createCompanyForm(companyRequestData);

                System.out.println(savedData+ "Saved Succesfully");
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing CSV file", e);
        }
    }
}