package note;

import interfacePerso.MartRangeable;
import matiere.MatiereProg;
import eleve.Eleve;
import eleve.EleveClasse;
import function.MartArranger;
import graphicsModel.MartList;

public class RangFinder {

	private NoteViewer noteViewer;

	public RangFinder(NoteViewer nv) {
		noteViewer = nv;
	}

	public String find(MatiereProg mat, EleveClasse elv) {
		int rgInt = 0;

		String str = "";

		try {
			MartList<MartRangeable> temp = noteViewer.Classeurs.find(mat
					.getIntitule());
			// System.out.println("La liste de notes de la matière (nview): taille= "+
			// temp.size());
			// for (MartRangeable m : Classeurs) {
			// System.out.println("#####################################"
			// + m.getId());
			// }

			// System.out.println("Elève: " + elv.getCodeInfo());
			MartRangeable rgv = temp.find(elv.getCodeInfo());
			rgInt = rgv.getRang();

			str = MartArranger.getOrder(rgInt, elv.getSexe());
		} catch (Exception e) {// il peut arriver qu'on ne trouve rien
			e.printStackTrace();
		}

		return str;
	}

	public Eleve find(String mat, int rg) {
		Eleve eleve = new Eleve();
		try {
			MartList<MartRangeable> temp = noteViewer.Classeurs.find(mat);
			MartRangeable rgValue = temp.find(rg);
			String matricule = rgValue.getId();
			eleve = noteViewer.eleves.find(matricule);

			if (mat.equals("moyAn")) {
				RapMoyenne rMoy = (RapMoyenne) noteViewer.rapmoydao
						.findObj(matricule);

				eleve.setMoyenne1(rMoy.getMoyenne1());
				eleve.setMoyenne2(rMoy.getMoyenne2());
				eleve.setMoyenne3(rMoy.getMoyenne3());
			}

			eleve.setValue(rgValue.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eleve;
	}

}
