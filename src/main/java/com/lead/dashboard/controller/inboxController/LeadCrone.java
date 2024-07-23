package com.lead.dashboard.controller.inboxController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.Ratings;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadHistoryRepository;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.RatingRepository;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UrlsManagmentRepo;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.serviceImpl.MailSendSerivceImpl;

@Service
@Transactional
public class LeadCrone {
	// boolean fl=false;

	@Autowired
	UserRepo userRepo;
	@Autowired
	private LeadRepository leadRepository;
	@Autowired
	MailSendSerivceImpl mailSendSerivceImpl;

	@Autowired
	LeadHistoryRepository leadHistoryRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	private UrlsManagmentRepo urlsManagmentRepo;

	//	public void assignLeadByCrone() {
	//		List<Lead>leadList =leadRepository.findAllByStatusIdAndAuto(1l,true);
	//
	//		//New means --> auto
	//		List<User>userList = userRepo.findAllActiveUser();
	//		List<User>qualityUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Quality-Manager")).collect(Collectors.toList());
	//		//-============== Quality Manager =========================
	//		User qualityManager =null;
	//
	//		//==================
	//		List<User>topUser = userList.stream().filter(i->i.getFeedback().equals("5") || i.getFeedback().equals("4")).collect(Collectors.toList());
	//		// System.out.println("top user list"+topUser.size());
	//		Map<String, Boolean> urlsMap = getAllUrls();
	//		for(Lead lead:leadList) {
	//			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
	//			// System.out.println(existingLead.size());
	//			if(existingLead!=null && existingLead.size()>1) {
	//				User assignee=getAssignee(existingLead);
	//				// System.out.println("assignee .."+assignee);
	//				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
	//				if(assignee!=null &&assignee.isDeleted()) {
	//					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
	//					lead.setAssignee(user);
	//					// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
	//
	//				}else {
	//					lead.setAssignee(assignee);
	//					// System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
	//				}
	//
	//			}else {
	//				if(lead.getEmail()!=null) {
	//					Boolean isProf = checkEmailDomain(lead.getEmail());
	//
	//					// System.out.println("ssssssssss"+isProf);
	//					// we check service and check  service in required true or false
	//					if(isProf) {
	//						//					   then we can assign Only 4* and 5*
	//						lead.setAssignee(topUser.get(0));
	//						// System.out.println("bbbbbbbbbbbbb");
	//
	//					}else { 
	//						// System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
	//						lead.setAssignee(userList.get(0));
	//
	//					}
	//				}else {
	//					// System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
	//					//========
	//					if(lead.getIsUrlsChecked()) {
	//						if(urlsMap.get(lead.getLeadName())) {// hume ek key lekr chalna hoga
	//							lead.setAssignee(qualityManager);				
	//
	//						}else {
	//							lead.setAssignee(topUser.get(0));				
	//						}
	//					}else {
	//						// those are working on this slug sales person
	//						lead.setAssignee(topUser.get(0));				
	//
	//					}
	//					///============
	//
	//					if(qualityUser.size()>0) {
	//						qualityManager=qualityUser.get(0);
	//					}
	//					lead.setAssignee(qualityManager);				
	//				}
	//			}
	//			leadRepository.save(lead);
	//
	//
	//		}
	//		//		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());
	//
	//
	//	}

	public void assignLeadByCroneV2() {
		List<Lead>leadList =leadRepository.findAllByStatusIdAndAuto(1l,true);

		//New means --> auto
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDesignation().equalsIgnoreCase("Quality-Manager")).collect(Collectors.toList());
		//-============== Quality Manager =========================
		User qualityManager =null;

