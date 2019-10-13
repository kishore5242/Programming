package com.kishore5242.blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kishore5242.util.FileUtil;

@Controller
@RequestMapping("/files")
public class FileUploadController {
	
	private static final Logger logger = LogManager.getLogger(FileUploadController.class);
	
	//Save the uploaded file to this folder
	@Value("${files.upload.loc}")
    private String UPLOADED_FOLDER;

    @GetMapping(value = {"/", ""})
    public String index(HttpServletRequest request, HttpServletResponse response) {
    	
    	String blog_id_str = request.getParameter("blog_id");
    	request.setAttribute("blog_id", blog_id_str);
    	
        return "files/upload";
    }
    
    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(HttpServletRequest request, HttpServletResponse response,
    								@RequestParam("file") MultipartFile file,
    								@RequestParam("blog_id") String blog_id,
                                   RedirectAttributes redirectAttributes) throws IOException {
        
    	// upload file
    	logger.info("File upload request for blog_id: "+blog_id);
        String filepath = blog_id+"/"+file.getOriginalFilename();
        FileUtil.writeMultipartFile(file, UPLOADED_FOLDER + filepath);

        request.setAttribute("filepath", filepath);
        
        return "files/uploadStatus";
    }

}
