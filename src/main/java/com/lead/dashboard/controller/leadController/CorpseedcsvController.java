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

import com.lead.dashboard.service.CorpseedCsvService;
import com.lead.dashboard.service.FileUploadService;
import com.lead.dashboard.util.ResponseMessage;
import com.lead.dashboard.util.UrlsMapping;

@Controller
public class CorpseedcsvController {
	
//	@Autowired
//	CorpseedCsvService corpseedCsvService;
//	  
//	  @PostMapping(value = UrlsMapping.IMPORT_CSV_FILE, consumes = {"multipart/form-data"})
//	  public String uploadimageToFileSystem(@RequestParam(name = "file", required = false) MultipartFile files) throws IllegalStateException, IOException {
//		  String imageData=corpseedCsvService.uploadimageToFileSystem(files);
//         return imageData;
//	  }
}
