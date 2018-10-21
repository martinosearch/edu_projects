package images;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import connection.MartConnection;
import progress.Avancer;
import function.Constance;
import graphicsModel.MartDialog;
import graphicsModel.MartFrame;

public class ShowPicture extends MartDialog {
	private static ShowPicture instance;
	private JPanel panPicture;
	private URL urlPhoto;

	public ShowPicture() {
		setSize(70, 180);

		container = new JPanel(new BorderLayout());
		panPicture = new JPanel(new BorderLayout());
		container.add(panPicture, BorderLayout.CENTER);

		urlPhoto = getURL("img_photo.png");

		getContentPane().add(container, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		File ShFile = new File(Constance.getMyFolder()
				+ "documents\\photos_eleves_" + MartConnection.getDataBase()
				+ "\\A0028.jpg");
		System.out.println(ShFile.exists());
		ShowPicture show = new ShowPicture();
		show.setFile(ShFile);
		show.setVisible(true);
	}

	public void setFile(File f) {
		panPicture.removeAll();
		System.out.println(f.getAbsolutePath() + "Existe: " + f.exists());

		Item item = new Item(new File(""));
		if (f.exists()) {
			item = new Item(f);
			panPicture.add(item, BorderLayout.CENTER);
		} else {
			item = new Item(urlPhoto);
			panPicture.add(item, BorderLayout.CENTER);
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				panPicture.revalidate();
				panPicture.repaint();
				container.revalidate();
				container.repaint();
			}
		});

	}

	class Item extends JPanel {
		private Image img;
		private File file;

		public Item(File f) {
			file = f;
			Toolkit kit = Toolkit.getDefaultToolkit();
			img = kit.getImage(file.getAbsolutePath());

			// file = new File("");
			setPreferredSize(new Dimension(70, 85));
		}

		public Item(URL u) {
			Toolkit kit = Toolkit.getDefaultToolkit();
			img = kit.getImage(u);

			// file = new File("");
			setPreferredSize(new Dimension(70, 85));
		}

		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}

		public File getFile() {
			return file;
		}
	}

	public static ShowPicture getInstance() {
		if (instance == null) {
			instance = new ShowPicture();
		}
		return instance;
	}

	private URL getURL(String str) {
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(Constance.getImageFolder() + str);

		return url;
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
