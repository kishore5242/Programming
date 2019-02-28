package com.kishore5242.photorg.bean;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RunDetails {
	
	private List<Path> identifiedMediaPaths = new ArrayList<Path>();
	private List<MediaFile> copiedMediaFiles = new ArrayList<MediaFile>();
	
	/**
	 * 
	 * @param mediaFile
	 */
	public void addCopiedMediaFile(MediaFile mediaFile) {
		this.copiedMediaFiles.add(mediaFile);
	}
	
	/**
	 * 
	 * @param mediaFile
	 * @return
	 */
	public boolean isImageAlreadyCopied(MediaFile mediaFile) {
		return this.copiedMediaFiles.contains(mediaFile);
	}
	
	/**
	 * 
	 * @param
	 */
	public void addIdentifiedMediaPaths(List<Path> mediaPaths) {
		this.identifiedMediaPaths.addAll(mediaPaths);
	}
	
	
}
