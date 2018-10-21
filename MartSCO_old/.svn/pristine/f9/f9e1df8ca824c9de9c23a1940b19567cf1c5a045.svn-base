package classe;

import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class ClasseModel extends AbstractModel {

	DAO clsdao, nivdao;

	public void valider(int tpe) {
		this.type = tpe;
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);

		if (type == UPDATE_CHOISE) {
			Classe cls = (Classe) data;
			clsdao.update_create(cls);
			notifyObserver();
		}
	}

	@Override
	public void supprimer(int type) {
		if (type == DELETE_COMPLETELY) {
			// methode pour supprimer une ligne
			if (this.mode == 0) {
				clsdao = DAOFactory.getDAO(DAO.CLASSE);
				clsdao.load();
				Classe cls = (Classe) data;
				clsdao.deleteObj(cls);
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
