package com.lead.dashboard.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.lead.dashboard.domain.BitrixLead;
import com.lead.dashboard.repository.BitrixLeadRepository;

@EnableBatchProcessing
@Configuration
public class CsvBatchConfig {

	
	@Autowired
	BitrixLeadRepository bitrixLeadRepository;
	
//	@Autowired
//	StepBuilderFactory stepBuilderFactory;
//	
//	@Autowired
//	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private PlatformTransactionManager transactionManager;
	
 // Item Reader
	public FlatFileItemReader<BitrixLead> bitrixReader(){
		FlatFileItemReader<BitrixLead> itemReader = new FlatFileItemReader<>(); 
		itemReader.setResource(new FileSystemResource("C:/Users/user/Downloads/exported_data (1).csv"));
		itemReader.setName("csv-reader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
// ItemData Processor
	private LineMapper<BitrixLead> lineMapper() {
		DefaultLineMapper<BitrixLead>lineMapper = new DefaultLineMapper();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","lead_name","status","client_name","mobile_no","email","assignee_person");
		
		//convert into java 
		BeanWrapperFieldSetMapper<BitrixLead>fieldSetMapper =new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(BitrixLead.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	//Processor
	
	@Bean
	public  BitrixDataProcessor createProcessor() {
		return new BitrixDataProcessor();
	}
	
	
//	Item Writer
	@Bean
	public RepositoryItemWriter<BitrixLead>bitrixWriter(){
		
		RepositoryItemWriter<BitrixLead>repositoryWriter = new RepositoryItemWriter<>();
		repositoryWriter.setRepository(bitrixLeadRepository);
		repositoryWriter.setMethodName("save");
		return repositoryWriter;
	}
// create Steps
//	@Bean
//	public Step step() {
//		return stepBuilderFactory.get("step-1").<BitrixLead ,BitrixLead>chunk(10)
//				               .reader(bitrixReader())
//				               .processor(createProcessor())
//				               .writer(bitrixWriter())
//				               .build();
//	}
//	
//	// create job
//	public Job job() {
//		return jobBuilderFactory.get("customers-job")
//				.flow(step())
//				.end()
//				.build();
//	}
	
	@Bean
	public Step step1() {
	    return new StepBuilder("csv-step", jobRepository)
	        .<BitrixLead, BitrixLead>chunk(10, transactionManager)
	        .reader(bitrixReader())
	        .processor(createProcessor())
	        .writer(bitrixWriter())
//	        .taskExecutor(taskExecutor())
	        .build();
	    }

	@Bean
	public Job runJob() {
	    return new JobBuilder("MSTabcNEUser", jobRepository)
	        .start(step1())
	        .build();
	}
	
}
