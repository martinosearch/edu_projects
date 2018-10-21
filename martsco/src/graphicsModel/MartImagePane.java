package graphicsModel;

import java.io.File;
import java.net.URL;

import editeur.MartEditorPane;

public class MartImagePane extends MartEditorPane {

	private URL filigrane;
	private String html;

	public MartImagePane() {

	}

	public void setImageText(String text, URL image) {
		filigrane = image;

		html = "<html><head><meta><style>body{background-image:"
				+ filigrane
				+ ";background-position:center;background-repeat:no-repeat"
				+ "}"
				+ "#contact{font-size:10pt;font-family:'comic sans ms';font-weight:bold;"
				+ "color:blue}"
				+ "#titre{font-size:40pt;font-family:'consolas';font-weight:bold;"
				+ "color:yellow}</style></meta></head><body>" + text
				+ "</body></html>";

		this.setHtml(html);
		this.revalidate();
	}
}
