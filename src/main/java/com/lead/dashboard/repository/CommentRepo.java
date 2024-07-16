package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> { 
}
