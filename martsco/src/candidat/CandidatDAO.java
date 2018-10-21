package candidat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;

import matiere.MatiereProg;
import connection.DAO;
import connection.MartConnection;
import eleve.Eleve;
import eleve.Scolarite;
import function.Constance;
import graphicsModel.MartList;

public class CandidatDAO extends DAO<Eleve> {

	protected Eleve eleve = new Eleve();

	// Constructeurs
	public CandidatDAO(Connection conn) {
		super(conn);
	}

	// insert un nouveau element
	public synchronized boolean createObj(Eleve obj) {
		// Insert une ligne dans table_perso

		this.eleve = obj;
		String strQuery = "'" + Constance.getCorAposthr(obj.getNom()) + "','"
				+ Constance.getCorAposthr(obj.getPrenom()) + "','"
				+ obj.getSexe() + "','"
				+ Constance.getCorAposthr(obj.getDateNais().toString()) + "','"
				+ obj.getCodeInfo() + "','"
				+ Constance.getCorAposthr(obj.getEts()) + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "INSERT INTO liste_eleve_exam(nom_eleve,prenom_eleve,sexe_eleve,"
					+ "datenais_eleve,codeInfo_eleve,ets_eleve) "
					+ "VALUES ("
					+ strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public synchronized boolean update_create(final Eleve obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public synchronized boolean updateObj(Eleve obj) {
		// corrige une ligne dans table_annee
		this.eleve = obj;

		String strQuery = "nom_eleve='" + Constance.getCorAposthr(obj.getNom())
				+ "',prenom_eleve='" + Constance.getCorAposthr(obj.getPrenom())
				+ "',sexe_eleve='" + obj.getSexe() + "',datenais_eleve='"
				+ obj.getDateNais() + "',codeInfo_eleve='" + obj.getCodeInfo()
				+ "',ets_eleve='" + Constance.getCorAposthr(obj.getEts()) + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE liste_eleve_exam SET " + strQuery
					+ " WHERE codeInfo_eleve='" + obj.getCodeInfo() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			// System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(MatiereProg obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public MartList getList() {
		// se connecte et rechercher
		listT = new MartList<Eleve>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM liste_eleve_exam ORDER BY "
					+ "nom_eleve,prenom_eleve";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				Eleve elv = new Eleve();
				elv.setNom(rst.getString(2));
				elv.setPrenom(rst.getString(3));
				elv.setSexe(rst.getString(4));

				try {
					elv.setDateNais(new DateTime(rst.getString(5)));
				} catch (Exception e) {
					// e.printStackTrace();
					elv.setDateNais(new DateTime());
				}

				elv.setCodeInfo(rst.getString(6));
				elv.setEts(rst.getString(7));
				elv.initPrimaryKey();

				listT.add(elv);

				// System.out.println("=====================================>>"
				// + rst.getString(7));
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(Eleve obj) {
		String query = "DELETE FROM liste_eleve_exam WHERE "
				+ "codeinfo_eleve='" + obj.getCodeInfo() + "'";
		updateDB(query);
		System.out.println(query);

		return false;
	}

	@Override
	public Eleve findObj(String matricule) {
		for (Eleve elv : listT) {
			while (elv.getCodeInfo().equals(matricule)) {
				eleve = elv;
				break;
			}
		}
		return eleve;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
