package graphicsModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import editeur.MartEditorPane;

public class PanPub extends JPanel {

	private MartList<Component> liste = new MartList<Component>();

	public PanPub() {

	}

	public void setAvis() {
		MartEditorPane editor = new MartEditorPane();
		editor.setHtml("<html><head></head><body><h3><u>IMPORTANT</u></h3><div>Ce logiciel "
				+ "est une propriété privée de Martino Corporation. Et pour l'heure cette"
				+ " entreprise démeure la seule acréditée à commercialiser ce logiciel. "
				+ "Vous ne devez payer aucune personne non acrédité par Martino Corporation "
				+ "pour son acquisition ou bien pour quel autre service que ce soit relativement "
				+ "à ce logiciel.</<div></body></html>");

		editor.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		addItem(editor);
	}

	public void setMyContact() {
		MartEditorPane editor2 = new MartEditorPane();
		editor2.setHtml("<html><head></head><body><h3><u>CONTACTS</u></h3>"
				+ "<div><u>Tel:</u> 91 75 56 32 /97 19 20 84</div>"
				+ "<div><u>Whatsapp:</u> 91 75 56 32</div>"
				+ "<div><u>email:</u> martinosearch@gmail.com</div>"
				+ "<div> Adidogomé (LOME)</div></body></html>");

		editor2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		addItem(editor2);
	}

	public void setMyLogo() {
		MartImage logo = new MyFrame().getMyLogo();
		logo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		addItem(logo);
	}

	private void addItem(Component comp) {
		liste.add(comp);

		setLayout(new GridLayout(1, liste.size(), 5, 5));

		for (Component c : liste) {
			/*
			 * Dimension size = getPreferredSize(); c.setPreferredSize(new
			 * Dimension(size.width / liste.size(), size.height - 10));
			 */

			add(c);
			c.revalidate();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		revalidate();
		repaint();
	}
}
