package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Designation;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {

	@Query(value = "SELECT * FROM designation d WHERE d.is_deleted =:b", nativeQuery = true)
	List<Designation> findAllByIsDeleted(boolean b);

	
}
