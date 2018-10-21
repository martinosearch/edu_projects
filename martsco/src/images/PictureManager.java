package images;

import eleve.AjouterEleve;
import eleve.Eleve;
import function.Constance;
import graphicsModel.MartFrame;

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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import connection.MartConnection;
import progress.Avancer;

public class PictureManager extends MartFrame {

	private JPanel panPicture;
	private String code = "A0000";
	private Eleve eleve = new Eleve();

	public PictureManager() {
		setSize(MEDIUM_FRAME);
		setLocation(INTERNAL_FRAME_LOCATION);
		container = new JPanel(new BorderLayout());
		panPicture = new JPanel();

		JScrollPane scroll = new JScrollPane(panPicture);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		container.add(scroll, BorderLayout.CENTER);
		getContentPane().add(container, BorderLayout.CENTER);

		setVisible(true);
	}

	public static void main(String[] args) {
		PictureManager p = new PictureManager();
		p.setPhotosEleve();
		/*
		 * File f = new File(Constance.getMyFolder() +
		 * "documents\\photos_eleves_" + Constance.getDataBase() + "\\" +
		 * "A0001" + ".jpg");
		 * 
		 * System.out.println((f.getName()).endsWith(".png"));
		 */
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setPhotosEleve() {
		File file = new File(Constance.getTempDir() + "photos");
		File[] files = file.listFiles();
		panPicture.setPreferredSize(new Dimension(MEDIUM_FRAME.width - 30,
				(int) ((files.length) * 0.2 * 70)));
		try {
			for (File f : files) {
				System.out.println(f.getAbsolutePath());

				Item item = new Item(f);
				item.addMouseListener(this);
				panPicture.add(item);
				panPicture.revalidate();

				// Thread.sleep();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Item extends JPanel {
		private Image img;
		private File file;

		public Item(File f) {
			file = f;
			Toolkit kit = Toolkit.getDefaultToolkit();
			img = kit.getImage(file.getAbsolutePath());
			setPreferredSize(new Dimension(70, 85));
		}

		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}

		public File getFile() {
			return file;
		}
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
	public void mousePressed(MouseEvent e) {
		System.out.println("Je réponds");
		if (e.getClickCount() > 1) {
			int rep = JOptionPane.showConfirmDialog(null,
					"Voulez- vous choisir cette photo?", "CONFIRMATION",
					JOptionPane.YES_NO_OPTION);

			if (rep == JOptionPane.YES_OPTION) {
				File fOld = ((Item) e.getSource()).getFile();

				try {
					BufferedInputStream bis = new BufferedInputStream(
							new FileInputStream(fOld));

					File fNew = null;

					if (fOld.getName().endsWith(".png")) {
						fNew = new File(Constance.getMyFolder()
								+ "documents\\photos_eleves_"
								+ MartConnection.getDataBase() + "\\"
								+ eleve.getCodeInfo() + ".png");

					} else if (fOld.getName().endsWith(".jpg")) {
						fNew = new File(Constance.getMyFolder()
								+ "documents\\photos_eleves_"
								+ MartConnection.getDataBase() + "\\"
								+ eleve.getCodeInfo() + ".jpg");

					}

					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(fNew));

					byte[] buff = new byte[8];

					while (bis.read(buff) != -1) {
						bos.write(buff);
					}

					bis.close();
					bos.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				AjouterEleve.getInstance().show.setFile(fOld);
				close();
			}
		}
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

	public void setEleve(Eleve elv) {
		eleve = elv;
	}
}
