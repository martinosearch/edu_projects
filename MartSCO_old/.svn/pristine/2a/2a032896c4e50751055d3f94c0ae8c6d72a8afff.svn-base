package planning;

import function.MatriGEN;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observable;
import interfacePerso.Observer;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import matiere.Matiere;
import progress.Avancer;
import agent.Agent;
import classe.Classe;
import connection.DAO;
import connection.DAOFactory;

public class NouveauActivite extends MartFrame implements ChangeListener,
		Observable {

	private static NouveauActivite instance;
	private JLabel lbEnseignant;
	private JLabel lbClasse;
	private JComboBox cbEnseignant;
	private JComboBox cbClasse;
	private JComboBox cbMatiere;
	private DAO ensdao, daoclasse, matdao, actdao;
	private MartList<Agent> enseignants;
	private MartList<Classe> classes;
	private JLabel lbSection;
	private JSpinner jspSection;
	private JTabbedPane panSection;
	private JPanel panBut;
	private int indice;
	private int count;
	private MartList<SpinnerPlanning> listeSection;
	private CreateActivite parent;
	private MartList<Matiere> matieres;
	private JLabel lbMatiere;
	private MatriGEN matriGen;
	private String annee;
	private MartList<Observer> listeObserver = new MartList<Observer>();

	public NouveauActivite(String an) {
		setTitle("Nouvelle Activité");
		setSize(250, 380);
		setLocationRelativeTo(null);
		annee = an;

		initComponent();

		getContentPane().add(container);

		setVisible(true);
	}

	private void initComponent() {
		lbEnseignant = new JLabel("Enseignant");
		lbClasse = new JLabel("Classe");
		lbMatiere = new JLabel("Matière");

		lbSection = new JLabel("Nombre de section");
		jspSection = new SpinnerPlanning("section");
		jspSection.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		jspSection.addChangeListener(this);

		panBut = new JPanel();
		panBut.add(bValider);

		panSection = new JTabbedPane();
		panSection.setPreferredSize(new Dimension(200, 80));

		cbEnseignant = new JComboBox();
		cbClasse = new JComboBox();
		cbMatiere = new JComboBox();

		cbEnseignant.setPreferredSize(new Dimension(200, 30));
		cbClasse.setPreferredSize(new Dimension(200, 30));
		cbMatiere.setPreferredSize(new Dimension(200, 30));

		container = new JPanel();
		container.setLayout(new FlowLayout());

		load();
		indice = (int) jspSection.getValue();
		incrementSection();

		container.add(lbEnseignant);
		container.add(cbEnseignant);
		container.add(lbClasse);
		container.add(cbClasse);
		container.add(lbMatiere);
		container.add(cbMatiere);
		container.add(lbSection);
		container.add(jspSection);
		container.add(panSection);
		container.add(panBut);
	}

	public static void main(String[] args) {

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
			System.out.println("===================>>" + annee);
			actdao.load(null, null, 0, annee);
			for (SpinnerPlanning section : listeSection) {
				Activite act = new Activite(getMatiere(), getClasse(), getEns());
				act.setNbreHeure((int) section.getValue());
				act.setCodeInfo(matriGen.getMatri());

				actdao.update_create(act);

				// System.out.println("Prof: " + act.getEnseignant() +
				// " Classe: "
				// + act.getClasse() + " Nbre H: " + act.getNbreHeure());
			}

			notifyObserver();
			close();
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
		ensdao = DAOFactory.getDAO(DAO.AGENT);
		daoclasse = DAOFactory.getDAO(DAO.CLASSE);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		actdao = DAOFactory.getDAO(DAO.ACTIVITE);

		ensdao.load();
		daoclasse.load();
		matdao.load();

		enseignants = ensdao.getListObt();
		classes = daoclasse.getListObt();
		matieres = matdao.getListObt();

		cbEnseignant.addItem("");
		cbClasse.addItem("");
		cbMatiere.addItem("");

		for (Agent ag : enseignants) {
			cbEnseignant.addItem(ag.decrisToi());
		}

		for (Classe cl : classes) {
			cbClasse.addItem(cl.getIntitule());
		}

		for (Matiere mat : matieres) {
			cbMatiere.addItem(mat.getIntitule());
		}

		// la liste des activités
		listeSection = new MartList<SpinnerPlanning>();
		matriGen = new MatriGEN(MatriGEN.ACTIVITE);
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
	public void stateChanged(ChangeEvent arg0) {
		count = indice;
		indice = (int) jspSection.getValue();
		if (count < indice) {
			incrementSection();
		} else {
			decrementSection();
		}
	}

	private void decrementSection() {
		panSection.remove(indice);
	}

	private void incrementSection() {
		JPanel pan = new JPanel();
		SpinnerPlanning jsp = new SpinnerPlanning("sect_" + indice);
		jsp.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		pan.add(new JLabel("Nombre d'heure"));
		pan.add(jsp);
		panSection.add(String.valueOf(indice), pan);

		listeSection.add(jsp);
		panSection.setSelectedIndex(indice - 1);
	}

	private String getClasse() {
		return (String) cbClasse.getSelectedItem();
	}

	private String getEns() {
		return (String) cbEnseignant.getSelectedItem();
	}

	private String getMatiere() {
		return (String) cbMatiere.getSelectedItem();
	}

	@Override
	public void addObserver(Observer obs) {
		listeObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		listeObserver = new MartList<Observer>();
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : listeObserver) {
			obs.update();
		}
	}
}
