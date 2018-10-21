package rapportBulletin;

import images.PictureFinder;
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
import graphicsModel.MartList;

public class ListeWriterModel extends AbstractModel {
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

	public ListeWriterModel() {
		histoMng = new HistoManager();
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		promodao = DAOFactory.getDAO(DAO.PROMO_ELEVE);
		andao = DAOFactory.getDAO(DAO.ANNEE);
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

		System.out.println("===================>> Photos: " + photo);

		// liste sans photos
		if (photo == false) {
			htmlBody += "<div class='saut'>";
			htmlBody += bs.writeEntete("LISTE NOMINATIVE");

			htmlBody += "<table class='tabSB' width='100%'>"// table ref trim
					+ "<tr>" + "<td>" + "<i>Année-Scolaire: </i><b>"
					+ annee
					+ "</b></td>" + "<td><i>Classe: </i><b>"
					+ classe
					+ "</b></td>" + "<td><i>Effectif: </i><b>"
					+ nbreElv
					+ "</b></td>" + "</tr>" + "</table>";

			htmlBody += "<table class='ficheNoteExemplaire' width='100%'>"
					+ "<tr><td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
					+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
					+ "<td class='tdtitletopC'>N°Mle</td></tr>";

			int num = 0;
			for (EleveClasse eleve : elevesC) {
				try {
					num++;
					htmlBody += "<tr>" + "<td class='tdSBT'>" + num + "</td>"
							+ "<td class='tdBInf'>" + eleve.getNom() + "</td>"
							+ "<td class='tdBInf'>" + eleve.getPrenom()
							+ "</td>" + "<td class='tdBInf'>" + eleve.getSexe()
							+ "</td>" + "<td class='tdBInf'>"
							+ eleve.getCodeInfo() + "</td></tr>";
				} catch (Exception e) {
					e.printStackTrace();
				}

				progress.increment();
			}

			htmlBody += "</table>";

			htmlBody += bs.getTabSignature(DocFormat.DIR, "tabSB");

			htmlBody += "</div>";
		}
		// la liste avec photos***********************

		if (photo == true) {
			htmlBody += "<div class='saut'>";
			htmlBody += bs.writeEntete("LISTE NOMINATIVE");

			htmlBody += "<table class='tabSB' width='100%'>" + "<tr>" + "<td>"
					+ "<i>Année-Scolaire: </i><b>" + annee + "</b></td>"
					+ "<td><i>Classe: </i><b>" + classe + "</b></td>"
					+ "<td><i>Effectif: </i><b>" + nbreElv + "</b></td>"
					+ "</tr>" + "</table>";

			htmlBody += "<table class='tabSB' width='100%'>"
					+ "<tr><td class='tdtitletopleftC'>N° ord</td><td class='tdtitletopC'>Nom</td>"
					+ "<td class='tdtitletopC'>Prénoms</td><td class='tdtitletopC'>Sexe</td>"
					+ "<td class='tdtitletopC'>Photo & N°Mle</td></tr>";

			int num2 = 0;
			for (EleveClasse eleve : elevesC) {
				try {
					num2++;
					// **********DEFINIT LA PHOTO DE L'ELEVE*************
					File photo = new PictureFinder().getPhotoEleve(eleve
							.getCodeInfo());
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
							+ eleve.getCodeInfo() + "</div></td></tr>";
				} catch (Exception e) {
					e.printStackTrace();
				}

				progress.increment();

				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			htmlBody += "</table>";

			htmlBody += bs.getTabSignature(DocFormat.DIR, "tabSB");

			htmlBody += "</div>";
		}

		html += "<html><head></head><body>" + htmlBody + "</body></html>";

		pframe.close();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.DOCUMENTS);
				editor.setHtml(html);
				editor.revalidate();

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
