package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.SubNodes;

@Repository
public interface SubNodesRepository extends JpaRepository<SubNodes, Long> {

}
