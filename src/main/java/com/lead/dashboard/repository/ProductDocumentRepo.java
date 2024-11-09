package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lead.dashboard.domain.ProductDocuments;

@Repository
public interface ProductDocumentRepo extends JpaRepository<ProductDocuments, Long> {

}
