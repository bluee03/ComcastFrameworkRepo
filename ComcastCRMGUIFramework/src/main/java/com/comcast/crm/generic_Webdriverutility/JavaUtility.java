package com.comcast.crm.generic_Webdriverutility;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	
	public int getRandomNumber() {
		Random ran= new Random();
	      int randomnum = ran.nextInt(5000);
		  return randomnum;
	}
	public String getSystemDateYYYYMMDD() {
		Date dateobj =new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
		return date;
	}
	
	public  String getRequiredDateYYYYMMDD(int days) {
		Date dateobj =new Date();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(dateobj);
	    Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String requireDate = sdf.format(cal.getTime());
		return requireDate;
		
//		 Calendar cal = Calendar.getInstance();
//		    cal.add(Calendar.DAY_OF_MONTH, days);
//		    Date data = cal.getTime();
//		    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
//		 String result = sdf.format(data);
//		 return result;
			
		
	}
	
	
	
	
}
