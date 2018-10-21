package function;

import javax.swing.JOptionPane;

import rapportBulletin.DocFormat;
import reference.Reference;
import agent.Responsable;
import annee.Annee;
import configurationAdmin.ConfigAdmin;
import configurationAppli.AbstractConfig;
import configurationEcole.ConfigEcole;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import examen.Examen;
import graphicsModel.MartList;

public class Constance {
	public static boolean LOGO_FOND = false, LOGO_ETS = false,
			FILIGRANE = false, PETIT_LOGO = false;
	public static int TYPE_STA_MOY_TRIM = DocFormat.STA_MIXTE;
	public static int TYPE_STA_MOY_AN = DocFormat.STA_MIXTE;
	public static int TYPE_STA_NOTE_COMPO = DocFormat.STA_MIXTE;
	public static int STA_MOY_TRIM_ROWH;
	public static int STA_MOY_AN_ROWH;
	public static int STA_NOTE_COMPO_ROWH;

	private static String CURRENT_ANNEE, examen;
	private static int trimestre;

	public static String POLICE_NOTE = null;
	public static int TYPE_DEC = 1, TYPE_RAPPORT = 0;
	public static int BULLETIN = 0, RELEVE = 1;
	private static DAO refdao, andao, clsdao, nivdao, respdao, setdao;
	public static String MY_LIFE = "";
	public static String COPY_RIGHT = "© Copy right 2017 Martino Corporation";
	public static String PUB_MSG = "Merci d'utiliser MartSCO";
	public static String DEVISE = "";
	public static String MINISTERE = "";
	public static String MINISTERE_ABR = "";
	public static String DRE, DRE_ABR;
	public static String INSPECTION, INSPECTION_ABR;
	public static Responsable DIRECTEUR_PRIM = new Responsable(),
			DIRECTEUR_COL = new Responsable(),
			DIRECTEUR_LEG = new Responsable(),
			DIRECTEUR_LET = new Responsable(), TITULAIRE = new Responsable(),
			PRESIDENT = new Responsable();

	// les références de l'ecole
	public static String INITIALE = "COMPLEXE SCOLAIRE";
	public static String NOM = "PATATI PATATA";
	public static String QUARTIER = "";
	public static String VILLE = "Lomé";
	public static String BP = "BP: 81251";
	public static String TEL = "22 22 22 22";
	// *********************************************************

	public static String NIVEAU;
	public static Examen EXAMEN;
	private static MartList<Annee> Annees;
	private static AbstractConfig conf;

	private static String deleteData = " la selection ";

	private static String photoFiltre, fet;

	private static MartFormatter fm;

	private static int type;
	private static Fichier fParams;
	private static ConfigAdmin confAdmin;

	public Constance() {
		System.out
				.println("*************************************************************");
	}

