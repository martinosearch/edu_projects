package eleve;

import function.Constance;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import matiere.MatiereProg;
import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class EleveClasseDAO extends DAO<EleveClasse> {

	protected EleveClasse eleve = new EleveClasse();
	private boolean first = true;
	private static DAO instancePromoElvDao = null;

	// Constructeurs
	public EleveClasseDAO(Connection conn) {
		super(conn);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
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

			String query = "INSERT INTO " + tableBull
					+ "(nom_eleve,prenom_eleve,codeinfo_eleve) VALUES ("
					+ strQuery + ")";

			state.executeUpdate(query);
			System.out.println(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public synchronized boolean update_create(EleveClasse obj) {
		try {
			String query1 = "CREATE TABLE " + tableBull + "(id_eleve serial,"
					+ "nom_eleve character varying(64) NOT NULL,"
					+ "prenom_eleve character varying(64) NOT NULL,"
					+ "codeInfo_eleve character varying(50)" + " PRIMARY KEY)";

			updateDB(query1);
		} catch (Exception e) {
			e.printStackTrace();

		}

		try {
			String query2 = "CREATE TABLE "
					+ tablePerso
					+ "(id_matiere_prog  serial,"
					+ " matiere_prog  character varying(64) PRIMARY KEY NOT NULL,"
					+ "charge character varying(64) NOT NULL)";

			updateDB(query2);
		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			String query3 = "CREATE TABLE " + tableArch + "(id_rap serial,"
					+ "matricule character varying PRIMARY KEY NOT NULL,"
					+ "moy1 double precision,rang1 character varying,"
					+ "moy2 double precision,rang2 character varying,"
					+ "moy3 double precision,rang3 character varying,"
					+ "moyann double precision,rangann character varying)";
			updateDB(query3);
		} catch (Exception e) {
			e.printStackTrace();

		}

		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}

		return exist;
	}

	public boolean updateObj(EleveClasse mat) {
		// corrige une ligne dans table_annee
		this.eleve = mat;

		String strQuery = "nom_eleve='"
				+ Constance.getCorAposthr(eleve.getNom()) + "',prenom_eleve='"
				+ Constance.getCorAposthr(eleve.getPrenom())
				+ "',codeInfo_eleve='" + eleve.getCodeInfo() + "'";

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tableBull + " SET " + strQuery
					+ " WHERE codeinfo_eleve='" + eleve.getCodeInfo() + "'";

			System.out.println(query);
			state.executeUpdate(query);
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
		elvdao.load();
		listT = new MartList<EleveClasse>();

		if (instancePromoElvDao == null) {
			instancePromoElvDao = DAOFactory.getDAO(DAO.PROMO_ELEVE);
			instancePromoElvDao.load(new String(), classe, trimestre, annee);
		}

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			System.out.println("La tableBull est :" + tableBull);
			String query = "SELECT * FROM " + tableBull + " ORDER BY "
					+ "nom_eleve,prenom_eleve";

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

				EleveClasse elv = new EleveClasse(dataObj);
				Eleve supEleve = (Eleve) elvdao.findObj(elv.getCodeInfo());
				elv.setClasse(classe);
				elv.setNiveau(niveau);
				elv.setSexe(supEleve.getSexe());

				Scolarite sco = (Scolarite) instancePromoElvDao.findObj(elv
						.getCodeInfo());

				elv.setSco(sco);

				// definition de la sco

				dataObj = new MartList();
				listT.add(elv);
			}

			rst.close();
			state.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(EleveClasse obj) {
		String query = "DELETE FROM " + tableBull + " WHERE "
				+ "codeinfo_eleve='" + obj.getCodeInfo() + "'";
		updateDB(query);
		System.out.println(query);

		return false;
	}

	@Override
	public EleveClasse findObj(String matricule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
