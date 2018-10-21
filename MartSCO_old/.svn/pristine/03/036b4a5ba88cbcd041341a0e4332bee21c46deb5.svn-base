package agent;

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

public class NouveauAgent extends OptionEditorFrame implements Observer {
	private static NouveauAgent instance;
	private DAO dao;

	private MartList<Agent> agents;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();

	AbstractModel model = new AgentModel();
	AbstractControler controler = new AgentControler(model);
	private PanChooseAgent panSearch;
	private Agent agent;
	private AjouterAgent fr;

	public NouveauAgent() {
		this.setTitle("Enseignant");

		setToolBarVertical();
		setIcone(new FrameIcon().getAgent());

		initComponent();
	}

	public void initComponent() {
		panSearch = new PanChooseAgent();
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

	public static NouveauAgent getInstance() {
		if (instance == null) {
			instance = new NouveauAgent();
		}

		return instance;
	}

	public void load() {
		dao = DAOFactory.getDAO(DAO.AGENT);
	}

	public void refresh() {
		// la Liste des Enseignants existants
		dao.load();
		agents = dao.getListObt();
		refreshItems();

		int i = 0;
		for (Agent obj : agents) {
			OptionItem item = new OptionItem("img_agent.png", "<div>"
					+ obj.decrisToi() + "</div><div id='explication'> Sexe: "
					+ obj.getSexe() + " Code: " + obj.getCodeInfo() + "</div>");
			item.setInfo(obj);
			item.addListener(this);
			addItem(item);
			System.out.println(obj.decrisToi().toUpperCase());
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
			controler.setData(agent);
			model.setData(agent);
			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bAjouter) {
			fr = AjouterAgent.getInstance();
			fr.reset();
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
		fr = AjouterAgent.getInstance();
		fr.loadAgent(agent);
		fr.setParent(this);
		fr.setVisible(true);
	}

	private void setSearchResult() {
		// System.out.println("listener: ok!");
		agent = panSearch.getSelectedItem();

		int i = 0;
		MartList<OptionItem> listeItem = panItem.getListeItem();
		for (OptionItem item : listeItem) {
			if (((Agent) item.getInfo()).getCodeInfo().equals(
					agent.getCodeInfo())) {
				panItem.setBarPosition(i * 60);
				panItem.setSelectedItem(i);
			}
			i++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		agent = (Agent) ((OptionItem) source).getInfo();
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
		agent = (Agent) ((OptionItem) source).getInfo();
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
