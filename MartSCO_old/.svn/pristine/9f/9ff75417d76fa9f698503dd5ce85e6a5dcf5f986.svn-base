package matiere;

import examen.Examen;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;
import tableComponent.MartTable;
import abstractObject.AbstractModel;
import configurationExamen.ConfMatiereProgExam;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;

public class MatiereProgExamModel extends AbstractModel {
	public static int ETABLISSEMENT = 0, MATIERE = 1;
	private DAO elvdao, matdao, matpdao, andao, chargedao, clsdao, respdao,
			examdao, petsdao;
	private ConfMatiereProgExam obsPrincipal;
	private ConfigExamen conf;
	private MartList<MatiereProg> matieresProg;

	public void executeList(int tpe) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valider(int tpe) {
		this.type = tpe;
		if (type == 1) {
			examdao = DAOFactory.getDAO(DAO.EXAMEN);
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
			examdao.load();
			matpdao.loadExam(examen);
			matieresProg = matpdao.getListObt();

			Examen exam = (Examen) examdao.findObj(examen);
			conf = new ConfigExamen(examen);

			for (Observer obs : listObserver) {
				if (obs instanceof ConfMatiereProgExam) {
					obsPrincipal = (ConfMatiereProgExam) obs;
					System.out.println("Retrouvé");
				}
			}

			MartTable tabMatiere = obsPrincipal.getMat();
			int max = tabMatiere.getRowCount();

			String niveau = exam.getNiveau();

			// on crée la liste de vérification des éléments retirer
			MartList<MatiereProg> listeSearch = new MartList<MatiereProg>();

			for (int j = 0; j < max; j++) {
				String matiere = (String) tabMatiere.getValueAt(j, 1);
				double coef = conf.relConfig.getCoef(matiere, niveau);

				MatiereProg temp = new MatiereProg(matiere, coef, "");
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

			notifyObserver();
		}
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

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}
}
