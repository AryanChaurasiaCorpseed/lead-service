package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.ProductAmount;

public interface ProductAmountRepo extends JpaRepository<ProductAmount, Long> {

}