		//==================
		List<User>topUser = userList.stream().filter(i->i.getFeedback().equals("5") || i.getFeedback().equals("4")).collect(Collectors.toList());
		// System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();
		for(Lead lead:leadList) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			// System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				// System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					// System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					// System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//					   then we can assign Only 4* and 5*
						lead.setAssignee(topUser.get(0));
						// System.out.println("bbbbbbbbbbbbb");

					}else { 
						// System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						lead.setAssignee(userList.get(0));

					}
				}else {
					// System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
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
		// System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();

		for(Lead lead:cronelead) {

			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			// System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				// System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					// System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					// System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//					   then we can assign Only 4* and 5*
						lead.setAssignee(topUser.get(0));
						// System.out.println("bbbbbbbbbbbbb");

					}else { 
						// System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						lead.setAssignee(userList.get(0));

					}
				}else {
					// System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
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
		int l1=user4!=null?user4.size():0;
		int l2=user5!=null?user5.size():0;
		while(i<l1 &&j<l2) {
			User u4=user4.get(i);
			User u5=user5.get(j);

			int c4=countMap!=null?countMap.get(u4)!=null?countMap.get(u4):0:0;
			int c5=countMap!=null?countMap.get(u5)!=null?countMap.get(u5)+10:10:10;

			if(c4>c5) {
				result.add(u5);
				int cInt = countMap!=null?countMap.get(u5)!=null?countMap.get(u5):0:0;
				//				countMap.put(u5, cInt+1);
				j++;
			}else {
				result.add(u4);
				int cInt=countMap!=null?countMap.get(u4)!=null?countMap.get(u4):0:0;
				//				countMap.put(u4, cInt+1);
				i++;
			}
		}

		if(i==l1 &&j<l2) {
			while(j<l2) {
				User u5=user5.get(j);
				int c5=countMap!=null?countMap.get(u5)!=null?countMap.get(u5):0:0;
				//				countMap.put(u5, c5+1);
				result.add(u5);
				j++;
			}
		}
		if(j==l2 &&i<l1) {
			while(i<l1) {
				User u4=user4.get(i);
				int c4=countMap!=null?countMap.get(u4)!=null?countMap.get(u4):0:0;
				//				countMap.put(u4, c4+1);
				result.add(u4);
				i++;
			}
		}
		System.out.println("=========start=============");

		for(User u:result) {
			int c=countMap!=null?countMap.get(u)!=null?countMap.get(u)+10:10:10;

			System.out.println(u.getId()+"...count..."+c);
		}
		System.out.println("==========end============");

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
		// System.out.println("top user list"+topUser.size());
		Map<String, Boolean> urlsMap = getAllUrls();

		for(Lead lead:cronelead) {

			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			// System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				// System.out.println("assignee .."+assignee);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
					// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");

				}else {
					lead.setAssignee(assignee);
					// System.out.println("BBBBBBBBBBBBBBBBBBBBBBB ");
				}

			}else {
				if(lead.getEmail()!=null) {
					Boolean isProf = checkEmailDomain(lead.getEmail());

					// System.out.println("ssssssssss"+isProf);
					// we check service and check  service in required true or false
					if(isProf) {
						//	then we can assign Only 4* and 5*						

						Long productId=urlsManagment.getId();
						String rating4="4";
						List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

						String rating5="5";
						List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						ArrayList<User> userMerged = mergeStarUser(user4Rating,user5Rating,userCounts);
						lead.setAssignee(userMerged.get(0));

					}else { 
						// System.out.println("cccccccccccccccccccccccccccccccccccccccccccccc");
						Long productId=urlsManagment.getId();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
						List<User> getAllStarUser = mergeAllUser1to5Star(ratingList, userCounts);
						lead.setAssignee(getAllStarUser.get(0));

					}
				}else {
					// System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
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
					Integer count = countManage.get(u)!=null?countManage.get(u):0;
					Integer c2=count+10;
					countManage.put(u, c2);
				}else if("3".equals(rating)) {
					Integer count = countManage.get(u)!=null?countManage.get(u):0;
					Integer c2=count+20;
					countManage.put(u, c2);
					resUser.add(u);  
				}else if("4".equals(rating)) {
					Integer count = countManage.get(u)!=null?countManage.get(u):0;
					Integer c2=count+30;
					countManage.put(u, c2);
					resUser.add(u);
				}else if("5".equals(rating)) {
					// System.out.println(countManage+"........... count manage");
					// System.out.println(u+"........... count manage by u");
					Integer count = countManage.get(u)!=null?countManage.get(u):0;
					Integer c2=count+40;
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
		croneStatus.add(1l);
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeleted(croneStatus, false);
		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().filter(i->i.getAssignee()!=null).map(i->i.getAssignee().getId()).collect(Collectors.toList());

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
		System.out.println(map.size());
		int cout=0;
		Map<String,Map<User,Integer>>mapCount=new HashMap<>();
		for(Map.Entry<String,List<Lead>> entry:map.entrySet()) {
			Map<User,Integer>temp = new HashMap<>();
			List<Lead> singleList = entry.getValue();
			for(Lead lead : singleList) {
				User assignee = lead.getAssignee();
				if(temp.containsKey(assignee)) {
					Integer count=temp.get(assignee);
					count=count+1;
					System.out.println(count);
					temp.put(assignee, count);
				}else {
					System.out.println(1);

					temp.put(assignee, 1);			
				}
			}
			mapCount.put(entry.getKey(), temp);

		}
		System.out.println("service and user map :::"+mapCount);
		//========================================start = 
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		// System.out.println(ql+"................");
		for(Lead lead:cronelead) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			// System.out.println("ORIGINAL NAME :::"+lead.getOriginalName());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				System.out.println("Extra Large");

				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);

				}else {
					lead.setAssignee(assignee);
				}
			}else {
				// System.out.println(lead.getIsUrlsChecked()+".....testing .. .. ."+lead.getId());
				if(lead!=null & lead.getIsUrlsChecked()) {

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
							int cInt = userCounts!=null?userCounts.get(userMerged.get(0))!=null?userCounts.get(userMerged.get(0)):0:0;
							userCounts.put(userMerged.get(0), cInt+1);

						}else { 
							Long productId=urlsManagment.getId();
							Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
							for(Map.Entry<User, Integer> entry:userCounts.entrySet()) {
								System.out.println(entry.getKey().getEmail()+" . . . . dATA . . ."+entry.getValue());
							}

							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

							if(ratingList!=null&&ratingList.size()>0) {
								List<User> getAllStarUser = mergeAllUser1to5Star(ratingList, userCounts);
								lead.setAssignee(getAllStarUser.get(0));
								int cInt = userCounts!=null?userCounts.get(getAllStarUser.get(0))!=null?userCounts.get(getAllStarUser.get(0)):0:0;
								userCounts.put(getAllStarUser.get(0), cInt+1);
							}

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
						int cInt = userCounts!=null?userCounts.get(userMerged.get(0))!=null?userCounts.get(userMerged.get(0)):0:0;
						userCounts.put(userMerged.get(0), cInt+1);
					}else { 
						Long productId=urlsManagment.getId();
						Map<User, Integer> userCounts = mapCount.get(lead.getOriginalName());
						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
						List<User> getAllStarUser = mergeAllUser1to5Star(ratingList, userCounts);
						lead.setAssignee(getAllStarUser.get(0));
						int cInt = userCounts!=null?userCounts.get(getAllStarUser.get(0))!=null?userCounts.get(getAllStarUser.get(0)):0:0;
						userCounts.put(getAllStarUser.get(0), cInt+1);
					}
				}
			}
			leadRepository.save(lead);



		}
	}

	public void assignLeadByCroneV6() {
		List<Long>croneStatus= new ArrayList<>();
		croneStatus.add(1l);
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeleted(croneStatus, false);
		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().filter(i->i.getAssignee()!=null).map(i->i.getAssignee().getId()).collect(Collectors.toList());

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
		int cout=0;
		Map<String,Map<Long,Integer>>mapCount=new HashMap<>();
		for(Map.Entry<String,List<Lead>> entry:map.entrySet()) {
			Map<Long,Integer>temp = new HashMap<>();
			List<Lead> singleList = entry.getValue();
			for(Lead lead : singleList) {
				User assignee = lead.getAssignee();
				if(temp.containsKey(assignee.getId())) {
					Integer count=temp.get(assignee.getId());
					count=count+1;
					temp.put(assignee.getId(), count);
				}else {
					System.out.println(1);

					temp.put(assignee.getId(), 1);			
				}
			}
			mapCount.put(entry.getKey(), temp);

		}
		System.out.println("service and user map :::"+mapCount);
		//========================================start = 
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		// System.out.println(ql+"................");
		for(Lead lead:cronelead) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			// System.out.println("ORIGINAL NAME :::"+lead.getOriginalName());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				System.out.println("Extra Large");

				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);

				}else {
					lead.setAssignee(assignee);
				}
			}else {
				// System.out.println(lead.getIsUrlsChecked()+".....testing .. .. ."+lead.getId());
				if(lead!=null & lead.getIsUrlsChecked()) {

					if(urlsManagment!=null &&(!urlsManagment.isQuality())) {

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
							Map<Long, Integer> userCounts = mapCount.get(lead.getOriginalName());
							ArrayList<User> userMerged = mergeStarUserV2(user4Rating,user5Rating,userCounts);
							lead.setAssignee(userMerged.get(0));
							int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;
							System.out.println(cInt);
							if(userCounts!=null) {
								userCounts.put(userMerged.get(0).getId(), cInt+1);
							}
							mapCount.put(lead.getOriginalName(), userCounts);


						}else { 
							Long productId=urlsManagment.getId();
							Map<Long, Integer> userCounts = mapCount.get(lead.getOriginalName());


							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

							if(ratingList!=null&&ratingList.size()>0) {
								System.out.println("User Counts"+userCounts);
								List<User> getAllStarUser = mergeAllUser1to5StarV2(ratingList, userCounts);
								lead.setAssignee(getAllStarUser.get(0));
								if(userCounts!=null) {
									Integer cInt = userCounts!=null?userCounts.get(getAllStarUser.get(0).getId())!=null?userCounts.get(getAllStarUser.get(0).getId()):0:0;
									userCounts.put(getAllStarUser.get(0).getId(), cInt);
								}
							}

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
						Map<Long, Integer> userCounts = mapCount.get(lead.getOriginalName());
						ArrayList<User> userMerged = mergeStarUserV2(user4Rating,user5Rating,userCounts);
						lead.setAssignee(userMerged.get(0));
						int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;
						System.out.println(cInt);
						if(userCounts!=null) {
							userCounts.put(userMerged.get(0).getId(), cInt+1);
						}
						mapCount.put(lead.getOriginalName(), userCounts);
					}else { 
						//						Long productId=urlsManagment.getId();
						//						Map<Long, Integer>   = mapCount.get(lead.getOriginalName());
						//						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
						//						List<User> getAllStarUser = mergeAllUser1to5StarV2(ratingList, userCounts);
						//						lead.setAssignee(getAllStarUser.get(0));
						//						if(userCounts!=null) {
						//							int cInt = userCounts!=null?userCounts.get(getAllStarUser.get(0).getId())!=null?userCounts.get(getAllStarUser.get(0).getId()):0:0;
						//							userCounts.put(getAllStarUser.get(0).getId(), cInt+1);
						//						}

						Long productId=urlsManagment.getId();
						Map<Long, Integer> userCounts = mapCount.get(lead.getOriginalName());


						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

						if(ratingList!=null&&ratingList.size()>0) {
							System.out.println("User Counts====="+userCounts);
							List<User> getAllStarUser = mergeAllUser1to5StarV2(ratingList, userCounts);
							lead.setAssignee(getAllStarUser.get(0));
							if(userCounts!=null) {
								Integer cInt = userCounts!=null?userCounts.get(getAllStarUser.get(0).getId())!=null?userCounts.get(getAllStarUser.get(0).getId()):0:0;
								userCounts.put(getAllStarUser.get(0).getId(), cInt);
							}
						}

					}
				}
			}
			//			lead.setAuto(false);
			leadRepository.save(lead);



		}
	}

	public  ArrayList<User>mergeStarUserV2(List<User>user4,List<User>user5,Map<Long,Integer>countMap){
		int i=0;
		int j=0;

		ArrayList<User>result =new ArrayList<>();
		int l1=user4!=null?user4.size():0;
		int l2=user5!=null?user5.size():0;
		while(i<l1 &&j<l2) {
			User u4=user4.get(i);
			User u5=user5.get(j);

			int c4=countMap!=null?countMap.get(u4.getId())!=null?countMap.get(u4.getId()):10:10;
			int c5=countMap!=null?countMap.get(u5.getId())!=null?countMap.get(u5.getId()):0:0;

			if(c4>c5) {
				result.add(u5);
				int cInt = countMap!=null?countMap.get(u5.getId())!=null?countMap.get(u5.getId()):0:0;
				//				countMap.put(u5, cInt+1);
				j++;
			}else {
				result.add(u4);
				int cInt=countMap!=null?countMap.get(u4.getId())!=null?countMap.get(u4.getId()):0:0;
				//				countMap.put(u4, cInt+1);
				i++;
			}
		}

		if(i==l1 &&j<l2) {
			while(j<l2) {
				User u5=user5.get(j);
				int c5=countMap!=null?countMap.get(u5.getId())!=null?countMap.get(u5.getId()):0:0;
				//				countMap.put(u5, c5+1);
				result.add(u5);
				j++;
			}
		}
		if(j==l2 &&i<l1) {
			while(i<l1) {
				User u4=user4.get(i);
				int c4=countMap!=null?countMap.get(u4.getId())!=null?countMap.get(u4.getId()):0:0;
				//				countMap.put(u4, c4+1);
				result.add(u4);
				i++;
			}
		}
		System.out.println("=========start=============");
		System.out.println(countMap);
		for(User u:result) {
			int c=countMap!=null?countMap.get(u.getId())!=null?countMap.get(u.getId()):0:0;

			System.out.println(u.getId()+"...count..."+c);
		}
		System.out.println("==========end============");

		return result;
	}


	public List<User> mergeAllUser1to5StarV2(List<Ratings>ratings,Map<Long,Integer>countMap) {
		Map<Long,Integer>countManage=countMap;
		List<User>resUser = new ArrayList<>();
		for(Ratings r:ratings) {
			//			Integer c2=countManage.get(u)+10;
			List<User> userList = r.getRatingsUser();
			String rating=r.getRating();
			for(User u:userList) {
				if("2".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+10;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
				}else if("3".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+20;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  
				}else if("4".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+30;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);
				}else if("5".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+40;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);
				}else {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					if(countManage!=null) {
						countManage.put(u.getId(), count);
					}
					resUser.add(u);
				}

			}
		}
		System.out.println(resUser.size()+". .. size");

		Collections.sort(resUser, (a, b) -> {
			Integer c1=0;
			if(countManage!=null && a.getId()!=null) {
				c1=countManage.get(a.getId())!=null?countManage.get(a.getId()):0;
			}
			Integer c2=0;
			if(countManage!=null && a.getId()!=null) {
				c2=countManage.get(b.getId())!=null?countManage.get(a.getId()):0;
			}
			return c1.compareTo(c2);
		});
		System.out.println("=======================A=============================="+countManage);
		for(User ru:resUser) {
			Integer count=countManage!=null?countManage.get(ru.getId())!=null?countManage.get(ru.getId()):0:0;
			System.out.println(ru.getFullName()+"... testing ...."+count);
		}
		System.out.println("=======================B===============================");

		return resUser;	
	}

	public void assignLeadByCroneV7() {
		List<Long>croneStatus= new ArrayList<>();
		croneStatus.add(1l);
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().filter(i->i.getAssignee()!=null).map(i->i.getAssignee().getId()).collect(Collectors.toList());

		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		System.out.println(cronelead.size());
		for(Lead lead:cronelead) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				System.out.println("Extra Large");

				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);

				}else {
					lead.setAssignee(assignee);
				}
			}else {
				if(lead!=null & lead.getIsUrlsChecked()) {
					if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
						Boolean isProf =false;
						if(lead.getEmail()!=null) {
							isProf= checkEmailDomain(lead.getEmail());
						}
						if(isProf) {
							Long productId=urlsManagment.getId();
							String rating4="4";
							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

							String rating5="5";
							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							//							lead.setAssignee(userMerged.get(0));	
							System.out.println(userCounts+".. user counts");
							User u1=null;
							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
							System.out.println(cInt+"   . . . . size");
							//							int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;
							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							if(count>cInt) {
								//								lead.setAssignee(userMerged.get(0));	
								u1=userMerged.get(0);
							}
							else {
								System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);

									//									int actualCount = userCounts!=null?userCounts.get(userMerged.get(i).getId())!=null?userCounts.get(userMerged.get(i).getId()):0:0;
									if(lockerSize<=actualCount) {
										u1=userMerged.get(i);
										break;
									}
								}
							}

							if(u1==null) {
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

								if(ratingList!=null&&ratingList.size()>0) {

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									System.out.println(getAllStarUser+".. get all star -");
									for(int i=0;i<getAllStarUser.size();i++) {

										if(getAllStarUser.get(i).getLockerSize()<=cInt) {

											u1=getAllStarUser.get(i);
											break;
										}
									}

									//									lead.setAssignee(getAllStarUser.get(0));
								}  
							}
							if(u1!=null) {

								lead.setAssignee(u1);

							}else {

								User user=userRepo.findByIsBackupTeam(true);
								lead.setAssignee(user);
							}

						}else { 

							Long productId=urlsManagment.getId();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
							if(ratingList!=null&&ratingList.size()>0) {
								System.out.println("User Counts..."+userCounts);
								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
								lead.setAssignee(getAllStarUser.get(0));

							}

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
					if(isProf) {
						Long productId=urlsManagment.getId();
						String rating4="4";
						List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

						String rating5="5";
						List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
						Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);

						ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
						User u1=null;

						int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;

						for(int i=0;i<userMerged.size();i++) {
							if(userMerged.get(i).getLockerSize()<=cInt) {

								u1=userMerged.get(i);
								break;
							}
						}
						if(u1==null) {

							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
							ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
							if(ratingList!=null&&ratingList.size()>0) {

								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);

								for(int i=0;i<getAllStarUser.size();i++) {

									if(getAllStarUser.get(i).getLockerSize()<=cInt) {
										u1=getAllStarUser.get(i);
										break;
									}
								}

								//								lead.setAssignee(getAllStarUser.get(0));
							}  
						}
						//						lead.setAssignee(userMerged.get(0));
						//						int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;
						if(u1!=null) {
							lead.setAssignee(u1);

						}else {
							User user=userRepo.findByIsBackupTeam(true);
							lead.setAssignee(user);
						}
						//						lead.setAssignee(userMerged.get(0));						

					}else { 

						Long productId=urlsManagment.getId();
						Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

						if(ratingList!=null&&ratingList.size()>0) {
							List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
							lead.setAssignee(getAllStarUser.get(0));
						}  

					}
				}
			}
			//			lead.setAuto(false);
			leadRepository.save(lead);



		}
	}

	public Map<Long,Integer>calculateLeadCount(String originalName,List<Long>statusId){
		List<Lead>leadList=leadRepository.findAllByStatusIdInAndOriginalName(statusId,originalName);
		Map<Long,Integer>map= new HashMap<>();	
		for(Lead l:leadList) {
			System.out.println(l.getId()+"testingggggggg");
			if(l.getAssignee()!=null) {
				if(map.containsKey(l.getAssignee().getId())) {
					Integer count=map.get(l.getAssignee().getId());
					map.put(l.getAssignee().getId(), count+1);
				}else {
					map.put(l.getAssignee().getId(), 1);
				}
			}
		}
		return map;
	}


	public  ArrayList<User>mergeStarUserV3(List<User>user4,List<User>user5,Map<Long,Integer>countMap){
		int i=0;
		int j=0;

		ArrayList<User>result =new ArrayList<>();
		int l1=user4!=null?user4.size():0;
		int l2=user5!=null?user5.size():0;
		while(i<l1 &&j<l2) {
			User u4=user4.get(i);
			User u5=user5.get(j);

			int c4=countMap!=null?countMap.get(u4.getId())!=null?countMap.get(u4.getId())+10:10:10;
			int c5=countMap!=null?countMap.get(u5.getId())!=null?countMap.get(u5.getId()):0:0;

			if(c4>c5) {
				result.add(u5);
				j++;
			}else {
				result.add(u4);
				i++;
			}
		}

		if(i==l1 &&j<l2) {
			while(j<l2) {
				User u5=user5.get(j);
				result.add(u5);
				j++;
			}
		}
		if(j==l2 &&i<l1) {
			while(i<l1) {
				User u4=user4.get(i);
				result.add(u4);
				i++;
			}
		}
		//		System.out.println("=========start=============");
		//		System.out.println(countMap);
		//		for(User u:result) {
		//			int c=countMap!=null?countMap.get(u.getId())!=null?countMap.get(u.getId()):0:0;
		//
		//			System.out.println(u.getId()+"...count..."+c);
		//		}
		//		System.out.println("==========end============");

		return result;
	}

	public List<User> mergeAllUser1to5StarV3(List<Ratings>ratings,Map<Long,Integer>countMap) {
		//		Map<Long,Integer>countManage=countMap;
		Map<Long,Integer>countManage = new HashMap<Long,Integer>(countMap);
		List<User>resUser = new ArrayList<>();
		for(Ratings r:ratings) {
			//			Integer c2=countManage.get(u)+10;
			List<User> userList = r.getRatingsUser();
			String rating=r.getRating();
			for(User u:userList) {
				if("2".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+30;
					if(count!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  

				}else if("3".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+2000;
					if(count!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  
				}else if("4".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+10;
					if(count!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);
				}else if("5".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count;
					if(count!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);
				}else {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					if(count!=null) {
						countManage.put(u.getId(), count);
					}
					resUser.add(u);
				}

			}
		}
		Collections.sort(resUser, (a, b) -> {
			Integer c1=0;
			if(countManage!=null && a.getId()!=null) {
				c1=countManage.get(a.getId())!=null?countManage.get(a.getId()):0;
			}
			Integer c2=0;
			if(countManage!=null && a.getId()!=null) {
				c2=countManage.get(b.getId())!=null?countManage.get(b.getId()):0;
			}
			System.out.println("Testing . . .. ");
			if(c1>c2) {
				return 1;
			}else if(c2>c1) {
				return -1;
			}else {
				Integer c11=0;
				if(countMap!=null && a.getId()!=null) {
					c11=countMap.get(a.getId())!=null?countMap.get(a.getId()):0;
				}
				Integer c22=0;
				if(countMap!=null && a.getId()!=null) {
					c22=countMap.get(b.getId())!=null?countMap.get(b.getId()):0;
				}
				double avg1=c11!=null?((double)a.getLockerSize())/((double)c11):a.getLockerSize();
				double avg2=c22!=null?((double)b.getLockerSize())/((double)c22):b.getLockerSize();

				if(avg1>avg2) {
					return 1;
				}else if(avg1<avg2){
					return -1;
				}else {
					return 0;
				}
			}
		});
		System.out.println("array List current data "+resUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

		//		System.out.println("=======================A=============================="+countManage);
		//		for(User ru:resUser) {
		//			Integer count=countManage!=null?countManage.get(ru.getId())!=null?countManage.get(ru.getId()):0:0;
		//		   System.out.println(ru.getFullName()+"... testing ...."+count);
		//		}
		//		System.out.println("=======================B===============================");

		return resUser;	
	}
	public void assignLeadByCroneV8() {
		List<Long>croneStatus= new ArrayList<>();
		croneStatus.add(1l);
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<String>originalName=cronelead.stream().map(i->i.getOriginalName()).collect(Collectors.toList());
		List<Long> assigneeIdList = cronelead.stream().filter(i->i.getAssignee()!=null).map(i->i.getAssignee().getId()).collect(Collectors.toList());

		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		System.out.println(cronelead.size());
		for(Lead lead:cronelead) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				System.out.println("Extra Large");

				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);

				}else {
					lead.setAssignee(assignee);
				}
			}else {
				if(lead!=null & lead.getIsUrlsChecked()) {
					if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
						Boolean isProf =false;
						if(lead.getEmail()!=null) {
							isProf= checkEmailDomain(lead.getEmail());
						}
						if(isProf) {
							Long productId=urlsManagment.getId();
							String rating4="4";
							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

							String rating5="5";
							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							//							lead.setAssignee(userMerged.get(0));	
							System.out.println(userCounts+".. user counts");
							User u1=null;
							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
							System.out.println(cInt+"   . . . . size");
							//							int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;
							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							System.out.println(count+".........a ...."+cInt);
							if(count>cInt) {
								System.out.println(count+"........bbb ...."+cInt);

								//								lead.setAssignee(userMerged.get(0));	
								u1=userMerged.get(0);
							}
							else {
								System.out.println("............... testingggg............................");
								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);

									//									int actualCount = userCounts!=null?userCounts.get(userMerged.get(i).getId())!=null?userCounts.get(userMerged.get(i).getId()):0:0;
									System.out.println("............... 111111111111111............................");

									if(lockerSize<=actualCount) {
										//		                                System.out.println("............... i............................");

										u1=userMerged.get(i);
										break;
									}
								}
							}

							if(u1==null) {
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

								if(ratingList!=null&&ratingList.size()>0) {

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									System.out.println(getAllStarUser+".. get all star -");
									for(int i=0;i<getAllStarUser.size();i++) {

										if(getAllStarUser.get(i).getLockerSize()<=cInt) {

											u1=getAllStarUser.get(i);
											break;
										}
									}

									//									lead.setAssignee(getAllStarUser.get(0));
								}  
							}
							if(u1!=null) {

								lead.setAssignee(u1);

							}else {

								User user=userRepo.findByIsBackupTeam(true);
								lead.setAssignee(user);
							}

						}else { 

							Long productId=urlsManagment.getId();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
							if(ratingList!=null&&ratingList.size()>0) {
								System.out.println("User Counts..."+userCounts);
								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
								lead.setAssignee(getAllStarUser.get(0));

							}

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
					if(isProf) {
						Long productId=urlsManagment.getId();
						String rating4="4";
						List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

						String rating5="5";
						List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
						Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);

						ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
						User u1=null;

						int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;

						for(int i=0;i<userMerged.size();i++) {
							if(userMerged.get(i).getLockerSize()<=cInt) {

								u1=userMerged.get(i);
								break;
							}
						}
						if(u1==null) {

							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
							ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
							if(ratingList!=null&&ratingList.size()>0) {

								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);

								for(int i=0;i<getAllStarUser.size();i++) {

									if(getAllStarUser.get(i).getLockerSize()<=cInt) {
										u1=getAllStarUser.get(i);
										break;
									}
								}

								//								lead.setAssignee(getAllStarUser.get(0));
							}  
						}
						//						lead.setAssignee(userMerged.get(0));
						//						int cInt = userCounts!=null?userCounts.get(userMerged.get(0).getId())!=null?userCounts.get(userMerged.get(0).getId()):0:0;
						if(u1!=null) {
							lead.setAssignee(u1);

						}else {
							User user=userRepo.findByIsBackupTeam(true);
							lead.setAssignee(user);
						}
						//						lead.setAssignee(userMerged.get(0));						

					}else { 

						Long productId=urlsManagment.getId();
						Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

						if(ratingList!=null&&ratingList.size()>0) {
							List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
							lead.setAssignee(getAllStarUser.get(0));
						}  

					}
				}
			}
			//			lead.setAuto(false);
			leadRepository.save(lead);



		}
	}
	public void assignLeadByCroneV9() {
		List<Long>croneStatus= new ArrayList<>();


		croneStatus.add(1l);
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		int count1=0;
		int count2=0;

		System.out.println(cronelead.size());
		for(Lead lead:cronelead) {
			System.out.println("lead "+lead.getId());
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println("bbbbbbbbb");
			if(existingLead!=null && existingLead.size()>1) {
				System.out.println("cccccccccc");

				User assignee=getAssignee(existingLead);
				//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
				if(assignee!=null &&assignee.isDeleted()) {
					User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
					lead.setAssignee(user);
				}else {
					lead.setAssignee(assignee);
				}
				System.out.println("cccccc");

			}else {
				System.out.println("dddddddddddd");

				UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
				count2++;

				if(lead!=null & lead.getIsUrlsChecked()) {
					System.out.println("eeeeeeeeeeeeeee");

					if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
						System.out.println("ffffffffffff");

						count1++;
						Boolean isProf =false;

						if(lead.getEmail()!=null) {
							isProf= checkEmailDomain(lead.getEmail());
						}

						if(isProf) {
							System.out.println("gggggggg");

							Long productId=urlsManagment.getId();
							String rating4="4";
							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
							System.out.println("hhhhhhhhhhhhh");

							String rating5="5";
							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
							System.out.println("iiiiiiiiiiiiiiiiiiiiii");

							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							System.out.println("jjjjjjjjjjjjjjjjj");

							User u1=null;
							//							System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");

							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							//							System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
							if(count>cInt) {
								System.out.println("kkkkkkkkkkkkkk");

								u1=userMerged.get(0);
							}
							else {
								System.out.println("llllllllllll"+userMerged.size());

								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(i).getId(),croneStatus);
									if(lockerSize>=actualCount) {
										u1=userMerged.get(i);
									}
								}
							}
							System.out.println("mmmmmmmm");

							if(u1==null) {
								System.out.println("nnnnnnnnnnnnn");

								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
								System.out.println("ooooooooooooooooooooo"+ratingList.size());

								if(ratingList!=null && ratingList.size()>0) {
									System.out.println("pppppppppppppppppppppppppppp");

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									System.out.println("p1-testfgddgdfgdfg  "+getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

									for(int i=0;i<getAllStarUser.size();i++) {

										//										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
										//
										//											u1=getAllStarUser.get(i);
										//											break;
										//										}
										int lockerSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
										int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
										System.out.println("Locker size "+lockerSize+"testing    . .  "+actualCount+"    count "+getAllStarUser.get(i));

										if(lockerSize>=actualCount) {
											u1=getAllStarUser.get(i);
										}

									}

								}  
							}
							if(u1!=null) {
								System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  .."+u1.getFullName());

								lead.setAssignee(u1);

							}else {
								System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

								User user=userRepo.findByIsBackupTeam(true);
								System.out.println("user is.... "+user);
								lead.setAssignee(user);
							}

						}else { 
							System.out.println("");

							//							System.out.println("Test1 . . .. . . . . .. . .");
							User u=null;
							Long productId=urlsManagment.getId();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
							if(ratingList!=null&&ratingList.size()>0) {
								System.out.println("tttttttttttttttttt");

								List<User> getAllStarUser = mergeAllUser1to3StarV3(ratingList, userCounts);	
								int lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
								int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
								//                                 System.out.println(getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

								if(lockerSize>=actualCount) {
									lead.setAssignee(getAllStarUser.get(0));
								}else {
									System.out.println("uuuuuuuuuuuuuuu");

									List<User> get4to5StarUser = mergeAllUser4to5StarV3(ratingList, userCounts);
									getAllStarUser.addAll(get4to5StarUser);
									for(int i=0;i<getAllStarUser.size();i++) {
										int lSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
										int aCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
										if(lSize>=aCount) {
											u=getAllStarUser.get(i);
											break;
										}
									}
									lead.setAssignee(u);
								}
							}

						}
					}else {
						System.out.println("vvvvvvvvvvvvvvvvvvv");

						if(ql>0) {
							System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
							lead.setAssignee(qualityUser.get(qi));
							qi++;
							if(qi==ql) {
								qi=0;
							}	
						}
					}
				}else{
					System.out.println("xxxxxxxxxxxxxxxxxx");

					Boolean isProf =false;
					if(lead.getEmail()!=null) {
						isProf= checkEmailDomain(lead.getEmail());
						System.out.println("yyyyyyyyyyyyyyyyyyyy");

					}
					if(isProf) {
						System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz");

						Long productId=urlsManagment.getId();
						String rating4="4";
						List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

						String rating5="5";
						List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
						Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);

						ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
						User u1=null;
						//						System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
						int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
						int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
						//						System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
						if(count>cInt) {

							u1=userMerged.get(0);
						}
						else {
							for(int i=1;i<userMerged.size();i++) {
								int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
								int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
								if(lockerSize>=actualCount) {
									u1=userMerged.get(i);
								}
							}
						}

						if(u1==null) {

							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
							ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

							if(ratingList!=null&&ratingList.size()>0) {

								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
								for(int i=0;i<getAllStarUser.size();i++) {

									if(getAllStarUser.get(i).getLockerSize()<=cInt) {

										u1=getAllStarUser.get(i);
										break;
									}
								}

							}  
						}
						if(u1!=null) {

							lead.setAssignee(u1);

						}else {
							System.out.println("User is in backup team"+lead.getId());
							User user=userRepo.findByIsBackupTeam(true);
							if(user!=null) {
								lead.setAssignee(user);

								sendMail();
							}else {
								sendMail();

							}
						}

					}else { 
						System.out.println("a1aaaaaaaaaaaaaaaaaaaaaaaaaa");

						Long productId=urlsManagment.getId();
						Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
						List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

						if(ratingList!=null&&ratingList.size()>0) {
							List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
							lead.setAssignee(getAllStarUser.get(0));
						}  

					}
				}
			}

			//			lead.setAuto(false);
			//			System.out.println(count1+"... this is count 1");
			//			System.out.println(count2+"... this is count  2"+lead.getId());

			leadRepository.save(lead);



		}
	}


	public List<User> mergeAllUser1to3StarV3(List<Ratings>ratings,Map<Long,Integer>countMap) {
		Map<Long,Integer>countManage=countMap;
		List<User>resUser = new ArrayList<>();
		for(Ratings r:ratings) {
			//			Integer c2=countManage.get(u)+10;
			List<User> userList = r.getRatingsUser();
			String rating=r.getRating();
			for(User u:userList) {
				//				System.out.println(u.getFullName()+"full name.."+rating);
				if("1".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+20;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  

				}else if("2".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+10;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  
				}else if("3".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  
				}

			}
		}
		Collections.sort(resUser, (a, b) -> {
			Integer c1=0;
			if(countManage!=null && a.getId()!=null) {
				c1=countManage.get(a.getId())!=null?countManage.get(a.getId()):0;
			}
			Integer c2=0;
			if(countManage!=null && a.getId()!=null) {
				c2=countManage.get(b.getId())!=null?countManage.get(b.getId()):0;
			}
			if(c1>c2) {
				return 1;
			}else if(c2>c1) {
				return -1;
			}else {

				Integer c11=0;
				if(countMap!=null && a.getId()!=null) {
					c11=countMap.get(a.getId())!=null?countMap.get(a.getId()):0;
				}
				Integer c22=0;
				if(countMap!=null && a.getId()!=null) {
					c22=countMap.get(b.getId())!=null?countMap.get(b.getId()):0;
				}
				double avg1=c11!=null?((double)a.getLockerSize())/((double)c11):a.getLockerSize();
				double avg2=c22!=null?((double)b.getLockerSize())/((double)c22):b.getLockerSize();

				if(avg1>avg2) {
					return 1;
				}else if(avg1<avg2){
					return -1;
				}else {
					return 0;
				}
			}
		});
		//		System.out.println("=======================A=============================="+countManage);
		//		for(User ru:resUser) {
		//			Integer count=countManage!=null?countManage.get(ru.getId())!=null?countManage.get(ru.getId()):0:0;
		//		   System.out.println(ru.getFullName()+"... testing ...."+count);
		//		}
		//		System.out.println("=======================B===============================");

		System.out.println("res user .. .... ... ..."+resUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));
		return resUser;	
	}

	public List<User> mergeAllUser4to5StarV3(List<Ratings>ratings,Map<Long,Integer>countMap) {
		Map<Long,Integer>countManage=countMap;
		List<User>resUser = new ArrayList<>();
		for(Ratings r:ratings) {
			//			Integer c2=countManage.get(u)+10;
			List<User> userList = r.getRatingsUser();
			String rating=r.getRating();
			for(User u:userList) {
				if("4".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+10;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
				}else if("5".equals(rating)) {
					Integer count=countManage!=null?countManage.get(u.getId())!=null?countManage.get(u.getId()):0:0;
					Integer c2=count+0;
					if(countManage!=null) {
						countManage.put(u.getId(), c2);
					}
					resUser.add(u);  
				}

			}
		}
		Collections.sort(resUser, (a, b) -> {
			Integer c1=0;
			if(countManage!=null && a.getId()!=null) {
				c1=countManage.get(a.getId())!=null?countManage.get(a.getId()):0;
			}
			Integer c2=0;
			if(countManage!=null && a.getId()!=null) {
				c2=countManage.get(b.getId())!=null?countManage.get(b.getId()):0;
			}
			if(c1>c2) {
				return 1;
			}else if(c2>c1) {
				return -1;
			}else {

				Integer c11=0;
				if(countMap!=null && a.getId()!=null) {
					c11=countMap.get(a.getId())!=null?countMap.get(a.getId()):0;
				}
				Integer c22=0;
				if(countMap!=null && a.getId()!=null) {
					c22=countMap.get(b.getId())!=null?countMap.get(b.getId()):0;
				}
				double avg1=c11!=null?((double)a.getLockerSize())/((double)c11):a.getLockerSize();
				double avg2=c22!=null?((double)b.getLockerSize())/((double)c22):b.getLockerSize();

				if(avg1>avg2) {
					return 1;
				}else if(avg1<avg2){
					return -1;
				}else {
					return 0;
				}
			}
		});
		//		System.out.println("=======================A=============================="+countManage);
		//		for(User ru:resUser) {
		//			Integer count=countManage!=null?countManage.get(ru.getId())!=null?countManage.get(ru.getId()):0:0;
		//		   System.out.println(ru.getFullName()+"... testing ...."+count);
		//		}
		//		System.out.println("=======================B===============================");

		return resUser;	
	}
	public boolean sendMail(){
		String[] emailTo= {"erp@corpseed.com"};
		String[] ccPersons= {"aryan.chaurasia@corpseed.com"};
		String subject="Alert ! for creation Backup Team";

		Context context = new Context();
		context.setVariable("Rurl", "https://www.corpseed.com/");		

		mailSendSerivceImpl.sendEmail(emailTo, ccPersons, ccPersons, subject, context, "auto_backup.html");

		return true;
	}

	public User lastActiveUser(Long leadId) {
		LeadHistory leadHistory=null;
		List<LeadHistory> leadHistoryList = leadHistoryRepository.findByEventTypeAndLeadId("lead assignee change",leadId);
		System.out.println("history size . .. . .  . . "+leadHistoryList.size());
		if(leadHistoryList!=null && (leadHistoryList.size()!=0)) {
			leadHistoryList=leadHistoryList.stream().sorted(Comparator.comparing(LeadHistory::getId).reversed()).filter(i->(i!=null?i.getPrevUser()!=null?i.getPrevUser().getDepartment():"NA":"NA").equals("Sales")).collect(Collectors.toList());
			Optional<LeadHistory> lHistory = leadHistoryList!=null?leadHistoryList.stream().findFirst():null;
			if(lHistory!=null&&lHistory.isPresent() &&lHistory.get()!=null) {
				leadHistory=lHistory.get();
			}
		}
		//		System.out.println(leadHistory!=null?leadHistory.getPrevUser():null);
		return leadHistory!=null?leadHistory.getPrevUser():null;


	}

	public void assignLeadByCroneV10() {

		List<Long>croneStatus= new ArrayList<>();


		croneStatus.add(1l);
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		int count1=0;
		int count2=0;

		System.out.println(cronelead.size());

		for(Lead lead:cronelead) {
			System.out.println("lead "+lead.getId());
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println("bbbbbbbbb");
			User lastUser = lastActiveUser(lead.getId());

			if(lead.isBacklogTask()&& lastUser!=null) {
				//				User user = lastActiveUser(lead.getId());
				lead.setAssignee(lastUser);
			}else {

				if(existingLead!=null && existingLead.size()>1) {
					System.out.println("cccccccccc");

					User assignee=getAssignee(existingLead);
					//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
					if(assignee!=null &&assignee.isDeleted()) {
						User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
						lead.setAssignee(user);
					}else {
						lead.setAssignee(assignee);
					}
					System.out.println("cccccc");

				}else {
					System.out.println("dddddddddddd");

					UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
					count2++;

					if(lead!=null & lead.getIsUrlsChecked()) {
						System.out.println("eeeeeeeeeeeeeee");

						if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
							System.out.println("ffffffffffff");

							count1++;
							Boolean isProf =false;

							if(lead.getEmail()!=null) {
								isProf= checkEmailDomain(lead.getEmail());
							}

							if(isProf) {
								System.out.println("gggggggg");

								Long productId=urlsManagment.getId();
								String rating4="4";
								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
								System.out.println("hhhhhhhhhhhhh");

								String rating5="5";
								List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
								System.out.println("iiiiiiiiiiiiiiiiiiiiii");

								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
								System.out.println("jjjjjjjjjjjjjjjjj");

								User u1=null;
								//					System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");

								int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
								int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
								//					System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
								if(count>cInt) {
									System.out.println("kkkkkkkkkkkkkk");

									u1=userMerged.get(0);
								}
								else {
									System.out.println("llllllllllll"+userMerged.size());

									for(int i=1;i<userMerged.size();i++) {
										int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
										int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(i).getId(),croneStatus);
										if(lockerSize>=actualCount) {
											u1=userMerged.get(i);
										}
									}
								}
								System.out.println("mmmmmmmm");

								if(u1==null) {
									System.out.println("nnnnnnnnnnnnn");

									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
									ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
									System.out.println("ooooooooooooooooooooo"+ratingList.size());

									if(ratingList!=null && ratingList.size()>0) {
										System.out.println("pppppppppppppppppppppppppppp");

										List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
										System.out.println("p1-testfgddgdfgdfg  "+getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

										for(int i=0;i<getAllStarUser.size();i++) {

											//										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
											//
											//											u1=getAllStarUser.get(i);
											//											break;
											//										}
											int lockerSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											System.out.println("Locker size "+lockerSize+"testing    . .  "+actualCount+"    count "+getAllStarUser.get(i));

											if(lockerSize>=actualCount) {
												u1=getAllStarUser.get(i);
											}

										}

									}  
								}
								if(u1!=null) {
									System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  .."+u1.getFullName());

									lead.setAssignee(u1);

								}else {
									System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

									User user=userRepo.findByIsBackupTeam(true);
									System.out.println("user is.... "+user);
									lead.setAssignee(user);
								}

							}else { 
								System.out.println("sssssssssssssssssssssssssssssssssssssss");

								//					System.out.println("Test1 . . .. . . . . .. . .");
								User u=null;
								Long productId=urlsManagment.getId();
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
								if(ratingList!=null&&ratingList.size()>0) {
									System.out.println("tttttttttttttttttt");

									List<User> getAllStarUser = mergeAllUser1to3StarV3(ratingList, userCounts);	
									int lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
									//                         System.out.println(getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

									if(lockerSize>=actualCount) {
										lead.setAssignee(getAllStarUser.get(0));
									}else {
										System.out.println("uuuuuuuuuuuuuuu");

										List<User> get4to5StarUser = mergeAllUser4to5StarV3(ratingList, userCounts);
										getAllStarUser.addAll(get4to5StarUser);

										for(int i=0;i<getAllStarUser.size();i++) {
											int lSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int aCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											if(lSize>=aCount) {
												u=getAllStarUser.get(i);
												break;
											}
										}
										lead.setAssignee(u);
									}
								}

							}
						}else {
							System.out.println("vvvvvvvvvvvvvvvvvvv");

							if(ql>0) {
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
								lead.setAssignee(qualityUser.get(qi));
								qi++;
								if(qi==ql) {
									qi=0;
								}	
							}
						}
					}else{
						System.out.println("xxxxxxxxxxxxxxxxxx");

						Boolean isProf =false;
						if(lead.getEmail()!=null) {
							isProf= checkEmailDomain(lead.getEmail());
							System.out.println("yyyyyyyyyyyyyyyyyyyy");

						}
						if(isProf) {
							System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz");

							Long productId=urlsManagment.getId();
							String rating4="4";
							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();

							String rating5="5";
							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);

							ArrayList<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							User u1=null;
							//				System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							//				System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
							if(count>cInt) {

								u1=userMerged.get(0);
							}
							else {
								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
									if(lockerSize>=actualCount) {
										u1=userMerged.get(i);
									}
								}
							}

							if(u1==null) {

								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

								if(ratingList!=null&&ratingList.size()>0) {

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									for(int i=0;i<getAllStarUser.size();i++) {

										if(getAllStarUser.get(i).getLockerSize()<=cInt) {

											u1=getAllStarUser.get(i);
											break;
										}
									}

								}  
							}
							if(u1!=null) {

								lead.setAssignee(u1);

							}else {
								System.out.println("User is in backup team"+lead.getId());
								User user=userRepo.findByIsBackupTeam(true);
								if(user!=null) {
									lead.setAssignee(user);

									sendMail();
								}else {
									sendMail();

								}
							}

						}else { 
							System.out.println("a1aaaaaaaaaaaaaaaaaaaaaaaaaa");

							Long productId=urlsManagment.getId();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

							if(ratingList!=null&&ratingList.size()>0) {
								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
								lead.setAssignee(getAllStarUser.get(0));
							}  

						}
					}
				}
			}

			//	lead.setAuto(false);
			//	System.out.println(count1+"... this is count 1");
			//	System.out.println(count2+"... this is count  2"+lead.getId());

			leadRepository.save(lead);



		}
	}

	//	@Scheduled(cron = "0 * * ? * *", zone = "IST")
	public void assignLeadByCroneV11() {
		List<Long>croneStatus= new ArrayList<>();

		List<Status>sList=statusRepository.findByEnableAutoAssign(true);
		sList.addAll(sList);
		croneStatus.addAll(sList.stream().map(i->i.getId()).collect(Collectors.toList()));
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		System.out.println("Quality User . . . "+qualityUser.size());
		qualityUser=qualityUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		int count1=0;
		int count2=0;

		System.out.println(cronelead.size());

		for(Lead lead:cronelead) {
			System.out.println("lead "+lead.getId());
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println("bbbbbbbbb");
			User lastUser = lastActiveUser(lead.getId());

			if(lead.isBacklogTask()&& (!lead.isNotAssignSame()) && lastUser!=null) {
				//				User user = lastActiveUser(lead.getId());
				lead.setAssignee(lastUser);
			}else {

				if(existingLead!=null && existingLead.size()>1) {
					System.out.println("cccccccccc");

					User assignee=getAssignee(existingLead);
					//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
					if(assignee!=null &&assignee.isDeleted()) {
						User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
						lead.setAssignee(user);
					}else {
						lead.setAssignee(assignee);
					}
					System.out.println("cccccc");

				}else {
					System.out.println("dddddddddddd");

					UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
					count2++;

					if(lead!=null & lead.getIsUrlsChecked()) {
						System.out.println("eeeeeeeeeeeeeee");

						if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
							System.out.println("ffffffffffff");

							count1++;
							Boolean isProf =false;

							if(lead.getEmail()!=null) {
								isProf= checkEmailDomain(lead.getEmail());
							}

							if(isProf) {
								System.out.println("gggggggg");

								Long productId=urlsManagment.getId();
								String rating4="4";
								Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
								List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;

								//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
								System.out.println("hhhhhhhhhhhhh");

								String rating5="5";
								Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
								List<User> user5Rating = ratings5.getRatingsUser();

								//								 List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
								System.out.println("iiiiiiiiiiiiiiiiiiiiii");

								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
								userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

								System.out.println("jjjjjjjjjjjjjjjjj");

								User u1=null;
								//					System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");

								int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
								int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
								//					System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
								if(count>cInt) {
									System.out.println("kkkkkkkkkkkkkk");

									u1=userMerged.get(0);
								}
								else {
									System.out.println("llllllllllll"+userMerged.size());

									for(int i=1;i<userMerged.size();i++) {
										int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
										int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(i).getId(),croneStatus);
										if(lockerSize>=actualCount) {
											u1=userMerged.get(i);
										}
									}
								}
								System.out.println("mmmmmmmm");

								if(u1==null) {
									System.out.println("nnnnnnnnnnnnn");

									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
									ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
									System.out.println("ooooooooooooooooooooo"+ratingList.size());

									if(ratingList!=null && ratingList.size()>0) {
										System.out.println("pppppppppppppppppppppppppppp");

										List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
										System.out.println("p1-testfgddgdfgdfg  "+getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

										for(int i=0;i<getAllStarUser.size();i++) {

											//										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
											//
											//											u1=getAllStarUser.get(i);
											//											break;
											//										}
											int lockerSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											System.out.println("Locker size "+lockerSize+"testing    . .  "+actualCount+"    count "+getAllStarUser.get(i));

											if(lockerSize>=actualCount) {
												u1=getAllStarUser.get(i);
											}

										}

									}  
								}
								if(u1!=null) {
									System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  .."+u1.getFullName());

									lead.setAssignee(u1);

								}else {
									System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

									User user=userRepo.findByIsBackupTeam(true);
									System.out.println("user is.... "+user);
									lead.setAssignee(user);
								}

							}else { 
								System.out.println("sssssssssssssssssssssssssssssssssssssss");

								//					System.out.println("Test1 . . .. . . . . .. . .");
								User u=null;
								Long productId=urlsManagment.getId();
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
								if(ratingList!=null&&ratingList.size()>0) {
									System.out.println("tttttttttttttttttt");

									List<User> getAllStarUser = mergeAllUser1to3StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

									int lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
									//                         System.out.println(getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

									if(lockerSize>=actualCount) {
										lead.setAssignee(getAllStarUser.get(0));
									}else {
										System.out.println("uuuuuuuuuuuuuuu");

										List<User> get4to5StarUser = mergeAllUser4to5StarV3(ratingList, userCounts);
										getAllStarUser.addAll(get4to5StarUser);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

										for(int i=0;i<getAllStarUser.size();i++) {
											int lSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int aCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											if(lSize>=aCount) {
												u=getAllStarUser.get(i);
												break;
											}
										}
										lead.setAssignee(u);
									}
								}

							}
						}else {
							System.out.println("vvvvvvvvvvvvvvvvvvv");

							if(ql>0) {
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
								lead.setAssignee(qualityUser.get(qi));
								qi++;
								if(qi==ql) {
									qi=0;
								}	
							}
						}
						lead.setIsUrlsChecked(false);
					}else{
						System.out.println("xxxxxxxxxxxxxxxxxx");

						Boolean isProf =false;
						if(lead.getEmail()!=null) {
							isProf= checkEmailDomain(lead.getEmail());
							System.out.println("yyyyyyyyyyyyyyyyyyyy");

						}
						if(isProf) {
							System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzz");

							Long productId=urlsManagment.getId();
							//							String rating4="4";
							//							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
							//
							//							String rating5="5";
							//							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();

							String rating4="4";
							Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
							List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;

							//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
							System.out.println("hhhhhhhhhhhhh");

							String rating5="5";
							Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
							List<User> user5Rating = ratings5.getRatingsUser();

							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);

							List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

							User u1=null;
							//				System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							//				System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
							if(count>cInt) {

								u1=userMerged.get(0);
							}
							else {
								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
									if(lockerSize>=actualCount) {
										u1=userMerged.get(i);
									}
								}
							}

							if(u1==null) {

								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

								if(ratingList!=null&&ratingList.size()>0) {

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

									for(int i=0;i<getAllStarUser.size();i++) {

										if(getAllStarUser.get(i).getLockerSize()<=cInt) {

											u1=getAllStarUser.get(i);
											break;
										}
									}

								}  
							}
							if(u1!=null) {

								lead.setAssignee(u1);

							}else {
								System.out.println("User is in backup team"+lead.getId());
								User user=userRepo.findByIsBackupTeam(true);
								if(user!=null) {
									lead.setAssignee(user);

									sendMail();
								}else {
									sendMail();

								}
							}

						}else { 
							System.out.println("a1aaaaaaaaaaaaaaaaaaaaaaaaaa");

							Long productId=urlsManagment.getId();
							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

							if(ratingList!=null&&ratingList.size()>0) {
								List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
								getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

								lead.setAssignee(getAllStarUser.get(0));
							}  

						}
					}
				}
			}
			lead.setIsUrlsChecked(false);
			lead.setAuto(false);
			//	System.out.println(count1+"... this is count 1");
			//	System.out.println(count2+"... this is count  2"+lead.getId());

			leadRepository.save(lead);



		}
	}

	public User sortArrayAndSingleUser(List<User>userList,List<Long>statusIds){
		System.out.println("Testing env  . . . . .");
		List<Long> userIds = userList.stream().map(i->i.getId()).collect(Collectors.toList());
		List<Lead> leadList = leadRepository.findAllByAssigneeIdInAndStatusIdIn(userIds, statusIds);
		Map<Long ,Long>res = new HashMap<>();
		for(Long uId:userIds) {
			res.put(uId, 0l);
		}
		for(Lead l:leadList) {
			Long assigneeId = l.getAssignee().getId();

			Long count = res.get(assigneeId);
			res.put(assigneeId, count+1);
		}

		User pUser=null;
		Long max =0l;
		for (User u:userList) {

			if(pUser==null) {
				pUser=u;
				max=res.get(u.getId());
			}else {
				if(res.get(u.getId())<max) {
					max=res.get(u.getId());
					pUser=u;
				}
			}
		}
		System.out.println("user data  . . "+pUser);
		return pUser;

	}
	//	@Scheduled(cron = "0 * * ? * *", zone = "IST")

	public void assignLeadByCroneV12() {
		List<Long>croneStatus= new ArrayList<>();
		System.out.println("First tsting  .....");
		List<Status>sList=statusRepository.findByEnableAutoAssign(true);
		sList.addAll(sList);
		croneStatus.addAll(sList.stream().map(i->i.getId()).collect(Collectors.toList()));
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		qualityUser=qualityUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		int count1=0;
		int count2=0;

		System.out.println(cronelead.size());

		for(Lead lead:cronelead) {
			System.out.println("lead "+lead.getId());
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println("bbbbbbbbb");
			User lastUser = lastActiveUser(lead.getId());

			if(lead.isBacklogTask()&& (!lead.isNotAssignSame()) && lastUser!=null) {
				//				User user = lastActiveUser(lead.getId());
				lead.setAssignee(lastUser);
			}else {

				if(existingLead!=null && existingLead.size()>1) {
					System.out.println("cccccccccc");

					User assignee=getAssignee(existingLead);
					//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
					if(assignee!=null &&assignee.isDeleted()) {
						User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
						lead.setAssignee(user);
					}else {
						lead.setAssignee(assignee);
					}
					System.out.println("cccccc");

				}else {
					System.out.println("dddddddddddd");
					System.out.println(lead.getOriginalName()+""+lead.getId());

					UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
					System.out.println(urlsManagment+"tttttttttttttttttttttttttttttttttttttttttttttttttLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

					count2++;

					if(lead!=null & lead.getIsUrlsChecked()) {
						System.out.println("eeeeeeeeeeeeeee");

						if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
							System.out.println("ffffffffffff");

							count1++;
							Boolean isProf =false;

							if(lead.getEmail()!=null) {
								isProf= checkEmailDomain(lead.getEmail());
							}

							if(isProf) {
								System.out.println("gggggggg");

								Long productId=urlsManagment.getId();
								String rating4="4";
								Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
								List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;

								//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
								System.out.println("hhhhhhhhhhhhh");

								String rating5="5";
								Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
								List<User> user5Rating = ratings5.getRatingsUser();

								//								 List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
								System.out.println("iiiiiiiiiiiiiiiiiiiiii");

								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
								userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

								System.out.println("jjjjjjjjjjjjjjjjj");

								User u1=null;
								//					System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");

								int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
								int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
								//					System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
								if(count>cInt) {
									System.out.println("kkkkkkkkkkkkkk");

									u1=userMerged.get(0);
								}
								else {
									System.out.println("llllllllllll"+userMerged.size());

									for(int i=1;i<userMerged.size();i++) {
										int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
										int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(i).getId(),croneStatus);
										if(lockerSize>=actualCount) {
											u1=userMerged.get(i);
										}
									}
								}
								System.out.println("mmmmmmmm");

								if(u1==null) {
									System.out.println("nnnnnnnnnnnnn");

									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
									ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
									System.out.println("ooooooooooooooooooooo"+ratingList.size());

									if(ratingList!=null && ratingList.size()>0) {
										System.out.println("pppppppppppppppppppppppppppp");

										List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
										System.out.println("p1-testfgddgdfgdfg  "+getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

										for(int i=0;i<getAllStarUser.size();i++) {

											//										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
											//
											//											u1=getAllStarUser.get(i);
											//											break;
											//										}
											int lockerSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											System.out.println("Locker size "+lockerSize+"testing    . .  "+actualCount+"    count "+getAllStarUser.get(i));

											if(lockerSize>=actualCount) {
												u1=getAllStarUser.get(i);
											}

										}

									}  
								}
								if(u1!=null) {
									System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  .."+u1.getFullName());

									lead.setAssignee(u1);

								}else {
									System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");

									User user=userRepo.findByIsBackupTeam(true);
									System.out.println("user is.... "+user);
									lead.setAssignee(user);
								}

							}else { 
								System.out.println("sssssssssssssssssssssssssssssssssssssss");

								//					System.out.println("Test1 . . .. . . . . .. . .");
								User u=null;
								Long productId=urlsManagment.getId();
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
								if(ratingList!=null&&ratingList.size()>0) {
									System.out.println("tttttttttttttttttt");

									List<User> getAllStarUser = mergeAllUser1to3StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
									int lockerSize=0;
									int actualCount=0;
									if(getAllStarUser!=null && getAllStarUser.size()!=0) {
										 lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
										 actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
										
									}
//									int lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
//									int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
									//                         System.out.println(getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));

									if(lockerSize>=actualCount) {
										lead.setAssignee(getAllStarUser.get(0));
									}else {
										System.out.println("uuuuuuuuuuuuuuu");

										List<User> get4to5StarUser = mergeAllUser4to5StarV3(ratingList, userCounts);
										getAllStarUser.addAll(get4to5StarUser);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

										for(int i=0;i<getAllStarUser.size();i++) {
											int lSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int aCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											if(lSize>=aCount) {
												u=getAllStarUser.get(i);
												break;
											}
										}
										lead.setAssignee(u);
									}
								}

							}
						}else {
							System.out.println("vvvvvvvvvvvvvvvvvvv");

							if(ql>0) {
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww t1");

								List<Long> statusList = sList.stream().map(i->i.getId()).collect(Collectors.toList());
								User u=sortArrayAndSingleUser(qualityUser,statusList);
								//								lead.setAssignee(qualityUser.get(qi));
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww t1");

								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww testing");

								lead.setAssignee(u);
								System.out.println("User..... count with ...."+u.getId());

								qi++;
								if(qi==ql) {
									qi=0;
								}	
							}
						}
						lead.setIsUrlsChecked(false);
					}else{
						System.out.println("xxxxxxxxxxxxxxxxxx");

						Boolean isProf =false;
						if(lead.getEmail()!=null) {
							isProf= checkEmailDomain(lead.getEmail());
							System.out.println("yyyyyyyyyyyyyyyyyyyy");

						}
						if(isProf && urlsManagment!=null) {
							System.out.println(urlsManagment+"zzzzzzzzzzzzzzzzzzzzzzzzzzz");

							Long productId=urlsManagment.getId();
							//							String rating4="4";
							//							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
							//
							//							String rating5="5";
							//							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();

							String rating4="4";
							Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
							List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;

							//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
							System.out.println("hhhhhhhhhhhhh");

							String rating5="5";
							Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
							List<User> user5Rating = ratings5.getRatingsUser();

							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);

							List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

							User u1=null;
							//				System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							//				System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
							if(count>cInt) {

								u1=userMerged.get(0);
							}
							else {
								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
									if(lockerSize>=actualCount) {
										u1=userMerged.get(i);
									}
								}
							}

							if(u1==null) {

								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

								if(ratingList!=null&&ratingList.size()>0) {

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

									for(int i=0;i<getAllStarUser.size();i++) {

										if(getAllStarUser.get(i).getLockerSize()<=cInt) {

											u1=getAllStarUser.get(i);
											break;
										}
									}

								}  
							}
							if(u1!=null) {

								lead.setAssignee(u1);

							}else {
								System.out.println("User is in backup team"+lead.getId());
								User user=userRepo.findByIsBackupTeam(true);
								if(user!=null) {
									lead.setAssignee(user);

									sendMail();
								}else {
									sendMail();

								}
							}

						}else { 
							System.out.println("a1aaaaaaaaaaaaaaaaaaaaaaaaaa");
							if(urlsManagment!=null) {
								Long productId=urlsManagment.getId();
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);

								if(ratingList!=null&&ratingList.size()>0) {
									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

									lead.setAssignee(getAllStarUser.get(0));
								}  
							}

						}
					}
				}
			}
			lead.setIsUrlsChecked(false);
			//				lead.setAuto(false);
			//	System.out.println(count1+"... this is count 1");
			//	System.out.println(count2+"... this is count  2"+lead.getId());

			leadRepository.save(lead);



		}
	}

