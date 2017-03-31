package com.sasanka.springboot.model;



import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"fileName",
"fileSize",
"fileType",
"fileDate"
})
public class Sample {

@JsonProperty("fileName")
private String fileName;
@JsonProperty("fileSize")
private String fileSize;
@JsonProperty("fileType")
private String fileType;
@JsonProperty("fileDate")
private String fileDate;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("fileName")
public String getFileName() {
return fileName;
}

@JsonProperty("fileName")
public void setFileName(String fileName) {
this.fileName = fileName;
}

@JsonProperty("fileSize")
public String getFileSize() {
return fileSize;
}

@JsonProperty("fileSize")
public void setFileSize(String fileSize) {
this.fileSize = fileSize;
}

@JsonProperty("fileType")
public String getFileType() {
return fileType;
}

@JsonProperty("fileType")
public void setFileType(String fileType) {
this.fileType = fileType;
}

@JsonProperty("fileDate")
public String getFileDate() {
return fileDate;
}

@JsonProperty("fileDate")
public void setFileDate(String fileDate) {
this.fileDate = fileDate;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}