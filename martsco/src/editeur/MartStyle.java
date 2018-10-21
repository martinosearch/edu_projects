package editeur;

import graphicsModel.MartList;
import myStyleSheets.StyleBulletin;
import myStyleSheets.StyleFiche;
import myStyleSheets.StylePage;
import myStyleSheets.StyleReleve;
import myStyleSheets.StyleTable;

public class MartStyle extends MartList<String> {

	public MartList<String> rules;
	public static int BULL = 0, DOCUMENTS = 1, RELEVE = 3;
	public static String padding = "padding:0pt;";
	public static String rowHeight = "height:1pt;";

	public MartStyle() {
		rules = new MartList<String>();
	}

	public MartList<String> getStyle(int type) {
		if (type == MartStyle.BULL) {
			add(new StyleBulletin());
			add(new StylePage());
			add(new StyleTable());
		}

		if (type == MartStyle.RELEVE) {
			add(new StyleReleve());
			add(new StylePage());
			add(new StyleTable());
		}

		if (type == MartStyle.DOCUMENTS) {
			add(new StyleFiche());
			add(new StylePage());
			add(new StyleTable());
		}

		return rules;
	}

	private void add(MartStyle liste) {
		for (String str : liste) {
			rules.add(str);
		}
	}

	public static void setPadding(int pad) {
		padding = "padding:" + pad + "pt;";
	}

	public static void setRowheight(int h) {
		rowHeight = "height:" + h + "pt;";
	}
}
