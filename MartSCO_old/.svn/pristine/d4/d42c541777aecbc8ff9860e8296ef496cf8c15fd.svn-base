package examen;

import eleve.EleveClasse;
import graphicsModel.MartList;

public class NumTableGenerator {
	MartList<EleveClasse> liste;

	public NumTableGenerator() {
	}

	public MartList<EleveClasse> getListe() {
		MartList<EleveClasse> tempListe = new MartList<EleveClasse>();
		int start = 0;
		int ind = 4000;
		int count = start;
		try {
			for (EleveClasse el : liste) {
				// pour le numéro de table
				if (count > 49) {
					count = start;
					ind = ind + 1000;
				}

				count++;
				el.setNumTable(String.valueOf(ind + count));

				tempListe.add(el);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		liste = tempListe;

		return liste;
	}

	public static void main(String[] args) {

	}

	public void setListe(MartList<EleveClasse> liste) {
		this.liste = liste;
	}

}
