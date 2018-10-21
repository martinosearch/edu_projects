package planning;

import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DAO;
import connection.MartConnection;

public class ActiviteDAO extends DAO<Activite> {
	private Activite activite = new Activite();

	public ActiviteDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Activite obj) {
		// Insert une ligne dans table_perso
		activite = obj;

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String strQuery = "'" + activite.getMatiere() + "','"
					+ activite.getClasse() + "','" + activite.getEnseignant()
					+ "','" + activite.getNbreHeure() + "','"
					+ activite.getCodeInfo() + "'";

			String query = "INSERT INTO " + tableActivite
					+ " (matiere_activite,classe_activite,enseignant_activite,"
					+ "nbreheure_activite,codeinfo_activite) VALUES ("
					+ strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Activite obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}

		return true;
	}

	public boolean updateObj(Activite obj) {
		return true;
	}

	public boolean deleteObj(Activite obj) {
		String query = "DELETE FROM table_annee WHERE " + "annee='"
				+ obj.getPrimaryKey() + "'";
		updateDB(query);
		return false;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Activite> getList() {
		// se connecte et rechercher
		listT = new MartList<Activite>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " + tableActivite
					+ " ORDER BY codeinfo_activite";

			System.out.println(query);
			ResultSet rst = state.executeQuery(query);

			System.out.println(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				Activite act = new Activite();
				act.setMatiere(rst.getString(2));
				act.setClasse(rst.getString(3));
				act.setEnseignant(rst.getString(4));
				act.setNbreHeure(Integer.parseInt(rst.getString(5)));
				act.setCodeInfo(rst.getString(6));

				listT.add(act);
			}

			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public Activite findObj(String s) {
		Activite result = null;
		for (Activite op : listT) {
			while ((op.getCodeInfo()).equals(s)) {
				result = op;
				break;
			}
		}

		return result;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
