package reference;

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
import function.Constance;
import graphicsModel.MartList;

public class ReferenceDAO extends DAO<Reference> {

	protected Reference reference = new Reference();
	private DAO andao;

	public ReferenceDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Reference obj) {
		// Insert une ligne dans table_perso
		this.reference = obj;
		String strQuery = "'" + Constance.getCorAposthr(reference.getRef())
				+ "','" + Constance.getCorAposthr(reference.getValueRef())
				+ "'";

		// se connecte et insert
		if (!reference.getRef().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				String query = "INSERT INTO table_ref (ref,value) "
						+ " VALUES (" + strQuery + ")";

				System.out.println(query);
				state.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null, "L'Entrée est" + " incorrect",
					"ERREUR!", JOptionPane.ERROR_MESSAGE);

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Reference obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public boolean updateObj(Reference mat) {
		// corrige une ligne dans table_annee
		this.reference = mat;

		String strQuery = "ref='" + Constance.getCorAposthr(reference.getRef())
				+ "'," + "value='"
				+ Constance.getCorAposthr(reference.getValueRef()) + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_ref SET " + strQuery + " WHERE ref='"
					+ reference.getRef() + "'";
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
	public MartList<Reference> getList() {
		// se connecte et rechercher
		listT = new MartList<Reference>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM table_ref";

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

				Reference mat = new Reference(dataObj);
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
	public Reference findObj(String intitule) {
		for (Reference mat : listT) {
			while (mat.getRef().equals(intitule)) {
				reference = mat;
				break;
			}
		}
		return reference;
	}

	@Override
	public boolean deleteObj(Reference obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
