package com.lead.dashboard.serviceImpl.dashboard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.Company;
import com.lead.dashboard.domain.Project;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.GraphDateFilter;
import com.lead.dashboard.dto.GraphFilterDto;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.dashboardService.SalesDashboardService;
import com.lead.dashboard.serviceImpl.TaskManagmentImpl.TaskManagmentServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;

@Service
public class SalesDashboardServiceImpl implements SalesDashboardService{

	@Autowired
	LeadRepository leadRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TaskManagmentServiceImpl taskManagmentServiceImpl;
	
	@Autowired
	UserRepo userRepo;



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
	public List<Map<String,Object>> getAllProjectGraph(GraphFilterDto graphFilterDto) {
		List<Project> project=new ArrayList<>();
		Long userId=graphFilterDto.getUserId();
		String projectName=graphFilterDto.getServiceName();
		String toDate=graphFilterDto.getToDate();
		String fromDate=graphFilterDto.getFromDate();
		//		System.out.println("aaaaaa");
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;

			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);
				//				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeIdAndInBetweenDate(userId,startDate,endDate);
				//				System.out.println("cccccccccccccccccc");

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByProjectNameAndInBetweenDate(projectName, startDate, endDate);
				//				System.out.println("ddddddddddddd");

			}else {
				project = projectRepository.findAllInBetweenDate(startDate, endDate);
				//				System.out.println("eeeeeeeeeeeeeeeeee");

			}
			Map<String,Integer>map=new HashMap<>();

			for(Project p:project) {
				if(map.containsKey(p.getName())){
					Integer count = map.get(p.getName());
					map.put(p.getName(), count+1);
				}else {

					map.put(p.getName(), 1);
				}
			}

			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Integer> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue());
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?-1:((int)i1.get("value"))>((int)i2.get("value"))?1:0);
			result=result.stream().sorted(Comparator.comparing(i->(int)i.get("value"))) .collect(Collectors.toList());	

			return result;

		}else {
			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByAssigneeIdAndProjectName(userId,projectName);
				//				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeId(userId);
				//				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				//				System.out.println("test ... "+projectName+"aaaa");
				project = projectRepository.findAllByProjectName(projectName);
				//				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

			}else {
				project = projectRepository.findAll();

			}
			Map<String,Integer>map=new HashMap<>();

			for(Project p:project) {
				if(map.containsKey(p.getName())){
					Integer count = map.get(p.getName());
					map.put(p.getName(), count+1);
				}else {

					map.put(p.getName(), 1);
				}
			}
			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Integer> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue());
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(int)i.get("value"))) .collect(Collectors.toList());	
			return result;
		}


	}

	//optimize Version
	public List<Map<String,Object>> getAllProjectGraphV2( GraphFilterDto graphFilterDto) {
		List<Project> project=new ArrayList<>();
		List<Object[]> projects=new ArrayList<>();

		Long userId=graphFilterDto.getUserId();
		String projectName=graphFilterDto.getServiceName();
		String toDate=graphFilterDto.getToDate();
		String fromDate=graphFilterDto.getFromDate();
		//		System.out.println("aaaaaa");
		//		System.out.println("111111"+toDate);

		//		System.out.println("222222"+fromDate);

		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate =fromDate;
			String endDate = toDate;

			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);

				//				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				projects = projectRepository.findIdAndNameByAssigneeIdAndInBetweenDate(userId,startDate,endDate);

				//				System.out.println("cccccccccccccccccc"+projects);

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameByProjectNameAndInBetweenDate(projectName, startDate, endDate);

				//				System.out.println("ddddddddddddd");

			}else {
				projects = projectRepository.findIdAndNameByInBetweenDate(startDate, endDate);

				//				System.out.println("eeeeeeeeeeeeeeeeee");

			}
			////
			Map<String,Integer>map=new HashMap<>();

			for(Object[] p:projects) { 
				String name = (String) p[1];
				if(map.containsKey(name)){
					Integer count = map.get(name);
					map.put(name, count+1);
				}else {

					map.put(name, 1);
				}
			}



			//			Map<String,Integer>map=new HashMap<>();
			//
			//			for(Project p:project) {
			//				if(map.containsKey(p.getName())){
			//					Integer count = map.get(p.getName());
			//					map.put(p.getName(), count+1);
			//				}else {
			//
			//					map.put(p.getName(), 1);
			//				}
			//			}

			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Integer> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue());
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?-1:((int)i1.get("value"))>((int)i2.get("value"))?1:0);
			result=result.stream().sorted(Comparator.comparing(i->(int)i.get("value"))) .collect(Collectors.toList());	

			return result;

		}else {
			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameByAssigneeIdAndProjectName(userId,projectName);

				//				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				projects = projectRepository.findIdAndNameByAssigneeId(userId);

				//				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				//				System.out.println("test ... "+projectName+"aaaa");
				projects = projectRepository.findIdAndNameByProjectName(projectName);

				//				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

			}else {
				projects = projectRepository.findIdAndName();

			}

			Map<String,Integer>map=new HashMap<>();

			for(Object[] p:projects) { 
				String name = (String) p[1];
				if(map.containsKey(name)){
					Integer count = map.get(name);
					map.put(name, count+1);
				}else {

					map.put(name, 1);
				}
			}

			//			Map<String,Integer>map=new HashMap<>();
			//
			//			for(Project p:project) {
			//				if(map.containsKey(p.getName())){
			//					Integer count = map.get(p.getName());
			//					map.put(p.getName(), count+1);
			//				}else {
			//
			//					map.put(p.getName(), 1);
			//				}
			//			}
			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Integer> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue());
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(int)i.get("value"))) .collect(Collectors.toList());	
			return result;
		}


	}

	//  =================== start new =================================================
	@Override
	public List<Map<String,Object>> getAllProjectGraphAmount(GraphFilterDto graphFilterDto) {
		List<Project> project=new ArrayList<>();
		Long userId=graphFilterDto.getUserId();
		String projectName=graphFilterDto.getServiceName();
		String toDate=graphFilterDto.getToDate();
		String fromDate=graphFilterDto.getFromDate();
		//		System.out.println("aaaaaa");
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;

			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);
				//				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeIdAndInBetweenDate(userId,startDate,endDate);
				//				System.out.println("cccccccccccccccccc");

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByProjectNameAndInBetweenDate(projectName, startDate, endDate);
				//				System.out.println("ddddddddddddd");

			}else {
				project = projectRepository.findAllInBetweenDate(startDate, endDate);
				//				System.out.println("eeeeeeeeeeeeeeeeee");

			}
			Map<String,Long>map=new HashMap<>();

			for(Project p:project) {
				if(map.containsKey(p.getName())){
					long amount=0l;
					//					Integer count = c;
					if(p.getAmount()!=null) {
						long a = Long.parseLong(p.getAmount());
						amount=map.get(p.getName())+a;
						map.put(p.getName(), amount);
					}
				}else {
					if(p.getAmount()!=null) {

						Long amount =  Long.parseLong(p.getAmount());
						map.put(p.getName(), amount);
					}
				}
			}

			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Long> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue());
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?-1:((int)i1.get("value"))>((int)i2.get("value"))?1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	

			return result;

		}else {
			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByAssigneeIdAndProjectName(userId,projectName);
				//				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeId(userId);
				//				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				//				System.out.println("test ... "+projectName+"aaaa");
				project = projectRepository.findAllByProjectName(projectName);
				//				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

			}else {
				project = projectRepository.findAll();

			}
			Map<String,Long>map=new HashMap<>();

			for(Project p:project) {
				if(map.containsKey(p.getName())){
					long amount=0l;
					//					Integer count = c;
					if(p.getAmount()!=null) {
						long a = Long.parseLong(p.getAmount());
						amount=map.get(p.getName())+a;
						map.put(p.getName(), amount);
					}
				}else {
					if(p.getAmount()!=null) {

						Long amount =  Long.parseLong(p.getAmount());
						map.put(p.getName(), amount);
					}
				}
			}
			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Long> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue()!=null?entry.getValue():0l);
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
			return result;
		}


	}


	public List<Map<String,Object>> getAllProjectGraphAmountV2(GraphFilterDto graphFilterDto) {
		List<Project> project=new ArrayList<>();
		List<Object[]> projects=new ArrayList<>();

		Long userId=graphFilterDto.getUserId();
		String projectName=graphFilterDto.getServiceName();
		String toDate=graphFilterDto.getToDate();
		String fromDate=graphFilterDto.getFromDate();
		System.out.println("aaaaaa");
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;

			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameAndAmountByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);

				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				projects = projectRepository.findIdAndNameAndAmountByAssigneeIdAndInBetweenDate(userId,startDate,endDate);

				System.out.println("cccccccccccccccccc");

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameAndAmountByProjectNameAndInBetweenDate(projectName, startDate, endDate);

				System.out.println("ddddddddddddd");

			}else {
				projects = projectRepository.findIdAndNameAndAmountByInBetweenDate(startDate, endDate);

				System.out.println("eeeeeeeeeeeeeeeeee");

			}


			Map<String,Long>map=new HashMap<>();
			//            System.out.println("test.............projecta2"+project);

			for(Object[] p:projects) {
				String name=(String)p[1];
				String am=(String)p[2];

				if(map.containsKey(name)){
					long amount=0l;
					//					Integer count = c;
					if(am!=null) {
						//						String s=(String)p[2];
						long a = Long.parseLong(am);
						amount=map.get(name)+a;
						map.put(name, amount);
					}
				}else {
					if(am!=null) {

						Long amount =  Long.parseLong(am);
						map.put(name, amount);
					}
				}
			}



			//			Map<String,Long>map=new HashMap<>();
			//
			//			for(Project p:project) {
			//				if(map.containsKey(p.getName())){
			//					long amount=0l;
			//					//					Integer count = c;
			//					if(p.getAmount()!=null) {
			//						long a = Long.parseLong(p.getAmount());
			//						amount=map.get(p.getName())+a;
			//						map.put(p.getName(), amount);
			//					}
			//				}else {
			//					if(p.getAmount()!=null) {
			//
			//						Long amount =  Long.parseLong(p.getAmount());
			//						map.put(p.getName(), amount);
			//					}
			//				}
			//			}
			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Long> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue());
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?-1:((int)i1.get("value"))>((int)i2.get("value"))?1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	

			return result;

		}else {
			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameByAssigneeIdAndProjectName(userId,projectName);

				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				projects = projectRepository.findIdAndNameAndAmountByAssigneeId(userId);

				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				System.out.println("test ... "+projectName+"aaaa");
				projects = projectRepository.findIdAndNameAndAmountByProjectName(projectName);

				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

			}else {
				projects = projectRepository.findIdAndNameAndAmount();
				System.out.println("iiiiiiiiiiiiii");

			}
			Map<String,Long>map=new HashMap<>();
			//             System.out.println("test.............project"+projects);
			for(Object[] p:projects) {
				String name=(String)p[1];
				String am=(String)p[2];

				if(map.containsKey(name)){
					long amount=0l;
					//					Integer count = c;
					if(am!=null) {
						// 						String s=(String)p[2];
						long a = Long.parseLong(am);
						amount=map.get(name)+a;
						map.put(name, amount);
					}
				}else {
					if(am!=null) {

						Long amount =  Long.parseLong(am);
						map.put(name, amount);
					}
				}
			}
			List<Map<String,Object>>result = new ArrayList<>();
			for(Entry<String,Long> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue()!=null?entry.getValue():0l);
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
			return result;
		}


	}



	@Override
	public List<Map<String, Object>> getAllCompanyAmountGraph(GraphFilterDto graphFilterDto) {
		List<Company>compList = new ArrayList<>();
		List<Map<String,Object>>result = new ArrayList<>();

		if(true) {

			String endDate= graphFilterDto.getToDate();
			String startDate =graphFilterDto.getFromDate();
			//			p.id ,cp.company_id , p.amount , c.companyName
			List<Object[]> project=new ArrayList<>();
			if(startDate!=null && endDate!=null) {
				project = projectRepository.findInBetweenDate(startDate,endDate);

			}else {
				project = projectRepository.findCompanyIdAndAmount();

			}
			//              List<Object[]> project = projectRepository.findInBetweenDate(startDate,endDate);
			Map<String, Long>map = new HashMap<>();
			for(Object[] o:project) {
				Long projectId=  (Long)o[0];
				Long companyId= (Long)o[1];
				String amount  =(String)o[2];
				long a = o[2]!=null?Long.parseLong((String)o[2]):0l;
				String companyName  =(String)o[3];
				if(map.containsKey(companyName)) {
					Long amo =  map.get(companyName)+a;
					map.put(companyName, amo);
				}else {
					map.put(companyName, a);
				}
			}
			for(Entry<String,Long> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey());
				m.put("value", entry.getValue()!=null?entry.getValue():0l);
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
			return result;

		}	
		return result;
	}
	@Override
	public List<Map<String, Object>> getAllAmountUserWise(GraphFilterDto graphFilterDto) {

		List<Company>compList = new ArrayList<>();
		List<Map<String,Object>>result = new ArrayList<>();

		if(true) {

			String startDate = graphFilterDto.getFromDate();
			String endDate =graphFilterDto.getToDate();
			//			p.id ,cp.company_id , p.amount , c.companyName
			List<Project> project =new ArrayList<>();
			if(startDate!=null && endDate!=null) {
				project = projectRepository.findAllByInBetweenDate(startDate,endDate);

			}else {
				project = projectRepository.findAllProject();

			}
			//               List<Project> project = projectRepository.findAllByInBetweenDate(startDate,endDate);
			Map<User, Long>map = new HashMap<>();
			for(Project p:project) {

				long a = p.getAmount()!=null?Long.parseLong((String)p.getAmount()):0l;
				User u = p.getAssignee();
				Object companyName;
				if(map.containsKey(u)) {
					Long amo =  map.get(u)+a;
					map.put(u, amo);
				}else {
					map.put(u, a);
				}
			}
			for(Entry<User,Long> entry:map.entrySet()) {
				Map<String,Object>m=new HashMap<>();
				m.put("name", entry.getKey()!=null?entry.getKey().getFullName():"NA");
				m.put("email", entry.getKey()!=null?entry.getKey().getEmail():"NA");
				m.put("value", entry.getValue()!=null?entry.getValue():0l);
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
			return result;

		}	
		return result;
	}
	@Override
	public long getTotalLeadCount() {
		long lead = leadRepository.findCountByIsDeleted(false);
		return lead;
	}
	@Override
	public long getTotalProjectCount() {
		long pCount=projectRepository.findAllCount();
		return pCount;
	}
	@Override
	public List<Map<String, Object>> getAllLeadMonthWise(GraphDateFilter graphDateFilter) {


		List<Object[]> projects=new ArrayList<>();

		String toDate=graphDateFilter.getToDate();
		String fromDate=graphDateFilter.getFromDate();
		String startDate = toDate;
		String endDate = fromDate;
		List<Object[]> leads=new ArrayList<>();

		System.out.println("aaaaaa  ......"+startDate);
		//		projects = projectRepository.findIdAndNameAndAmountByInBetweenDate(startDate, endDate);
		leads=leadRepository.findIdAndNameAndCreateDateByInBetweenDate(startDate, endDate);
		System.out.println("t..........................t"+leads.size());

		Map<String,Map<String,Object>>map=new LinkedHashMap();
		for(Object[] l:leads) {
			Long id=(Long)l[0];
			String leadName=(String)l[1];
			Date createDate=(Date)l[2];
			//			createDate=taskManagmentServiceImpl.convertTimeV1(createDate);
			int year = 1900+createDate.getYear();
			String temp=createDate.getMonth()+""+year;
			if(map.containsKey(temp)) {

				Map<String, Object> c = map.get(temp);
				long cValue=(long)c.get("counts");
				c.put("date", createDate);
				c.put("counts", cValue+1);
				map.put(temp,c);
			}else {
				Long count = 1l; ;
				Map<String,Object>m=new HashMap<>();
				m.put("counts", count);
				m.put("date", createDate);
				map.put(temp, m);
			}
		}

		List<Map<String,Object>>result = new ArrayList<>();
		for(Entry<String,Map<String,Object>> entry:map.entrySet()) {
			Map<String,Object>m=new HashMap<>();
			Map<String, Object> data = entry.getValue();
			m.put("name", data.get("date"));
			m.put("value", data.get("counts"));
			m.put("test", entry.getKey());

			result.add(m);
		}		
//		result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	

		return result;



	}



	public List<Map<String, Object>> getAllProjectMonthWise(GraphDateFilter graphDateFilter) {

		String toDate=graphDateFilter.getToDate();
		String fromDate=graphDateFilter.getFromDate();
		String startDate = toDate;
		String endDate = fromDate;
		List<Object[]> projects=new ArrayList<>();
		projects = projectRepository.findIdAndNameAndCreateDateByInBetweenDate(startDate, endDate);
		Map<String,Map<String,Object>>map=new LinkedHashMap();
		for(Object[] l:projects) {
			Long id=(Long)l[0];
			String leadName=(String)l[1];
			if(l[2]!=null) {
				Date createDate=(Date)l[2];
				int year = 1900+createDate.getYear();
				String temp=createDate.getMonth()+""+year;
				if(map.containsKey(temp)) {
					Map<String, Object> c = map.get(temp);
					long cValue=(long)c.get("counts");
					c.put("date", createDate);
					c.put("counts", cValue+1);
					map.put(temp,c);
				}else {
					Long count = 1l; ;
					Map<String,Object>m=new HashMap<>();
					m.put("counts", count);
					m.put("date", createDate);
					map.put(temp, m);
				}
			}
		}
		List<Map<String,Object>>result = new ArrayList<>();
		for(Entry<String,Map<String,Object>> entry:map.entrySet()) {
			Map<String,Object>m=new HashMap<>();
			Map<String, Object> data = entry.getValue();
			m.put("name", data.get("date"));
			m.put("value", data.get("counts"));
			m.put("test", entry.getKey());

			result.add(m);
		}		
//		result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
		return result;
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
	
	public Date getEndDateofMonth(String dateToConvert) {
		 String sDate2 =dateToConvert;
		 SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");  
       Date date2=null;
		try {
			date2 = formatter2.parse(sDate2);
		} catch (ParseException e) {
			
			e.printStackTrace();
		} 
		LocalDate now =convertToLocalDateViaInstant(date2);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
		Date date = Date.from(lastDay.atStartOfDay(defaultZoneId).toInstant());
		
		return date; 
	}
	
	
	
	public Date getStartDateofMonth(String dateToConvert) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		String pattern = "yyyy-MM-dd HH:mm";

		 String sDate2 = dateToConvert;
		 SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");  
     Date date2=null;
		try {
			date2 = formatter2.parse(sDate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		LocalDate now =convertToLocalDateViaInstant(date2);

		LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
		Date date = Date.from(firstDay.atStartOfDay(defaultZoneId).toInstant());
		System.out.println("start day of month "+date);
		return date;
	}
	
	public Date convertTime(Date date) {
		 Calendar calendar=Calendar.getInstance();
	     calendar.setTime(date);
		return calendar.getTime();
	}

	public List<Map<String, Object>> getAllMonthProject(String date) {

		String s=date;
		Date sDate = getStartDateofMonth(s);
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date1 = simpleDateFormat.format(convertTime(sDate));
		System.out.println("after start conveersion "+s);
		 Date eDate = getEndDateofMonth(s);
		
		String date2 = simpleDateFormat.format(convertTime(eDate));
		System.out.println(date1+"  ..  ..  time .. . . "+date2);
		List<Object[]> projects=new ArrayList<>();
		projects = projectRepository.findIdAndNameAndCreateDateByInBetweenDate(date1, date2);
		Map<String,Map<String,Object>>map=new LinkedHashMap();
		for(Object[] l:projects) {
			Long id=(Long)l[0];
			String leadName=(String)l[1];
			if(l[2]!=null) {
				Date createDate=(Date)l[2];
				int year = 1900+createDate.getYear();
				String temp=createDate.getMonth()+""+year;
				if(map.containsKey(temp)) {
					Map<String, Object> c = map.get(temp);
					long cValue=(long)c.get("counts");
					c.put("date", createDate);
					c.put("counts", cValue+1);
					map.put(temp,c);
				}else {
					Long count = 1l; ;
					Map<String,Object>m=new HashMap<>();
					m.put("counts", count);
					m.put("date", createDate);
					map.put(temp, m);
				}
			}
		}
		System.out.println("Size for testing "+projects.size());
		List<Map<String,Object>>result = new ArrayList<>();
		for(Entry<String,Map<String,Object>> entry:map.entrySet()) {
			Map<String,Object>m=new HashMap<>();
			Map<String, Object> data = entry.getValue();
			m.put("name", data.get("date"));
			m.put("value", data.get("counts"));
			m.put("test", entry.getKey());

			result.add(m);
		}		
//		result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
		return result;
	}
	@Override
	public Map<String, Object> getAllTypeLeadCount(GraphDateFilter graphDateFilter) {
		List<Object[]> projects=new ArrayList<>();


		long ivrCount=0l;
		long websiteCount=0l;
		long qualityReopenCount=0l;
		long totalCount=0l;
		Map<String,Object>map=new HashMap<>();
		if(graphDateFilter.getToDate()!=null &&graphDateFilter.getFromDate()!=null) {
			String toDate=graphDateFilter.getToDate();
			String fromDate=graphDateFilter.getFromDate();
			String startDate = toDate;
			String endDate = fromDate;
			ivrCount=leadRepository.findCountBySourceAndInBetweenDate("IVR",startDate, endDate);
			websiteCount=leadRepository.findCountBySourceAndInBetweenDate("Website",startDate, endDate);
			qualityReopenCount=leadRepository.findCountByIsReopenByQualityAndSourceAndInBetweenDate(startDate, endDate);
            map.put("ivrCount", ivrCount);
            map.put("websiteCount", websiteCount);
            map.put("qualityReopenCount", qualityReopenCount);
            map.put("totalCount", ivrCount+websiteCount+qualityReopenCount);

		}else {
			ivrCount=leadRepository.findCountBySource("IVR");
			websiteCount=leadRepository.findCountBySource("Website");
			qualityReopenCount=leadRepository.findCountByIsReopenByQuality();
			
			  map.put("ivrCount", ivrCount);
	            map.put("websiteCount", websiteCount);
	            map.put("qualityReopenCount", qualityReopenCount);
	            map.put("totalCount", ivrCount+websiteCount+qualityReopenCount);

		}
		return map;
	}
	@Override
	public long getTortalUserCount() {
			long count=userRepo.findCountByIsDeleted(false);
			return count;
	}
	@Override
	public long getTotalCompanyCount() {
		  long count=companyRepository.findAllCount();
		return count;
	}
	
}

