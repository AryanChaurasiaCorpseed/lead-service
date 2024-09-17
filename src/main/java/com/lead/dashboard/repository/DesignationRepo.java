package com.lead.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Designation;

@Repository
public interface DesignationRepo extends JpaRepository<Designation, Long> {

	@Query(value = "SELECT * FROM designation d WHERE d.is_deleted =:b", nativeQuery = true)
	List<Designation> findAllByIsDeleted(boolean b);
	
	@Query(value = "SELECT d.id FROM designation d WHERE d.is_deleted =:b and weight_value>5", nativeQuery = true)
	List<Long> findIdByIsDeletedAndWeightValue(boolean b);

	@Query(value = "SELECT * FROM designation d WHERE d.id in(:designationId)", nativeQuery = true)
	List<Designation> findAllByIdIn(List<Long> designationId);


	Optional<Designation> findByName(String procurementManager);
}
