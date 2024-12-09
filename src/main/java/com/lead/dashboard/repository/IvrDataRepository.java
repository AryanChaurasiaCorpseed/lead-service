package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.IvrData;

@Repository  
public interface IvrDataRepository extends JpaRepository<IvrData, Long> {

	@Query(value = "SELECT count(*) FROM ivr_data", nativeQuery = true)
	int findAllCount();

}
