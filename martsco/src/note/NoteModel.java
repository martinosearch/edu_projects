package note;

import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import javax.swing.JTable;

import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class NoteModel extends AbstractModel {

	public void valider(int tpe) {
		this.type = tpe;
		if (type == 1) {
			Note note = (Note) data;
			notedao = DAOFactory.getDAO(DAO.NOTE);

			System.out.println("=============>>" + matiere + classe + trimestre
					+ annee + note.getNote1());

			notedao.load(matiere, classe, trimestre, annee);

			notedao.updateObj(note);
		}
		notifyObserver();
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
