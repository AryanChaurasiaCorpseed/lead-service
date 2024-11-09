package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.Role;
import com.lead.dashboard.domain.lead.Lead;


public interface RoleRepository extends JpaRepository<Role,Long>{
	
	@Query(value = "SELECT * FROM roles r WHERE r.name in(:name)", nativeQuery = true)
	List<Role> findAllByNameIn(List<String> name);

}
