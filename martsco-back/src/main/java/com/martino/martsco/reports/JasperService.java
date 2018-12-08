package com.martino.martsco.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.util.LoggerApp;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@ManagedBean
public class JasperService {
	private File finalFile;
	@Autowired
	private FileStorageProperties fileStorageProperties;
	private String folderReports;

	public File generateReport(String titre, String[] sousRapports, Map<String, Object> parameters) {
		// Output file location
		File outputFile = getOutPutFile();
		folderReports = fileStorageProperties.getFolderReports();

		try {
			for (String sousRapport : sousRapports) {
				File jrxmlInput = getReportDesignFile(titre);
				JasperDesign design = JRXmlLoader.load(jrxmlInput);
				JasperReport jasperReport = JasperCompileManager.compileReport(design);
				LoggerApp.info("sous rapport bien compilé");
			}

			File jrxmlInput = getReportDesignFile(titre);

			// load the template
			JasperDesign design = JRXmlLoader.load(jrxmlInput);
			LoggerApp.info("Model bien chargé");

			// compile the template to report
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			LoggerApp.info("Report compilé avec succes");

			/* Using compiled version(.jasper) of Jasper report to generate PDF */
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			/* outputStream to create PDF */
			OutputStream outputStream = new FileOutputStream(outputFile);

			/* Write content to PDF file */
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			finalFile = outputFile;

			LoggerApp.info("Completed Successfully: ");
			return finalFile;
		} catch (Exception e) {
			LoggerApp.info("Une erreur lors de la création du rapport", e);
			return null;
		}
	}

	public File getReportDesignFile(String titre) {
		try {
			// return this.getClass().getClassLoader().getResource("reports\\" + titre +
			// ".jrxml").openStream();
			return new File(folderReports + "\\" + titre+".jrxml");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public File getOutPutFile() {
		// User home directory location
		// String userHomeDirectory = System.getProperty("user.home");
		String userHomeDirectory = fileStorageProperties.getFolder();
		try {
			return File.createTempFile("pdfTemp", ".pdf", new File(userHomeDirectory));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
