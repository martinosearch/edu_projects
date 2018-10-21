package Import;

import eleve.Eleve;
import examen.ChooserExamEts;
import function.FiltreFichier;
import graphicsModel.MartList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class ImportListeEleve {

	private MartList<Eleve> listeElv;

	public ImportListeEleve() {
	}

	public MartList<Eleve> getListeExcelClasse(String classe) {
		MartList<Eleve> liste = new MartList<Eleve>();
		JFileChooser fChooser = new JFileChooser();
		fChooser.setCurrentDirectory(new File("d:\\"));

		fChooser.setFileFilter(new FiltreFichier("fichier d'importation_",
				"xls"));

		int selection = fChooser.showOpenDialog(null);

		if (selection == JFileChooser.APPROVE_OPTION) {
			String path = fChooser.getSelectedFile().getAbsolutePath();

			Tableau tab = new Tableau(path, classe);

			Object[][] corps = tab.getBody();

			int i = 0;
			for (Object obj : corps) {
				try {
					Eleve eleve = new Eleve();
					eleve.setNom(((String) corps[i][1]).trim());
					eleve.setPrenom(((String) corps[i][2]).trim());
					eleve.setSexe(((String) corps[i][3]).trim());
					eleve.setClasse(classe);

					liste.add(eleve);

					System.out.println(i + 1 + "- " + eleve.decrisToi()
							+ "sexe: " + eleve.getSexe());

					Thread.sleep(100);

				} catch (Exception e) {
					e.printStackTrace();
				}

				i++;
			}
		}
		return liste;
	}

	public MartList<Eleve> getListeExcelExamen(String ets) {
		listeElv = new MartList<Eleve>();

		JFileChooser fChooser = new JFileChooser();
		fChooser.setCurrentDirectory(new File("d:\\"));

		fChooser.setFileFilter(new FiltreFichier("fichier d'importation_",
				"xls"));
		int selection = fChooser.showOpenDialog(null);

		if (selection == JFileChooser.APPROVE_OPTION) {
			String path = fChooser.getSelectedFile().getAbsolutePath();

			Tableau tab = new Tableau(path, ets);

			Object[][] corps = tab.getBody();

			int i = 0;
			for (Object obj : corps) {
				try {
					Eleve eleve = new Eleve();
					eleve.setNom(((String) corps[i][1]).trim());
					eleve.setPrenom(((String) corps[i][2]).trim());
					eleve.setSexe(((String) corps[i][3]).trim());
					eleve.setEts(ets);

					listeElv.add(eleve);

					System.out.println(i + 1 + "- " + eleve.decrisToi()
							+ "sexe: " + eleve.getSexe());

					Thread.sleep(100);

				} catch (Exception e) {
					e.printStackTrace();
				}

				i++;
			}
		}

		return listeElv;
	}

	public static void main(String[] args) {
		new ImportListeEleve().getListeExcelClasse("5ème F");
	}
}
