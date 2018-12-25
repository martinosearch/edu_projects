package com.martino.martsco.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.models.Annee;
import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.services.AnneeService;
import com.martino.martsco.util.LoggerApp;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
public class DataListeAnnee {
	@Autowired
	AnneeService anneeService;
	@Autowired
	private FileStorageProperties fileStorageProperties;

	public JRBeanCollectionDataSource loadDataSource() {

		List<Annee> listItems = new ArrayList<>();
		Annee annee1 = new Annee();
		Annee annee2 = new Annee();
		annee1.setIntitule("2017-2018");
		annee1.setIntitule("2018-2019");
		listItems.add(annee1);
		listItems.add(annee2);

		// List<Annee> listItems = anneeService.findAll();
		LoggerApp.info("La taille de la liste des années est: " + listItems.size());
		return new JRBeanCollectionDataSource(listItems);
	}

	public Map<String, Object> loadParameters() {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("signe_interprise", "Edition  © Martino Corporation (Tel: 91 75 56 32/ 97 19 20 84)");
		parameters.put("DETAILS", "\\reports\\liste_annee.jasper");
		String folderImages = fileStorageProperties.getFolderImages();
		parameters.put("P_LOGO", folderImages + "\\p_logo.jpg");
		parameters.put("DATA_ANNEE", loadDataSource());

		LoggerApp.info("Le contexte est: " + folderImages);
		LoggerApp.info("Chargement des parametres effectué avec succès");

		return parameters;
	}
}
