package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.UserRecord;

@Repository
public interface UserHistoryRepo extends JpaRepository<UserRecord, Long> {

	@Query(value = "SELECT * FROM user_record ur WHERE ur.current_user_id in(:userId)", nativeQuery = true)
	List<UserRecord> findAllByCurrentUserId(Long userId);

}
