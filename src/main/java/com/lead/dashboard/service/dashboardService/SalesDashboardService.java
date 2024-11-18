package com.lead.dashboard.service.dashboardService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.GraphDateFilter;
import com.lead.dashboard.dto.GraphFilterDto;

@Service
public interface SalesDashboardService {

	Map<String,Integer> getNoOfLeadDataGraph(Long userId, Long d1, Long d2);

	List<Lead> getLatestLead(Long userId);


	List<Map<String,Object>> getAllProjectGraph(GraphFilterDto graphFilterDto);
	
	List<Map<String,Object>> getAllProjectGraphAmount(GraphFilterDto graphFilterDto);

	List<Map<String, Object>> getAllCompanyAmountGraph(GraphFilterDto graphFilterDto);

	List<Map<String, Object>> getAllAmountUserWise(GraphFilterDto graphFilterDto);

	List<Map<String, Object>> getAllProjectGraphV2(GraphFilterDto graphFilterDto);

	List<Map<String, Object>> getAllProjectGraphAmountV2(GraphFilterDto graphFilterDto);

	long getTotalLeadCount();

	long getTotalProjectCount();

	List<Map<String, Object>> getAllLeadMonthWise(GraphDateFilter graphDateFilter);

	List<Map<String, Object>> getAllProjectMonthWise(GraphDateFilter graphDateFilter);

	List<Map<String, Object>> getAllMonthProject(String date);

	Map<String, Object> getAllTypeLeadCount(GraphDateFilter graphDateFilter);

	long getTortalUserCount();

	long getTotalCompanyCount();


}
