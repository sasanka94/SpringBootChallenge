package com.sasanka.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.sasanka.springboot.model.MetaData;

public interface MetaDataRepository extends JpaRepository<MetaData, Long> {
	
	List<MetaData> findAllByFileType(String fileType);

}
