package com.lead.dashboard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.lead.Lead;

@Service
public interface LeadHistorySevice {

	ArrayList<Map<String,Object>> getAllLeadHistory(Long leadId);

}
