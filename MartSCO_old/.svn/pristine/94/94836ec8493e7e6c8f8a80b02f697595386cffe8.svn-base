package note;

import function.Constance;
import graphicsModel.MartList;
import interfacePerso.SuperDAO;

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

public class RapMoyenneDAO extends DAO<RapMoyenne> implements SuperDAO {

	protected RapMoyenne rmoy = new RapMoyenne();
	private String archive = "";

	public RapMoyenneDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(RapMoyenne obj) {
		// Insert une ligne dans table_perso
		this.rmoy = obj;
		String strQuery = "'" + rmoy.getMatricule() + "','"
				+ rmoy.getMoyenne1() + "','" + rmoy.getRang1() + "','"
				+ rmoy.getMoyenne2() + "','" + rmoy.getRang2() + "','"
				+ rmoy.getMoyenne3() + "','" + rmoy.getRang3() + "','"
				+ rmoy.getMoyAnn() + "','" + rmoy.getRangAnn() + "'";

		// se connecte et insert
		if (!rmoy.getMatricule().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				String query = "INSERT INTO " + getTableArch()
						+ "(matricule,moy1,rang1,"
						+ "moy2,rang2,moy3,rang3,moyann,rangann) "
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
	public boolean update_create(RapMoyenne obj) {
		if (exists(obj) == true) {
			updateObj(obj);
			return true;
		} else {
			createObj(obj);
			return false;
		}

	}

	public boolean updateObj(RapMoyenne mat) {
		// corrige une ligne dans table_annee
		this.rmoy = mat;

		String strQuery = "matricule='" + rmoy.getMatricule() + "',moy1='"
				+ rmoy.getMoyenne1() + "',rang1='" + rmoy.getRang1()
				+ "',moy2='" + rmoy.getMoyenne2() + "',rang2='"
				+ rmoy.getRang2() + "',moy3='" + rmoy.getMoyenne3()
				+ "',rang3='" + rmoy.getRang3() + "',moyann='"
				+ rmoy.getMoyAnn() + "',rangann='" + rmoy.getRangAnn() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + getTableArch() + " SET " + strQuery
					+ " WHERE matricule='" + rmoy.getMatricule() + "'";

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
	public MartList<RapMoyenne> getList() {
		// se connecte et rechercher
		listT = new MartList<RapMoyenne>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " + getTableArch()
					+ " ORDER BY matricule";

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
				RapMoyenne mat = new RapMoyenne(dataObj);
				dataObj = new ArrayList();
				listT.add(mat);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
			Constance.showClasseNotFoundMsg(classe, annee);
		}

		return listT;
	}

	public void setTable(String tb) {
	}

	@Override
	public RapMoyenne findObj(String matricule) {

		for (RapMoyenne mat : listT) {
			while (mat.getMatricule().equals(matricule)) {
				rmoy = mat;
				break;
			}
		}

		return rmoy;
	}

	@Override
	public boolean deleteObj(RapMoyenne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCoef(Coefficient coef) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Coefficient> getListCoef(String classe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findCoef(String matiere, String niveau) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void saveRgAn(String matricule, String rg) {
		// met a jour le rang annuellr
		String strQuery = "rangann='" + rg + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + getTableArch() + " SET " + strQuery
					+ " WHERE matricule='" + matricule + "'";
			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
