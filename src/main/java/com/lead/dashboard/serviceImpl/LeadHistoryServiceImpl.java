package com.lead.dashboard.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lead.dashboard.domain.LeadHistory;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.Remark;
import com.lead.dashboard.repository.LeadHistoryRepository;
import com.lead.dashboard.service.LeadHistorySevice;

@Service
public class LeadHistoryServiceImpl implements LeadHistorySevice{


	@Autowired
	LeadHistoryRepository leadHistoryRepository;
	
	@Override
	public ArrayList<Map<String,Object>> getAllLeadHistory(Long leadId) {
		ArrayList<Map<String,Object>>result = new ArrayList<>();				
		// TODO Auto-generated method stub
		List<LeadHistory>historyList=leadHistoryRepository.findByLeadId(leadId);
		for(LeadHistory l:historyList) {
			Map<String,Object>res = new HashMap<>();
			res.put("id", l.getId());
			res.put("event", l.getEventType());
			res.put("description", l.getDescription());
			res.put("createdBy", l.getCreatedBy()!=null?l.getCreatedBy().getFullName():"NA");
			res.put("createdDate", l.getCreateDate());
			res.put("leadId", l.getLeadId());		
			result.add(res);
		
		}
		return result;
	}
	

	public Boolean deleteRemarkHistory(User user, Long leadId, Remark chat) {
		Boolean flag=false;
		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setCreatedBy(user);
		leadHistory.setLeadId(leadId);
		String desc = "'"+chat.getMessage()+"' has been deleted by "+user.getFullName();
		leadHistory.setDescription(desc);
		leadHistory.setEventType("Remark has been deleted");
		leadHistoryRepository.save(leadHistory);
		flag=true;
		return flag;
	}
	
	public Boolean updateRemarkHistory(User user, Long leadId, String preRemark,String remark) {
		Boolean flag=false;
		LeadHistory leadHistory = new LeadHistory();
		leadHistory.setCreateDate(new Date());
		leadHistory.setCreatedBy(user);
		leadHistory.setLeadId(leadId);
		String desc = "Remark has been updated from '"+preRemark+"' to '"+remark+"' by "+user.getFullName();
		leadHistory.setDescription(desc);
		leadHistory.setEventType("Remark has been updated");
		leadHistoryRepository.save(leadHistory);
		flag=true;
		return flag;
	}


}
