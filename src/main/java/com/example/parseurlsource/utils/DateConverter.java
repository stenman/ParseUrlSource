package com.example.parseurlsource.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateConverter {
	public Date convertStringToDate(String dateToParse) {
		Date date;
		try {
			date = new SimpleDateFormat("M/d/yyyy HH:mm:ss", Locale.ENGLISH).parse(dateToParse);
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(date); 
			cal.add(Calendar.HOUR_OF_DAY, 6);
			return cal.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
