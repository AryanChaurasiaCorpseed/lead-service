package com.lead.dashboard.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lead.dashboard.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	public String UPLOAD_DIR="/Users/aryanchaurasia/Documents/Corpseed-img";
	
	 public boolean uploadFilesData( MultipartFile multipartFile) {
		    boolean f=false;
		    try {
		    	InputStream ls=multipartFile.getInputStream();
		    	byte data[]=new byte[ls.available()];
		    	ls.read(data);
		    	
		    	FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());
		    	fos.write(data);
		    	fos.flush();
		    	fos.close();
		    	f=true;
		    	
		    }catch(Exception e) {
		    	
		    }
//		  if(files.isEmpty()) {
//			  
//		  }
//		  if(files.getContentType().equals("image/jpeg")) {
//			  System.out.println("iiiiiiii");
//		  }
		    return f;


	  }




//	  private final Path root = Paths.get("uploads");
//
//	  @Override
//	  public void init() {
//	    try {
//	      Files.createDirectory(root);
//	    } catch (IOException e) {
//	      throw new RuntimeException("Could not initialize folder for upload!");
//	    }
//	  }
//
//	  @Override
//	  public void save(MultipartFile file) {
//	    try {
//	      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
//	    } catch (Exception e) {
//	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
//	    }
//	  }
//
//	  @Override
//	  public Resource load(String filename) {
//	    try {
//	      Path file = root.resolve(filename);
//	      Resource resource = new UrlResource(file.toUri());
//
//	      if (resource.exists() || resource.isReadable()) {
//	        return resource;
//	      } else {
//	        throw new RuntimeException("Could not read the file!");
//	      }
//	    } catch (MalformedURLException e) {
//	      throw new RuntimeException("Error: " + e.getMessage());
//	    }
//	  }
//
//	  @Override
//	  public void deleteAll() {
//	    FileSystemUtils.deleteRecursively(root.toFile());
//	  }
//
//	  @Override
//	  public Stream<Path> loadAll() {
//	    try {
//	      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
//	    } catch (IOException e) {
//	      throw new RuntimeException("Could not load the files!");
//	    }
//	  }
}
