package myStyleSheets;

import editeur.MartStyle;

public class StylePage extends MartStyle {

	private static final long serialVersionUID = 2229923636000690090L;

	public StylePage() {
		// page
		// rules.add("@page{size: A4;margin: 0;}");
		// rules.add("@media print {htm, body { width: 210mm;height: 297mm;}}");

		// saut de ligne
		add(".saut{height:820pt;overflow:hidden;width:100%;"
				+ "border:1pt blue none;" + "padding:0pt;"
				+ "vertical-align:center;}");

		add(".sautPaysage{height:574pt;overflow:hidden; width:100%;"
				+ "border:1pt blue none; padding:0pt;"
				+ "vertical-align:center;}");

		add("#sautligne{padding:0pt;" + "text-align:left;" + "font-size:1pt;}");
	}

}
