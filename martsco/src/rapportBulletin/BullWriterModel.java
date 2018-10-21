package rapportBulletin;

import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import abstractObject.AbstractModel;
import annee.Decoupage;
import classe.Classe;
import configurationAdmin.ConfigAdmin;
import configurationClasse.ConfigClasse;
import configurationEcole.ConfigEcole;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.MartArranger;
import graphicsModel.MartList;
import images.PictureFinder;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;
import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerBull;
import note.RapMoyenne;
import progress.Progress;
import progress.ProgressFrame;

public abstract class BullWriterModel extends AbstractModel {
	protected String htmlBody;
	protected String html;
	protected BullWriter writter;
	protected int nbreBull = 0, progmax = 0, maxtrim, effectif = 0;
	protected Progress progress;
	protected MartEditorPane editor;

	protected NoteViewer nview;
	protected InfoClasse infocls;
	protected InfoNote infonote;
	protected MartList<EleveClasse> elevesC;
	protected MartList<Eleve> eleves;
	protected MartList<MatiereProg> Matieres;
	protected DocFormat bs;
	protected JTable tabElv;
	protected ConfigEcole conf;

	protected static File photo;
	protected HistoManager histoMng;
	protected Thread treatment;
	protected DecimalFormat formatter;
	protected ProgressFrame pFrame;
	protected int nbreElv;
	protected int nbreMat;
	protected Decoupage decoupage;
	protected EleveClasse eleve;
	protected String statut;
	protected String tabNote;
	protected int numMat;
	protected String rangGen;
	protected String moyGenStr;
	protected String mention, obs;
	protected Moyenne bilan1;
	protected String totaux1;
	protected String moyGen1;
	protected Moyenne bilan2;
	protected String totaux2;
	protected String moyGen2;
	protected Moyenne bilan3;
	protected String totaux3;
	protected String moyGen3;
	protected double moy3;
	protected String rangtrim3;
	protected String moytrim3;
	protected String rangtrim2;
	protected String moytrim2;
	protected String rangtrim1;
	protected String moytrim1;
	protected String moyAnn;
	protected String rangAnn;
	protected Moyenne bilan;
	protected String totaux;
	protected String moyGen;
	protected double moy1;
	protected double moy2;
	protected String Hmoy;
	protected String Lmoy;
	protected String mCls;
	protected String trimstr;
	private ConfigClasse confClasse;
	private Classe superClasse;
	protected ConfigAdmin confAdmin;

