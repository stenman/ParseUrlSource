package com.example.parseurlsource.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Helps with converting to and from java.util.Date objects
 * 
 * @author Roger
 * 
 */
@Component
@Scope("prototype")
public class DateConverter {

	/**
	 * Converts a String to a Date object.
	 * 
	 * @param dateToParse
	 *            The String representation on a date that should be converted to a Date object.
	 * @return A Date representation of the provided String.
	 */
	public Date convertStringToDate(String dateToParse) {
		Date date;
		try {
			date = new SimpleDateFormat("M/d/yyyy HH:mm:ss", Locale.ENGLISH).parse(dateToParse);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR_OF_DAY, 6);
			return cal.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}
}
