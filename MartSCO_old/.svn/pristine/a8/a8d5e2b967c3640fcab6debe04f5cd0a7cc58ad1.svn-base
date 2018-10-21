package agent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DAO;
import connection.MartConnection;
import function.Constance;
import graphicsModel.MartList;

public class AgentDAO extends DAO<Agent> {

	private Agent professeur = new Agent();

	public AgentDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Agent prof) {
		// Insert une ligne dans table_perso
		this.professeur = prof;
		String strQuery = "'" + Constance.getCorAposthr(professeur.getNom())
				+ "','" + Constance.getCorAposthr(professeur.getPrenom())
				+ "','" + Constance.getCorAposthr(professeur.getSexe()) + "','"
				+ professeur.getDateNais() + "','" + professeur.getDateEntree()
				+ "','" + professeur.getDateSortie() + "','"
				+ Constance.getCorAposthr(professeur.getTel()) + "','"
				+ Constance.getCorAposthr(professeur.getEmail()) + "','"
				+ Constance.getCorAposthr(professeur.getPersoPrev()) + "','"
				+ Constance.getCorAposthr(professeur.getTelPerso()) + "','"
				+ Constance.getCorAposthr(professeur.getCodeInfo()) + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO liste_prof (nom_prof,prenom_prof,sexe_prof,"
					+ "date_nais_prof,date_entree_prof,date_sortie_prof,"
					+ "tel_prof,email_prof,perso_prev_prof,tel_perso_prof,codeinfo_prof)"
					+ " VALUES (" + strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("Données inserées avec succès");

		return true;
	}

	public boolean updateObj(Agent prof) {
		this.professeur = prof;
		String strQuery = "nom_prof='"
				+ Constance.getCorAposthr(professeur.getNom()) + "',"
				+ "prenom_prof='"
				+ Constance.getCorAposthr(professeur.getPrenom()) + "',"
				+ "sexe_prof='" + Constance.getCorAposthr(professeur.getSexe())
				+ "'," + "date_nais_prof='" + professeur.getDateNais() + "',"
				+ "date_entree_prof='" + professeur.getDateEntree() + "',"
				+ "date_sortie_prof='" + professeur.getDateSortie() + "',"
				+ "tel_prof='" + Constance.getCorAposthr(professeur.getTel())
				+ "'," + "email_prof='"
				+ Constance.getCorAposthr(professeur.getEmail()) + "',"
				+ "perso_prev_prof='"
				+ Constance.getCorAposthr(professeur.getPersoPrev()) + "',"
				+ "tel_perso_prof='"
				+ Constance.getCorAposthr(professeur.getTelPerso()) + "',"
				+ "codeinfo_prof='"
				+ Constance.getCorAposthr(professeur.getCodeInfo()) + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE liste_prof SET " + strQuery
					+ " WHERE codeinfo_prof='" + professeur.getCodeInfo() + "'";

			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Agent findObj(String matricule) {
		Agent result = new Agent();
		for (Agent ens : listT) {
			while (ens.getCodeInfo().equals(matricule)) {
				result = ens;
				break;
			}
		}
		return result;
	}

	public boolean update_create(Agent obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Agent> getList() {
		// se connecte et rechercher
		listT = new MartList<Agent>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM liste_prof ORDER BY nom_prof, prenom_prof";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				MartList dataObj = new MartList();

				for (int j = 2; j <= col; j++) {
					dataObj.add(rst.getObject(j));
				}
				Agent ens = new Agent(dataObj);
				dataObj = new MartList();
				listT.add(ens);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(Agent obj) {
		String query = "DELETE FROM liste_prof WHERE " + "codeinfo_prof='"
				+ obj.getCodeInfo() + "'";
		updateDB(query);
		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
