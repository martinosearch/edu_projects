package classe;

import graphicsModel.ListeSelectorData;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.MartListSelector;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import componentFactory.MartButton;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import progress.Avancer;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import connection.DAO;
import connection.DAOFactory;

public class FusionClasse extends MartFrame implements Observer {

	private DAO clsdao;
	private MartList<Classe> classes;
	private Container container;
	private AbstractModel model;
	private AbstractControler controler;
	private String annee;
	private MartListSelector selector;
	private JPanel panIntitule;
	private JLabel lbIntitule;
	private JTextField tfIntitule;
	private Font font = new Font("Consolas", Font.BOLD, 16);
	private MartButton bValider = new MartButton().getValider();

	public FusionClasse(String an) {
		setTitle("Fusion de classe");
		setSize(600, 350);
		setLocationRelativeTo(null);

		annee = an;

		initComponent();
		model = new FusionClasseModel();
		model.addObserver(this);
		controler = new FusionClasseControler(model);

		getContentPane().add(container);
	}

	private void initComponent() {
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		clsdao.load();

		lbIntitule = new JLabel("Intitulé de la Fusion");
		tfIntitule = new JTextField();
		tfIntitule.setPreferredSize(new Dimension(200, 30));

		panIntitule = new JPanel();
		panIntitule.setLayout(new FlowLayout());
		tfIntitule.setFont(font);

		panIntitule.add(lbIntitule);
		panIntitule.add(tfIntitule);

		classes = clsdao.getListObt();
		Object[][] data1 = new Object[classes.size()][1];
		String[] title1 = { "Classes par défaut" };

		int i = 0;
		for (Classe cl : classes) {
			data1[i][0] = cl.getIntitule();
			i++;
		}

		MartTabModel mod1 = new MartTabModel(data1, title1);

		MartTable tableDef = new MartTable(mod1);

		Object[][] data2 = new Object[0][3];
		String[] title2 = { "N°", "Intitule", "Niveau" };

		MartTabModel mod2 = new MartTabModel(data2, title2);

		MartTable tableNou = new MartTable(mod2);
		tableNou.setColumnSize(0, 0.5);

		selector = new MartListSelector(this, tableDef, tableNou);
		selector.setData(new ListeSelectorData<Classe>(classes));
		selector.setTitle1("Déplacer les classes concernées ici");

		JPanel panBut = new JPanel();
		panBut.add(bValider);

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(panIntitule, BorderLayout.NORTH);
		container.add(selector, BorderLayout.CENTER);
		container.add(panBut, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		new FusionClasse("2016-2017").setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bValider) {
			model.setAnnee(annee);
			controler.valider();
		}
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
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh() {
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
	public void update() {
		// TODO Auto-generated method stub

	}

	public MartTable getTableDef() {
		return selector.getTableDef();
	}

	public MartTable getTable() {
		return selector.getTable();
	}

	public String getIntitule() {
		return tfIntitule.getText();
	}
}
