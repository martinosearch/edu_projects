package classe;

import graphicsModel.MartFrame;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import progress.Avancer;
import abstractObject.AbstractChooser;
import connection.DAO;
import connection.DAOFactory;

/**
 * La classe qui permet de faire des choix En effet elle contient des m�thode
 * qui permettent de choisir: l'ann�e, le trimestre, la classe, etc
 * 
 * @author Administrateur
 *
 */
public class ChooserClasse extends AbstractChooser {

	private static ChooserClasse instance;

	public ChooserClasse() {
		super();
		this.setSize(300, 220);
		this.setLocation(MartFrame.SMALL_FRAME_CHOOSER_LOCATION);
		this.setModal(false);

		initComponent();

		this.getContentPane().add(container);
	}

	public void initComponent() {
		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);

		panAnnee = new JPanel();
		panTrimestre = new JPanel();
		panButton = new JPanel();
		panClasse = new JPanel();
		container = new JPanel();

		panButton.add(bValider);

		load();// pour les instantiations dynamiques

		panAnnee.setLayout(new GridLayout());
		panButton.setLayout(new GridLayout());
		panTrimestre.setLayout(new GridLayout());
		panClasse.setLayout(new GridLayout());

		panTrimestre.add(ckTrimestre1);
		panTrimestre.add(ckTrimestre2);
		panTrimestre.add(ckTrimestre3);
		ckTrimestre1.setSelected(true);

		container.setLayout(new GridLayout(4, 1, 8, 8));
		container.add(panAnnee);
		container.add(panClasse);
		container.add(panTrimestre);
		container.add(panButton);

		control();
	}

	// cree une instance
	public static ChooserClasse getInstance() {
		if (instance == null)
			instance = new ChooserClasse();

		return instance;
	}

	@Override
	public void load() {
		// on ajoute la liste des annees
		andao.load();
		clsdao.load();
		annees = andao.getList();
		classes = clsdao.getList();

		// on initialise l'année et la classe
		// valeur par défaut
		int indAn = annees.size();
		int indCls = classes.size();

		String[] LAnnee = new String[annees.size()];

		for (int i = 0; i < annees.size(); i++) {
			LAnnee[i] = annees.get(i).getIntitule();
		}

		cbAnnee = new JComboBox(LAnnee);

		panAnnee.removeAll();
		panAnnee.add(lbAnnee);
		panAnnee.add(cbAnnee);
		panAnnee.revalidate();

		// Ensuite la combo des classes

		String[] LClasse = new String[classes.size()];

		for (int i = 0; i < classes.size(); i++) {
			LClasse[i] = classes.get(i).getIntitule();
		}

		cbClasse = new JComboBox(LClasse);
		cbAnnee.addActionListener(this);
		cbClasse.addActionListener(this);

		panClasse.removeAll();
		panClasse.add(lbClasse);
		panClasse.add(cbClasse);
		panClasse.revalidate();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

	}

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	public void close() {
		dispose();
	}

	public Classe getClasse() {
		return classes.find((String) cbClasse.getSelectedItem());
	}

	public void setAction(ActionListener action) {
		bValider.removeActionListener(validerListener);
		validerListener = action;
		bValider.addActionListener(validerListener);
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

	public void setLabelAnnee(String str) {
		lbAnnee.setText(str);
	}

	public void setLabelClasse(String str) {
		lbClasse.setText(str);
	}

	public boolean getFirstOption() {
		System.out.println("Je suis appelé==========>>" + getTrimestre());
		return ckTrimestre1.isSelected();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
