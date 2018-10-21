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

public class StaNoteCompoModelMascFem extends StaNoteCompoModel {

	public StaNoteCompoModelMascFem() {
		super();
	}

	@Override
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
				+ trimstr
				+ "&#160;"
				+ (dec.toString()).toUpperCase()
				+ "</b></div>"
				+ "<div id='sautligne'></div>" + "</td>" + "</tr>"

				+ "</table>";
		// fin table des en-têtes

		htmlBody += "<table class='tabStaNoteCompo' width='100%'>"
				+ "<tr>"
				+ "<td width='85%' class='tdB' colspan='"
				+ (nbreCol)
				+ "' align='center'><b>"
				+ "NOTES DE COMPOSITION</b></td>"

				+ "<td width='15%' class='tdB' rowspan='2'"
				+ " align='center'><b>"
				+ "Répartition des Moyennes "
				+ dec.getAdjFemP()
				+ " des élèves</b></td>"

				+ "</tr>"
				+ "<tr class='rowSta'>"
				+ "<td width='17%' class='tdSBT' colspan='2'>"
				+ "<b>MATIERES</b></td>" + "</td>";

		for (MatSta mst2 : stamat) {
			Matiere mat = (Matiere) matdao.findObj(mst2
					.getIntitule());
			String matdim = mat.getDim();

			htmlBody += "<td width='4%' class='tdBInf'><b>"
					+ matdim + "</b></td>";
		}

		// Present
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "Présents</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getPresentg() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getPresentg()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getPresentf() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getPresentf()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getPresent() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getPresent()
				+ "</td>" + "</tr>";

		// Inférieur à 2
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[0, 2[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf2g()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf2g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf2f()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf2f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf2()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf2()
				+ "</td>" + "</tr>";

		// Inférieur à 4
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[2, 4[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf4g()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf4g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf4f()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf4f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf4()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf4()
				+ "</td>" + "</tr>";

		// Inférieur à 6
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[4, 6[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf6g()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf6g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf6f()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf6f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf6()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf6()
				+ "</td>" + "</tr>";

		// Inférieur à 8
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[6, 8[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf8g()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf8g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf8f()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf8f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf8()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf8()
				+ "</td>" + "</tr>";

		// Inférieur à 10
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[8, 10[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf10g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf10g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf10f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf10f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf10()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf10()
				+ "</td>" + "</tr>";

		// Inférieur à 12
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[10, 12[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf12g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf12g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf12f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf12f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf12()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf12()
				+ "</td>" + "</tr>";

		// Inférieur à 14
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[12, 14[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf14g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf14g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf14f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf14f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr  class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf14()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf14()
				+ "</td>" + "</tr>";

		// Inférieur à 16
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[14, 16[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf16g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf16g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf16f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf16f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf16()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf16()
				+ "</td>" + "</tr>";

		// Inférieur à 18
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "[16, 18[</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf18g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf18g()
				+ "</td>" + "</tr>";
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getInf18f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf18f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getInf18()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getInf18()
				+ "</td>" + "</tr>";

		// Sup18
		htmlBody += "<tr  class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "<latex>Notes\\geqslant 18</latex>" + "</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getSup18g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getSup18g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getSup18f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getSup18f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getSup18()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getSup18()
				+ "</td>" + "</tr>";

		// On leur moyenne
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "<latex>Notes\\geqslant 10</latex>" + "</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getSup10g() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getSup10g()
				+ "</td>" + "</tr>";

		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ mst2.getSup10f() + "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getSup10f()
				+ "</td>" + "</tr>";

		htmlBody += "<tr  class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>" + mst2.getSup10()
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>" + staG.getSup10()
				+ "</td>" + "</tr>";

		// Pourcentage de ce qui ont leur moyenne
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' width='17%' rowspan='3'>"
				+ "<latex>\\% Notes\\geqslant 10</latex>"
				+ "</td>"
				+ "<td class='tdSBT' width='3%'>G</td>";

		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getPerSup10g())
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>"
				+ formatter.format(staG.getPerSup10g())
				+ "</td>" + "</tr>";
		htmlBody += "<tr  class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>F</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getPerSup10f())
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>"
				+ formatter.format(staG.getPerSup10f())
				+ "</td>" + "</tr>";

		htmlBody += "<tr  class='rowSta'>"
				+ "<td class='tdSBT' width='3%'>T</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getPerSup10())
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>"
				+ formatter.format(staG.getPerSup10())
				+ "</td>" + "</tr>";

		// sup à
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' colspan='2'>Moy. de Classe</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getMoyCls())
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>"
				+ formatter.format(staG.getMoyCls()) + "</td>"
				+ "</tr>";

		// sup à
		htmlBody += "<tr  class='rowSta'>"
				+ "<td class='tdSBT' colspan='2'>Plus forte note</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getHNote())
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>"
				+ formatter.format(staG.getHNote()) + "</td>"
				+ "</tr>";

		// sup à
		htmlBody += "<tr class='rowSta'>"
				+ "<td class='tdSBT' colspan='2'>Plus faible note</td>";
		for (MatSta mst2 : stamat) {
			htmlBody += "<td class='tdBInf'>"
					+ formatter.format(mst2.getLNote())
					+ "</td>";
		}
		htmlBody += "<td class='tdBInf'>"
				+ formatter.format(staG.getLNote()) + "</td>"
				+ "</tr>";

		htmlBody += "</table>";// fin du tableau

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
