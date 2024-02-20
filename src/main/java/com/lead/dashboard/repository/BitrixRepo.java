package com.lead.dashboard.repository;

import com.lead.dashboard.domain.BitrixBad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BitrixRepo extends JpaRepository<BitrixBad, Long> {
}
