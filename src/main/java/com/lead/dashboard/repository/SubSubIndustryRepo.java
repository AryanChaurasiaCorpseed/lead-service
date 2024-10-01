package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.SubSubIndustry;

public interface SubSubIndustryRepo extends JpaRepository<SubSubIndustry, Long> {

	@Query(value = "SELECT * FROM sub_sub_industry i WHERE i.id in (:subsubIndustryId)", nativeQuery = true)
	List<SubSubIndustry> findAllByIdIn(List<Long> subsubIndustryId);

}
