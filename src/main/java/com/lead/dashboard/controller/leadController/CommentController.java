package com.lead.dashboard.controller.leadController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lead.dashboard.domain.Comment;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.LeadDTO;
import com.lead.dashboard.util.UrlsMapping;
import com.lead.dashboard.service.CommentService;

@RestController
public class CommentController {	
	
 
	@Autowired
	CommentService CommentService;
	
	@PostMapping(UrlsMapping.CREATE_COMMENT)
	public 	Boolean createComments(@RequestParam String comment)
	{
		Boolean res=CommentService.createComments(comment);
		return res;
	}

	@GetMapping(UrlsMapping.GET_ALL_COMMENTS)
	public 	List<Comment> getAllComment()
	{
		List<Comment> res=CommentService.getAllComment();
		return res;
	}
	
	@DeleteMapping(UrlsMapping.DELETE_COMMENT)
	public 	Boolean deleteComment(@RequestParam Long id)
	{
		Boolean res=CommentService.deleteComment(id);
		return res;
	}
	@PutMapping(UrlsMapping.UPDATE_COMMENT)
	public 	Comment updateComment(@RequestParam Long id, @RequestParam String comment)
	{
		Comment res=CommentService.updateComment(id,comment);
		return res;
	}

}
