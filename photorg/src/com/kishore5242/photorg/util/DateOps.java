package com.kishore5242.photorg.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateOps {

	public static String getFormattedDateString(LocalDateTime localDateTime, String pattern) {
		
		String str = "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		str = localDateTime.format(formatter);
		
		return str;
	}
	
}