	public static void miseajour() throws Exception {
		confAdmin = new ConfigAdmin(trimestre, CURRENT_ANNEE);

		if (type == BULLETIN) {
			conf = new ConfigEcole(trimestre, CURRENT_ANNEE);
		}

		if (type == RELEVE) {
			conf = new ConfigExamen(examen);
		}

		refdao.load();
		andao.load();
		clsdao.load();
		respdao.load();
		nivdao.load();

		INITIALE = ((Reference) refdao.findObj("initiale")).getValueRef();
		NOM = ((Reference) refdao.findObj("nom")).getValueRef();
		QUARTIER = ((Reference) refdao.findObj("quartier")).getValueRef();
		VILLE = ((Reference) refdao.findObj("ville")).getValueRef();
		BP = ((Reference) refdao.findObj("bp")).getValueRef();
		TEL = ((Reference) refdao.findObj("tel")).getValueRef();
		MY_LIFE = ((Reference) refdao.findObj("register_key")).getValueRef();

		if (type == BULLETIN) {
			try {
				POLICE_NOTE = ((ConfigEcole) conf).bullConfig.getNotesFont();
				FILIGRANE = ((ConfigEcole) conf).bullConfig.filigranne();
				LOGO_FOND = ((ConfigEcole) conf).bullConfig.logoFond();
				LOGO_ETS = ((ConfigEcole) conf).bullConfig.logoEtt();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				TYPE_STA_MOY_TRIM = ((ConfigEcole) conf).bullConfig
						.getTypeStaMoyTrim();
				TYPE_STA_MOY_AN = ((ConfigEcole) conf).bullConfig
						.getTypeStaMoyAn();
				TYPE_STA_NOTE_COMPO = ((ConfigEcole) conf).bullConfig
						.getTypeStaNoteCompo();
				STA_NOTE_COMPO_ROWH = ((ConfigEcole) conf).bullConfig
						.getStaNoteCompoRowH();
				STA_MOY_TRIM_ROWH = ((ConfigEcole) conf).bullConfig
						.getStaMoyTrimRowH();
				STA_MOY_AN_ROWH = ((ConfigEcole) conf).bullConfig
						.getStaMoyAnRowH();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				DEVISE = (String) confAdmin.adminConfig.findSetting("devise");
				INSPECTION = (String) confAdmin.adminConfig
						.findSetting("inspection");
				INSPECTION_ABR = (String) confAdmin.adminConfig
						.findSetting("inspection_abr");
				MINISTERE = (String) confAdmin.adminConfig
						.findSetting("ministere");
				MINISTERE_ABR = (String) confAdmin.adminConfig
						.findSetting("ministere_abr");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (type == RELEVE) {
			POLICE_NOTE = ((ConfigExamen) conf).relConfig.getNotesFont();
			FILIGRANE = ((ConfigExamen) conf).relConfig.filigranne();
			LOGO_FOND = ((ConfigExamen) conf).relConfig.logoFond();
			LOGO_ETS = ((ConfigExamen) conf).relConfig.logoEtt();

			TYPE_STA_MOY_TRIM = ((ConfigExamen) conf).relConfig
					.getTypeStaMoyTrim();
			STA_MOY_TRIM_ROWH = ((ConfigExamen) conf).relConfig
					.getStaMoyTrimRowH();
		}

		DIRECTEUR_PRIM = new Responsable();
		try {
			DIRECTEUR_PRIM = (Responsable) respdao.findObj("directeur_PRIM_"
					+ getCor(CURRENT_ANNEE));
		} catch (Exception e) {
			DIRECTEUR_PRIM = new Responsable();
		}

		try {
			DIRECTEUR_COL = (Responsable) respdao.findObj("directeur_COL_"
					+ getCor(CURRENT_ANNEE));
		} catch (Exception e) {
			DIRECTEUR_COL = new Responsable();
		}

		try {
			DIRECTEUR_LET = (Responsable) respdao.findObj("directeur_LET_"
					+ getCor(CURRENT_ANNEE));
		} catch (Exception e) {
			DIRECTEUR_LET = new Responsable();
		}

		try {
			DIRECTEUR_LEG = (Responsable) respdao.findObj("directeur_LEG_"
					+ getCor(CURRENT_ANNEE));
		} catch (Exception e) {
			DIRECTEUR_LEG = new Responsable();
		}

		try {
			PRESIDENT = (Responsable) respdao.findObj("president_"
					+ getCor(examen));
		} catch (Exception e) {
			DIRECTEUR_LEG = new Responsable();
		}
	}

	public static void setTypeRapport(int type) {
		TYPE_RAPPORT = type;
	}

	public static int getAnPetite(String an) {
		fm = new MartFormatter();
		fm.decomposer(an, '-');
		int pAnnee = 0;
		try {
			pAnnee = Integer.parseInt(fm.getDecomp(1));
		} catch (Exception e) {

		}

		return pAnnee;
	}

	public static int getAnGrande(String an) {
		fm.decomposer(an, '-');
		int gAnnee = 0;
		try {
			gAnnee = Integer.parseInt(fm.getDecomp(2));
		} catch (Exception e) {

		}

		return gAnnee;
	}

	public static String getAnneePrec(String an) {
		int ppAn = getAnPetite(an) - 1;
		int pgAn = getAnGrande(an) - 1;
		String annnePrec = ppAn + "-" + pgAn;

		return annnePrec;
	}

	public static String getAnneeSuiv(String an) {
		int ppAn = getAnPetite(an) + 1;
		int pgAn = getAnGrande(an) + 1;
		String annneSuiv = ppAn + "-" + pgAn;

		return annneSuiv;
	}

	public static String getCor(String brute) {
		String cor = "";
		try {
			cor = brute.replaceAll("[\\é\\è\\ê]", "e").replaceAll(
					"[\\s\\-\\p{Punct}]", "_");
		} catch (Exception e) {

		}
		return cor;
	}

	public static String getCorAposthr(String brute) {
		String cor = "";
		try {
			cor = brute.replaceAll("[\\']", "''");
		} catch (Exception e) {

		}
		return cor;
	}

	public static String getAnnee() {
		return CURRENT_ANNEE;
	}

	public static void setAnnee(String an) {
		CURRENT_ANNEE = an;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public static void initialize() {
		fm = new MartFormatter();
		refdao = DAOFactory.getDAO(DAO.REFERENCE);
		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		setdao = DAOFactory.getDAO(DAO.SETTING);

		try {
			andao.load();
			Annees = andao.getListObt();

			CURRENT_ANNEE = Annees.get(0).getIntitule();
			CURRENT_ANNEE = CURRENT_ANNEE;
			trimestre = 1;
			System.out.println("Initialisation des constances: "
					+ CURRENT_ANNEE + " " + trimestre);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Vous devez créer une année- scolaire");
		}

		/*
		 * // initialisation du lookAndFeel try { String look =
		 * Design.getCurrentLookAndFeel(); UIManager.setLookAndFeel(look); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */
	}

	public static Object getCreateError(String str) {
		return "Il se peut qu'une information sur " + str + " soit incorrecte";
	}

	public static String getSuccesUpdate() {
		return "Mise à jour éffectuée avec succès!";
	}

	public static String getFailUpdate() {
		return "Echec de la mise à jour!";
	}

	public static void showClasseNotFoundMsg(String cls, String an) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "La classe " + cls
				+ " n'existe pas pour l'année- scolaire: " + an);
	}

	public static String getPhotoFiltre() {
		photoFiltre = new Fichier(getTempDir() + "params.martsco")
				.findParam("cheminPhotoFiltre");
		return photoFiltre;
	}

	public static String getFet() {
		fet = new Fichier(getTempDir() + "params.martsco")
				.findParam("cheminFet");
		return fet;
	}

	public static String getExtension() {
		return ".csv";
	}

	public static String getTempDir() {
		// TODO Auto-generated method stub
		return "c:\\temp\\";
	}

	public static String getDataTitle() {
		return "Software MartSCO ; created by Martin \r\n*********************"
				+ "**************************************************";
	}

	public static String getJarName() {
		return "MartSCO_2.0.jar";
	}

	public static void setExamen(String exam) {
		setType(RELEVE);
		examen = exam;
	}

	private static void setType(int typ) {
		type = typ;
	}

	public static String getParamExtension() {
		return ".martsco";
	}

	public static String getCommandExtension() {
		return ".bat";
	}

	public static String getImageFolder() {
		return "sysimages/";
	}

	public static String getTempImagesFolder() {
		return getTempDir() + "temp_images\\";
	}

	public static String getMyFolder() {
		return System.getProperty("user.dir") + "\\";
	}

	public static void main(String[] args) {
		new Constance();
	}

	public static void showMessage(int str) {
		System.out.println("Vous avez cherché à voir ====================>>"
				+ str);
	}

	public static String getDeviseMonaie() {
		return " F CFA";
	}

	public static Fichier getFichierParam() {
		Fichier file = new Fichier(Constance.getTempDir() + "params"
				+ Constance.getParamExtension());
		System.out.println("Le fichier de configuration existe? "
				+ file.exists());
		return file;
	}

	public static void getNotSet() {
		JOptionPane.showMessageDialog(null,
				"Cette fonctionnalité n'est pas disponible");
	}
}
