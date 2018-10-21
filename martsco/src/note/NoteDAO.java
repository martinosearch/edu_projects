package note;

import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import annee.Annee;
import connection.DAO;
import connection.MartConnection;

public class NoteDAO extends DAO<Note> {
	private Note note;

	public NoteDAO(Connection conn) {
		super(conn);

	}

	public boolean createObj(Note obj) {
		return false;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Note not) {
		updateObj(not);
		return false;
	}

	public boolean updateObj(Note obj) {
		// insert une note
		this.note = obj;
		String strQuery = "";

		// il faudra aussi situer le trimestre; donc
		if (trimestre == 1) {
			strQuery = matiereCor + "_note11='" + note.getNote1str() + "',"
					+ matiereCor + "_note12='" + note.getNote2str() + "',"
					+ matiereCor + "_note13='" + note.getNote3str() + "',"
					+ matiereCor + "_note14='" + note.getNote4str() + "'";
		}
		if (trimestre == 2) {
			strQuery = matiereCor + "_note21='" + note.getNote1str() + "',"
					+ matiereCor + "_note22='" + note.getNote2str() + "',"
					+ matiereCor + "_note23='" + note.getNote3str() + "',"
					+ matiereCor + "_note24='" + note.getNote4str() + "'";
		}
		if (trimestre == 3) {
			strQuery = matiereCor + "_note31='" + note.getNote1str() + "',"
					+ matiereCor + "_note32='" + note.getNote2str() + "',"
					+ matiereCor + "_note33='" + note.getNote3str() + "',"
					+ matiereCor + "_note34='" + note.getNote4str() + "'";
		}

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "UPDATE " + tableBull + " SET " + strQuery
					+ " WHERE codeInfo_eleve='" + note.getCodeInfo() + "'";

			System.out.println(query);

			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(Annee obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public Note findObj(int id) {
		return null;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Note> getList() {
		// se connecte et rechercher
		listT = new MartList<Note>();

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "";

			if (trimestre == 1) {
				query = "SELECT codeinfo_eleve," + matiereCor + "_note11,"
						+ matiereCor + "_note12," + matiereCor + "_note13,"
						+ matiereCor + "_note14 " + " FROM " + tableBull
						+ " ORDER BY nom_eleve,prenom_eleve";
			}
			if (trimestre == 2) {
				query = "SELECT codeinfo_eleve," + matiereCor + "_note21,"
						+ matiereCor + "_note22," + matiereCor + "_note23,"
						+ matiereCor + "_note24 " + " FROM " + tableBull
						+ " ORDER BY nom_eleve,prenom_eleve";
			}
			if (trimestre == 3) {
				query = "SELECT codeinfo_eleve," + matiereCor + "_note31,"
						+ matiereCor + "_note32," + matiereCor + "_note33,"
						+ matiereCor + "_note34 " + " FROM " + tableBull
						+ " ORDER BY nom_eleve,prenom_eleve";
			}

			System.out.println("==============================>>" + query);
			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();
			int g = 0;

			rst.beforeFirst();

			while (rst.next()) {
				MartList<String> dataObj = new MartList<String>();
				String matricule = rst.getString(1);
				dataObj.add(matricule);

				for (int j = 2; j <= col; j++) {
					String note = rst.getString(j);
					dataObj.add(note);
				}

				Note an = new Note(dataObj);
				an.setMatiere(matiereCor);
				listT.add(an);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(Note obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Note findObj(String matri) {
		for (Note n : listT) {
			while (n.getCodeInfo().equals(matri)) {
				note = n;
				break;
			}
		}

		return note;
	}

	public Note findObj(String classe, String annee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
