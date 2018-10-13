package com.martino.martsco.models;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;

public class ClasseTest extends MyIntegrationTest {
	@Autowired
	Classe classe;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		assertNotNull("L'objet classe est nul", classe);
		classe.setIntitule("6ème");
	}
}
