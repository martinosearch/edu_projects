package com.martino.martsco.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.properties.FileStorageProperties;
import com.martino.martsco.util.LoggerApp;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
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
	@Autowired
	private DataListeAnnee dataFactory;

	public File generateReport(String titre) {
		// Output file location
		// User home directory location
		// String userHomeDirectory = System.getProperty("user.home");

		String folder = fileStorageProperties.getFolder();

		File outputFile = null;
		try {
			outputFile = File.createTempFile("pdfTemp", ".pdf", new File(folder));
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputStream jrxmlMaster = null;
		InputStream jrxmlSubReport = null;
		try {
			jrxmlMaster = this.getClass().getClassLoader().getResource("\\reports\\" + titre + ".jrxml").openStream();
		} catch (IOException e1) {
			LoggerApp.error("Dans la classe jasperservice", e1);
		}

		// Data source
		// JRBeanCollectionDataSource jRBean = dataFactory.loadDataSource();
		Map<String, Object> parameters = dataFactory.loadParameters();

		// load the template
		JasperDesign design = null;
		try {
			design = JRXmlLoader.load(jrxmlMaster);
			LoggerApp.info("Model bien chargé");
		} catch (JRException ex) {
			LoggerApp.error("Dans la classe jasperservice lors du chargement du design", ex);
		}

		// compile the template to report
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(design);
			LoggerApp.info("Report compilé avec succes");
		} catch (JRException e) {
			LoggerApp.error("Dans la classe jasperservice lors de la compilation", e);
		}

		try {
			/* Using compiled version(.jasper) of Jasper report to generate PDF */
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

			/* outputStream to create PDF */
			OutputStream outputStream = new FileOutputStream(outputFile);

			/* Write content to PDF file */
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			finalFile = outputFile;

			LoggerApp.info("Completed Successfully: ");

		} catch (Exception e) {
			LoggerApp.error("Une erreur lors de la création du rapport", e);
		}

		return finalFile;
	}
}
