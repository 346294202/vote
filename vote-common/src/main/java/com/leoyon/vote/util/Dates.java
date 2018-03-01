package com.leoyon.vote.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dates {

	public static final String DEFAULT_FMT = "yyyy-MM-dd HH:mm:ss";

	public static String format(long timeMillis, String fmtString) {
		SimpleDateFormat fmt = new SimpleDateFormat(fmtString);//2012-12-27
		Date date = new Date(timeMillis);
		return fmt.format(date);
	}
	
	public static String format(Date time, String fmtString) {
		SimpleDateFormat fmt = new SimpleDateFormat(fmtString);//2012-12-27
		return fmt.format(time);
	}

	public static long parse(String timeString, String fmtString) throws ParseException {
		return parseTime(timeString, fmtString).getTime();
	}
	
	public static Date parseTime(String timeString, String fmtString) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat(fmtString);
		Date date = fmt.parse(timeString);
		return date;
	}

	public static Date parseTime(String s) throws ParseException {
		return parseTime(s, DEFAULT_FMT);
	}

}
