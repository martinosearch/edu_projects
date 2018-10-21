package classe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import matiere.Matiere;
import connection.DAO;
import connection.MartConnection;
import eleve.Scolarite;
import graphicsModel.MartList;

public class NiveauDAO extends DAO<Niveau> {

	protected Niveau niveau = new Niveau();

	public NiveauDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Niveau obj) {
		// Insert une ligne dans table_perso
		this.niveau = obj;
		String strQuery = "'" + niveau.getIntitule() + "','"
				+ niveau.getTypeEns() + "'";

		// se connecte et insert
		if (!niveau.getIntitule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				String query = "INSERT INTO table_niveau (niveau,type_ens) "
						+ " VALUES (" + strQuery + ")";

				System.out.println(query);
				state.executeUpdate(query, state.RETURN_GENERATED_KEYS);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null, "L'intitulé de la matiere est"
					+ " incorrect", "ERREUR!", JOptionPane.ERROR_MESSAGE);

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Niveau obj) {
		if (exists(obj) == true) {
			updateObj(obj);
			return true;
		} else {
			createObj(obj);
			return false;
		}
	}

	public boolean updateObj(Niveau mat) {
		// corrige une ligne dans table_annee
		this.niveau = mat;

		String strQuery = "niveau='" + niveau.getIntitule() + "',"
				+ "type_ens='" + niveau.getTypeEns() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_niveau SET " + strQuery
					+ " WHERE niveau='" + niveau.getIntitule() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ==============================================================================
	public boolean deleteObj(Matiere obj) {
		// TODO Auto-generated method stub
		return false;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Niveau> getList() {
		// se connecte et rechercher
		listT = new MartList<Niveau>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_niveau ORDER BY niveau";

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

				Niveau mat = new Niveau(dataObj);
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

	@Override
	public Niveau findObj(String niveau) {
		for (Niveau mat : listT) {
			while (mat.getIntitule().equals(niveau)) {
				this.niveau = mat;
				break;
			}
		}
		return this.niveau;
	}

	@Override
	public boolean deleteObj(Niveau obj) {
		String query = "DELETE FROM table_niveau WHERE " + "niveau='"
				+ obj.getIntitule() + "'";
		updateDB(query);
		return true;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
