package rapportCompta;

import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.awt.Color;

import javax.swing.SwingUtilities;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class ListeFraisInscriptionClasseModel extends AbstractModel {
	private DAO versecolagedao, elvdao;
	private String htmlBody;
	private String html = "";
	private HistoManager histoMng;
	private Progress progress;
	private ProgressFrame progressFrame;
	private int progmax;
	private MartList<EleveEcolage> listeVersEcolage;
	private DocFormat bs;
	private String titre;
	private Eleve superEleve;
	private EcolageComputer computer;
	private Thread treat;
	private MartList<EleveClasse> eleves;

	// constructeur avec paramètre
	public ListeFraisInscriptionClasseModel() {
		histoMng = new HistoManager();

		initComponent();
	}

	// creation des composants
	public void initComponent() {
		versecolagedao = DAOFactory.getDAO(DAO.VERSEMENT_ECOLAGE);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		elvdao.load();
	}

	public void createListe() {
		// on recupere la liste des élève
		versecolagedao.load(null, null, 0, annee);
		elvclsdao.load(null, classe, 0, annee);
		eleves = elvclsdao.getListObt();

		listeVersEcolage = versecolagedao.getListObt();
		titre = "FICHE DE PAYEMENT DES FRAIS D'INSCRIPTION";

		html = "";
		htmlBody = "";

		// ********debut de la barre de progression***************
		progress = new Progress();
		progressFrame = new ProgressFrame();

		progress.getLoading(progressFrame, "Chargement des données en cours");
		// ****************fin partielle***********************

		// ***************LA BARRE DE PROGRESSION********************
		progmax = listeVersEcolage.size() * 2;
		progress.getProgress(progressFrame, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************/*****

		treat = new Thread(new Runnable() {
			public void run() {
				computer = new EcolageComputer();
				for (EleveEcolage eleve : listeVersEcolage) {
					superEleve = (Eleve) elvdao.findObj(eleve.getCodeInfo());
					eleve.setSexe(superEleve.getSexe());
					computer.setEleveEcolage(eleve);

					progress.increment();
				}

				bs = new BullFormat(0, annee);

				// créons le tableau
				htmlBody += "<div class='saut'>" + "<div id='sautligne'></div>"
						+ "<div class='cadre'>";// le cadre

				// reférence de l'établissement
				htmlBody += bs.writeEntete(titre);
				// fin des en-têtes

				htmlBody += "<br/>" + "<table width=100% class='tabSB'>"
						+ "<tr>" + "<td class='tdSB'>";

				htmlBody += "<div id='result'><b>Classe: " + classe
						+ "</b></div>";

				htmlBody += "<div id='result'>Effectif: " + eleves.size()
						+ "</div><br>";

				htmlBody += "<table width=100% class='tabSB'>"
						+ "<tr  id='result'>"
						+ "<td class='tdBC' width=7%>N° d'ordre</td>"
						+ "<td class='tdSBLC' width=20%>Nom</td>"
						+ "<td class='tdSBLC' width=45%>Prénoms</td>"
						+ "<td class='tdSBLC' width=8%>Sexe</td>"
						+ "<td class='tdSBLC' width=20%>Total ("
						+ Constance.getDeviseMonaie() + ")</td>";

				htmlBody += "</tr>";

				int i = 0;
				computer = new EcolageComputer();
				for (EleveClasse eleve : eleves) {
					try {
						i++;
						EleveEcolage elveco = (EleveEcolage) versecolagedao
								.findObj(eleve.getCodeInfo());
						computer.setEleveEcolage(elveco);

						htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
								+ i + "</td><td class='tdBInf'>"
								+ eleve.getNom() + "</td>"
								+ "<td class='tdBInf'>" + eleve.getPrenom()
								+ "</td>" + "<td class='tdBInf'>"
								+ eleve.getSexe() + "</td>"
								+ "<td class='tdBInf'><b>"
								+ computer.getInscription() + "</b></td>";

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
