package com.kishore5242.photorg.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class FileService {

	private static Log log = LogFactory.getLog(FileService.class);
	
	@Value("${test.key}")
	private String testValue;
	
	public void copyAllFiles(Path srcDirPath, Path destDirPath) {
		try {
			
			FileUtils.copyDirectory(srcDirPath.toFile(), destDirPath.toFile());
			
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	public void copyFile(Path filePath, Path destDirPath) {
		try {
			
			FileUtils.copyFileToDirectory(filePath.toFile(), destDirPath.toFile());
			
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	public List<Path> listAllFilesInDirectory(String directoryPath) {
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				return true;
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			log.info(e);
		}
		
		return fileList;
	}

	public List<Path> listAllImagesInDirectory(String directoryPath) {
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				String mimetype;
				try {
					mimetype = Files.probeContentType(filePath);
					if (mimetype != null && mimetype.split("/")[0].equals("image")) {
					    return true;
					}
				} catch (IOException e) {
					log.info(e);
				}
				return false;
				
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			log.info(e);
		}
		
		//TODO delete logging
		fileList.forEach(filePath -> {
			log.info(filePath.toString());
		});
		
		
		return fileList;
	}
	
	public List<Path> listAllVideosInDirectory(String directoryPath) {
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				String mimetype;
				try {
					mimetype = Files.probeContentType(filePath);
					if (mimetype != null && mimetype.split("/")[0].equals("video")) {
					    return true;
					}
				} catch (IOException e) {
					log.info(e);
				}
				return false;
				
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			log.info(e);
		}
		
		return fileList;
	}
	
	public List<Path> listAllImagesAndVideosInDirectory(String directoryPath) {
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				String mimetype;
				try {
					mimetype = Files.probeContentType(filePath);
					if (mimetype != null && 
							(mimetype.startsWith("image") ||
									mimetype.startsWith("image"))) {
					    return true;
					}
				} catch (IOException e) {
					log.info(e);
				}
				return false;
				
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			log.info(e);
		}
		
		return fileList;
	}
	
	public void organizeMonthly(String srcPathStr, String destPathStr) {
		
		List<Path> imageFilePaths = listAllImagesInDirectory(srcPathStr);
		imageFilePaths.forEach(imageFilePath -> {
			
			//copyFile(imageFilePath, destDirPath);
		});
		
		
		
		
		List<Path> videoFilePaths = listAllVideosInDirectory(srcPathStr);
		
	}
	
}
