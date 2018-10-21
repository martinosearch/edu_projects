package rapportBulletin;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import abstractObject.Rapport;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import function.MartArranger;

public class FicheRstAn2Semestre extends FicheRstAn2 {

	@Override
	public void write() {
		// créons le tableau
		htmlBody += "<div class='saut'>" + "<div id='sautligne'></div>"
				+ "<div class='cadre'>";// le cadre

		htmlBody += bs.writeEntete(titre);

		htmlBody += "<div id='result'><b>Classe: " + classe + "</b></div>";

		htmlBody += "<div id='result'>Inscrits: </div>"
				+ "<div id='result'>Ont composés: </div>"
				+ "<div id='result'>Sont admis: </div>"
				+ "<div id='result'>Pourcentage:</div>" + "</td>" + "</tr>"
				+ "</table><br/>";

		// On v�rifie le type de rapport à fournir
		if (model == Rapport.MODEL_SECOND) {// pour le secondaire
			htmlBody += "<table width=100% class='tabSB'>"
					+ "<tr  id='result'>"
					+ "<td class='tdBC' width=8%>Rang</td>"
					+ "<td class='tdSBLC' width=20%>Nom</td>"
					+ "<td class='tdSBLC' width=40%>Prénoms</td>"
					+ "<td class='tdSBLC' width=10%>Moy. 1er sem</td>"
					+ "<td class='tdSBLC' width=10%>Moy. 2è sem</td>"
					+ "<td class='tdSBLC' width=10%>Moyenne Gle</td>";

			htmlBody += "</tr>";

			for (int i = 1; i < eleves.size() + 1; i++) {
				try {
					Eleve eleve = nview.getElvAuRg("moyAn", i);

					String matricule = eleve.getCodeInfo();

					double moy = eleve.getValue();

					// On affiche la moyenne si l'élève a composé
					if (moy > 0) {
						String moyf = formatter.format(moy);
						String sexe = eleve.getSexe();

						String rang = MartArranger.getOrder(i, sexe);

						htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
								+ rang + "</td>" + "<td class='tdBInf'>"
								+ eleve.getNom() + "</td>"
								+ "<td class='tdBInf'>" + eleve.getPrenom()
								+ "</td>" + "<td class='tdBInf'>"
								+ eleve.getMoyenne1() + "</td>"
								+ "<td class='tdBInf'>" + eleve.getMoyenne2()
								+ "<td class='tdBInf'><b>" + moyf + "</b></td>";

						htmlBody += "</tr>";

						// *****POUR LA BARRE DE PORGRESSION***********
						progress.increment();
						// *********************FIN********************
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// fin for �l�ve
		}// fin SECONDAIRE

		// FIN DES VERIFICATIONS

		htmlBody += "</table>";

		htmlBody += bs.getTabSignature(DocFormat.DIR_TITUL, "tabSB");

		htmlBody += "</div>"; // fin cadre
		htmlBody += "<div id='sautligne'></div>"; // le saut de page

		html += "<html><head></head><body>" + htmlBody + "</body>";
		System.out.println(html);

		// affichage des notes
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

		// Pour l'archive
		String title = "Fiche résultat Annuel/ " + annee;

		Histo his = new Histo(title, html, new DateTime());
		histoMng.save(his);
		// fin archive

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************

	}

}
