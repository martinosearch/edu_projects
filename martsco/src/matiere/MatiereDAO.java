package matiere;

import graphicsModel.MartList;
import interfacePerso.MartOrdonneur;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import classe.Classe;
import annee.Annee;
import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class MatiereDAO extends DAO<Matiere> implements MartOrdonneur {

	protected Matiere matiere = new Matiere();
	private DAO clsDao, anDao, matProgDao;

	public MatiereDAO(Connection conn) {
		super(conn);

		clsDao = DAOFactory.getDAO(DAO.CLASSE);
		anDao = DAOFactory.getDAO(DAO.ANNEE);
		matProgDao = DAOFactory.getDAO(DAO.MATIERE_PROG);
	}

	public boolean createObj(Matiere obj) {
		// Insert une ligne dans table_perso
		this.matiere = obj;
		String strQuery = "'" + matiere.getIntitule() + "','"
				+ matiere.getType() + "','" + matiere.getDim() + "','"
				+ matiere.getOrdre() + "'";

		// se connecte et insert
		if (!matiere.getIntitule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String query = "INSERT INTO table_matiere (intitule_matiere,"
						+ "type_matiere,dim_matiere,ordre_matiere) "
						+ " VALUES (" + strQuery + ")";

				System.out.println(query);
				state.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else
			JOptionPane.showMessageDialog(null, "L'intitulé de la matiere est"
					+ " incorrect", "ERREUR!", JOptionPane.ERROR_MESSAGE);

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	@Override
	public synchronized boolean update_create(Matiere obj) {
		if (exists(obj) == true) {
			updateObj(obj);

			matProgDao.setTypeUpdate(typeUpdate);
			System.out.println("===================================>>"
					+ typeUpdate);

			// mise à jour importante dans les table déjà créées
			if (typeUpdate == GENERAL_UPDATE) {
				anDao.load();
				clsDao.load();

				MartList<Annee> annees = anDao.getListObt();
				MartList<Classe> classes = clsDao.getListObt();

				for (Annee an : annees) {
					for (Classe cl : classes) {
						matProgDao.load("", cl.getIntitule(), trimestre,
								an.getIntitule());

						MatiereProg matp = (MatiereProg) matProgDao.findObj(obj
								.getPrimaryKey());

						if (!matp.getIntitule().equals("")) {
							matp.setIntitule(obj.getIntitule());
							matp.setPrimaryKey(obj.getPrimaryKey());

							matProgDao.update_create(matp);
						}
					}
				}
			}
			return true;
		} else {
			createObj(obj);
			return false;
		}
	}

	public boolean updateObj(Matiere mat) {
		// corrige une ligne dans table_annee
		this.matiere = mat;

		String strQuery = "intitule_matiere='" + matiere.getIntitule() + "',"
				+ "type_matiere='" + matiere.getType() + "',dim_matiere='"
				+ matiere.getDim() + "',ordre_matiere='" + matiere.getOrdre()
				+ "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_matiere SET " + strQuery
					+ " WHERE intitule_matiere='" + matiere.getPrimaryKey()
					+ "'";
			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// ==============================================================================
	// pour la suppression
	public boolean deleteObj(Matiere obj) {
		String query = "DELETE FROM table_matiere WHERE "
				+ "intitule_matiere='" + obj.getIntitule() + "'";
		updateDB(query);

		return false;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Matiere> getList() {
		// se connecte et rechercher
		listT = new MartList<Matiere>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_matiere ORDER BY ordre_matiere";

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
				Matiere mat = new Matiere(dataObj);
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
	public Matiere findObj(String intitule) {
		for (Matiere mat : listT) {
			while (mat.getIntitule().equals(intitule)) {
				matiere = mat;
				break;
			}
		}

		return matiere;
	}

	public int getOrdre(String intitule) {
		Matiere mat = findObj(intitule);
		int ordre = mat.getOrdre();

		return ordre;
	}

	public Matiere findObj(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	public Matiere findObj(String str1, String str2) {
		// TODO Auto-generated method stub
		return null;
	}

}
