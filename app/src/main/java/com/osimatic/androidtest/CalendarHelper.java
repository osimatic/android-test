package com.osimatic.androidtest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarHelper {
	public static String formatDateTime(Calendar date, Locale locale) {
		return formatDate(date, locale)+" "+formatTime(date, locale);
	}
	public static String formatDate(Calendar date, Locale locale) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		df.setTimeZone(date.getTimeZone());
		return df.format(new Timestamp(date.getTimeInMillis()));
	}
	public static String formatTime(Calendar date, Locale locale) {
		DateFormat df = DateFormat.getTimeInstance(DateFormat.SHORT, locale);
		df.setTimeZone(date.getTimeZone());
		return df.format(new Timestamp(date.getTimeInMillis()));
	}

	public static Calendar timestampToCalendar(long timestamp) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTimeInMillis(timestamp);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Calendar timestampToCalendar(long timestamp, String timeZone) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZone), Locale.getDefault());
		//Calendar cal = Calendar.getInstance(Locale.getDefault());
		//cal.setTimeZone(TimeZone.getTimeZone(timeZone));
		cal.setTimeInMillis(timestamp);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

}