//		@Scheduled(cron = "0 * * ? * *", zone = "IST")
	public void assignLeadByCrone() {
		List<Long>croneStatus= new ArrayList<>();
		System.out.println("First tsting  .....");
		List<Status>sList=statusRepository.findByEnableAutoAssign(true);
		sList.addAll(sList);
		croneStatus.addAll(sList.stream().map(i->i.getId()).collect(Collectors.toList()));
		List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
		qualityUser=qualityUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
		System.out.println("aaaaaaaaaaaaaaaaa");

		int qi=0;//initial statge og quality user
		int ql=qualityUser.size();
		int count1=0;
		int count2=0;
		System.out.println("testing .......");
		System.out.println("lead size ......."+cronelead);

		for(Lead lead:cronelead) {
			System.out.println("lead "+lead.getId());
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			if(lead.getEmail()!=null &&(!lead.getEmail().equals("NA"))) {
				existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			}else {
				existingLead=leadRepository.findAllByMobile(lead.getMobileNo());

			}
			System.out.println("bbbbbbbbb");
			User lastUser = lastActiveUser(lead.getId());
			if(lead.isBacklogTask()&& (!lead.isNotAssignSame()) && lastUser!=null) {
				//				User user = lastActiveUser(lead.getId());
				lead.setAssignee(lastUser);
			}else {
				if(existingLead!=null && existingLead.size()>1) {
					System.out.println("cccccccccc");
					User assignee=getAssignee(existingLead);
					//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
					if(assignee!=null &&assignee.isDeleted()) {
						User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
						lead.setAssignee(user);
					}else {
						lead.setAssignee(assignee);
					}
					System.out.println("cccccc");
				}else {
					System.out.println("dddddddddddd");
					System.out.println(lead.getOriginalName()+""+lead.getId());
					System.out.println(lead.getOriginalName());

					UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
					System.out.println(urlsManagment+"tttttttttttttttttttttttttttttttttttttttttttttttttLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
					count2++;
					if(lead!=null & lead.getIsUrlsChecked()) {
						System.out.println("eeeeeeeeeeeeeee");
						if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
							System.out.println("ffffffffffff");
							count1++;
							Boolean isProf =false;
							if(lead.getEmail()!=null &&(!lead.getEmail().equals("NA"))) {
								isProf= checkEmailDomain(lead.getEmail());
							}
							if(isProf) {
						System.out.println("gggggggg");
								Long productId=urlsManagment.getId();
								String rating4="4";
								Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
								List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;
								//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
								System.out.println("hhhhhhhhhhhhh");
								String rating5="5";
								System.out.println(productId);
								Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
								List<User> user5Rating = ratings5!=null?ratings5.getRatingsUser():null;
								//								 List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
								System.out.println("iiiiiiiiiiiiiiiiiiiiii");
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
								userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
								System.out.println("jjjjjjjjjjjjjjjjj");
								User u1=null;
								//					System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
								int cInt=0;
								int count=0;
								if(userMerged!=null && userMerged.size()>0) {
									 cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
										 count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;

								}
//								int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
								//					System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
								if(count>cInt) {
									System.out.println("kkkkkkkkkkkkkk");

									u1=userMerged.get(0);
								}
								else {
									System.out.println("llllllllllll"+userMerged.size());

									for(int i=1;i<userMerged.size();i++) {
										int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
										int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(i).getId(),croneStatus);
										if(lockerSize>=actualCount) {
											u1=userMerged.get(i);
										}
									}
								}
								System.out.println("mmmmmmmm");

								if(u1==null) {
									System.out.println("nnnnnnnnnnnnn");
									System.out.println(productId);
									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
									ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
									System.out.println("ooooooooooooooooooooo"+ratingList.size());
									if(ratingList!=null && ratingList.size()>0) {
										System.out.println("pppppppppppppppppppppppppppp");
										List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
										System.out.println("p1-testfgddgdfgdfg  "+getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));
										for(int i=0;i<getAllStarUser.size();i++) {

											//										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
											//
											//											u1=getAllStarUser.get(i);
											//											break;
											//										}
											int lockerSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											System.out.println("Locker size "+lockerSize+"testing    . .  "+actualCount+"    count "+getAllStarUser.get(i));
											if(lockerSize>=actualCount) {
												u1=getAllStarUser.get(i);
											}
										}
									}  
								}
								if(u1!=null) {
									System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  .."+u1.getFullName());
									lead.setAssignee(u1);
									lead.setIsUrlsChecked(false);
									lead.setAuto(false);
								}else {
									System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
									User user=userRepo.findByIsBackupTeam(true);
									System.out.println("user is.... "+user);
									if(user!=null) {
										lead.setAssignee(user);
										lead.setIsUrlsChecked(false);
										lead.setAuto(false);
									}
									
								}
							}else { 
								System.out.println("sssssssssssssssssssssssssssssssssssssss");
								User u=null;
								Long productId=urlsManagment.getId();
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
								if(ratingList!=null&&ratingList.size()>0) {
									System.out.println("tttttttttttttttttt");
									List<User> getAllStarUser = mergeAllUser1to3StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
									int lockerSize=0;
									int actualCount=0;
									if(getAllStarUser!=null && getAllStarUser.size()!=0) {
										 lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
										 actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
									}
									if((getAllStarUser!=null && getAllStarUser.size()!=0)&&lockerSize>=actualCount) {
										lead.setAssignee(getAllStarUser.get(0));
										lead.setAuto(false);
									}else {
										System.out.println("uuuuuuuuuuuuuuu");

										List<User> get4to5StarUser = mergeAllUser4to5StarV3(ratingList, userCounts);
										getAllStarUser.addAll(get4to5StarUser);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

										for(int i=0;i<getAllStarUser.size();i++) {
											int lSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
											int aCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
											if(lSize>=aCount) {
												u=getAllStarUser.get(i);
												break;
											}
										}
										if(u!=null) {
											lead.setAssignee(u);
											lead.setIsUrlsChecked(false);
											lead.setAuto(false);
										}
										
									}
								}

							}
						}else {
							System.out.println("vvvvvvvvvvvvvvvvvvv");

							if(ql>0) {
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww t1");
								List<Long> statusList = sList.stream().map(i->i.getId()).collect(Collectors.toList());
								User u=sortArrayAndSingleUser(qualityUser,statusList);
								//								lead.setAssignee(qualityUser.get(qi));
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww t1");
								System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww testing");
								lead.setAssignee(u);
								lead.setAuto(false);
								System.out.println("User..... count with ...."+u.getId());
								qi++;
								if(qi==ql) {
									qi=0;
								}	
							}
						}
						lead.setIsUrlsChecked(false);
					}else{
						System.out.println("xxxxxxxxxxxxxxxxxx");

						Boolean isProf =false;
						if(lead.getEmail()!=null&& (!lead.getEmail().equals("NA"))) {
							System.out.println(lead.getEmail());
							isProf= checkEmailDomain(lead.getEmail());
							System.out.println("yyyyyyyyyyyyyyyyyyyy");
						}
						if(isProf && urlsManagment!=null) {
							System.out.println(urlsManagment+"zzzzzzzzzzzzzzzzzzzzzzzzzzz");

							Long productId=urlsManagment.getId();
							//							String rating4="4";
							//							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
							//
							//							String rating5="5";
							//							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();

							String rating4="4";
							Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
							List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;

							//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
							System.out.println("hhhhhhhhhhhhh");

							String rating5="5";
							Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
							List<User> user5Rating = ratings5!=null?ratings5.getRatingsUser():null;

							Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
							List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
							userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
							User u1=null;
							//				System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
							int cInt=0;
							int count =0;
							if(userMerged!=null&&userMerged.size()!=0) {
								cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
								count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;							
							}
//							int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
//							int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
							//				System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
							if(count>cInt) {

								u1=userMerged.get(0);
							}
							else {
								for(int i=1;i<userMerged.size();i++) {
									int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
									int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
									if(lockerSize>=actualCount) {
										u1=userMerged.get(i);
									}
								}
							}

							if(u1==null) {

								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

								if(ratingList!=null&&ratingList.size()>0) {

									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

									for(int i=0;i<getAllStarUser.size();i++) {

										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
											u1=getAllStarUser.get(i);
											break;
										}
									}
								}  
							}
							if(u1!=null) {
								lead.setAssignee(u1);
								lead.setIsUrlsChecked(false);
								lead.setAuto(false);
							}else {
								System.out.println("User is in backup team"+lead.getId());
								User user=userRepo.findByIsBackupTeam(true);
								if(user!=null) {
									lead.setAssignee(user);
									lead.setIsUrlsChecked(false);
									lead.setAuto(false);
									sendMail();
								}else {
									sendMail();

								}
							}

						}else { 
							System.out.println("a1aaaaaaaaaaaaaaaaaaaaaaaaaa");
							if(urlsManagment!=null) {
								Long productId=urlsManagment.getId();
								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
								if(ratingList!=null&&ratingList.size()>0) {
									List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
									getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
                                    if(getAllStarUser.get(0)!=null) {
                                    	lead.setAssignee(getAllStarUser.get(0));
    									lead.setIsUrlsChecked(false);
    									lead.setAuto(false);
                                    }		
								}  
							}

						}
					}
				}
			}
			//			lead.setIsUrlsChecked(false);
			//			lead.setAuto(false);
			leadRepository.save(lead);
		}
	}
