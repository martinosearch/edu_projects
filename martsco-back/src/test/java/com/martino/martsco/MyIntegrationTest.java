package com.martino.martsco;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.martino.martsco.controllers.AnneeController;
import com.martino.martsco.models.Annee;
import com.martino.martsco.models.Classe;
import com.martino.martsco.models.Eleve;
import com.martino.martsco.models.Matiere;
import com.martino.martsco.models.NiveauEns;
import com.martino.martsco.models.TypeEns;
import com.martino.martsco.models.TypeMatiere;
import com.martino.martsco.reports.ReportService;
import com.martino.martsco.services.AnneeService;
import com.martino.martsco.services.ClasseService;
import com.martino.martsco.services.EleveService;
import com.martino.martsco.services.MatiereService;
import com.martino.martsco.services.NiveauEnsService;
import com.martino.martsco.services.TypeEnsService;
import com.martino.martsco.services.TypeMatiereService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class MyIntegrationTest {
	@TestConfiguration
	static class MyIntegrationTestContextConfiguration {
		@Bean
		public Annee annee() {
			Annee annee = new Annee();
			annee.setIntitule("2017-2018");
			return annee;
		}

		@Bean
		public Classe classe() {
			Classe classe = new Classe();
			return classe;
		}

		@Bean
		public Eleve eleve() {
			Eleve eleve = new Eleve();
			eleve.setNom("ABALO");
			eleve.setPrenom("KOUDJOUKA");
			eleve.setSexeId(0);
			return eleve;
		}

		@Bean
		public TypeEns typeEns() {
			TypeEns typeEns = new TypeEns();
			typeEns.setIntitule("Collège");
			return typeEns;
		}

		@Bean
		public NiveauEns niveauEns() {
			NiveauEns niveauEns = new NiveauEns();
			niveauEns.setIntitule("6ème");
			return niveauEns;
		}

		@Bean
		public TypeMatiere typeMatiere() {
			TypeMatiere typeMatiere = new TypeMatiere();
			typeMatiere.setIntitule("Matière Littéraire");
			return typeMatiere;
		}

		@Bean
		public Matiere matiere() {
			Matiere matiere = new Matiere();
			matiere.setIntitule("Anglais");
			return matiere;
		}

		@Bean
		public AnneeService anneeService() {
			return new AnneeService();
		}

		@Bean
		public ClasseService classeService() {
			return new ClasseService();
		}

		@Bean
		public EleveService eleveService() {
			return new EleveService();
		}

		@Bean
		public MatiereService matiereService() {
			return new MatiereService();
		}

		@Bean
		public NiveauEnsService niveauEnsService() {
			return new NiveauEnsService();
		}

		@Bean
		public TypeEnsService typeEnsService() {
			return new TypeEnsService();
		}

		@Bean
		public TypeMatiereService typeMatiereService() {
			return new TypeMatiereService();
		}

		@Bean
		public ReportService reportService() {
			return new ReportService();
		}

		@Bean
		public AnneeController anneeController() {
			AnneeController controller = new AnneeController();
			return controller;
		}
	}
}
