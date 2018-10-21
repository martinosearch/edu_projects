package rapportBulletin;

import interfacePerso.MartRangeable;

import java.awt.Color;
import java.io.File;

import javax.swing.SwingUtilities;

import progress.Progress;
import progress.ProgressFrame;
import abstractObject.AbstractModel;
import classe.Classe;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.Constance;
import graphicsModel.MartList;

public class FicheNoteWriterModel extends AbstractModel {
	String htmlBody;
	String html;
	int progmax = 0, maxtrim;
	public Progress progress;
	private MartEditorPane editor;

	private MartList<EleveClasse> elevesC;

	private static File Plogo = new File("documents/images/Plogo.jpg");
	private HistoManager histoMng;
	private Classe superClasse;
	private MartList<Eleve> eleves;
	private int nColonne = 4;
	private ProgressFrame pFrame;

	public FicheNoteWriterModel() {
		histoMng = new HistoManager();
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		promodao = DAOFactory.getDAO(DAO.PROMO_ELEVE);
	}

	public void valider(int tpe) {
		clsdao.load();
		superClasse = (Classe) clsdao.findObj(classe);

		elvdao.load();
		eleves = elvdao.getListObt();

		// ********debut de la barre de progression***************
		pFrame = new ProgressFrame();
		progress = new Progress();
		progress.getLoading(pFrame, "Chargement des données en cours");

		// thread qui lance la création des Liste
		new Thread(new Runnable() {
			public void run() {
				elvclsdao.load(new String(), classe, trimestre, annee);
				elevesC = elvclsdao.getListObt();

				// on crée les bulletins
				createListe();
			}
		}).start();

	}

