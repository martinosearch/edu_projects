package rapportCompta;

import ecolage.EcoViewer;
import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import ecolage.Operation;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.awt.Color;
import java.awt.Component;
import java.awt.print.PageFormat;

import javax.swing.SwingUtilities;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.HistoManager;
import abstractObject.AbstractModel;
import agent.Agent;
import connection.DAO;
import connection.DAOFactory;

public class StaRecouvrement extends AbstractModel {
	protected DAO versecolagedao, elvdao, agentdao;
	protected String htmlBody;
	protected String html = "";
	protected HistoManager histoMng;
	protected Progress progress;
	protected ProgressFrame progressFrame;
	protected int progmax;
	protected MartList<Operation> listOperation = new MartList<Operation>();
	protected DocFormat bs;
	protected String titre;
	protected Eleve superEleve;
	protected EcolageComputer computer;
	protected Thread treat;
	protected DateTimeFormatter formatter;
	private EcoViewer viewer;
	private boolean isPeriodSelection = false;

	// constructeur avec paramètre
	public StaRecouvrement() {
		histoMng = new HistoManager();

		initComponent();
	}

	// creation des composants
	public void initComponent() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		agentdao = DAOFactory.getDAO(DAO.AGENT);
		elvdao.load();
		agentdao.load();

		formatter = DateTimeFormat.shortDateTime();
		formatter.withZoneUTC();
	}

	public void createListe() {
		// on recupere la liste des élève
		viewer = new EcoViewer();
		viewer.setListener(this);
		viewer.load(annee);
		viewer.loadInfoEts();
		try {
			listOperation = viewer.getListeOperation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		titre = "STATISTIQUE DES RECOUVREMENTS";

		html = "";
		htmlBody = "";

		treat = new Thread(new Runnable() {

			public void run() {
				// ********debut de la barre de progression***************
				progress = new Progress();
				progressFrame = new ProgressFrame();

				progress.getLoading(progressFrame,
						"Chargement des données en cours");
				// ****************fin partielle***********************

				// ***************LA BARRE DE PROGRESSION********************
				progmax = listOperation.size() + 1;
				progress.getProgress(progressFrame, 0, progmax);
				progress.setColor(Color.green);
				// ********************FIN*****************************/*****
				System.out.println("Maxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx: "
						+ progmax);

				computer = new EcolageComputer();
				bs = new BullFormat(0, annee);

				// créons le tableau
				htmlBody += bs.writeEntete(titre);

				htmlBody += "<div id='result'><table><tr><td colspan=2><b>"
						+ getPeriode() + "</b></td></tr>"
						+ "<tr><td>Total Inscription:</td><td>"
						+ computer.getTotalInsription() + " "
						+ Constance.getDeviseMonaie() + "</td></tr>"
						+ "<tr><td>Total Ecolage:</td><td>"
						+ computer.getTotalEcolage() + " "
						+ Constance.getDeviseMonaie() + "</td></tr>"
						+ "<tr>Total Encaissé:</td><td>"
						+ computer.getTotalEncaisse() + " "
						+ Constance.getDeviseMonaie()
						+ "</td></tr></table></div>";

				htmlBody += "<br/>";

				htmlBody += "<table width=100% class='tabSB'>";

				htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
						+ "</td>"
						+ "<td class='tdBInf'>Total des frais à payer</td>"
						+ "<td class='tdBInf'>" + op.getNomEleve()
						+ "</td></tr>";

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
						editor.setOrientation(PageFormat.LANDSCAPE);

						// on fait appelle à la visionneuse
						Preview bsh = new Preview(editor);

						// *****POUR LA BARRE DE PORGRESSION***********
						progress.increment();
						// *********************FIN********************
						System.out
								.println("Maxxxxxxxxxxxxxxxxxxxxxxxttttttttttttttttttxxxxxxxxxxx: "
										+ progmax);
						progressFrame.close();
					}
				});
			}

		});

		treat.start();

	}

	public String getPeriode() {
		if (isPeriodSelection) {
			DateTimeFormatter formatter = DateTimeFormat.fullDate();
			formatter.withZoneUTC();
			String date1 = formatter.print(getDate1());
			String date2 = formatter.print(getDate2());
			String periode = "Du " + date1 + " au " + date2;
			return periode.toUpperCase();
		} else {
			return getAnnee();
		}
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
