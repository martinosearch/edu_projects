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

import configurationEcole.ConfigEcole;
import note.Coefficient;
import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class MatiereProgDAO extends DAO<MatiereProg> {

	protected MatiereProg matiere = new MatiereProg();
	MartArranger ma;

	// Constructeurs
	public MatiereProgDAO(Connection conn) {
		super(conn);
		listT = new MartList<MatiereProg>();
		ma = new MartArranger();
	}

	// insert un nouveau element
	public boolean createObj(MatiereProg obj) {
		// Insert une ligne dans table_perso
		this.matiere = obj;
		String strQuery = "'" + matiere.getIntitule() + "','"
				+ Constance.getCorAposthr(matiere.getCharge()) + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "INSERT INTO " + tablePerso
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
		boolean exist = exists(obj);

		if (exist == true) {
			updateObj(obj);

			if (typeUpdate == GENERAL_UPDATE) {
				// pour les coef

				Coefficient coef = new Coefficient(obj.getCoef(),
						obj.getIntitule(), niveau);
				coef.setPrimaryKey(obj.getPrimaryKey());

				// System.out.println("====================================>>"
				// + niveau);

				coefdao = DAOFactory.getDAO(DAO.COEFFICIENT);
				coefdao.load("", classe, trimestre, annee);
				coefdao.update_create(coef);

				String query2 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note11 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note11";

				String query3 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note12 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note12";

				String query4 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note13 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note13";

				String query5 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note14 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note14";

				String query6 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note21 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note21";

				String query7 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note22 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note22";

				String query8 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note23 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note23";

				String query9 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note24 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note24";

				String query10 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note31 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note31";

				String query11 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note32 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note32";

				String query12 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note33 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note33";

				String query13 = "ALTER TABLE " + getTableBull()
						+ " RENAME COLUMN "
						+ Constance.getCor(obj.getPrimaryKey()) + "_note34 TO "
						+ Constance.getCor(obj.getIntitule()) + "_note34";

				updateDB(query2);
				updateDB(query3);
				updateDB(query4);
				updateDB(query5);
				updateDB(query6);
				updateDB(query7);
				updateDB(query8);
				updateDB(query9);
				updateDB(query10);
				updateDB(query11);
				updateDB(query12);
				updateDB(query13);
			}
		} else {
			createObj(obj);

			String query2 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note11 character varying";

			String query3 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note12 character varying";

			String query4 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note13 character varying";

			String query5 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note14 character varying";

			String query6 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note21 character varying";

			String query7 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note22 character varying";

			String query8 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note23 character varying";

			String query9 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note24 character varying";

			String query10 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note31 character varying";

			String query11 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note32 character varying";

			String query12 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note33 character varying";

			String query13 = "ALTER TABLE " + getTableBull() + " ADD "
					+ Constance.getCor(obj.getIntitule())
					+ "_note34 character varying";

			updateDB(query2);
			updateDB(query3);
			updateDB(query4);
			updateDB(query5);
			updateDB(query6);
			updateDB(query7);
			updateDB(query8);
			updateDB(query9);
			updateDB(query10);
			updateDB(query11);
			updateDB(query12);
			updateDB(query13);
		}
		return exist;
	}

	public boolean updateObj(MatiereProg mat) {
		// corrige une ligne dans table_annee
		this.matiere = mat;

		String strQuery = "matiere_prog='" + matiere.getIntitule()
				+ "',charge='" + Constance.getCorAposthr(matiere.getCharge())
				+ "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tablePerso + " SET " + strQuery
					+ " WHERE matiere_prog='" + matiere.getPrimaryKey() + "'";
			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ==============================================================================
	public boolean deleteObj(MatiereProg obj) {

		String query = "DELETE FROM " + tablePerso + " WHERE "
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

	public MartList<MatiereProg> getList() {
		// se connecte et rechercher
		listT = new MartList<MatiereProg>();
		ArrayList<MartRangeable> temp = new ArrayList<MartRangeable>();
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		matdao.load();

		coefdao = DAOFactory.getDAO(DAO.COEFFICIENT);
		coefdao.load(new String(), classe, trimestre, annee);

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM " + tablePerso
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

				mat.setCoef(coef);// On corrige le coefficient

				// on ajoute le type
				Matiere superMatiere = (Matiere) matdao.findObj(mat
						.getIntitule());
				mat.setType(superMatiere.getType());

				dataObj = new ArrayList();

				// On l'ajoute à la liste rangeable avec
				temp.add(mat);
			}
			rst.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Classe " + classe
					+ " n'existe pas pour l'année scolaire " + annee);
		}

		// on ordonne
		temp = ma.ordonner(temp);
		for (MartRangeable rg : temp) {
			listT.add((MatiereProg) rg);
		}

		return listT;
	}

	public ArrayList<MatiereProg> getList(String str) {
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
		return 0;
	}

}
