package agent;

import tableComponent.MartTabModel;
import abstractObject.AbstractModel;
import function.MatriGEN;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import connection.DAO;
import connection.DAOFactory;

public class AgentModel extends AbstractModel {

	public void valider(int tpe) {
		this.type = tpe;
		MatriGEN codeGen = new MatriGEN(MatriGEN.AGENT);

		if (type == 1) {
			Agent ens = (Agent) data;

			ensdao = DAOFactory.getDAO(DAO.AGENT);
			ensdao.load();

			// attribution de numéro matricule en cas de défaut
			String matricule = ens.getCodeInfo();
			System.out.println("###################**************" + matricule);

			if (matricule.equals("")) {
				matricule = codeGen.getMatri();

			} else {
				Agent oldEns = new Agent();
				try {
					oldEns = (Agent) ensdao.findObj(ens.getCodeInfo());
				} catch (Exception e) {

				}

				ens.setPrimaryKey(oldEns.getPrimaryKey());
			}

			ens.setCodeInfo(matricule);
			ensdao.update_create(ens);

			notifyObserver();
		}
	}

	@Override
	public void supprimer(int mode2) {
		MartTabModel mod;
		this.mode = mode2;
		// methode pour supprimer une ligne
		if (this.mode == DELETE_COMPLETELY) {
			DAO dao = DAOFactory.getDAO(DAO.AGENT);
			dao.load();

			Agent ens = (Agent) data;
			dao.deleteObj(ens);

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
