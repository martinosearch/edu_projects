package security;

import function.Serial;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DAO;
import connection.MartConnection;

public class SerialDAO extends DAO<Serial> {
	private Serial serial = new Serial();

	public SerialDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Serial cls) {
		// Insert une ligne dans table_perso
		this.serial = cls;
		String strQuery = "'" + serial.getIntitule() + "','"
				+ serial.getSerialValue() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO serial_center (intitule_serial,value_serial) "
					+ " VALUES (" + strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// System.out.println("Données inserées");

		return true;
	}

	// met ajour
	public boolean updateObj(Serial obj) {
		// corrige une ligne dans table_annee
		this.serial = obj;
		String strQuery = "intitule_serial='" + serial.getIntitule() + "',"
				+ "value_serial='" + serial.getSerialValue() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE serial_center SET " + strQuery + " WHERE"
					+ " intitule_serial='" + serial.getIntitule() + "'";

			System.out.println(query);
			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	// choisit le type de mise ajour
	public boolean update_create(Serial obj) {
		if (exists(obj) == true) {
			updateObj(obj);
			return true;
		} else {
			createObj(obj);
			return false;
		}

	}

	public boolean deleteObj(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public Serial findObj(String intitule) {
		serial = new Serial();
		for (Serial set1 : listT) {
			while (set1.getIntitule().equals(intitule)) {
				serial = set1;
				break;
			}
		}
		// System.out.println("le serial renvoyée est: " +
		// serial.getIntitule());
		return serial;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Serial> getList() {
		// se connecte et rechercher
		listT = new MartList<Serial>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM serial_center ORDER BY intitule_serial";

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
				Serial serial = new Serial();
				serial.setIntitule((String) dataObj.get(0));
				serial.setSerialValue((int) dataObj.get(1));

				listT.add(serial);
				dataObj = new ArrayList();
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(Serial obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}