package responsable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import agent.Responsable;
import connection.DAO;
import connection.MartConnection;
import eleve.Scolarite;
import function.Constance;
import graphicsModel.MartList;

public class RespDAO extends DAO<Responsable> {

	protected Responsable resp = new Responsable();
	private DAO profdao;
	Constance cst;
	String col;

	public RespDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Responsable obj) {
		// Insert une ligne dans table_perso

		this.resp = obj;
		String strQuery = "'" + resp.getIntitule() + "','" + resp.getCodeInfo()
				+ "','" + resp.getSexe() + "','" + resp.getFonction() + "'";

		// se connecte et insert
		if (!resp.getIntitule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String query = "INSERT INTO table_resp(titre_resp,"
						+ "matricule_resp,sexe_resp,fonction_resp) "
						+ " VALUES (" + strQuery + ")";

				System.out.println(query);
				state.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null, "L'entrée est incorrect",
					"ERREUR!", JOptionPane.ERROR_MESSAGE);

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Responsable obj) {
		if (exists(obj) == true) {
			updateObj(obj);
			return true;
		} else {
			createObj(obj);
			return false;
		}

	}

	public boolean updateObj(Responsable mat) {
		// corrige une ligne dans table_annee
		this.resp = mat;

		String strQuery = "titre_resp='" + resp.getIntitule()
				+ "', matricule_resp='" + resp.getCodeInfo() + "',sexe_resp='"
				+ resp.getSexe() + "',fonction_resp='" + resp.getFonction()
				+ "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_resp SET " + strQuery + " WHERE "
					+ "titre_resp='" + resp.getIntitule() + "'";

			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ==============================================================================
	public boolean deleteObj(Responsable obj) {
		// TODO Auto-generated method stub
		return false;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Responsable> getList() {
		// se connecte et rechercher
		listT = new MartList<Responsable>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_resp ORDER BY titre_resp";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				ArrayList dataObj = new ArrayList();

				for (int j = 2; j < col; j++) {
					dataObj.add(rst.getObject(j));
				}

				Responsable mat = new Responsable(dataObj);
				mat.setFonction(rst.getString(5));

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
	public Responsable findObj(String titre) {
		resp = listT.find(titre);
		return resp;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
