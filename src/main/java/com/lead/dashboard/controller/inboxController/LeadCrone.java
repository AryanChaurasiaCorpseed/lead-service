package com.lead.dashboard.controller.inboxController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lead.dashboard.domain.Client;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.UserRepo;

@Service
public class LeadCrone {
    // boolean fl=false;
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	private LeadRepository leadRepository;
	
	public void assignLeadByCrone() {
		//New means --> auto
		List<User>userList = userRepo.findAllActiveUser();
		List<User>qualityUser = userList.stream().collect(Collectors.toList());
		List<User>topUser = userList.stream().collect(Collectors.toList());
		List<Lead>leadList =leadRepository.findAllByStatusIdAndAuto(1l,true);
		for(Lead lead:leadList) {
			List<Lead>existingLead=leadRepository.findAllByEmailAndMobile(lead.getEmail(),lead.getMobileNo());
			System.out.println(existingLead.size());
			if(existingLead!=null && existingLead.size()>1) {
				User assignee=getAssignee(existingLead);
				lead.setAssignee(assignee);
				   System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA ");

			}else {
				if(lead.getEmail()!=null) {
				   Boolean isProf = checkEmailDomain(lead.getEmail());

				   System.out.println("ssssssssss");
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

					lead.setAssignee(qualityUser.get(0));				
				}
			}
			
		}
//		List<Lead>leadList=leadRepository.findAllByEmailAndMobile(email,leadDTO.getMobileNo());

		
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
