package ecolage;

import security.CurrentUser;
import tableComponent.MartTabModel;
import function.MatriGEN;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;
import abstractObject.AbstractModel;
import annee.Annee;
import annee.NouvelleAnnee;
import connection.DAO;
import connection.DAOFactory;

public class EcolageModel extends AbstractModel {

	private NouvelleAnnee obsPrincipale;
	private MatriGEN numGenerator;

	public void valider(int tpe) {
		versecolagedao = DAOFactory.getDAO(DAO.VERSEMENT_ECOLAGE);
		operationdao = DAOFactory.getDAO(DAO.OPERATION);
		numGenerator = new MatriGEN(MatriGEN.COMPTABILITE);
		numGenerator.setAnnee(annee);

		this.type = tpe;
		if (type == CREATE_OBJECT) {
			// enrégistrement de l'ecolage
			EleveEcolage eleve = (EleveEcolage) data;
			eleve.incrementSerial();
			String numOp = numGenerator.getMatri();
			eleve.setNumOperation(numOp);

			// utilisateur pour le compte du charge de l'opération
			CurrentUser user = CurrentUser.getInstance();
			eleve.getCurrentOperation().setCodeInfoCharge(user.getCodeInfo());
			versecolagedao.load("", "", 0, annee);

			versecolagedao.update_create(eleve);

			// enrégistrement de l'operation
			Operation oper = eleve.getCurrentOperation();
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

			versecolagedao.deleteObj(an);

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
