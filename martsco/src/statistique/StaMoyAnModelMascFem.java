package statistique;

import java.awt.print.PageFormat;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import editeur.Preview;
import function.Constance;

public class StaMoyAnModelMascFem extends StaMoyAnModel {

	// ecris le model mixte du type de statistique
	public void write() {
		html += "<html><head><meta charset='utf-8' /></head>"
				+ "<body><div id='rapportsta'>" + htmlBody + "</div>"
				+ "</body></html>";

		System.out.println(html);

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editor.setLatexSize(10);
				editor.setHtml(html);
				// on fait appelle à l'editeur
				editor.revalidate();
				editor.setOrientation(PageFormat.PORTRAIT);
				Preview bsh = new Preview(editor);
			}
		});

		new Thread(new Runnable() {
			public void run() {
				// Pour l'archive********************************
				String title = "Statistiques_Annuelles/ " + annee;

				Histo his = new Histo(title, html, new DateTime());
				histoMng.save(his);
				// fin archive**********************************
			}
		}).start();
	}
}
