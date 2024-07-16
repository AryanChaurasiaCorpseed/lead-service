package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.lead.dashboard.domain.Comment;
import com.lead.dashboard.repository.CommentRepo;
import com.lead.dashboard.service.CommentService;

public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentRepo commentRepo;

	@Override
	public Boolean createComments(String commentName) {
		Boolean flag=false;
		Comment comment = new Comment();
		comment.setName(commentName);
		comment.setDeleted(false);
		commentRepo.save(comment);
		flag=true;
		return flag;
	}

	@Override
	public List<Comment> getAllComment() {
		List<Comment> res = commentRepo.findAll().stream().filter(i->i.isDeleted()==false).collect(Collectors.toList());
		return res;
	}

}
