package myStyleSheets;

import rapportBulletin.DocFormat;
import editeur.MartStyle;
import function.Constance;

public class StyleTable extends MartStyle {
	private static final long serialVersionUID = -6465797044885728513L;

	public StyleTable() {

		// page
		// rules.add("@page{size: A4;margin: 0;}");
		// rules.add("@media print {htm, body { width: 210mm;height: 297mm;}}");
		add("#footer{text-align:center;" + "padding:0pt;" + "font-size:12pt;"
				+ "font-family:bookman old style;" + "border-width:1pt;"
				+ "border-top-style:solid;" + "}");

		// table par défaut
		add(".tabB{border-width:1pt;" + "border-style:solid;"
				+ "border-color:black;" + "border-collapse:collapse;"
				+ "border-spacing:0pt;}");

		// table en-tête
		add(".tabSB{border-width:0pt;" + "border-style:none;"
				+ "border-color:none;" + "border-collapse:collapse;"
				+ "border-spacing:0pt;}");

		// table Statistique
		add(".tabStaMoyTrim{" + "font-size:8pt;" + "border-width:0pt;"
				+ "border-style:none;" + "border-color:none;"
				+ "border-collapse:collapse;" + "border-spacing:0pt;}");

		// table Statistique
		if (Constance.TYPE_STA_MOY_TRIM == DocFormat.STA_MIXTE) {
			add(".tabStaMoyTrim{" + "font-size:14pt;" + "border-width:0pt;"
					+ "border-style:none;" + "border-color:none;"
					+ "border-collapse:collapse;" + "border-spacing:0pt;}");
		} else {
			add(".tabStaMoyTrim{" + "font-size:10pt;" + "border-width:0pt;"
					+ "border-style:none;" + "border-color:none;"
					+ "border-collapse:collapse;" + "border-spacing:0pt;}");
		}

		if (Constance.TYPE_STA_MOY_AN == DocFormat.STA_MIXTE) {
			add(".tabStaMoyAn{" + "font-size:14pt;" + "border-width:0pt;"
					+ "border-style:none;" + "border-color:none;"
					+ "border-collapse:collapse;" + "border-spacing:0pt;}");
		} else {
			add(".tabStaMoyAn{" + "font-size:10pt;" + "border-width:0pt;"
					+ "border-style:none;" + "border-color:none;"
					+ "border-collapse:collapse;" + "border-spacing:0pt;}");
		}

		if (Constance.TYPE_STA_NOTE_COMPO == DocFormat.STA_MIXTE) {
			add(".tabStaNoteCompo{" + "font-size:14pt;" + "border-width:0pt;"
					+ "border-style:none;" + "border-color:none;"
					+ "border-collapse:collapse;" + "border-spacing:0pt;}");
		} else {
			add(".tabStaNoteCompo{" + "font-size:10pt;" + "border-width:0pt;"
					+ "border-style:none;" + "border-color:none;"
					+ "border-collapse:collapse;" + "border-spacing:0pt;}");
		}

		// affichage des moyennes
		add("#tabMoy{border-width:0pt;font-size:12pt;"
				+ "border-style:none;border-color:none;"
				+ "border-collapse:collapse;" + "border-spacing:0pt;}");

		// tableau d'honneur
		add(".ficheNoteExemplaire{border-width:0pt;"
				+ "font-size:12pt;font-family:cambria" + "border-style:none;"
				+ "border-color:none;" + "border-collapse:collapse;"
				+ "border-spacing:0pt;" + "vertical-align:top;}");

		add(".tabSB{border-width:0pt;" + "border-style:none;"
				+ "border-color:none;" + "border-collapse:collapse;"
				+ "border-spacing:0pt;}");

		// Cellules de tableau
		add(".tdtitletopleft{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:solid;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:center;"
				+ "background-color:none;" + rowHeight + padding + "}");

		add(".tdtitletopleftC{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:solid;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:center;"
				+ "background-color:silver;" + rowHeight + padding + "}");

		add(".tdtitletop{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:center;"
				+ "background-color:none;" + rowHeight + padding + "}");

		add(".tdtitletopC{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:center;"
				+ "background-color:silver;" + rowHeight + padding + "}");

		add(".tdtitlerightC{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:none;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:center;"
				+ "background-color:silver;" + rowHeight + padding + "}");

		add(".tdtitleleft{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:solid;" + "border-right-style:solid;"
				+ "border-top-style:none;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:left;"
				+ "background-color:none;" + rowHeight + padding + "}");

		add(".tdtitleleftC{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:solid;"
				+ "border-top-style:none;" + "border-color:black;"
				+ "font-weight:bold;" + "text-align:left;"
				+ "background-color:silver;" + rowHeight + padding + "}");

		add(".tdB{border-width:1pt;" + "border-style:solid;"
				+ "border-color:black;" + rowHeight + padding + "}");

		add(".tdSB{border-width:0pt;" + "border-style:none;" + padding + "}");

		add(".tdBSup{border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:solid;" + "border-right-style:none;"
				+ "border-bottom-style:none;" + "border-color:black;"
				+ rowHeight + padding + "}");

		add(".tdSBL{border-width:1pt;" + "border-left-style:none;"
				+ "border-top-style:solid;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ rowHeight + padding + "}");

		add(".tdSBT{border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ rowHeight + padding + "}");

		add(".tdBInf{border-width:1pt;" + "border-left-style:none;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ rowHeight + padding + "}");

		add(".tdSBB{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:none;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;" + rowHeight
				+ padding + "}");

		add(".tdBB{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:none;"
				+ "border-top-style:none;" + "border-color:black;" + rowHeight
				+ padding + "}");

		add(".tdBTB{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:none;"
				+ "border-top-style:solid;" + "border-color:black;" + rowHeight
				+ padding + "}");

		add(".tdBLR{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:none;" + "border-right-style:solid;"
				+ "border-top-style:none;" + "border-color:black;" + rowHeight
				+ padding + "}");

		add(".tdSBI{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:none;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;" + rowHeight
				+ padding + "}");

		// cellule type de mati�res
		add(".typemat{border-width:1pt;" + "border-top-style:none;"
				+ "border-right-style:none;" + "border-bottom-style:solid;"
				+ "border-left-style:none;" + "border-color:black;"
				+ "text-align:center;" + "font-size:10pt;"
				+ "font-weight:bold;" + rowHeight + padding + "}");

		// *************************************************************
		// les cellules coloriées
		add(".tdBC{border-width:1pt;" + "border-style:solid;"
				+ "border-color:black;" + "background-color:rgb(203,203,207);"
				+ rowHeight + padding + "}");

		add(".tdSBC{border-width:0pt;" + "border-style:none;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdBSupC{border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:solid;" + "border-right-style:none;"
				+ "border-bottom-style:none;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdSBLC{border-width:1pt;" + "border-left-style:none;"
				+ "border-top-style:solid;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdSBTC{border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdBInfC{border-width:1pt;" + "border-left-style:none;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdSBBC{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:none;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdBBC{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:none;"
				+ "border-top-style:none;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdBTBC{border-width:1pt;" + "border-left-style:none;"
				+ "border-bottom-style:solid;" + "border-right-style:none;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdBLRC{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:none;" + "border-right-style:solid;"
				+ "border-top-style:none;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		add(".tdSBIC{border-width:1pt;" + "border-left-style:solid;"
				+ "border-bottom-style:none;" + "border-right-style:solid;"
				+ "border-top-style:solid;" + "border-color:black;"
				+ "background-color:rgb(203,203,207);" + rowHeight + padding
				+ "}");

		// ***********************************************************
	}

}
