package rapportBulletin;

import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.MartArranger;
import graphicsModel.MartFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import matiere.Matiere;
import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerBull;

import org.joda.time.DateTime;

import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import annee.Decoupage;
import classe.Classe;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;

public class FicheRst1 extends MartFrame {
	private JLabel lbTitre = new JLabel();
	private JPanel panTitre = new JPanel();
	private MartEditorPane panListe;

	private JPanel panBut = new JPanel();

	private int trimestre = 1;

	private DAO clsdao, andao, matpdao, matdao, elvdao, elvclsdao;

	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Font police2 = new Font("Courier", Font.PLAIN, 16);

	private ArrayList<MatiereProg> matieres;
	private ArrayList<EleveClasse> eleves;
	private String htmlBody;
	private String html = "";

	private String classe;
	private String annee;

	private HistoManager histoMng;
	private Decoupage dec;

	private NoteViewer nview;
	private DecimalFormat formatter = new DecimalFormat("00.00");
	private File Plogo = new File("documents/images/Plogo.jpg");
	private Progress progress;
	private Classe cls;
	private DocFormat bs;
	private ProgressFrame progressFrame;
	private ConfigClasse conf;

	// constructeur avec param�tre
	public FicheRst1() {

		histoMng = new HistoManager();

		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		DAOFactory.getDAO(DAO.NOTE);

		clsdao.load();
		andao.load();
		elvdao.load();
		matdao.load();

		initComponent();
	}

	// creation des composants
	public void initComponent() {
		// les autres composants(bouton imprimer...)
		panTitre.setPreferredSize(new Dimension(500, 20));
		panTitre.setBackground(Color.YELLOW);
		panTitre.setLayout(new BorderLayout());

		panBut.setLayout(new GridLayout(1, 3));

		lbTitre.setPreferredSize(new Dimension(500, 15));
		lbTitre.setFont(police1);

		panListe = new MartEditorPane();
		panListe.setBackground(Color.white);
		MartStyle.setPadding(0);
		MartStyle.setRowheight(5);
		panListe.setStyle(MartStyle.DOCUMENTS);
	}

	public void createResutat(String cl, int trim, String an) {
		classe = cl;
		annee = an;
		trimestre = trim;

		cls = (Classe) clsdao.findObj(classe);
		bs = new BullFormat(trimestre, annee);
		bs.setClasse(cls);
		bs.load();

		load();
		refresh();
	}

	public void load() {
		Classe superClasse = (Classe) clsdao.findObj(classe);
		conf = new ConfigClasse(superClasse, annee);
		dec = conf.persoClasse.getDecoupage();
		dec.setSection(trimestre);

		panBut.removeAll();
	}

