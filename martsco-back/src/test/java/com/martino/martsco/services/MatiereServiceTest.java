package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.models.Matiere;
import com.martino.martsco.models.TypeMatiere;

public class MatiereServiceTest extends MyIntegrationTest {
	@Autowired
	private Matiere matiere;
	@Autowired
	MatiereService matiereService;
	@Autowired
	private TypeMatiere typeMatiere;
	@Autowired
	TypeMatiereService typeMatiereService;
	private TypeMatiere savedTypeMatiere;
	private Matiere savedMatiere;

	@Before
	public void setUp() throws Exception {
		typeMatiereService.save(typeMatiere);
		savedTypeMatiere = typeMatiereService.findAll().get(0);

		matiere.setTypeMatiereId(savedTypeMatiere.getId());
		matiereService.save(matiere);
		savedMatiere = matiereService.findAll().get(0);
	}

	@After
	public void tearDown() throws Exception {
		typeMatiereService.delete(savedTypeMatiere.getId());
		matiereService.delete(savedMatiere.getId());
	}

	@Test
	public final void testFindAll() {
		assertNotNull("La methode findAll a echoué", matiereService.findAll());
	}

	@Test
	public final void testFindOne() {
		assertNotNull("La methode findOne a echoué", matiereService.findOne(savedMatiere.getId()));
	}
}
