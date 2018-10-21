package rapportBulletin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DAO;
import connection.MartConnection;
import eleve.Scolarite;
import graphicsModel.MartList;
import security.User;

public class HistoDAO extends DAO<Histo> {
	private Histo histo = new Histo();

	public HistoDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Histo his) {
		// Insert une ligne dans table_perso
		this.histo = his;
		String title = (histo.getIntitule()).replaceAll("[\\']", "''");
		String value = (histo.getHisto()).replaceAll("[\\']", "''");
		String date = String.valueOf(histo.getDate().getMillis());
		String orient = String.valueOf(histo.getOrientation());

		String strQuery = "'" + title + "','" + value + "','" + date + "','"
				+ orient + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO table_histo"
					+ "(intitule_histo,value_histo,date_histo,orient_histo)  VALUES ("
					+ strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Données inserées");
		return true;
	}

	// met ajour
	public boolean updateObj(Histo his) {
		// corrige une ligne dans table_annee
		this.histo = his;
		String title = (histo.getIntitule()).replaceAll("[\\']", "''");
		String value = (histo.getHisto()).replaceAll("[\\']", "''");
		String date = String.valueOf(histo.getDate().getMillis());
		String orient = String.valueOf(histo.getOrientation());

		String strQuery = "intitule_histo='" + title + "'," + "value_histo='"
				+ value + "'," + "date_histo='" + date + "',"
				+ "orient_histo='" + orient + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_histo SET " + strQuery + " WHERE"
					+ " intitule_histo='" + histo.getIntitule() + "'";
			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// choisit le type de mise ajour
	public boolean update_create(Histo obj) {
		if (exists(obj) == true) {
			updateObj(obj);
			return true;
		} else {
			createObj(obj);
			return false;
		}

	}

	public Histo findObj(String intitule) {

		for (Histo histo1 : listT) {
			while (histo1.getIntitule().equals(intitule)) {
				histo = histo1;
				break;
			}
		}
		return histo;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Histo> getList() {
		// se connecte et rechercher
		listT = new MartList<Histo>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_histo ORDER BY intitule_histo";

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

				Histo his = new Histo(dataObj);
				his.setRang((int) rst.getObject(1));// attribution du numéro
				his.setOrientation(rst.getInt(5));// définition de l'orientation
													// du papier

				listT.add(his);
				dataObj = new MartList();
			}

			rst.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(Histo obj) {
		String query = "DELETE FROM table_histo WHERE " + "id_histo='"
				+ obj.getRang() + "'";
		updateDB(query);

		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}