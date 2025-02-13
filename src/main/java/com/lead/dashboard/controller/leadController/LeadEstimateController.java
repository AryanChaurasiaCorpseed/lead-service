package com.lead.dashboard.controller.leadController;


import com.lead.dashboard.domain.ServiceDetails;
import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.dto.CreateServiceDetails;
import com.lead.dashboard.dto.EditEstimate;
import com.lead.dashboard.dto.EditEstimateAddress;
import com.lead.dashboard.service.EstimateService;
import com.lead.dashboard.service.LeadService;
import com.lead.dashboard.util.UrlsMapping;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeadEstimateController {


    
    @Autowired
    EstimateService estimateService;

	@PostMapping(UrlsMapping.CREATE_ESTIMATE)
    public Boolean createEstimate(@RequestBody CreateServiceDetails createServiceDetails) throws Exception
    {
    	Boolean res=estimateService.createEstimate(createServiceDetails);
        return res;
    }
    @GetMapping(UrlsMapping.GET_ALL_ESTIMATE)
    public List<ServiceDetails> getAllEst̥imate()
    {
    	List<ServiceDetails> res=estimateService.getAllEstimate();
        return res;
    }
    
    @GetMapping(UrlsMapping.GET_ESTIMATE)
    public ServiceDetails getEstimate(@RequestParam Long  estimateId)
    {
    	ServiceDetails res=estimateService.getEstimate(estimateId);
        return res;
    }

    @PutMapping(UrlsMapping.EDIT_ESTIMATE_INVOICE)
    public Boolean editEstimateInvoice(@RequestBody EditEstimate  editEstimate)
    {
    	Boolean res=estimateService.editEstimateInvoice(editEstimate);
        return res;
    }
    
    @PutMapping(UrlsMapping.EDIT_ESTIMATE_ADDRESS)
    public ServiceDetails editEstimateAddress(@RequestBody EditEstimateAddress  editEstimateAddress)
    {
    	ServiceDetails res=estimateService.editEstimateAddress(editEstimateAddress);
        return res;
    }
    
    @GetMapping(UrlsMapping.GET_ESTIMATE_BY_LEAD_ID)
    public Map<String,Object> getEstimateByLeadId(@RequestParam Long  leadId)
    {
    	Map<String,Object> res=estimateService.getEstimateByLeadId(leadId);
        return res;
    }
    
//    @GetMapping(UrlsMapping.GET_ESTIMATE_BY_USER_ID)
    public List<ServiceDetails> getEstimateByUserIdV1(@RequestParam Long  userId)
    {
//    	 List<ServiceDetails> res=estimateService.getEstimateByUserId(userId);
        return null;
    }
    
    
    @GetMapping(UrlsMapping.GET_ESTIMATE_BY_USER_ID)
    public List<Map<String,Object>> getEstimateByUserId(@RequestParam Long  userId,@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "100") int size)
    {
    	List<Map<String,Object>> res=estimateService.getEstimateByUserId(userId,page-1,size);
        return res;
    }   
    @GetMapping(UrlsMapping.GET_ESTIMATE_BY_USER_ID_COUNT)
    public long getEstimateByUserIdCount(@RequestParam Long  userId)
    {
    	 long res=estimateService.getEstimateByUserIdCount(userId);
        return res;
    }
    
    @PostMapping(UrlsMapping.GET_ESTIMATE_BY_ID)
    public Map<String,Object> getEstimateById(@RequestBody Long  estimateId)
    {
    	Map<String,Object> res=estimateService.getEstimateById(estimateId);
        return res;
    }
    
    @GetMapping(UrlsMapping.GET_ESTIMATE_BY_STATUS)
    public List<Map<String,Object>> getEstimateByStatus(@RequestParam String status, @RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size,@RequestParam Long userId)
    {
    	List<Map<String,Object>> res=estimateService.getEstimateByStatus(status,page-1,size,userId);
        return res;
    }
    
    @GetMapping(UrlsMapping.GET_ESTIMATE_BY_STATUS_COUNT)
    public long getEstimateByStatusCount(@RequestParam String status,@RequestParam Long userId)
    {
    	long res=estimateService.getEstimateByStatusCount(status,userId);
        return res;
    }
    
    @GetMapping(UrlsMapping.SEARCH_ESTIMATE)
    public List<Map<String,Object>> searchEstimate(@RequestParam String status,@RequestParam Long userId)
    {
    	List<Map<String,Object>> res=estimateService.searchEstimate(status,userId);
        return res;
    }
    
    @GetMapping(UrlsMapping.CHECK_PENDING_AMOUNT)
    public Boolean checkPendingAmount(@RequestParam Long estimateId)
    {
    	Boolean res=estimateService.checkPendingAmount(estimateId);
        return res;
    }
    
    
    @PutMapping(UrlsMapping.APPROVE_ESTIMATE)
    public Boolean approvedEstimate(@RequestParam String status,@RequestParam Long estimateId ,@RequestParam Long userId)
    {
    	Boolean res=estimateService.approvedEstimate(status,estimateId, userId);
        return res;
    }
    
}

