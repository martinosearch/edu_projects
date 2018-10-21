package salaire;

import interfacePerso.MartRangeable;
import interfacePerso.Observer;
import security.CurrentUser;
import tableComponent.MartTabModel;
import abstractObject.AbstractModel;
import annee.Annee;
import annee.NouvelleAnnee;
import connection.DAO;
import connection.DAOFactory;
import ecolage.Operation;
import function.MatriGEN;
import graphicsModel.MartList;

public class PayerSalaireModel extends AbstractModel {

	private NouvelleAnnee obsPrincipale;
	private MatriGEN numGenerator;

	public void valider(int tpe) {
		payersaldao = DAOFactory.getDAO(DAO.PAYEMENT_SALAIRE);
		operationdao = DAOFactory.getDAO(DAO.OPERATION);

		numGenerator = new MatriGEN(MatriGEN.COMPTABILITE);
		numGenerator.setAnnee(annee);

		this.type = tpe;
		if (type == CREATE_OBJECT) {
			// enrégistrement du salaire
			AgentSalaire salAgent = (AgentSalaire) data;
			salAgent.incrementSerial();

			String numOp = numGenerator.getMatri();
			salAgent.setNumOperation(numOp);

			// utilisateur pour le compte du charge de l'opération
			CurrentUser user = CurrentUser.getInstance();
			salAgent.getCurrentOperation()
					.setCodeInfoCharge(user.getCodeInfo());
			payersaldao.load("", "", 0, annee);

			payersaldao.update_create(salAgent);

			// enrégistrement de l'operation
			Operation oper = salAgent.getCurrentOperation();
			operationdao.load("", "", 0, annee);
			operationdao.update_create(oper);

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
			Annee an = (Annee) data;
			payersaldao.deleteObj(an);
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
