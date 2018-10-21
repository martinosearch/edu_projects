package statistique;

import java.awt.print.PageFormat;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import editeur.Preview;
import function.Constance;

public class StaMoyAnModelMixte extends StaMoyAnModel {

	// ecris le model mixte du type de statistique
	public void write() {
		// Niveau collège###########################################
		htmlBody += "<div class='saut'>"// saut page
				+ "<div id='sautligne'></div>"
				+ "<div class='cadre'>" // le cadre

				// la table des en-têtes
				+ "<table class='tabSB' width='100%'>"
				+ "<tr>"
				+ "<td width='70%'>"
				+ "<div>"
				+ Constance.INSPECTION_ABR
				+ "</div>"
				+ "<div> Etablissement: "
				+ Constance.INITIALE
				+ "&#160;"
				+ Constance.NOM
				+ "</div>"
				+ "</td>"
				+ "<td width='30%'>Année Scolaire: "
				+ annee
				+ "</td></tr>"

				+ "<tr>"
				+ "<td class='tdb' align='center' colspan='3'>"
				+ "<div>RESULTATS DE FIN D'ANNEE- SCOLAIRE "
				+ annee
				+ " PAR NIVEAU</div>"
				+ "<div><b>Statistiques des moyennes générales des élèves</b></div>"
				+ "</td>" + "</tr>"

				+ "</table>";// fin table des en-têtes

		// Le titre du tableau
		htmlBody += "<table class='tabStaMoyAn' width='100%'>"// table sta
				+ "<tr>"
				+ "<td class='tdB' size='28%'></td>"
				+ "<td class='tdSBL' size='18%'>SIXIEME</td>"
				+ "<td class='tdSBL' size='18%'>CINQUIEME</td>"
				+ "<td class='tdSBL' size='18%'>QUATRIEME</td>"
				+ "<td class='tdSBL' size='18%'>TROISIEME</td>" + "</tr>"

				// ligne inscrits
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Inscrits</td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInscrit()
				+ "</td>"
				+ "</tr>"

				// ligne présents en fin d'année
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Présent en fin d'année</td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getPresent()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 2
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$0\\leqslant Moyennes<2$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf2()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 4
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$2\\leqslant Moyennes<4$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf4()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 6
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$4\\leqslant Moyennes<6$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf6()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 8
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$6\\leqslant Moyennes<8$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf8()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$8\\leqslant Moyennes<10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf10()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 12
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$10\\leqslant Moyennes<12$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf12()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 14
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$12\\leqslant Moyennes<14$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf14()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 16
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$14\\leqslant Moyennes<16$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf16()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$16\\leqslant Moyennes<18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getInf18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getSup18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta3eme.getSup10()
				+ "</td>"
				+ "</tr>"

				// ligne per sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$\\% Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta6eme.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta5eme.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta4eme.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta3eme.getPerSup10())
				+ "</td>"
				+ "</tr>"

				// ligne moyenne niveau
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Moyenne du niveau</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta6eme.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta5eme.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta4eme.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta3eme.getMoyCls())
				+ "</td>"
				+ "</tr>"

				// ligne Plus forte moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus forte Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta6eme.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta5eme.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta4eme.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta3eme.getHNote())
				+ "</td>"
				+ "</tr>"

				// ligne Plus faible moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus faible Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta6eme.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta5eme.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta4eme.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta3eme.getLNote())
				+ "</td>"
				+ "</tr>"

