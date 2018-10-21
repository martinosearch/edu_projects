package graphicsModel;

//cette class met une image ds un JPanel

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.swing.JPanel;

public class MartImage extends JPanel {
	Image img = null;
	Image imgFiltre = null;

	public MartImage() {
	}

	public MartImage(URL url) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(url);
	}

	public MartImage(File p) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		img = kit.getImage(p.getAbsolutePath());
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
