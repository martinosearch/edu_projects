package myStyleSheets;

import editeur.MartStyle;
import function.Constance;

public class StyleFiche extends MartStyle {
	private static final long serialVersionUID = -6465797044885728513L;

	private String policeNote;

	public StyleFiche() {
		policeNote = Constance.POLICE_NOTE;

		// page
		// rules.add("@page{size: A4;margin: 0;}");
		// rules.add("@media print {htm, body { width: 210mm;height: 297mm;}}");

		// les rapport statisi
		add("#rapportsta{font-size:14pt;" + "font-family:'calibri';"
				+ "border:0pt black none;" + "border-collapse:collapse;"
				+ padding + "}");

		add("#enLettres{font-size:10pt;" + "font-family: 'consolas';"
				+ "font-weight:bold;" + "text-align:center;"
				+ "border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ "border-collapse:collapse;" + "padding:0pt;}");

		add("#mention{font-size:10pt;" + "font-family: 'Tahoma';"
				+ "font-weight:bold;" + "text-align:left;"
				+ "border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ "border-collapse:collapse;" + "padding:0pt;}");

		add("#obsConseil{font-size:12pt;" + "font-family: 'Tahoma';"
				+ "font-weight:bold;" + "text-align:left;"
				+ "border-width:1pt;" + "border-left-style:solid;"
				+ "border-top-style:none;" + "border-right-style:solid;"
				+ "border-bottom-style:solid;" + "border-color:black;"
				+ "border-collapse:collapse;" + "padding:0pt;}");

		add("#tabHon{border-width:1pt;font-size:9pt;"
				+ "border-style:solid;border-color:none;"
				+ "border-collapse:collapse;border-spacing:0pt;}");

		add("#discipline{border-width:1pt;font-size:9pt;"
				+ "border-style:solid;border-color:none;"
				+ "border-collapse:collapse;}");

		add("#tabMoyClasse{border-width:0pt;font-size:11pt;"
				+ "border-style:none;border-color:none;"
				+ "border-collapse:collapse;border-spacing:0pt;"
				+ "vertical-align:top;}");

		add("#result{font-size:12pt;font-weight:normal;"
				+ "font-family:'courier new';text-align:left;}");

		add("#ficheNote{font-size:12pt;" + "font-weight:normal;"
				+ "font-family:'new times roman';}");

		add("#pays{font-size:10pt;" + "font-family:'bookman old style';"
				+ "border:1pt black none;" + "padding:0pt;"
				+ "vertical-align:top;" + "text-align:center;}");

		add("#pays_releve{font-size:8pt;" + "font-family:'bookman old style';"
				+ "border:1pt black none;" + "padding:0pt;"
				+ "vertical-align:top;" + "text-align:center;}");

		add("#blocidentites{border:0pt black none;"
				+ "font-family:'bookman old style';" + "padding:0pt;"
				+ "border-spacing:0pt;" + "border-collapse:collapse;}");

		add("#adresse1{font-size:15pt;" + "font-family:'Tahoma';"
				+ "font-weigth:bold;" + "border:1pt black none;"
				+ "vertical-align:center;" + "text-align:center;"
				+ "padding:0pt;}");

		add("#adresse2{font-size:7pt;" + "font-family:'Tahoma';"
				+ "font-weight:bold;" + "border:1pt black none;"
				+ "vertical-align:top;" + "text-align:left;" + "padding:0pt;}");

		// titre avec soulignement
		add("#titre{font-size:14pt;" + "font-family:'Tahoma';"
				+ "font-weight:bold;" + "border:0pt black none;"
				+ "padding:0pt;" + "text-align:left;}");

		add("#titreBull{font-size:14pt;"
				+ "font-family:'Tahoma';font-weight:bold;"
				+ "border-width:2pt;border-left-style:none;"
				+ "border-bottom-style:solid;border-right-style:none;"
				+ "border-top-style:solid;padding:0pt;" + "text-align:center;"
				+ "background-color:silver;}");

		add("#titreCd{font-size:14pt;"
				+ "font-family:'Tahoma';font-weight:bold;"
				+ "border-width:1pt;border-left-style:solid;"
				+ "border-bottom-style:solid;border-right-style:solid;"
				+ "border-top-style:solid;padding:0pt;" + "text-align:center;"
				+ "background-color:silver;}");

		add("#titreCdSmall{font-size:11pt;"
				+ "font-family:'Comic Sans MS';font-weight:bold;"
				+ "border-width:1pt;border-left-style:solid;"
				+ "border-bottom-style:solid;border-right-style:solid;"
				+ "border-top-style:solid;padding:0pt;border-collapse:collapse;text-align:center;"
				+ "background-color:silver;}");

		// titre sans soulignement
		add("#titreSS{font-size:12pt;" + "font-family:'bookman old style';"
				+ "font-weight:bold;}");

		// reference de l'eleve
		add("#ideleve{border: .5pt black solid;"
				+ "font-family:'bookman old style';" + "vertical-align:top;"
				+ "font-size:12pt;" + "font-family:'bookman old style';"
				+ "font-weight:bold;" + "font-style:normal;" + "padding:0pt;"
				+ "border-collapse:collapse;}");

		add("#nom{" + "font-size:18pt;" + "font-family:'bookman old style';"
				+ "font-weight:bold;" + "font-style:normal;}");

		add("#textNormal{" + "font-size:12pt;"
				+ "font-family:'bookman old style';" + "font-weight:normal;"
				+ "font-style:normal;}");

		// reference de la classe
		add("#idclasse{border: 0pt black solid;" + "vertical-align:top;"
				+ "font-size:16pt;" + "font-family:'bookman old style';"
				+ "font-weight:bold;" + "font-style:normal;" + "padding:0pt;"
				+ "border-collapse:collapse;}");

		// numero d'ordre
		add("#numordre{text-align:right;" + "padding:0pt;}");

		// notes
		add("#notes{border: 1pt blue solid; padding:0pt;" + "font-size:10pt;"
				+ "font-family:'" + policeNote + "';}");

		// annéé- scolaire et trimestre
		add("#annee{border: 0pt black solid;"
				+ "vertical-align:bottom;text-align:center;"
				+ "font-size:10pt;font-family:'bookman old style';"
				+ "font-weight:bold;" + "font-style:normal;padding:0pt;"
				+ "border-collapse:collapse;}");

		// notes rel
		add("#notesRel{padding:0pt;" + "font-size:12pt;" + "font-family:'"
				+ policeNote + "';" + "}");

		// note d'avertissement
		add("#NB{text-align:right;" + "padding:0pt;" + "font-size:6pt;"
				+ "font-style:italic;}");

		add("#pub{text-align:left;" + "padding:0pt;" + "font-size:5pt;"
				+ "font-style:italic;}");

		add("#devise{text-align:center;" + "padding:0pt;" + "font-size:10pt;"
				+ "font-family:'cmathcal','brusk script m7';"
				+ "font-style:normal;}");

		add("#codebar{text-align:left;" + "padding:1pt;" + "font-size:12pt;"
				+ "font-family:ccode39;" + "border:1pt black none;}");

		add("#personnalite{text-align:center;" + "padding:0pt;"
				+ "font-size:12pt;" + "font-weight:bold;"
				+ "font-family:bookman old style;" + "}");

		// ***********************************************************
	}

}
