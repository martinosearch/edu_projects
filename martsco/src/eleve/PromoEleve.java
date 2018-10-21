package eleve;

import function.GeneralVoid;
import graphicsModel.ListeSelectorData;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.MartTwoListeSelector;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;

import componentFactory.MartButton;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import connection.DAO;
import connection.DAOFactory;

/**
 * Cette classe est contruite sur le model purement MVC Elle est une vue Elle
 * permet de définir la promotion des élèves
 * 
 * @author martino
 *
 */
public class PromoEleve extends MartFrame implements Observer {
	// nom des conteneurs

	private JPanel panNou = new JPanel();
	private JPanel panRed = new JPanel();
	private JPanel container2 = new JPanel();
	private JPanel panListePrinc = new JPanel();
	private JPanel panMoovers = new JPanel();
	private JPanel container = new JPanel();
	private JPanel butPan;
	private JPanel panListeEtButton;
	private JSplitPane split1;
	private JSplitPane split2;
	private JSplitPane split3;

	private MartTable tabRed;
	private MartTable defaultTable;
	private MartTable tabNou;
	private Object[] data2;

	private MartTabModel modtab;
	private MartTabModel modtabRed;
	private MartTabModel modtabNou;

	private Dimension dim1 = new Dimension(150, 20);
	private Dimension dim2 = new Dimension(100, 20);

	private AbstractModel model;
	private AbstractControler controler;
	private DAO elvclsdao;

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 14);
	public int nouCount, redCount;
	private MartTwoListeSelector selector;
	private MartList<EleveClasse> listEleveNou;
	private MartList<EleveClasse> listEleveRed;
	private MartList<EleveClasse> listNonClasses;
	private MartList<EleveClasse> listEleve;
	private MartButton bQuitter = new MartButton().getQuitter();
	private MartButton bSave = new MartButton().getSauvegarder();
	private String classe;
	private int trimestre;
	private String annee;

	// constructeur
	public PromoEleve(AbstractControler controler) {
		this.setTitle("DEFINITION DE LA PROMOTION DES ELEVES");
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.addWindowListener(this);
		setToolBarVertical();

		// le controleur
		this.controler = controler;
		model = controler.getModel();
		classe = model.getClasse();
		trimestre = model.getTrimestre();
		annee = model.getAnnee();

		initComponent();

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	// ************************************************************************
	// CREATION DE COMPOSANTS
	// ************************************************************************

	public void initComponent() {
		barreOutilsV.add(bSave);
		barreOutilsV.add(bQuitter);

		addListeners();
		// -------------------------------------------------------------------
		load();// Chargement des remplissage dynamiques

		selector = new MartTwoListeSelector(this, tabRed, defaultTable, tabNou);
		selector.setData(new ListeSelectorData<EleveClasse>(listEleve));
		selector.setTitle1("Liste des Redoublants");
		selector.setTitle2("Liste des Nouveaux");

		container.setLayout(new BorderLayout());

		refresh();
	}

	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bQuitter) {
			close();
		}

		if (source == bSave) {
			controler.valider();
		}
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void windowClosing(WindowEvent arg0) {
	}

	public void windowDeactivated(WindowEvent arg0) {

	}

	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}

	// cree une instance
	// permet donc de ne instancier qu'une seul fois (pattener singleton)
	@SuppressWarnings("unchecked")
	public void load() {
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		elvclsdao.load(new String(), classe, trimestre, annee);

		listEleve = elvclsdao.getListObt();
		listNonClasses = new MartList<EleveClasse>();
		listEleveNou = new MartList<EleveClasse>();
		listEleveRed = new MartList<EleveClasse>();

		// initialisation des promotion
		for (EleveClasse elv : listEleve) {
			String statut = null;
			try {
				statut = (elv.getStatut()).toString();
			} catch (Exception e) {

			}

			if (statut != null) {
				if (statut.equals("N")) {
					listEleveNou.add(elv);
				} else if (statut.equals("R")) {
					listEleveRed.add(elv);
				} else {
					listNonClasses.add(elv);
				}
			} else {
				listNonClasses.add(elv);
			}
		}
		MartList<MartRangeable> ressources = model.getRessources();
		nouCount = 0;
		redCount = 0;

		// Remplissage des tables
		// table de la liste par défaut
		String[][] temp = new String[listNonClasses.size()][4];

		int i3 = 1;
		for (EleveClasse elv : listNonClasses) {
			temp[i3 - 1][0] = elv.getCodeInfo();
			temp[i3 - 1][1] = elv.getNom();
			temp[i3 - 1][2] = elv.getPrenom();
			temp[i3 - 1][3] = elv.getSexe();
			i3++;
		}

		String[][] tempNou = new String[listEleveNou.size()][4];

		int i4 = 1;
		for (EleveClasse elv : listEleveNou) {
			nouCount++;
			tempNou[i4 - 1][0] = String.valueOf(nouCount);
			tempNou[i4 - 1][1] = elv.getCodeInfo();
			tempNou[i4 - 1][2] = elv.getNom();
			tempNou[i4 - 1][3] = elv.getPrenom();
			i4++;
		}

		String[][] tempRed = new String[listEleveRed.size()][4];

		int i5 = 1;
		for (EleveClasse elv : listEleveRed) {
			redCount++;
			tempRed[i5 - 1][0] = String.valueOf(redCount);
			tempRed[i5 - 1][1] = elv.getCodeInfo();
			tempRed[i5 - 1][2] = elv.getNom();
			tempRed[i5 - 1][3] = elv.getPrenom();

			i5++;
			redCount++;
		}

		// le conteneur de la liste principale
		String[] title = { "Code Info", "Nom", "Prénoms", "Sexe" };
		modtab = new MartTabModel(temp, title);
		defaultTable = new MartTable(modtab);
		defaultTable.addMouseListener(modtab);

		// liste des nouveaux
		String[] title2 = { "N°", "Code Info", "Nom", "Prénoms" };
		modtabNou = new MartTabModel(tempNou, title2);
		tabNou = new MartTable(modtabNou);
		tabNou.addMouseListener(modtabNou);
		tabNou.setId("tableNou");

		// liste des Redoublants
		modtabRed = new MartTabModel(tempRed, title2);
		tabRed = new MartTable(modtabRed);
		tabRed.addMouseListener(modtabRed);
		tabRed.setId("tableRed");
	}

	@Override
	public void refresh() {
		container.removeAll();
		container.add(selector, BorderLayout.CENTER);
		container.revalidate();
	}

	public static void reset() {
	}

	@Override
	public Avancer getAvancer() {
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
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
	public void update() {
		load();
		refresh();
	}

	public static void main(String[] args) {
		new GeneralVoid().promoEleve();
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

	public MartTwoListeSelector getSelector() {
		return selector;
	}

}
