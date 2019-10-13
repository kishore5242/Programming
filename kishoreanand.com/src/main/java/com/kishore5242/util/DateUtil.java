package com.kishore5242.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class DateUtil {

	private static final Logger logger = LogManager.getLogger(DateUtil.class);

	public static String getCurrentDate(String format) {
		// "yyyy/MM/dd HH:mm:ss"
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}
