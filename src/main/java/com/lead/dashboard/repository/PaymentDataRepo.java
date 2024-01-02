package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.PaymentData;

public interface PaymentDataRepo extends JpaRepository<PaymentData, Long>{
  
}
