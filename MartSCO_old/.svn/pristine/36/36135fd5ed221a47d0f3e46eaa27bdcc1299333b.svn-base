package matiere;

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

public class NouvelleMatiere extends OptionEditorFrame implements Observer {
	private static NouvelleMatiere instance;
	private DAO dao;

	private MartList<Matiere> matieres;
	private Matiere matiere;

	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();
	private MartButton bSection = new MartButton().getSection();

	AbstractModel model = new MatiereModel();
	AbstractControler controler = new MatiereControler(model);
	private PanChooseMatiere panSearch;
	private AjouterMatiere fr;

	public NouvelleMatiere() {
		this.setTitle("Matière");
		setToolBarVertical();
		setIcone(new FrameIcon().getMatiere());

		initComponent();
	}

	public void initComponent() {
		panSearch = new PanChooseMatiere();
		panSearch.addListener(this);

		barreOutils.add(panSearch);
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bSection);
		barreOutilsV.add(bQuitter);

		addListeners();
		model.addObserver(this);

		load();
		refresh();

	}

	public void update() {
		try {
			Thread.sleep(2000);
			refresh();
			this.showMessage("Succès!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static NouvelleMatiere getInstance() {
		if (instance == null) {
			instance = new NouvelleMatiere();
		}

		return instance;
	}

	public void load() {
		dao = DAOFactory.getDAO(DAO.MATIERE);
	}

	public void refresh() {
		// la Liste des Enseignants existants
		dao.load();
		matieres = dao.getListObt();

		refreshItems();

		int i = 0;
		for (Matiere obj : matieres) {
			OptionItem item = new OptionItem("img_matiere.png", "<div>"
					+ obj.getIntitule()
					+ "</div><div id='explication'> Diminutif: " + obj.getDim()
					+ " type: " + obj.getType() + "</div>");
			item.setInfo(obj);
			item.addListener(this);
			addItem(item);
			// System.out.println(obj.decrisToi().toUpperCase());
			i++;
		}

	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
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
			controler.setData(matiere);
			controler.setAnnee(Constance.getAnnee());

			model.setData(matiere);
			model.setAnnee(Constance.getAnnee());

			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bAjouter) {
			fr = new AjouterMatiere();
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
		fr = new AjouterMatiere();
		fr.loadMatiere(matiere);
		fr.setParent(this);
		fr.setVisible(true);
	}

	private void setSearchResult() {
		// System.out.println("listener: ok!");
		matiere = panSearch.getSelectedItem();

		int i = 0;
		MartList<OptionItem> listeItem = panItem.getListeItem();
		for (OptionItem item : listeItem) {
			if (((Matiere) item.getInfo()).getIntitule().equals(
					matiere.getIntitule())) {
				panItem.setBarPosition(i * 64);
				panItem.setSelectedItem(i);
			}
			i++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		matiere = (Matiere) ((OptionItem) source).getInfo();
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
	public void mousePressed(MouseEvent e) {
		Component source = (Component) e.getSource();
		matiere = (Matiere) ((OptionItem) source).getInfo();
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
}
