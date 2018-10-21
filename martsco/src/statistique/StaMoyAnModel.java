package statistique;

import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;

import note.InfoClasse;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerBull;
import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import annee.Decoupage;
import classe.Classe;
import classe.Niveau;
import configurationAdmin.ConfigAdmin;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import eleve.EleveClasse;
import function.Constance;
import function.StaData;
import function.Statistician;
import function.Statistician.MoySta;
import graphicsModel.MartList;

public abstract class StaMoyAnModel extends AbstractModel {
	String htmlBody;
	String html;
	int nbreSection = 0, nbreClasses = 0, progmax = 0, maxtrim, effectif = 0;
	protected MartEditorPane editor;

	protected double mpass6eme, mpass5eme, mpass4eme, mpass2nde_A4,
			mpass2nde_CD;
	protected double mpass = 10;

	protected NoteViewer nview;
	protected InfoClasse infocls;
	protected ArrayList<EleveClasse> Eleves;
	protected MartList<Niveau> Niveaux;
	protected DocFormat bs;
	protected JTable tabClasses;
	protected Statistician staman;

	protected DecimalFormat formatter = new DecimalFormat("00.00");
	protected HistoManager histoMng;

	protected StaMoyAn writer;
	protected ConfigAdmin conf;
	protected Progress progress;
	protected MartList<MoySta> stamoy;
	protected MoySta defaultMoySta;
	protected MoySta sta6eme;
	protected MoySta sta5eme;
	protected MoySta sta4eme;
	protected MoySta sta3eme;
	protected MoySta sta2nde_CD;
	protected MoySta sta2nde_A4;
	protected MoySta sta1ere_A4;
	protected MoySta sta1ere_C4;
	protected MoySta sta1ere_D;
	protected MoySta staTle_A4;
	protected MoySta staTle_C4;
	protected MoySta staTle_D;
	private ConfigClasse confClasse;
	private ProgressFrame pFrame;

	public StaMoyAnModel() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		notedao = DAOFactory.getDAO(DAO.NOTE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		rapmoydao = DAOFactory.getDAO(DAO.RAPMOY);
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);

		// les chargements ne dépendant d'aucun paramètre
		elvdao.load();
		matdao.load();
		ensdao.load();
		clsdao.load();
		nivdao.load();

