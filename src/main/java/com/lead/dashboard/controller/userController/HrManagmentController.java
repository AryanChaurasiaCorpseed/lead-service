package com.lead.dashboard.controller.userController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.service.HrManagmentService;
import com.lead.dashboard.util.UrlsMapping;

@RestController
public class HrManagmentController {

	@Autowired
	HrManagmentService hrManagmentService;
	
	@GetMapping(UrlsMapping.GET_USER_APPROVAL_HR)
	public ResponseEntity<List<User>> getUserApprovalHr(@RequestParam Long userId)
	{

		List<User> allUser=hrManagmentService.getUserApprovalHr(userId);
		if(!allUser.isEmpty())
		{
			return  new ResponseEntity<>(allUser,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(allUser,HttpStatus.OK);
		}
	}
	
	@PutMapping(UrlsMapping.APPROVED_USER_BY_HR)
	public ResponseEntity<Boolean> approvedUserByHr(@RequestParam Long currentUserId,@RequestParam Long userId,boolean flag)
	{

		Boolean allUser=hrManagmentService.approvedUserByHr(currentUserId,userId,flag);
		if(allUser)
		{
			return  new ResponseEntity<>(allUser,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(allUser,HttpStatus.OK);
		}
	}
	
	
	
}
