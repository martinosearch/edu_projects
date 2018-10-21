package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import function.Constance;
import function.Fichier;

public class FirstConnection {
	/**
	 * URL de connection
	 */
	private static String url = "jdbc:postgresql://localhost:5432/postgres";

	/**
	 * Nom du user
	 */
	private static String user = "postgres";

	/**
	 * Mot de passe du user
	 */
	private static String passwd = "martin90";

	/**
	 * Objet Connection
	 */
	private static Connection connect;

	/**
	 * Méthode qui va retourner notre instance et la créer si elle n'existe
	 * pas...
	 * 
	 * @return
	 */
	public static Connection getInstance() {

		if (connect == null) {
			try {
				connect = DriverManager.getConnection(url, user, passwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connect;
	}

	// returne l'�tat de la connection
	public static boolean getconnectivity() {
		return true;
	}

	// pour tester
	public void doInstall() {
		Connection conx = FirstConnection.getInstance();
		Statement state;
		String query1 = "CREATE DATABASE " + MartConnection.getDataBase()
				+ " WITH OWNER = postgres ENCODING = 'UTF8'";

		try {
			state = conx.createStatement();
			state.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
