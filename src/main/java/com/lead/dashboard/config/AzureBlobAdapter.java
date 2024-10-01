package com.lead.dashboard.config;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClientBuilder;

@Service
public class AzureBlobAdapter {

    @Autowired
    BlobClientBuilder client;

    @Autowired
    AmazonS3 amazonS3Client;

    @Value("${aws.s3.bucket-name}")
    private String awsBucketName;
    
    public String uploadT(MultipartFile file, long prefixName) {
    	String fileName=null;
    	if(file != null && file.getSize() > 0) {
            try {
                //implement your own file name logic.
            	if(prefixName!=0)
            	fileName = prefixName+file.getOriginalFilename().replace(" ", "_");
            	
            	else fileName=file.getOriginalFilename().replace(" ", "_");
            	boolean fileExist = isFileExist(fileName);
            	if(!fileExist)
                client.blobName(fileName).buildClient().upload(file.getInputStream(),file.getSize());               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	    	
        return fileName;
    }
    public String uploadv2(MultipartFile file, long prefixName) {
    	String fileName=null;
    	if(file != null && file.getSize() > 0) {
            try {
                //implement your own file name logic.
            	if(prefixName!=0)
            	fileName = prefixName+file.getOriginalFilename().replace(" ", "_");
            	
            	else fileName=file.getOriginalFilename().replace(" ", "_");
            	
            	
           	    long date = new Date().getTime();
               	String path=""+date;
            	fileName=path+fileName;
            	boolean fileExist = isFileExist(fileName);
            	if(!fileExist)
                client.blobName(fileName).buildClient().upload(file.getInputStream(),file.getSize());               
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	    	
        return fileName;
    }

    public byte[] getFile(String name) {
        try {
            File temp = new File("/temp/"+name);
//            BlobProperties properties = client.blobName(name).buildClient().downloadToFile(temp.getPath());
            byte[] content = Files.readAllBytes(Paths.get(temp.getPath()));
            temp.delete();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteFile(String name) {
        try {
        	if(client.blobName(name).buildClient().exists())
            client.blobName(name).buildClient().delete();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean isFileExist(String name) {
    	boolean flag=false;
        try {
        	if(client.blobName(name).buildClient().exists())            
        		flag=true;        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    //AWS Upload method
    public String upload(MultipartFile file, long prefixName) {
        String fileName = null;

        if (file != null && file.getSize() > 0) {
            try {
                if (prefixName != 0) {
                    fileName = prefixName + file.getOriginalFilename().replace(" ", "_");
                } else {
                    fileName = file.getOriginalFilename().replace(" ", "_");
                }

                boolean fileExist = isFileExist(fileName);
                if (!fileExist) {
                    ObjectMetadata metadata = new ObjectMetadata();
                    metadata.setContentLength(file.getSize());

                    PutObjectRequest putObjectRequest = new PutObjectRequest(awsBucketName, fileName, file.getInputStream(), metadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead);

                    amazonS3Client.putObject(putObjectRequest);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileName;
    }








}
