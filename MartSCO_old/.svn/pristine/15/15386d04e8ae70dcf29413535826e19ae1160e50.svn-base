package rapportBulletin;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import annee.Decoupage;
import matiere.MatiereProg;
import note.InfoClasse;
import note.NoteViewer;
import note.NoteViewerBull;
import progress.Progress;
import progress.ProgressFrame;
import classe.Classe;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartStyle;
import eleve.EleveClasse;
import graphicsModel.MartList;

public abstract class FicheRstAn2 {
	protected JPanel panBut = new JPanel();

	protected String annee = "", classe = "", examen = "";
	protected int trimestre = 1;
	protected String trimstr = "";

	protected ArrayList<MatiereProg> matieres;
	protected ArrayList<EleveClasse> eleves;
	protected String htmlBody;
	protected String html = "";
	protected HistoManager histoMng;

	protected Decoupage dec;
	protected int model = 1;

	protected NoteViewer nview;
	protected DecimalFormat formatter = new DecimalFormat("00.00");
	protected File Plogo = new File("documents/images/Plogo.jpg");
	protected String titre;

	protected int num = 0;
	protected int max;
	protected String ins = "";
	protected String strEts = "";
	protected String matiere;
	protected Progress progress;
	protected MartList<String> listeEts;
	protected int progmax;
	protected DocFormat bs;
	protected ProgressFrame progressFrame;

	private DAO elvclsdao, matpdao;

	// constructeur avec param�tre
	public FicheRstAn2() {

		histoMng = new HistoManager();

		initComponent();
	}

	// creation des composantsm
	public void initComponent() {
		// les autres composants(bouton imprimer...)
		panBut.setLayout(new GridLayout(1, 3));
		MartStyle.setPadding(0);
		MartStyle.setRowheight(5);
	}

	public void createResutat(Classe cl, String an) {
		classe = cl.getIntitule();
		annee = an;

		bs = new BullFormat(trimestre, annee);
		bs.setClasse(cl);
		bs.load();

		load();
		refresh();
	}

	public void load() {
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		titre = "FICHE DE RESULTAT ANNUEL" + annee + " CLASSE: " + classe;
		titre = "RESULTAT ANNUEL";
		panBut.removeAll();
	}

	public void refresh() {
		html = "";
		htmlBody = "";

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();
		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		elvclsdao.load(new String(), classe, trimestre, annee);

		eleves = elvclsdao.getListObt();
		int nbreElv = eleves.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbreElv + progress.FIN;

		progress.getProgress(progressFrame, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************/*****

		// Chargement des notes
		nview = new NoteViewerBull();
		nview.setProgressAvancer(progress.getAvancer());

		nview.load(classe, 3, annee);

		InfoClasse infocls = nview.getInfoClasse();

		write();
	}

	public abstract void write();

}
