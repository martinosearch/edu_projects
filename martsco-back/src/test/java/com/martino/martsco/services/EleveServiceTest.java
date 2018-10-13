package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.models.Classe;
import com.martino.martsco.models.Eleve;
import com.martino.martsco.models.NiveauEns;
import com.martino.martsco.models.TypeEns;

public class EleveServiceTest extends MyIntegrationTest {
	@Autowired
	private NiveauEns niveauEns;
	@Autowired
	NiveauEnsService niveauEnsService;
	@Autowired
	EleveService eleveService;
	@Autowired
	ClasseService classeService;
	@Autowired
	Classe classe;
	@Autowired
	private TypeEns typeEns;
	@Autowired
	TypeEnsService typeEnsService;
	@Autowired
	Eleve eleve;
	private Classe savedClasse;
	private NiveauEns savedNiveauEns;
	private TypeEns savedTypeEns;
	private Eleve savedEleve;

	@Before
	public void setUp() throws Exception {
		typeEnsService.save(typeEns);
		savedTypeEns = typeEnsService.findAll().get(0);

		niveauEns.setTypeEnsId(savedTypeEns.getId());
		niveauEnsService.save(niveauEns);
		savedNiveauEns = niveauEnsService.findAll().get(0);

		classe.setNiveauId(savedNiveauEns.getId());
		classeService.save(classe);
		savedClasse = classeService.findAll().get(0);

		eleve.setClasseActuelleId(savedClasse.getId());
		eleveService.save(eleve);
		savedEleve = eleveService.findAll().get(0);
	}

	@After
	public void tearDown() throws Exception {
		typeEnsService.delete(savedTypeEns.getId());
		niveauEnsService.delete(savedNiveauEns.getId());
		classeService.delete(savedClasse.getId());
		eleveService.delete(savedEleve.getId());
	}

	@Test
	public final void testFindAll() {
		assertNotNull("La methode findAll a echoué", eleveService.findAll());
	}

	@Test
	public final void testFindOne() {
		assertNotNull("La methode findOne a echoué", eleveService.findOne(savedEleve.getId()));
	}
}
