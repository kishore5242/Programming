package com.kishore5242.photorg.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class FileOps {

	final static Logger logger = Logger.getLogger(FileOps.class);
	
	public static void listAllFilesInDirectory(String directoryPath) {
		try {
			
			Files.list(Paths.get(directoryPath)).filter(filePath -> {
				return true;
			}).forEach(filePath -> {
				logger.info(filePath);
			});
			
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public static String getFileCreationDateString(Path path, String pattern) {
		
		String creationDateStr = "Time_Unknown";
		
		FileTime imageFileTime;
		try {
			
			imageFileTime = Files.readAttributes(path, BasicFileAttributes.class).lastModifiedTime();
			
			LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(imageFileTime.toMillis()), ZoneId.systemDefault());
			
			creationDateStr = DateOps.getFormattedDateString(localDateTime, pattern);
			
		} catch (IOException e) {
			logger.error(e);
		}
		
		return creationDateStr;
	}

	public static boolean isWhatsappImage(Path filePath) {
		String fileName = filePath.getFileName().toString();
		if(fileName.startsWith("IMG-") &&
				fileName.contains("-WA")) {
			return true;
		}
		return false;
	}

	public static boolean isWhatsappVideo(Path filePath) {
		String fileName = filePath.getFileName().toString();
		if(fileName.startsWith("VID-") &&
				fileName.contains("-WA")) {
			return true;
		}
		return false;
	}

	public static boolean isWhatsappAudio(Path filePath) {
		String fileName = filePath.getFileName().toString();
		if(fileName.startsWith("AUD-") &&
				fileName.contains("-WA")) {
			return true;
		}
		return false;
	}

	public static boolean isValidSrcPath(String srcPath) {
		
		if(null != srcPath && !srcPath.isEmpty() && srcPath.length() > 2) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isValidDestPath(String destPath, String srcPath) {
		
		if(null != srcPath && !srcPath.isEmpty() && srcPath.length() > 2) {
			if(null != destPath && !destPath.isEmpty() && destPath.length() > 2 && !srcPath.equalsIgnoreCase(destPath)) {
				return true;
			}
		}
		
		return false;
	}
}
