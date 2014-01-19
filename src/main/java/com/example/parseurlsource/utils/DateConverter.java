package com.example.parseurlsource.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

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
		} catch (ParseException pe) {
			logger.warn(String.format("Failed to convert String to Date. Exception: %s", pe));
		}
		return null;
	}

	/**
	 * Adds time to a provided Date object. There is probably a better way of doing this, but for now, this will have to do.
	 * 
	 * @param date
	 *            The Date object to add time to.
	 * @param year
	 *            Number of years to add.
	 * @param month
	 *            Number of months to add.
	 * @param day
	 *            Number of days to add.
	 * @param hour
	 *            Number of hours to add.
	 * @param minute
	 *            Number of minutes to add.
	 * @param second
	 *            Number of seconds to add.
	 * @param millisecond
	 *            Number of milliseconds to add.
	 * @return A Date object with the added time.
	 */
	public Date addTimeToDate(Date date, int year, int month, int day, int hour, int minute, int second, int millisecond) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		cal.add(Calendar.MINUTE, minute);
		cal.add(Calendar.SECOND, second);
		cal.add(Calendar.MILLISECOND, millisecond);
		return cal.getTime();
	}
}
