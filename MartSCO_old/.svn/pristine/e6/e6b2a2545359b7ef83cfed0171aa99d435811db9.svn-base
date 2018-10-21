package function;

import eleve.Eleve;
import eleve.EleveClasse;
import graphicsModel.MartList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import configurationAppli.Setting;

public class Fichier extends File {

	private static String cheminFichier;
	private File file;

	public Fichier(String chem) {
		super(chem);
		cheminFichier = chem;
	}

	public static MartList<Setting> getListeParams() {
		MartList<Setting> params = new MartList<Setting>();

		File fichierParam = new File(cheminFichier);
		BufferedReader dataReader;

		try {
			dataReader = new BufferedReader(new FileReader(fichierParam));
			dataReader.readLine();
			dataReader.readLine();

			String line;
			do {
				line = dataReader.readLine();
				if (line != null) {
					String tempInt = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String tempValue = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					params.add(new Setting(tempInt, tempValue));
				}
			} while (line != null);

			dataReader.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return params;
	}

	public static String findParam(String str) {
		ArrayList<Setting> listeParams = getListeParams();
		String value = "";
		for (Setting p : listeParams) {
			if (p.getIntitule().equals(str)) {
				value = (String) p.getAttribut();
			}
		}
		return value;
	}

	public void writeParam(Setting set) {
		MartList<Setting> params = getListeParams();

		// ajout de la nouvelle paramètre
		Setting newParam = new Setting(set.getIntitule(), set.getAttribut());

		boolean exist = false;
		for (Setting p : params) {
			if (p.getIntitule().equals(newParam.getIntitule())) {
				exist = true;
				p.setIntitule(newParam.getIntitule());
				p.setAttribut(newParam.getAttribut());
			}
		}

		if (exist == false) {
			params.add(newParam);
		}

		try {
			FileWriter dataWriter = new FileWriter(new File(cheminFichier));

			dataWriter.write(Constance.getDataTitle());

			for (Setting p : params) {
				dataWriter.write("\r\n" + p.getIntitule() + ";"
						+ p.getAttribut() + ";");
			}

			dataWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out
				.println("========================================*********************************");
	}

	public MartList<Eleve> getListeEleves() {
		MartList<Eleve> eleves = new MartList<Eleve>();

		File fichierEleve = new File(cheminFichier);
		BufferedReader dataReader;

		try {
			dataReader = new BufferedReader(new FileReader(fichierEleve));
			dataReader.readLine();
			dataReader.readLine();

			String line;
			do {
				line = dataReader.readLine();
				if (line != null) {
					String num = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String nom = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String prenom = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String sexe = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					Eleve eleve = new Eleve();
					eleve.setNom(nom);
					eleve.setPrenom(prenom);
					eleve.setSexe(sexe);
					eleve.setPrimaryKey(nom + prenom);

					eleves.add(eleve);
				}
			} while (line != null);

			dataReader.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return eleves;
	}

	public static void main(String[] args) {
		Fichier f = new Fichier(Constance.getTempDir() + "params"
				+ Constance.getParamExtension());
		f.writeParam(new Setting("je marche", "ok"));
	}

	public MartList<EleveClasse> getListeElevesClasse() {
		MartList<EleveClasse> eleves = new MartList<EleveClasse>();

		File fichierEleve = new File(cheminFichier);
		BufferedReader dataReader;

		try {
			dataReader = new BufferedReader(new FileReader(fichierEleve));
			dataReader.readLine();
			dataReader.readLine();

			String line;
			do {
				line = dataReader.readLine();
				if (line != null) {
					String num = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String nom = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String prenom = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String sexe = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					String classe = line.substring(0, line.indexOf(";"));
					line = line.substring(line.indexOf(";") + 1);

					EleveClasse eleve = new EleveClasse();
					eleve.setNom(nom);
					eleve.setPrenom(prenom);
					eleve.setClasse(classe);

					eleves.add(eleve);
				}
			} while (line != null);

			dataReader.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return eleves;
	}

	public void create() {
		try {
			FileWriter dataWriter = new FileWriter(new File(cheminFichier));

			dataWriter.write(Constance.getDataTitle());

			dataWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void createParam() {
		try {
			FileWriter dataWriter = new FileWriter(new File(cheminFichier));

			dataWriter.write(Constance.getDataTitle());

			dataWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
