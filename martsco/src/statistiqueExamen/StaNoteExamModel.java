package statistiqueExamen;

import interfacePerso.Observer;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerExam;

import org.joda.time.DateTime;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import rapportExamen.RelFormat;
import statistique.StaNoteCompo;
import abstractObject.AbstractModel;
import annee.Decoupage;
import classe.Classe;
import classe.Niveau;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.EleveClasse;
import etablissement.FilterEts;
import examen.Examen;
import function.Constance;
import function.StaData;
import function.Statistician;
import function.Statistician.MatSta;
import function.Statistician.MoySta;

public abstract class StaNoteExamModel extends AbstractModel {
	protected String htmlBody;
	protected String html;
	protected int progmax = 0, effectif = 0;
	protected Progress progress;
	protected MartEditorPane editor;

	protected NoteViewer nview;
	protected InfoClasse infocls;
	protected InfoNote infonote;
	protected ArrayList<EleveClasse> eleves;
	protected ArrayList<MatiereProg> matieres;
	protected ArrayList<Niveau> Niveaux;
	protected Statistician staman;

	protected DecimalFormat formatter = new DecimalFormat("00.00");
	protected HistoManager histoMng;

	protected StaNoteCompo principaleObs;
	protected ConfigExamen conf;
	protected String trimstr;
	protected MoySta staG;
	protected ArrayList<MatSta> stamat;
	protected ArrayList<MoySta> stamoy;
	protected int nbreMat;
	protected int nbreElv;
	protected int nbreCol;
	protected Decoupage dec;
	protected DocFormat bs;
	private ProgressFrame pFrame;
	protected Examen superExamen;

	public StaNoteExamModel() {
		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		notedao = DAOFactory.getDAO(DAO.NOTE_EXAM);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		examdao = DAOFactory.getDAO(DAO.EXAMEN);

		// les chargements ne dépendant d'aucun paramètre
		elvdao.load();
		matdao.load();
		clsdao.load();
		examdao.load();

		histoMng = new HistoManager();
	}

	public void valider(int tpe) {
		this.type = tpe;
		conf = new ConfigExamen(examen);
		bs = new RelFormat(examen);

		superExamen = (Examen) examdao.findObj(examen);

		// paramètres généraux
		for (Observer obs : listObserver) {
			if (obs instanceof StaNoteCompo) {
				principaleObs = (StaNoteCompo) obs;
			}
		}

		if (type == 1) {
			pFrame = new ProgressFrame();
			progress = new Progress();
			progress.getLoading(pFrame, "Chargement en cours");

			// thread qui lance la création des fiches
			new Thread(new Runnable() {
				public void run() {
					createSta(conf.relConfig.getTypeStaMoyTrim());
				}
			}).start();
		}
	}

	public void createSta(int type) {
		html = "";
		htmlBody = "";

		Classe cls;

		// ****************on crée en même temps les pages*******************
		editor = new MartEditorPane(MartEditorPane.MATHS_DOCUMENT);
		MartStyle.setPadding(0);
		MartStyle.setRowheight(conf.relConfig.getStaMoyHRow());
		editor.setStyle(MartStyle.DOCUMENTS);
		// ****************************************************************

		stamoy = new ArrayList<MoySta>();

		// instantier le statisticien
		staman = new Statistician();
		stamat = new ArrayList<MatSta>();

		eleves = new ArrayList<EleveClasse>();
		matieres = new ArrayList<MatiereProg>();

		// chargement des élèves et des matières au programme
		elvclsdao.loadExam(examen);
		matpdao.loadExam(examen);

		FilterEts filter = new FilterEts(etablissement);
		eleves = filter.getListe(elvclsdao.getListObt());

		matieres = matpdao.getListObt();

		nview = new NoteViewerExam();
		nview.load(examen);
		nview.setEtablissement(etablissement);

		infocls = nview.getInfoClasse();
		effectif = infocls.getEff();

		staman.addClasse(examen);

		// ***************LA BARRE DE
		// PROGRESSION********************
		progmax = 3 * effectif * (nbreMat + 2) + progress.FIN;

		progress = new Progress();
		progress.getProgress(pFrame, 0, progmax);
		progress.setColor(Color.green);
		nview.setProgressAvancer(progress.getAvancer());

		// ********************FIN*****************************/*****
		// *****POUR LA BARRE DE PORGRESSION***********
		progress.setText("Traitement des données de la :" + classe);
		// *********************FIN********************

		// on crée les bulletins
		// on reccupère la moyenne de chaque élève
		for (MatiereProg mat : matieres) {
			staman.addMatiere(mat.getIntitule());

			for (EleveClasse elv : eleves) {
				InfoNote note = nview.getNotes(mat, elv);
				double moy = 0.001;
				try {
					moy = Double.valueOf(note.getCompo());
				} catch (Exception e) {
					moy = moy;
				}

				StaData data = new StaData(mat.getIntitule(), moy,
						elv.getSexe(), note.hasCompose());

				staman.setDataMat(data);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}
		}

		// Ajout des moyennes générales de élève du niveau considéré
		for (EleveClasse elv : eleves) {
			Moyenne moyG = nview.getMoyenne(elv);
			double moytrim = moyG.getMoyGen();

			StaData data = new StaData(examen, moytrim, elv.getSexe(),
					moyG.hasCompose());

			staman.setDataMoy(data);

			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
			// *********************FIN********************
		}

		// *******************on commence par
		// éditer**************************

		stamat = staman.getResultMat();
		stamoy = staman.getResultMoy();

		nbreCol = stamat.size() + 2;

		// On choisi la statistique générale correspondant au niveau
		staG = null;

		for (MoySta moyst : stamoy) {
			staG = moyst;

			write();
		}// fin for moysta

		// *****POUR LA BARRE DE PORGRESSION********
		progress.increment();
		// *********************FIN********************

		progress.setText("Finalisation des traitements");

		html += "<html>" + "<head>" + "<meta charset='utf-8' />" + "</head>"
				+ "<body><div id='rapportsta'>" + htmlBody + "</div>"
				+ "</body></html>";

		System.out.println(html);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editor.setHtml(html);
				// on fait appelle à l'editeur
				editor.revalidate();
				Preview bsh = new Preview(editor);
			}
		});

		// Pour l'archive********************************
		String title = "Statistiques_Compo/ " + trimstr + "/ " + annee;

		Histo his = new Histo(title, html, new DateTime());
		histoMng.save(his);
		// fin archive**********************************

		// ********************************
		progress.stop();
		// ****************************************************
		pFrame.close();
	}

	public abstract void write();

	public int getNbreBull() {
		return effectif;
	}

	public void setEtablissement(String ets) {
		etablissement = ets;
	}
}
