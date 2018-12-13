package com.martino.martsco.reports;

import java.io.File;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.services.AnneeService;

@Service
public class ReportService {
	@Autowired
	AnneeService anneeService;
	private int type;
	@Autowired
	private ReportData data;
	@Autowired
	private JasperService jasperService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FileStorageProperties fileStorageProperties;

	// This method generates a PDF report
	public File createRepport(int type) throws Exception {
		this.type = type;
		String titre;
		Map<String, Object> parameters;

		String[] sousReports = null;
		switch (this.type) {
		case Report.LISTE_ANNEE:
			// titre = "entete_portrait";
			titre = "liste_annee";
			sousReports = new String[1];
			sousReports[0] = "liste_annee";
			parameters = data.getAnneeParams();
			break;
		default:
			titre = "pdf";
			parameters = null;
			break;
		}

		// ajout des paramètres généraux
		// String context = servletContext.getRealPath("/");

		// lancement de la génération du documents
		return jasperService.generateReport(titre, parameters);
	}
}
