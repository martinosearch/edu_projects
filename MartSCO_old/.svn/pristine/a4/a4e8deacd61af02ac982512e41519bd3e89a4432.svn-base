package eleve;

import function.Constance;
import function.MartFormatter;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;

public class PromoEleveDAO extends DAO<Scolarite> {

	protected Scolarite scolarite = new Scolarite();
	private PromoEleveModel pmg;
	private MartFormatter fm;

	// Constructeurs
	public PromoEleveDAO(Connection conn) {
		super(conn);
	}

	// insert un nouveau element
	public boolean createObj(Scolarite obj) {
		// Insert une ligne dans table_promo
		this.scolarite = obj;
		String strQuery = "'" + scolarite.getMatricule() + "','"
				+ scolarite.getNiveau() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String col = "as_" + anneeCor;
			String query = "INSERT INTO table_promo_eleve(matricule_eleve,"
					+ col + ") " + "VALUES (" + strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query, state.RETURN_GENERATED_KEYS);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(Scolarite obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public boolean updateObj(Scolarite mat) {
		// corrige une ligne dans table_annee
		this.scolarite = mat;

		String col = "as_" + anneeCor;
		String strQuery = "matricule_eleve='" + scolarite.getMatricule() + "',"
				+ col + "='" + scolarite.getNiveau() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_promo_eleve SET " + strQuery
					+ " WHERE matricule_eleve='" + scolarite.getMatricule()
					+ "'";

			System.out.println(query);
			state.executeUpdate(query);
			System.out.println("Mise à jour effectuée");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public MartList getList() {
		// se connecte et rechercher
		pmg = new PromoEleveModel();
		listT = new MartList<Scolarite>();

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM table_promo_eleve";

			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			rst.beforeFirst();

			// On doit considérer juste les années scolaires antérieur
			while (rst.next()) {
				ArrayList dataObj = new ArrayList();

				String matricule = rst.getString(2);

				for (int j = 4; j <= col; j++) {
					fm = new MartFormatter();
					String colonne = rstMeta.getColumnName(j);
					fm.decomposer(colonne, '_');

					int tempAnnee = pAnnee + 2;

					try {
						tempAnnee = Integer.parseInt(fm.getDecomp(2));
					} catch (Exception e) {

					}
					// on pose alors la conditioj
					if (tempAnnee <= pAnnee) {
						// System.out
						// .println("Mise à jour faite pour =======================>>"
						// + tempAnnee);
						Scolarite sco = new Scolarite(matricule,
								rst.getString(j));

						if (tempAnnee == pAnnee) {
							sco.setNiveauActuel(rst.getString(j));
						}

						pmg.addValue(sco);
					}

				}

				listT = pmg.getListeScolarites();

			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("FIN DU CHARGEMENT");
		return listT;
	}

	@Override
	public int getOrdre(String intitule) {
		return 0;
	}

	@Override
	public Scolarite findObj(String matricule) {
		try {
			scolarite = listT.find(matricule);
		} catch (Exception e) {

		}
		return scolarite;
	}

	@Override
	public boolean deleteObj(Scolarite obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		Constance.initialize();
		DAO elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		DAO promoelvdao = DAOFactory.getDAO(DAO.PROMO_ELEVE);

		promoelvdao.load(new String(), "3ème C", 3, "2015-2016");
		elvclsdao.load(new String(), "3ème C", 3, "2015-2016");

		MartList<EleveClasse> eleves = elvclsdao.getListObt();

		for (EleveClasse elv : eleves) {
			System.out.println("Elève : " + elv.getCodeInfo() + " "
					+ elv.getNom());
			System.out.println("Scolarité 1: "
					+ ((Scolarite) promoelvdao.findObj(elv.getCodeInfo()))
							.getStatut("3ème"));

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
