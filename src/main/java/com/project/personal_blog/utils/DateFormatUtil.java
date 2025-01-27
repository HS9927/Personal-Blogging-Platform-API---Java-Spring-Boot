package com.project.personal_blog.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {

	private static DateTimeFormatter formatterDDMMYYHHMMSSSlash = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private static DateTimeFormatter formatterDDMMYYDash = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static String formatLocalDateTimeToDDMMYYHHMMSS (LocalDateTime localDateTime) {
		return localDateTime.format(formatterDDMMYYHHMMSSSlash);
	}
	
	
	public static LocalDate formatStringLocalDateToLocalDate (String strLocalDate) {
		return LocalDate.parse(strLocalDate, formatterDDMMYYDash);
	}
}
