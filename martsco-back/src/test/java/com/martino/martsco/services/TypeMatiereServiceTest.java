package com.martino.martsco.services;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;
import com.martino.martsco.models.TypeMatiere;

public class TypeMatiereServiceTest extends MyIntegrationTest {
	@Autowired
	TypeMatiereService service;
	@Autowired
	TypeMatiere typeMatiere;
	private TypeMatiere savedTypeMatiere;

	@Before
	public void setUp() throws Exception {
		service.save(typeMatiere);
		savedTypeMatiere = service.findAll().get(0);
	}

	@After
	public void tearDown() throws Exception {
		service.delete(savedTypeMatiere.getId());
	}

	@Test
	public final void testFindAll() {
		assertNotNull("La méthode findAll a échouée", service.findAll());
	}

	@Test
	public final void testFindOne() {
		long id = service.findAll().get(0).getId();
		assertNotNull("La méthode findOne a échouée", service.findOne(savedTypeMatiere.getId()));
	}
}
