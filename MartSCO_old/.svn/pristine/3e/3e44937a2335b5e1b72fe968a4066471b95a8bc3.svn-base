package rapportCompta;

import ecolage.EcoViewer;
import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import ecolage.InfoEcoEts;
import ecolage.Operation;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import function.Constance;
import function.MartFormatter;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.awt.Color;
import java.awt.Component;
import java.awt.print.PageFormat;
import java.math.BigDecimal;

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

public class ListeEntreeScolariteModel extends AbstractModel {
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
	protected Thread treat;
	protected DateTimeFormatter formatter;
	private EcoViewer viewer;
	private boolean isPeriodSelection = false;
	private InfoEcoEts infoEts;

	// constructeur avec paramètre
	public ListeEntreeScolariteModel() {
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
			infoEts = viewer.getInfoEcoEts();
		} catch (Exception e) {
			e.printStackTrace();
		}

		titre = "RAPPORT DES ENTREES";

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

				bs = new BullFormat(0, annee);

				// créons le tableau
				htmlBody += bs.writeEntete(titre);

				htmlBody += "<div id='result'><table width='100%'><tr><td colspan=2><b>"
						+ getPeriode()
						+ "</b></td></tr>"
						+ "<tr><td width='20%'>Total Inscription:</td><td width='80%'>"
						+ new BigDecimal(infoEts.getTotalIns())
						+ " "
						+ Constance.getDeviseMonaie()
						+ "</td></tr>"
						+ "<tr><td>Total Ecolage:</td><td>"
						+ new BigDecimal(infoEts.getTotalEco())
						+ " "
						+ Constance.getDeviseMonaie()
						+ "</td></tr>"
						+ "<tr>Total Encaissé:</td><td>"
						+ new BigDecimal(infoEts.getTotalEnCaisse())
						+ " "
						+ Constance.getDeviseMonaie()
						+ "</td></tr></table></div>";

				htmlBody += "</td></tr></table><br/>";

				htmlBody += "<table width=100% class='tabSB'>"
						+ "<tr  id='result'>"
						+ "<td class='tdBC' width=5%>N° d'ordre</td>"
						+ "<td class='tdSBLC' width=15%>Code</td>"
						+ "<td class='tdSBLC' width=15%>Nom</td>"
						+ "<td class='tdSBLC' width=15%>Prénoms</td>"
						+ "<td class='tdSBLC' width=5%>Sexe</td>"
						+ "<td class='tdSBLC' width=5%>Classe</td>"
						+ "<td class='tdSBLC' width=15%>Somme("
						+ Constance.getDeviseMonaie() + ")</td>"
						+ "<td class='tdSBLC' width=10%>Justification</td>"
						+ "<td class='tdSBLC' width=10%>Date</td>"
						+ "<td class='tdSBLC' width=25%>Chargé</td>";

				htmlBody += "</tr>";

				// ecriture du model
				write();

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

	public boolean write() {
		int i = 0;
		for (Operation op : listOperation) {
			try {
				i++;
				htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
						+ i
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ op.getNumOperation()
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ op.getNomEleve()
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ op.getPrenomEleve()
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ op.getSexeEleve()
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ op.getClasseEleve()
						+ "</td>"
						+ "<td class='tdBInf'><b>"
						+ op.getMontant()
						+ "</b></td>"
						+ "<td class='tdBInf'>"
						+ op.getJustification()
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ formatter.print(op.getDate())
						+ "</td>"
						+ "<td class='tdBInf'>"
						+ ((Agent) agentdao.findObj(op.getCharge()))
								.decrisToi() + "</td>";

			} catch (Exception e) {
				e.printStackTrace();
			}

			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
			// *********************FIN********************
		}// fin for op

		return true;
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
