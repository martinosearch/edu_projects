package com.martino.martsco.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtil {
    public static DateTime getDateTime(String str) {
	return new DateTime(getDate(str));
    }

    public static Date getDate(String str) {

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date date = null;
	try {
	    date = format.parse(str);
	} catch (ParseException e) {
	    System.out.println("La date n'est pas du type: ");
	    format = new SimpleDateFormat("dd-MM-yyyy");
	    try {
		date = format.parse(str);
	    } catch (ParseException e1) {
	    }
	}
	return date;
    }

    public static void main(String[] args) {
	String str = "03-06-2011";
	// DateTime date = DateUtil.getDateTime(str);
	Date date = DateUtil.getDate(str);

	DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
	formatter.withZoneUTC();

	System.out.println(date);
	// System.out.println(formatter.print(date));
    }

}
