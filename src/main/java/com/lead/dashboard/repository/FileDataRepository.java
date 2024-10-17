package com.lead.dashboard.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lead.dashboard.domain.lead.FileData;


public interface FileDataRepository extends JpaRepository<FileData, Long> {

	Optional<FileData> findByName(String fileName);
	
	@Query(value = "SELECT * FROM file_data fd WHERE fd.file_path in(:fileName)", nativeQuery = true)
	List<FileData> findByfilePathIn(List<String> fileName);
	
	@Query(value = "SELECT * FROM file_data fd WHERE fd.name =:fileName limit 1", nativeQuery = true)
	FileData findByNameExist(String fileName);

}
