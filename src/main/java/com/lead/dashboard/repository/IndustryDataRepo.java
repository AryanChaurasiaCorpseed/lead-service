package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.IndustryData;

public interface IndustryDataRepo extends JpaRepository<IndustryData, Long> {

	@Query(value = "SELECT * FROM industry_data i WHERE i.id in (:industryDataId)", nativeQuery = true)
	List<IndustryData> findAllByIdIn(List<Long> industryDataId);

}
