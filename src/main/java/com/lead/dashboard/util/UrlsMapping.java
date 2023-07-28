package com.lead.dashboard.util;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8081")

public class UrlsMapping {
	static String version="api/v1/";
	public final  static  String GET_ALL_LEAD = version+"api/v1/lead/getAllLead";

}
