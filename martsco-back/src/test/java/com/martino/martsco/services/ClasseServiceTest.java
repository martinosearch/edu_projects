package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.models.Classe;
import com.martino.martsco.models.NiveauEns;
import com.martino.martsco.models.TypeEns;

public class ClasseServiceTest extends MyIntegrationTest {
	@Autowired
	private NiveauEns niveauEns;
	@Autowired
	NiveauEnsService niveauEnsService;
	@Autowired
	Classe classe;
	@Autowired
	ClasseService service;
	@Autowired
	private TypeEns typeEns;
	@Autowired
	TypeEnsService typeEnsService;
	private Classe savedClasse;
	private NiveauEns savedNiveauEns;
	private TypeEns savedTypeEns;

	@Before
	public void setUp() throws Exception {
		typeEnsService.save(typeEns);
		savedTypeEns = typeEnsService.findAll().get(0);

		niveauEns.setTypeEnsId(savedTypeEns.getId());
		niveauEnsService.save(niveauEns);
		savedNiveauEns = niveauEnsService.findAll().get(0);

		classe.setNiveauId(savedNiveauEns.getId());
		service.save(classe);
		savedClasse = service.findAll().get(0);
	}

	@After
	public void tearDown() throws Exception {
		typeEnsService.delete(savedTypeEns.getId());
		niveauEnsService.delete(savedNiveauEns.getId());
		service.delete(savedClasse.getId());
	}

	@Test
	public final void testFindAll() {
		assertNotNull("La methode findAll a echoué", service.findAll());
	}

	@Test
	public final void testFindOne() {
		assertNotNull("La methode findOne a echoué", service.findOne(savedClasse.getId()));
	}
}
