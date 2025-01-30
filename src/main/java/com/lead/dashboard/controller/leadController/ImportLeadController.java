package com.lead.dashboard.controller.leadController;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.serviceImpl.CsvLeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class ImportLeadController {

    @Autowired
    private CsvLeadService csvLeadService;

    @PostMapping("/import-csv-from-path")
    public ResponseEntity<List<Lead>> importCsvFromPath(@RequestParam("filePath") String filePath) {
        List<Lead> leads = csvLeadService.processCsvFileFromPath(filePath);
        return ResponseEntity.ok(leads);
    }

    @PostMapping("/import-csv-from-s3")
    public ResponseEntity<List<Lead>> importCsvFromS3(@RequestParam("s3Url") String s3Url) {
        List<Lead> leads = csvLeadService.processCsvFileFromS3(s3Url);
        return ResponseEntity.ok(leads);
    }
}
