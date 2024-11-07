package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.SubIndustry;
@Repository
public interface SubIndustryRepo extends JpaRepository<SubIndustry, Long> {

	@Query(value = "SELECT * FROM sub_industry WHERE name=:name limit 1", nativeQuery = true)
	SubIndustry findByName(String name);

}
