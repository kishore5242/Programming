package com.kishore5242.photorg.util;

public class CheckOps {

	public static boolean isNotEmptyString(String filePathString) {
		if(filePathString != null &&
				!filePathString.isEmpty()
				) {
			return true;
		}
		return false;
	}
	
}
