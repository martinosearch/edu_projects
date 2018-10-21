package eleve;

import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import graphicsModel.TablePanel;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import componentFactory.MartButton;
import progress.Avancer;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class NouveauEleve extends OptionEditorFrame implements Observer {
	private static NouveauEleve instance;
	private DAO dao;

	private MartList<Eleve> eleves;
	private Eleve eleve;

	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();

	AbstractModel model = new EleveModel();
	AbstractControler controler = new EleveControler(model);
	private PanChooseEleve panSearch;
	private AjouterEleve fr;

	public NouveauEleve() {
		super();
		this.setTitle("Eleve");
		setToolBarVertical();
		setIcone(new FrameIcon().getEleve());
		setVisible(true);

		initComponent();
	}

	public static void main(String[] args) {
		getInstance();
	}

	public void initComponent() {
		panSearch = new PanChooseEleve();
		panSearch.addListener(this);

		barreOutils.add(panSearch);

		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bQuitter);

		addListeners();
		model.addObserver(this);

		load();
		refresh();
	}

	public void update() {
		try {
			Thread.sleep(1000);

			refresh();

			this.showMessage("Succès!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static NouveauEleve getInstance() {
		if (instance == null) {
			instance = new NouveauEleve();
		}

		return instance;
	}

	public void load() {
		dao = DAOFactory.getDAO(DAO.ELEVE);
	}

	public void refresh() {
		// la Liste des Enseignants existants
		dao.load();
		eleves = new MartList<Eleve>();
		eleves = dao.getListObt();

		refreshItems();

		int i = 0;
		for (Eleve obj : eleves) {
			OptionItem item = new OptionItem("img_eleve.png", "<div>"
					+ obj.decrisToi()
					+ "</div><div id='explication'>N° Ordre: " + (i + 1)
					+ " Sexe: " + obj.getSexe() + " Code: " + obj.getCodeInfo()
					+ "</div>");
			item.setInfo(obj);
			item.addListener(this);
			addItem(item);

			// System.out.println(obj.decrisToi().toUpperCase());
			i++;
		}

		// mise ajour de pansearch
		panSearch.setListe(eleves);
		panSearch.addListener(this);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSupprimer) {
			controler.setData(eleve);
			controler.setAnnee(Constance.getAnnee());
			controler.setClasse(eleve.getClasseAnnee(Constance.getAnnee()));

			model.setData(eleve);
			model.setAnnee(Constance.getAnnee());
			model.setClasse(eleve.getClasseAnnee(Constance.getAnnee()));

			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bAjouter) {
			fr = AjouterEleve.getInstance();
			fr.reset();
			fr.setParent(this);
			fr.setParent(this);
			fr.setVisible(true);
		}

		if (source == bModifier) {
			doModifier();
		}

		if (source == panSearch.getValider()) {
			setSearchResult();
		}

		if (source == bQuitter) {
			close();
		}
	}

	private void doModifier() {
		fr = AjouterEleve.getInstance();
		fr.loadEleve(eleve);
		fr.setParent(this);
		fr.setVisible(true);
	}

	private void setSearchResult() {
		// System.out.println("listener: ok!");
		eleve = panSearch.getSelectedItem();

		int i = 0;
		MartList<OptionItem> listeItem = panItem.getListeItem();
		for (OptionItem item : listeItem) {
			if (((Eleve) item.getInfo()).getCodeInfo().equals(
					eleve.getCodeInfo())) {
				panItem.setBarPosition(i * 64);
				panItem.setSelectedItem(i);
			}
			i++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		eleve = (Eleve) ((OptionItem) source).getInfo();
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
			panItem.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}

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
	public void keyPressed(KeyEvent e) {
		Component source = (Component) e.getSource();
		System.out.println("J'écoute");
		if (e.getKeyCode() == 10) {
			if (source == panSearch.getComponent()) {
				if (panSearch.itemExists()) {
					setSearchResult();
				}
			}
		}
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
