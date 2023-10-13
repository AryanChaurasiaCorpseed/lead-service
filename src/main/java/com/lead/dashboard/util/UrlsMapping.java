package com.lead.dashboard.util;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
public class UrlsMapping {

	public final static String PREFIX = "leadService/api/v1";
	
	
	// Resource Allocation
	public static final String TEST=PREFIX+ "/lead/test";
	public static final String CREATE_LEAD=PREFIX+"/lead/createLead";
	public static final String GET_ALL_LEAD=PREFIX+"/lead/getAllLead";
	public static final String UPDATE_LEAD=PREFIX+"/lead/updateLead";
	public static final String DELETE_LEAD=PREFIX+"/lead/deleteLead";
	public static final String SEND_MAIL_IN_LEAD=PREFIX+"/lead/sendMailInLead";
	public static final String GET_SINGLE_LEAD_DATA=PREFIX+"/lead/getSingleLeadData";
	public static final String CREATE_ESTIMATE=PREFIX+"/lead/createEstimate";
	public static final String GET_ALL_STATUS_HISTORY=PREFIX+"/lead/getAllStatusHistory";
    public static final String UPDATE_ASSIGNEE =PREFIX+"/lead/updateAssignee";
	public static final String CREATE_OPPORTUNITY=PREFIX+"/lead/createOpportunity";

	public static final String UPDATE_OPPORTUNITY=PREFIX+"/lead/updateOpportunity";
	public static final String GET_OPPORTUNITY=PREFIX+"/lead/getOpportunity";
	public static final String GET_ALL_OPPORTUNITY=PREFIX+"/lead/getAllOpportunity";

	public static final String DELETE_OPPORTUNITY=PREFIX+"/lead/removeOpportunity";



}
