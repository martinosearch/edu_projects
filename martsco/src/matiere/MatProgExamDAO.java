package matiere;

import function.Constance;
import function.MartArranger;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import note.Coefficient;
import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class MatProgExamDAO extends DAO<MatiereProg> {

	protected MatiereProg matiere = new MatiereProg();
	private MartArranger ma;

	// Constructeurs
	public MatProgExamDAO(Connection conn) {
		super(conn);
		ma = new MartArranger();
	}

	// insert un nouveau element
	public boolean createObj(MatiereProg obj) {
		// Insert une ligne dans table_perso
		this.matiere = obj;
		String strQuery = "'" + matiere.getIntitule() + "','"
				+ matiere.getCharge() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "INSERT INTO " + tablePersoExam
					+ "(matiere_prog,charge) " + "VALUES (" + strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			System.out.print(e.getMessage());
			JOptionPane.showMessageDialog(null,
					"L'intitulé de la matiere est incorrect", "ERREUR!",
					JOptionPane.ERROR_MESSAGE);
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(MatiereProg obj) {
		if (exists(obj) == true) {
			updateObj(obj);

			String query2 = "ALTER TABLE " + getTableExam() + " RENAME COLUMN "
					+ Constance.getCor(obj.getIntitule())
					+ "_note1 character varying TO "
					+ Constance.getCor(obj.getPrimaryKey())
					+ "_note1 character varying";

			String query3 = "ALTER TABLE " + getTableExam() + " RENAME COLUMN "
					+ Constance.getCor(obj.getIntitule())
					+ "_note2 character varying TO "
					+ Constance.getCor(obj.getPrimaryKey())
					+ "_note2 character varying";

			updateDB(query2);
			updateDB(query3);

			return true;
		} else {
			createObj(obj);

			String matint = obj.getIntitule();
			String matcor = Constance.getCor(matint);

			String query2 = "ALTER TABLE " + getTableExam() + " ADD " + matcor
					+ "_note1 character varying";
			String query3 = "ALTER TABLE " + getTableExam() + " ADD " + matcor
					+ "_note2 character varying";

			updateDB(query2);
			updateDB(query3);

			return false;
		}
	}

	public boolean updateObj(MatiereProg mat) {
		// corrige une ligne dans table_annee
		this.matiere = mat;

		String strQuery = "matiere_prog='" + matiere.getIntitule()
				+ "',charge='" + matiere.getCharge() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tablePersoExam + " SET " + strQuery
					+ " WHERE matiere_prog='" + matiere.getIntitule() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ==============================================================================
	public boolean deleteObj(MatiereProg obj) {
		String query = "DELETE FROM " + tablePersoExam + " WHERE "
				+ "matiere_prog='" + obj.getIntitule() + "'";
		updateDB(query);
		System.out.println("l'action demandée" + query);

		return false;
	}

	// ========================================================================
	public MatiereProg findObj(int id) {

		return null;
	}

	// =================================================================================

	public MartList getList() {
		// se connecte et rechercher
		listT = new MartList<MatiereProg>();
		ArrayList<MartRangeable> temp = new ArrayList<MartRangeable>();
		coefdao = DAOFactory.getDAO(DAO.COEFFICIENT);
		coefdao.loadExam(examen);

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM " + tablePersoExam
					+ " ORDER BY matiere_prog";

			System.out.println(query);
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

				MatiereProg mat = new MatiereProg(dataObj);
				double coef = ((Coefficient) coefdao.findObj(mat.getIntitule()
						+ niveau)).getCoef();

				// System.out.println("mat:" + mat.getIntitule() + " niveau: "
				// + niveau + " coef: " + coef);

				mat.setCoef(coef);// On corrige la moyenne

				dataObj = new ArrayList();

				// On l'ajoute à la liste rangeable avec
				temp.add(mat);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "Classe " + classe
			// + " n'existe pas pour l'année scolaire " + annee);
		}

		// on ordonne
		temp = ma.ordonner(temp);
		for (MartRangeable rg : temp) {
			listT.add((MatiereProg) rg);
		}

		return listT;
	}

	public ArrayList<MatiereProg> getList(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatiereProg findObj(String intitule) {
		for (MatiereProg obj : listT) {
			while (obj.getIntitule().equals(intitule)) {
				matiere = obj;
				break;
			}
		}
		return matiere;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
