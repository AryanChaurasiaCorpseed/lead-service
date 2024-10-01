package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.domain.lead.LeadStatusChangeHistory;
import com.lead.dashboard.dto.CreateLeadStatus;
import com.lead.dashboard.dto.UpdateLeadStatusDto;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.domain.User;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.LeadStatusChangeHisoryRepo;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.repository.UserRepo;
import com.lead.dashboard.service.StatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LeadRepository leadRepository;
    
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    LeadServiceImpl leadServiceImpl;

    @Autowired
    private LeadStatusChangeHisoryRepo leadStatusChangeHisoryRepo;

    @Override
    public Status createStatus(CreateLeadStatus status) {
    	Status s = new Status();
    	s.setName(status.getName());
    	s.setDescription(status.getDescription());
        s.setCreatedTime(LocalDateTime.now());
        s.setUpdatedTime(LocalDateTime.now());
        s.setEnableAutoAssign(status.isAuto());
        return statusRepository.save(s);
    }

    @Override
    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Status getStatusById(Long id) {
        return statusRepository.getStatusById(id);
    }

    @Override
    public Status updateStatus(UpdateLeadStatusDto updateLeadStatusDto) {
    	 Status status = statusRepository.findById(updateLeadStatusDto.getId()).get();
    	 status.setName(updateLeadStatusDto.getName());
    	 status.setDescription(updateLeadStatusDto.getDescription());
    	 statusRepository.save(status);
    	 return status;
    }

    @Override
    public void deleteStatus(Long id) {
        Status status = getStatusById(id);
        statusRepository.delete(status);
    }

//    @Override
//    public void updateLeadStatus(Long leadId, Long statusId,Long currentUserId) {
//        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new EntityNotFoundException("no lead"));
//        List<Status>filterStatus=statusRepository.findAllByEnableAutoAssign(true);
//        User cUser=null; 
//        if(currentUserId!=null) {
//            cUser= userRepo.findById(currentUserId).get();
//        }
//        Status prevStatus = lead.getStatus();
//        Status newstatusdata = statusRepository.findById(statusId).orElseThrow(() -> new EntityNotFoundException("status not there"));
////        Status newstatusdata = statusRepository.findAllByName(status);
//        lead.setStatus(newstatusdata);    
//        leadRepository.save(lead);
//
////        LeadStatusChangeHistory leadStatusChange= new LeadStatusChangeHistory();
//
////        leadStatusChange.setNewStatus(newstatusdata);
////        leadStatusChange.setChangeTime(newstatusdata.getUpdatedTime());
////        leadStatusChange.setChangedByUser("Aryan");
////        leadStatusChange.setLead(lead);
//        leadServiceImpl.multiLeadStatusHistory(leadId, prevStatus, newstatusdata, cUser);
////        leadStatusChangeHisoryRepo.save(leadStatusChange);
//
//    }
    
    @Override
    public void updateLeadStatus(Long leadId, Long statusId,Long currentUserId) {
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new EntityNotFoundException("no lead"));
        List<Status>filterStatus=statusRepository.findAllByEnableAutoAssign(true);
        User cUser=null; 
        if(currentUserId!=null) {
            cUser= userRepo.findById(currentUserId).get();
        }
        Status prevStatus = lead.getStatus();
        Status newstatusdata = statusRepository.findById(statusId).orElseThrow(() -> new EntityNotFoundException("status not there"));
        if(filterStatus!=null && filterStatus.contains(newstatusdata)) {
        	lead.setAuto(true);
        }
        lead.setStatus(newstatusdata);    
        leadRepository.save(lead);
        leadServiceImpl.multiLeadStatusHistory(leadId, prevStatus, newstatusdata, cUser);
        if(newstatusdata.getName().equals("Bad Fit")) {
        	leadServiceImpl.assignBadFitToQuality(lead);
        }
    }
    

    @Override
    public List<LeadStatusChangeHistory> getStatusHistoryForLead(Long leadId)
    {
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new EntityNotFoundException("No Data found"));
        Optional<LeadStatusChangeHistory> leadStatusChangeHistory = leadStatusChangeHisoryRepo.findById(leadId);
        System.out.println(leadStatusChangeHistory);
     return null;
    }
    public List<Status> getAllPreviusStatus(Long statusId)
    {
    	List<Status>statusList=statusRepository.findAll(); 
    	List<Status>sList = new ArrayList<>();
    	for(Status s:statusList) {
    		sList.add(s);
    		if(s.getId().equals(statusId)) {
    			break;
    		}
    	}
     return sList;
    }
    
    


}
