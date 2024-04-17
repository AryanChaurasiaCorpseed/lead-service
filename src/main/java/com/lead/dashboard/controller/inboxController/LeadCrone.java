package com.lead.dashboard.controller.inboxController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.RatingRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;
import com.lead.dashboard.repository.UserRepo;

@Service
public class LeadCrone {
	// boolean fl=false;

	@Autowired
	UserRepo userRepo;
	@Autowired
	private LeadRepository leadRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	private UrlsManagmentRepo urlsManagmentRepo;

	public void assignLeadByCrone() {
		List<Lead>leadList =leadRepository.findAllByStatusIdAndAuto(1l,true);

		//New means --> auto
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Quality-Manager")).collect(Collectors.toList());
		//-============== Quality Manager =========================
		User qualityManager =null;

		//==================
		List<User>topUser = userList.stream().filter(i->i.getFeedback().equals("5") || i.getFeedback().equals("4")).collect(Collectors.toList());
		System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();
		for(Lead lead:leadList) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//					   then we can assign Only 4* and 5*
						lead.setAssignee(topUser.get(0));
						System.out.println("bbbbbbbbbbbbb");

					}else { 
						System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						lead.setAssignee(userList.get(0));

					}
				}else {
					System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					//========
					if(lead.getIsUrlsChecked()) {
						if(urlsMap.get(lead.getLeadName())) {// hume ek key lekr chalna hoga
							lead.setAssignee(qualityManager);				

						}else {
							lead.setAssignee(topUser.get(0));				
						}
					}else {
						// those are working on this slug sales person
						lead.setAssignee(topUser.get(0));				

					}
					///============

					if(qualityUser.size()>0) {
						qualityManager=qualityUser.get(0);
					}
					lead.setAssignee(qualityManager);				
				}
			}
			leadRepository.save(lead);


		}
		//		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());


	}

	public void assignLeadByCroneV2() {
		List<Lead>leadList =leadRepository.findAllByStatusIdAndAuto(1l,true);

		//New means --> auto
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Quality-Manager")).collect(Collectors.toList());
		//-============== Quality Manager =========================
		User qualityManager =null;

		//==================
		List<User>topUser = userList.stream().filter(i->i.getFeedback().equals("5") || i.getFeedback().equals("4")).collect(Collectors.toList());
		System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();
		for(Lead lead:leadList) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//					   then we can assign Only 4* and 5*
						lead.setAssignee(topUser.get(0));
						System.out.println("bbbbbbbbbbbbb");

					}else { 
						System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						lead.setAssignee(userList.get(0));

					}
				}else {
					System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					//========
					if(lead.getIsUrlsChecked()) {
						if(urlsMap.get(lead.getLeadName())) {// hume ek key lekr chalna hoga
							lead.setAssignee(qualityManager);				

						}else {
							lead.setAssignee(topUser.get(0));				
						}
					}else {
						// those are working on this slug sales person

						lead.setAssignee(topUser.get(0));				

					}
					///============

					if(qualityUser.size()>0) {
						qualityManager=qualityUser.get(0);
					}
					lead.setAssignee(qualityManager);				
				}
			}
			leadRepository.save(lead);


		}
		//		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());


	}


	public void assignLeadByCroneV3() {
		List<Long>croneStatus= new ArrayList<>();
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeleted(croneStatus, false);

		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().map(i->i.getAssignee().getId()).collect(Collectors.toList());

		List<Lead> allLeadList = leadRepository.findAllByStatusIdInAndAssigneeIdInAndOriginalNameInAndIsDeleted(croneStatus, assigneeIdList,originalName, false);
		Map<String,List<Lead>>map = new HashMap<>();
		// ServiceName , LeadList
		for(Lead lead:allLeadList ) {
			if(map.containsKey(lead.getOriginalName())) {
				List<Lead> d = map.get(lead.getOriginalName());
				d.add(lead);
				map.put(lead.getOriginalName(), d);
			}else {
				List<Lead> d = new ArrayList<>();
				d.add(lead);
				map.put(lead.getOriginalName(), d);
			}
		}

		//they return the count value on the basis of service
		Map<String,Map<User,Integer>>mapCount=new HashMap<>();
		for(Map.Entry<String,List<Lead>> entry:map.entrySet()) {
			Map<User,Integer>temp = new HashMap<>();
			List<Lead> singleList = entry.getValue();
			for(Lead lead : singleList) {
				User assignee = lead.getAssignee();
				if(temp.containsKey(assignee)) {
					Integer count=temp.get(assignee);
					count=count+1;
					temp.put(assignee, count);
				}else {
					temp.put(assignee, 1);			
				}
			}

		}
		//



		//=======================================================================================start = 
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Quality-Manager")).collect(Collectors.toList());
		List<User>salesUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Sales-Executive")).collect(Collectors.toList());

		//-============== Quality Manager =========================
		User qualityManager =null;

		//==================
		List<User>topUser = userList.stream().filter(i->i.getFeedback().equals("5") || i.getFeedback().equals("4")).collect(Collectors.toList());
		System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();

		for(Lead lead:cronelead) {

			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//					   then we can assign Only 4* and 5*
						lead.setAssignee(topUser.get(0));
						System.out.println("bbbbbbbbbbbbb");

					}else { 
						System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						lead.setAssignee(userList.get(0));

					}
				}else {
					System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					//========
					if(lead.getIsUrlsChecked()) {
						if(urlsMap.get(lead.getLeadName())) {// hume ek key lekr chalna hoga
							lead.setAssignee(qualityManager);				

						}else {
							lead.setAssignee(topUser.get(0));				
						}
					}else {
						// those are working on this slug sales person

						lead.setAssignee(salesUser.get(0));				

					}
					///============

					//					if(qualityUser.size()>0) {
					//						qualityManager=qualityUser.get(0);
					//					}
					//					lead.setAssignee(qualityManager);				
				}
			}
			leadRepository.save(lead);



		}





	}

	public  ArrayList<User>mergeStarUser(List<User>user4,List<User>user5,Map<User,Integer>countMap){
		int i=0;
		int j=0;
		ArrayList<User>result =new ArrayList<>();
		int l1=user4.size()-1;
		int l2=user5.size()-1;
		while(i<=l1 &&j<=l2) {
			User u4=user4.get(i);
			User u5=user5.get(j);

			int c4=countMap.get(u4);
			int c5=countMap.get(u5)+10;

			if(c4>c5) {
				result.add(u5);
				j++;
			}else {
				result.add(u4);
				i++;
			}
		}
		if(i==l1 &&j<l2) {
			while(j<l2-1) {
				User u5=user5.get(j);
				result.add(u5);
				j++;
			}
		}
		if(j==l2-1 &&i<l1-1) {
			while(i<l1-1) {
				User u4=user4.get(i);
				result.add(u4);
				i++;
			}
		}
		return result;
	}

	public  Map<Long,List<User>>allUserServiceRating(){

		return null;
	}

	public Map<String,User> getUserAccordingToService(List<Lead>leads){
		return null;
	}

	public  Map<String,Boolean> getAllUrls(){
		List<UrlsManagment> urlsList = urlsManagmentRepo.findAll();
		Map<String,Boolean>map=new HashMap<>();
		for(UrlsManagment url:urlsList) {
			map.put(url.getUrlsName(), url.isQuality());
		}
		return map;
	}

	public User userAccordingToLeadData() {
		return null;
	}


	public UserRepo getUserRepo() {
		return userRepo;
	}
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	public LeadRepository getLeadRepository() {
		return leadRepository;
	}
	public void setLeadRepository(LeadRepository leadRepository) {
		this.leadRepository = leadRepository;
	}
	public User getAssignee(List<Lead>leadList) {
		User assignee=null;
		if(leadList!=null && leadList.size()!=0) {
			Lead lead = leadList.get(0);
			assignee = lead.getAssignee();

		}
		return assignee;
	}
	public Boolean checkEmailDomain(String email) {
		Boolean flag=true;
		String separator ="@";
		int sepPos = email.indexOf(separator);
		String s1=email.substring(sepPos);
		if("@gmail.com".equals(s1)||"@yahoo.com".equals(s1)||"@myyahoo.com".equals(s1)) {
			flag=false;
		}
		return flag;
	}




	/////=====final Data = = = == = == = = = = == = = = = = = == == = = = = == = = = = = = == = = = = 

	public void assignLeadByCroneV4() {
		List<Long>croneStatus= new ArrayList<>();
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeleted(croneStatus, false);

		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().map(i->i.getAssignee().getId()).collect(Collectors.toList());

		List<Lead> allLeadList = leadRepository.findAllByStatusIdInAndAssigneeIdInAndOriginalNameInAndIsDeleted(croneStatus, assigneeIdList,originalName, false);
		Map<String,List<Lead>>map = new HashMap<>();
		// ServiceName , LeadList
		for(Lead lead:allLeadList ) {
			if(map.containsKey(lead.getOriginalName())) {
				List<Lead> d = map.get(lead.getOriginalName());
				d.add(lead);
				map.put(lead.getOriginalName(), d);
			}else {
				List<Lead> d = new ArrayList<>();
				d.add(lead);
				map.put(lead.getOriginalName(), d);
			}
		}

		//they return the count value on the basis of service
		Map<String,Map<User,Integer>>mapCount=new HashMap<>();
		for(Map.Entry<String,List<Lead>> entry:map.entrySet()) {
			Map<User,Integer>temp = new HashMap<>();
			List<Lead> singleList = entry.getValue();
			for(Lead lead : singleList) {
				User assignee = lead.getAssignee();
				if(temp.containsKey(assignee)) {
					Integer count=temp.get(assignee);
					count=count+1;
					temp.put(assignee, count);
				}else {
					temp.put(assignee, 1);			
				}
			}

		}
		//



		//=======================================================================================start = 
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Quality-Manager")).collect(Collectors.toList());
		List<User>salesUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Sales-Executive")).collect(Collectors.toList());

		//-============== Quality Manager =========================
		User qualityManager =null;

		//==================
		List<User>topUser = userList.stream().filter(i->i.getFeedback().equals("5") || i.getFeedback().equals("4")).collect(Collectors.toList());
		System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();

		for(Lead lead:cronelead) {

			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//	then we can assign Only 4* and 5*						

						//						 List<String>ratingList= new ArrayList<>();
						//						 ratingList.add("4");
						//						 ratingList.add("5");
						Long productId=urlsManagment.getId();
						String rating4="4";
						List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

						String rating5="5";
						List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						ArrayList<User> userMerged = mergeStarUser(user4Rating,user5Rating,userCounts);
						lead.setAssignee(userMerged.get(0));

					}else { 
						System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						Long productId=urlsManagment.getId();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						List<Ratings> ratingList = ratingRepository.findAllByProdctId(productId);
						List<User> getAllStarUser = mergeAllUser1to5Star(ratingList, userCounts);
						lead.setAssignee(getAllStarUser.get(0));

					}
				}else {
					System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
					//========
					if(lead.getIsUrlsChecked()) {
						if(urlsMap.get(lead.getLeadName())) {// hume ek key lekr chalna hoga
							lead.setAssignee(qualityManager);				

						}else {
							lead.setAssignee(topUser.get(0));				
						}
					}else {
						// those are working on this slug sales person

						lead.setAssignee(salesUser.get(0));				

					}
					///============

					//					if(qualityUser.size()>0) {
					//						qualityManager=qualityUser.get(0);
					//					}
					//					lead.setAssignee(qualityManager);				
				}
			}
			leadRepository.save(lead);



		}
	}
	public void mergeAllUser1to5Star(List<User>user1,List<User>user2,List<User>user3,List<User>user4,List<User>user5,Map<User,Integer>countMap) {
		Map<User,Integer>countManage=countMap;
		List<User>resUser = new ArrayList<>(user1);
		for(User u:user2) {
			Integer c2=countManage.get(u)+10;
			countManage.put(u, c2);
			resUser.add(u);
		}

		for(User u:user3) {
			Integer c2=countManage.get(u)+20;
			countManage.put(u, c2);
			resUser.add(u);  

		}
		for(User u:user4) {
			Integer c2=countManage.get(u)+30;
			countManage.put(u, c2);
			resUser.add(u);

		}
		for(User u:user5) {
			Integer c2=countManage.get(u)+40;
			countManage.put(u, c2);
			resUser.add(u);

		}
		Collections.sort(resUser, (a, b) -> {
			Integer c1=countManage.get(a);
			Integer c2=countManage.get(b);
			return c1.compareTo(c2);
		});



	}


	public List<User> mergeAllUser1to5Star(List<Ratings>ratings,Map<User,Integer>countMap) {
		Map<User,Integer>countManage=countMap;
		List<User>resUser = new ArrayList<>();
		for(Ratings r:ratings) {
			//			Integer c2=countManage.get(u)+10;
			List<User> userList = r.getRatingsUser();
			String rating=r.getRating();
			for(User u:userList) {
				if("2".equals(rating)) {
					Integer c2=countManage.get(u)+10;
					countManage.put(u, c2);
				}else if("3".equals(rating)) {
					Integer c2=countManage.get(u)+20;
					countManage.put(u, c2);
					resUser.add(u);  
				}else if("4".equals(rating)) {
					Integer c2=countManage.get(u)+30;
					countManage.put(u, c2);
					resUser.add(u);
				}else if("5".equals(rating)) {
					Integer c2=countManage.get(u)+40;
					countManage.put(u, c2);
					resUser.add(u);
				}else {
					Integer c2=countManage.get(u);
					countManage.put(u, c2);
					resUser.add(u);
				}

			}
		}


		Collections.sort(resUser, (a, b) -> {
			Integer c1=countManage.get(a);
			Integer c2=countManage.get(b);
			return c1.compareTo(c2);
		});

		return resUser;	
	}

	public void assignLeadByCroneV5() {
		List<Long>croneStatus= new ArrayList<>();
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeleted(croneStatus, false);
		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().map(i->i.getAssignee().getId()).collect(Collectors.toList());

		List<Lead> allLeadList = leadRepository.findAllByStatusIdInAndAssigneeIdInAndOriginalNameInAndIsDeleted(croneStatus, assigneeIdList,originalName, false);
		Map<String,List<Lead>>map = new HashMap<>();
		// ServiceName , LeadList
		for(Lead lead:allLeadList ) {
			if(map.containsKey(lead.getOriginalName())) {
				List<Lead> d = map.get(lead.getOriginalName());
				d.add(lead);
				map.put(lead.getOriginalName(), d);
			}else {
				List<Lead> d = new ArrayList<>();
				d.add(lead);
				map.put(lead.getOriginalName(), d);
			}
		}

		//they return the count value on the basis of service
		Map<String,Map<User,Integer>>mapCount=new HashMap<>();
		for(Map.Entry<String,List<Lead>> entry:map.entrySet()) {
			Map<User,Integer>temp = new HashMap<>();
			List<Lead> singleList = entry.getValue();
			for(Lead lead : singleList) {
				User assignee = lead.getAssignee();
				if(temp.containsKey(assignee)) {
					Integer count=temp.get(assignee);
					count=count+1;
					temp.put(assignee, count);
				}else {
					temp.put(assignee, 1);			
				}
			}

		}
		//=======================================================================================start = 
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
        int qi=0;//initial statge og quality user
        int ql=qualityUser.size();
		for(Lead lead:cronelead) {

			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);

				}else {
					lead.setAssignee(assignee);
				}
			}else {
				if(lead.getIsUrlsChecked()) {
					if(!urlsManagment.isQuality()) {

							Boolean isProf =false;
							if(lead.getEmail()!=null) {
								isProf= checkEmailDomain(lead.getEmail());
							}
							// we check service and check  service in required true or false
							if(isProf) {
								Long productId=urlsManagment.getId();
								String rating4="4";
								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
								String rating5="5";
								List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
								Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
								ArrayList<User> userMerged = mergeStarUser(user4Rating,user5Rating,userCounts);
								lead.setAssignee(userMerged.get(0));

							}else { 
								Long productId=urlsManagment.getId();
								Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
								List<Ratings> ratingList = ratingRepository.findAllByProdctId(productId);
								List<User> getAllStarUser = mergeAllUser1to5Star(ratingList, userCounts);
								lead.setAssignee(getAllStarUser.get(0));
							}
					}else {
						if(ql>0) {
							lead.setAssignee(qualityUser.get(qi));
							qi++;
							if(qi==ql) {
								qi=0;
							}	
						}
					}
				}else{
					Boolean isProf =false;
					if(lead.getEmail()!=null) {
						isProf= checkEmailDomain(lead.getEmail());
					}
					// we check service and check  service in required true or false
					if(isProf) {
						Long productId=urlsManagment.getId();
						String rating4="4";
						List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
						String rating5="5";
						List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						ArrayList<User> userMerged = mergeStarUser(user4Rating,user5Rating,userCounts);
						lead.setAssignee(userMerged.get(0));
					}else { 
						Long productId=urlsManagment.getId();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						List<Ratings> ratingList = ratingRepository.findAllByProdctId(productId);
						List<User> getAllStarUser = mergeAllUser1to5Star(ratingList, userCounts);
						lead.setAssignee(getAllStarUser.get(0));
					}
				}
			}
			leadRepository.save(lead);



		}
	}




}
