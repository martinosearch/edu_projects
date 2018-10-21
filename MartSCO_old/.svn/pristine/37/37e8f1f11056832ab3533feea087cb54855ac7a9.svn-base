package classe;

import eleve.AjouterEleve;
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

import javax.imageio.plugins.bmp.BMPImageWriteParam;
import javax.swing.JPanel;

import componentFactory.MartButton;
import progress.Avancer;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class NouveauNiveau extends OptionEditorFrame implements Observer {
	private static NouveauNiveau instance;
	private DAO dao;

	private MartList<Niveau> liste;
	private Niveau niveau;

	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();

	AbstractModel model = new NiveauModel();
	AbstractControler controler = new NiveauControler(model);
	private PanChooseNiveau panSearch;
	private AjouterNiveau fr;

	public NouveauNiveau() {
		this.setTitle("Niveau d'Enseignement");
		setToolBarVertical();
		setIcone(new FrameIcon().getNiveau());

		initComponent();
	}

	public void initComponent() {
		panSearch = new PanChooseNiveau();
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
			Thread.sleep(2000);
			refresh();
			this.showMessage("Succès!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static NouveauNiveau getInstance() {
		if (instance == null) {
			instance = new NouveauNiveau();
		}

		return instance;
	}

	public void load() {
		dao = DAOFactory.getDAO(DAO.NIVEAU);
	}

	public void refresh() {
		// la Liste des Enseignants existants
		dao.load();
		liste = dao.getListObt();
		refreshItems();

		int i = 0;
		for (Niveau obj : liste) {
			OptionItem item = new OptionItem("img_niveau.png", "<div>"
					+ obj.getIntitule()
					+ "</div><div id='explication'> Type d'Enseignement: "
					+ obj.getTypeEns() + "</div>");
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
			controler.setData(niveau);
			model.setData(niveau);

			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bAjouter) {
			fr = new AjouterNiveau();
			fr.setParent(this);
			fr.setVisible(true);
		}

		if (source == bModifier) {
			fr = new AjouterNiveau();
			fr.loadNiveau(niveau);
			fr.setParent(this);
			fr.setVisible(true);
		}

		if (source == panSearch.getValider()) {
			setSearchResult();
		}

		if (source == bQuitter) {
			close();
		}
	}

	private void setSearchResult() {
		// System.out.println("listener: ok!");
		niveau = panSearch.getSelectedItem();

		int i = 0;
		MartList<OptionItem> listeItem = panItem.getListeItem();
		for (OptionItem item : listeItem) {
			if (((Niveau) item.getInfo()).getIntitule().equals(
					niveau.getIntitule())) {
				panItem.setBarPosition(i * 64);
				panItem.setSelectedItem(i);
			}
			i++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		niveau = (Niveau) ((OptionItem) source).getInfo();
		if (e.getClickCount() > 1) {
			doModifier();
		}
	}

	private void doModifier() {
		fr = new AjouterNiveau();
		fr.loadNiveau(niveau);
		fr.setParent(this);
		fr.setVisible(true);
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
		niveau = (Niveau) ((OptionItem) source).getInfo();
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
