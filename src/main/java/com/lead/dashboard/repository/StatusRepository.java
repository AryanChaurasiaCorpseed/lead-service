package com.lead.dashboard.repository;

import com.lead.dashboard.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long>
{
    Status getStatusById(Long id);

}
