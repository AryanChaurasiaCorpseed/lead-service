package com.lead.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.IvrData;

@Repository  
public interface IvrDataRepository extends JpaRepository<IvrData, Long> {

	@Query(value = "SELECT count(*) FROM ivr_data", nativeQuery = true)
	int findAllCount();
	@Query(value = "SELECT ivd.id FROM ivr_data ivd where ivd.caller_number =:callerph and start_time =:start_time", nativeQuery = true)
	List<Long> findByCallerNumberAndStartTime(String callerph, String start_time);

}
