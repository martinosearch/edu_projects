package note;

import function.MartArranger;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import abstractObject.Rapport;

public class InfoMatiereLoader {

	public void treat(NoteViewer noteViewer, String mat) {
		// on charge selon le type de traitement en cours (exam/bulletin)
		if (noteViewer.type == noteViewer.RELEVE) {
			if (noteViewer.typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
				noteViewer.notedao.load(mat, noteViewer.classe,
						noteViewer.trimestre, noteViewer.annee);
			} else {
				noteViewer.notedao.loadExam(mat, noteViewer.examen);
			}
		} else {
			noteViewer.notedao.load(mat, noteViewer.classe,
					noteViewer.trimestre, noteViewer.annee);// pour bulletin
		}

		MartList<MartRangeable> temp = noteViewer.notedao.getListObt();// les
																		// notes
																		// sont
		// des rangeables
		temp.setId(mat);
		noteViewer.SeriesNotes.add(temp);

		// menage pour le classement
		MartList<MartRangeable> listOrdonne = MartArranger.arrange(temp);
		listOrdonne.setId(mat);

		noteViewer.Classeurs.add(listOrdonne);
	}

}
