package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.models.NiveauEns;
import com.martino.martsco.models.TypeEns;

public class NiveauEnsServiceTest extends MyIntegrationTest {
	@Autowired
	NiveauEnsService niveauEnsService;
	@Autowired
	TypeEnsService typeEnsService;

	@Autowired
	NiveauEns niveauEns;
	@Autowired
	TypeEns typeEns;
	private TypeEns savedTypeEns;
	private NiveauEns savedNiveauEns;

	@Before
	public void setUp() throws Exception {
		typeEnsService.save(typeEns);
		savedTypeEns = typeEnsService.findAll().get(0);

		niveauEns.setTypeEnsId(savedTypeEns.getId());
		niveauEnsService.save(niveauEns);
		savedNiveauEns = niveauEnsService.findAll().get(0);
	}

	@After
	public void tearDown() throws Exception {
		typeEnsService.delete(savedTypeEns.getId());
		niveauEnsService.delete(savedNiveauEns.getId());
	}

	@Test
	public final void testFindAll() {
		assertNotNull("La methode findAll a echoué", niveauEnsService.findAll());
	}

	@Test
	public final void testFindOne() {
		assertNotNull("La methode findOne a echoué", niveauEnsService.findOne(savedNiveauEns.getId()));
	}
}
