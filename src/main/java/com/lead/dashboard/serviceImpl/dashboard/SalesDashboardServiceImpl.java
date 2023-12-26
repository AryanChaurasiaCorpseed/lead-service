package com.lead.dashboard.serviceImpl.dashboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.service.dashboardService.SalesDashboardService;

@Service
public class SalesDashboardServiceImpl implements SalesDashboardService{

	@Autowired
	LeadRepository leadRepository;
	@Override
	public List<Lead> getNoOfLeadDataGraph(Long userId,Long d1,Long d2) {
		 Date startDate = new Date(d1);
		 Date endDate = new Date(d2);
        System.out.println("start Date .."+startDate);
        System.out.println("end Date  . . ."+endDate);
        
        
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date1 = simpleDateFormat.format(startDate);
        String date2 = simpleDateFormat.format(endDate);
        System.out.println("date 1 . ."+date1);
        System.out.println("date 2 . . "+date2);

		List<Lead> leadList = leadRepository.findAllByAssigneeAndInBetweenDate(userId,date1,date2);
		
		
		return leadList;
	}

}
