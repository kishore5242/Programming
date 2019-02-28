package com.kishore5242.photorg.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kishore5242.photorg.bean.MediaFile;
import com.kishore5242.photorg.bean.RunDetails;
import com.kishore5242.photorg.util.FileOps;


@Service
public class FileService {

	final static Logger logger = Logger.getLogger(FileOps.class);
	
	@Value("${test.key}")
	private String testValue;
	
	@Autowired
	private RunDetails runDetails;
	
	
	
	public void organizeIntoTimeFolders(String srcPathStr, String destPathStr) {
		
		// copy images
		logger.info("..........................................Organizing Image files..........................................");
		List<Path> imageFilePaths = listAllMediaFilesInDirectory(srcPathStr, "image");
		runDetails.addIdentifiedMediaPaths(imageFilePaths);
		copyImagesToTimeFolders(destPathStr, "yyyy-MM", imageFilePaths, 50000);
		
		// copy videos
		logger.info("..........................................Organizing video files..........................................");
		List<Path> videoFilePaths = listAllMediaFilesInDirectory(srcPathStr, "video");
		runDetails.addIdentifiedMediaPaths(videoFilePaths);
		copyVideosToTimeFolders(destPathStr, "yyyy-MM", videoFilePaths, 0);
		
		// copy audio
		logger.info("..........................................Organizing audio files..........................................");
		List<Path> audioFilePaths = listAllMediaFilesInDirectory(srcPathStr, "audio");
		runDetails.addIdentifiedMediaPaths(audioFilePaths);
		copyAudiosToTimeFolders(destPathStr, "yyyy-MM", audioFilePaths, 0);
		
		// copy other files
		logger.info("..........................................Organizing other files..........................................");
		List<String> excludedMediaFiles = Arrays.asList("image", "video", "audio");
		List<Path> nonMediaFilePaths = listAllFilesInDirectory(srcPathStr, excludedMediaFiles);
		runDetails.addIdentifiedMediaPaths(nonMediaFilePaths);
		copyFilesToTimeFolders(destPathStr, "yyyy-MM", nonMediaFilePaths, 0);
		
	}


