package com.lead.dashboard.serviceImpl.dashboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String,Integer> getNoOfLeadDataGraph(Long userId,Long d1,Long d2) {
        String date1 = convertLongToStringDateFormat(d1);
        String date2 = convertLongToStringDateFormat(d2);
        System.out.println("date 1 . ."+date1);
        System.out.println("date 2 . . "+date2);

		List<Lead> leadList = leadRepository.findAllByAssigneeAndInBetweenDate(userId,date1,date2);
		Map<String,Integer>res =new LinkedHashMap<> ();
		for(int i=0;i<6;i++) {
			Date d = new Date(d1);
			d.setMonth(d.getMonth()+i);
			String pattern = "yyyy-MMMM";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	        String df1 = simpleDateFormat.format(d);
			res.put(df1,0);
		}
		for(Lead l:leadList) {
			Date ld = l.getCreateDate();
			String pattern = "yyyy-MMMM";
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	        String df1 = simpleDateFormat.format(ld);
	        Integer count = res.get(df1)!=null?res.get(df1):0;
	        res.put(df1, count+1);
		}
		return res;
	}
	public String convertLongToStringDateFormat(Long date) {
		Date startDate = new Date(date);
//		String pattern = "yyyy-MM-dd HH:mm:ss"; //Format of String
		String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date1 = simpleDateFormat.format(startDate);
        return date1;
	}
	@Override
	public List<Lead> getLatestLead(Long userId) {
		// TODO Auto-generated method stub
		List<Lead> leadList = leadRepository.findAllByAssigneeLatest(userId);

		return leadList;
	}

}