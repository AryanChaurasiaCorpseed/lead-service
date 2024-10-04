package com.lead.dashboard.controller.taskManagmentController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.TaskStatus;
import com.lead.dashboard.service.taskManagmentService.TaskStatusService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

@RequestMapping("/leadService/")
public class TaskStatusController {

	
	@Autowired
	TaskStatusService taskStatusService;
	
    @PostMapping("api/v1/createTaskStatus")
    public ResponseEntity<TaskStatus> createStatus(@RequestParam String name,@RequestParam String description) {
        TaskStatus createdStatus = taskStatusService.createStatus(name,description);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @GetMapping("api/v1/getAllTaskStatus")
    public ResponseEntity<List<TaskStatus>> getAllStatuses() {
        List<TaskStatus> statuses = taskStatusService.getAllStatuses();
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping("api/v1/getStatusId")
    public ResponseEntity<TaskStatus> getTaskStatusById(@RequestParam("id") Long id) {
    	TaskStatus status = taskStatusService.getTaskStatusById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @PutMapping("api/v1/updateStatus")
    public ResponseEntity<TaskStatus> updateStatus(@RequestParam Long id,@RequestParam String name,@RequestParam String description) {
    	TaskStatus updatedStatus = taskStatusService.updateStatus(id,name,description);
        return new ResponseEntity<>(updatedStatus, HttpStatus.OK);
    }


    @DeleteMapping("api/v1/deleteStaus")
    public ResponseEntity<Boolean> deleteTaskStatus(@RequestParam("id") Long id) {
    	Boolean deleteStatus=taskStatusService.deleteStatus(id);
    	if(deleteStatus) {
            return new ResponseEntity<>(deleteStatus, HttpStatus.OK);
    	}else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    	}
    }
}
