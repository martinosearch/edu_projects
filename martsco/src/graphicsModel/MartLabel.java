package graphicsModel;

import javax.swing.JLabel;

public class MartLabel extends JLabel {

	public MartLabel(String str) {
		super();
		setText("<html><head><meta><style>" + getStyle()
				+ "</style></meta></head><body>" + str + "</body></html>");
	}

	public MartLabel() {
		super();
	}

	public void setText(String str) {
		super.setText("<html><head><meta><style>" + getStyle()
				+ "</style></meta></head><body>" + str + "</body></html>");
	}

	public static String getStyle() {
		String style = "#explication{font-size:12pt;"
				+ "font-family:'bookman old style';font-style:'italic';font-weight:normal;"
				+ "}";

		return style;
	}

}
