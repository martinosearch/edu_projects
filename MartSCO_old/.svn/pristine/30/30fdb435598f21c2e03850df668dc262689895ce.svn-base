package note;

import abstractObject.AbstractModel;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import connection.DAO;
import connection.DAOFactory;

public class NoteExamModel extends AbstractModel {
	protected DAO notedao;

	public void valider(int tpe) {
		this.type = tpe;
		if (type == 1) {
			Note note = (Note) data;

			notedao = DAOFactory.getDAO(DAO.NOTE_EXAM);
			notedao.loadExam(matiere, examen);

			// System.out.println("=============================>>" + matiere
			// + examen);
			notedao.updateObj(note);
		}
		notifyObserver();
	}

	// les accesseurs
	public void setMatiere(String mat) {
		this.matiere = mat;
	}

	public String getMatiere() {
		return matiere;
	}

	public void setAnnee(String an) {
		this.annee = an;
	}

	public String getAnnee() {
		return annee;
	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}
}
