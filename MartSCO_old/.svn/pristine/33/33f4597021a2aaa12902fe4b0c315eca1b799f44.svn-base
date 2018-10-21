package rapportExamen;

import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
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
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import annee.Decoupage;
import classe.Classe;
import configurationClasse.ConfigClasse;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.TabbedPreview;
import eleve.EleveClasse;
import examen.SalleGenerator;

public class ListeSalle implements Observer {
	private ArrayList<EleveClasse> eleves;

	private HistoManager histoMng;
	public String trimstr;
	public String classe;

	static NoteViewer nview;
	int num = 0;
	int max;

	private Progress progress;
	private DocFormat bs;
	private int nbreElv;
	private MartFrame pframe = new ProgressFrame();
	private TabbedPreview preview;
	private Thread treat;

	private String examen;

	private DAO elvdao;

	private DAO elvclsdao;

	// constructeur avec paramètre
	public ListeSalle() {
		histoMng = new HistoManager();
		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);

		elvdao.load();

		initComponent();
	}

	// creation des composants
	public void initComponent() {

	}

	public void createListe(String exam) {
		examen = exam;

		load();
		refresh();
	}

	public void load() {
		elvclsdao.loadExam(examen);
		SalleGenerator gen = new SalleGenerator(examen);
		eleves = gen.getList(elvclsdao.getListObt());
	}

	// nous définissons la mise ajour du tableau
	public void refresh() {
		// ********debut de la barre de progression***************
		progress = new Progress();
		progress.getLoading(pframe, "Chargement des données en cours");
		// ****************fin partielle***********************

		nbreElv = eleves.size();

		// ***************LA BARRE DE PROGRESSION********************
		int progmax = (nbreElv) + progress.FIN;

		progress = new Progress();
		progress.getProgress(pframe, 0, progmax);
		progress.setColor(Color.green);

		// ************************FIN*****************************/

		bs = new RelFormat(examen);
		// on fait appelle à la visionneuse
		preview = new TabbedPreview();

		treat = new Thread(new Runnable() {
			private MartList<EleveClasse> listeSalle;
			private int numSalle;

			public void run() {
				listeSalle = new MartList<EleveClasse>();
				numSalle = 1;

				for (final EleveClasse el : eleves) {
					if (el.getSalle() != numSalle) {
						createRapport(listeSalle, numSalle);
						numSalle = el.getSalle();
						listeSalle = new MartList<EleveClasse>();

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					listeSalle.add(el);
					// *****POUR LA BARRE DE PORGRESSION***********
					progress.increment();
					// *********************FIN********************
				}

				// dans le cas d'une seul salle
				createRapport(listeSalle, numSalle);

			}
		});
		treat.start();

		pframe.close();
	}

	private synchronized void createRapport(MartList<EleveClasse> listeSalle,
			final int salle) {
		System.out.println("Numéro ================================ "
				+ listeSalle.size());
		String htmlBody = "";

		htmlBody += bs.writeEntete("LISTE DES CANDIDATS");

		htmlBody += "<table width=100% class='tabSB'><tr>"
				+ "<td class='tdSB' width=50% vertical-align=top>"
				+ "<div>Salle: " + salle + "</div><div>Effectif: "
				+ listeSalle.size() + "</div></td></tr></table><br/>";

		htmlBody += "<table width=100% class='tabSB'>" + "<tr id='ficheNote'>"
				+ "<td class='tdBC' width=6%>N° Table</td>"
				+ "<td class='tdSBLC' width=20%>Nom</td>"
				+ "<td class='tdSBLC' width=30%>Prénoms</td>"
				+ "<td class='tdSBLC' width=4%>Sexe</td>"
				+ "<td class='tdSBLC' width=40>Etablissement</td></tr>";

		// LES INFO CONCERNANT L'ELEVE
		for (EleveClasse elv : listeSalle) {
			try {
				htmlBody += "<tr id='ficheNote'>" + "<td class='tdSBT'>"
						+ elv.getNumTable() + "</td>" + "<td class='tdBInf'>"
						+ elv.getNom() + "</td>" + "<td class='tdBInf'>"
						+ elv.getPrenom() + "</td>" + "<td class='tdBInf'>"
						+ elv.getSexe() + "</td><td class='tdBInf'>"
						+ elv.getEts() + "</td>" + "</tr>";
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
			// *********************FIN********************
		}

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************

		final String html = "<html><head></head><body>" + htmlBody + "</body>";
		// System.out.println(html);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MartEditorPane editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.BULL);
				editor.setHtml(html);
				editor.revalidate();

				preview.addPanel("Salle " + salle, editor);
			}
		});

		preview.addObserver(ListeSalle.this);

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************

		// Pour l'archive
		String title = "liste par salle " + examen;

		Histo his = new Histo(title, html, new DateTime());
		histoMng.save(his);
		// fin archive

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************
	}

	public static void main(String[] args) {
		Constance.initialize();
		try {
			Constance.miseajour();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ListeSalle fr = new ListeSalle();
		fr.createListe("BEPC- BLANC 3ème AVRIL 2018");
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
