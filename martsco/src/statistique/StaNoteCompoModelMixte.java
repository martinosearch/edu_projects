package statistique;

import function.Constance;
import function.MartArranger;
import function.StaData;
import function.Statistician;
import function.Statistician.MatSta;
import function.Statistician.MoySta;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

import matiere.Matiere;
import matiere.MatiereProg;
import note.InfoClasse;
import note.InfoNote;
import note.Moyenne;
import note.NoteViewer;

import org.joda.time.DateTime;

import classe.Classe;
import classe.Niveau;
import configurationEcole.ConfigEcole;
import abstractObject.AbstractModel;
import annee.Decoupage;
import progress.Progress;
import rapportBulletin.DocFormat;
import rapportBulletin.Histo;
import rapportBulletin.HistoManager;
import connection.DAO;
import connection.DAOFactory;
import editeur.MartEditorPane;
import editeur.MartStyle;
import editeur.Preview;
import eleve.EleveClasse;

public class StaNoteCompoModelMixte extends StaNoteCompoModel {

	public StaNoteCompoModelMixte() {
		super();
	}

	public void write() {
		htmlBody += "<div class='saut'>"// saut de page A4
				+ "<div id='sautligne'></div>"
				+ "<div class='cadre'>" // le cadre

				// la table des en-têtes
				+ "<table class='tabSB' width='100%'>"
				+ "<tr>"
				+ "<td class='tdSB' width='100%' colspan='2'>"
				+ Constance.INSPECTION_ABR
				+ "</td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td class='tdSB' width='80%'>Etablissement: "
				+ Constance.INITIALE
				+ "&#160;"
				+ Constance.NOM
				+ "</td>"
				+ "<td class='tdSB' width='20%'>Année Scolaire: "
				+ annee
				+ "</td></tr>"
				+ "<tr>"
				+ "<td class='tdSB' colspan='2'>Niveau: <u>"
				+ niveau
				+ "</u></td>"
				+ "</tr>"

				+ "<tr>"
				+ "<td class='tdSB' align='center' colspan='2'><div><b>STATISTIQUES"
				+ " DES NOTES PAR MATIERE DE LA COMPOSITION DU "
				+ (dec.toString()).toUpperCase()
				+ "</b></div>"
				+ "<div id='sautligne'></div>" + "</td>" + "</tr>"

				+ "</table>";// fin table des en-têtes

		htmlBody += "<table class='rapportsta' width='100%'>"

		+ "<tr>" + "<td width='90%' class='tdB' colspan='" + (nbreCol - 1)
				+ "' align='center'><b>" + "NOTES DE COMPOSITION</b></td>"

				+ "<td width='10%' class='tdB' rowspan='2'"
				+ " align='center'><b>" + "Répartition des Moyennes "
				+ dec.getAdjFemP() + " des élèves</b></td>"

				+ "</tr>"

				+ "<tr>" + "<td width='20%' class='tdSBT'>"
				+ "<b>MATIERES</b></td>" + "</td>";

		for (MatSta mst2 : stamat) {
			Matiere mat = (Matiere) matdao.findObj(mst2.getIntitule());
			String matdim = mat.getDim();

			htmlBody += "<td width='4%' class='tdBInf'><b>" + matdim
					+ "</b></td>";
		}

		htmlBody += "<tr>" + "<td class='tdSBT'>Présents</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getPresent() + "</td>";
		}
		htmlBody += "<td class='tdBInf' >" + staG.getPresent() + "</td>"
				+ "</tr>";

		// sup à
		htmlBody += "<tr>" + "<td class='tdSBT'>[0, 2[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf2() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf2() + "</td>" + "</tr>";

		// sup à 2
		htmlBody += "<tr>" + "<td class='tdSBT'>[2, 4[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf4() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf4() + "</td>" + "</tr>";

		// sup à 4
		htmlBody += "<tr>" + "<td class='tdSBT'>[4, 6[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf6() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf6() + "</td>" + "</tr>";

		// sup à 6
		htmlBody += "<tr>" + "<td class='tdSBT'>[6, 8[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf8() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf8() + "</td>" + "</tr>";

		// sup à 8
		htmlBody += "<tr>" + "<td class='tdSBT'>[8, 10[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf10() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf10() + "</td>" + "</tr>";

		// sup à 10
		htmlBody += "<tr>" + "<td class='tdSBT'>[10, 12[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf12() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf12() + "</td>" + "</tr>";

		// sup à 12
		htmlBody += "<tr>" + "<td class='tdSBT'>[12, 14[</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf14() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf14() + "</td>" + "</tr>";

		// sup à 14
		htmlBody += "<tr>" + "<td class='tdSBT'>[14, 16[</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf16() + "</td>";
		}

		htmlBody += "<td class='tdBInf'>" + staG.getInf16() + "</td>" + "</tr>";
		// sup à 16
		htmlBody += "<tr>" + "<td class='tdSBT'>[16, 18[</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf18() + "</td>";
		}

		htmlBody += "<td class='tdBInf'>" + staG.getInf18() + "</td>" + "</tr>";
		// sup à 18
		htmlBody += "<tr>" + "<td class='tdSBT'>"
				+ "<latex>Notes\\geqslant 18</latex>" + "</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getSup18() + "</td>";
		}

		htmlBody += "<td class='tdBInf'>" + staG.getSup18() + "</td>" + "</tr>";

		// ont la moyenne
		htmlBody += "<tr>" + "<td class='tdSBTC'>"
				+ "<latex>Notes\\geqslant 10</latex>" + "</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInfC'>" + mst2.getSup10() + "</td>";
		}
		htmlBody += "<td class='tdBInfC'>" + staG.getSup10() + "</td>"
				+ "</tr>";

		// pour de ce qui ont la moy
		htmlBody += "<tr>" + "<td class='tdSBTC'>"
				+ "<latex>\\% Notes\\geqslant 10</latex>" + "</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInfC'>"
					+ formatter.format(mst2.getPerSup10()) + "</td>";
		}
		htmlBody += "<td class='tdBInfC'>"
				+ formatter.format(staG.getPerSup10()) + "</td>" + "</tr>";

		// sup à
		htmlBody += "<tr>" + "<td class='tdSBT'>Moy. de Classe</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getMoyCls()) + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + formatter.format(staG.getMoyCls())
				+ "</td>" + "</tr>";

		// sup à
		htmlBody += "<tr>" + "<td class='tdSBT'>Plus forte note</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getHNote()) + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + formatter.format(staG.getHNote())
				+ "</td>" + "</tr>";

		// sup à
		htmlBody += "<tr>" + "<td class='tdSBT'>Plus faible note</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getLNote()) + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + formatter.format(staG.getLNote())
				+ "</td>" + "</tr>";

		htmlBody += "</table>";// fin du tableau sta

		// signature
		htmlBody += docSet.getSignatureSta();

		htmlBody += "</div>" // fin cadre
				+ "</div>" + "<div id='sautligne'></div>"; // le
															// saut
															// de
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
