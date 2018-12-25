package com.martino.service;

import java.util.Map;
import java.util.logging.Logger;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperReportFill {
	private static final Logger logger = Logger.getLogger(JasperReportFill.class.getName());

	public void createReport(String masterReportFileName, Map<String, Object> parameters, JRDataSource dataSource) {

		String subReportFileName = "design\\body_bull.jrxml";

		// load the template
		JasperDesign design = null;
		try {
			design = JRXmlLoader.load(masterReportFileName);
			logger.info("Model bien chargé");
		} catch (JRException ex) {
			logger.info("Dans la classe jasperservice lors du chargement du design");
		}

		// compile the template to report
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(design);
			logger.info("Report compilé avec succes");
		} catch (JRException e) {
			logger.info("Dans la classe jasperservice lors de la compilation");
		}

		// compile the subreport
		JasperReport jasperSubReport = null;
		try {
			jasperSubReport = JasperCompileManager.compileReport(subReportFileName);
			logger.info("Report compilé avec succes");
		} catch (JRException e) {
			logger.info("Dans la classe jasperservice lors de la compilation");
		}

		// add sub report to parameters
		parameters.put("subreportParameter", jasperSubReport);

		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		} catch (JRException e) {
			logger.info(e.getMessage());
		}

		JasperViewer viewer = new JasperViewer(jasperPrint);
		viewer.setVisible(true);
	}
}
