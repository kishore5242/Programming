package com.kishore5242.photorg.driver;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kishore5242.photorg.config.SpringConfig;
import com.kishore5242.photorg.service.FileService;
import com.kishore5242.photorg.util.FileOps;

public class Driver {

	public static void main(String[] args) {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		FileService fileService = ctx.getBean(FileService.class);

		Scanner scanIn = new Scanner(System.in);
		
		String srcPathStr = "";
		System.out.println("PLEASE ENTER THE FOLDER WHICH YOU WISH TO ORGANIZE. Eg., D:\\media\\ ");
		srcPathStr = scanIn.nextLine();
		
		while(!FileOps.isValidSrcPath(srcPathStr)) {
			System.out.println("SORRY NOT A VALID FOLDER PATH. MAKE SURE THE FOLDER EXISTS. \n PLEASE ENTER AGAIN. Eg., D:\\media\\ ");
			srcPathStr = scanIn.nextLine();
		}
		
		String destPathStr = "";
		System.out.println("PLEASE ENTER THE DESTINATION FOLDER. Eg., D:\\media_organized\\ ");
		destPathStr = scanIn.nextLine();
		
		while(!FileOps.isValidDestPath(destPathStr, srcPathStr)){
			System.out.println("SORRY NOT A VALID FOLDER PATH. MAKE SURE THE FOLDER EXISTS. \n PLEASE ENTER AGAIN. Eg., D:\\\\media_organized\\\\ ");
			destPathStr = scanIn.nextLine();
		}

		scanIn.close();
		
		// fileService.organizeIntoTimeFolders("I:\\gallery\\", "O:\\gallery");
		fileService.organizeIntoTimeFolders(srcPathStr, destPathStr);

	}

}
