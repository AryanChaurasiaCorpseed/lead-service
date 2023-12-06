package com.lead.dashboard.repository;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long>
{

	@Query(value = "SELECT * FROM user u WHERE u.email =:email", nativeQuery = true)
	User findByemail(String email);
	
	@Query(value = "SELECT u.role FROM user u WHERE u.id =:id", nativeQuery = true)
	List<String> findRoleById(Long id);
	
	@Query(value = " SELECT r.name FROM  user_role ur left join roles r on r.id=ur.user_role_id WHERE ur.user_id=:id", nativeQuery = true)
	List<String> findRoleNameById(Long id);
	
}