	// nous d�finissons la mise ajour du tableau
	public void refresh() {
		html = new String();
		htmlBody = new String();

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();

		progressFrame.setVisible(true);

		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		matpdao.load(new String(), classe, trimestre, annee);
		matieres = matpdao.getList();

		// la listes des élèves
		elvclsdao.load(new String(), classe, trimestre, annee);
		eleves = elvclsdao.getListObt();

		int nbreElv = eleves.size();
		int nbreMat = matieres.size();

		// ***************LA BARRE DE PROGRESSION********************
		int progmax = (nbreMat + nbreElv * (nbreMat + 1))
				+ (nbreMat * (1 + nbreElv)) + progress.FIN;

		progress.getProgress(progressFrame, 0, progmax);
		progress.setColor(Color.green);

		nview = new NoteViewerBull();
		nview.setProgressAvancer(progress.getAvancer());
		nview.load(classe, trimestre, annee);
		InfoClasse infocls = nview.getInfoClasse();

		// creons le tableau
		htmlBody += bs.writeEntete("RESULTAT DU " + dec.toString()
				+ " PAR MATIERE");
		// fin des en-têtes

		htmlBody += "<table width=100% class='tabSB'><br/>"

		+ "<tr id='result'>" + "<td class='tdSB'>"
				+ "<div id='result'><b>Classe: " + classe + "</b></div>"
				+ "<div id='result'>Effectif: " + infocls.getEff() + "</div>"
				+ "<div id='result'>Ont composés: " + infocls.getOntComp()
				+ "</div>" + "<div id='result'>Sont admis: "
				+ infocls.getNMoy() + "</div>"
				+ "<div id='result'>Pourcentage: " + infocls.getPercent()
				+ "</div>" + "</td>"

				+ "</tr>" + "</table><br/>";

		htmlBody += "<table width=100% class='tabSB'>" + "<tr  id='result'>"
				+ "<td class='tdtitletopleftC' width=1%>Rang</td>"
				+ "<td class='tdtitletopC' width=10%>Nom</td>"
				+ "<td class='tdtitletopC' width=20%>Prénoms</td>"
				+ "<td class='tdtitletopC' width=1%>Sexe</td>"
				+ "<td class='tdtitletopC' width=1%>Sco.</td>";

		// colonne des moyenne
		for (MatiereProg mat : matieres) {
			Matiere matiere = (Matiere) matdao.findObj(mat.getIntitule());
			htmlBody += "<td class='tdtitletopC' width=1%>" + matiere.getDim()
					+ "</td>";

			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
			// *********************FIN********************
		}

		htmlBody += "<td class='tdtitletopC' width=1%>Total</td>"
				+ "<td class='tdtitlerightC' width=1%>Moy Gle</td>" + "</tr>";

		for (int i = 1; i < eleves.size() + 1; i++) {
			Eleve eleve = nview.getElvAuRg("std", i);
			String matricule = "";
			try {
				matricule = eleve.getCodeInfo();

				Moyenne moy = nview.getMoyenne(new EleveClasse(matricule));
				double moytrim = moy.getMoyGen();
				String moyf = "";
				String total = "", rang = "", sexe = "";

				if (moy.hasCompose() == true) {
					moyf = formatter.format(moytrim);
					total = formatter.format(moy.getGrdTotal());
					sexe = eleve.getSexe();
					rang = MartArranger.getOrder(i, sexe);
				}

				htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>" + rang
						+ "</td>" + "<td class='tdBInf'>" + eleve.getNom()
						+ "</td>" + "<td class='tdBInf'>" + eleve.getPrenom()
						+ "</td>" + "<td class='tdBInf'>" + eleve.getSexe()
						+ "</td>" + "<td class='tdBInf'>Sco</td>";

				// colonne des moyenne
				for (MatiereProg mat : matieres) {
					InfoNote info = nview.getNotes(mat, new EleveClasse(
							matricule));
					String moyp = "";

					if (info.hasCompose() == true) {
						moyp = formatter.format(info.getPtObt());
					}

					htmlBody += "<td class='tdBInf'>" + moyp + "</td>";

					// *****POUR LA BARRE DE PORGRESSION***********
					progress.increment();
					// *********************FIN********************
				}

				htmlBody += "<td class='tdBInfC'>" + total + "</td>"
						+ "<td class='tdBInfC'>" + moyf + "</td>" + "</tr>";
				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		htmlBody += "</table>"
				+ bs.getTabSignature(DocFormat.DIR_TITUL, "tabSB");

		html += "<html><head></head><body>" + htmlBody + "</body>";
		System.out.println(html);
		// affichage des notes
		panListe.removeAll();

		panListe.setHtml(html);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MartEditorPane editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.DOCUMENTS);
				editor.setOrientation(PageFormat.LANDSCAPE);

				editor.setHtml(html);

				// on fait appelle à la visionneuse
				Preview bsh = new Preview(editor);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
				progressFrame.close();
			}
		});

		// Pour l'archive********************************
		String title = "Fiche résultat 1/ " + classe + " " + dec.toString()
				+ "/ " + annee;

		Histo his = new Histo(title, html, new DateTime());
		his.setOrientation(PageFormat.LANDSCAPE);

		histoMng.save(his);
		// fin archive**********************************

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();

		progress.stop();
		// *********************FIN********************

		// changement des affichage
		panListe.revalidate();
	}

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