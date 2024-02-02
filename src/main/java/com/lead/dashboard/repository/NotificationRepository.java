package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Notification;
import com.lead.dashboard.domain.lead.Lead;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
  
	@Query(value = "SELECT * FROM notification n WHERE n.is_view =:b and n.user_id = :userId", nativeQuery = true)
	List<Notification> findAllByUserIdAndIsView(Long userId,boolean b);
	
}
