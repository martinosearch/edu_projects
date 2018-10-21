package examen;

import interfacePerso.MartRangeable;
import tableComponent.MartTabModel;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import graphicsModel.MartList;

public class ExamModel extends AbstractModel {

	private DAO examdao;

	public void valider(int tpe) {
		this.type = tpe;
		if (type == 1) {
			Examen exam = (Examen) data;
			examen = exam.getIntitule();

			examdao = DAOFactory.getDAO(DAO.EXAMEN);
			examdao.load();
			examdao.update_create(exam);

			// ON CREE LES TABLES NECESSAIRES
			examdao.loadExam(exam.getIntitule());

			String tableExam = examdao.getTableExam();
			String tablePersoExam = examdao.getTablePersoExam();

			String query1 = "CREATE TABLE " + tableExam + "("
					+ "id_eleve serial," + " nom_eleve character varying,"
					+ " prenom_eleve character varying,"
					+ " codeinfo_eleve character varying PRIMARY KEY NOT NULL)";

			String query2 = "CREATE TABLE "
					+ tablePersoExam
					+ "(id_matiere_prog  serial,"
					+ " matiere_prog  character varying PRIMARY KEY NOT NULL, charge character varying)";

			// on crée aussi une table pour les personnalisation
			String examstr = Constance.getCor(examen);

			String query3 = "CREATE TABLE "
					+ "table_coef_"
					+ examstr
					+ "(id_coef serial,"
					+ "intitule_coef character varying PRIMARY KEY NOT NULL, valeur_coef character varying)";

			String query4 = "CREATE TABLE " + "table_set_" + examstr
					+ "(id_set serial,"
					+ "intitule_set character varying PRIMARY KEY NOT NULL,"
					+ "attribut_set character varying NOT NULL)";

			examdao.updateDB(query1);
			examdao.updateDB(query2);
			examdao.updateDB(query3);
			examdao.updateDB(query4);

			notifyObserver();
		}
	}

	public void supprimer(int mode2) {
		MartTabModel mod;
		this.mode = mode2;

		// methode pour supprimer une ligne
		if (this.mode == DELETE_COMPLETELY) {
			DAO examdao = DAOFactory.getDAO(DAO.EXAMEN);
			examdao.load();

			examdao.deleteObj((Examen) data);

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
