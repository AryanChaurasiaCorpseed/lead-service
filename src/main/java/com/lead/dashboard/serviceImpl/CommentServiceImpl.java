package com.lead.dashboard.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Comment;
import com.lead.dashboard.repository.CommentRepo;
import com.lead.dashboard.service.CommentService;


@Service
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

	@Override
	public Boolean deleteMapping(Long id) {
		Boolean flag=false;
		Comment comment = commentRepo.findById(id).get();
		comment.setDeleted(true);
		commentRepo.save(comment);
		flag=true;
		return flag;
	}

}
