package note;

import java.text.DecimalFormat;

import interfacePerso.MartRangeable;
import matiere.MatiereProg;
import eleve.EleveClasse;
import function.MartComputer;
import graphicsModel.MartList;

public class InfoEleveFinder {

	private NoteViewer noteViewer;
	private DecimalFormat formatter = new DecimalFormat("00.00");

	public InfoEleveFinder(NoteViewer nv) {
		noteViewer = nv;
	}

	public InfoNote get(MatiereProg matiere, EleveClasse elv) {
		MartList<MartRangeable> temp = noteViewer.SeriesNotes.find(matiere
				.getIntitule());

		Note note = new Note();
		try {
			note = (Note) temp.find(elv.getCodeInfo());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// nous devons corriger l'affichage des notes
		String moyClsStr = "";
		String moyStr = "";
		String ptObtStr = "";

		// calcule des autres info
		MartComputer mc = new MartComputer(noteViewer.model);
		mc.setNoteViewer(noteViewer);

		mc.setNote(note);

		double moyCls = mc.getMoyCls();
		if (moyCls != 0.001) {
			moyClsStr = formatter.format(moyCls);
		} else {
			moyClsStr = "";
		}

		double moy = mc.getMoyp();

		if (moy != 0.001) {
			moyStr = formatter.format(moy);
		} else {
			moyStr = "";
		}

		double ptObt = mc.getPtObtenus(matiere);

		if (ptObt != 0.001) {
			ptObtStr = formatter.format(ptObt);
		} else {
			ptObtStr = "";
		}

		long time5 = System.currentTimeMillis(); // traceur de

		String rang = noteViewer.getRang(matiere, elv);

		InfoNote infoElv = new InfoNote();

		boolean hasCompose = mc.getHasCompose();

		infoElv.setInfo(note.getNote1str(), note.getNote2str(),
				note.getNote3str(), note.getNote4str(), moyCls, moy, ptObt,
				rang, hasCompose);

		infoElv.setMoyStr(moyClsStr, moyStr, ptObtStr);

		infoElv.setCoefCons(mc.getCoefConsidered());

		// on définit le type de rapport pour les gestion des barèmes
		infoElv.setModel(noteViewer.model);

		// System.out.println("Note renvoyées: " + moyClsStr + " " + moyStr +
		// " "
		// + ptObtStr + hasCompose + infoElv.hasComposeCompo());

		return infoElv;
	}

}
