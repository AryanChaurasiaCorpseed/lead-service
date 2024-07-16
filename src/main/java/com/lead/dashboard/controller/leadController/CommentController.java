package com.lead.dashboard.controller.leadController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.lead.dashboard.domain.Comment;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.util.UrlsMapping;
import com.lead.dashboard.service.CommentService;

public class CommentController {	
	
 
	@Autowired
	CommentService CommentService;
	
	@PostMapping(UrlsMapping.CREATE_COMMENT)
	public 	Boolean createComments(@RequestParam String comment)
	{
		Boolean res=CommentService.createComments(comment);
		return res;
	}

	@PostMapping(UrlsMapping.GET_ALL_COMMENTS)
	public 	List<Comment> getAllComment()
	{
		List<Comment> res=CommentService.getAllComment();
		return res;
	}

}
