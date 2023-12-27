package com.lead.dashboard.service.dashboardService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.lead.Lead;

@Service
public interface SalesDashboardService {

	Map<String,Integer> getNoOfLeadDataGraph(Long userId, Long d1, Long d2);

	List<Lead> getLatestLead(Long userId);

}