	public BullWriterModel() {
		histoMng = new HistoManager();
		notedao = DAOFactory.getDAO(DAO.NOTE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		rapmoydao = DAOFactory.getDAO(DAO.RAPMOY);
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		promodao = DAOFactory.getDAO(DAO.PROMO_ELEVE);

		// les chargements ne dépendant d'aucun param�tre
		elvdao.load();
		ensdao.load();
		clsdao.load();

		eleves = elvdao.getListObt();
	}

	@Override
	public void valider(int tpe) {
		this.type = tpe;
		for (Observer obs : listObserver) {
			if (obs instanceof BullWriter) {
				writter = (BullWriter) obs;
			}
		}

		if (type == 1) {
			// ********debut de la barre de progression***************
			pFrame = new ProgressFrame();
			progress = new Progress();
			progress.getLoading(pFrame, "Chargement des données en cours");

			elvclsdao.load(new String(), classe, trimestre, annee);
			matpdao.load(new String(), classe, trimestre, annee);

			elevesC = elvclsdao.getListObt();
			Matieres = matpdao.getListObt();

			// on crée les bulletins
			createBull(1);

		}
	}

	public abstract void write();

	public void createBull(int typ) {
		final long time1 = System.currentTimeMillis();// traceur de
		// temps###################
		html = "";
		htmlBody = "";
		tabElv = writter.getMat();
		writter.dispose();// On ferme le writter après avoir recupérrer le
		// tableau
		type = typ;

		nbreBull = tabElv.getRowCount();

		conf = new ConfigEcole(trimestre, annee);
		superClasse = (Classe) clsdao.findObj(classe);
		confClasse = new ConfigClasse(superClasse, annee);

		nbreElv = elevesC.size();
		nbreMat = Matieres.size();

		formatter = new DecimalFormat("00.00");

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbreBull * ((nbreMat * 3) + 1) + (nbreMat * (1 + nbreElv)) + progress.FIN;

		// ********************FIN*****************************/*****

		// thread qui lance la cr�ation des bulletin
		treatment = new Thread(new Runnable() {
			@Override
			public void run() {
				progress.getProgress(pFrame, 0, progmax);
				progress.setColor(Color.green);

				maxtrim = 3;
				nview = new NoteViewerBull();
				nview.setProgressAvancer(progress.getAvancer());
				nview.load(classe, trimestre, annee);
				infocls = nview.getInfoClasse();
				effectif = infocls.getEff();

				decoupage = confClasse.persoClasse.getDecoupage();

				// le maximun pour le découpage
				if (decoupage.getTypeDec() == 1)
					maxtrim = 3;
				if (decoupage.getTypeDec() == 2)
					maxtrim = 2;

				if (type == 1) {
					trimstr = MartArranger.getOrder(trimestre, "M") + " " + decoupage.toString();

					bs = new BullFormat(trimestre, annee);
					superClasse.setEffectif(effectif);
					bs.setClasse(superClasse);

					bs.load();// pour les mises à jour nécessaires
					tabNote = bs.getTabNote();

					// recuperation des info de la classe
					Hmoy = formatter.format(infocls.getHnote());
					Lmoy = formatter.format(infocls.getLnote());
					mCls = formatter.format(infocls.getMoy_Cls());

					// ****************************************************
					// début de la création des bulletins

					for (int j = 0; j < nbreBull; j++) {
						String matricule = (String) tabElv.getValueAt(j, 1);
						eleve = elevesC.find(matricule);

						String nom = eleve.getNom();
						String prenom = eleve.getPrenom();
						String sexe = eleve.getSexe();

						statut = "";
						try {
							statut = eleve.getStatut().toString();
						} catch (Exception e) {
						}

						// **********DEFINIT LA PHOTO DE L'ELEVE*************
						photo = new PictureFinder().getPhotoEleve(eleve.getCodeInfo());

						// ********pour la barre de progression**************
						progress.setText("MartSco est en train d'éditer le bulletin de: " + eleve.getNom() + " "
								+ eleve.getPrenom());
						// ******************************************************************

						bilan = nview.getMoyenne(new EleveClasse(matricule));
						totaux = formatter.format(bilan.getGrdTotal());
						moyGen = formatter.format(bilan.getMoyGen());
						rangGen = nview.getRang(new MatiereProg("std"), new EleveClasse(matricule));

						moyGenStr = (bilan.toString()).toUpperCase();
						mention = bilan.getMention().toUpperCase();

						bilan1 = bilan.getLit();
						totaux1 = formatter.format(bilan1.getGrdTotal());
						moyGen1 = formatter.format(bilan1.getMoyGen());

						bilan2 = bilan.getScien();
						totaux2 = formatter.format(bilan2.getGrdTotal());
						moyGen2 = formatter.format(bilan2.getMoyGen());

						bilan3 = bilan.getFac();
						totaux3 = formatter.format(bilan3.getGrdTotal());
						moyGen3 = formatter.format(bilan3.getMoyGen());

						// rappel de moyenne
						// On affichera pas 0 comme moyenne
						rapmoydao.load("", classe, trimestre, annee);
						RapMoyenne rpm = (RapMoyenne) rapmoydao.findObj(matricule);

						moy1 = rpm.getMoyenne1();
						moy2 = rpm.getMoyenne2();
						moy3 = rpm.getMoyenne3();

						moytrim1 = "";
						rangtrim1 = "";
						moytrim2 = "";
						rangtrim2 = "";
						moytrim3 = "";
						rangtrim3 = "";

						if (moy1 > 0) {
							moytrim1 = formatter.format(moy1);
							rangtrim1 = rpm.getRang1();
						}
						if (moy2 > 0) {
							moytrim2 = formatter.format(moy2);
							rangtrim2 = rpm.getRang2();
						}
						if (moy3 > 0) {
							moytrim3 = formatter.format(moy3);
							rangtrim3 = rpm.getRang3();
						}

						// la moyenne annuelle est sans condition
						moyAnn = formatter.format(rpm.getMoyAnn());
						rangAnn = rpm.getRangAnn();

						// numéro des matières
						numMat = 1;

						// appreciation de la moyenne selon le trimestre
						bilan.setTrimestre(trimestre);
						bilan.setAnnee(annee);
						conf = new ConfigEcole(trimestre, annee);
						confAdmin = new ConfigAdmin(trimestre, annee);

						boolean decisionAuto = conf.bullConfig.decisionAuto();
						double moyPass = confAdmin.adminConfig.getMoyPass(niveau);
						bilan.setDecisionAuto(decisionAuto);
						bilan.setMoyennePassage(moyPass);
						bilan.setNiveau(niveau);

						obs = bilan.getObs(bilan.getMoyGen()).toUpperCase();
						if (trimestre == maxtrim) {
							bilan.setEndOfYear(true);
							obs = bilan.getObs(rpm.getMoyAnn()).toUpperCase();
						}

						write();
						// ********pour la barre de progression**************
						progress.setText("MartSco se prépare pour afficher les Bulletins");
						// ******************************************************************
					}

					html += "<html><head></head><body>" + htmlBody + "</body></html>";

					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							editor = new MartEditorPane();
							MartStyle.setPadding(0);
							MartStyle.setRowheight(5);
							editor.setStyle(MartStyle.BULL);
							editor.setHtml(html);

							// on fait appelle à la visionneuse
							Preview bsh = new Preview(editor);

							// *****POUR LA BARRE DE PORGRESSION***********
							progress.increment();
							// *********************FIN********************

							pFrame.close();
						}
					});

					// Pour l'archive
					String title = "Bulletins/ " + classe + " " + trimstr + "/ " + annee;

					Histo his = new Histo(title, html, new DateTime());
					histoMng.save(his);
					// fin archive

					long time2 = System.currentTimeMillis();
					System.out.println("###########################################" + "La durée de l'exécution est:"
							+ (double) (time2 - time1) / 1000 + " s");
				}
			}

		});
		treatment.start();
	}

	public int getNbreBull() {
		return nbreBull;
	}

	@Override
	public MartList<MartRangeable> getRessources() {
		eleves.setId("listeEleve");
		ressources.add(eleves);
		return null;
	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}
}
