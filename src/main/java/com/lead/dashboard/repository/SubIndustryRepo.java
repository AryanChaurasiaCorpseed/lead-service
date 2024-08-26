package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.SubIndustry;

@Repository
public interface SubIndustryRepo extends JpaRepository<SubIndustry, Long> {

	@Query(value = "SELECT * FROM sub_industry i WHERE i.id in (:subIndustryId)", nativeQuery = true)
	List<SubIndustry> findAllByIdIn(List<Long> subIndustryId);

}
