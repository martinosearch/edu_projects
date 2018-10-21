package note;

import java.util.ArrayList;

import matiere.MatiereProg;
import eleve.EleveClasse;

public class Archiver {

	public void treat(NoteViewer noteViewer, EleveClasse elv, Moyenne moyenne) {
		// ici c'est l'archivage des moyennes trimestrielles
		RapMoyenne rpmoy1 = new RapMoyenne();
		RapMoyenne rpmoy2 = new RapMoyenne();

		double moy1 = 0, moy2 = 0, moy3 = 0;
		String rg1 = "", rg2 = "", rg3 = "";
		String matricule = moyenne.getIntitule();

		noteViewer.rapmoydao.load(new String(), noteViewer.classe,
				noteViewer.trimestre, noteViewer.annee);
		rpmoy2 = (RapMoyenne) noteViewer.rapmoydao.findObj(matricule);

		switch (noteViewer.trimestre) {
		case 1:
			moy1 = moyenne.getMoyGen();
			moy2 = rpmoy2.getMoyenne2();
			moy3 = rpmoy2.getMoyenne3();
			rg1 = noteViewer.getRang(new MatiereProg("std"), elv);
			rg2 = rpmoy2.getRang2();
			rg3 = rpmoy2.getRang2();
			break;

		case 2:
			moy1 = rpmoy2.getMoyenne1();
			moy2 = moyenne.getMoyGen();
			moy3 = rpmoy2.getMoyenne3();
			rg1 = rpmoy2.getRang1();
			rg2 = noteViewer.getRang(new MatiereProg("std"), elv);
			rg3 = rpmoy2.getRang3();
			break;

		case 3:
			moy1 = rpmoy2.getMoyenne1();
			moy2 = rpmoy2.getMoyenne2();
			moy3 = moyenne.getMoyGen();
			rg1 = rpmoy2.getRang1();
			rg2 = rpmoy2.getRang2();
			rg3 = noteViewer.getRang(new MatiereProg("std"), elv);

			break;
		}

		// calcul de la moyenne annuelle###########################
		double totann = 0, moyann = 0;
		String rangann;

		ArrayList<Double> data = new ArrayList<Double>();
		if (moy1 != 0) {
			data.add(moy1);
		}

		if (moy2 != 0) {
			data.add(moy2);
		}

		if (moy3 != 0) {
			data.add(moy3);
		}

		// total des moyenne
		for (double d : data) {
			totann = totann + d;
		}
		// le dividende
		int div = data.size();

		if (div != 0) {
			moyann = totann / data.size();
		}

		rpmoy1 = new RapMoyenne(matricule, moy1, rg1, moy2, rg2, moy3, rg3,
				moyann, null);

		noteViewer.rapmoydao.setTableArch(noteViewer.classe, noteViewer.annee);
		noteViewer.rapmoydao.update_create(rpmoy1);
	}

}
