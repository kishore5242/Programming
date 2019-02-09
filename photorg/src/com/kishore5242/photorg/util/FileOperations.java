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

public class FileOperations {

	private static Log log = LogFactory.getLog(FileOperations.class);
	
	public static void listAllFilesInDirectory(String directoryPath) {
		try {
			
			Files.list(Paths.get(directoryPath)).filter(filePath -> {
				return true;
			}).forEach(filePath -> {
				log.info(filePath);
			});
			
		} catch (IOException e) {
			log.info(e);
		}
	}
	
	public static String getFileCreationDateString(Path path, String format) {
		
		String creationDateStr = "Time_Unknown";
		
		FileTime imageFileTime;
		try {
			
			imageFileTime = Files.readAttributes(path, BasicFileAttributes.class).creationTime();
			
			LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(imageFileTime.toMillis()), ZoneId.systemDefault());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return creationDateStr;
	}
	
}
