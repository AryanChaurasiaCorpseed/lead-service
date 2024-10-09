package com.lead.dashboard.serviceImpl.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.lead.dashboard.dto.GraphFilterDto;
import com.lead.dashboard.repository.CompanyRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.ProjectRepository;
import com.lead.dashboard.service.dashboardService.SalesDashboardService;

@Service
public class SalesDashboardServiceImpl implements SalesDashboardService{

	@Autowired
	LeadRepository leadRepository;
	
	@Autowired
    CompanyRepository companyRepository;

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
	public List<Map<String,Object>> getAllProjectGraph(GraphFilterDto graphFilterDto) {
		List<Project> project=new ArrayList<>();
		Long userId=graphFilterDto.getUserId();
		String projectName=graphFilterDto.getServiceName();
		String toDate=graphFilterDto.getToDate();
		String fromDate=graphFilterDto.getFromDate();
		System.out.println("aaaaaa");
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;

			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);
				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeIdAndInBetweenDate(userId,startDate,endDate);
				System.out.println("cccccccccccccccccc");

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByProjectNameAndInBetweenDate(projectName, startDate, endDate);
				System.out.println("ddddddddddddd");

			}else {
				project = projectRepository.findAllInBetweenDate(startDate, endDate);
				System.out.println("eeeeeeeeeeeeeeeeee");

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
				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeId(userId);
				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				System.out.println("test ... "+projectName+"aaaa");
				project = projectRepository.findAllByProjectName(projectName);
				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

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
	public List<Map<String,Object>> getAllProjectGraphV2(GraphFilterDto graphFilterDto) {
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
				projects = projectRepository.findIdAndNameByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);

				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				projects = projectRepository.findIdAndNameByAssigneeIdAndInBetweenDate(userId,startDate,endDate);

				System.out.println("cccccccccccccccccc");

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				projects = projectRepository.findIdAndNameByProjectNameAndInBetweenDate(projectName, startDate, endDate);

				System.out.println("ddddddddddddd");

			}else {
				projects = projectRepository.findIdAndNameByInBetweenDate(startDate, endDate);

				System.out.println("eeeeeeeeeeeeeeeeee");

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

				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				projects = projectRepository.findIdAndNameByAssigneeId(userId);

				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				System.out.println("test ... "+projectName+"aaaa");
				projects = projectRepository.findIdAndNameByProjectName(projectName);

				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

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
		System.out.println("aaaaaa");
		if(toDate!=null && (!toDate.equals("")) && fromDate!=null &&(!fromDate.equals(""))) {
			String startDate = toDate;
			String endDate = fromDate;

			if(userId!=null && projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByAssigneeIdAndProjectNameAndInBetweenDate(userId,projectName,startDate,endDate);
				System.out.println("bbbbbbbbbbbbbbbb");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeIdAndInBetweenDate(userId,startDate,endDate);
				System.out.println("cccccccccccccccccc");

			}else if(userId==null &&projectName!=null &&(!projectName.equals(""))) {
				project = projectRepository.findAllByProjectNameAndInBetweenDate(projectName, startDate, endDate);
				System.out.println("ddddddddddddd");

			}else {
				project = projectRepository.findAllInBetweenDate(startDate, endDate);
				System.out.println("eeeeeeeeeeeeeeeeee");

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
				System.out.println("fffffffffffffffffff");

			}else if(userId!=null &&projectName==null) {
				project = projectRepository.findAllByAssigneeId(userId);
				System.out.println("gggggggggggggggggggggg");

			}else if(userId==null && projectName!=null &&(!projectName.equals(""))) {
				System.out.println("test ... "+projectName+"aaaa");
				project = projectRepository.findAllByProjectName(projectName);
				System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

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

		if(graphFilterDto.getUserId()!=null) {
			
			String startDate = graphFilterDto.getToDate();
			String endDate =graphFilterDto.getFromDate();
//			p.id ,cp.company_id , p.amount , c.companyName
			
              List<Object[]> project = projectRepository.findInBetweenDate(startDate,endDate);
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

		if(graphFilterDto.getUserId()!=null) {
			
			String startDate = graphFilterDto.getToDate();
			String endDate =graphFilterDto.getFromDate();
//			p.id ,cp.company_id , p.amount , c.companyName
			
               List<Project> project = projectRepository.findAllByInBetweenDate(startDate,endDate);
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
				m.put("name", entry.getKey().getFullName());
				m.put("email", entry.getKey().getEmail());
				m.put("value", entry.getValue()!=null?entry.getValue():0l);
				result.add(m);
			}
			//            Collections.sort(result,(i1,i2)->((int)i1.get("value"))>((int)i2.get("value"))?1:((int)i1.get("value"))>((int)i2.get("value"))?-1:0);
			result=result.stream().sorted(Comparator.comparing(i->(long)i.get("value"))) .collect(Collectors.toList());	
			return result;
              
		}	
		return result;
	}
	
//	compList=companyRepository.findByAssigneeId(graphFilterDto.getUserId());
//	Map<String,Integer>map = new HashMap<>();
//	for(Company comp:compList) {
//		 List<Company> company = companyRepository.findAllByParentId(comp.getId());
//		 for(Company c:company) {
//			 
//		 }
//	}

}