				// ligne moyenne de passage
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Moyenne de Passage</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta6eme.getMPass())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta5eme.getMPass())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta4eme.getMPass())
				+ "</td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// ligne Nombre d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'admis</td>"
				+ "<td class='tdBInf'>"
				+ sta6eme.getAdmis()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta5eme.getAdmis()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta4eme.getAdmis()
				+ "</td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// ligne % d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>% d'admis</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta6eme.getPerAdmis())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta5eme.getPerAdmis())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta4eme.getPerAdmis())
				+ "</td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// Nombre d'élèves autorisés à redoubler
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'élèves autorisés à "
				+ "redoubler</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>" + "</tr>";

		htmlBody += "</table>";// fin table sta

		// table des signature
		bs.load();
		htmlBody += bs.getTabSignature(DocFormat.CHEF, "tabSB");

		htmlBody += "</div>" // fin cadre
				+ "</div>"// saut page
				+ "<div id='sautligne'></div>"; // le saut de ligne
		// fin niveau collège#############################################

		// Niveau
		// Seconde#########################################################"
		htmlBody += "<div class='saut'>"// saut page
				+ "<div id='sautligne'></div>"
				+ "<div class='cadre'>" // le cadre

				// la table des en-têtes
				+ "<table class='tabSB' width='100%'>"
				+ "<tr>"
				+ "<td width='70%'>" + "<div>"
				+ Constance.INSPECTION_ABR
				+ "</div>" + "<div> Etablissement: "
				+ Constance.INITIALE
				+ "&#160;" + Constance.NOM + "</div>"
				+ "</td>"
				+ "<td width='30%'>Année Scolaire: " + annee
				+ "</td></tr>"

				+ "<tr>"
				+ "<td class='tdb' align='center' colspan='3'>"
				+ "<div>RESULTATS DE FIN D'ANNEE- SCOLAIRE "
				+ annee
				+ " PAR NIVEAU</div>"
				+ "<div><b>Statistiques des moyennes générales des élèves "
				+ "de <u>Seconde</u></b></div>" + "</td>" + "</tr>"

				+ "</table>";// fin table des en-têtes

		// Le titre du tableau
		htmlBody += "<table class='tabStaMoyAn' width='100%'>"// table sta
				+ "<tr>"
				+ "<td class='tdB' size='28%'></td>"
				+ "<td class='tdSBL' size='18%'>SECONDE L</td>"
				+ "<td class='tdSBL' size='18%'>SECONDE S</td>" + "</tr>"

				// ligne inscrits
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Inscrits</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInscrit()
				+ "</td>"
				+ "</tr>"

				// ligne présents en fin d'année
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Présent en fin d'année</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getPresent()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 2
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$0\\leqslant Moyennes<2$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf2()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 4
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$2\\leqslant Moyennes<4$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf4()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 6
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$4\\leqslant Moyennes<6$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf6()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 8
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$6\\leqslant Moyennes<8$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf8()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$8\\leqslant Moyennes<10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf10()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 12
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$10\\leqslant Moyennes<12$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf12()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 14
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$12\\leqslant Moyennes<14$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf14()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 16
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$14\\leqslant Moyennes<16$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf16()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$16\\leqslant Moyennes<18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getInf18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getSup18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getSup10()
				+ "</td>"
				+ "</tr>"

				// ligne per sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$\\% Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_A4.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_CD.getPerSup10())
				+ "</td>"
				+ "</tr>"

				// ligne moyenne niveau
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Moyenne du niveau</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_A4.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_CD.getMoyCls())
				+ "</td>"
				+ "</tr>"

				// ligne Plus forte moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus forte Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_A4.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_CD.getHNote())
				+ "</td>"
				+ "</tr>"

				// ligne Plus faible moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus faible Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_A4.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_CD.getLNote())
				+ "</td>"
				+ "</tr>"

				// ligne moyenne de passage
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Moyenne de Passage</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_A4.getMPass())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_CD.getMPass())
				+ "</td>"
				+ "</tr>"

				// ligne Nombre d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'admis</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_A4.getAdmis()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta2nde_CD.getAdmis()
				+ "</td>"
				+ "</tr>"

				// ligne % d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>% d'admis</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_A4.getPerAdmis())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta2nde_CD.getPerAdmis())
				+ "</td>"
				+ "</tr>"

				// Nombre d'élèves autorisés à redoubler
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'élèves autorisés à "
				+ "redoubler</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>" + "</tr>";

		htmlBody += "</table>";// fin table sta

		// table des signature
		bs.load();
		htmlBody += bs.getTabSignature(DocFormat.CHEF, "tabSB");

		htmlBody += "</div>" // fin cadre
				+ "</div>"// saut page
				+ "<div id='sautligne'></div>"; // le saut de ligne
		// fin niveau Seconde#############################################

		// Niveau
		// PREMIERE#########################################################"
		htmlBody += "<div class='saut'>"// saut page
				+ "<div id='sautligne'></div>"
				+ "<div class='cadre'>" // le cadre

				// la table des en-têtes
				+ "<table class='tabSB' width='100%'>"
				+ "<tr>"
				+ "<td width='70%'>" + "<div>"
				+ Constance.INSPECTION_ABR
				+ "</div>" + "<div> Etablissement: "
				+ Constance.INITIALE
				+ "&#160;" + Constance.NOM + "</div>"
				+ "</td>"
				+ "<td width='30%'>Année Scolaire: " + annee
				+ "</td></tr>"

				+ "<tr>"
				+ "<td class='tdb' align='center' colspan='3'>"
				+ "<div>RESULTATS DE FIN D'ANNEE- SCOLAIRE "
				+ annee
				+ " PAR NIVEAU</div>"
				+ "<div><b>Statistiques des moyennes générales des élèves "
				+ "de <u>Première</u></b></div>" + "</td>" + "</tr>"

				+ "</table>";// fin table des en-têtes

		// Le titre du tableau
		htmlBody += "<table class='tabStaMoyAn' width='100%'>"// table sta
				+ "<tr>"
				+ "<td class='tdB' size='28%'></td>"
				+ "<td class='tdSBL' size='18%'>PREMIERE A</td>"
				+ "<td class='tdSBL' size='18%'>PREMIERE C</td>"
				+ "<td class='tdSBL' size='18%'>PREMIERE D</td>" + "</tr>"

				// ligne inscrits
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Inscrits</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInscrit()
				+ "</td>"
				+ "</tr>"

				// ligne présents en fin d'année
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Présent en fin d'année</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getPresent()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 2
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$0\\leqslant Moyennes<2$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf2()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 4
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$2\\leqslant Moyennes<4$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf4()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 6
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$4\\leqslant Moyennes<6$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf6()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 8
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$6\\leqslant Moyennes<8$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf8()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$8\\leqslant Moyennes<10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf10()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 12
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$10\\leqslant Moyennes<12$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf12()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 14
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$12\\leqslant Moyennes<14$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf14()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 16
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$14\\leqslant Moyennes<16$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf16()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$16\\leqslant Moyennes<18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getInf18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getSup18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_A4.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_C4.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ sta1ere_D.getSup10()
				+ "</td>"
				+ "</tr>"

				// ligne per sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$\\% Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_A4.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_C4.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_D.getPerSup10())
				+ "</td>"
				+ "</tr>"

				// ligne moyenne niveau
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Moyenne du niveau</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_A4.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_C4.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_D.getMoyCls())
				+ "</td>"
				+ "</tr>"

				// ligne Plus forte moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus forte Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_A4.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_C4.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_D.getHNote())
				+ "</td>"
				+ "</tr>"

				// ligne Plus faible moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus faible Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_A4.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_C4.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(sta1ere_D.getLNote())
				+ "</td>"
				+ "</tr>"

				// ligne Nombre d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'admis au BAC 1</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// ligne % d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>% d'admis au BAC 1</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// Nombre d'élèves autorisés à redoubler
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'élèves autorisés à "
				+ "redoubler</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>";

		htmlBody += "</table>";// fin table sta

		// table des signature
		bs.load();
		htmlBody += bs.getTabSignature(DocFormat.CHEF, "tabSB");

		htmlBody += "</div>" // fin cadre
				+ "</div>"// saut page
				+ "<div id='sautligne'></div>"; // le saut de ligne
		// fin niveau PREMIERE#############################################

		// Niveau
		// TERMINALE#########################################################"
		htmlBody += "<div class='saut'>"// saut page
				+ "<div id='sautligne'></div>"
				+ "<div class='cadre'>" // le cadre

				// la table des en-têtes
				+ "<table class='tabSB' width='100%'>"
				+ "<tr>"
				+ "<td width='70%'>" + "<div>"
				+ Constance.INSPECTION_ABR
				+ "</div>" + "<div> Etablissement: "
				+ Constance.INITIALE
				+ "&#160;" + Constance.NOM + "</div>"
				+ "</td>"
				+ "<td width='30%'>Année Scolaire: " + annee
				+ "</td></tr>"

				+ "<tr>"
				+ "<td class='tdb' align='center' colspan='3'>"
				+ "<div>RESULTATS DE FIN D'ANNEE- SCOLAIRE "
				+ annee
				+ " PAR NIVEAU</div>"
				+ "<div><b>Statistiques des moyennes générales des élèves "
				+ "de <u>Terminale</u></b></div>" + "</td>" + "</tr>"

				+ "</table>";// fin table des en-têtes

		// Le titre du tableau
		htmlBody += "<table class='tabStaMoyAn' width='100%'>"// table sta
				+ "<tr>"
				+ "<td class='tdB' size='28%'></td>"
				+ "<td class='tdSBL' size='18%'>TERMINALE A</td>"
				+ "<td class='tdSBL' size='18%'>TERMINALE C</td>"
				+ "<td class='tdSBL' size='18%'>TERMINALE D</td>" + "</tr>"

				// ligne inscrits
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Inscrits</td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInscrit()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInscrit()
				+ "</td>"
				+ "</tr>"

				// ligne présents en fin d'année
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Présent en fin d'année</td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getPresent()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getPresent()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 2
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$0\\leqslant Moyennes<2$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf2()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf2()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 4
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$2\\leqslant Moyennes<4$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf4()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf4()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 6
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$4\\leqslant Moyennes<6$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf6()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf6()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 8
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$6\\leqslant Moyennes<8$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf8()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf8()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$8\\leqslant Moyennes<10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf10()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 12
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$10\\leqslant Moyennes<12$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf12()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf12()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 14
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$12\\leqslant Moyennes<14$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf14()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf14()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 16
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$14\\leqslant Moyennes<16$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf16()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf16()
				+ "</td>"
				+ "</tr>"

				// ligne inf à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$16\\leqslant Moyennes<18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getInf18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getInf18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 18
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 18$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getSup18()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getSup18()
				+ "</td>"
				+ "</tr>"

				// ligne sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ staTle_A4.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_C4.getSup10()
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ staTle_D.getSup10()
				+ "</td>"
				+ "</tr>"

				// ligne per sup à 10
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>"
				+ "<latex>$\\% Moy\\geqslant 10$</latex></td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_A4.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_C4.getPerSup10())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_D.getPerSup10())
				+ "</td>"
				+ "</tr>"

				// ligne moyenne niveau
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Moyenne du niveau</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_A4.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_C4.getMoyCls())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_D.getMoyCls())
				+ "</td>"
				+ "</tr>"

				// ligne Plus forte moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus forte Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_A4.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_C4.getHNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_D.getHNote())
				+ "</td>"
				+ "</tr>"

				// ligne Plus faible moyenne
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Plus faible Moyenne</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_A4.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_C4.getLNote())
				+ "</td>"
				+ "<td class='tdBInf'>"
				+ formatter.format(staTle_D.getLNote())
				+ "</td>"
				+ "</tr>"

				// ligne Nombre d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'admis au BAC 2</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// ligne % d'admis
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>% d'admis</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>"

				// Nombre d'élèves autorisés à redoubler
				+ "<tr>"
				+ "<td class='tdSBT' size='28%'>Nombre d'élèves autorisés à "
				+ "redoubler</td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "<td class='tdBInf'></td>"
				+ "</tr>";

		htmlBody += "</table>";// fin table sta

		// table des signature
		bs.load();
		htmlBody += bs.getTabSignature(DocFormat.CHEF, "tabSB");

		htmlBody += "</div>" // fin cadre
				+ "</div>"// saut page
				+ "<div id='sautligne'></div>"; // le saut de ligne
		// fin niveau PREMIERE#############################################

		html += "<html>" + "<head>" + "<meta charset='utf-8' />" + "</head>"
				+ "<body><div id='rapportsta'>" + htmlBody + "</div>"
				+ "</body></html>";

		System.out.println(html);

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				editor.setLatexSize(12);
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
