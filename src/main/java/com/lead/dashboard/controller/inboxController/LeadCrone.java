package com.lead.dashboard.controller.inboxController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.UrlsManagment;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
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
	public  ArrayList<User>mergeStarUser(ArrayList<User>user4,ArrayList<User>user5,Map<Long,Integer>countMap){
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
}
