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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	private static final Logger logger = LogManager.getLogger(FileUtil.class);
	
	public static void writeMultipartFile(MultipartFile file,  String destpath) throws IOException {
        
		logger.info("Uploading file to "+destpath);
		
		// Get the file and save it somewhere
        byte[] bytes = file.getBytes();
        
        Path path = Paths.get(destpath);
        
        File f = path.toFile();
        if (f.getParentFile() != null) {
        	  f.getParentFile().mkdirs();
        }
        
        Files.write(path, bytes);
        
        logger.info("Successfully updated.");
	}
	
	public static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                logger.info("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	
	
//    public static String getHtmlFile(String dir) {
//    	
//    	String pattern = ".html";
//        File file = Paths.get(dir).toFile();
//        String foundFile = "";
//        
//        List<String> files = Stream.of(file.list((pFile, pString) ->  pString.endsWith(pattern))).collect(Collectors.toList());
//        if(files.size()>0) {
//        	foundFile = files.get(0);
//        } else {
//        	// TODO
//        }
//        
//        return foundFile;
//    }
    
    
    public static void clearFolder(String dir) {
    	File directory = Paths.get(dir).toFile();
    	directory.mkdirs();
        File[] files = directory.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
            	f.delete();
            }
        }
    }
    
    public static void deleteFolder(String dir) {
    	
    	File directory = Paths.get(dir).toFile();
    	directory.mkdirs();
    	directory.delete();
    }
    
    
    public static void modifyHtmlFile(String fullPath, String url) throws IOException {
    	//http://localhost:8080/uploaded/50/52/
    	
    	Path path = Paths.get(fullPath);
    	Charset charset = StandardCharsets.UTF_8;

    	String content = new String(Files.readAllBytes(path), charset);
    	content = content.replaceAll("src=\"", "src=\""+url);
    	content = content.replaceAll("href=\"", "href=\""+url);
    	Files.write(path, content.getBytes(charset));
    	
    		
    }
    
}
