/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package complements;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author webdev
 */
public class EcranPannel extends JPanel {
	private static final long serialVersionUID = 1671314658637614873L;
	private int inset = 2;
	private Color buttonColor = Color.black.brighter().brighter().brighter()
			.brighter();
	private String separateur = System.getProperty("file.separator");
	private String imgDir = System.getProperty("user.dir") + separateur
			+ "sysImages" + separateur; // REPERTOIRE RACINE DES IMAGES
	private String name;
	private String separ = System.getProperty("file.separator");
	private Image img;
	private Chemin myChemin = new Chemin();
	private String imgPath = new File("").getAbsolutePath() + separateur
			+ "sysImages" + separateur;

	public EcranPannel(String str) {
		this.name = str;
		try {
			img = ImageIO.read(new File(imgPath + "ecr_palmoc2.png").toURL());

		} catch (IOException e) {
			try {
				img = ImageIO.read(new File(myChemin.getMyPath() + separ
						+ "sysimages" + separ + "ecr_palmoc2.png"));
			} catch (IOException k) {
			}
		}
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20,
				Color.cyan, true);
		g2d.setPaint(gp);
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.lightGray);
		g2d.drawString(this.name, this.getWidth() / 2
				- (this.getWidth() / 2 / 4), (this.getHeight() / 2) + 5);

	}
}
