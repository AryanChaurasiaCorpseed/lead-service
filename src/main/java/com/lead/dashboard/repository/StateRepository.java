package com.lead.dashboard.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.State;

public interface StateRepository extends JpaRepository<State, Long> {

	@Query(value = "SELECT * FROM state s WHERE s.id in(:stateId)", nativeQuery = true)
	List<State> findAllByIdIn(List<Long> stateId);



}
