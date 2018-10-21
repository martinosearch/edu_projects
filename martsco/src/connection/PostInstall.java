package connection;

import org.joda.time.DateTime;

import annee.Annee;
import security.User;

public class PostInstall {
	static DAO andao, userdao;

	public static void doInstall() {
		// On crée la base de données
		FirstConnection conx = new FirstConnection();
		conx.doInstall();

		// on crée les tables dans la base
		String query1 = "CREATE TABLE liste_eleve(" + " id_eleve serial,"
				+ " nom_eleve character varying NOT NULL,"
				+ " prenom_eleve character varying NOT NULL,"
				+ " sexe_eleve character(1),"
				+ " classe_eleve character varying,"
				+ " codeinfo_eleve character varying PRIMARY KEY NOT NULL ,"
				+ " datenais_eleve character varying,"
				+ " dateins_eleve character varying,"
				+ " datesortie_eleve character varying,"
				+ " nomparent_eleve character varying,"
				+ " prenomparent_eleve character varying,"
				+ " addressparent_eleve character varying)";

		String query2 = " CREATE TABLE table_annee(" + " id_annee serial,"
				+ " annee character varying PRIMARY KEY NOT NULL" + ")";

		String query3 = " CREATE TABLE table_classe(" + " id_classe serial,"
				+ " intitule_classe character varying PRIMARY KEY NOT NULL,"
				+ " niveau_classe character varying NOT NULL" + ")";

		String query4 = " CREATE TABLE table_matiere(" + " id_matiere serial,"
				+ " intitule_matiere character varying PRIMARY KEY NOT NULL,"
				+ " type_matiere character varying NOT NULL,"
				+ " dim_matiere character varying NOT NULL" + ")";

		String query5 = "ALTER TABLE table_matiere ADD ordre_matiere integer";

		String query6 = "CREATE TABLE table_decoupage("
				+ "classe character varying PRIMARY KEY" + ")";

		String query7 = "CREATE TABLE table_ref("
				+ "id_ref serial, ref character varying  PRIMARY KEY,"
				+ " value character varying" + ");"
				+ "INSERT INTO table_ref(ref,value) VALUES ('initiale','');"
				+ "INSERT INTO table_ref(ref,value) VALUES ('nom','');"
				+ "INSERT INTO table_ref(ref,value) VALUES ('quartier','');"
				+ "INSERT INTO table_ref(ref,value) VALUES ('ville','');"
				+ "INSERT INTO table_ref(ref,value) VALUES ('bp','');"
				+ "INSERT INTO table_ref(ref,value) VALUES ('tel','');"
				+ "INSERT INTO table_ref(ref,value) VALUES ('register_key','')";

		String query8 = "CREATE TABLE liste_prof("
				+ "id_prof serial, nom_prof character varying,"
				+ " prenom_prof character varying, sexe_prof character varying(1),"
				+ " date_nais_prof character varying, date_entree_prof "
				+ "character varying,date_sortie_prof character varying, "
				+ "tel_prof character varying,"
				+ " email_prof character varying, perso_prev_prof character varying,"
				+ " tel_perso_prof character varying,"
				+ " codeinfo_prof character varying PRIMARY KEY)";

		String query9 = "CREATE TABLE charge_cours("
				+ " matiere character varying PRIMARY KEY" + ")";

		String query10 = "CREATE TABLE table_niveau("
				+ " id_niveau serial, niveau character varying  PRIMARY KEY,"
				+ " type_ens character varying " + ")";

		String query12 = " CREATE TABLE table_user(" + " id_user serial,"
				+ " login_user character varying NOT NULL PRIMARY KEY,"
				+ " pass_user character varying NOT NULL,"
				+ " type_user character varying NOT NULL,"
				+ " niveau_user integer NOT NULL,"
				+ " codeinfo_user character varying," + " etat_user integer)";

		String query13 = " CREATE TABLE table_resp(" + " id_resp serial,"
				+ " titre_resp character varying PRIMARY KEY,"
				+ " matricule_resp character varying,"
				+ " sexe_resp character varying,"
				+ " fonction_resp character varying)";

		String query14 = " CREATE TABLE table_promo_eleve("
				+ " id_eleve serial,"
				+ " matricule_eleve character varying  PRIMARY KEY,"
				+ " sexe_eleve character varying)";

		String query15 = " CREATE TABLE table_histo(id_histo serial,"
				+ " intitule_histo character varying  PRIMARY KEY,"
				+ " value_histo character varying,"
				+ "date_histo character varying,"
				+ "orient_histo character varying)";

		String query16 = " CREATE TABLE table_examen(" + " id_examen serial,"
				+ " intitule_examen character varying  PRIMARY KEY,"
				+ " mois_examen character varying,"
				+ " annee_examen character varying,"
				+ " serie_examen character varying,"
				+ " type_examen character varying)";

		String query17 = " CREATE TABLE table_ets("
				+ " id_ets serial PRIMARY KEY,"
				+ " intitule_ets character varying,"
				+ " nom_ets character varying,"
				+ " contact_ets character varying)";

		String query18 = "CREATE TABLE liste_eleve_exam(" + " id_eleve serial,"
				+ " nom_eleve character varying NOT NULL,"
				+ " prenom_eleve character varying NOT NULL,"
				+ " sexe_eleve character(1),"
				+ " datenais_eleve character varying,"
				+ " codeinfo_eleve character varying PRIMARY KEY NOT NULL,"
				+ " ets_eleve character varying)";

		String query19 = "CREATE TABLE serial_center(" + " id_eleve serial,"
				+ " intitule_serial character varying PRIMARY KEY NOT NULL,"
				+ " value_serial integer)";

		String query21 = " CREATE TABLE table_classes_eleve("
				+ " id_eleve serial,"
				+ " codeinfo_eleve character varying  PRIMARY KEY" + ")";

		// connexion pour executer
		andao = DAOFactory.getDAO(DAO.ANNEE);

		andao.updateDB(query1);
		andao.updateDB(query2);
		andao.updateDB(query3);
		andao.updateDB(query4);
		andao.updateDB(query5);
		andao.updateDB(query6);
		andao.updateDB(query7);
		andao.updateDB(query8);
		andao.updateDB(query9);
		andao.updateDB(query10);
		andao.updateDB(query12);
		andao.updateDB(query13);
		andao.updateDB(query14);
		andao.updateDB(query15);
		andao.updateDB(query16);
		andao.updateDB(query17);
		andao.updateDB(query18);
		andao.updateDB(query19);
		andao.updateDB(query21);

		User adm = new User("adm", "alpha", "ADMINISTRATEUR", 5, "");
		userdao = DAOFactory.getDAO(DAO.USER);
		userdao.update_create(adm);

		// création d'une nouvelle année
		DateTime date = new DateTime();
		int annee = date.getYear();
		int annee2 = annee + 1;

		Annee superAnnee = new Annee(annee + "-" + annee2);
		andao.load();
		andao.update_create(superAnnee);
	}

	public static void main(String[] args) {
		doInstall();
	}

}
