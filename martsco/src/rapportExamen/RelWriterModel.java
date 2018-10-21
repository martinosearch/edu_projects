package rapportExamen;

import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JTable;

import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.NoteViewer;
import note.NoteViewerExam;
import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import abstractObject.Rapport;
import configurationAppli.AbstractConfig;
import configurationEcole.ConfigEcole;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import eleve.Eleve;
import eleve.EleveClasse;
import examen.Examen;
import graphicsModel.MartList;

public abstract class RelWriterModel extends AbstractModel {
	protected String htmlBody;
	protected String html;
	protected RelWriter writer;
	protected int nbreBull = 0, progmax = 0, maxtrim, effectif = 0;
	protected Progress progress;
	protected MartEditorPane editor;

	protected NoteViewer nview;
	protected InfoClasse infocls;
	protected InfoNote infonote;
	protected MartList<EleveClasse> listeEleveProg;
	protected MartList<MatiereProg> Matieres;
	protected DocFormat bs;
	protected JTable tabElv;
	protected int bullCount;

	protected File Plogo;
	protected File photo;
	protected HistoManager histoMng;
	protected Examen superExamen;
	protected DecimalFormat formatter;
	private Thread traitement;
	protected ProgressFrame pframe;
	protected MartList<EleveClasse> listeChoix;
	protected String tabNote;
	protected AbstractConfig conf;
	MartList<String> etablissements;
	protected EleveClasse eleve;
	protected static int model = 1;

	public RelWriterModel() {
		histoMng = new HistoManager();

	}

	public abstract void write();

	public void valider(int tpe) {
		// ********debut de la barre de progression***************
		Thread chg = new Thread(new Runnable() {
			public void run() {
				pframe = new ProgressFrame();
				progress = new Progress();
				progress.getLoading(pframe, "Chargement des données en cours");
			}
		});
		chg.start();

		if (getTypeRapport() == Rapport.EVAL_TRIMESTRIELLE) {
			notedao = DAOFactory.getDAO(DAO.NOTE);
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
			elvdao = DAOFactory.getDAO(DAO.ELEVE);
			elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);

			elvclsdao.load(null, classe, trimestre, annee);
			matpdao.load(null, classe, trimestre, annee);

			nview = new NoteViewerExam();
			nview.load(getEvaluation(), classe, trimestre, annee);

			if (superNiveau.getTypeEns().equals("PRIM")) {
				model = Rapport.MODEL_PRIM;
			} else {
				model = Rapport.MODEL_SECOND;
			}

			bs = new BullFormat(trimestre, annee);
			bs.setModel(model);
			bs.setTypeRapport(typeRapport);
			bs.setClasse(superClasse);
			bs.load();

			conf = new ConfigEcole(trimestre, annee);
		} else {
			notedao = DAOFactory.getDAO(DAO.NOTE_EXAM);
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
			elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
			examdao = DAOFactory.getDAO(DAO.EXAMEN);
			elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);

			examdao.load();
			elvclsdao.loadExam(examen);
			matpdao.loadExam(examen);

			nview = new NoteViewerExam();
			nview.load(examen);

			superExamen = (Examen) examdao.findObj(examen);
			model = superExamen.getModelRap();// pour définir le

			// model
			bs = new RelFormat(examen);
			bs.setModel(model);
			bs.load();

			conf = new ConfigExamen(examen);
			etablissements = ((ConfigExamen) conf).relConfig.getEtsPerso();
		}

		matdao = DAOFactory.getDAO(DAO.MATIERE);
		ensdao = DAOFactory.getDAO(DAO.AGENT);

		// les chargements ne dépendant d'aucun param�tre
		matdao.load();
		ensdao.load();
		elvdao.load();

		this.type = tpe;

		// Bulletin au format A4
		if (type == 1) {
			// thread qui lance la création des bulletin
			traitement = new Thread(new Runnable() {
				public void run() {
					listeEleveProg = elvclsdao.getListObt();
					Matieres = matpdao.getListObt();

					// on crée les bulletins
					createBull();
				}
			});
			traitement.start();
		}
	}

	public void createBull() {
		html = "";
		htmlBody = "";

		for (Observer obs : listObserver) {
			if (obs instanceof RelWriter) {
				writer = (RelWriter) obs;
			}
		}

		tableChoix = writer.matdef;
		tabElv = writer.mat;
		nbreBull = tabElv.getRowCount();

		writer.dispose();// On ferme le writter après avoir recupérrer le
		// tableau

		int nbreElv = listeEleveProg.size();
		int nbreMat = Matieres.size();
		bullCount = 0;
		formatter = new DecimalFormat("00.00");

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbreElv * (1 + nbreMat) + progress.FIN;

		progress = new Progress();

		progress.getProgress(pframe, 0, progmax);
		progress.setColor(Color.green);

		// ********************FIN*****************************/*****
		nview.setProgressAvancer(progress.getAvancer());

		infocls = nview.getInfoClasse();
		effectif = infocls.getOntComp();

		tabNote = bs.getTabNote();

		listeChoix = new MartList<EleveClasse>();
		for (int j = 0; j < nbreBull; j++) {
			String code = (String) tabElv.getValueAt(j, 1);

			eleve = (EleveClasse) listeEleveProg.find(code);
			try {
				eleve.getCodeInfo();// pour tester si l'élève a un code
				listeChoix.add(eleve);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// lancement de l'écriture dans la classe fille
		write();

		pframe.close();
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
