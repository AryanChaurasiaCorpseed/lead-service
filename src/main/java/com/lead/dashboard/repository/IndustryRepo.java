package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.Industry;

public interface IndustryRepo extends JpaRepository<Industry, Long> {

	@Query(value = "SELECT * FROM industry WHERE name=:name limit 1", nativeQuery = true)
	Industry findByName(String name);
}
