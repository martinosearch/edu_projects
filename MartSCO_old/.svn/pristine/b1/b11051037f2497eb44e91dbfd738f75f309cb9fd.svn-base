package matiere;

import tableComponent.MartTabModel;
import tableComponent.MartTable;
import classe.Classe;
import abstractObject.AbstractModel;
import annee.Decoupage;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;
import configurationClasse.ConfMatiereProg;
import configurationClasse.OptionSettingClasse;
import configurationEcole.ConfigEcole;
import connection.DAO;
import connection.DAOFactory;

public class MatiereProgModel extends AbstractModel {
	private ConfMatiereProg obsPrincipal;
	private ConfigEcole conf;
	private MartList matieres;
	private MartList enseignant;
	private MartList<MatiereProg> matieresProg;
	private MartTabModel mod;

	public void supprimer(int mode) {
		// methode pour supprimer une ligne
		if (this.mode == DELETE_COMPLETELY) {

			for (Observer obs : listObserver) {
				if (obs instanceof OptionSettingClasse) {
					obsPrincipal = (ConfMatiereProg) obs;
				}
			}

			tableChoix = obsPrincipal.getMat();
			mod = (MartTabModel) tableChoix.getModel();

			int x = mod.getSelectedRow();
			int y = mod.getSelectedColumn();
			int taille = mod.getRowCount();

			// ligne à supprimer
			String intitule = (String) tableChoix.getValueAt(x, 1);

			// on cherche les autre référence
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
			matpdao.load(new String(), classe, trimestre, annee);

			MatiereProg mat = (MatiereProg) matpdao.findObj(intitule);

			matpdao.deleteObj(mat);
		}
	}

	@Override
	public void valider(int tpe) {
		this.type = tpe;

		if (type == 1) {
			// on prepare les données
			for (Observer obs : listObserver) {
				if (obs instanceof ConfMatiereProg) {
					obsPrincipal = (ConfMatiereProg) obs;
				}
			}

			MartTable tabMat = obsPrincipal.getMat();

			// on cherche les autre référence
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
			matpdao.load(new String(), classe, trimestre, annee);
			matieresProg = matpdao.getListObt();

			System.out.println("Information" + classe + annee);

			// on insert les chargés de cours
			// on remplit la table perso
			int max = tabMat.getRowCount();
			conf = new ConfigEcole(trimestre, annee);

			clsdao = DAOFactory.getDAO(DAO.CLASSE);
			clsdao.load();
			Classe superClasse = (Classe) clsdao.findObj(this.classe);
			String niveau = superClasse.getNiveau();

			// on crée la liste de vérification des éléments retirer
			MartList<MatiereProg> listeSearch = new MartList<MatiereProg>();

			for (int j = 0; j < max; j++) {
				String matiere = (String) tabMat.getValueAt(j, 1);
				double coef = conf.bullConfig.getCoef(matiere, niveau);
				String charge = (String) tabMat.getValueAt(j, 3);

				MatiereProg temp = new MatiereProg(matiere, coef, charge);
				listeSearch.add(temp);
			}

			// on supprime les matières retirées
			for (MatiereProg mat : matieresProg) {
				MatiereProg result = null;
				try {
					result = listeSearch.find(mat.getIntitule());
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (result == null) {
					matpdao.deleteObj(mat);
				}
			}

			for (MatiereProg mat : listeSearch) {
				matpdao.update_create(mat);
			}

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
}