	public void createListe() {
		html = "";
		htmlBody = "";
		final int nbreElv = elevesC.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbreElv + 1;

		progress.getProgress(pFrame, 0, progmax);
		progress.setColor(Color.green);

		// ********************FIN*****************************/*****

		new Thread(new Runnable() {
			public void run() {
				DocFormat bs = new BullFormat(trimestre, annee);
				bs.setClasse(superClasse);
				bs.load();

				// fiche a 4 notes
				if (nColonne != 2) {
					htmlBody += "<div class='saut'>";

					// reférence de l'établissement
					htmlBody += bs.writeEntete("FICHE DE NOTES");

					htmlBody += "<table class='tabSB' width='100%'>" + "<td>"
							+ "<i>Année-Scolaire: </i><b>" + annee
							+ "</b></td>" + "<td><i>Classe: </i><b>" + classe
							+ "</b></td>" + "<td><i>Effectif: </i><b>"
							+ nbreElv + "</b></td>" + "</tr>"

							+ "<tr>" + "<td colspan='2'>"
							+ "<i><u>Matière </u></i></td>"
							+ "<td><i><u>Chargé:</u></i></td>" + "</tr>"
							+ "</table>";

					htmlBody += "<table class='ficheNoteExemplaire' width='100%'>"
							+ "<td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
							+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
							+ "<td class='tdtitletopC'>Int1</td>"
							+ "<td class='tdtitletopC'>Int2</td>"
							+ "<td class='tdtitletopC'>Int3</td>"
							+ "<td class='tdtitletopC'>Dev</td>"
							+ "<td class='tdtitletopC'>Compo</td>";

					int num = 0;
					for (EleveClasse el : elevesC) {
						try {
							Eleve eleve = eleves.find(el.getCodeInfo());
							num++;
							htmlBody += "<tr>" + "<td class='tdSBT'>" + num
									+ "</td>" + "<td class='tdBInf'>"
									+ eleve.getNom() + "</td>"
									+ "<td class='tdBInf'>" + eleve.getPrenom()
									+ "</td>" + "<td class='tdBInf'>"
									+ eleve.getSexe() + "</td>"
									+ "<td class='tdBInf'></td>"
									+ "<td class='tdBInf'></td>"
									+ "<td class='tdBInf'></td>"
									+ "<td class='tdBInf'></td>"
									+ "<td class='tdBInf'></td></tr>";
						} catch (Exception e) {
							e.printStackTrace();
						}

						// pour la progression
						progress.increment();

						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					htmlBody += "</table>";

					htmlBody += "</div>";
				} else {
					// fiche à 2 notes
					htmlBody += "<div class='saut'>";

					// reférence de l'établissement
					htmlBody += "<table width='100%'>"// table ref ets
							+ "<tr>"
							+ "<td class='tdSB' width='20%'></td>"
							+ "<td class='tdSB' width='60%'>"
							+ "<div id='titre'>"
							+ (Constance.INITIALE)
									.replaceAll("[\\s]", "&#160;")
							+ "&#160;"
							+ (Constance.NOM).replaceAll("[\\s]", "&#160;")
							+ "</div>"
							+ "<div id='adresse2'>"
							+ Constance.QUARTIER
							+ "</div>"
							+ "<div id='adresse2'>" + Constance.BP
							+ " / Tel: "
							+ Constance.TEL + "</div>" + "</td>"

							+ "<td width='20%'>" + "</td>"
							+ "</tr>"
							+ "</table>";// fin
											// table
											// des
											// réfférence
											// de
											// l'etabls

					htmlBody += "<table class='tabSB' width='100%'>"// table ref
																	// trim
							+ "<tr>"
							+ "<td width='30%'></td>"
							+ "<td id='titreCd' width='40%'>FICHE DE NOTES</td>"
							+ "<td width='30%'></td>"
							+ "</tr>"

							+ "<tr>"
							+ "<td>"
							+ "<i>Année-Scolaire: </i><b>"
							+ annee
							+ "</b></td>"
							+ "<td><i>Classe: </i><b>"
							+ classe
							+ "</b></td>"
							+ "<td><i>Effectif: </i><b>"
							+ nbreElv
							+ "</b></td>"
							+ "</tr>"

							+ "<tr>"
							+ "<td colspan='2'>"
							+ "<i><u>Matière </u></i><br/>________________________</td>"
							+ "<td><i><u>Chargé du cours </u></i><br/>_____________________</td>"
							+ "</tr>" + "</table>";

					htmlBody += "<table class='ficheNoteExemplaire' width='100%'>"
							+ "<td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
							+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
							+ "<td class='tdtitletopC'>N°Mle</td>"
							+ "<td class='tdtitletopC'>Note Cl.</td>"
							+ "<td class='tdtitletopC'>Compo</td>";

					int num1 = 0;
					for (EleveClasse el : elevesC) {
						try {
							Eleve eleve = eleves.find(el.getCodeInfo());
							num1++;
							htmlBody += "<tr>" + "<td class='tdSBT'>" + num1
									+ "</td>" + "<td class='tdBInf'>"
									+ eleve.getNom() + "</td>"
									+ "<td class='tdBInf'>" + eleve.getPrenom()
									+ "</td>" + "<td class='tdBInf'>"
									+ eleve.getSexe() + "</td>"
									+ "<td class='tdBInf'>"
									+ eleve.getCodeInfo() + "</td>"
									+ "<td class='tdBInf'></td>"
									+ "<td class='tdBInf'></td>" + "</tr>";
						} catch (Exception e) {
							e.printStackTrace();
						}

						// pour la progression
						progress.increment();
					}

					htmlBody += "</table>";

					htmlBody += "</div>";
				}

				html += "<html><head></head><body>" + htmlBody
						+ "</body></html>";

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						editor = new MartEditorPane();
						MartStyle.setPadding(0);
						MartStyle.setRowheight(5);
						editor.setStyle(MartStyle.DOCUMENTS);
						editor.setHtml(html);

						// on fait appelle à la visionneuse
						Preview bsh = new Preview(editor);

						// pour la progression
						progress.increment();
						pFrame.close();
					}
				});
			}
		}).start();

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
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
