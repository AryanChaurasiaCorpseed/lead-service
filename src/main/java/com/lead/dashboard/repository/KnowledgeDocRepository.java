package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.KnowledgeDocument;

@Repository
public interface KnowledgeDocRepository extends JpaRepository<KnowledgeDocument, Long> {

}
