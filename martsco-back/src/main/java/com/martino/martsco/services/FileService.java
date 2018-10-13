package com.martino.martsco.services;

import javax.servlet.ServletContext;

import org.springframework.http.MediaType;

import com.martino.martsco.util.LoggerApp;

public class FileService {
	// abc.zip
	// abc.pdf,..
	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
		// application/pdf
		// application/xml
		// image/gif, ...
		String mineType = servletContext.getMimeType(fileName);
		try {
			MediaType mediaType = MediaType.parseMediaType(mineType);
			return mediaType;
		} catch (Exception e) {
			LoggerApp.info("Could not determine file type.");
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}