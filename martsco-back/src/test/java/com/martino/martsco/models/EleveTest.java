package com.martino.martsco.models;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.martino.martsco.MyIntegrationTest;

public class EleveTest extends MyIntegrationTest {
	@Autowired
	private Eleve eleve;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testEleve() {
		assertNotNull("L'objet eleve est nul", eleve);
	}

}
