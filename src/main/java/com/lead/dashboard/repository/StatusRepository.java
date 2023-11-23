package com.lead.dashboard.repository;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.dto.CreateLeadStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long>
{
    Status getStatusById(Long id);

	Status findAllByName(String status);

//	Status save(CreateLeadStatus status);

}
