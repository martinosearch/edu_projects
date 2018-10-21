package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import function.Constance;
import interfacePerso.Afficher;
import interfacePerso.Progressible;

public abstract class MartDialog extends JDialog
		implements Progressible, ActionListener, MouseListener, WindowListener, Afficher, FocusListener, KeyListener {
	protected JPanel panEtat, container;
	// Composant
	protected JToolBar barreOutils = new JToolBar();
	private Image icone;
	private Container parent;

	public MartDialog() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		icone = kit.getImage(getClass().getClassLoader().getResource(Constance.getImageFolder() + "img_iconemc.png"));

		setIconImage(icone);

		panEtat = new JPanel();
		panEtat.setLayout(new BorderLayout());

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panEtat, BorderLayout.SOUTH);
	}

	public void setToolBar() {
		this.getContentPane().add(barreOutils, BorderLayout.NORTH);
		this.repaint();
	}

	public void setPub() {
		JLabel lbPub = new JLabel(Constance.COPY_RIGHT);
		lbPub.setFont(new Font("Times new Romam", Font.ITALIC, 16));
		lbPub.setForeground(Color.DARK_GRAY);
		panEtat.add(lbPub, BorderLayout.EAST);
		panEtat.setBackground(Color.PINK);
		lbPub.setForeground(Color.BLACK);
		panEtat.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	}

	@Override
	public JPanel getPanEtat() {
		return this.panEtat;
	}

	public void showMessage(String msg) {
		final String message = msg;

		new Thread(new Runnable() {
			JLabel lb = new JLabel();

			@Override
			public void run() {
				lb.setForeground(Color.BLUE);
				lb.setText(message);

				panEtat.add(lb, BorderLayout.WEST);

				for (int i = 0; i < 6; i++) {
					if (i % 2 == 0) {
						lb.setText(message);
						System.out.println("Exécuter, Info à i=" + i);
						panEtat.revalidate();
					} else {
						lb.setText("");
					}
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}// fin show message

	public void setParent(Container p) {
		parent = p;
	}

	@Override
	public Container getParent() {
		return parent;
	}

	public void close() {
		System.out.println("Demande Fermeture");
		dispose();
	}
}
