package com.sasanka.springboot.service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.sasanka.springboot.model.MetaData;
import com.sasanka.springboot.repository.MetaDataRepository;

@Service
public class MetaDataService {
	
	@Autowired
	MetaDataRepository metadatarepo;
	
	JSONParser parser = new JSONParser();
	
	// Save the uploaded file to this folder
		private static String UPLOADED_FOLDER = "F://New folder//";
	// save file
		public void saveUploadedFiles(List<MultipartFile> files) throws IOException, ParseException {

			List<MetaData> metaData = new ArrayList<>();

			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					continue; // next pls
				}
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);

				FileReader file_file = new FileReader(UPLOADED_FOLDER + file.getOriginalFilename());
				Object obj = parser.parse(file_file);
				JSONObject jsonObject = (JSONObject) obj;

				for (Object key : jsonObject.keySet()) {
					// based on you key types
					String keyStr = (String) key;
					Object keyvalue = jsonObject.get(keyStr);
					MetaData md = new Gson().fromJson(keyvalue.toString(), MetaData.class);

					metaData.add(md);

				}

				metadatarepo.save(metaData);
			}

		}

}
