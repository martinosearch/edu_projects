package planning;

import graphicsModel.MartList;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class planningWriterModel {

	private static String annee;
	private static DAO actdao, setdao;
	private static int nbreJour;
	private static int nbreHeure;
	private static MartList<String> listeJour, listeHeure;
	private static MartList<Activite> listeActivites;
	private static MartList<String> listClasse = new MartList<String>();
	private static MartList<String> listMatieres = new MartList<String>();

	public static void main(String[] args) {
		createPlanning("2016-2017");
	}

	private static void createPlanning(String an) {
		annee = an;
		actdao = DAOFactory.getDAO(DAO.ACTIVITE);
		setdao = DAOFactory.getDAO(DAO.SETTING);

		setdao.load(null, null, 0, annee);
		actdao.load(null, null, 0, annee);

		nbreJour = Integer.parseInt((String) ((Setting) setdao
				.findObj("nbre_jour")).getAttribut());

		nbreHeure = Integer.parseInt((String) ((Setting) setdao
				.findObj("nbre_heure")).getAttribut());

		// On recupère la liste de jour et des heures
		listeJour = new MartList<String>();
		listeHeure = new MartList<String>();

		for (int i = 0; i < nbreJour; i++) {
			listeJour.add((String) ((Setting) setdao.findObj("planning_Jour "
					+ (i + 1))).getAttribut());
		}

		for (int i = 0; i < nbreHeure; i++) {
			listeHeure.add((String) ((Setting) setdao.findObj("planning_Heure "
					+ (i + 1))).getAttribut());
		}

		// liste des activites
		listeActivites = actdao.getListObt();

		// liste des classes en jeux
		for (Activite act : listeActivites) {
			String classe = act.getClasse();
			MartList<String> tempListe = new MartList<String>();
			for (String str : listClasse) {
				if (str.equals(classe)) {
					tempListe.add(str);
				}
			}

			listClasse = tempListe;
		}

		// liste des matieres en jeux
		for (Activite act : listeActivites) {
			String matiere = act.getMatiere();
			MartList<String> tempListe = new MartList<String>();
			for (String str : listMatieres) {
				if (str.equals(matiere)) {
					tempListe.add(str);
				}
			}

			listMatieres = tempListe;
		}

	}

}
