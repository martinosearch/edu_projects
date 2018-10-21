package help;

import editeur.MartEditorPane;
import editeur.MartStyle;
import function.XmlManager;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jdom2.Element;

import componentFactory.MartButton;

import progress.Avancer;

public class Help extends MartFrame {
	public static final int USER_HELP = 0;
	public static final int EDITOR_INFO = 1;
	public static final int CUSTOM_INFO = 2;
	MartEditorPane panel = new MartEditorPane(MartStyle.DOCUMENTS);
	private BufferedReader dataReader;
	private JPanel container;
	private int currentTheme = 0;
	private JPanel panBut;
	private List themes;
	private Iterator iterator;
	private int type;
	private MartButton bPrecedent = new MartButton().getPrecedent();
	private MartButton bSuivant = new MartButton().getSuivant();
	private static Help instance;

	public Help() {
		this.setSize(MEDIUM_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		this.setResizable(false);
		setToolBar();
		setIcone(new FrameIcon().getHelp());

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(new JScrollPane(panel), BorderLayout.CENTER);

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	public static Help getInstance() {
		if (instance == null) {
			instance = new Help();
		}
		return instance;
	}

	public static void main(String[] args) {
		Help help = getInstance();
		help.getType(help.USER_HELP);
		help.setVisible(true);
	}

	public void getType(int typ) {
		type = typ;
		load();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == bPrecedent) {
			if (currentTheme > 0) {
				currentTheme--;
				refresh();
			}
		}

		if (source == bSuivant) {
			if (currentTheme < themes.size() - 1) {
				currentTheme++;
				refresh();
			}
		}
	}

	@Override
	public void load() {
		this.setTitle("Que savez- vous sur MartSCO?");

		XmlManager manager = new XmlManager(new File("help.martsco"));
		Element racine = manager.getRacine();

		if (type == USER_HELP) {
			themes = racine.getChildren("rubrique");

			System.out.println("guide");
		}

		if (type == EDITOR_INFO) {
			themes = racine.getChildren("editeur");
		}

		System.out.println("taille: " + themes.size());

		refresh();

		if (themes.size() > 1) {
			barreOutils.add(bSuivant);
			barreOutils.add(bPrecedent);
			addListeners();
		}
	}

	@Override
	public void refresh() {
		Element rubliqueActuelle = (Element) themes.get(currentTheme);

		String titre = rubliqueActuelle.getChild("intitule").getText();
		String contenu = rubliqueActuelle.getChild("contenu").getText();
		panel.setHtml("<html><head><meta><style>" + getStyle()
				+ "</style></meta></head><body><p id=titre>" + titre
				+ "</p><p id='contenu'>" + contenu + "</p></body></html>");
		panel.revalidate();
	}

	private String getStyle() {
		String style = "#titre{font-size:40pt;"
				+ "font-family:'bookman old style';font-weight:'bold';}";

		style += "#contenu{font-size:16pt;" + "font-family:'arial';}";

		style += "#signature{font-size:16pt;"
				+ "font-family:'arial';font-weight:'bold';text-align:center;}";
		return style;
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
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
