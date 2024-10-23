package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.ProductDocuments;


public interface ProductDocumentRepo extends JpaRepository<ProductDocuments, Long> {

}
