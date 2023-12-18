package com.lead.dashboard.util;

import java.util.Calendar;
import java.util.Date;

public class CorpseedCalender {

	 public static Date addDate(Date date,int count) { 
			Calendar c = Calendar.getInstance();
	        c.setTime(date);
			c.add(Calendar.DATE, count);
			Date res = c.getTime();
	        return res;		
	 }
	 
	 public static Date subtractDate(Date date,int count) { 
			Calendar c = Calendar.getInstance();
	        c.setTime(date);
			c.add(Calendar.DATE, -count);
			Date res = c.getTime();
	        return res;		
	 }
	
}
