package rapportExamen;

import images.PictureFinder;

import javax.swing.SwingUtilities;

import matiere.Matiere;
import matiere.MatiereProg;
import note.InfoNote;
import note.Moyenne;

import org.joda.time.DateTime;

import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import abstractObject.Rapport;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.Eleve;
import eleve.EleveClasse;
import function.Constance;

public class RelWriterModelSingle extends RelWriterModel {

	@Override
	public void write() {
		// on écris le releve du type 1
		Thread traitement1 = new Thread(new Runnable() {

			public void run() {

				// début de la création des bulletins
				for (int j = 0; j < nbreBull; j++) {
					bullCount++;
					String code = (String) tabElv.getValueAt(j, 1);
					Eleve eleve = (Eleve) elvdao.findObj(code);
					String nom = eleve.getNom();
					String prenom = eleve.getPrenom();
					String sexe = eleve.getSexe();
					String matricule = eleve.getCodeInfo();

					// **********DEFINIT LA PHOTO DE L'ELEVE*************
					photo = new PictureFinder().getPhotoEleve(matricule);

					// ********pour la barre de progression**************
					progress.setText("MartSco est en train d'éditer le relevé de: "
							+ eleve.getNom() + " " + eleve.getPrenom());
					// ******************************************************************

					Moyenne bilan = nview
							.getMoyenne(new EleveClasse(matricule));

					String totaux = formatter.format(bilan.getGrdTotal());
					String moyGen = formatter.format(bilan.getMoyGen());
					String rangGen = nview.getRang(new MatiereProg("std"),
							new EleveClasse(matricule)) + " Sur " + effectif;

					// String moyGenStr = (bilan.toString()).toUpperCase();
					String mention = bilan.getMention();

					// numéro des matières
					int numMat = 1;
					htmlBody += "<div class='saut'>";// pour le saut de page

					htmlBody += bs.writeEntete("RELEVE DE NOTES");

					htmlBody += "<div id='blocidentites'>"
							+ "<table class='tabB' width='100%'>"// table
																	// identite
																	// eleves
							+ "<tr>"
							+ "<td id='ideleve' width='85%'>"
							+ "<div id='nom'><span id='italic'>Nom: </span>"
							+ nom
							+ " &#160;"
							+ " &#160; "
							+ prenom
							+ "</div>"
							+ "<div><span id='italic'>Sexe: </span>"
							+ sexe
							+ "</div>"
							+ "<div><span id='italic'>Code: </span>"
							+ matricule
							+ "<span id='italic'>&#160; &#160; &#160; Statut: </span>&#160;</div>"
							+ "</td>"

							+ "<td id='image' width='15%'>";

					if (conf.relConfig.photoEleve() == true) {
						htmlBody += "<div><img src='" + photo.toURI()
								+ "'/></div>";
					}

					htmlBody += "</td>";

					htmlBody += "</tr>" + "</table>"// fin table id élèes

							+ "</div>";

					// TABLEAU DES NOTES
					int nbrcol = bs.getnbreCol();
					htmlBody += "<div id='notesRel'>" + tabNote;

					for (MatiereProg mat : Matieres) {
						String intitule = mat.getIntitule();
						Matiere matiere = (Matiere) matdao.findObj(intitule);

						// on recup7re les notes de l'élève
						// on fait appel au gestionnaire da l'affichage des
						// note

						InfoNote info = nview.getNotes(mat, new EleveClasse(
								matricule));
						double sur;
						String coef = "", charge = "", note1 = "", note2 = "", dev = "", moyCls = "", compo = "", moy = "", ptobt = "", rang = "", appr = "", surStr = "";

						// LE MODEL POUR LE PRIMAIRE
						if (model == Rapport.MODEL_PRIM) {
							if (info.hasCompose() == true) {
								coef = info.getCoefConsidered();
								moy = info.getmoyStr();
								ptobt = info.getPtObtStr();
								sur = Double.parseDouble(coef) * 10;
								surStr += sur;
							}

							// on adffiche
							htmlBody += "<tr>" + "<td class='tdtitleleft'>"
									+ numMat + "</td>" + "<td class='tdBInf'>"
									+ intitule + "</td>"
									+ "<td class='tdBInf'>" + ptobt + "</td>"
									+ "<td class='tdBB'>" + surStr + "</td>"
									+ "</tr>";

							numMat++;
						}// Fin model primaire

						// LE MODEL POUR LE SECON
						if (model == Rapport.MODEL_SECOND) {
							if (info.hasCompose() == true) {
								coef = info.getCoefConsidered();
								moy = info.getmoyStr();
								ptobt = info.getPtObtStr();
								sur = (int) Double.parseDouble(coef) * 20;
								surStr += sur;
							}

							// on adffiche
							htmlBody += "<tr>" + "<td class='tdtitleleft'>"
									+ numMat + "</td>" + "<td class='tdBInf'>"
									+ intitule + "</td>"
									+ "<td class='tdBInf'>" + ptobt + "</td>"
									+ "<td class='tdBB'>" + surStr + "</td>"
									+ "</tr>";

							numMat++;
						}

						// *****POUR LA BARRE DE PORGRESSION***********
						progress.increment();
					}

					// saut de ligne

					// grand total
					htmlBody += "<tr>" + "<td id='sautligne' colspan='4'></td>"
							+ "</tr>" + "<tr>"
							+ "<td id='sautligne' colspan='4'></td>" + "</tr>"
							+ "<tr>" + "<td id='sautligne' colspan='4'></td>"
							+ "</tr>" + "<tr>"
							+ "<td class='tdBC' colspan='2'><b>Total</b></td>"
							+ "<td class='tdSBLC'>" + totaux + "</td>"
							+ "<td class='tdSBLC'>" + bilan.getSurTotal()
							+ "</td>" + "</tr>" + "<tr>"
							+ "<td id='sautligne'></td>" + "</tr>";

					// *************************************************************

					htmlBody += "</table>" + "</div>"; // fin div id notes
					// fin de la table des notes

					// PARTIE AFFICHAGE DES MOYENNE
					htmlBody += "<div width='100%'>"
							+ "<table width='100%' class='tabB'>"// table
																	// des
																	// trois
																	// colonnes
							+ "<tr>" + "<td width='45%' class='tdSB'>"
							+ "<table id='tabMoy' width='100%'  valign='top'>";// table
																				// 1�re
																				// col

					if (model == Rapport.MODEL_SECOND) {
						htmlBody += "<tr>"
								+ "<td class='tdSBLC' width='15%'>Moyenne Gen.</td>"
								+ "<td class='tdSBLC' width='15%'><b>" + moyGen
								+ "</b></td>" + "</tr>";
					}
					htmlBody += "<tr>"
							+ "<td class='tdSBLC' width='15%'>Rang</td>"
							+ "<td class='tdSBLC' width='15%'><b>"
							+ rangGen
							+ "</b></td>"
							+ "</tr>"

							+ "<tr></tr>"

							+ "<tr><td colspan='2'><u><b>Décision du Jury</b></u></td></tr>";

					if (bilan.hasSucced() == true) {
						htmlBody += "<tr><td class='tdBC'>Admis(e)</td>"
								+ "<td>Ajourné(e)</td></tr>";
					} else {
						htmlBody += "<tr><td >Admis(e)</td>"
								+ "<td class='tdBC'>Ajourné(e)</td></tr>";
					}

					htmlBody += "<tr id='sautligne'></tr>"

					+ "</table>"// fin table 1ère col
							+ "</td>" + "<td width='55%' class='tdSB'></td>"

							+ "</tr>" + "</table>"// fin table des trois
													// colonnes
							+ "</div>";// fin div affichage

					// les signatures
					htmlBody += "<div>"
							+ bs.getTabSignature(DocFormat.PRESI, "tabSB")
							+ "</div>";

					// les codes
					htmlBody += "<div class='tabB'>"
							+ "<table class='tabSB' width='100%'>" + "<tr>"
							+ "<td id='codebar' width='25%'>" + matricule
							+ "AUTH</td>"
							+ "<td id='code' valign='top' width='8%'></td>"
							+ "<td id='devise' valign='top' width='42%'>"
							+ Constance.DEVISE + "</td>"
							+ "<td id='NB' valign='top' width='25%'><u>NB</u>"
							+ ":Il n'est délivré qu'un seul bulletin,prenez"
							+ "-en soin</td>" + "</tr>" + "</table>" + "</div>";

					htmlBody += "</div>" // fin cadre

							+ "</div>" + "<div id='sautligne'></div>"; // le
																		// saut
																		// de
																		// page
					// *****POUR LA BARRE DE PORGRESSION***********
					progress.increment();
					// *********************FIN********************

				}// Fin edition

				// ********pour la barre de progression**************
				progress.setText("MartSco se prépare pour afficher les Bulletins");
				// ******************************************************************

				html += "<html><head></head><body>" + htmlBody
						+ "</body></html>";

				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						editor = new MartEditorPane();
						MartStyle.setPadding(0);
						MartStyle.setRowheight(5);
						editor.setStyle(MartStyle.BULL);
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
}
