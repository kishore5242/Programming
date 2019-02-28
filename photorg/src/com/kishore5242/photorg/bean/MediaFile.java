package com.kishore5242.photorg.bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class MediaFile {

	private Path mediaPath;
	private BasicFileAttributes basicFileAttributes;
		
	public MediaFile(Path mediaPath) throws IOException {
		super();
		this.mediaPath = mediaPath;
		this.basicFileAttributes = Files.readAttributes(mediaPath, BasicFileAttributes.class);
	}

	public Path getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(Path mediaPath) {
		this.mediaPath = mediaPath;
	}
	
	public BasicFileAttributes getBasicFileAttributes() {
		return basicFileAttributes;
	}

	/**
	 * Identifies if two media files are same
	 */
	@Override
	public boolean equals(Object obj) {
		
		// if both the object references are  
	    // referring to the same object. 
	    if(this == obj) 
	            return true; 
	          
	        // it checks if the argument is of the  
	        // type MediaFile by comparing the classes  
	        // of the passed argument and this object. 
	        // if(!(obj instanceof MediaFile)) return false; ---> avoid. 
	        if(obj == null || obj.getClass()!= this.getClass()) 
	            return false; 
	          
	        // type casting of the argument.  
	        MediaFile mediaFile = (MediaFile) obj; 
	          
	        // null check for both
	        if(this.mediaPath == null || mediaFile.getMediaPath() == null) {
	        	return false;
	        }
	        
	        // comparing the state of argument with  
	        // the state of 'this' Object. 
	        // two files re same if name is same and size is same
	        if(this.mediaPath.getFileName().equals(mediaFile.getMediaPath().getFileName()) && 
	        		this.basicFileAttributes.size() == mediaFile.basicFileAttributes.size()) {
	        	return true;
	        }

	        return false;
	        
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	
}
