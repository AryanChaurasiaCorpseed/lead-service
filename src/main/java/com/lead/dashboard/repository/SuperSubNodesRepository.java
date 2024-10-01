package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.SuperSubNodes;
@Repository
public interface SuperSubNodesRepository extends JpaRepository<SuperSubNodes, Long> {

}
