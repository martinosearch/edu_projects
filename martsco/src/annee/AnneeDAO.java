package annee;

import function.Constance;
import function.Serial;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class AnneeDAO extends DAO<Annee> {
	private Annee annee = new Annee();
	private MartList<String> listeUpdate;

	public AnneeDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public boolean createObj(Annee obj) {
		// Insert une ligne dans table_perso
		annee = obj;

		// se connecte et insert
		if (!annee.getIntitule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String strQuery = "'" + annee.getIntitule() + "'";

				String query = "INSERT INTO table_annee (annee) VALUES ("
						+ strQuery + ")";

				System.out.println("Je suis appellé pour: " + query);
				state.executeUpdate(query);

				System.out.println(query);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			JOptionPane.showMessageDialog(null, "Année-Scolaire incorrecte",
					"ERREUR!", JOptionPane.ERROR_MESSAGE);
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Annee obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			// on crée l'objet
			createObj(obj);

			// initialisation du compteur de versements
			serialdao = DAOFactory.getDAO(DAO.SERIAL);
			serialdao.load();
			Serial act = new Serial("versEcolage_"
					+ Constance.getCor(obj.getIntitule()));
			act.setSerialValue(1);
			serialdao.update_create(act);
			// fin compteur

			String query1 = "CREATE TABLE "
					+ "table_coef_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_coef serial,"
					+ "intitule_coef character varying PRIMARY KEY NOT NULL, valeur_coef character varying)";

			String query2 = "CREATE TABLE " + "table_set_"
					+ Constance.getCor(obj.getIntitule()) + "(id_set serial,"
					+ "intitule_set character varying PRIMARY KEY NOT NULL,"
					+ "attribut_set character varying NOT NULL)";

			String query3 = "ALTER TABLE table_decoupage ADD as_"
					+ Constance.getCor(obj.getIntitule()) + " integer";

			String query4 = "ALTER TABLE table_promo_eleve ADD as_"
					+ Constance.getCor(obj.getIntitule())
					+ " character varying";

			String query5 = "ALTER TABLE table_classes_eleve ADD as_"
					+ Constance.getCor(obj.getIntitule())
					+ " character varying";

			String query6 = "CREATE TABLE table_ecolage_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_ecolage serial,"
					+ "nom_eleve character varying, prenom_eleve character varying, "
					+ "codeinfo_eleve character varying PRIMARY KEY NOT NULL,classe_eleve character varying,"
					+ "vers1 character varying,num_vers1 character varying)";

			String query7 = "CREATE TABLE table_operation_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_ecolage serial,"
					+ "num_operation character varying PRIMARY KEY NOT NULL, date_operation character varying, "
					+ "codecharge_operation character varying,justification_operation character varying)";

			String query8 = "CREATE TABLE table_salaire_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_salaire serial,"
					+ "nom_agent character varying, prenom_agent character varying, "
					+ "codeinfo_agent character varying PRIMARY KEY NOT NULL,"
					+ "vers1 character varying,num_vers1 character varying)";

			String query9 = "CREATE TABLE table_payement_salaire_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_salaire serial,"
					+ "nom_agent character varying, prenom_agent character varying, "
					+ "codeinfo_agent character varying PRIMARY KEY NOT NULL,"
					+ "vers1 character varying,num_vers1 character varying)";

			String query10 = "CREATE TABLE table_activite_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_activite_activite serial, matiere_activite character varying,classe_activite character varying,"
					+ "enseignant_activite character varying,nbreheure_activite character varying,"
					+ "codeinfo_activite character varying PRIMARY KEY NOT NULL)";

			String query11 = "CREATE TABLE table_serie_note_"
					+ Constance.getCor(obj.getIntitule())
					+ "(id_serie serial,"
					+ "code_serie character varying PRIMARY KEY NOT NULL, value_serie character varying, "
					+ "date_serie character varying,classe_serie character varying,matiere_serie character varying,"
					+ "trimestre_serie integer)";

			listeUpdate = new MartList<String>();
			listeTraitement = new MartList<Runnable>();

			listeUpdate.add(query1);
			listeUpdate.add(query2);
			listeUpdate.add(query6);
			listeUpdate.add(query7);
			listeUpdate.add(query8);
			listeUpdate.add(query9);
			listeUpdate.add(query10);

			listeUpdate.add(query3);
			listeUpdate.add(query4);
			listeUpdate.add(query5);
			listeUpdate.add(query11);

			for (final String str : listeUpdate) {
				Runnable run = new Runnable() {
					public void run() {
						updateDB(str);
					}
				};

				listeTraitement.add(run);
			}

			new Thread(new Runnable() {
				public void run() {
					for (Runnable run : listeTraitement) {
						try {
							launch(run);
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		return true;
	}

	public boolean updateObj(Annee obj) {
		// corrige une ligne dans table_annee
		this.annee = obj;

		String strQuery = "annee='" + annee.getIntitule() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_annee SET " + strQuery
					+ " WHERE annee='" + annee.getPrimaryKey() + "'";
			System.out.println(query);
			state.executeUpdate(query);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"MartSCO a détecté une erreur dans l'une de vos entrées",
					"ERREUR D'ENREGISTREMENT", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(Annee obj) {
		String query = "DELETE FROM table_annee WHERE " + "annee='"
				+ obj.getPrimaryKey() + "'";
		updateDB(query);
		return false;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Annee> getList() {
		// se connecte et rechercher
		listT = new MartList<Annee>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_annee ORDER BY annee DESC";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				ArrayList dataObj = new ArrayList();

				for (int j = 2; j <= col; j++) {
					dataObj.add(rst.getObject(j));
				}
				Annee an = new Annee(dataObj);

				dataObj = new ArrayList();
				listT.add(an);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public Annee findObj(String an1) {
		Annee result = null;
		for (Annee an : listT) {
			while ((an.getIntitule()).equals(an1)) {
				result = an;
				break;
			}
		}
		if (result == null)
			JOptionPane.showMessageDialog(null, "Année-scolaire " + an1
					+ " n'existe pas!", "ERREUR", JOptionPane.ERROR_MESSAGE);

		return result;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
