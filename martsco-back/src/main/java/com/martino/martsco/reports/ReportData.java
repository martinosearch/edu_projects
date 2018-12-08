package com.martino.martsco.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.models.Annee;
import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.services.AnneeService;
import com.martino.martsco.util.LoggerApp;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@Data
public class ReportData {
	int type;
	@Autowired
	AnneeService anneeService;
	@Autowired
	private FileStorageProperties fileStorageProperties;

	public Map<String, Object> getAnneeParams() {
		List<Annee> listItems = anneeService.findAll();
		JRBeanCollectionDataSource jRBean = new JRBeanCollectionDataSource(listItems);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("annees", jRBean);
		parameters.put("signe_interprise", "Edition  © Martino Corporation (Tel: 91 75 56 32/ 97 19 20 84)");

		String folderReports = fileStorageProperties.getFolderReports();
		LoggerApp.info("folder reports: " + folderReports);
		parameters.put("DETAILS", folderReports + "\\liste_annee.jasper");

		String folderImages = fileStorageProperties.getFolderImages();
		parameters.put("P_LOGO", folderImages + "\\p_logo.jpg");

		LoggerApp.info("folder images: " + folderImages);
		LoggerApp.info("Chargement des parametres effectué avec succès");
		return parameters;
	}
}
