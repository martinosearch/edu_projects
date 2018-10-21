package rapportExamen;

import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import etablissement.FilterEts;
import examen.Examen;
import function.MartArranger;
import function.MartFormatter;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import matiere.MatiereProg;
import note.InfoClasse;
import note.Moyenne;
import note.NoteViewer;
import note.NoteViewerExam;
import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.Rapport;
import classe.Classe;
import classe.Niveau;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;

public class FicheRst2Exam extends MartFrame {
	private String annee = "", classe = "", examen = "";
	private int trimestre = 1;
	private ArrayList<EleveClasse> eleves;
	private String htmlBody;
	private String html = "";
	private HistoManager histoMng;
	private int model = 1;
	private NoteViewer nview;
	private DecimalFormat formatter = new DecimalFormat("00.00");

	private String strEts = "";
	private Progress progress;
	private MartList<String> listeEts = new MartList<String>();
	private int progmax;
	private DocFormat bs;
	protected MartFrame progressFrame;
	private DAO elvdao, nivdao;
	private DAO elvclsdao;
	private DAO examdao;
	private ConfigExamen conf;
	private int evaluation;
	private Classe superClasse;
	private int typeRapport;
	private String etablissement;

	// constructeur avec paramètre
	public FicheRst2Exam() {
		histoMng = new HistoManager();
	}

	// Pour créer le résultat des examens
	public synchronized void createResutat(String exam) {
		examen = exam;
		typeRapport = Rapport.EXAMEN;

		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		examdao = DAOFactory.getDAO(DAO.EXAMEN);

		elvdao.load();
		examdao.load();

		Examen superExam = (Examen) examdao.findObj(examen);
		model = superExam.getModelRap();

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
			model = Rapport.MODEL_PRIM;
		} else {
			model = Rapport.MODEL_SECOND;
		}

		load();
		refresh();
	}

	@Override
	public void load() {
		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			bs = new BullFormat(trimestre, annee);
			bs.setClasse(superClasse);
		} else {
			bs = new RelFormat(examen);
			conf = new ConfigExamen(examen);
			listeEts = conf.relConfig.getEtsPerso();

			for (String str : listeEts) {
				strEts += str + ", ";
			}
		}
		bs.load();
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

		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			elvclsdao.load(null, classe, trimestre, annee);
		} else {
			elvclsdao.loadExam(examen);
		}

		FilterEts filter = new FilterEts(etablissement);
		eleves = filter.getListe(elvclsdao.getListObt());

		int nbreElv = eleves.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = (2 * nbreElv) + progress.FIN;
		progress.getProgress(progressFrame, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************/*****

		// Chargement des notes
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
		htmlBody += "<div class='saut'>" + "<div id='sautligne'></div>"
				+ "<div class='cadre'>";// le cadre

		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			htmlBody += bs.writeEntete("RESULTAT " + nview.getTitreRapport());
		} else {
			htmlBody += ((RelFormat) bs).writeEntete("RESULTAT "
					+ nview.getTitreRapport());
		}
		// fin des en-têtes

		htmlBody += "<br/>" + "<table width=100% class='tabSB'><tr"
				+ "<td class='tdSB'>";

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
					+ "<td class='tdSBLC' width=10%>Sexe</td>"
					+ "<td class='tdSBLC' width=10%>Total</td>"
					+ "<td class='tdSBLC' width=10%>Moyenne</td>";

			if (listeEts.size() > 1) {
				htmlBody += "<td class='tdSBLC' width=15%>Etablissement</td>";
			}

			htmlBody += "</tr>";

			for (int i = 1; i < eleves.size() + 1; i++) {
				try {
					Eleve eleve = nview.getElvAuRg("std", i);
					String matricule = eleve.getCodeInfo();

					Moyenne moy = nview.getMoyenne(new EleveClasse(matricule));

					/*
					 * System.out
					 * .println("===================================>> Jai: " +
					 * moy.getEvaluation() + " " + moy.getGrdTotal() + " " +
					 * moy.getId());
					 */

					// On affiche la moyenne si l'élève a composé
					if (moy.hasCompose() == true) {
						String totF = MartFormatter.correctDecimal(String
								.valueOf(moy.getGrdTotal()));
						String moyf = formatter.format(moy.getMoyGen());
						String sexe = eleve.getSexe();

						String rang = MartArranger.getOrder(i, sexe);

						htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
								+ rang + "</td>" + "<td class='tdBInf'>"
								+ eleve.getNom() + "</td>"
								+ "<td class='tdBInf'>" + eleve.getPrenom()
								+ "</td>" + "<td class='tdBInf'>"
								+ eleve.getSexe() + "</td>"
								+ "<td class='tdBInf'>" + totF + "</td>"
								+ "<td class='tdBInf'>" + moyf + "</td>";

						if (listeEts.size() > 1) {
							htmlBody += "<td class='tdBInf' width=10%>"
									+ eleve.getEts() + "</td>";
						}

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

			if (listeEts.size() > 1) {
				htmlBody += "<td class='tdSBLC' width=15%>Etablissement</td>";
			}

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

						if (listeEts.size() > 1) {
							htmlBody += "<td class='tdBInf' width=10%>"
									+ eleve.getEts() + "</td>";
						}

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

		htmlBody += "<div id='sautligne'></div>";

		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			htmlBody += bs.getTabSignature(DocFormat.DIR_TITUL, "tabSB");
		} else {
			htmlBody += bs.getTabSignature(DocFormat.PRESI, "tabSB");
		}

		htmlBody += "</div>"; // fin cadre

		if (typeRapport != Rapport.EVAL_TRIMESTRIELLE) {
			htmlBody += "<div id='footer'>" + strEts + "</div>";
		}

		htmlBody += "<div id='sautligne'></div>"; // le saut de ligne

		html += "<html><head></head><body>" + htmlBody + "</body>";

		System.out.println(html);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MartEditorPane editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.DOCUMENTS);
				editor.setHtml(html);

				// on fait appelle à la visionneuse
				Preview bsh = new Preview(editor);

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

	public void setEtablissement(String ets) {
		etablissement = ets;
	}

}
