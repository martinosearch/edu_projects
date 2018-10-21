package salaire;

import interfacePerso.Observable;
import interfacePerso.Observer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;
import ecolage.Operation;
import graphicsModel.MartList;

public class PayerSalaireDAO extends DAO<AgentSalaire> implements
		Observable {
	private AgentSalaire salAgent = new AgentSalaire();
	private int nbreColumn;
	private MartList<Observer> listeObserver;

	public PayerSalaireDAO(Connection conn) {
		super(conn);
		operationdao = DAOFactory.getDAO(DAO.OPERATION);
	}

	public boolean createObj(AgentSalaire obj) {
		salAgent = obj;

		if (!salAgent.getCodeInfo().equals("")) {
			try {
				Statement state = MartConnection.getInstance().createStatement(
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);

				Operation versement = salAgent.getCurrentOperation();

				String query = "INSERT INTO " + tablePayementSalaire
						+ " (nom_agent,prenom_agent,codeinfo_agent,vers"
						+ salAgent.getSerieVersement() + ",num_vers"
						+ salAgent.getSerieVersement() + ") VALUES ('"
						+ salAgent.getNom() + "','" + salAgent.getPrenom()
						+ "','" + salAgent.getCodeInfo() + "','"
						+ versement.getMontant() + "','"
						+ versement.getNumOperation() + "')";

				state.executeUpdate(query);
				System.out.println(query);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Aucun agent n'est choisi!",
					"ERREUR!", JOptionPane.ERROR_MESSAGE);
		}

		return true;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(AgentSalaire obj) {
		salAgent = obj;
		boolean exist = exists(salAgent);
		if (exist == true) {
			updateObj(salAgent);
		} else {
			// on crée l'objet
			createObj(salAgent);
		}

		Operation versement = salAgent.getCurrentOperation();
		// System.out.println("série: " + eleve.getSerieVersement());

		if (salAgent.getSerieVersement() * 2 + 4 >= nbreColumn) {
			salAgent.incrementSerial();
			String query2 = "ALTER TABLE " + tablePayementSalaire + " ADD vers"
					+ salAgent.getSerieVersement()
					+ " character varying, ADD num_vers"
					+ salAgent.getSerieVersement() + " character varying";

			salAgent.decrementSerial();
			// System.out.println(query2);
			updateDB(query2);
		}

		return true;
	}

	public boolean updateObj(AgentSalaire obj) {
		// corrige une ligne dans table_annee
		this.salAgent = obj;
		Operation versement = salAgent.getCurrentOperation();

		String strQuery = "vers" + salAgent.getSerieVersement() + "='"
				+ versement.getMontant() + "',num_vers"
				+ salAgent.getSerieVersement() + "='"
				+ versement.getNumOperation() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE " + tablePayementSalaire + " SET "
					+ strQuery + " WHERE codeinfo_agent='"
					+ salAgent.getPrimaryKey() + "'";
			System.out.println(query);
			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(AgentSalaire obj) {
		String query = "DELETE FROM tablePayementSalaire WHERE "
				+ "codeinfo_agent='" + obj.getPrimaryKey() + "'";
		updateDB(query);
		return false;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<AgentSalaire> getList() {
		// se connecte et rechercher
		listT = new MartList<AgentSalaire>();
		operationdao.load(null, null, 0, annee);

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM " + tablePayementSalaire
					+ " ORDER BY nom_agent";

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
				AgentSalaire salAgent = new AgentSalaire();
				salAgent.setCodeInfo(rst.getString(4));

				Operation vers = new Salaire();
				for (int j = 5; j <= col; j++) {
					// les colonne impaires
					if (j % 2 != 0) {
						try {
							vers = new Salaire();
							vers.setMatricule(salAgent.getCodeInfo());
							vers.setMontant(rst.getDouble(j));
							vers.setNumOperation(rst.getString(j + 1));

							if (vers.getMontant() != 0) {
								String num = vers.getNumOperation();
								Operation op = (Operation) operationdao
										.findObj(num);
								op.setMontant(vers.getMontant());

								salAgent.addOperation(op);
								salAgent.incrementSerial();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				listT.add(salAgent);
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public AgentSalaire findObj(String str) {
		AgentSalaire result = null;
		for (AgentSalaire el : listT) {
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

	@Override
	public void addObserver(Observer obs) {
		listeObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		listeObserver = new MartList<Observer>();
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : listeObserver) {
			obs.update();
		}
	}

}
