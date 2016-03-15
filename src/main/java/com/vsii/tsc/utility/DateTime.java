package com.vsii.tsc.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
	public static String createDateText(){
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ;
		String dateText = dateFormat.format(date);
		return dateText;
		
	}

}
