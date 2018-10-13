package com.martino.martsco.services;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.martino.martsco.util.JasperService;
import com.martino.martsco.util.Report;
import com.martino.martsco.util.ReportData;

@Service
public class ReportService {
	@Autowired
	AnneeService anneeService;
	private int type;
	@Autowired
	private ReportData data;
	@Autowired
	private JasperService jasperService;

	// This method generates a PDF report
	public File createRepport(int type) throws IOException {
		this.type = type;
		String titre;
		Map<String, Object> paramaters;

		switch (this.type) {
		case Report.LISTE_ANNEE:
			titre = "entete_portrait";
			paramaters = data.getAnneeParams();
			break;
		default:
			titre = "pdf";
			paramaters = null;
			break;
		}

		// lancement de la génération du documents
		return jasperService.generateReport(titre, paramaters);
	}
}
