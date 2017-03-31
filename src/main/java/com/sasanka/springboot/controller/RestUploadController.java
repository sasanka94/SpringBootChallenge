package com.sasanka.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sasanka.springboot.model.UploadModel;
import com.sasanka.springboot.repository.MetaDataRepository;
import com.sasanka.springboot.service.MetaDataService;
import com.sasanka.springboot.model.MetaData;
import com.sasanka.springboot.model.Sample;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@RestController
public class RestUploadController {

	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	
	@Autowired
	MetaDataService service;


	@Autowired
	MetaDataRepository metadatarepo;

	// Single file upload
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) throws ParseException {
		logger.debug("Single file upload!");
		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		try {

			service.saveUploadedFiles(Arrays.asList(uploadfile));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);
	}

	// Multiple file upload
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/api/upload/multi")
	public ResponseEntity<?> uploadFileMulti(@RequestParam("extraField") String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles) throws ParseException {

		logger.debug("Multiple file upload!");

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			service.saveUploadedFiles(Arrays.asList(uploadfiles));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);

	}

	// maps html form to a Model
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/api/upload/multi/model")
	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model) throws ParseException {

		logger.debug("Multiple file upload! With UploadModel");

		try {

			service.saveUploadedFiles(Arrays.asList(model.getFiles()));

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

	@RequestMapping(value = "/api/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<MetaData>> retrieveAllRecords() {

		Collection<MetaData> mt = new ArrayList<>();
		for (MetaData metadata : metadatarepo.findAll()) {
			mt.add(metadata);
		}

		return new ResponseEntity<Collection<MetaData>>(mt, HttpStatus.OK);

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/api/getAll/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MetaData> getById(@PathVariable("id") long id) {

		MetaData mt = metadatarepo.findOne(id);
		

		return new ResponseEntity<MetaData>(mt, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/api/getId/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Long>> getIdbyFileType(@PathVariable("name") String fileType){
		List<Long> id = new ArrayList<>();
		List<MetaData> sample = metadatarepo.findAllByFileType(fileType);
		Iterator it = sample.iterator();
		while (it.hasNext()) {
			id.add(((MetaData) it.next()).getId());
		}
		return new ResponseEntity<Collection<Long>>(id,HttpStatus.OK);
	}
		
}
