package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.IvrData;

@Repository  
public interface IvrDataRepository extends JpaRepository<IvrData, Long> {

}
