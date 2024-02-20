package com.lead.dashboard.controller.leadController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.lead.dashboard.service.FileUploadService;
import com.lead.dashboard.util.ResponseMessage;


@RestController
public class FileUploadController {

	  @Autowired
	  FileUploadService storageService;
	  
	  @PostMapping("/uploadFileData")
	  public void uploadFilesData(@RequestParam MultipartFile files) {
		  if(files.isEmpty()) {	  
		  }
		  if(files.getContentType().equals("image/jpeg")) {
			  System.out.println("iiiiiiii");
		  }
		  storageService.uploadFilesData(files);
	  }
	  
	  @GetMapping("/getFileData")
	  public String[] getFileData() {	  
		  String[] s=storageService.getFilesData();
            return s;
	  }
	  
	  //=========================================Upload Image = = == = = = = = = = == = = = = = = = = = == =  = 
	
	  
	  @PostMapping("/uploadimageToFileSystem")
	  public String uploadimageToFileSystem(@RequestParam MultipartFile files) throws IllegalStateException, IOException {
		  String imageData=storageService.uploadImageToFileData(files);
           return imageData;
	  }
	  
	  @GetMapping("/downloadImageToFileSystem")
	  public byte[] downloadImageToFileSystem(@RequestParam String filePath) throws IllegalStateException, IOException {
		  byte[] imageData=storageService.downloadImageToFileSystem(filePath);
           return imageData;
	  }
	  
	  
	  @GetMapping("/getImageToFileSystem")
	  public String getImageToFileSystem(@RequestParam String filePath) throws IllegalStateException, IOException {
		  String imageData=storageService.getImageToFileSystem(filePath);
           return imageData;
	  }

	  
}
