package etablissement;

import graphicsModel.FrameIcon;
import graphicsModel.MyFrame;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import interfacePerso.Observer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import progress.Avancer;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class NouveauEts extends OptionEditorFrame implements Observer {

	protected static NouveauEts instance;
	public MartTable tableClasse;
	private static ArrayList<Etablissement> etablissements;
	private static AbstractModel model = new EtsModel();
	private static AbstractControler controler = new EtsControler(model);
	private DAO etsdao;
	private MyFrame fr;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();
	private Etablissement etablissement;

	// constructeur
	public NouveauEts() {
		super();
		this.setTitle("Etablissements");
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getEts());

		initComponent();
	}

	// initialisaton des contenus
	public void initComponent() {
		// pour la barre d'outils
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bQuitter);
		addListeners();

		load();
		refresh();
	}

	// methode qui met ajour
	public void update() {
		// on demande que la table puisse se mettre ajour
		refresh();
		showMessage("Action réussie!");
	}

	// *********************************************************
	// cree une instance
	// permet donc de ne instancier qu'une seul fois (pattener singleton)
	public static NouveauEts getInstance() {
		if (instance == null) {
			instance = new NouveauEts();
		}
		return instance;
	}

	public void load() {
		DAOFactory.getDAO(DAO.ELEVE);
		DAOFactory.getDAO(DAO.CLASSE);
		etsdao = DAOFactory.getDAO(DAO.ETABLISSEMENT);
		model.addObserver(this);
	}

	public void refresh() {
		etsdao.load();
		etablissements = etsdao.getList();
		refreshItems();

		int i = 0;
		for (Etablissement ets : etablissements) {
			OptionItem item = new OptionItem("img_ecole.png", "<div>"
					+ ets.getIntitule() + " " + ets.getNom()
					+ "</div><div id='explication'>" + ets.getContact()
					+ "</div>");
			item.setInfo(ets);
			item.addListener(this);

			addItem(item);

			// System.out.println(obj.decrisToi().toUpperCase());
			i++;
		}

		container.revalidate();

		// System.out.println("SSSSSSSSSSSSSSSSSSS" +
		// tableClasse.getRowCount());
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public MartTable getTableMatiere() {
		return tableClasse;
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		etablissement = (Etablissement) ((OptionItem) source).getInfo();

		if (e.getClickCount() > 1) {
			doModifier();
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		if (source == bAjouter) {
			AjouterEts ajouter = AjouterEts.getInstance();
			ajouter.setObject(new Etablissement());
			ajouter.setParent(this);
			ajouter.setVisible(true);
		}

		if (source == bModifier) {
			doModifier();
		}

		if (source == bSupprimer) {
			controler.setData(etablissement);
			model.setData(etablissement);

			controler.setData(etablissement);
			model.setData(etablissement);

			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bQuitter) {
			close();
		}
	}

	private void doModifier() {
		AjouterEts ajouter = AjouterEts.getInstance();
		ajouter.setObject(etablissement);
		ajouter.setParent(this);

		ajouter.setVisible(true);
	}
}
