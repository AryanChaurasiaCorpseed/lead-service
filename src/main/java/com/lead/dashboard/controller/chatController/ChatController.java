package com.lead.dashboard.controller.chatController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lead.dashboard.service.ChatService;
import com.lead.dashboard.domain.Client;


@Controller
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

}
