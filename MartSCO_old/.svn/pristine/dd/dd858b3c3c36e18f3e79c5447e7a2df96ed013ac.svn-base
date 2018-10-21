package candidat;

import eleve.Eleve;
import eleve.EleveClasse;
import eleve.PanChooseEleve;
import examen.ChooserExam;
import graphicsModel.FrameIcon;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import progress.Avancer;
import rapportExamen.ListeSalle;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class NouveauCdt extends OptionEditorFrame implements Observer {
	private static NouveauCdt instance;
	private AbstractModel model;
	private AbstractControler controler;

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);

	private DAO cdtExamDao, cdtDao;
	private String examen;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bModifier = new MartButton().getModifier();
	private MartButton bImprimer = new MartButton().getImprimer();
	private EleveClasse eleve;
	private PanChooseEleve panSearch;
	private MartList<EleveClasse> eleves;
	private Eleve superEleve;

	public NouveauCdt() {
		this.setTitle("Renseignements Elève");
		setToolBarVertical();
		setIcone(new FrameIcon().getCandidat());

		// le controleur
		model = new CdtModel();
		model.addObserver(this);
		controler = new CdtControler(model);

		initComponent();
	}

	public static void main(String[] args) {
		NouveauCdt n = new NouveauCdt();
		n.setVisible(true);
		n.setExamen("BEPC- BLANC 3ème NOVEMBRE 2017");
		n.load();
	}

	public void setExamen(String exam) {
		examen = exam;
	}

	public void initComponent() {
		panSearch = new PanChooseEleve();
		panSearch.addListener(this);

		barreOutils.add(panSearch);

		// pour la barre d'outils
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bImprimer);
		barreOutilsV.add(bQuitter);

		addListeners();

	}

	@Override
	public void update() {
		System.out
				.println("====================================================");
		refresh();
	}

	public void load() {
		cdtDao = DAOFactory.getDAO(DAO.CANDIDAT);
		cdtExamDao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		cdtDao.load();

		refresh();
	}

	public void refresh() {
		// la Liste des élèves existants
		cdtExamDao.loadExam(examen);
		eleves = cdtExamDao.getListObt();

		refreshItems();

		new Thread(new Runnable() {
			public void run() {
				int i = 0;
				for (EleveClasse obj : eleves) {
					i++;
					String explic = "<div>" + i + "- " + obj.decrisToi()
							+ "</div><div id='explication'> Sexe: "
							+ obj.getSexe() + " Code: " + obj.getCodeInfo()
							+ " Num. Table: " + obj.getNumTable();

					if (obj.getEts() != null) {
						explic += " Ets: " + obj.getEts();
					}

					explic += "</div>";

					OptionItem item = new OptionItem("img_eleve.png", explic);

					item.setInfo(obj);
					item.addListener(NouveauCdt.this);
					addItem(item);

					// System.out.println(obj.decrisToi().toUpperCase()
					// + "oooooooooooooooooooooo " + obj.getEts());

					revalidate();
				}
			}
		}).start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAjouter) {
			AjouterCdt ajouter = AjouterCdt.getInstance();
			ajouter.setExamen(examen);
			ajouter.loadEleve(new Eleve());
			ajouter.setParent(this);
			ajouter.setVisible(true);
		}

		if (source == bModifier) {
			doModifier();
		}

		if (source == bSupprimer) {
			controler.setData(eleve);
			model.setData(eleve);
			controler.supprimer(model.DELETE_COMPLETELY);
		}

		if (source == bQuitter) {
			close();
		}

		if (source == bImprimer) {
			ListeSalle pan = new ListeSalle();
			pan.createListe(examen);

			close();
		}
	}

	@Override
	public Avancer getAvancer() {
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		eleve = (EleveClasse) ((OptionItem) source).getInfo();
		if (e.getClickCount() > 1) {
			doModifier();
		}
	}

	private void doModifier() {
		AjouterCdt ajouter = AjouterCdt.getInstance();
		superEleve = (Eleve) cdtDao.findObj(eleve.getCodeInfo());

		ajouter.loadEleve(superEleve);
		ajouter.setExamen(examen);
		ajouter.setParent(this);
		ajouter.setVisible(true);
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

	public static NouveauCdt getInstance() {
		if (instance == null)
			instance = new NouveauCdt();

		return instance;
	}
}
