package rapportExamen;

import java.awt.print.PageFormat;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import abstractObject.Rapport;
import configurationEcole.ConfigEcole;
import configurationExamen.ConfigExamen;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.EleveClasse;
import function.MartFormatter;
import images.PictureFinder;
import matiere.Matiere;
import matiere.MatiereProg;
import note.InfoNote;
import note.Moyenne;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;

public class RelWriterModelDouble extends RelWriterModel {
    @Override
    public void write() {
	// on écris le releve du type 2 (double colonne)

	Thread traitement1 = new Thread(new Runnable() {

	    @Override
	    public void run() {
		// on ajuste nombre de bulletin pour la parité
		if (nbreBull % 2 != 0) {
		    listeChoix.add(eleve);
		}

		for (EleveClasse eleve : listeChoix) {
		    bullCount++;

		    // **********DEFINIT LA PHOTO DE L'ELEVE*************

		    System.out.println("liste choiw taillle: " + listeChoix.size());// + " " + eleve.decrisToi());

		    try {
			photo = new PictureFinder().getPhotoEleve(eleve.getCodeInfo());
		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    Moyenne bilan = null;
		    String totaux = null;
		    String moyGen = null;
		    String mention = null;
		    try {
			bilan = nview.getMoyenne(new EleveClasse(eleve.getCodeInfo()));
			totaux = formatter.format(bilan.getGrdTotal());
			moyGen = formatter.format(bilan.getMoyGen());

			// String moyGenStr = (bilan.toString()).toUpperCase();
			mention = bilan.getMention();

			// ********pour la barre de progression**************
			progress.setText("MartSco est en train d'éditer le relevé de: " + eleve.getNom() + " "
				+ eleve.getPrenom());
			// ******************************************************************
		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    String rangGen = null;
		    String id = "std";
		    try {
			rangGen = nview.getRang(new MatiereProg(id), eleve) + " Sur " + effectif;
		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    // numéro des matières
		    int numMat = 1;

		    if (bullCount % 2 == 0) {
			htmlBody += "</td><td width=2%></td><td class='tdB' width='49%'>";

		    } else {
			htmlBody += "<div class='sautPaysage'>";// pour le saut
								// de
			// page
			// table qui se charge des colonnes
			htmlBody += "<table class='tabSB' width='100%'>";
			htmlBody += "<tr><td class='tdB' width='49%'>";
		    }

		    htmlBody += bs.writeEntete("RELEVE DE NOTE " + nview.getTitreRapport());

		    htmlBody += "<div id='sautligne'></div>";

		    htmlBody += "<div id='blocidentites'>"

			    + "<table class='tabB' width='100%'>"// table
								 // identite
								 // eleves
			    + "<tr>" + "<td id='ideleve' width='85%'>"
			    + "<div id='nom'><span id='italic'>Elève: </span>" + eleve.getNom() + " &#160;" + " &#160; "
			    + eleve.getPrenom() + "</div>" + "<div><span id='italic'>Sexe: </span>" + eleve.getSexe()
			    + "</div>";

		    if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			htmlBody += "<div><span id='italic'>Classe: </span>" + eleve.getClasse() + "</div>";
		    } else {
			htmlBody += "<div><span id='italic'>N° de table: </span>" + eleve.getNumTable() + "</div>";
		    }
		    try {
			if (etablissements.size() > 1) {
			    htmlBody += "<div><span id='italic'>Etablissement: </span>" + eleve.getEts() + "</div>";
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }

		    htmlBody += "</td><td id='image' width='15%'>";

		    if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			if (((ConfigEcole) conf).bullConfig.photoEleve() == true) {
			    htmlBody += "<div><img src='" + photo.toURI() + "'/></div>";
			    htmlBody += "</td>";
			}
		    } else {
			if (((ConfigExamen) conf).relConfig.photoEleve() == true) {
			    htmlBody += "<div><img src='" + photo.toURI() + "'/></div>";
			    htmlBody += "</td>";
			}
		    }

		    htmlBody += "</tr>" + "</table>"// fin table id élève

			    + "</div>";

		    // TABLEAU DES
		    // NOTES*******************************************************************
		    int nbrcol = bs.getnbreCol();
		    htmlBody += "<div id='notesRel'>" + tabNote;

		    for (MatiereProg mat : Matieres) {
			String intitule = mat.getIntitule();
			Matiere matiere = (Matiere) matdao.findObj(intitule);

			// on recup7re les notes de l'élève
			// on fait appel au gestionnaire da l'affichage des
			// note

			InfoNote info = nview.getNotes(mat, new EleveClasse(eleve.getCodeInfo()));

			String sur = "";
			String coef = "", charge = "", note1 = "", note2 = "", dev = "", moyCls = "", compo = "",
				moy = "", ptobt = "", rang = "", appr = "";

			if (info.hasCompose() == true) {
			    coef = info.getCoefConsidered();
			    moy = info.getmoyStr();
			    ptobt = info.getPtObtStr();
			    sur = info.getSurTotal();
			}

			// LE MODEL POUR LE PRIMAIRE
			if (model == Rapport.MODEL_PRIM) {

			    // on adffiche
			    htmlBody += "<tr>" + "<td class='tdtitleleft'>" + numMat + "</td>" + "<td class='tdBInf'>"
				    + intitule + "</td>" + "<td class='tdBInf'>" + ptobt + "</td>" + "<td class='tdBB'>"
				    + MartFormatter.correctDecimal(sur) + "</td>" + "</tr>";

			    numMat++;
			} // Fin model primaire

			// LE MODEL POUR LE SECON
			if (model == Rapport.MODEL_SECOND) {
			    // on adffiche
			    htmlBody += "<tr>" + "<td class='tdtitleleft'>" + numMat + "</td>" + "<td class='tdBInf'>"
				    + intitule + "</td>" + "<td class='tdBInf'>" + ptobt + "</td>" + "<td class='tdBB'>"
				    + MartFormatter.correctDecimal(sur) + "</td>" + "</tr>";

			    numMat++;
			} // Fin model primaire

			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
		    }

		    // saut de ligne

		    // grand total
		    htmlBody += "<tr>" + "<td id='sautligne' colspan='4'></td>" + "</tr>" + "<tr>"
			    + "<td id='sautligne' colspan='4'></td>" + "</tr>" + "<tr>"
			    + "<td id='sautligne' colspan='4'></td>" + "</tr>" + "<tr>"
			    + "<td class='tdBC' colspan='2'><b>Total</b></td>"

			    + "<td class='tdSBLC'>" + totaux + "</td>"

			    + "<td class='tdSBLC'>" + bilan.getSurTotal() + "</td>" + "</tr>" + "<tr>"
			    + "<td id='sautligne'></td>" + "</tr>";

		    // *************************************************************

		    htmlBody += "</table>" + "</div>"; // fin div id notes
		    // fin de la table des notes

		    // PARTIE AFFICHAGE DES MOYENNE
		    htmlBody += "<div width='100%'>" + "<table width='100%' class='tabB'>"// table de 2
											  // colonne
			    + "<tr>" + "<td width='50%' class='tdSB'>"
			    + "<table id='tabMoy' width='100%'  valign='top'>";// table
									       // colonne
									       // 1

		    if (model == Rapport.MODEL_SECOND) {
			htmlBody += "<tr>" + "<td class='tdSBLC' width='15%'>Moyenne Gen.</td>"
				+ "<td class='tdSBLC' width='15%'><b>" + moyGen + "</b></td>" + "</tr>";
		    }

		    htmlBody += "<tr>" + "<td class='tdSBLC' width='15%'>Rang</td>"
			    + "<td class='tdSBLC' width='15%'><b>" + rangGen + "</b></td>" + "</tr></table></td>"// fin
														 // table
														 // colonne
														 // 1

			    + "<td width='50%' class='tdSB'><table id='tabMoy' width='100%'  valign='top'>";

		    if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			htmlBody += "<tr><td colspan='2'><u><b>Décision du Conseil</b></u></td></tr>";
		    } else {
			htmlBody += "<tr><td colspan='2'><u><b>Décision du Jury</b></u></td></tr>";
		    }

		    if (bilan.hasSucced()) {
			htmlBody += "<tr><td class='tdBC'>Admis(e)</td>" + "<td>Ajourné(e)</td></tr>";
		    } else {
			htmlBody += "<tr><td >Admis(e)</td>" + "<td class='tdBC'>Ajourné(e)</td></tr>";
		    }

		    htmlBody += "</table>"// fin table 1ère col
			    + "</td></tr></table>"// fin table des trois
						  // colonnes
			    + "</div>";// fin div affichage

		    // les signatures
		    if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			htmlBody += "<div>" + bs.getTabSignature(DocFormat.DIR_TITUL, "tabSB") + "</div>";
		    } else {
			htmlBody += "<div>" + bs.getTabSignature(DocFormat.PRESI, "tabSB") + "</div>";
		    }

		    htmlBody += "<div>";
		    htmlBody += writePied(eleve);
		    htmlBody += "</div>";

		    if (bullCount % 2 == 0) {
			htmlBody += "</tr>";
			htmlBody += "</table></div>";// fin cadre
		    }
		    // *****POUR LA BARRE DE PORGRESSION***********
		    progress.increment();
		    // *********************FIN********************

		    try {
			Thread.sleep(100);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }

		} // Fin edition

		// ********pour la barre de progression**************
		progress.setText("MartSco se prépare pour afficher les Bulletins");
		// ******************************************************************

		html += "<html><head></head><body>" + htmlBody + "</body></html>";

		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {
			editor = new MartEditorPane();
			MartStyle.setPadding(0);
			MartStyle.setRowheight(5);
			editor.setStyle(MartStyle.RELEVE);
			editor.setOrientation(PageFormat.LANDSCAPE);
			editor.setHtml(html);

			// on fait appelle à la visionneuse
			Preview bsh = new Preview(editor);

			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
			// *********************FIN********************
		    }
		});

		// Pour l'archive
		String title = "Bulletins/ ";

		Histo his = new Histo(title, html, new DateTime());
		histoMng.save(his);
		// fin archiv
	    }
	});
	traitement1.start();

    }

    private String writePied(EleveClasse eleve) {
	return bs.writeCode(eleve.getCodeInfo(), "", "<u></u>Il n'est délivré qu'un seul relevé. Prenez- en soin.");
    }
}
