package com.lead.dashboard.controller.chatController;

import java.io.IOException;
import java.util.List;

import com.lead.dashboard.dto.UpdateRemark;
import com.lead.dashboard.dto.remarkDTO.UpdateRemarkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.service.ChatService;
import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.dto.CreateRemark;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/leadService/")
public class ChatController {       
	
	@Autowired
	ChatService chatService;
	
	
	@PostMapping("api/v1/createChat")
	public ResponseEntity<Client> createChat(@RequestParam Long clientId,Long userId,String message){
			Client updatedDeatils =chatService.createChat(clientId,userId,message);
			return ResponseEntity.ok(updatedDeatils);
	}
	
	@DeleteMapping("api/v1/deleteChat")
	public ResponseEntity<Boolean> deleteChat(@RequestParam Long chatId){
			Boolean updatedDeatils =chatService.deleteChat(chatId);
			return ResponseEntity.ok(updatedDeatils);
	}
	
	@PostMapping("api/v1/createRemarks")
	public Remark createRemarks(@RequestBody CreateRemark createRemark){
		Remark updatedDeatils =chatService.createRemarks(createRemark.getLeadId(),createRemark.getUserId(),createRemark.getMessage(),createRemark.getFile(),createRemark.getType());
			return updatedDeatils;
	}

	@PostMapping("api/v1/updateRemark")
	public Remark updateRemark(@RequestBody UpdateRemarkRequest updateRemarkRequest) {
		Remark updatedDetails = chatService.updateRemarks(updateRemarkRequest);
		return updatedDetails;
	}

	@GetMapping("api/v1/getAllRemarks")
	public List<Remark> getAllRemarks(@RequestParam Long leadId){
		List<Remark> updatedDeatils =chatService.getAllRemarks(leadId);
			return updatedDeatils;
	}
	

}
