package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> { 
}
