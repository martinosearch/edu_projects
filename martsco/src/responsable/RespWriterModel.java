package responsable;

import interfacePerso.MartRangeable;

import java.awt.Color;
import java.io.File;

import javax.swing.SwingUtilities;

import progress.Progress;
import progress.ProgressFrame;
import abstractObject.AbstractModel;
import agent.Responsable;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.EleveClasse;
import function.Constance;
import graphicsModel.MartList;

public class RespWriterModel extends AbstractModel {
	protected String htmlBody;
	protected String html;
	protected int progmax = 0, maxtrim;
	protected Progress progress;
	protected MartEditorPane editor;

	protected MartList<EleveClasse> elevesC;

	protected static File Plogo = new File("documents/images/Plogo.jpg");
	protected MartList<Responsable> eleves;
	protected boolean photo = false;
	protected ProgressFrame frame;

	public RespWriterModel() {

	}

	public void valider(int tpe) {
		clsdao.load();
		elvdao.load();
		eleves = elvdao.getListObt();

		// ********debut de la barre de progression***************
		progress = new Progress();
		frame = new ProgressFrame();
		progress.getLoading(frame, "Chargement des données en cours");

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

		// ***************LA BARRE DE PROGRESSION********************
		progmax = eleves.size() + progress.FIN;

		progress.getProgress(frame, 0, progmax);
		progress.setColor(Color.green);

		// ********************FIN*****************************/*****

		// liste sans photos
		if (photo == false) {
			htmlBody += "<div class='saut'>";

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
					+ "</b></td>"
					+ "</tr>" + "</table>";

			htmlBody += "<table class='tabSB' width='100%'>"
					+ "<td class='tdtitletopleftC'>N° ord</td>"
					+ "<td class='tdtitletopC'>Classe</td>"
					+ "<td class='tdtitletopC'>Titulaire</td>";

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

	public void setAfficherPhoto(boolean b) {
		photo = b;
	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}
}
