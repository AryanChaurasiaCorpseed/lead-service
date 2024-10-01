package com.lead.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.lead.Remark;

public interface RemarkRepository extends JpaRepository<Remark, Long> {

}
