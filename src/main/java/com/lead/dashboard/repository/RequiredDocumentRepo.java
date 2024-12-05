package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.RequiredDocuments;

@Repository
public interface RequiredDocumentRepo  extends JpaRepository<RequiredDocuments, Long> {
	
}
