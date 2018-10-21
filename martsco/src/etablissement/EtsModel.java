package etablissement;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractModel;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import connection.DAO;
import connection.DAOFactory;

public class EtsModel extends AbstractModel {

	private MartTable table;
	private DAO etsdao;

	public void valider(int tpe) {
		this.type = tpe;
		if (type == 1) {
			Etablissement mat = (Etablissement) data;
			etsdao = DAOFactory.getDAO(DAO.ETABLISSEMENT);
			etsdao.load();
			etsdao.update_create(mat);

			notifyObserver();
		}
	}

	public void supprimer(int mode2) {
		// methode pour supprimer une ligne
		if (this.mode == DELETE_COMPLETELY) {
			etsdao = DAOFactory.getDAO(DAO.ETABLISSEMENT);
			etsdao.load();
			Etablissement mat = (Etablissement) data;
			etsdao.deleteObj(mat);

			notifyObserver();
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
