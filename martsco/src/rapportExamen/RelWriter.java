package rapportExamen;

import eleve.EleveClasse;
import etablissement.FilterEts;
import graphicsModel.ListeSelectorData;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.MartListSelector;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JTable;

import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import abstractObject.Rapport;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class RelWriter extends MartFrame implements Observer {
	// nom des conteneurs

	public MartTable mat, matdef;
	private MartTabModel modtab;
	public MartTabModel modtab2;

	private AbstractControler controler;
	private AbstractModel model;
	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 14);
	private DAO elvclsdao;
	private MartListSelector selector;
	private MartButton bValider = new MartButton().getValider();
	private MartButton bAnnuler = new MartButton().getAnnuler();
	private String examen;
	private int typeRapport;
	private String annee;
	private int trimestre;
	private String classe;
	private int evaluation;
	private MartList<EleveClasse> eleves;
	private String etablissement;

	// constructeur
	public RelWriter(AbstractControler controler) {
		this.setTitle("Génération de Bulletins");
		this.setSize(MEDIUM_FRAME);
		setLocation(INTERNAL_FRAME_LOCATION);
		setToolBarVertical();

		// le controleur
		this.controler = controler;
		model = controler.getModel();

		initComponent();

		// fenetre proprieties
		this.getContentPane().add(selector, BorderLayout.CENTER);
	}

	public RelWriter(AbstractControler controler, String ets) {
		this.setTitle("Génération de Bulletins");
		this.setSize(MEDIUM_FRAME);
		setLocation(INTERNAL_FRAME_LOCATION);
		setToolBarVertical();

		// le controleur
		this.controler = controler;
		model = controler.getModel();
		etablissement = ets;

		initComponent();

		// fenetre proprieties
		this.getContentPane().add(selector, BorderLayout.CENTER);
	}

	/**
	 * Initialisation des composants
	 * 
	 */
	public void initComponent() {

		// le conteneur des boutons
		barreOutilsV.add(bValider);
		barreOutilsV.add(bAnnuler);

		addListeners();

		// tableau du choix de matières
		// table de la liste des matières au programme
		Object[][] data2_1 = new Object[0][4];
		String[] title2_1 = { "N°", "N°Mle", "Nom", "Prenom" };
		modtab2 = new MartTabModel(data2_1, title2_1);

		mat = new MartTable(modtab2);
		mat.addMouseListener(modtab2);

		refresh();
	}

	public JTable getMat() {
		return mat;
	}

	// methode qui met ajour
	public void update() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bValider) {
			controler.valider();
		}
	}

	@Override
	public void refresh() {
		typeRapport = model.getTypeRapport();

		if (typeRapport == Rapport.EVAL_TRIMESTRIELLE) {
			annee = model.getAnnee();
			trimestre = model.getTrimestre();
			classe = model.getClasse();
			evaluation = model.getEvaluation();

			elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
			elvclsdao.load(null, classe, trimestre, annee);
			eleves = elvclsdao.getListObt();
		} else {
			examen = model.getExamen();
			elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
			elvclsdao.loadExam(examen);

			FilterEts filter = new FilterEts(etablissement);
			eleves = filter.getListe(elvclsdao.getListObt());
		}

		// ****tableau de la liste des élèves*******
		String[][] temp = new String[eleves.size()][3];
		int i3 = 1;
		for (EleveClasse elv : eleves) {
			temp[i3 - 1][0] = elv.getCodeInfo();
			temp[i3 - 1][1] = elv.getNom();
			temp[i3 - 1][2] = elv.getPrenom();
			i3++;
		}

		String[] title = { "Code Info", "Nom", "Prénoms" };
		modtab = new MartTabModel(temp, title);
		matdef = new MartTable(modtab);
		matdef.addMouseListener(modtab);

		// ajout des données
		selector = new MartListSelector(this, matdef, mat);
		selector.setData(new ListeSelectorData(eleves));
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
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
	public void load() {
		// TODO Auto-generated method stub

	}

	public void setEtablissement(String ets) {
		etablissement = ets;
	}
}
