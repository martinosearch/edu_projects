package rapportBulletin;

import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.MartArranger;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import matiere.MatiereProg;
import note.InfoClasse;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerBull;
import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import abstractObject.Rapport;
import annee.Decoupage;
import classe.Classe;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;

public class FicheRst2 extends MartFrame {
	private String annee = "", classe = "";
	private int trimestre = 1;
	private DAO clsdao, andao, matpdao, elvdao, elvclsdao;
	private ArrayList<MatiereProg> matieres;
	private ArrayList<EleveClasse> eleves;
	private String htmlBody;
	private String html = "";
	private Decoupage dec;
	private int model = 1;
	private NoteViewer nview;
	private DecimalFormat formatter = new DecimalFormat("00.00");
	private Progress progress;
	private int progmax;
	private DocFormat bs;
	private Classe cls;
	protected MartFrame progressFrame;
	private ConfigClasse conf;

	// constructeur avec paramètre
	public FicheRst2() {
		new HistoManager();

		initComponent();
	}

	// creation des composants
	public void initComponent() {
	}

	public void createResutat(String cl, int trim, String an) {
		classe = cl;
		annee = an;
		trimestre = trim;

		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		DAOFactory.getDAO(DAO.NOTE);

		clsdao.load();
		andao.load();
		elvdao.load();

		cls = (Classe) clsdao.findObj(classe);

		load();
		refresh();
	}

	@Override
	public void load() {
		// Definition de la dénomination
		bs = new BullFormat(trimestre, annee);
		bs.setClasse(cls);
		bs.load();

		Classe superClasse = (Classe) clsdao.findObj(classe);
		conf = new ConfigClasse(superClasse, annee);
		dec = conf.persoClasse.getDecoupage();

		dec.setSection(trimestre);
	}

	// nous définissons la mise ajour de la fiche
	@Override
	public void refresh() {
		html = "";
		htmlBody = "";

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();

		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		// table des notes
		matpdao.load(new String(), classe, trimestre, annee);
		elvclsdao.load(new String(), classe, trimestre, annee);

		eleves = elvclsdao.getListObt();
		matieres = matpdao.getListObt();

		int nbreElv = eleves.size();
		int nbreMat = matieres.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = (nbreElv) + (nbreMat * (1 + nbreElv)) + progress.FIN;
		progress.getProgress(progressFrame, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************/*****

		// Chargement des notes
		nview = new NoteViewerBull();
		nview.setProgressAvancer(progress.getAvancer());

		nview.load(classe, trimestre, annee);

		InfoClasse infocls = nview.getInfoClasse();
		// créons le tableau
		htmlBody += "<div class='saut'>" + "<div id='sautligne'></div>"
				+ "<div class='cadre'>";// le cadre

		htmlBody += bs.writeEntete("RESULTAT DU " + dec.toString()
				+ " PAR ORDRE DE MERITE");
		// fin des en-têtes

		htmlBody += "<br/>" + "<table width=100% class='tabSB'><tr"
				+ "<td class='tdSB'>";

		htmlBody += "<div id='result'><b>Classe: " + classe + "</b></div>";

		htmlBody += "<div id='result'>Inscrits: " + infocls.getEff() + "</div>"
				+ "<div id='result'>Ont composés: " + infocls.getOntComp()
				+ "</div>" + "<div id='result'>Sont admis: "
				+ infocls.getNMoy() + "</div>"
				+ "<div id='result'>Pourcentage: " + infocls.getPercent()
				+ "</div>" + "</td>" + "</tr>" + "</table><br/>";

		// On vérifie le type de rapport � fournir
		if (model == Rapport.MODEL_SECOND) {// pour le secondaire
			htmlBody += "<table width=100% class='tabSB'>"
					+ "<tr  id='result'>"
					+ "<td class='tdBC' width=8%>Rang</td>"
					+ "<td class='tdSBLC' width=20%>Nom</td>"
					+ "<td class='tdSBLC' width=50%>Prénoms</td>"
					+ "<td class='tdSBLC' width=10%>Moyenne</td>";

			htmlBody += "</tr>";

			for (int i = 1; i < eleves.size() + 1; i++) {
				try {
					Eleve eleve = nview.getElvAuRg("std", i);
					String matricule = eleve.getCodeInfo();

					Moyenne moy = nview.getMoyenne(new EleveClasse(matricule));

					// On affiche la moyenne si l'élève a composé
					if (moy.hasCompose() == true) {

						double moytrim = moy.getMoyGen();

						String moyf = formatter.format(moytrim);
						String sexe = eleve.getSexe();

						String rang = MartArranger.getOrder(i, sexe);

						htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
								+ rang + "</td>" + "<td class='tdBInf'>"
								+ eleve.getNom() + "</td>"
								+ "<td class='tdBInf'>" + eleve.getPrenom()
								+ "</td>" + "<td class='tdBInf'>" + moyf
								+ "</td>";

						htmlBody += "</tr>";
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}// fin for élève
		}// fin SECONDAIRE

		if (model == Rapport.MODEL_PRIM) {// pour le primaire
			htmlBody += "<table width=100% class='tabSB'>"
					+ "<tr  id='result'>"
					+ "<td class='tdBC' width=8%>Rang</td>"
					+ "<td class='tdSBLC' width=20%>Nom</td>"
					+ "<td class='tdSBLC' width=50%>Prénoms</td>"
					+ "<td class='tdSBLC' width=10%>Points Obtenus</td>";

			htmlBody += "</tr>";

			for (int i = 1; i < eleves.size() + 1; i++) {
				try {
					Eleve eleve = nview.getElvAuRg("std", i);
					String matricule = eleve.getCodeInfo();

					Moyenne moy = nview.getMoyenne(new EleveClasse(matricule));

					// On affiche la moyenne si l'élève a composé
					if (moy.hasCompose() == true) {

						double ptObtenus = moy.getGrdTotal();// ON RENVOI PLUTOT
																// LE
																// TOTAL

						String moyf = formatter.format(ptObtenus);
						String sexe = eleve.getSexe();

						String rang = MartArranger.getOrder(i, sexe);

						htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
								+ rang + "</td>" + "<td class='tdBInf'>"
								+ eleve.getNom() + "</td>"
								+ "<td class='tdBInf'>" + eleve.getPrenom()
								+ "</td>" + "<td class='tdBInf'>" + moyf
								+ "</td>";

						htmlBody += "</tr>";
					}
				} catch (Exception e) {
					e.getStackTrace();
				}
				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}// fin for élève
		}// fin PRIMAIRE

		// FIN DES VERIFICATIONS

		htmlBody += "</table>";
		htmlBody += bs.getTabSignature(DocFormat.DIR_TITUL, "tabSB");

		htmlBody += "</div>"; // fin cadre

		htmlBody += "<div id='sautligne'></div>"; // le saut de page

		html += "<html><head></head><body>" + htmlBody + "</body>";

		System.out.println(html);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MartEditorPane editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.DOCUMENTS);
				editor.setHtml(html);

				new Preview(editor);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
				progressFrame.close();
			}
		});
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
