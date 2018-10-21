package classe;

import eleve.Eleve;
import graphicsModel.FrameIcon;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import interfacePerso.Observer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import progress.Avancer;
import tableComponent.MartTable;
import abstractObject.AbstractControler;

import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class NouvelleClasse extends OptionEditorFrame implements Observer {
	private Dimension dim1 = new Dimension(100, 30);
	private ClasseModel model = new ClasseModel();
	private AbstractControler controler = new ClasseControler(model);
	protected static NouvelleClasse instance;
	public MartTable tableClasse;

	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Font police2 = new Font("courrier new", Font.BOLD, 14);

	private DAO clsdao, elvdao, matdao;
	int num = 0;
	int max;
	String ins = "";
	String cl = "";
	private MartList<Eleve> eleves;
	private MartList<Classe> classes;
	private AjouterClasse fr;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();

	private Classe classe;

	// constructeur
	public NouvelleClasse() {
		this.setTitle("Classe");
		setToolBarVertical();
		setIcone(new FrameIcon().getClasse());

		// le controleur
		model.addObserver(this);

		initComponent();

		this.setVisible(true);
	}

	// initialisaton des contenus
	public void initComponent() {
		// // pour la barre d'outils
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bQuitter);

		addListeners();
		load();
		refresh();
	}

	// methode qui met ajour
	public void update() {
		// on demande que la table puisse se mettre ajour
		this.showMessage("Succès !");
		try {
			Thread.sleep(2000);
			refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// *********************************************************
	// cree une instance
	// permet donc de ne instancier qu'une seul fois (pattener singleton)
	public static NouvelleClasse getInstance() {
		if (instance == null) {
			instance = new NouvelleClasse();
		}

		return instance;
	}

	public void load() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);

		clsdao.load();
	}

	public void refresh() {
		clsdao.load();
		classes = clsdao.getList();

		refreshItems();

		int i = 0;
		for (Classe obj : classes) {
			OptionItem item = new OptionItem("img_classe.png", "<div>"
					+ obj.getIntitule()
					+ "</div><div id='explication'> Niveau: " + obj.getNiveau()
					+ "</div>");
			item.setInfo(obj);
			item.addListener(this);
			addItem(item);
			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAjouter) {
			fr = new AjouterClasse();
			fr.setParent(this);
			fr.setVisible(true);
		}

		if (source == bSupprimer) {
			controler.setData(classe);
			model.setData(classe);
			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bQuitter) {
			close();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		classe = (Classe) ((OptionItem) source).getInfo();
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
		classe = (Classe) ((OptionItem) source).getInfo();
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

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}
}
