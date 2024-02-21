package com.lead.dashboard.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.lead.dashboard.domain.BitrixLead;

@EnableBatchProcessing
@Configuration
public class CsvBatchConfig {

	
 
	public FlatFileItemReader<BitrixLead> bitrixReader(){
		FlatFileItemReader<BitrixLead> itemReader = new FlatFileItemReader<>(); 
		itemReader.setResource(new FileSystemResource("// "));
		itemReader.setName("csv-reader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}

	private LineMapper<BitrixLead> lineMapper() {
		DefaultLineMapper<BitrixLead>lineMapper = new DefaultLineMapper();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","lead_name","status","client_name","mobile_no","email","assignee_person");
		
		
		return null;
	}
}
