package com.martino.martsco.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.reports.Report;
import com.martino.martsco.reports.ReportService;
import com.martino.martsco.services.FileService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/file")
public class FileController {
	@Autowired
	private FileStorageProperties fileStorageProperties;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private ReportService service;

	@GetMapping("/report/{fileName:.+}/download")
	public ResponseEntity<ByteArrayResource> download(@PathVariable String fileName) throws Exception {
		File file = null;
		if (fileName.equals("liste_annee")) {
			file = service.createRepport(Report.LISTE_ANNEE);
		}

		MediaType mediaType = FileService.getMediaTypeForFileName(this.servletContext, file.getName());

		Path path = Paths.get(fileStorageProperties.getFolder() + "/" + file.getName());
		byte[] data = Files.readAllBytes(path);
		ByteArrayResource resource = new ByteArrayResource(data);

		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
				// Content-Type
				.contentType(mediaType) //
				// Content-Lengh
				.contentLength(data.length) //
				.body(resource);
	}
}