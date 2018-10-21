package annee;

import tableComponent.MartTabModel;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class AnneeModel extends AbstractModel {

	private NouvelleAnnee obsPrincipale;

	public void valider(int tpe) {
		DAO andao = DAOFactory.getDAO(DAO.ANNEE);
		this.type = tpe;
		if (type == 1) {
			Annee an = (Annee) data;
			andao.load();
			// System.out.println("je suis ici" + an.getIntitule());

			andao.update_create(an);
			notifyObserver();
		}
	}

	@Override
	public void supprimer(int mode2) {
		MartTabModel mod;
		this.mode = mode2;

		for (Observer obs : listObserver) {
			if (obs instanceof NouvelleAnnee) {
				obsPrincipale = (NouvelleAnnee) obs;
			}
		}

		if (this.mode == DELETE_COMPLETELY) {
			// on cherche les autre référence
			DAO dao = DAOFactory.getDAO(DAO.ANNEE);
			dao.load();
			Annee an = (Annee) data;
			dao.deleteObj(an);
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
