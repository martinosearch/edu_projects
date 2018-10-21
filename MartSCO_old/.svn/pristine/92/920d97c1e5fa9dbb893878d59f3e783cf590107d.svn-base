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

public class ScoWriterModel extends AbstractModel {
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
	private boolean photo = false;
	private ProgressFrame pframe;

	public ScoWriterModel() {
		histoMng = new HistoManager();

		matdao = DAOFactory.getDAO(DAO.MATIERE);
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		promodao = DAOFactory.getDAO(DAO.PROMO_ELEVE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
	}

	public void valider(int tpe) {
		clsdao.load();
		superClasse = (Classe) clsdao.findObj(classe);

		elvdao.load();
		eleves = elvdao.getListObt();

		// ********debut de la barre de progression***************
		pframe = new ProgressFrame();
		progress = new Progress();
		progress.getLoading(pframe, "Chargement des données en cours");

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
		int nbreElv = elevesC.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbreElv + progress.FIN;

		progress.getProgress(pframe, 0, progmax);
		progress.setColor(Color.green);

		// ********************FIN*****************************/*****

		DocFormat bs = new BullFormat(trimestre, annee);
		bs.setClasse(superClasse);
		bs.load();

		// liste sans photos
		if (photo == false) {
			htmlBody += "<div class='saut'>";
			htmlBody += bs.writeEntete();

			// reférence de l'établissement
			htmlBody += "<table width='100%'>"// table ref ets
					+ "<tr>"
					+ "<td class='tdSB' width='20%'></td>"
					+ "<td class='tdSB' width='60%'>"
					+ "<div id='titre'>"
					+ (Constance.INITIALE).replaceAll("[\\s]", "&#160;")
					+ "&#160;"
					+ (Constance.NOM).replaceAll("[\\s]", "&#160;")
					+ "</div>" + "<div id='adresse2'>"
					+ Constance.QUARTIER
					+ "</div>" + "<div id='adresse2'>"
					+ Constance.BP
					+ " / Tel: " + Constance.TEL + "</div>" + "</td>"

					+ "<td width='20%'>" + "</td>" + "</tr>" + "</table>";// fin
																			// table
																			// des
																			// réfférence
																			// de
																			// l'etabls

			htmlBody += "<table class='tabSB' width='100%'>"// table ref trim
					+ "<tr>"
					+ "<td width='30%'></td>"
					+ "<td id='titreCd' width='40%'>LISTE NOMINATIVE</td>"
					+ "<td width='30%'></td>" + "</tr>"

					+ "<tr>" + "<td>" + "<i>Année-Scolaire: </i><b>"
					+ annee
					+ "</b></td>" + "<td><i>Classe: </i><b>"
					+ classe
					+ "</b></td>" + "<td><i>Effectif: </i><b>"
					+ nbreElv
					+ "</b></td>" + "</tr>" + "</table>";

			htmlBody += "<table class='tabSB' width='100%'>"
					+ "<td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
					+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
					+ "<td class='tdtitletopC'>N°Mle</td><td class='tdtitletopC'>Statut</td>"
					+ "<td class='tdtitletopC'>Ancienneté</td>";

			int num = 0;
			for (EleveClasse eleve : elevesC) {
				try {
					String statut = "";
					String gradeStr = "";
					int grade = 0;
					try {
						statut = eleve.getStatut().toString();
						grade = eleve.getStatut().intValue();

						if (grade > 0) {
							gradeStr = String.valueOf(grade);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					num++;
					htmlBody += "<tr>" + "<td class='tdSBT'>" + num + "</td>"
							+ "<td class='tdBInf'>" + eleve.getNom() + "</td>"
							+ "<td class='tdBInf'>" + eleve.getPrenom()
							+ "</td>" + "<td class='tdBInf'>" + eleve.getSexe()
							+ "</td>" + "<td class='tdBInf'>"
							+ eleve.getCodeInfo() + "</td>"
							+ "<td class='tdBInf'>" + statut + "</td>"
							+ "<td class='tdBInf'>" + gradeStr + "</td>"
							+ "</tr>";
				} catch (Exception e) {

				}
			}

			htmlBody += "</table>";

			htmlBody += bs.getTabSignature(DocFormat.DIR, "tabSB");

			htmlBody += "</div>";
		}

		// la liste avec photos***********************
		if (photo == true) {
			htmlBody += "<div class='saut'>";
			htmlBody += bs.writeEntete();

			// reférence de l'établissement
			htmlBody += "<table width='100%'>"// table ref ets
					+ "<tr>"
					+ "<td class='tdSB' width='20%'></td>"
					+ "<td class='tdSB' width='60%'>"
					+ "<div id='titre'>"
					+ (Constance.INITIALE).replaceAll("[\\s]", "&#160;")
					+ "&#160;"
					+ (Constance.NOM).replaceAll("[\\s]", "&#160;")
					+ "</div>" + "<div id='adresse2'>"
					+ Constance.QUARTIER
					+ "</div>" + "<div id='adresse2'>"
					+ Constance.BP
					+ " / Tel: " + Constance.TEL + "</div>" + "</td>"

					+ "<td width='20%'>" + "</td>" + "</tr>" + "</table>";// fin
																			// table
																			// des
																			// réfférence
																			// de
																			// l'etabls

			htmlBody += "<table class='tabSB' width='100%'>"// table ref trim
					+ "<tr>"
					+ "<td width='30%'></td>"
					+ "<td id='titreCd' width='40%'>LISTE NOMINATIVE</td>"
					+ "<td width='30%'></td>" + "</tr>"

					+ "<tr>" + "<td>" + "<i>Année-Scolaire: </i><b>"
					+ annee
					+ "</b></td>" + "<td><i>Classe: </i><b>"
					+ classe
					+ "</b></td>" + "<td><i>Effectif: </i><b>"
					+ nbreElv
					+ "</b></td>" + "</tr>" + "</table>";

			htmlBody += "<table class='tabSB' width='100%'>"
					+ "<td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
					+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
					+ "<td class='tdtitletopC'>Statut</td>"
					+ "<td class='tdtitletopC'>Ancienneté</td>"
					+ "<td class='tdtitletopC'>Photo & N°Mle</td>";

			int num2 = 0;
			for (EleveClasse eleve : elevesC) {
				try {
					num2++;

					String statut = "";
					String gradeStr = "";
					int grade = 0;
					try {
						statut = eleve.getStatut().toString();
						grade = eleve.getStatut().intValue();

						if (grade > 0) {
							gradeStr = String.valueOf(grade);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					// **********DEFINIT LA PHOTO DE L'ELEVE*************
					File photo = new File("documents/photos/"
							+ eleve.getCodeInfo() + ".jpg");
					// ******************************************************************
					htmlBody += "<tr>" + "<td class='tdSBT'>"
							+ num2
							+ "</td>"
							+ "<td class='tdBInf'>"
							+ eleve.getNom()
							+ "</td>"
							+ "<td class='tdBInf'>"
							+ eleve.getPrenom()
							+ "</td>"
							+ "<td class='tdBInf'>"
							+ eleve.getSexe()
							+ "</td>"
							+ "<td class='tdBInf'>"
							+ statut
							+ "</td>"
							+ "<td class='tdBInf'>"
							+ gradeStr
							+ "</td>"
							+ "<td class='tdBInf'><div align='center'><img src='"
							+ photo.toURI() + "'/></div>"
							+ "<div align='center'>" + eleve.getCodeInfo()
							+ "</div></td>" + "</tr>";
				} catch (Exception e) {

				}
			}

			htmlBody += "</table>";

			htmlBody += bs.getTabSignature(DocFormat.DIR, "tabSB");

			htmlBody += "</div>";
		}

		html += "<html><head></head><body>" + htmlBody + "</body></html>";

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.DOCUMENTS);
				editor.setHtml(html);

				// on fait appelle à la visionneuse
				Preview bsh = new Preview(editor);
			}
		});

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

	public void setAfficherPhoto(boolean b) {
		photo = b;
	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}
}
