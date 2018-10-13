package com.martino.martsco.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;
import javax.servlet.ServletContext;
import javax.swing.ImageIcon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.martino.martsco.models.Annee;
import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.services.AnneeService;
import com.martino.martsco.services.FileService;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@Data
public class ReportData {
	int type;
	@Autowired
	AnneeService anneeService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileStorageProperties fileStorageProperties;

	public Map<String, Object> getAnneeParams() {
		List<Annee> listItems = anneeService.findAll();
		JRBeanCollectionDataSource jRBean = new JRBeanCollectionDataSource(listItems);

		ImageIcon image = getImage("PLogo.jpg");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("p_logo", "resources\\static\\PLogo.jpg");
		parameters.put("annees", jRBean);
		parameters.put("signe_interprise", "Edition  © Martino Corporation (Tel: 91 75 56 32/ 97 19 20 84)");

		LoggerApp.info("Chargement des parametres effectué avec succès");
		return parameters;
	}

	private ImageIcon getImage(String str) {

		File file = null;

		MediaType mediaType = FileService.getMediaTypeForFileName(this.servletContext, str);
		Path path = Paths.get("resources\\static\\" + str);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (Exception ex) {
			LoggerApp.error("Un erreur est survenue", ex);
		}

		ImageIcon image = new ImageIcon(data);

		/*
		 * URL url = this.getClass().getClassLoader().getResource("static\\" + str);
		 * LoggerApp.info("===============================================>>" + url);
		 * ImageIcon image = new ImageIcon(url); Image finalImage = image.getImage();
		 */

		return image;
	}

}
