package editeur;

import java.awt.Color;
import java.awt.print.PageFormat;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;

import function.Constance;

public class MartEditorPane extends JEditorPane {
	String html = "";
	public static int SIMPLE_DOCUMENT = 0, MATHS_DOCUMENT = 1;
	// pour l'impression
	MartHTMLEditorKit kit;
	private int mathOption = 0;
	public int orientation = PageFormat.PORTRAIT;
	private float latexsize = 9;

	public MartEditorPane() {
		kit = new MartHTMLEditorKit();
		this.setEditable(false);
	}

	public MartEditorPane(int math) {
		kit = new MartHTMLEditorKit();
		mathOption = math;
		this.setEditable(false);
	}

	public void setStyle(int typeStyle) {
		MartStyle style = new MartStyle();

		if (typeStyle == MartStyle.BULL) {
			ArrayList<String> rules = style.getStyle(MartStyle.BULL);
			kit.addStyle(rules);
		}

		if (typeStyle == MartStyle.RELEVE) {
			ArrayList<String> rules = style.getStyle(MartStyle.RELEVE);
			kit.addStyle(rules);
		}

		if (typeStyle == MartStyle.DOCUMENTS) {
			ArrayList<String> rules = style.getStyle(MartStyle.DOCUMENTS);
			kit.addStyle(rules);
		}

		this.setEditable(false);
	}

	public void setStyle(String str) {
		kit.addStyle(str);

		this.setEditable(false);
	}

	public void setHtml(String text) {
		html = "";
		if (mathOption == MATHS_DOCUMENT) {
			html = getHtml(text);
		} else {
			html = text;
		}

		super.setText(html);
		// le code pour générer du text à partir du html
		FileWriter fw = null;
		try {
			fw = new FileWriter("C:/temp/tmp.html");
			fw.write(html);
			fw.close();

			File file = new File("C:/temp/tmp.html");
			this.setEditorKit(kit);
			this.setPage(file.toURL());

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		this.revalidate();
	}

	private String getHtml(String text) {

		String htmlLaktex = "", temp = text, latex = "";
		int imgCount = 0;
		int indexLaktex = -1;

		do {
			latex = "";
			try {// tester la présence de laktex
				indexLaktex = temp.indexOf("<latex>");
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (indexLaktex != -1) {// si laktex existe
				htmlLaktex += temp.substring(0, indexLaktex);
				temp = temp.substring(indexLaktex + 7);
				latex = temp.substring(0, temp.indexOf("</latex>"));
				temp = temp.substring(temp.indexOf("</latex>") + 8);
			}

			if (!latex.equals("")) {
				System.out.println("===================>> laktex");
				TeXFormula formula = new TeXFormula(latex);
				formula.createPNG(TeXConstants.STYLE_DISPLAY, latexsize,
						Constance.getTempImagesFolder() + "form" + (imgCount)
								+ ".png", Color.WHITE, Color.black);

				File file = new File(Constance.getTempImagesFolder() + "form"
						+ (imgCount) + ".png");
				htmlLaktex += "<img align='middle' src='" + file.toURI()
						+ "'/>";

				imgCount++;

			}
		} while (indexLaktex != -1);

		// on ajoute le reste de la chaine de caract�res
		htmlLaktex += temp;

		return htmlLaktex;
	}

	public String getHtml() {
		return this.html;
	}

	public void setEditorKit(MartHTMLEditorKit edkit) {
		this.kit = edkit;
	}

	public MartHTMLEditorKit getMartEditorKit() {
		return kit;
	}

	public static void main(String[] args) {
		MartEditorPane pan = new MartEditorPane();
		String str = "<html>"
				+ "<head></head>"
				+ "<body>"
				+ "<div>une première formule <latex>$Moy \\geqslant 10$</latex></div>"
				+ "<div>une deuxieme formule <latex>$x=x^2$</latex></div>"
				+ "<div>ma motivation est forte; un troisième <latex>$moy\\leqslant10$"
				+ "</latex>" + "</div>" + "</body>" + "</html>";

		String cor = pan.getHtml(str);
		System.out.println(cor);
		pan.setHtml(cor);
		JFrame fr = new JFrame();
		fr.setSize(800, 200);
		fr.setLocationRelativeTo(null);
		fr.getContentPane().add(pan);
		fr.setVisible(true);
	}

	public int getMathOption() {
		return mathOption;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orient) {
		this.orientation = orient;
	}

	public void setLatexSize(float f) {
		latexsize = f;
	}
}