		histoMng = new HistoManager();
	}

	public void valider(int tpe) {
		this.type = tpe;

		// paramètres généraux
		for (Observer obs : listObserver) {
			if (obs instanceof StaMoyAn) {
				writer = (StaMoyAn) obs;
			}
		}

		pFrame = new ProgressFrame();
		progress = new Progress();
		// System.out.println(writer.getTitle());
		progress.getLoading(pFrame, "Chargement des données");

		nview = new NoteViewerBull();
		staman = new Statistician();// instantier le statisticien

		if (type == 1) {
			// creation des statistiques
			new Thread(new Runnable() {
				public void run() {
					createSta();
				}
			}).start();
		}
	}

	public void createSta() {
		html = "";
		htmlBody = "";

		tabClasses = tableChoix;
		nbreClasses = tabClasses.getRowCount();

		// ****************on crée en même temps les pages*******************
		editor = new MartEditorPane(MartEditorPane.MATHS_DOCUMENT);
		MartStyle.setPadding(0);
		Constance.showMessage(Constance.STA_MOY_AN_ROWH);

		MartStyle.setRowheight(Constance.STA_MOY_AN_ROWH);
		editor.setStyle(MartStyle.DOCUMENTS);
		// ****************************************************************

		// initialisation des moyennes de passage
		conf = new ConfigAdmin(trimestre, annee);
		try {
			mpass6eme = Double.parseDouble((String) conf.adminConfig
					.findSetting("moyenne_passage_6ème"));
		} catch (Exception e) {

		}

		try {
			mpass5eme = Double.parseDouble((String) conf.adminConfig
					.findSetting("moyenne_passage_5ème"));
		} catch (Exception e) {
		}

		try {
			mpass4eme = Double.parseDouble((String) conf.adminConfig
					.findSetting("moyenne_passage_4ème"));
		} catch (Exception e) {
		}

		try {
			mpass2nde_A4 = Double.parseDouble((String) conf.adminConfig
					.findSetting("moyenne_passage_2nde_A4"));
		} catch (Exception e) {
		}

		try {
			mpass2nde_CD = Double.parseDouble((String) conf.adminConfig
					.findSetting("moyenne_passage_2nde_CD"));
		} catch (Exception e) {
		}

		Niveaux = new MartList<Niveau>();

		// créer la liste des niveaux
		for (int i = 0; i < nbreClasses; i++) {// POUR PARCOURRIR LES CLASSES
			classe = (String) tabClasses.getValueAt(i, 1);
			Classe classe1 = (Classe) clsdao.findObj(classe);
			String niveau1 = classe1.getNiveau();

			Niveau niveau = (Niveau) nivdao.findObj(niveau1);
			Niveaux.smartAdd(niveau);

		}// fin de création de la listes des niveau

		/* la barre de progression définitive */
		progmax = (nbreClasses * Niveaux.size()) + 1;
		progress.getProgress(pFrame, 0, progmax);

		for (Niveau niv : Niveaux) {
			if ((niv.getIntitule()).equals("6ème"))
				mpass = mpass6eme;
			if ((niv.getIntitule()).equals("5ème"))
				mpass = mpass5eme;
			if ((niv.getIntitule()).equals("4ème"))
				mpass = mpass4eme;
			if ((niv.getIntitule()).equals("2nde A4"))
				mpass = mpass2nde_A4;
			if ((niv.getIntitule()).equals("2nde CD"))
				mpass = mpass2nde_CD;

			staman.setMPass(mpass);// spécifition de la moyenne de passage

			for (int i = 0; i < nbreClasses; i++) {
				String classe = (String) tabClasses.getValueAt(i, 1);
				Classe superClasse = (Classe) clsdao.findObj(classe);
				classe = superClasse.getIntitule();

				confClasse = new ConfigClasse(superClasse, annee);
				Decoupage dec = confClasse.persoClasse.getDecoupage();

				trimestre = dec.getMax();// le maximun du découpage

				bs = new BullFormat(trimestre, annee);// ceci pour initialiser
														// le bs
				bs.setClasse(superClasse);// pour le Bullsets

				// si la classe est dans le niveau actuel
				if (superClasse.getNiveau().equals(niv.getIntitule())) {

					Eleves = new ArrayList<EleveClasse>();

					elvclsdao.load(new String(), classe, trimestre, annee);

					Eleves = elvclsdao.getListObt();

					int nbreElv = Eleves.size();

					nview.load(classe, trimestre, annee);
					infocls = nview.getInfoClasse();
					effectif = infocls.getEff();
					staman.addClasse(superClasse.getNiveau());

					// Ajout des moyennes générales de élève du niveau considéré
					for (EleveClasse elv : Eleves) {
						Moyenne moyG = nview.getMoyenne(elv);
						double moytrim = moyG.getMoyGen();

						StaData data = new StaData(superClasse.getNiveau(),
								moytrim, elv.getSexe(), moyG.hasCompose());

						staman.setDataMoy(data);
					}
				}// fin si la classe est dans le niveau

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}// fin for classe
		}// for Niveau

		// *******************on commence par éditer**************************
		stamoy = staman.getResultMoy();
		defaultMoySta = staman.new MoySta();
		sta6eme = stamoy.find("6ème");
		sta5eme = stamoy.find("5ème");
		sta4eme = stamoy.find("4ème");
		sta3eme = stamoy.find("3ème");
		sta2nde_CD = stamoy.find("2nde CD");
		sta2nde_A4 = stamoy.find("2nde A4");
		sta1ere_A4 = stamoy.find("1ère A4");
		sta1ere_C4 = stamoy.find("1ère C4");
		sta1ere_D = stamoy.find("1ère D");
		staTle_A4 = stamoy.find("Tle A4");
		staTle_C4 = stamoy.find("Tle C4");
		staTle_D = stamoy.find("Tle D");

		if (sta6eme == null) {
			sta6eme = defaultMoySta;
		}

		if (sta5eme == null) {
			sta5eme = defaultMoySta;
		}

		if (sta4eme == null) {
			sta4eme = defaultMoySta;
		}

		if (sta3eme == null) {
			sta3eme = defaultMoySta;
		}

		if (sta2nde_CD == null) {
			sta2nde_CD = defaultMoySta;
		}

		if (sta2nde_A4 == null) {
			sta2nde_A4 = defaultMoySta;
		}

		if (sta1ere_A4 == null) {
			sta1ere_A4 = defaultMoySta;
		}

		if (sta1ere_C4 == null) {
			sta1ere_C4 = defaultMoySta;
		}

		if (sta1ere_D == null) {
			sta1ere_D = defaultMoySta;
		}

		if (staTle_A4 == null) {
			staTle_A4 = defaultMoySta;
		}

		if (staTle_C4 == null) {
			staTle_C4 = defaultMoySta;
		}

		if (staTle_D == null) {
			staTle_D = defaultMoySta;
		}
		write();

		pFrame.close();
	}

	public abstract void write();

	public int getNbreBull() {
		return effectif;
	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}
}
