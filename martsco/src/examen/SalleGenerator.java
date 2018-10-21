package examen;

import eleve.EleveClasse;
import graphicsModel.MartList;
import configurationExamen.ConfigExamen;

public class SalleGenerator {
	private String examen;
	private ConfigExamen conf;
	private int nbreSalle = 0;

	public SalleGenerator(String exam) {
		examen = exam;
		conf = new ConfigExamen(examen);
		try {
			nbreSalle = conf.relConfig.getNbreSalle();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MartList<EleveClasse> getList(MartList<EleveClasse> list) {
		int salle = 1;
		int eff = 0;
		try {
			eff = conf.relConfig.getEffSalle(salle);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int count = 0;

		// System.out.println("Effectif======================================= "
		// + eff);

		for (EleveClasse elv : list) {
			if (count < eff) {
			} else {
				salle++;
				try {
					eff = conf.relConfig.getEffSalle(salle);
				} catch (Exception e) {
					e.printStackTrace();
				}
				count = 0;
			}

			count++;
			elv.setSalle(salle);
			elv.setNumOrdre(count);
			elv.setEffSalle(eff);

			// System.out.println("Eleve======================================= "
			// + elv.decrisToi() + " " + elv.getSalle());
		}

		return list;
	}
}
