package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.Nodes;

@Repository
public interface NodeRepository extends JpaRepository<Nodes, Long> {

}
