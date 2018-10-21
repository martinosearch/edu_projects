package statistique;

import graphicsModel.MartFrame;
import graphicsModel.MartListSelector;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progress.Avancer;
import rapportBulletin.BullFormat;
import rapportBulletin.DocFormat;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Annee;
import classe.Classe;
import connection.DAO;
import connection.DAOFactory;

public class ChooserSta extends AbstractChooser {
	// nom des conteneurs
	protected JLabel lb1 = new JLabel("Année-Scolaire: ");

	public MartTable mat, matDef;
	protected Object[] data2;

	protected MartTabModel modtab;
	protected MartTabModel modtab2;
	public int typeDec;

	protected Dimension dim1 = new Dimension(150, 20);
	protected Dimension dim2 = new Dimension(100, 20);

	protected AbstractModel model;
	protected AbstractControler controler;

	protected String annee, niveau;

	Classe classe;
	public DocFormat staFormat;

	private AbstractStaWritter writer;

	private MartListSelector selector;

	// constructeur
	public ChooserSta(AbstractStaWritter wrt) {
		super();
		this.setSize(MartFrame.MEDIUM_FRAME);
		this.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.writer = wrt;
		this.setTitle(writer.getTitle());

		initComponent();
		this.addWindowListener(this);

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	public void initComponent() {
		container = new JPanel();

		container.setLayout(new BorderLayout());

		cbAnnee.setPreferredSize(dim1);
		lb1.setPreferredSize(dim1);

		load();
		update();

		JPanel panAn = new JPanel();
		panAn.setBorder(BorderFactory.createTitledBorder("Choix de l'Année"));
		panAn.add(lb1);
		panAn.add(cbAnnee);

		// le conteneur des boutons
		panButton.setLayout(new FlowLayout());
		panButton.add(bValider);
		panButton.add(bAnnuler);

		panTrimestre.add(ckTrimestre1);
		panTrimestre.add(ckTrimestre2);
		panTrimestre.add(ckTrimestre3);
		panTrimestre.setBorder(BorderFactory
				.createTitledBorder("Choix de la section"));
		panTrimestre.setPreferredSize(new Dimension(350, 60));

		JPanel panNorth = new JPanel();
		panNorth.add(panAn);
		panNorth.add(panTrimestre);
		container.add(panNorth, BorderLayout.NORTH);
		container.add(panButton, BorderLayout.SOUTH);
	}

	@Override
	public void load() {
		int trim;
		staFormat = new BullFormat();
		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);

		// permet de remplir la combobox annee scolaire
		annees = andao.getList();
		String[] anneetemp = new String[annees.size()];

		int i = 1;
		for (Annee an : annees) {
			anneetemp[i - 1] = an.getIntitule();
			i++;
		}
		cbAnnee = new JComboBox(anneetemp);

		// Remplissage des tables
		// table de la liste par d�faut
		clsdao.load();
		classes = clsdao.getListObt();
		String[][] temp = new String[classes.size()][1];

		int i3 = 1;
		for (Classe cls : classes) {
			temp[i3 - 1][0] = cls.getIntitule();
			i3++;
		}

		String[] title = { "La liste des Classes" };
		modtab = new MartTabModel(temp, title);
		matDef = new MartTable(modtab);
		matDef.addMouseListener(modtab);

		// table de la liste des mati�res au programme
		Object[][] data2_1 = new Object[0][2];
		String[] title2_1 = { "N°", "Classe" };

		modtab2 = new MartTabModel(data2_1, title2_1);
		mat = new MartTable(modtab2);
		mat.addMouseListener(modtab2);

		// les conteneurs
		selector = new MartListSelector(this, matDef, mat);
		selector.addObserver(this);
		container.add(selector, BorderLayout.CENTER);
	}

	// *************************************************************************************
	public MartTable getTableOfChoise() {
		return mat;
	}

	public MartTable getMatDef() {
		return matDef;
	}

	// les accesseurs
	public String getNiveau() {
		return niveau;
	}

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	public int getDecoupage() {
		return typeDec;
	}

	public DocFormat getFormat() {
		return staFormat;
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

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public void isTrimestreSetting(boolean b) {
		panTrimestre.setVisible(b);
		panTrimestre.revalidate();

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
			writer.valider();
		}
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
	public Classe getClasse() {
		return classe;
	}

	@Override
	public void update() {
		System.out.println("Je suis mise à jour");
		classe = classes.find(selector.getSelection());
		control();
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}
}