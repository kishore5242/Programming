package com.kishore5242.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateUtil {

	private static final Logger logger = LogManager.getLogger(DateUtil.class);

	public static String getCurrentDate(String format) {
		// "yyyy/MM/dd HH:mm:ss"
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
