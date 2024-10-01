package com.lead.dashboard.serviceImpl.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.GraphFilterDto;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.service.dashboardService.SalesDashboardService;

@Service
public class SalesDashboardServiceImpl implements SalesDashboardService{

	@Autowired
	LeadRepository leadRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
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
	@Override
	public Map<String,Integer> getAllProjectGraphAmount(GraphFilterDto graphFilterDto) {
		List<Project> project=new ArrayList<>();
		Long userId=graphFilterDto.getUserId();
		String projectName=graphFilterDto.getServiceName();
		String toDate=graphFilterDto.getToDate();
		String fromDate=graphFilterDto.getFromDate();
		
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;
			
			if(userId!=null && projectName!=null) {
				project = projectRepository.findAllByAssigneeIdAndProjectName(userId,projectName);
			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeId(userId);

			}else if(userId==null &&projectName!=null) {
				project = projectRepository.findAllByProjectName(projectName);

			}else {
				project = projectRepository.findAll();

			}
			List<Map<String,Integer>>result = new ArrayList<>();
			Map<String,Integer>map=new HashMap<>();

			for(Project p:project) {
				if(map.containsKey(p.getName())){
					Integer count = map.get(p.getName());
					map.put(p.getName(), count);
				}else {
					
					map.put(p.getName(), 1);
				}
			}
				
			return map;
			
		}else {
			if(userId!=null && projectName!=null) {
				project = projectRepository.findAllByAssigneeIdAndProjectName(userId,projectName);
			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeId(userId);

			}else if(userId==null &&projectName!=null) {
				project = projectRepository.findAllByProjectName(projectName);

			}else {
				project = projectRepository.findAll();

			}
			List<Map<String,Integer>>result = new ArrayList<>();
			Map<String,Integer>map=new HashMap<>();

			for(Project p:project) {
				if(map.containsKey(p.getName())){
					Integer count = map.get(p.getName());
					map.put(p.getName(), count);
				}else {
					
					map.put(p.getName(), 1);
				}
			}
				
			return map;
		}
		
		
	}
	
	//  =================== start new =================================================

}
