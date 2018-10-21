package rapportCompta;

import java.awt.print.PageFormat;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import agent.Agent;
import connection.DAO;
import connection.DAOFactory;
import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import ecolage.Operation;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import function.Constance;

public class RecuModel {
	private int page;
	private String htmlBody = "";
	private DocFormat bs;
	private String html = "";
	protected MartEditorPane editor;
	protected Progress progress;
	private HistoManager histoMng = new HistoManager();
	private ProgressFrame pFrame;
	private String annee;
	private DAO clsdao;
	private Operation operation;

	public void writeRecu(Operation op) {
		operation = op;
		clsdao = DAOFactory.getDAO(DAO.CLASSE);

		progress = new Progress();
		pFrame = new ProgressFrame();
		progress.getLoading(pFrame,
				"Traitement en cours, prennez votre mal en patience");

		while (page < 2) {
			page++;

			if (page % 2 == 0) {
				htmlBody += "</td><td width=3%></td><td class='tdB'>";

			} else {
				htmlBody += "<div class='sautPaysage'>";// pour le saut
														// de
				// page
				// table qui se charge des colonnes
				htmlBody += "<table class='tabSB' width='100%'>";
				htmlBody += "<tr><td class='tdB'>";
			}

			EleveEcolage eleve = operation.getEleveEco();
			Agent charge = operation.getSuperCharger();
			EcolageComputer computer = operation.getComputer();
			DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
			formatter.withZoneUTC();

			bs = new BullFormat(0, annee);

			String title = "BORDEREAU DE PAYEMENT";
			if (operation.getJustification().equals(Operation.ANNULATION_ECO)) {
				title = "BORDEREAU D'ANNULATION DE PAYEMENT";
			}
			if (operation.getJustification().equals(Operation.ANNULATION_INS)) {
				title = "BORDEREAU D'ANNULATION";
			}

			htmlBody += bs.writeEntete(title);
			htmlBody += "<div align='center'>------------------------------------"
					+ "------------------------------</div>";

			// retour en ligne pour donner de l'espace vertical
			htmlBody += "<div id='vspace'></div>";

			htmlBody += "<table class='ficheNoteExemplaire' width='100%'>"
					+ "<tr><td class='tdB'><b>Reférence</b></td><td class='tdSBL'>"
					+ operation.getNumOperation()
					+ "</td></tr>"
					+ "<tr><td class='tdB'><b>Nom et Prénoms de l'élève</b></td><td class='tdSBL'>"
					+ eleve.decrisToi()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>Classe</b></td><td class='tdBInf'>"
					+ eleve.getClasse()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>N° Mle</b></td><td class='tdBInf'></td></tr>"
					+ "<tr><td class='tdSBT'><b>Code</b></td><td class='tdBInf'>"
					+ eleve.getCodeInfo()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>Montant payé</b></td><td class='tdBInf'>"
					+ operation.getMontant()
					+ " "
					+ Constance.getDeviseMonaie()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>En lettres</b></td><td class='tdBInf'>"
					+ computer.getRegleEnLettres()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>Justification</b></td><td class='tdBInf'>"
					+ operation.getJustification()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>Total réglé</b></td><td class='tdBInf'>"
					+ computer.getRegle()
					+ " "
					+ Constance.getDeviseMonaie()
					+ "</td></tr>"
					+ "<tr><td class='tdSBT'><b>Reste à payer</b></td><td class='tdBInf'>"
					+ computer.getReste()
					+ " "
					+ Constance.getDeviseMonaie()
					+ "</td></tr>" + "</table>";

			htmlBody += "<table class='ficheNoteExemplaire' width='100%'>"
					+ "<tr><td class='tdSB' width='40%'><b></b></td><td class='tdSB' width='60%'><b></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b>Fait Lomé le "
					+ formatter.print(operation.getDate())
					+ "</b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b><u>La Direction</u></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b></b></td></tr>"
					+ "<tr><td class='tdSB'><b></b></td><td class='tdSB'><b>"
					+ charge.decrisToi() + "</b></td></tr>";
			htmlBody += "</table>";
		}

		htmlBody += "<tr><td>";
		htmlBody += writePied();
		htmlBody += "</td><td></td><td>";
		htmlBody += writePied();
		htmlBody += "</td></tr>";
		htmlBody += "</table>";
		// affichage
		html += "<html><head></head><body>" + htmlBody + "</body></html>";

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				editor = new MartEditorPane();
				MartStyle.setPadding(0);
				MartStyle.setRowheight(5);
				editor.setStyle(MartStyle.BULL);
				editor.setOrientation(PageFormat.LANDSCAPE);
				editor.setHtml(html);

				// on fait appelle à la visionneuse
				Preview bsh = new Preview(editor);

				// *****POUR LA BARRE DE PORGRESSION***********
				pFrame.close();
				// *********************FIN********************
			}
		});

		// Pour l'archive
		String title = "Bulletins/ ";

		Histo his = new Histo(title, html, new DateTime());
		histoMng.save(his);
		// fin archiv
	}

	private String writePied() {
		// TODO Auto-generated method stub
		return bs
				.writeCode(
						operation.getNumOperation(),
						"",
						"<u></u>Ce documents est une preuve importante de la présente opération. Prenez- en soin.");
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

}
