package rapportExamen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import matiere.Matiere;
import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerExam;

import org.joda.time.DateTime;

import abstractObject.Rapport;
import classe.Classe;
import classe.Niveau;
import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;
import editeur.EditorPanePrinter;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import etablissement.FilterEts;
import examen.Examen;
import function.MartArranger;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

public class FicheRst1Exam extends MartFrame {
	private JLabel lbTitre = new JLabel();
	private JPanel panTitre = new JPanel();
	private MartEditorPane panListe;

	private JPanel panBut = new JPanel();

	private int trimestre = 1;

	private DAO examdao, matpdao, matdao, elvdao, elvclsdao;

	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private ArrayList<MatiereProg> matieres;
	private ArrayList<EleveClasse> eleves;
	private String htmlBody;
	private String html = "";

	private String examen;
	private String annee;

	private NoteViewer nview;
	private DecimalFormat formatter = new DecimalFormat("00.00");
	private File Plogo = new File("documents/images/Plogo.jpg");

	private Progress progress;
	private Examen superExamen;
	private DocFormat bs;
	private HistoManager histoMng;

	private MartEditorPane editor;
	private ProgressFrame progressFrame;
	private int typeRapport;
	private Classe superClasse;
	private String classe;
	private int evaluation;
	private DAO nivdao;
	private ConfigExamen conf;
	private MartList<String> listeEts;
	private String etablissement;

	// constructeur avec paramètre
	public FicheRst1Exam() {
		this.setTitle("RESULTAT PAR MATIERES ET PAR ORDRE DECROISSANT DE MERITE");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		DAOFactory.getDAO(DAO.NOTE_EXAM);

		histoMng = new HistoManager();

		examdao.load();
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

		this.addWindowListener(this);
		this.getContentPane().add(new JScrollPane(panListe),
				BorderLayout.CENTER);
		this.getContentPane().add(panBut, BorderLayout.NORTH);
	}

	public void createResutat(String exam) {
		examen = exam;

		superExamen = (Examen) examdao.findObj(examen);
		bs = new RelFormat(examen);
		bs.load();

		load();
		refresh();
	}

	// Pour créer le résultat des évaluation
	public void createResutat(Classe cl, int eval, int trim, String an) {
		typeRapport = Rapport.EVAL_TRIMESTRIELLE;
		superClasse = cl;
		classe = cl.getIntitule();
		evaluation = eval;
		trimestre = trim;
		annee = an;

		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);

		elvdao.load();
		nivdao.load();

		Niveau niv = (Niveau) nivdao.findObj(superClasse.getNiveau());

		if (niv.getTypeEns().equals("PRIM")) {
		} else {
		}

		load();
		refresh();
	}

	public void load() {
		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			bs = new BullFormat(trimestre, annee);
			bs.setClasse(superClasse);
		} else {
			bs = new RelFormat(examen);
			conf = new ConfigExamen(examen);
			listeEts = conf.relConfig.getEtsPerso();

			for (String str : listeEts) {
			}
		}
		bs.load();
	}

	// nous définissons la mise ajour du tableau
	public void refresh() {
		html = new String();
		htmlBody = new String();

		panEtat.removeAll();

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();

		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		matpdao.loadExam(examen);
		matieres = matpdao.getList();

		// la listes des élèves
		elvclsdao.loadExam(examen);

		FilterEts filter = new FilterEts(etablissement);
		eleves = filter.getListe(elvclsdao.getListObt());

		int nbreElv = eleves.size();
		int nbreMat = matieres.size();

		// ***************LA BARRE DE PROGRESSION********************
		int progmax = (nbreMat + nbreElv * (nbreMat + 1))
				+ (nbreMat * (1 + nbreElv)) + progress.FIN;

		progress.getProgress(this, 0, progmax);
		progress.setColor(Color.green);

		nview = new NoteViewerExam();
		nview.setEtablissement(etablissement);

		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			nview.load(evaluation, classe, trimestre, annee);
		} else {
			nview.load(examen);
		}

		nview.setProgressAvancer(progress.getAvancer());

		InfoClasse infocls = nview.getInfoClasse();

		// créons le tableau
		htmlBody += bs.writeEntete(nview.getTitreRapport());// table
		// des
		// en-têtes

		htmlBody += "<table width=100% class='tabSB'>" + "<tr id='result'>"
				+ "<td class='tdSB'>" + "<div id='result'>Effectif: "
				+ infocls.getEff() + "</div>"
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
				+ "<td class='tdtitletopC' width=1%>Ets</td>";

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
			String matricule = eleve.getCodeInfo();

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
					+ "</td>" + "<td class='tdBInf'>" + eleve.getEts()
					+ "</td>";

			// colonne des moyenne
			for (MatiereProg mat : matieres) {
				InfoNote info = nview.getNotes(mat, new EleveClasse(matricule));
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
		}

		htmlBody += "</table>" + bs.getTabSignature(DocFormat.PRESI, "tabSB");

		html += "<html><head></head><body>" + htmlBody + "</body>";
		System.out.println(html);
		// affichage des notes

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				editor = new MartEditorPane();
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
		String title = "Fiche résultat 1/ " + examen;

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
	public void actionPerformed(ActionEvent e) {
		e.getSource();

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
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

	public void setEtablissement(String ets) {
		etablissement = ets;
	}

}
