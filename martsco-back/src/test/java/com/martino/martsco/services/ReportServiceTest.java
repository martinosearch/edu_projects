package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;

public class ReportServiceTest extends MyIntegrationTest {
	@Autowired
	ReportService reportService;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateRepport() {
		assertNotNull("le fichier pdf n'a pas été généré", reportService.createRepport());
	}

	@Test
	public void testGetOutPutFile() {
		assertNotNull("le fichier de sortie est introuvable", reportService.getOutPutFile());
	}

	@Test
	public void getReportDesignFile() {
		assertNotNull("le fichier de model de rapport est introuvable", reportService.getReportDesignFile());
	}
}
