package com.lead.dashboard.service.dashboardService;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.lead.Lead;

@Service
public interface SalesDashboardService {

	List<Lead> getNoOfLeadDataGraph(Long userId, Long d1, Long d2);

}
