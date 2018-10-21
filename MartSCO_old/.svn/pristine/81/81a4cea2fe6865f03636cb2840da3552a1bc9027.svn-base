package rapportBulletin;

import function.Constance;
import graphicsModel.MartFrame;
import interfacePerso.Observer;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import matiere.MatiereProg;
import note.StaMatiereClasse;
import note.InfoNote;
import note.NoteViewer;
import note.NoteViewerBull;

import org.joda.time.DateTime;

import progress.Progress;
import progress.ProgressFrame;
import annee.Decoupage;
import classe.Classe;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.TabbedPreview;
import eleve.EleveClasse;

public class RapportSaisie implements Observer {
	private int trimestre = 1;

	private DAO clsdao, andao, matpdao, elvdao, elvclsdao, notedao;

	private String annee;
	private ArrayList<MatiereProg> matieres;
	private ArrayList<EleveClasse> eleves;

	private HistoManager histoMng;
	private Decoupage dec;
	public String classe;

	static NoteViewer nview;
	int num = 0;
	int max;

	private Progress progress;
	private String htmlBody;
	private String html;
	private DecimalFormat formatter = new DecimalFormat("00.00");
	private DocFormat bs;
	private int nbreElv;
	private int nbreMat;
	private MartFrame pframe = new ProgressFrame();
	private TabbedPreview preview;
	private ConfigClasse conf;

	private Thread treat;

	// constructeur avec paramètre
	public RapportSaisie() {
		histoMng = new HistoManager();
		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		notedao = DAOFactory.getDAO(DAO.NOTE);

		clsdao.load();
		andao.load();
		elvdao.load();

		initComponent();
	}

	// creation des composants
	public void initComponent() {

	}

	public void createResutat(String cl, int trim, String an) {
		classe = cl;
		annee = an;
		trimestre = trim;

		load();
		refresh();
	}

	public void load() {
		// POUR LE TRIMESTRE
		Classe superClasse = (Classe) clsdao.findObj(classe);
		conf = new ConfigClasse(superClasse, annee);
		dec = conf.persoClasse.getDecoupage();
		dec.setSection(trimestre);
	}

	// nous définissons la mise ajour du tableau
	public void refresh() {
		// ********debut de la barre de progression***************
		progress = new Progress();
		progress.getLoading(pframe, "Chargement des données en cours");
		// ****************fin partielle***********************

		// table des notes
		matpdao.load(new String(), classe, trimestre, annee);

		matieres = matpdao.getList();

		// la listes des élèves
		elvclsdao.load(new String(), classe, trimestre, annee);
		eleves = elvclsdao.getListObt();

		nbreElv = eleves.size();
		nbreMat = matieres.size();

		// ***************LA BARRE DE PROGRESSION********************
		final int progmax = ((nbreElv + 2) * nbreMat) + progress.FIN;

		progress = new Progress();
		progress.getProgress(pframe, 0, progmax);
		progress.setColor(Color.green);

		// ************************FIN*****************************/
		preview = new TabbedPreview();

		nview = new NoteViewerBull();
		nview.load(classe, trimestre, annee);
		bs = new BullFormat(trimestre, annee);

		treat = new Thread(new Runnable() {
			public void run() {
				for (final MatiereProg mat : matieres) {
					createRapport(mat);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// *****POUR LA BARRE DE PORGRESSION***********
					progress.increment();
					// *********************FIN********************
				}
			}
		});
		treat.start();

		pframe.close();
	}

