package rapportBulletin;

import eleve.EleveClasse;
import function.Constance;
import function.GeneralVoid;
import graphicsModel.ListeSelectorData;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.MartListSelector;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import componentFactory.MartButton;
import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class BullWriter extends MartFrame implements Observer {
	// nom des conteneurs

	private JPanel panBut = new JPanel();
	JPanel splitCont = new JPanel();
	JLabel lbTitle = new JLabel("Choisissez les élèves concernés");

	public MartTable mat, matdef;
	private Object[] data2;

	private MartTabModel modtab;
	public MartTabModel modtab2;

	public int Dec;
	private String annee = "", classe = "";
	private int trimestre = 1;

	private Dimension dim1 = new Dimension(150, 30);
	private AbstractControler controler;
	private AbstractModel model;
	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 14);
	private DAO elvclsdao;
	private MartListSelector selector;
	private MartButton bValider = new MartButton().getValider();
	private MartButton bAnnuler = new MartButton().getAnnuler();

	// constructeur
	public BullWriter(AbstractControler controler) {
		setTitle("Génération de Bulletins");
		setSize(MEDIUM_FRAME);
		setLocation(INTERNAL_FRAME_LOCATION);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setToolBarVertical();

		// le controleur
		this.controler = controler;
		model = controler.getModel();

		initComponent();

		// fenetre proprieties
		getContentPane().add(container, BorderLayout.CENTER);
	}

	/**
	 * Initialisation des composants
	 * 
	 */
	public void initComponent() {
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);

		// le conteneur des boutons
		panBut = new JPanel();
		panBut.setLayout(new GridLayout());

		barreOutilsV.add(bValider);
		barreOutilsV.add(bAnnuler);

		addListeners();

		Object[][] data2_1 = new Object[0][4];
		String[] title2_1 = { "N°", "N°Mle", "Nom", "Prenom" };
		modtab2 = new MartTabModel(data2_1, title2_1);

		mat = new MartTable(modtab2);
		mat.addMouseListener(modtab2);

		refresh();

		container = new JPanel(new BorderLayout());
		container.add(selector, BorderLayout.CENTER);
		container.add(panBut, BorderLayout.SOUTH);
	}

	public JTable getMat() {
		return mat;
	}

	// methode qui met ajour
	public void update() {
		lbTitle.setText("Bulletins: " + model.getClasse() + "/ "
				+ model.getTrimestre() + "/ " + model.getAnnee());
	}

	public void reset() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bValider) {
			controler.valider();
		}
	}

	@Override
	public void load() {
	}

	@Override
	public void refresh() {
		annee = model.getAnnee();
		trimestre = model.getTrimestre();
		classe = model.getClasse();

		elvclsdao.load(new String(), classe, trimestre, annee);

		MartList<EleveClasse> listElv = elvclsdao.getList();

		// ****tableau de la liste des élèves*******
		String[][] temp = new String[listElv.size()][3];
		int i3 = 1;
		for (EleveClasse elv : listElv) {
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
		selector.setData(new ListeSelectorData(listElv));
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

	public MartTable getTableDefaut() {
		return selector.defaultTable;
	}

	public MartTable getTable() {
		return selector.newTable;
	}

	public static void main(String[] args) {
		Constance.initialize();
	}

}
