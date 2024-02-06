package com.lead.dashboard.service;

import org.springframework.stereotype.Service;


import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface FileUploadService {

	boolean uploadFilesData(MultipartFile files);

}
