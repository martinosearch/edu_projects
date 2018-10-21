package statistique;

import interfacePerso.Observer;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerBull;

import org.joda.time.DateTime;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import annee.Decoupage;
import classe.Classe;
import classe.Niveau;
import configurationClasse.ConfigClasse;
import configurationEcole.ConfigEcole;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.EleveClasse;
import function.Constance;
import function.MartArranger;
import function.StaData;
import function.Statistician;
import function.Statistician.MatSta;
import function.Statistician.MoySta;
import graphicsModel.MartFrame;

public abstract class StaNoteCompoModel extends AbstractModel {
	protected String htmlBody;
	protected String html;
	protected int nbreSection = 0, nbreClasses = 0, progmax = 0, maxtrim,
			effectif = 0;
	protected Progress progress;
	protected MartEditorPane editor;

	protected NoteViewer nview;
	protected InfoClasse infocls;
	protected InfoNote infonote;
	protected ArrayList<EleveClasse> Eleves;
	protected ArrayList<MatiereProg> Matieres;
	protected ArrayList<Niveau> Niveaux;
	protected DocFormat bs;
	protected JTable tabClasses;
	protected Statistician staman;

	protected DecimalFormat formatter = new DecimalFormat("00.00");
	protected int countChg;
	protected HistoManager histoMng;

	protected StaNoteCompo principaleObs;
	protected ConfigEcole conf;
	protected String trimstr;
	protected MoySta staG;
	protected ArrayList<MatSta> stamat;
	protected ArrayList<MoySta> stamoy;
	protected int nbreMat;
	protected int nbreElv;
	protected int nbreCol;
	protected Decoupage dec;
	protected DocFormat docSet;
	private ConfigClasse confClasse;
	private ProgressFrame pFrame;

	public StaNoteCompoModel() {
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
		conf = new ConfigEcole(trimestre, annee);
		docSet = new BullFormat(trimestre, annee);

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
					createSta(conf.bullConfig.getTypeStaNoteCompo());
				}
			}).start();
		}
	}

	public void createSta(int type) {
		html = "";
		htmlBody = "";
		countChg = 0;

		tabClasses = principaleObs.getTableOfChoise();
		nbreClasses = tabClasses.getRowCount();

		Classe cls;

		// ****************on crée en même temps les pages*******************
		editor = new MartEditorPane(MartEditorPane.MATHS_DOCUMENT);
		MartStyle.setPadding(0);
		MartStyle.setRowheight(Constance.STA_NOTE_COMPO_ROWH);
		editor.setStyle(MartStyle.DOCUMENTS);
		// ****************************************************************

		Niveaux = new ArrayList<Niveau>();

		// créer la table des niveaux
		for (int i = 0; i < nbreClasses; i++) {
			classe = (String) tabClasses.getValueAt(i, 1);
			Classe classe1 = (Classe) clsdao.findObj(classe);
			String niveau1 = classe1.getNiveau();

			confClasse = new ConfigClasse(classe1, annee);
			dec = confClasse.persoClasse.getDecoupage();
			dec.setSection(trimestre);

			int test = 0;
			for (Niveau niv : Niveaux) {
				if (niv.getIntitule().equals(niveau1)) {
					test++;
				}
			}
			if (test == 0) {
				Niveau niveau = (Niveau) nivdao.findObj(niveau1);
				Niveaux.add(niveau);
			}
		}

		stamoy = new ArrayList<MoySta>();

		for (Niveau niv : Niveaux) {
			niveau = niv.getIntitule();
			// instantier le statisticien
			staman = new Statistician();
			stamat = new ArrayList<MatSta>();

			for (int i = 0; i < nbreClasses; i++) {
				classe = (String) tabClasses.getValueAt(i, 1);
				cls = (Classe) clsdao.findObj(classe);
				classe = cls.getIntitule();

				// si la classe est dans le niveau actuel
				if (cls.getNiveau().equals(niv.getIntitule())) {
					countChg++;

					// ********debut de la barre de progression***************

					progress = new Progress();
					progress.getLoading(pFrame,
							MartArranger.getOrder(countChg, "M")
									+ " Chargement Sur " + nbreClasses);

					Eleves = new ArrayList<EleveClasse>();
					Matieres = new ArrayList<MatiereProg>();

					elvclsdao.load(new String(), classe, trimestre, annee);
					matpdao.load(new String(), classe, trimestre, annee);

					Eleves = elvclsdao.getList();
					Matieres = matpdao.getList();

					nbreMat = Matieres.size();
					nbreElv = Eleves.size();

					nview = new NoteViewerBull();

					nview.load(classe, trimestre, annee);
					infocls = nview.getInfoClasse();
					effectif = infocls.getEff();
					staman.addClasse(cls.getNiveau());

					// ***************LA BARRE DE
					// PROGRESSION********************
					progmax = effectif * (nbreMat + 2) + progress.FIN;

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
					for (MatiereProg mat : Matieres) {
						staman.addMatiere(mat.getIntitule());
						for (EleveClasse elv : Eleves) {
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
					for (EleveClasse elv : Eleves) {
						Moyenne moyG = nview.getMoyenne(elv);
						double moytrim = moyG.getMoyGen();

						StaData data = new StaData(cls.getNiveau(), moytrim,
								elv.getSexe(), moyG.hasCompose());

						staman.setDataMoy(data);

						// *****POUR LA BARRE DE PORGRESSION***********
						progress.increment();
						// *********************FIN********************
					}
				}// fin if
			}// fin for classe

			// *******************on commence par
			// éditer**************************

			stamat = staman.getResultMat();
			stamoy = staman.getResultMoy();

			nbreCol = stamat.size() + 2;

			// On choisi la statistique générale correspondant au niveau
			staG = null;

			for (MoySta moyst : stamoy) {
				if ((moyst.getIntitule()).equals(niv.getIntitule())) {
					staG = moyst;

					write();
				}// fin si la sta du niveau est trouvée
			}// fin for moysta

			// *****POUR LA BARRE DE PORGRESSION********
			progress.increment();
			// *********************FIN********************
		}// for Niveau

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
}
