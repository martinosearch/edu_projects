package examen;

import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.DAO;
import connection.MartConnection;

public class ExamDAO extends DAO<Examen> {
	protected Examen nExamen = new Examen();
	private DAO clsdao;
	private static int count = 1;

	public ExamDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Examen obj) {
		// Insert une ligne dans table_perso
		this.nExamen = obj;
		String strQuery = "'" + nExamen.getIntitule() + "','"
				+ nExamen.getMois() + "','" + nExamen.getAnnee() + "','"
				+ nExamen.getNiveau() + "','" + nExamen.getType() + "'";

		// se connecte et insert
		if (!nExamen.getIntitule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				String query = "INSERT INTO table_examen (intitule_examen,"
						+ "mois_examen,annee_examen,serie_examen,type_examen) "
						+ " VALUES (" + strQuery + ")";

				System.out.println(query);
				state.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null, "L'Entrée est" + " incorrecte",
					"ERREUR!", JOptionPane.ERROR_MESSAGE);

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Examen obj) {
		nExamen = obj;
		boolean exist = exists(nExamen);

		// tant que l'examen existe
		while (exist == true) {
			int reponse = JOptionPane.showConfirmDialog(
					null,
					"Un examen portant ce même" + " nom "
							+ nExamen.getIntitule() + "\n "
							+ "est déjà créé!\n" + "S'agit-il de celui-ci?",
					"INFO", JOptionPane.YES_NO_OPTION);

			if (reponse == JOptionPane.YES_OPTION) {
				updateObj(nExamen);

				break;
			} else {
				count++;
				nExamen.setIntitule(nExamen.getIntitule() + " Num_" + count);
				exist = exists(nExamen);
			}
		}

		// Au cas où l'examen n'existe pas
		if (exists(nExamen) == false) {
			createObj(nExamen);
		}
		return exist;
	}

	public boolean updateObj(Examen mat) {
		// corrige une ligne dans table_annee
		this.nExamen = mat;

		String strQuery = "intitule_examen='" + nExamen.getIntitule() + "',"
				+ "mois_examen='" + nExamen.getMois() + "'," + "annee_examen='"
				+ nExamen.getAnnee() + "'," + "serie_examen='"
				+ nExamen.getNiveau() + "'," + "type_examen='"
				+ nExamen.getType() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_examen SET " + strQuery + " "
					+ "WHERE intitule_examen='" + nExamen.getIntitule() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ==============================================================================
	// pour la suppression
	public boolean deleteObj(Examen obj) {
		String query = "DELETE FROM table_examen WHERE " + "intitule_examen='"
				+ obj.getIntitule() + "'";
		updateDB(query);

		return false;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Examen> getList() {
		// se connecte et rechercher
		listT = new MartList<Examen>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_examen ORDER BY intitule_examen";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				MartList dataObj = new MartList();

				for (int j = 3; j <= col; j++) {
					dataObj.add(rst.getObject(j));
				}
				Examen mat = new Examen(dataObj);
				mat.setIntitule(rst.getString(2));

				dataObj = new MartList();
				listT.add(mat);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	public void setTable(String tb) {
	}

	@Override
	public Examen findObj(String intit) {
		for (Examen mat : listT) {
			while (mat.getIntitule().equals(intit)) {
				nExamen = mat;
				break;
			}
		}
		System.out.println("l'examen renvoyée est: " + nExamen.getIntitule());
		return nExamen;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
