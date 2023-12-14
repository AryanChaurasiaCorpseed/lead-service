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
	public static final String GET_ALL_DELETE_LEAD =PREFIX+"/lead/getAllDeleteLead";

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
	public static final String GET_ESTIMATE=PREFIX+"/leadEstimate/getEstimate";	
	public static final String EDIT_ESTIMATE_INVOICE=PREFIX+"/leadEstimate/editEstimateInvoice";
	public static final String EDIT_ESTIMATE_ADDRESS=PREFIX+"/leadEstimate/editEstimateAddress";
	
	
	// Node
	public static final String CREATE_NODE=PREFIX+"/node/createNode";
	public static final String GET_NODE=PREFIX+"/node/getNode";
	public static final String UPDATE_NODE=PREFIX+"/node/updateNode";
	public static final String DELETE_NODE=PREFIX+"/node/deleteNode";
	
	//Sub Nodes
	public static final String CREATE_SUB_NODE=PREFIX+"/node/createSubNode";
	public static final String GET_SUB_NODE=PREFIX+"/node/getSubNode";
	public static final String UPDATE_SUB_NODE=PREFIX+"/node/updateSubNode";
	public static final String DELETE_SUB_NODE=PREFIX+"/node/deleteSubNode";
	
 	//Super Sub Nodes

	public static final String CREATE_SUPER_SUB_NODE=PREFIX+"/node/createSuperSubNode";
	public static final String GET_SUPER_SUB_NODE=PREFIX+"/node/getSuperSubNode";
	public static final String UPDATE_SUPER_SUB_NODE=PREFIX+"/node/updateSuperSubNode";
	public static final String DELETE_SUPER_SUB_NODE=PREFIX+"/node/deleteSuperSubNode";
	
	
	//AccessManagment
	public static final String CREATE_USER_ACCESS=PREFIX+"/access/createUserAccess";
	public static final String GET_USER_ACCESS=PREFIX+"/access/getUserAccess";
	public static final String GET_ALL_USER_ACCESS=PREFIX+"/access/getAllUserAccess";
	public static final String UPDATE_USER_ACCESSE=PREFIX+"/access/updateUserAccess";
	public static final String REMOVE_USER_ACCESS=PREFIX+"/access/removeUserAccess";
	public static final String GET_SUBNODE_BY_NODE_AND_ROLE=PREFIX+"/access/getSubNodeByNodeAndRole";
	public static final String GET_SUPER_SUBNODE_BY_SUBNODE=PREFIX+"/access/getSuperSubNodeBySubNode";

	
	//Company Services
	public static final String CREATE_COMPANY=PREFIX+"/company/createCompany";
	public static final String GET_COMPANY=PREFIX+"/company/getCompany";
	public static final String DELETE_COMPANY=PREFIX+"/company/deleteCompany";
	public static final String GET_ALL_COMPANY=PREFIX+"/company/getAllCompany";
	public static final String UPDATE_COMPANY=PREFIX+"/company/updateCompany";






}
