package com.lead.dashboard.controller.statusController;


import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.CreateLeadStatus;
import com.lead.dashboard.service.LeadService;
import com.lead.dashboard.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//KPS
//Here is Status Controller where put all status type
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @Autowired
    private LeadService leadService;

    @PostMapping("api/v1/status/CreateLeadStatus")
    public ResponseEntity<Status> createStatus(@RequestBody CreateLeadStatus status) {
        Status createdStatus = statusService.createStatus(status);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @GetMapping("api/v1/status/getAllStatus")
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping("api/v1/status/getStatusId")
    public ResponseEntity<Status> getStatusById(@RequestParam("id") Long id) {
        Status status = statusService.getStatusById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @PutMapping("api/v1/status/updateStatus")
    public ResponseEntity<Status> updateStatus(@RequestBody Status status) {
        Status updatedStatus = statusService.updateStatus(status);
        return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
    }

    @DeleteMapping("api/v1/status/deleteStaus")
    public ResponseEntity<Status> deleteStatus(@RequestParam("id") Long id) {
        statusService.deleteStatus(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("api/v1/status/updateLeadStatus")
    public ResponseEntity<String> updateLeadStatus(@RequestParam Long leadId,@RequestParam Long statusId,@RequestParam(required=false) Long currentUserId) {
        try {
            statusService.updateLeadStatus(leadId,statusId,currentUserId);
            return ResponseEntity.ok("Lead status updated successfully");
        }

        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");

        }

    }
    
    @GetMapping("api/v1/status/getAllPreviusStatus")
    public ResponseEntity<List<Status>> getAllPreviusStatus(@RequestParam Long statusId) {
        List<Status> statuses = statusService.getAllPreviusStatus(statusId);
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }


}
