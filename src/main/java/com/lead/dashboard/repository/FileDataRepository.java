package com.lead.dashboard.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lead.dashboard.domain.lead.FileData;


public interface FileDataRepository extends JpaRepository<FileData, Long> {

	Optional<FileData> findByName(String fileName);

}
