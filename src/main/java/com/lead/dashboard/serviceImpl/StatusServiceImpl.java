package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Lead;
import com.lead.dashboard.domain.LeadStatusChangeHistory;
import com.lead.dashboard.domain.Status;
import com.lead.dashboard.repository.LeadRepository;
import com.lead.dashboard.repository.LeadStatusChangeHisoryRepo;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.service.StatusService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadStatusChangeHisoryRepo leadStatusChangeHisoryRepo;

    @Override
    public Status createStatus(Status status) {
        status.setCreatedTime(LocalDateTime.now());
        status.setUpdatedTime(LocalDateTime.now());
        return statusRepository.save(status);
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
    public Status updateStatus(Status status) {
        Status existingStatus = getStatusById(status.getId());
        existingStatus.setName(status.getName());
        existingStatus.setDescription(status.getDescription());
        existingStatus.setIsDeleted(status.getIsDeleted());
        existingStatus.setUpdatedTime(LocalDateTime.now());
        return statusRepository.save(existingStatus);
    }

    @Override
    public void deleteStatus(Long id) {
        Status status = getStatusById(id);
        statusRepository.delete(status);
    }

    @Override
    public void updateLeadStatus(Long leadId, Long statusId) {
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new EntityNotFoundException("no lead"));
        System.out.println(lead);
        Status newstatusdata = statusRepository.findById(statusId).orElseThrow(() -> new EntityNotFoundException("status not there"));

        lead.setStatus(newstatusdata);
        leadRepository.save(lead);

        LeadStatusChangeHistory leadStatusChange= new LeadStatusChangeHistory();

        leadStatusChange.setNewStatus(newstatusdata);
        leadStatusChange.setChangeTime(newstatusdata.getUpdatedTime());
        leadStatusChange.setChangedByUser("Kauhsal");
        leadStatusChange.setLead(lead);
        leadStatusChangeHisoryRepo.save(leadStatusChange);


    }

}
