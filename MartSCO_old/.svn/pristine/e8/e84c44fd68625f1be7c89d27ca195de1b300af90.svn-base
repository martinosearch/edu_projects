package ecolage;

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

public class VersementEcolageDAO extends DAO<EleveEcolage> {
	private EleveEcolage eleve = new EleveEcolage();
	private int nbreColumn;

	public VersementEcolageDAO(Connection conn) {
		super(conn);
		operationdao = DAOFactory.getDAO(DAO.OPERATION);
	}

	public boolean createObj(EleveEcolage obj) {

		// Insert une ligne dans table_ecolage
		eleve = obj;

		// se connecte et insert
		if (!eleve.getCodeInfo().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				Operation versement = eleve.getCurrentOperation();

				String query = "INSERT INTO "
						+ tableEcolage
						+ " (nom_eleve,prenom_eleve,codeinfo_eleve,classe_eleve,vers"
						+ eleve.getSerieVersement() + ",num_vers"
						+ eleve.getSerieVersement() + ") VALUES ('"
						+ Constance.getCorAposthr(eleve.getNom()) + "','"
						+ Constance.getCorAposthr(eleve.getPrenom()) + "','"
						+ eleve.getCodeInfo() + "','" + eleve.getClasse()
						+ "','" + versement.getMontant() + "','"
						+ versement.getNumOperation() + "')";

				state.executeUpdate(query);
				System.out.println(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucun élève n'est choisi!",
					"ERREUR!", JOptionPane.ERROR_MESSAGE);
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(EleveEcolage obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			// on crée l'objet
			createObj(obj);
		}

		Operation versement = eleve.getCurrentOperation();
		// System.out.println("série: " + eleve.getSerieVersement());

		if (eleve.getSerieVersement() * 2 + 5 >= nbreColumn) {
			eleve.incrementSerial();
			String query2 = "ALTER TABLE " + tableEcolage + " ADD vers"
					+ eleve.getSerieVersement()
					+ " character varying, ADD num_vers"
					+ eleve.getSerieVersement() + " character varying";

			eleve.decrementSerial();
			// System.out.println(query2);
			updateDB(query2);
		}

		return true;
	}

	public boolean updateObj(EleveEcolage obj) {
		// corrige une ligne dans table_annee
		this.eleve = obj;
		Operation versement = eleve.getCurrentOperation();

		String strQuery = "vers" + eleve.getSerieVersement() + "='"
				+ versement.getMontant() + "',num_vers"
				+ eleve.getSerieVersement() + "='"
				+ versement.getNumOperation() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tableEcolage + " SET " + strQuery
					+ " WHERE codeinfo_eleve='" + eleve.getPrimaryKey() + "'";
			System.out.println(query);
			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(EleveEcolage obj) {
		String query = "DELETE FROM table_annee WHERE " + "annee='"
				+ obj.getPrimaryKey() + "'";
		updateDB(query);
		return false;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<EleveEcolage> getList() {
		// se connecte et rechercher
		listT = new MartList<EleveEcolage>();
		operationdao.load(null, null, 0, annee);
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " + tableEcolage
					+ " ORDER BY nom_eleve";

			System.out.println(query);
			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();

			// on profite pour le comptage du nbre de versements
			nbreColumn = col;

			rst.beforeFirst();

			while (rst.next()) {
				EleveEcolage eleve = new EleveEcolage();
				eleve.setCodeInfo(rst.getString(4));
				eleve.setClasse(rst.getString(5));

				Operation vers = new VersementEcolage();
				for (int j = 6; j <= col; j++) {
					if (j % 2 == 0) {
						try {
							vers = new VersementEcolage();
							vers.setMatricule(eleve.getCodeInfo());
							vers.setMontant(rst.getDouble(j));
							vers.setNumOperation(rst.getString(j + 1));

							if (vers.getMontant() != 0) {
								String num = vers.getNumOperation();
								Operation op = (Operation) operationdao
										.findObj(num);
								op.setMontant(vers.getMontant());

								eleve.addOperation(op);
								eleve.incrementSerial();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				listT.add(eleve);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public EleveEcolage findObj(String str) {
		EleveEcolage result = null;
		for (EleveEcolage el : listT) {
			while ((el.getCodeInfo()).equals(str)) {
				result = el;
				break;
			}
		}

		return result;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
