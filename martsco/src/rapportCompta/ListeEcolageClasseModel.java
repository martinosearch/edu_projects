package rapportCompta;

import ecolage.EcoViewer;
import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import ecolage.InfoEcoEleve;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.Constance;
import function.MartFormatter;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Formatter;

import javax.swing.SwingUtilities;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class ListeEcolageClasseModel extends AbstractModel {
	private DAO elvdao;
	private String htmlBody;
	private String html = "";
	private HistoManager histoMng;
	private Progress progress;
	private ProgressFrame progressFrame;
	private int progmax;
	private DocFormat bs;
	private String titre;
	private Eleve superEleve;
	private EcolageComputer computer;
	private Thread treat;
	private MartList<EleveClasse> eleves;
	private EcoViewer ecoViewer;

	// constructeur avec paramètre
	public ListeEcolageClasseModel() {
		histoMng = new HistoManager();

		initComponent();
	}

	// creation des composants
	public void initComponent() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		elvdao.load();
	}

	public void createListe() {
		// on recupere la liste des élève
		ecoViewer = new EcoViewer();
		ecoViewer.load(annee);
		ecoViewer.loadInfoClasse(classe);

		elvclsdao.load(null, classe, 0, annee);
		eleves = elvclsdao.getListObt();

		titre = "FICHE DE PAYEMENT DE L'ECOLAGE";

		html = "";
		htmlBody = "";

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();

		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		// ***************LA BARRE DE PROGRESSION********************
		progmax = eleves.size() + 1;
		progress.getProgress(progressFrame, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************/*****

		treat = new Thread(new Runnable() {
			private DecimalFormat formatter = new DecimalFormat("00.00");

			public void run() {
				bs = new BullFormat(0, annee);
				// créons le tableau
				htmlBody += "<div class='saut'>" + "<div id='sautligne'></div>"
						+ "<div class='cadre'>";// le cadre

				// reférence de l'établissement
				htmlBody += bs.writeEntete(titre);
				// fin des en-têtes

				htmlBody += "<br/>" + "<table width=100% class='tabSB'>";

				htmlBody += "<tr><td id='result' width='20%'>Classe:</td><td width='60%'>"
						+ classe + "</td></tr>";
				htmlBody += "<tr><td id='result'>Effectif:</td><td>"
						+ eleves.size() + "</td></tr>";
				htmlBody += "<tr><td id='result'>Frais par élève:</td><td>"
						+ MartFormatter.correctDecimal(String.valueOf(ecoViewer
								.getEcolageClasse())) + "</td></tr>";
				htmlBody += "<tr><td id='result'>Montant total à payer: </td><td>"
						+ MartFormatter.correctDecimal(String.valueOf(ecoViewer
								.getTotalClasse())) + "</td></tr>";
				htmlBody += "<tr><td id='result'>Montant payé:</td><td>"
						+ MartFormatter.correctDecimal(String.valueOf(ecoViewer
								.getTotalPayeClasse())) + "</td></tr>";
				htmlBody += "<tr><td id='result'>Montant restant:</td><td>"
						+ MartFormatter.correctDecimal(String.valueOf(ecoViewer
								.getTotalRestantClasse())) + "</td></tr>";
				htmlBody += "<tr><td id='result'>Taux de recouvrement:</td><td>"
						+ formatter.format(ecoViewer.getTauxRecEcolageClasse())
						+ " % </td></tr>";
				htmlBody += "</table>";

				htmlBody += "<table width=100% class='tabSB'>"
						+ "<tr  id='result'>"
						+ "<td class='tdBC' width=7%>N° d'ordre</td>"
						+ "<td class='tdSBLC' width=20%>Nom</td>"
						+ "<td class='tdSBLC' width=35%>Prénoms</td>"
						+ "<td class='tdSBLC' width=8%>Sexe</td>"
						+ "<td class='tdSBLC' width=15%>Total ("
						+ Constance.getDeviseMonaie() + ")</td>"
						+ "<td class='tdSBLC' width=15%>Reste à payer</td>";

				htmlBody += "</tr>";

				int i = 0;
				computer = new EcolageComputer();
				computer.setAnnee(annee);

				for (EleveClasse eleve : eleves) {
					InfoEcoEleve info = ecoViewer.findInfo(eleve.getCodeInfo());

					System.out.println("info: " + info);

					try {
						htmlBody += "<tr  id='result'>"
								+ "<td class='tdSBT'>"
								+ i
								+ "</td><td class='tdBInf'>"
								+ eleve.getNom()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ eleve.getPrenom()
								+ "</td>"
								+ "<td class='tdBInf'>"
								+ eleve.getSexe()
								+ "</td>"
								+ "<td class='tdBInf'><b>"
								+ MartFormatter.correctDecimal(String
										.valueOf(info.getRegle()))
								+ "</b></td>"
								+ "<td class='tdBInf'><b>"
								+ MartFormatter.correctDecimal(String
										.valueOf(info.getReste()))
								+ "</b></td>";

					} catch (Exception e) {
						e.printStackTrace();
					}

					// *****POUR LA BARRE DE PORGRESSION***********
					progress.increment();
					// *********************FIN********************

				}// fin for élève

				htmlBody += "</table>";
				// htmlBody += bs.getTabSignature(DocFormat.DIR, "tabSB");

				htmlBody += "</div>"; // fin cadre
				htmlBody += "<div id='sautligne'></div>"; // le saut de page

				html += "<html><head></head><body>" + htmlBody + "</body>";

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
		});

		treat.start();

	}

	@Override
	public void valider(int type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

}
