package com.lead.dashboard.repository;


import com.lead.dashboard.domain.LeadStatusChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadStatusChangeHisoryRepo extends JpaRepository<LeadStatusChangeHistory,Long>
{

}