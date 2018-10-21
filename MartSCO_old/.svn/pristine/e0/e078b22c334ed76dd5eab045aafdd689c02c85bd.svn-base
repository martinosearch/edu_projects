package note;

import java.text.DecimalFormat;

import function.Statistician.MatSta;
import graphicsModel.MartList;

public class StaMatiereClasseFinder {

	private NoteViewer noteViewer;
	private DecimalFormat formatter = new DecimalFormat("00.00");

	public StaMatiereClasseFinder(NoteViewer nv) {
		noteViewer = nv;
	}

	public StaMatiereClasse find(String matiere) {
		MartList<MatSta> result = noteViewer.staman.getResultMat();

		MatSta sta = result.find(matiere);

		// System.out.println("contient: " + result.size());

		StaMatiereClasse info = null;
		try {
			info = new StaMatiereClasse();
			info.setOntComp(sta.getPresent());
			info.setNMoy(sta.getSup10());
			info.setHMoy(sta.getHNote());
			info.setLMoy(sta.getLNote());
			info.setPercent(formatter.format(sta.getPerSup10()) + " %");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return info;
	}

}
