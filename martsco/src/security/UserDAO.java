package security;

import function.GeneralVoid;
import graphicsModel.MartList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import connection.DAO;
import connection.MartConnection;

public class UserDAO extends DAO<User> {
	private User user = new User();
	private int id = 0;

	public UserDAO(Connection conn) {
		super(conn);
	}

	public boolean createObj(User cls) {
		// Insert une ligne dans table_perso
		this.user = cls;
		String strQuery = "'" + user.getLogin() + "','" + user.getPass()
				+ "','" + user.getType() + "','" + user.getNiveau() + "','"
				+ user.getCodeInfo() + "','" + user.getEtat() + "'";

		// se connecte et insert
		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "INSERT INTO table_user "
					+ "(login_user,pass_user,type_user,niveau_user,codeinfo_user,etat_user) "
					+ " VALUES (" + strQuery + ")";

			System.out.println(query);
			state.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Données inserées");
		return true;
	}

	// met ajour
	public boolean updateObj(User obj) {
		// corrige une ligne dans table_annee
		this.user = obj;
		String strQuery = "login_user='" + user.getLogin() + "',pass_user='"
				+ user.getPass() + "',type_user='" + user.getType()
				+ "',niveau_user='" + user.getNiveau() + "',codeinfo_user='"
				+ user.getCodeInfo() + "',etat_user='" + user.getEtat() + "'";

		// se connecte et insert

		try {
			Statement state = MartConnection.getInstance().createStatement();
			String query = "UPDATE table_user SET " + strQuery + " WHERE"
					+ " login_user='" + user.getLogin() + "'";
			System.out.println(query);
			state.executeUpdate(query);
			// System.out.println("Mise à jour effectuée");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// choisit le type de mise ajour
	public boolean update_create(User obj) {
		if (exists(obj) == true) {
			updateObj(obj);
			return true;
		} else {
			createObj(obj);
			return false;
		}
	}

	public boolean deleteObj(User obj) {
		String query = "DELETE FROM table_user WHERE " + "login_user='"
				+ obj.getPrimaryKey() + "'";
		updateDB(query);

		return false;
	}

	public User findObj(String login) {
		for (User mat : listT) {
			while (mat.getLogin().equals(login)) {
				user = mat;
				break;
			}
		}
		// System.out.println("le compte renvoyée est: " + user.getLogin());
		return user;
	}

	// ******************************************************

	// methode permettant de faire des recherche
	public MartList<User> getList() {
		// se connecte et rechercher
		listT = new MartList<User>();
		try {
			Statement state = MartConnection.getInstance().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "SELECT * FROM table_user ORDER BY login_user";

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

				User cl = new User(dataObj);

				listT.add(cl);
				dataObj = new MartList();
			}
			rst.close();
			state.close();

		} catch (Exception e) {
			new GeneralVoid().doMiseAjour();
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