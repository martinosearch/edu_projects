package graphicsModel;

import images.PictureFinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import agent.Agent;
import editeur.MartEditorPane;
import eleve.Eleve;
import function.Constance;

public class InfoPanel extends JPanel implements MouseListener {
	private static final Color TEXT_BACK_COLOR = new Color(255, 164, 157);
	private MartEditorPane panText;
	private Color BACK_COLOR = Color.WHITE;
	private JPanel panPhoto;
	private MartImage panImage;
	private Font police2 = new Font("courrier new", Font.BOLD, 16);

	public InfoPanel() {
		setPreferredSize(new Dimension(630, 160));
		panPhoto = new JPanel();
		panPhoto.setLayout(new GridLayout());
		panPhoto.setPreferredSize(new Dimension(120, 160));
		panPhoto.setBackground(BACK_COLOR);
		panPhoto.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		panImage = new MartImage(getURL("img_photo.png"));

		panPhoto.add(panImage);
		panPhoto.addMouseListener(this);

		panText = new MartEditorPane();
		panText.setPreferredSize(new Dimension(500, 160));
		panText.setFont(police2);
		panText.setBackground(TEXT_BACK_COLOR);

		add(panText, BorderLayout.CENTER);
		add(panPhoto, BorderLayout.EAST);
	}

	public void setEleve(Eleve eleve) {

	}

	public void setAgent(Agent agent) {
		// affichage de la photo
		String text = "<html><head></head><body><div>Nom: " + agent.getNom()
				+ "</div>" + "<div>Prénoms: " + agent.getPrenom() + "</div>"
				+ "<div>Sexe: " + agent.getSexe() + "</div>" + "<div>N° Mle: "
				+ agent.getCodeInfo() + "</div></body>" + "</html>";

		panText.setHtml(text);

		// définition de la photo
		File p = new PictureFinder().getPhotoAgent(agent.getCodeInfo());
		MartImage photo = new MartImage(p);

		panPhoto.removeAll();
		panPhoto.add(photo);
		panPhoto.revalidate();

	}

	private URL getURL(String str) {
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(Constance.getImageFolder() + str);

		return url;
	}

	public void reset() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				System.out.println("ok");
				panPhoto.removeAll();
				panText.setHtml("");

				panPhoto.revalidate();
				panText.revalidate();

				repaint();
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("J'ecoute");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
