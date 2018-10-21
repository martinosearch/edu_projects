package annee;

import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.MyFrame;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import graphicsModel.TablePanel;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import componentFactory.MartButton;
import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import connection.DAO;
import connection.DAOFactory;

public class NouvelleAnnee extends OptionEditorFrame implements Observer {
	protected AnneeModel model = new AnneeModel();
	protected AbstractControler controler = new AnneeControler(model);
	protected static NouvelleAnnee instance;

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);

	DAO clsdao, elvdao, matdao, andao;
	int num = 0;
	int max;
	String ins = "";
	String cl = "";
	private AjouterAnnee fr;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	protected MartButton bValider = new MartButton().getValider();
	private MartList<Annee> annees;
	protected Annee annee;

	// constructeur
	public NouvelleAnnee() {
		this.setTitle("Année- scolaire");
		setToolBarVertical();
		model.addObserver(this);

		setIcone(new FrameIcon().getAnnee());
		initComponent();
	}

	// initialisaton des contenus
	public void initComponent() {
		// pour la barre d'outils
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bQuitter);

		addListeners();

		load();
		refresh();
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	// methode qui met ajour
	public void update() {
		this.showMessage("Succès");
		System.out.println("Je suis mis à jour");

		try {
			refresh();
			revalidate();
			container.repaint();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// *********************************************************
	// cree une instance
	// permet donc de ne instancier qu'une seul fois (pattener singleton)
	public static NouvelleAnnee getInstance() {
		if (instance == null) {
			instance = new NouvelleAnnee();
		}
		return instance;
	}

	public void load() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		andao = DAOFactory.getDAO(DAO.ANNEE);
	}

	public void refresh() {
		// la Liste des classe
		andao = DAOFactory.getDAO(DAO.ANNEE);
		andao.load();

		annees = andao.getListObt();

		refreshItems();
		// System.out.println(annees.size());

		int i = 0;
		for (Annee an : annees) {
			OptionItem item = new OptionItem("img_annee.png", an.getIntitule());
			item.setInfo(an);
			item.addListener(this);
			addItem(item);
			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAjouter) {
			fr = new AjouterAnnee();
			fr.setParent(this);
			fr.setVisible(true);
		}

		if (source == bSupprimer) {
			controler.setData(annee);
			model.setData(annee);
			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bQuitter) {
			close();
		}

	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		annee = (Annee) ((OptionItem) source).getInfo();
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
		Component source = (Component) e.getSource();
		annee = (Annee) ((OptionItem) source).getInfo();
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
		try {
			fr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {

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
	public void keyPressed(KeyEvent e) {

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