	public void copyImagesToTimeFolders(String destPathStr, String folderPattern, List<Path> filePaths, long minEligibleSize) {
		
		logger.info("Copying started...");
		
		long completedCount = 0;
		final long totalCount = filePaths.size();
		
		
		for(Path filePath: filePaths) {

			MediaFile mediaFile;
			try{
				mediaFile = new MediaFile(filePath);
				
				if(runDetails.isImageAlreadyCopied(mediaFile)) {
					//logger.info("duplicate: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Images/posible_duplicates/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(FileOps.isWhatsappImage(filePath)) {
					//logger.info("whatsapp file: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Images/whatsapp/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(mediaFile.getBasicFileAttributes().size() < minEligibleSize) {
					//logger.info("small: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Images/small/"));
				} else {
					copyFile(filePath, Paths.get(destPathStr+ "/Images/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
					runDetails.addCopiedMediaFile(mediaFile);
				}
				
			} catch (IOException e) {
				logger.error("File could not be organized - "+filePath);
			}
			
			logger.info("Images Copied: "+ ++completedCount +"/"+totalCount+ " --> "+filePath);
		
		}
		
		logger.info("Copying complete.");
		
	}

	public void copyVideosToTimeFolders(String destPathStr, String folderPattern, List<Path> filePaths, long minEligibleSize) {
		
		logger.info("Copying started...");
		
		long completedCount = 0;
		final long totalCount = filePaths.size();
		
		
		for(Path filePath: filePaths) {

			MediaFile mediaFile;
			try{
				mediaFile = new MediaFile(filePath);
				
				if(runDetails.isImageAlreadyCopied(mediaFile)) {
					//logger.info("duplicate: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Videos/posible_duplicates/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(FileOps.isWhatsappVideo(filePath)) {
					//logger.info("whatsapp file: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Videos/whatsapp/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(mediaFile.getBasicFileAttributes().size() < minEligibleSize) {
					//logger.info("small: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Videos/small/"));
				} else {
					copyFile(filePath, Paths.get(destPathStr+ "/Videos/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
					runDetails.addCopiedMediaFile(mediaFile);
				}
				
			} catch (IOException e) {
				logger.error("File could not be organized - "+filePath);
			}
			
			logger.info("Videos Copied: "+ ++completedCount +"/"+totalCount+ " --> "+filePath);
		
		}
		
		logger.info("Copying complete.");
		
	}
	
	
	public void copyAudiosToTimeFolders(String destPathStr, String folderPattern, List<Path> filePaths, long minEligibleSize) {
		
		logger.info("Copying started...");
		
		long completedCount = 0;
		final long totalCount = filePaths.size();
		
		
		for(Path filePath: filePaths) {

			MediaFile mediaFile;
			try{
				mediaFile = new MediaFile(filePath);
				
				if(runDetails.isImageAlreadyCopied(mediaFile)) {
					//logger.info("duplicate: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Audio/posible_duplicates/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(FileOps.isWhatsappAudio(filePath)) {
					//logger.info("whatsapp file: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Audio/whatsapp/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(mediaFile.getBasicFileAttributes().size() < minEligibleSize) {
					//logger.info("small: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Audio/small/"));
				} else {
					copyFile(filePath, Paths.get(destPathStr+ "/Audio/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
					runDetails.addCopiedMediaFile(mediaFile);
				}
				
			} catch (IOException e) {
				logger.error("File could not be organized - "+filePath);
			}
			
			logger.info("Audio Copied: "+ ++completedCount +"/"+totalCount+ " --> "+filePath);
		
		}
		
		logger.info("Copying complete.");
		
	}
	
	public void copyFilesToTimeFolders(String destPathStr, String folderPattern, List<Path> filePaths, long minEligibleSize) {
		
		logger.info("Copying started...");
		
		long completedCount = 0;
		final long totalCount = filePaths.size();
		
		
		for(Path filePath: filePaths) {

			MediaFile mediaFile;
			try{
				mediaFile = new MediaFile(filePath);
				
				if(runDetails.isImageAlreadyCopied(mediaFile)) {
					//logger.info("duplicate: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Others/posible_duplicates/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
				} else if(mediaFile.getBasicFileAttributes().size() < minEligibleSize) {
					//logger.info("small: "+ imageFilePath.getFileName().toString());
					copyFile(filePath, Paths.get(destPathStr+ "/Others/small/"));
				} else {
					copyFile(filePath, Paths.get(destPathStr+ "/Others/" + FileOps.getFileCreationDateString(filePath, folderPattern)));
					runDetails.addCopiedMediaFile(mediaFile);
				}
				
			} catch (IOException e) {
				logger.error("File could not be organized - "+filePath);
			}
			
			logger.info("Other files Copied: "+ ++completedCount +"/"+totalCount+ " --> "+filePath);
		
		}
		
		logger.info("Copying complete! (^_^) Your files are inside "+destPathStr);
		
	}

	public void copyAllFiles(Path srcDirPath, Path destDirPath) {
		try {
			
			FileUtils.copyDirectory(srcDirPath.toFile(), destDirPath.toFile());
			
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void copyFile(Path filePath, Path destDirPath) {
		try {
			
			if(filePath.toFile().isFile()) {
				FileUtils.copyFileToDirectory(filePath.toFile(), destDirPath.toFile());
			} else {
				logger.info("Folder: "+filePath);
			}
			
		} catch (Exception e) {
			logger.error("File Could not be copied - " + filePath);
		}
	}
	
	public List<Path> listAllFilesInDirectory(String directoryPath) {
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				return true;
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			logger.info(e);
		}
		
		return fileList;
	}

	public List<Path> listAllMediaFilesInDirectory(String directoryPath, String mediaType) {
		
		logger.info("Looking for "+mediaType+" files...");
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				String mimetype;
				try {
					mimetype = Files.probeContentType(filePath);
					if (mimetype != null &&
							mimetype.toLowerCase().startsWith(mediaType.toLowerCase())) {
					    return true;
					}
				} catch (IOException e) {
					logger.error(e);
				}
				return false;
				
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Identified "+fileList.size()+" "+mediaType+" files.");
		
		return fileList;
	}
	
	public List<Path> listAllFilesInDirectory(String directoryPath, List<String> excludeMediaTypes) {
		
		logger.info("Looking for files excluding "+Arrays.toString(excludeMediaTypes.toArray())+"....");
		
		List<Path> fileList = new ArrayList<>();
		
		try {

			fileList = Files.walk(Paths.get(directoryPath)).filter(filePath -> {
				String mimetype;
				boolean isValid = true;
				try {
					mimetype = Files.probeContentType(filePath);
					
					for(String mediaType: excludeMediaTypes) {
						if (mimetype != null &&
								mimetype.toLowerCase().startsWith(mediaType.toLowerCase())) {
							isValid = false;
						}
					}

				} catch (IOException e) {
					logger.error(e);
				}
				return isValid;
				
			}).collect(Collectors.toList());
			
		} catch (IOException e) {
			logger.error(e);
		}

		logger.info("Identified "+fileList.size()+" files.");
		
		return fileList;
	}
	
}
