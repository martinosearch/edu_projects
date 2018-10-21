package classe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DAO;
import connection.MartConnection;
import eleve.Scolarite;
import function.Constance;
import graphicsModel.MartList;

public class ClasseDAO extends DAO<Classe> {
	private Classe classe = new Classe();

	public ClasseDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(Classe cls) {
		// Insert une ligne dans table_perso
		this.classe = cls;
		String strQuery = "'" + classe.getIntitule() + "','"
				+ classe.getNiveau() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO table_classe (intitule_classe,niveau_classe) "
					+ " VALUES (" + strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query, state.RETURN_GENERATED_KEYS);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println("Données inserées");
		return true;
	}

	// met ajour
	public boolean updateObj(Classe obj) {
		// System.out.println("Mise ajour effectuée");
		return false;
	}

	// choisit le type de mise ajour
	public boolean update_create(Classe obj) {
		boolean exist = exists(obj);
		if (exist == true) {
			updateObj(obj);
		} else {
			createObj(obj);
		}
		return exist;
	}

	public boolean deleteObj(Classe obj) {
		String query = "DELETE FROM table_classe WHERE " + "intitule_classe='"
				+ obj.getIntitule() + "'";
		updateDB(query);
		return false;
	}

	public Classe findObj(String intitule) {
		for (Classe mat : listT) {
			while (mat.getIntitule().equals(intitule)) {
				classe = mat;
				break;
			}
		}
		return classe;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<Classe> getList() {
		// se connecte et rechercher
		listT = new MartList<Classe>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_classe ORDER BY "
					+ "intitule_classe";

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
				Classe cl = new Classe(dataObj);

				listT.add(cl);
				dataObj = new MartList();
			}
			rst.close();
			state.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listT;
	}

	@Override
	public int getOrdre(String intitule) {
		// TODO Auto-generated method stub
		return 0;
	}

}