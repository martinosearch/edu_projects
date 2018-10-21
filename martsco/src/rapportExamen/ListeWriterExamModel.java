package rapportExamen;

import interfacePerso.MartRangeable;

import java.awt.Color;
import java.io.File;

import javax.swing.SwingUtilities;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
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

public class ListeWriterExamModel extends AbstractModel {
	String htmlBody;
	String html;
	int progmax = 0, maxtrim;
	public Progress progress;
	private MartEditorPane editor;

	private MartList<EleveClasse> elevesC;
	private MartList<Eleve> listeTrie;

	private static File Plogo = new File("documents/images/Plogo.jpg");
	private HistoManager histoMng;
	private Classe superClasse;
	private MartList<Eleve> eleves;
	private boolean photo = false;
	private ProgressFrame pFrame;

	public ListeWriterExamModel() {
		histoMng = new HistoManager();
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		promodao = DAOFactory.getDAO(DAO.PROMO_ELEVE);
	}

	public void valider(int tpe) {
		elvdao.load();
		pFrame = new ProgressFrame();

		// ********debut de la barre de progression***************
		progress = new Progress();
		progress.getLoading(pFrame, "Chargement des données en cours");

		// thread qui lance la création des Liste
		new Thread(new Runnable() {

			public void run() {
				elvclsdao.loadExam(examen);
				elevesC = elvclsdao.getListObt();
				System.out.println("l'examen " + examen + "taille: "
						+ elevesC.size());
				listeTrie = new MartList<Eleve>();
				// on crée les bulletins
				for (EleveClasse eleve : elevesC) {
					try {
						Eleve superEleve = (Eleve) elvdao.findObj(eleve
								.getCodeInfo());
						if (superEleve.getEts().equals(etablissement)) {
							listeTrie.add(superEleve);
						}
					} catch (Exception e) {

					}
				}
				createListe();
			}
		}).start();

	}

	public void createListe() {
		html = "";
		htmlBody = "";
		int nbreElv = listeTrie.size();

		// ***************LA BARRE DE PROGRESSION********************
		progmax = nbreElv + progress.FIN;

		progress.getProgress(pFrame, 0, progmax);
		progress.setColor(Color.green);

		// ********************FIN*****************************/*****

		DocFormat bs = new RelFormat(examen);
		bs.load();

		// liste sans photos
		if (photo == false) {
			htmlBody += "<div class='saut'>";
			htmlBody += bs.writeEntete();

			// reférence de l'établissement
			htmlBody += bs.writeRefEts();

			// htmlBody += "<td width='20%'>" + "</td>" + "</tr>" +
			// "</table>";// fin
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

					+ "<tr>" + "<td>" + "<i>Examen: </i><b>"
					+ examen
					+ "</b></td>"
					+ "<td><i>Etablissement: </i><b>"
					+ etablissement + "</b></td>"
					+ "<td><i>Effectif: </i><b>"
					+ nbreElv + "</b></td>" + "</tr>" + "</table>";

			htmlBody += "<table class='tabSB' width='100%'>"
					+ "<td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
					+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
					+ "<td class='tdtitletopC'>N°Mle</td>";

			int num = 0;
			for (Eleve eleve : listeTrie) {
				try {
					num++;
					htmlBody += "<tr>" + "<td class='tdSBT'>" + num + "</td>"
							+ "<td class='tdBInf'>" + eleve.getNom() + "</td>"
							+ "<td class='tdBInf'>" + eleve.getPrenom()
							+ "</td>" + "<td class='tdBInf'>" + eleve.getSexe()
							+ "</td>" + "<td class='tdBInf'>"
							+ eleve.getCodeInfo() + "</td>" + "</tr>";

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
					+ "<td class='tdtitletopC'>Photo & N°Mle</td>";

			int num2 = 0;
			for (EleveClasse eleve : elevesC) {
				try {
					num2++;
					// **********DEFINIT LA PHOTO DE L'ELEVE*************
					File photo = new File("documents/photos/"
							+ eleve.getCodeInfo() + ".jpg");
					// ******************************************************************
					htmlBody += "<tr>"
							+ "<td class='tdSBT'>"
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
							+ "<td class='tdBInf'><div align='center'><img src='"
							+ photo.toURI() + "'/></div><div align='center'>"
							+ eleve.getCodeInfo() + "</div></td>" + "</tr>";
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
