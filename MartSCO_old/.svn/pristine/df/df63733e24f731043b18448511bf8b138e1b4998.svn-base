package editeur;

import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.MartPanel;
import interfacePerso.Observable;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import componentFactory.MartButton;
import progress.Avancer;

public class TabbedPreview extends MartFrame implements ChangeListener,
		Observable {
	String html = "";
	int nbreBull;

	private MartList<EditorPanePrinter> panes;
	private JTabbedPane jtp;

	private MartButton bImprimer = new MartButton().getImprimer();
	private MartButton bExport = new MartButton().getPDF();
	private MartList<Observer> listObserver = new MartList<Observer>();

	public TabbedPreview() {
		setTitle("APERCU AVANT IMPRESSION");
		setSize(700, 800);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setToolBar();
		setIcone(new FrameIcon().getPrintView());

		Paper p = new Paper();
		p.setSize(595, 842);

		initComponent();
		getContentPane().add(container, BorderLayout.CENTER);
		this.setVisible(true);
	}

	protected void initComponent() {
		// mise sur la barre
		barreOutils.add(bExport);
		barreOutils.add(bImprimer);

		addListeners();

		container = new JPanel();
		container.setLayout(new BorderLayout());

		bImprimer.setFocusable(false);

		load();

		new JPanel();

		jtp = new JTabbedPane();
		jtp.addChangeListener(this);

		container.add(jtp, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		TabbedPreview prev = new TabbedPreview();
		MartEditorPane ed = new MartEditorPane();
		prev.addPanel("CLASSES", ed);
		MartEditorPane ed2 = new MartEditorPane();
		prev.addPanel("ENSEIGNANTS", ed2);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bImprimer) {
			EditorPanePrinter pan = panes.get(jtp.getSelectedIndex());
			// lancement de l'impression
			pan.print();
		}

		if (source == bExport) {
			EditorPanePrinter pan = panes.get(jtp.getSelectedIndex());
			// lancement de l'impression
			pan.exportPDF();
		}
	}

	public void addPanel(String title, MartEditorPane pan) {
		Paper p = new Paper();
		p.setSize(595, 842);

		EditorPanePrinter panel = new EditorPanePrinter(pan, p, new Insets(10,
				15, 10, 15));
		panes.add(panel);

		panel.setBackground(Color.DARK_GRAY);
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jtp.add(title, scroll);

		panel.revalidate();
		jtp.revalidate();
		container.revalidate();
		scroll.revalidate();
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
		notifyObserver();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		notifyObserver();
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
		panes = new MartList<EditorPanePrinter>();
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

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		listObserver = new MartList<Observer>();
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : listObserver) {
			obs.update();
		}
	}

}
