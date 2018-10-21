package graphicsModel;

import function.Constance;
import interfacePerso.Afficher;
import interfacePerso.Progressible;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import componentFactory.MartButton;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public abstract class MartFrame extends JFrame implements Progressible,
		ActionListener, MouseListener, WindowListener, Afficher, FocusListener,
		KeyListener {

	public static final Dimension SMALL_FRAME = new Dimension(500, 420);
	public static final Dimension MEDIUM_FRAME = new Dimension(750, 585);
	public static final Dimension MEDIUM_PLUS_FRAME = new Dimension(1000, 585);
	public static final Dimension LARGE_FRAME = new Dimension(900, 800);
	public static final Point INTERNAL_FRAME_LOCATION = new Point(5, 135);
	public static final Color COLOR_TABLE = new Color(236, 214, 191);
	public static final Point SMALL_FRAME_CHOOSER_LOCATION = new Point(510, 135);
	public static final Dimension TOOL_BUTTON_SIZE = new Dimension(100, 80);
	public static final Dimension OPTION_BUTTON_SIZE = new Dimension(80, 80);
	public static final Point MEDIUM_FRAME_CHOOSER_LOCATION = new Point(760,
			135);
	public static Color MENU_COLOR = new Color(50, 50, 50);

	public JPanel panEtat, container;

	// Composant
	public JToolBar barreOutils = new JToolBar();
	public VerticalToolBar barreOutilsV = new VerticalToolBar();
	protected Image icone;
	protected JPanel panToolBar;
	protected Font fontMenu = new Font("times new roman", Font.BOLD, 12);
	protected JMenuBar menuBar;
	protected Container parent;

	public MartFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		icone = kit.getImage(getClass().getClassLoader().getResource(
				Constance.getImageFolder() + "img_iconemc.png"));

		setIconImage(icone);
		menuBar = new JMenuBar();
		menuBar.setFont(fontMenu);
		menuBar.setBackground(MENU_COLOR);

		panEtat = new JPanel();
		panEtat.setLayout(new BorderLayout());

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panEtat, BorderLayout.SOUTH);
		addWindowListener(this);
	}

	public void setToolBar() {
		panToolBar = new JPanel();
		panToolBar.setLayout(new BorderLayout());

		barreOutils.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panToolBar.add(barreOutils, BorderLayout.EAST);

		getContentPane().add(panToolBar, BorderLayout.NORTH);
		repaint();
	}

	public void setToolBarVertical() {
		getContentPane().add(barreOutilsV, BorderLayout.EAST);
		repaint();
	}

	public void setIcone(JPanel pan) {
		panToolBar.add(pan, BorderLayout.WEST);
		// panToolBar.setBackground(Color.WHITE);
		// barreOutils.setBackground(Color.WHITE);

	}

	public void setPub() {
		JLabel lbPub = new JLabel(Constance.COPY_RIGHT);
		lbPub.setFont(new Font("Times new Romam", Font.ITALIC, 16));
		lbPub.setForeground(Color.DARK_GRAY);

		panEtat.setLayout(new BorderLayout());
		panEtat.add(lbPub, BorderLayout.EAST);
		panEtat.setBackground(Color.PINK);
		lbPub.setForeground(Color.BLACK);
		panEtat.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		panEtat.revalidate();

		this.revalidate();
	}

	public JPanel getPanEtat() {
		return panEtat;
	}

	public MartImage getImageMartsco() {
		MartImage image = new MartImage(getClass().getClassLoader()
				.getResource(Constance.getImageFolder() + "martsco.png"));

		return image;
	}

	public MartImage getMyLogo() {
		MartImage image = new MartImage(getClass().getClassLoader()
				.getResource(Constance.getImageFolder() + "martino.png"));

		return image;
	}

	public void showMessage(String msg) {
		final String message = msg;

		new Thread(new Runnable() {
			JLabel lb = new JLabel();

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

	// pour definir cette classe ecouteur de tous les bouton
	public void addListeners() {
		Component[] liste1 = barreOutils.getComponents();
		Component[] liste2 = barreOutilsV.getComponents();
		System.out.println("JE suiq" + liste2.length);

		for (Component comp : liste1) {
			if (comp instanceof AbstractButton) {
				((AbstractButton) comp).addActionListener(this);
			}
		}

		for (Component comp : liste2) {
			if (comp instanceof AbstractButton) {
				((AbstractButton) comp).addActionListener(this);
			}
		}
	}

	public void close() {
		System.out.println("Demande Fermeture");
		dispose();
	}

	public void setParent(Container p) {
		parent = p;
	}

	public Container getParent() {
		return parent;
	}
}
