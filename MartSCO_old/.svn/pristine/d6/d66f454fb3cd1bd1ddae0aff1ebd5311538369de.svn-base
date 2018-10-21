package examen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import annee.Annee;
import note.Note;
import connection.DAO;
import connection.MartConnection;
import eleve.Scolarite;
import function.Constance;
import graphicsModel.MartList;

public class NoteExamDAO extends DAO<Note> {
	private Note note;

	public NoteExamDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Note obj) {
		return false;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Note not) {
		return false;

	}

	public boolean updateObj(Note obj) {
		// insert une note
		this.note = obj;
		String strQuery = matiereCor + "_note1='" + note.getNote3str() + "',"
				+ matiereCor + "_note2='" + note.getNote4str() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "UPDATE " + tableExam + " SET " + strQuery
					+ " WHERE codeInfo_eleve='" + note.getCodeInfo() + "'";

			System.out.println(query);
			state.executeUpdate(query);

			System.out.println("Mise à jour effectuée");
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
			String query = "SELECT codeinfo_eleve," + Constance.getCor(matiere)
					+ "_note1," + Constance.getCor(matiere) + "_note2 "
					+ " FROM " + tableExam + " ORDER BY nom_eleve,prenom_eleve";

			System.out.println(query);
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

				Note note = new Note();
				note.setCodeInfo(dataObj.get(0));
				note.setNote3str(dataObj.get(1));
				note.setNote4str(dataObj.get(2));

				listT.add(note);
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
