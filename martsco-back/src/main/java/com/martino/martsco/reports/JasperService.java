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

	public File generateReport(String titre, Map<String, Object> parameters) {
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

		InputStream jrxmlInput = null;
		try {
			jrxmlInput = this.getClass().getClassLoader().getResource("\\reports\\" + titre + ".jrxml").openStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// load the template
		JasperDesign design = null;
		try {
			design = JRXmlLoader.load(jrxmlInput);
			LoggerApp.info("Model bien chargé");
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// compile the template to report
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(design);
			LoggerApp.info("Report compilé avec succes");
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			LoggerApp.info("Une erreur lors de la création du rapport", e);
			return null;
		}

		return finalFile;
	}
}
