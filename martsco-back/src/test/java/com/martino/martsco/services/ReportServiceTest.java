package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.reports.ReportFactory;
import com.martino.martsco.util.Report;

public class ReportServiceTest extends MyIntegrationTest {
	@Autowired
	ReportFactory reportService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateRepport() throws IOException {
		assertNotNull("le fichier pdf n'a pas été généré", reportService.createRepport(Report.LISTE_ANNEE));
	}
}
