package com.sasanka.springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class MetaData implements Serializable {
	
	@Id
	@GeneratedValue
	private long Id;
	private String fileName;
	private String fileSize;
	private String fileType;
	private String fileDate;
		
/*	static class Builder
	{
		private final String fileName;
		private final String fileSize;
		private final String fileType;
		private final String fileDate;
		
		public MetaData build()
		{
			return new MetaData(this);
		}
		
	private MetaData(Builder build)
	{
		
		this.fileName= Builder.fileName;
		this.fileSize= Builder.fileSize;
		this.fileType= Builder.fileType;
		this.fileDate= Builder.fileDate;
	}
	}*/
	
	public MetaData(){
		
	}
	
	public MetaData(String fileName, String fileSize, String fileType, String fileDate) {
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileType = fileType;
		this.fileDate = fileDate;
	}
	public String getFileName() {
		return fileName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public String getFileDate() {
		return fileDate;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MetaData [Id=");
		builder.append(Id);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", fileType=");
		builder.append(fileType);
		builder.append(", fileDate=");
		builder.append(fileDate);
		builder.append("]");
		return builder.toString();
	}

	public long getId() {
		return Id;
	}
	
	

		
	
}
