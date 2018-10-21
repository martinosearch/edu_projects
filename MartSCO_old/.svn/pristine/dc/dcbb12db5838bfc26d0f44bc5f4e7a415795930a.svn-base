package etablissement;

import function.Constance;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.DAO;
import connection.MartConnection;

public class EtsDAO extends DAO<Etablissement> {

	protected Etablissement ets = new Etablissement();
	private DAO clsdao;

	public EtsDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Etablissement obj) {
		// Insert une ligne dans table_perso
		this.ets = obj;
		String strQuery = "'" + ets.getIntitule().replaceAll("[\\']", "''")
				+ "','" + Constance.getCorAposthr(ets.getNom()) + "','"
				+ ets.getContact() + "'";

		// se connecte et insert
		if (!ets.getIntitule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				String query = "INSERT INTO table_ets (intitule_ets,"
						+ "nom_ets,contact_ets) " + " VALUES (" + strQuery
						+ ")";

				System.out.println(query);
				state.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null,
					"La donnée est" + " incorrecte", "ERREUR!",
					JOptionPane.ERROR_MESSAGE);

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Etablissement obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public boolean updateObj(Etablissement mat) {
		// corrige une ligne dans table_annee
		this.ets = mat;

		String strQuery = "intitule_ets='" + ets.getIntitule() + "',"
				+ "nom_ets='" + Constance.getCorAposthr(ets.getNom())
				+ "',contact_ets='" + ets.getContact() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_ets SET " + strQuery + " WHERE "
					+ "intitule_ets='"
					+ Constance.getCorAposthr(ets.getIntitule()) + "'"
					+ " AND nom_ets='" + Constance.getCorAposthr(ets.getNom())
					+ "'";
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
	public boolean deleteObj(Etablissement obj) {
		ets = obj;
		String query = "DELETE FROM table_ets WHERE intitule_ets='"
				+ Constance.getCorAposthr(ets.getIntitule()) + "'"
				+ " AND nom_ets='" + Constance.getCorAposthr(ets.getNom())
				+ "'";
		updateDB(query);

		return false;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Etablissement> getList() {
		// se connecte et rechercher
		listT = new MartList<Etablissement>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_ets ORDER BY intitule_ets";

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

				Etablissement mat = new Etablissement(dataObj);

				dataObj = new ArrayList();
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
	public Etablissement findObj(String intitule) {
		for (Etablissement mat : listT) {
			while (mat.getIntitule().equals(intitule)) {
				ets = mat;
				break;
			}
		}
		return ets;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
