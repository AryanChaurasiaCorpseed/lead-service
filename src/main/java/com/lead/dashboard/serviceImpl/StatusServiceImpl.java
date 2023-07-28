package com.lead.dashboard.serviceImpl;

import com.lead.dashboard.domain.Status;
import com.lead.dashboard.repository.StatusRepository;
import com.lead.dashboard.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

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
}
