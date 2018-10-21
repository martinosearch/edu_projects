package classe;

import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class NiveauModel extends AbstractModel {

	private DAO nivdao;

	public void valider(int tpe) {
		this.type = tpe;
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		nivdao.load();

		if (type == UPDATE_CHOISE) {
			Niveau cls = (Niveau) data;
			nivdao.update_create(cls);
			notifyObserver();
		}
	}

	@Override
	public void supprimer(int type) {
		if (type == DELETE_COMPLETELY) {
			// methode pour supprimer une ligne
			if (this.mode == 0) {
				nivdao = DAOFactory.getDAO(DAO.NIVEAU);
				nivdao.load();
				Niveau cls = (Niveau) data;
				nivdao.deleteObj(cls);
				notifyObserver();
			}
		}

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}
}
