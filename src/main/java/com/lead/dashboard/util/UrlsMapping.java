package com.lead.dashboard.util;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:3000")
public class UrlsMapping {

	public final static String PREFIX = "leadService/api/v1";
	
	
	//======================= Lead Service =============
	
	public static final String TEST=PREFIX+ "/lead/test";
	public static final String CREATE_LEAD=PREFIX+"/lead/createLead";
	public static final String GET_ALL_LEAD=PREFIX+"/lead/getAllLead";
	public static final String UPDATE_LEAD=PREFIX+"/lead/updateLead";
	public static final String DELETE_LEAD=PREFIX+"/lead/deleteLead";
	public static final String SEND_MAIL_IN_LEAD=PREFIX+"/lead/sendMailInLead";
	public static final String GET_SINGLE_LEAD_DATA=PREFIX+"/lead/getSingleLeadData";
	public static final String GET_ALL_STATUS_HISTORY=PREFIX+"/lead/getAllStatusHistory";
    public static final String UPDATE_ASSIGNEE =PREFIX+"/lead/updateAssignee";
	public static final String CREATE_PRODUCT_IN_LEAD = PREFIX+"/lead/createProductInLead";
	public static final String DELETE_PRODUCT_IN_LEAD = PREFIX+"/lead/deleteProductInLead";
	public static final String UPDATE_LEAD_NAME = PREFIX+"/lead/updateLeadName";

	//=================== Lead - Oppoertunity Service ===================
	
	public static final String CREATE_OPPORTUNITY = PREFIX+"/leadOpportunity/createOpportunity";
	public static final String UPDATE_OPPORTUNITY = PREFIX+"/leadOpportunity/UpdateOpportunity";
	public static final String GET_OPPORTUNITY_BY_ID = PREFIX+"/leadOpportunity/getOpportunityById";
	public static final String GET_ALL_OPPORTUNITY = PREFIX+"/leadOpportunity/getAllOpportunity";
	public static final String DELETE_OPPORTUNITY = PREFIX+"/leadOpportunity/deleteOpportunity";

	
	//=================== Lead - History =================================
	
	public static final String GET_ALL_LEAD_HISTORY=PREFIX+"/leadHistory/getAllLeadHistory";
	
	
	//===================== Lead - Estimate ==============================================
	
	public static final String CREATE_ESTIMATE=PREFIX+"/leadEstimate/createEstimate";
	public static final String GET_ALL_ESTIMATE=PREFIX+"/leadEstimate/getAllEstimate";


	
	
	
	

}
