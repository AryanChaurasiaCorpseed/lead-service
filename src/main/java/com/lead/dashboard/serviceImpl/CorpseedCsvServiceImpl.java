package com.lead.dashboard.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lead.dashboard.domain.lead.Lead;
import com.lead.dashboard.service.CorpseedCsvService;

public class CorpseedCsvServiceImpl implements CorpseedCsvService{

//	@Override
//	public String uploadimageToFileSystem(MultipartFile files) {
//		 try {
//		      List<Lead>lead = csvToTutorials(files.getInputStream());
//		      repository.saveAll(lead);
//		    } catch (IOException e) {
//		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
//		    }
//		 return null;
//	}
//	
//	public static String TYPE = "text/csv";
//	  static String[] HEADERs = { "Id", "Title", "Description", "Published" };
//
//	  public static boolean hasCSVFormat(MultipartFile file) {
//
//	    if (!TYPE.equals(file.getContentType())) {
//	      return false;
//	    }
//
//	    return true;
//	  }
//
//	  public static List<Lead> csvToTutorials(InputStream is) {
//	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//	        CSVParser csvParser = new CSVParser(fileReader,
//	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//
//	      List<Tutorial> tutorials = new ArrayList<Tutorial>();
//
//	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//	      for (CSVRecord csvRecord : csvRecords) {
//	        Tutorial tutorial = new Tutorial(
//	              Long.parseLong(csvRecord.get("Id")),
//	              csvRecord.get("Title"),
//	              csvRecord.get("Description"),
//	              Boolean.parseBoolean(csvRecord.get("Published"))
//	            );
//
//	        tutorials.add(tutorial);
//	      }
//
//	      return tutorials;
//	    } catch (IOException e) {
//	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//	    }
//	  }

}
