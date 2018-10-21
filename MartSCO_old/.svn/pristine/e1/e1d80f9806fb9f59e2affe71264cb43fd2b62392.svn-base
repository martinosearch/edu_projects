package ecolage;

import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import connection.DAO;
import connection.MartConnection;

public class OperationDAO extends DAO<Operation> {
	private Operation operation = new Operation();

	public OperationDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Operation obj) {
		// Insert une ligne dans table_perso
		operation = obj;

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String strQuery = "'" + operation.getNumOperation() + "','"
					+ operation.getDate().getMillis() + "','"
					+ operation.getCharge() + "','"
					+ operation.getJustification() + "'";

			String query = "INSERT INTO "
					+ tableOperation
					+ " (num_operation,date_operation,codecharge_operation,justification_operation) VALUES ("
					+ strQuery + ")";

			state.executeUpdate(query);

			System.out.println(query);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Operation obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}

		return true;
	}

	public boolean updateObj(Operation obj) {
		return true;
	}

	public boolean deleteObj(Operation obj) {
		String query = "DELETE FROM table_annee WHERE " + "annee='"
				+ obj.getPrimaryKey() + "'";
		updateDB(query);
		return false;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Operation> getList() {
		// se connecte et rechercher
		listT = new MartList<Operation>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " + tableOperation
					+ " ORDER BY num_operation";

			ResultSet rst = state.executeQuery(query);

			System.out.println(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				Operation op = new Operation();
				op.setNumOperation(rst.getString(2));

				try {
					DateTime date = new DateTime(rst.getLong(3));
					op.setDate(date);
					op.setCodeInfoCharge(rst.getString(4));
					op.setJustification(rst.getString(5));
				} catch (Exception e) {
					e.printStackTrace();
				}

				listT.add(op);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public Operation findObj(String s) {
		Operation result = null;
		for (Operation op : listT) {
			while ((op.getNumOperation()).equals(s)) {
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
