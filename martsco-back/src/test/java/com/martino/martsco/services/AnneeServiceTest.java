package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.models.Annee;

public class AnneeServiceTest extends MyIntegrationTest {
	@Autowired
	Annee annee;
	@Autowired
	AnneeService anneeService;
	private Annee savedAnnee;

	@Before
	public void setUp() throws Exception {
		anneeService.save(annee);
		savedAnnee = anneeService.findAll().get(0);
	}

	@After
	public void tearDown() throws Exception {
		anneeService.delete(savedAnnee.getId());
	}

	@Test
	public final void testFindAll() {
		assertNotNull("La methode findAll a echoué", anneeService.findAll());
	}

	@Test
	public final void testFindOne() {
		assertNotNull("La methode findOne a echoué", anneeService.findOne(savedAnnee.getId()));
	}
}
