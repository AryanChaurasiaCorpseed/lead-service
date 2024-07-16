package com.lead.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Comment;

@Service
public interface CommentService {

	Boolean createComments(String comment);

	List<Comment> getAllComment();

}
