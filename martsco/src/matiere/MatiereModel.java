package matiere;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractModel;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import connection.DAO;
import connection.DAOFactory;

public class MatiereModel extends AbstractModel {

	private MartTable table;

	public void valider(int tpe) {
		this.type = tpe;
		if (type == CREATE_OBJECT) {
			Matiere mat = (Matiere) data;
			matdao = DAOFactory.getDAO(DAO.MATIERE);
			matdao.load();

			matdao.setTypeUpdate(typeUpdate);
			matdao.update_create(mat);
			notifyObserver();
		}
	}

	public void supprimer(int mode2) {
		DAO matdao = DAOFactory.getDAO(DAO.MATIERE);
		matdao.load();
		Matiere mat = (Matiere) data;

		matdao.deleteObj(mat);

		notifyObserver();
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
