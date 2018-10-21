package candidat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import matiere.MatiereProg;
import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;
import eleve.Eleve;
import eleve.EleveClasse;
import examen.NumTableGenerator;
import function.Constance;
import graphicsModel.MartList;

public class CdtPersoDAO extends DAO<EleveClasse> {

	protected EleveClasse eleve = new EleveClasse();
	DAO cdtdao;

	// Constructeurs
	public CdtPersoDAO(Connection conn) {
		super(conn);
		cdtdao = DAOFactory.getDAO(DAO.CANDIDAT);
	}

	// insert un nouveau element
	public synchronized boolean createObj(EleveClasse obj) {
		// Insert une ligne dans table_perso
		this.eleve = obj;

		String strQuery = "'" + Constance.getCorAposthr(eleve.getNom()) + "','"
				+ Constance.getCorAposthr(eleve.getPrenom()) + "','"
				+ eleve.getCodeInfo() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "INSERT INTO " + tableExam
					+ "(nom_eleve,prenom_eleve,codeInfo_eleve) " + "VALUES ("
					+ strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public synchronized boolean update_create(EleveClasse obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public synchronized boolean updateObj(EleveClasse mat) {
		// corrige une ligne dans table_annee
		this.eleve = mat;

		String strQuery = "nom_eleve='"
				+ Constance.getCorAposthr(eleve.getNom()) + "',prenom_eleve='"
				+ Constance.getCorAposthr(eleve.getPrenom())
				+ "',codeInfo_eleve='" + eleve.getCodeInfo() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tableExam + " SET " + strQuery
					+ " WHERE codeInfo_eleve='" + eleve.getCodeInfo() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			// System.out.println("Mise � jour effectu�e");
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
		cdtdao.load();
		listT = new MartList<EleveClasse>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM " + tableExam + " ORDER BY "
					+ "nom_eleve,prenom_eleve";

			System.out.println(query);
			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			while (rst.next()) {
				String matricule = (String) rst.getObject(4);

				Eleve superEleve = (Eleve) cdtdao.findObj(matricule);

				EleveClasse elv = new EleveClasse(matricule);
				elv.setNom(superEleve.getNom());
				elv.setPrenom(superEleve.getPrenom());
				elv.setSexe(superEleve.getSexe());
				elv.setEts(superEleve.getEts());

				// System.out.println("Recherche: =======================>>"
				// + matricule + " " + superEleve.getEts());
				listT.add(elv);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// numéro de table
		NumTableGenerator gen = new NumTableGenerator();
		gen.setListe(listT);

		return gen.getListe();
	}

	@Override
	public boolean deleteObj(EleveClasse obj) {
		String query = "DELETE FROM " + tableExam + " WHERE "
				+ "codeinfo_eleve='" + obj.getCodeInfo() + "'";
		updateDB(query);
		System.out.println(query);

		return false;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EleveClasse findObj(String intitule) {
		// TODO Auto-generated method stub
		return null;
	}
}
