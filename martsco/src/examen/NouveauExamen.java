package examen;

import graphicsModel.FrameIcon;
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
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class NouveauExamen extends OptionEditorFrame implements Observer {
	protected static NouveauExamen instance;
	private static ArrayList<Examen> exams;
	private static AbstractModel model = new ExamModel();
	private static AbstractControler controler = new ExamControler(model);
	private DAO examdao;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private Examen exam;

	// constructeur
	public NouveauExamen() {
		this.setTitle("Examen");
		setToolBarVertical();
		setIcone(new FrameIcon().getExamen());
		model.addObserver(this);

		initComponent();
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
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

	// methode qui met ajour
	public void update() {
		// on demande que la table puisse se mettre ajour
		refresh();
		showMessage("Action réussie!");
	}

	// *********************************************************
	// cree une instance
	// permet donc de ne instancier qu'une seul fois (pattener singleton)
	public static NouveauExamen getInstance() {
		if (instance == null) {
			instance = new NouveauExamen();
		}
		return instance;
	}

	public void load() {
		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		examdao.load();
	}

	public void refresh() {
		examdao.load();
		exams = examdao.getList();

		refreshItems();

		new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 0;
				for (Examen examen : exams) {
					try {
						OptionItem item = new OptionItem(
								"img_rapport.png",
								"<div>"
										+ examen.getIntitule()
										+ "</div><div id='explication'>Centre: "
										+ examen.getCentre()
										+ "</div>"
										+ "</div><div id='explication'>Niveau: "
										+ examen.getNiveau() + "</div>");
						item.setInfo(examen);
						item.addListener(NouveauExamen.this);

						addItem(item);

						// System.out.println(obj.decrisToi().toUpperCase());
						i++;

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// System.out.println("SSSSSSSSSSSSSSSSSSS" +
		// tableClasse.getRowCount());
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		exam = (Examen) ((OptionItem) source).getInfo();
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
			AjouterExamen ajouter = AjouterExamen.getInstance();
			ajouter.setParent(this);
			ajouter.setVisible(true);
		}

		if (source == bSupprimer) {
			controler.setData(exam);
			model.setData(exam);

			controler.setData(exam);
			model.setData(exam);

			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bQuitter) {
			close();
		}
	}

}
