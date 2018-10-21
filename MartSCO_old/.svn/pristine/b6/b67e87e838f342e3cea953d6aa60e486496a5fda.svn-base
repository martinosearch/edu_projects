package progress;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BarreAttente extends JPanel {
	private Image image;
	private int angleCourant = 0;

	public BarreAttente() {
		setPreferredSize(new Dimension(200, 200));
		setBorder(BorderFactory.createEtchedBorder());
	}

	@Override
	public void paintComponent(Graphics g) {
		if (image != null) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			AffineTransform origine = g2d.getTransform();
			AffineTransform nouvelle = (AffineTransform) (origine.clone());
			nouvelle.rotate(Math.toRadians(angleCourant), getWidth() / 2,
					getHeight() / 2);
			g2d.setTransform(nouvelle);
			g2d.drawImage(image, (getWidth() - image.getWidth(this)) / 2,
					(getHeight() - image.getHeight(this)) / 2, this);
			g2d.setTransform(origine);
		}
	}

	public void tourner(int angle) {
		angleCourant -= angle;

		if (angleCourant <= 0) {
			angleCourant += 360;
		}
	}
}
