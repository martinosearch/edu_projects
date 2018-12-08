package com.martino.martsco.properties;

import javax.annotation.ManagedBean;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ManagedBean
@ConfigurationProperties(prefix = "file")
@Data
public class FileStorageProperties {
	private String folder;
	private String folderImages;
	private String folderReports;
}
