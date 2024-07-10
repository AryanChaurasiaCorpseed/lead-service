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
	public static final String CREATE_VIEW_HISTORY =PREFIX+"/lead/viewHistoryCreate";
	public static final String UPDATE_MULTI_LEAD_ASSIGNE =PREFIX+"/lead/updateMultiLeadAssigne";
	public static final String DELETE_MULTI_LEAD =PREFIX+"/lead/deleteMultiLead";
    public static final String UPDATE_STATUS_AND_AUTO_SAME =PREFIX+"/lead/updateStatusAndAutoSame";
	public static final String UPDATE_LEAD_ORIGINAL_NAME=PREFIX+"/lead/updateLeadOriginalName";
	public static final String GET_ALL_LEAD_NAME_AND_ID=PREFIX+"/lead/getAllLeadNameAndId";
	public static final String UPDATE_HELPER=PREFIX+"/lead/updateHelper";


	//=================== Lead - Oppoertunity Service ===================
	
	public static final String CREATE_OPPORTUNITY = PREFIX+"/leadOpportunity/createOpportunity";
	public static final String UPDATE_OPPORTUNITY = PREFIX+"/leadOpportunity/UpdateOpportunity";
	public static final String GET_OPPORTUNITY_BY_ID = PREFIX+"/leadOpportunity/getOpportunityById";
	public static final String CRONE_HIT = PREFIX+"/leadOpportunity/croneHit";

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
	public static final String GET_ALL_PROJECT_BY_COMPANY =PREFIX+"/company/getAllProjectByCompany";
	public static final String GET_ALL_LEAD_BY_COMPANY =PREFIX+"/company/getAllLeadByCompany";
	public static final String GET_ALL_PARENT_COMPANY=PREFIX+"/company/getAllParentCompany";

	
	// Sales-Dashboard
	
	public static final String GET_NO_OF_LEAD_DATA_GRAPH=PREFIX+"/salesDashboard/getNoOfLeadDataGraph";
	public static final String GET_LATEST_LEAD=PREFIX+"/salesDashboard/getLatestLead";
	
	
	// Project
	public static final String CREATE_PROJECT=PREFIX+"/project/createProject";
	public static final String CREATE_PROJECT_V2=PREFIX+"/project/createProjectV2";

	public static final String GET_ALL_PROJECT=PREFIX+"/project/getAllProject";
	public static final String GET_ALL_PROJECT_NAME_AND_ID=PREFIX+"/project/getAllProjectNameAndId";



	//    Notification
	public static final String GET_ALL_NOTIFICATION=PREFIX+"/notification/getAllNotification";
	public static final String VIEW_NOTIFICATION=PREFIX+"/notification/viewNotification";
	public static final String GET_UNSEEN_COUNT=PREFIX+"/notification/getUnseenCount";
	
	//    Urls
	public static final String UPLOAD_IMAGE_TO_FILE=PREFIX+"/upload/uploadimageToFileSystem";
	public static final String GET_IMAGE_TO_FILE=PREFIX+"/upload/getImageToFileSystem";


	public static final String IMPORT_CSV_FILE=PREFIX+"/upload/importCsvFile";

//     hr microservices
	public static final String GET_USER_APPROVAL_HR=PREFIX+"/hrManagment/getUserApprovalHr";
	public static final String APPROVED_USER_BY_HR=PREFIX+"/hrManagment/approvedUserByHr";

//  Rating Controller
	public static final String ADD_USER_AND_RATING=PREFIX+"/rating/addUserAndRating";
	public static final String GET_ALL_USER_RATING=PREFIX+"/rating/getAllUserRating";
	public static final String GET_ALL_RATING_BY_SERVICE_ID=PREFIX+"/rating/getAllRatingByServiceId";
	public static final String ADD_USER_IN_RATING_SERVICE=PREFIX+"/rating/addUserInRatingsService";
	public static final String UPDATE_USER_RATING_SERVICE=PREFIX+"/rating/updateUserRatingService";
	public static final String GET_RATING_BY_URLS=PREFIX+"/rating/getRetingByUrls";

	
	// User History
	public static final String GET_ALL_USER_HISTORY=PREFIX+"/rating/getAllUserHistory";



	public static final String GET_ALL_COMPLIANCE_DOCUMENTS=PREFIX+"/complianceDocumnets/getAllComplianceDocuments";

	
	//IVR Module
	public static final String GET_ALL_IVR_DATA=PREFIX+"/rating/getAllIvrData";
	public static final String CREATE_IVR_DATA=PREFIX+"/rating/createIvrData";




}
