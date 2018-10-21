package configurationAppli;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import connection.DAO;
import connection.MartConnection;
import eleve.Scolarite;
import function.Constance;
import graphicsModel.MartList;
import security.User;

public class SetDAO extends DAO<Setting> {
	private Setting set = new Setting();

	public SetDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Setting cls) {
		// Insert une ligne dans table_perso
		this.set = cls;
		String strQuery = "'" + set.getIntitule() + "','"
				+ Constance.getCorAposthr((String) set.getAttribut()) + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO " + tableSet
					+ "(intitule_set,attribut_set) " + " VALUES (" + strQuery
					+ ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Données inserées");
		return true;
	}

	// met ajour
	public boolean updateObj(Setting obj) {
		// corrige une ligne dans table_annee
		this.set = obj;
		String strQuery = "intitule_set='" + set.getIntitule() + "',"
				+ "attribut_set='"
				+ Constance.getCorAposthr((String) set.getAttribut()) + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tableSet + " SET " + strQuery + " WHERE"
					+ " intitule_set='" + set.getIntitule() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// choisit le type de mise ajour
	public boolean update_create(Setting obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public boolean deleteObj(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public Setting findObj(String intitule) {
		set = new Setting();
		for (Setting set1 : listT) {
			while (set1.getIntitule().equals(intitule)) {
				set = set1;
				break;
			}
		}
		return set;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Setting> getList() {
		// se connecte et rechercher
		listT = new MartList<Setting>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " + tableSet
					+ " ORDER BY intitule_set";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			// System.out.println(query);

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				MartList dataObj = new MartList();

				for (int j = 2; j <= col; j++) {
					dataObj.add(rst.getObject(j));
				}
				Setting cl = new Setting(dataObj);

				listT.add(cl);
				dataObj = new MartList();
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(Setting obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}