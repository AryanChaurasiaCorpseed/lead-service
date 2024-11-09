package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.SubIndustry;
import com.lead.dashboard.domain.SubSubIndustry;
@Repository
public interface SubSubIndustryRepo extends JpaRepository<SubSubIndustry, Long> {

	@Query(value = "SELECT * FROM sub_sub_industry i WHERE i.id in (:subsubIndustryId)", nativeQuery = true)
	List<SubSubIndustry> findAllByIdIn(List<Long> subsubIndustryId);
	@Query(value = "SELECT * FROM sub_sub_industry WHERE name=:name limit 1", nativeQuery = true)
	SubSubIndustry findByName(String name);

}
