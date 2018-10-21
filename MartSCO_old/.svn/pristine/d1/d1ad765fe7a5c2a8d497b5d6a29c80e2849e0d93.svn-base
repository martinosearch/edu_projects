package connection;

import function.Constance;
import function.Fichier;
import interfacePerso.Afficher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import configurationAppli.Setting;
import accueil.AccueilSCO;
import security.Soldier;

public class MartConnection {
	/**
	 * URL de connection
	 */
	private static String url;

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

	private static Fichier fParams;

	private static String ipHote;

	private static String db;

	/**
	 * Méthode qui va retourner notre instance et la créer si elle n'existe
	 * pas...
	 * 
	 * @return
	 */
	public static Connection getInstance() {
		if (connect == null) {
			fParams = Constance.getFichierParam();
			ipHote = "localhost";
			db = "martsco";

			try {
				ipHote = fParams.findParam("ipHote");
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				db = fParams.findParam("db");
			} catch (Exception e) {
				e.printStackTrace();
			}

			// demande du choix de la base de données
			final ChooserDataBase chooser = ChooserDataBase.getInstance();
			chooser.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					fParams.writeParam(new Setting("db", chooser.getDataBase()));
					db = chooser.getDataBase();
					chooser.close();
				}
			});

			// demande du choix de la base de données
			// if (chooser.getListeBases().size() > 1) {
			chooser.setVisible(true);
			// }

			try {
				System.out.println("Initialisation de la connection" + ipHote
						+ "/ " + db);
				url = "jdbc:postgresql://" + ipHote + ":5432/" + db;
				connect = DriverManager.getConnection(url, user, passwd);

			} catch (Exception e) {
				Soldier sold = Soldier.getInstance();
				sold.setFirstTime(true);
				PostInstall pInst = new PostInstall();
				pInst.doInstall();
			}
		}

		return connect;
	}

	// returne l'état de la connection
	public static boolean getconnectivity() {
		return true;
	}

	public static void main(String[] args) {
		getInstance();
		System.out.println("Connection établie lool!");
	}

	public void load() {
		// TODO Auto-generated method stub

	}

	public static void refresh() {
		connect = null;
	}

	public static String getDataBase() {
		return db;
	}

	public static String getIpHote() {
		return ipHote;
	}

	public static boolean isServer() {
		return getIpHote().equals("localhost");
	}
}
