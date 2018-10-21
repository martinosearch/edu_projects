package note;

import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.joda.time.DateTime;

import annee.Annee;
import connection.DAO;
import connection.MartConnection;

public class SerieNoteDAO extends DAO<SerieNote> {
	private SerieNote serie;

	public SerieNoteDAO(Connection conn) {
		super(conn);

	}

	public boolean createObj(SerieNote obj) {
		this.serie = obj;
		String strQuery = "'" + serie.getPrimaryKey() + "','"
				+ serie.getValueSerie() + "','" + serie.getDate() + "','"
				+ serie.getClasse() + "','" + serie.getMatiere() + "','"
				+ serie.getTrimestre() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "INSERT INTO "
					+ tableSerieNote
					+ "(code_serie,value_serie,date_serie,classe_serie,matiere_serie,trimestre_serie) VALUES ("
					+ strQuery + ")";

			state.executeUpdate(query);
			System.out.println(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// methode qui invoque soit l'insertion ou soit la mise ajour
	public boolean update_create(SerieNote not) {
		if (exists(not)) {
			updateObj(not);
		} else {
			createObj(not);
		}
		return false;
	}

	public boolean updateObj(SerieNote obj) {
		// insert une note
		this.serie = obj;
		String strQuery = "";

		// il faudra aussi situer le trimestre; donc
		strQuery = "code_serie='" + serie.getPrimaryKey() + "',"
				+ "value_serie='" + serie.getValueSerie() + "',"
				+ "date_serie='" + serie.getDate() + "'," + "classe_serie='"
				+ serie.getClasse() + "'," + "matiere_serie='"
				+ serie.getMatiere() + "'," + "trimestre_serie='"
				+ serie.getTrimestre() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "UPDATE " + tableSerieNote + " SET " + strQuery
					+ " WHERE code_serie='" + serie.getPrimaryKey() + "'";

			System.out.println(query);

			state.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteObj(Annee obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public SerieNote findObj(int id) {
		return null;
	}

	public void setTable(String tb) {
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<SerieNote> getList() {
		// se connecte et rechercher
		listT = new MartList<SerieNote>();

		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "";

			query = "SELECT code_serie, value_serie, date_serie, classe_serie, matiere_serie,"
					+ " trimestre_serie FROM "
					+ tableSerieNote
					+ " ORDER BY code_serie";

			System.out.println("==============================>>" + query);
			ResultSet rst = state.executeQuery(query);
			ResultSetMetaData rstMeta = rst.getMetaData();

			rst.last();
			int length = rst.getRow();
			int col = rstMeta.getColumnCount();
			int g = 0;

			rst.beforeFirst();

			while (rst.next()) {
				String id = rst.getString(1);
				SerieNote serie = new SerieNote(id);
				serie.setValueSerie(rst.getString(2));

				try {
					serie.setDate(new DateTime(rst.getString(3)));
				} catch (Exception e) {
					e.printStackTrace();
				}

				serie.setClasse(rst.getString(4));
				serie.setMatiere(rst.getString(5));
				serie.setTrimestre(rst.getInt(6));

				if (serie.getClasse().equals(classe)
						&& serie.getMatiere().equals(matiere)
						&& serie.getTrimestre() == trimestre) {
					listT.add(serie);
				}
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public boolean deleteObj(SerieNote obj) {
		String query = "DELETE FROM " + tableSerieNote + " WHERE "
				+ "code_serie='" + obj.getPrimaryKey() + "'";
		updateDB(query);
		return false;
	}

	@Override
	public SerieNote findObj(String matri) {
		for (SerieNote n : listT) {
			while (n.getPrimaryKey().equals(matri)) {
				serie = n;
				break;
			}
		}

		return serie;
	}

	public SerieNote findObj(String classe, String annee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}
