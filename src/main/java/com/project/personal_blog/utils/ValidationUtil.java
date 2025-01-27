package com.project.personal_blog.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtil {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * Function check String of LocalDate is match yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static boolean validateDate(String date) {
		try {
			LocalDate.parse(date, DATE_FORMATTER);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
	
	/**
	 * Function check string of date can't not null
	 * @param date
	 * @return
	 */
	public static boolean validateDateNotNull (String date) {
		if (date != null && !date.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function check String of LocalDate is match with pattern provided
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static boolean validateDatePattern (String date, String pattern) {
		try {
			LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
	
	/**
	 * Function check string can't not null
	 * @param str
	 * @return
	 */
	public static boolean validateString (String str) {
		if (str != null && !str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Function check int is valid
	 * @param str
	 * @return
	 */
	public static boolean validateInt (String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
