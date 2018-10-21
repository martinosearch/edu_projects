package eleve;

import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DAO;
import connection.MartConnection;

public class CursusDAO extends DAO<Cursus> {
	private Cursus cursus = new Cursus();

	public CursusDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Cursus obj) {
		// Insert une ligne dans table_perso
		cursus = obj;

		// se connecte et insert
		if (!cursus.getCodeInfo().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String strQuery = "'" + cursus.getCodeInfo() + "','"
						+ cursus.getClasse() + "'";
				String query = "INSERT INTO table_classes_eleve (codeinfo_eleve,as_"
						+ anneeCor + ") " + " VALUES (" + strQuery + ")";

				state.executeUpdate(query);

				System.out.println(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public synchronized boolean update_create(Cursus obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public boolean updateObj(Cursus obj) {
		// corrige une ligne dans table_annee
		this.cursus = obj;

		String strQuery = "codeinfo_eleve='" + cursus.getCodeInfo() + "',as_"
				+ anneeCor + "='" + cursus.getClasse() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_classes_eleve SET " + strQuery
					+ " WHERE codeinfo_eleve='" + cursus.getCodeInfo() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(Cursus obj) {
		String query = "DELETE FROM table_classes_eleve WHERE "
				+ "codeinfo_eleve='" + obj.getCodeInfo() + "'";
		updateDB(query);
		return false;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Cursus> getList() {
		// se connecte et rechercher
		listT = new MartList<Cursus>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT codeinfo_eleve,as_" + anneeCor
					+ " FROM table_classes_eleve";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				Cursus curs = new Cursus(rst.getString(1), annee,
						rst.getString(2));

				listT.add(curs);
			}
			rst.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public Cursus findObj(String matricule) {
		for (Cursus an : listT) {
			while ((an.getCodeInfo()).equals(matricule)) {
				cursus = an;
				break;
			}
		}
		// System.out.println("Cursus=================>>" + cursus.getAnnee()+
		// cursus.getClasse());
		return cursus;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