//	@Scheduled(cron = "0 * * ? * *", zone = "IST")
		public void assignLeadByCroneV() {
			List<Long>croneStatus= new ArrayList<>();
			System.out.println("First tsting  .....");
			List<Status>sList=statusRepository.findByEnableAutoAssign(true);
			sList.addAll(sList);
			croneStatus.addAll(sList.stream().map(i->i.getId()).collect(Collectors.toList()));
			List<Lead>cronelead=leadRepository.findAllByStatusIdInAndIsDeletedAndAuto(croneStatus, false,true);
			List<User>userList = userRepo.findAllActiveUser();
			List<User>qualityUser = userList.stream().filter(i->i.getDepartment().equalsIgnoreCase("Quality")).collect(Collectors.toList());
			qualityUser=qualityUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
			System.out.println("aaaaaaaaaaaaaaaaa");

			int qi=0;//initial statge og quality user
			int ql=qualityUser.size();
			int count1=0;
			int count2=0;
			System.out.println("testing .......");
			System.out.println("lead size ......."+cronelead);

			for(Lead lead:cronelead) {
				System.out.println("lead "+lead.getId());
				List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
				if(lead.getEmail()!=null &&(!lead.getEmail().equals("NA"))) {
					existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
				}else {
					existingLead=leadRepository.findAllByMobile(lead.getMobileNo());

				}
				System.out.println("bbbbbbbbb");
				User lastUser = lastActiveUser(lead.getId());
				if(lead.isBacklogTask()&& (!lead.isNotAssignSame()) && lastUser!=null) {
					//				User user = lastActiveUser(lead.getId());
					lead.setAssignee(lastUser);
				}else {
					if(existingLead!=null && existingLead.size()>1) {
						System.out.println("cccccccccc");
						User assignee=getAssignee(existingLead);
						//CHECK USER IS NOT ACTIVE THEN WE ASSIGN A TEAM 
						if(assignee!=null &&assignee.isDeleted()) {
							User user = userRepo.findAllByIsDeletedAndIsMaster(false,true);
							lead.setAssignee(user);
						}else {
							lead.setAssignee(assignee);
						}
						System.out.println("cccccc");
					}else {
						System.out.println("dddddddddddd");
						System.out.println(lead.getOriginalName()+""+lead.getId());
						System.out.println(lead.getOriginalName());

						UrlsManagment urlsManagment = urlsManagmentRepo.findByUrlsName(lead.getOriginalName());
						System.out.println(urlsManagment+"tttttttttttttttttttttttttttttttttttttttttttttttttLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
						count2++;
						if(lead!=null & lead.getIsUrlsChecked()) {
							System.out.println("eeeeeeeeeeeeeee");
							if(urlsManagment!=null &&(!urlsManagment.isQuality())) {
								System.out.println("ffffffffffff");
								count1++;
								Boolean isProf =false;
								if(lead.getEmail()!=null &&(!lead.getEmail().equals("NA"))) {
									isProf= checkEmailDomain(lead.getEmail());
								}
								if(isProf||((lead.getEmail()==null)||(lead.getEmail()!=null &&(lead.getEmail().equals("NA"))))) {
									System.out.println("gggggggg");
									Long productId=urlsManagment.getId();
									String rating4="4";
									Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
									List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;
									//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
									System.out.println("hhhhhhhhhhhhh");
									String rating5="5";
									System.out.println(productId);
									Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
									List<User> user5Rating = ratings5!=null?ratings5.getRatingsUser():null;
									//								 List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();
									System.out.println("iiiiiiiiiiiiiiiiiiiiii");
									Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
									List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
									userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
									System.out.println("jjjjjjjjjjjjjjjjj");
									User u1=null;
									//					System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
									int cInt=0;
									int count=0;
									if(userMerged!=null && userMerged.size()>0) {
										 cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
											 count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;

									}
//									int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
									//					System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
									if(count>cInt) {
										System.out.println("kkkkkkkkkkkkkk");

										u1=userMerged.get(0);
									}
									else {
										System.out.println("llllllllllll"+userMerged.size());

										for(int i=1;i<userMerged.size();i++) {
											int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
											int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(i).getId(),croneStatus);
											if(lockerSize>=actualCount) {
												u1=userMerged.get(i);
											}
										}
									}
									System.out.println("mmmmmmmm");

									if(u1==null) {
										System.out.println("nnnnnnnnnnnnn");
										System.out.println(productId);
										List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
										ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());
										System.out.println("ooooooooooooooooooooo"+ratingList.size());
										if(ratingList!=null && ratingList.size()>0) {
											System.out.println("pppppppppppppppppppppppppppp");
											List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
											getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
											System.out.println("p1-testfgddgdfgdfg  "+getAllStarUser.stream().map(i->i.getFullName()).collect(Collectors.toList()));
											for(int i=0;i<getAllStarUser.size();i++) {

												//										if(getAllStarUser.get(i).getLockerSize()<=cInt) {
												//
												//											u1=getAllStarUser.get(i);
												//											break;
												//										}
												int lockerSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
												int actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
												System.out.println("Locker size "+lockerSize+"testing    . .  "+actualCount+"    count "+getAllStarUser.get(i));
												if(lockerSize>=actualCount) {
													u1=getAllStarUser.get(i);
												}
											}
										}  
									}
									if(u1!=null) {
										System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq  .."+u1.getFullName());
										lead.setAssignee(u1);
										lead.setIsUrlsChecked(false);
										lead.setAuto(false);
									}else {
										System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
										User user=userRepo.findByIsBackupTeam(true);
										System.out.println("user is.... "+user);
										if(user!=null) {
											lead.setAssignee(user);
											lead.setIsUrlsChecked(false);
											lead.setAuto(false);
										}
										
									}
								}else { 
									System.out.println("sssssssssssssssssssssssssssssssssssssss");
									User u=null;
									Long productId=urlsManagment.getId();
									Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);                            
									if(ratingList!=null&&ratingList.size()>0) {
										System.out.println("tttttttttttttttttt");
										List<User> getAllStarUser = mergeAllUser1to3StarV3(ratingList, userCounts);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
										int lockerSize=0;
										int actualCount=0;
										if(getAllStarUser!=null && getAllStarUser.size()!=0) {
											 lockerSize =getAllStarUser!=null?getAllStarUser.get(0)!=null?getAllStarUser.get(0).getLockerSize():0:0;
											 actualCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(0).getId(),croneStatus);
										}
										if((getAllStarUser!=null && getAllStarUser.size()!=0)&&lockerSize>=actualCount) {
											lead.setAssignee(getAllStarUser.get(0)
													
													
													
													);
											lead.setAuto(false);
										}else {
											System.out.println("uuuuuuuuuuuuuuu");

											List<User> get4to5StarUser = mergeAllUser4to5StarV3(ratingList, userCounts);
											getAllStarUser.addAll(get4to5StarUser);
											getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

											for(int i=0;i<getAllStarUser.size();i++) {
												int lSize =getAllStarUser!=null?getAllStarUser.get(i)!=null?getAllStarUser.get(i).getLockerSize():0:0;
												int aCount=leadRepository.findCountByAssigneeId(getAllStarUser.get(i).getId(),croneStatus);
												if(lSize>=aCount) {
													u=getAllStarUser.get(i);
													break;
												}
											}
											if(u!=null) {
												lead.setAssignee(u);
												lead.setIsUrlsChecked(false);
												lead.setAuto(false);
											}
											
										}
									}

								}
							}else {
								System.out.println("vvvvvvvvvvvvvvvvvvv");

								if(ql>0) {
									System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww");
									System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww t1");
									List<Long> statusList = sList.stream().map(i->i.getId()).collect(Collectors.toList());
									User u=sortArrayAndSingleUser(qualityUser,statusList);
									//								lead.setAssignee(qualityUser.get(qi));
									System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww t1");
									System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwww testing");
									lead.setAssignee(u);
									lead.setAuto(false);
									System.out.println("User..... count with ...."+u.getId());
									qi++;
									if(qi==ql) {
										qi=0;
									}	
								}
							}
							lead.setIsUrlsChecked(false);
						}else{
							System.out.println("xxxxxxxxxxxxxxxxxx");

							Boolean isProf =false;
							if(lead.getEmail()!=null&& (!lead.getEmail().equals("NA"))) {
								System.out.println(lead.getEmail());
								isProf= checkEmailDomain(lead.getEmail());
								System.out.println("yyyyyyyyyyyyyyyyyyyy");
							}
							if(isProf && urlsManagment!=null) {
								System.out.println(urlsManagment+"zzzzzzzzzzzzzzzzzzzzzzzzzzz");

								Long productId=urlsManagment.getId();
								//							String rating4="4";
								//							List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).getRatingsUser();
								//
								//							String rating5="5";
								//							List<User> user5Rating = ratingRepository.findByRatingAndProdctId(rating5,productId).getRatingsUser();

								String rating4="4";
								Ratings ratings4 = ratingRepository.findByRatingAndProdctId(rating4,productId);
								List<User> user4Rating = ratings4!=null? ratings4.getRatingsUser():null;

								//								List<User> user4Rating = ratingRepository.findByRatingAndProdctId(rating4,productId).g̥etRatingsUser();
								System.out.println("hhhhhhhhhhhhh");

								String rating5="5";
								Ratings ratings5 = ratingRepository.findByRatingAndProdctId(rating5,productId);
								List<User> user5Rating = ratings5!=null?ratings5.getRatingsUser():null;

								Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
								List<User> userMerged = mergeStarUserV3(user4Rating,user5Rating,userCounts);
								userMerged=userMerged.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
								User u1=null;
								//				System.out.println(userMerged.stream().map(i->i.getId()).collect(Collectors.toList())+"......|||||||||||||||||dash");
								int cInt=0;
								int count =0;
								if(userMerged!=null&&userMerged.size()!=0) {
									cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
									count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;							
								}
//								int cInt=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
//								int count =userMerged!=null?userMerged.get(0)!=null?userMerged.get(0).getLockerSize():0:0;
								//				System.out.println("count size . . "+cInt+"  ...  "+userMerged.get(0));
								if(count>cInt) {

									u1=userMerged.get(0);
								}
								else {
									for(int i=1;i<userMerged.size();i++) {
										int lockerSize =userMerged!=null?userMerged.get(i)!=null?userMerged.get(i).getLockerSize():0:0;
										int actualCount=leadRepository.findCountByAssigneeId(userMerged.get(0).getId(),croneStatus);
										if(lockerSize>=actualCount) {
											u1=userMerged.get(i);
										}
									}
								}

								if(u1==null) {

									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
									ratingList=ratingList.stream().filter(i->i.getRating().equals("3")||i.getRating().equals("2")||i.getRating().equals("1")).collect(Collectors.toList());

									if(ratingList!=null&&ratingList.size()>0) {

										List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());

										for(int i=0;i<getAllStarUser.size();i++) {

											if(getAllStarUser.get(i).getLockerSize()<=cInt) {
												u1=getAllStarUser.get(i);
												break;
											}
										}
									}  
								}
								if(u1!=null) {
									lead.setAssignee(u1);
									lead.setIsUrlsChecked(false);
									lead.setAuto(false);
								}else {
									System.out.println("User is in backup team"+lead.getId());
									User user=userRepo.findByIsBackupTeam(true);
									if(user!=null) {
										lead.setAssignee(user);
										lead.setIsUrlsChecked(false);
										lead.setAuto(false);
										sendMail();
									}else {
										sendMail();

									}
								}

							}else { 
								System.out.println("a1aaaaaaaaaaaaaaaaaaaaaaaaaa");
								if(urlsManagment!=null) {
									Long productId=urlsManagment.getId();
									Map<Long, Integer> userCounts = calculateLeadCount(lead.getOriginalName(),croneStatus);
									List<Ratings> ratingList = ratingRepository.findAllByProductId(productId);
									if(ratingList!=null&&ratingList.size()>0) {
										List<User> getAllStarUser = mergeAllUser1to5StarV3(ratingList, userCounts);
										getAllStarUser=getAllStarUser.stream().filter(i->i.isAutoActive()).collect(Collectors.toList());
	                                    if(getAllStarUser.get(0)!=null) {
	                                    	lead.setAssignee(getAllStarUser.get(0));
	    									lead.setIsUrlsChecked(false);
	    									lead.setAuto(false);
	                                    }		
									}  
								}

							}
						}
					}
				}
				//			lead.setIsUrlsChecked(false);
				//			lead.setAuto(false);
				leadRepository.save(lead);
			}
		}



}
