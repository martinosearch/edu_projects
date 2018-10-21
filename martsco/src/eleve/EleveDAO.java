package eleve;

import function.Constance;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class EleveDAO extends DAO<Eleve> {

	private Eleve eleve = new Eleve();

	public EleveDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	public boolean createObj(Eleve elv) {
		// Insert une ligne dans table_perso
		this.eleve = elv;
		String strQuery = "'" + Constance.getCorAposthr(eleve.getNom()) + "','"
				+ Constance.getCorAposthr(eleve.getPrenom()) + "','"
				+ eleve.getSexe() + "','"
				+ Constance.getCorAposthr(eleve.getClasse()) + "','"
				+ eleve.getCodeInfo() + "','" + eleve.getDateNais() + "','"
				+ eleve.getDateEntree() + "','" + eleve.getDateSortie() + "','"
				+ Constance.getCorAposthr(eleve.getPersoPrev()) + "','"
				+ Constance.getCorAposthr(eleve.getProfessionPersoPrev())
				+ "','" + Constance.getCorAposthr(eleve.getTelPersoPrev())
				+ "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO liste_eleve (nom_eleve,prenom_eleve,sexe_eleve,classe_eleve,"
					+ "codeinfo_eleve,datenais_eleve,dateins_eleve,datesortie_eleve,nomparent_eleve, "
					+ "prenomparent_eleve,addressparent_eleve)"
					+ " VALUES ("
					+ strQuery + ")";

			System.out.println(query);

			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print("Données inserées avec succès");

		return true;
	}

	public boolean updateObj(Eleve elv) {
		this.eleve = elv;
		String strQuery = "nom_eleve='"
				+ Constance.getCorAposthr(eleve.getNom()) + "',"
				+ "prenom_eleve='" + Constance.getCorAposthr(eleve.getPrenom())
				+ "'," + "sexe_eleve='" + eleve.getSexe() + "',"
				+ "classe_eleve='" + eleve.getClasse() + "',"
				+ "datenais_eleve='" + eleve.getDateNais() + "',"
				+ "dateins_eleve='" + eleve.getDateEntree() + "',"
				+ "datesortie_eleve='" + eleve.getDateSortie() + "',"
				+ "nomparent_eleve='"
				+ Constance.getCorAposthr(eleve.getPersoPrev()) + "',"
				+ "prenomparent_eleve='"
				+ Constance.getCorAposthr(eleve.getProfessionPersoPrev())
				+ "'," + "addressparent_eleve='"
				+ Constance.getCorAposthr(eleve.getTelPersoPrev()) + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE liste_eleve SET " + strQuery
					+ " WHERE codeinfo_eleve='" + eleve.getCodeInfo() + "'";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public Eleve findObj(String matricule) {
		for (Eleve elv : listT) {
			while (elv.getCodeInfo().equals(matricule)) {
				eleve = elv;
				break;
			}
		}
		return eleve;
	}

	public boolean update_create(Eleve obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Eleve> getList() {
		// se connecte et rechercher
		listT = new MartList<Eleve>();

		long time1 = System.currentTimeMillis();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM liste_eleve ORDER BY nom_eleve, prenom_eleve";

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

				Eleve elv = new Eleve(dataObj);

				dataObj = new MartList();

				listT.add(elv);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		long time2 = System.currentTimeMillis();
		System.out
				.println("le temps est de : " + (time2 - time1) * 0.001 + "s");
		return listT;
	}

	public boolean deleteObj(Eleve obj) {
		String query = "DELETE FROM liste_eleve WHERE " + "codeinfo_eleve='"
				+ obj.getCodeInfo() + "'";
		updateDB(query);
		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		DAO dao = DAOFactory.getDAO(ELEVE);
		dao.load();
	}
}