	private synchronized void createRapport(final MatiereProg mat) {
		html = new String();
		htmlBody = new String();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// pour les statistiques

				StaMatiereClasse infoMatiere = nview.getStaMatiereClasse(mat
						.getIntitule());
				System.out
						.println("############################################################# cc"
								+ mat.getIntitule());

				htmlBody += bs.writeEntete("FICHE DE NOTES DU "
						+ nview.getTitreRapport());

				htmlBody += "<table width=100% class='tabSB'><tr>"
						+ "<td class='tdSB' width=50% vertical-align=top>"
						+ "<div>Classe: "
						+ classe
						+ "</div>"
						+ "<div>Matière: "
						+ (mat.getIntitule()).toUpperCase()
						+ "</div>"
						+ "<div>Type: "
						+ mat.getType()
						+ "</div>"
						+ "<div>Prof Chargé: "
						+ mat.getCharge()
						+ "</div>"
						+ "</td><td class='tdSB'width=50% >"
						+ "<div>Effectif: "
						+ nbreElv
						+ "</div>"
						+ "<div>Ont Composé: "
						+ infoMatiere.getOntComp()
						+ "</div>"
						+ "<div>Nombre de Moyenne: "
						+ infoMatiere.getNMoy()
						+ "</div>"
						+ "<div>Pourcentage: "
						+ infoMatiere.getPercent()
						+ "</div>"
						+ "<div>La plus forte note: "
						+ formatter.format(infoMatiere.getHNote())
						+ "</div>"
						+ "<div>La plus faible note: "
						+ formatter.format(infoMatiere.getLnote())
						+ "</div>"
						+ "</td>" + "</tr>" + "</table><br/>";

				htmlBody += "<table width=100% class='tabSB'>"
						+ "<tr id='ficheNote'>"
						+ "<td class='tdBC' width=4%>N°ord</td>"
						+ "<td class='tdSBLC' width=15%>Nom</td>"
						+ "<td class='tdSBLC' width=21%>Prénoms</td>"
						+ "<td class='tdSBLC' width=4%>Sexe</td>"
						+ "<td class='tdSBLC' width=4%>Sco.</td>"
						+ "<td class='tdSBLC' width=6%>Int1</td>"
						+ "<td class='tdSBLC' width=6%>Int2</td>"
						+ "<td class='tdSBLC' width=6%>Dev</td>"
						+ "<td class='tdSBLC' width=6%>Moy. Cl</td>"
						+ "<td class='tdSBLC' width=6%>Compo</td>"
						+ "<td class='tdSBLC' width=6%>Moy.</td>"
						+ "<td class='tdSBLC' width=6%>Rang</td>" + "</tr>";

				// LES INFO CONCERNANT L'ELEVE
				int num = 0;
				for (EleveClasse elv : eleves) {
					num++;
					String matricule = elv.getCodeInfo();
					InfoNote info = new InfoNote();

					try {
						info = nview.getNotes(mat, elv);
					} catch (Exception e) {
						e.printStackTrace();
					}

					String nom = elv.getNom();
					String prenom = elv.getPrenom();
					String sexe = elv.getSexe();
					double moyenne = info.getmoy();
					try {
						htmlBody += "<tr id='ficheNote'>"
								+ "<td class='tdSBT'>"
								+ num
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ nom
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ prenom
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ sexe
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ ""
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ info.getNote1()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ info.getNote2()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ info.getDev()
								+ "</td>"
								+ "<td class='tdBInf'><b>"
								+ formatter.format(info.getmoyCls())
								+ "</b></td>"
								+ "<td class='tdBInf'>"
								+ info.getCompo()
								+ "</td>"
								+ "<td class='tdBInf'><b>"
								+ formatter.format(info.getmoy())
								+ "</b></td>"
								+ "<td class='tdBInf'>"
								+ info.getRang()
								+ "</td>" + "</tr>";
					} catch (Exception e) {
						e.printStackTrace();
					}

					// *****POUR LA BARRE DE PORGRESSION***********
					progress.increment();
					// *********************FIN********************
				}

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************

				html += "<html><head></head><body>" + htmlBody + "</body>";
				// System.out.println(html);

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						MartEditorPane editor = new MartEditorPane();
						MartStyle.setPadding(0);
						MartStyle.setRowheight(5);
						editor.setStyle(MartStyle.BULL);
						editor.setHtml(html);

						// on fait appelle à la visionneuse

						preview.addPanel(mat.getIntitule(), editor);
					}
				});

				preview.addObserver(RapportSaisie.this);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************

				// Pour l'archive
				String title = "Fiche de Note/" + mat.getIntitule() + "/ "
						+ classe + " " + dec.toString() + "/ " + annee;

				Histo his = new Histo(title, html, new DateTime());
				histoMng.save(his);
				// fin archive

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}
		}).start();
	}

	public static void main(String[] args) {
		Constance.initialize();
		try {
			Constance.miseajour();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RapportSaisie fr = new RapportSaisie();
		fr.createResutat("3ème", 1, "2017-2018");
	}

	@Override
	public void update() {
		try {
			treat.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
