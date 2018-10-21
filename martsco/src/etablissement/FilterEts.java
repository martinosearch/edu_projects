package etablissement;

import java.util.ArrayList;

import eleve.EleveClasse;
import graphicsModel.MartList;

public class FilterEts {

	private String etablissement;

	public FilterEts(String ets) {
		etablissement = ets;
	}

	public MartList<EleveClasse> getListe(MartList<EleveClasse> liste) {
		MartList<EleveClasse> listeTemp = new MartList<EleveClasse>();

		if (etablissement != null) {
			if (!etablissement.equals("Tous les Ets.")) {
				for (EleveClasse elv : liste) {
					if (elv.getEts().equals(etablissement)) {
						listeTemp.add(elv);
					}
				}
			} else {
				listeTemp = liste;
				System.out
						.println("C'est ici et ets=================================================>> "
								+ etablissement + "Taille: " + liste.size());
			}
		} else {
			listeTemp = liste;
		}

		return listeTemp;
	}

}
