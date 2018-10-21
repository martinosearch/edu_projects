package rapportBulletin;

import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import note.NoteModel;
import progress.Avancer;
import abstractObject.AbstractChooser;
import abstractObject.AbstractModel;
import abstractObject.Rapport;
import annee.Annee;
import classe.Classe;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class ChooserEval extends AbstractChooser implements Observer {
	private Dimension dim1 = new Dimension(100, 30);

	private static AbstractModel model = new NoteModel();

	private static ChooserEval instance;

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Container container;
	private DAO andao, clsdao;
	private MartButton bSuivant = new MartButton().getSuivant();
	protected MartList<Classe> classes;

	// constructeur
	public ChooserEval() {
		super();
		this.setTitle("Saisie de notes");
		this.setSize(MartFrame.SMALL_FRAME);
		this.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		this.setResizable(false);

		initComponent();

		this.setContentPane(container);
	}

	// initialisaton des contenus
	public void initComponent() {
		// pour le choix de l'evalution
		panTypeNote.setBorder(BorderFactory.createTitledBorder("Evaluation"));

		ButtonGroup bg = new ButtonGroup();
		bg.add(ckInterro1);
		bg.add(ckInterro2);
		bg.add(ckDevoir);
		bg.add(ckCompo);
		ckCompo.setSelected(true);

		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);

		MartList<Annee> annees = andao.getList();
		String[] anneetemp = new String[annees.size()];

		int i = 1;
		for (Annee an : annees) {
			anneetemp[i - 1] = an.getIntitule();
			i++;
		}
		cbAnnee = new JComboBox(anneetemp);

		// permet de remplir la combobox classe
		classes = clsdao.getList();
		String[] cltemp = new String[classes.size()];

		int i2 = 1;
		for (Classe cl : classes) {
			cltemp[i2 - 1] = cl.getIntitule();
			i2++;
		}
		cbClasse = new JComboBox(cltemp);

		cbAnnee.setPreferredSize(dim1);
		cbClasse.setPreferredSize(dim1);
		cbMatiere.setPreferredSize(dim1);
		cbAnnee.addActionListener(this);
		cbClasse.addActionListener(this);

		// mise ajour de l'affichage
		panReference.removeAll();
		panReference.add(lbAnnee);
		panReference.add(cbAnnee);
		panReference.add(lbClasse);
		panReference.add(cbClasse);

		panReference.revalidate();

		// on ajoute les box aux panels
		panReference.setBorder(BorderFactory.createTitledBorder("Références"));
		panReference.setLayout(new GridLayout(3, 2, 10, 10));

		panTrimestre.setBorder(BorderFactory
				.createTitledBorder("Le type de découpage"));
		panTrimestre.setLayout(new GridLayout(3, 1, 10, 10));
		panTrimestre.add(ckTrimestre1);
		panTrimestre.add(ckTrimestre2);
		panTrimestre.add(ckTrimestre3);

		// ajout des écouteurs
		ckTrimestre1.addActionListener(this);
		ckTrimestre2.addActionListener(this);
		ckTrimestre3.addActionListener(this);

		// conteneurs des conteneurs
		JPanel panSelector = new JPanel();
		panSelector.setLayout(new GridLayout(1, 2));
		panSelector.add(panTrimestre);
		panSelector.add(panTypeNote);

		container = new JPanel();
		container.removeAll();
		container.setLayout(new BorderLayout());
		container.add(panSelector, BorderLayout.CENTER);
		container.add(panReference, BorderLayout.NORTH);

		panButton = new JPanel(new FlowLayout());
		panButton.add(bValider);
		panButton.add(bAnnuler);
		bSuivant.addActionListener(this);

		container.add(panButton, BorderLayout.SOUTH);
		container.revalidate();

		control();
	}

	// Mise ajour
	public void update() {
		this.revalidate();
	}

	public Classe getClasse() {
		return classes.find((String) cbClasse.getSelectedItem());
	}

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	// pour les séries de notes
	public boolean isInterro1() {
		return (ckInterro1.isSelected()) ? true : false;
	}

	public boolean isInterro2() {
		return (ckInterro2.isSelected()) ? true : false;
	}

	public boolean isDevoir() {
		return (ckDevoir.isSelected()) ? true : false;
	}

	public boolean isCompo() {
		return (ckCompo.isSelected()) ? true : false;
	}

	public void actionPerformed(ActionEvent e) {
		control();
		Component source = (Component) e.getSource();

	}

	public static void main(String[] args) {
		Constance.initialize();
		new ChooserEval().setVisible(true);
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

	public String getTrimestreStr() {
		String str = "";
		if (getTrimestre() == 1)
			str = ckTrimestre1.getText();
		if (getTrimestre() == 2)
			str = ckTrimestre1.getText();
		if (getTrimestre() == 3)
			str = ckTrimestre1.getText();

		return str;
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}

	public static ChooserEval getInstance() {
		if (instance == null) {
			instance = new ChooserEval();
		}
		return instance;
	}

	public int getEvaluation() {
		if (isInterro1())
			return Rapport.INTERRO1;
		if (isInterro2())
			return Rapport.INTERRO2;
		if (isDevoir())
			return Rapport.DEVOIR;
		else
			return Rapport.COMPO;
	}

}
