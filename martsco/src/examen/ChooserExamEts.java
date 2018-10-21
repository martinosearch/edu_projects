package examen;

import function.MartArranger;
import function.MartFormatter;
import function.NoteComparator;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progress.Avancer;
import abstractObject.AbstractChooser;
import abstractObject.MartObjet;
import annee.Annee;
import classe.Classe;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;

public class ChooserExamEts extends AbstractChooser {
	private JPanel panAnnee, panRadio, panButton, panExamen, panEts;
	private JPanel container;
	private JButton butValider;
	private String anneeChoosed, examenChoosed;
	private ActionListener validerListener;
	private static ChooserExamEts instance;
	private Dimension dim1 = new Dimension(100, 30);
	private Dimension dim2 = new Dimension(250, 30);
	private Dimension dim3 = new Dimension(290, 60);
	DAO examdao, andao, matdao, elvdao, elvclsdao, decdao, pEtsDao;
	String ins = "", eName = "";
	MartList<Annee> Annees;
	private JComboBox cbAnnee;
	private JComboBox cbEts;
	MartList<Examen> Examens;
	private JComboBox cbExamen;
	private MartFormatter decomposer;
	private MartArranger ma = new MartArranger();
	private String etsChoosed;
	private ConfigExamen conf;

	public ChooserExamEts() {
		this.setSize(400, 230);
		this.setLocationRelativeTo(null);

		initComponent();
	}

	private void initComponent() {
		panAnnee = new JPanel();
		panRadio = new JPanel();
		panButton = new JPanel();
		panExamen = new JPanel();
		panEts = new JPanel();
		container = new JPanel();
		butValider = new JButton("Valider");

		panButton.add(butValider);

		load();

		panAnnee.removeAll();
		panAnnee.add(new JLabel("Année"));
		panAnnee.add(cbAnnee);
		panAnnee.revalidate();

		panAnnee.setLayout(new FlowLayout());
		panButton.setLayout(new FlowLayout());
		panRadio.setLayout(new FlowLayout());
		panExamen.setLayout(new FlowLayout());

		container.setLayout(new GridLayout(5, 1));
		container.add(panAnnee);
		container.add(panExamen);
		container.add(panEts);
		container.add(panRadio);
		container.add(panButton);

		this.getContentPane().add(container);
	}

	public static ChooserExamEts getInstance() {
		if (instance == null)
			instance = new ChooserExamEts();

		instance.load();
		return instance;
	}

	public void load() {
		decomposer = new MartFormatter();

		andao = DAOFactory.getDAO(DAO.ANNEE);
		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		matdao = DAOFactory.getDAO(DAO.MATIERE);
		elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
		elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);

		andao.load();
		examdao.load();

		// on ajoute la liste des annees
		Annees = andao.getListObt();
		Examens = examdao.getListObt();

		// on initialise l'année et l'examen
		// valeur par défaut
		int indexAn = Annees.size();
		int indexExam = Examens.size();

		// permet de remplir la combobox annee scolaire
		String[] anneeTemp = new String[Annees.size() + 1];
		MartList<MartRangeable> listeAnTemp = new MartList<MartRangeable>();

		int i = 0;
		decomposer.decomposer(Annees.get(0).getIntitule(), '-');
		MartObjet obj = new MartObjet(decomposer.getDecomp(2),
				decomposer.getDecomp(2));
		obj.setRang(i);
		listeAnTemp.smartAdd(obj);

		for (Annee an : Annees) {
			i++;
			decomposer.decomposer(an.getIntitule(), '-');
			MartObjet obj2 = new MartObjet(decomposer.getDecomp(1),
					decomposer.getDecomp(1));
			obj2.setRang(i);
			listeAnTemp.smartAdd(obj2);

		}

		MartList<MartRangeable> listeAnOrdonne = ma.ordonner(listeAnTemp,
				NoteComparator.DESCENDANT);

		int j = 0;
		for (MartRangeable obj3 : listeAnOrdonne) {
			String data = (String) (((MartObjet) obj3).getObject());
			anneeTemp[j] = data;
			// System.out.println("============>>" + data);
			j++;
		}

		cbAnnee = new JComboBox(anneeTemp);
		cbAnnee.setPreferredSize(dim2);
		cbAnnee.addActionListener(this);

		// permet de remplir la combobox examen
		setComboExamen();
	}

	private void setComboExamen() {
		// Intialisation
		anneeChoosed = (String) cbAnnee.getSelectedItem();
		String[] extemp = new String[Examens.size()];

		int i2 = 0;
		for (Examen exam : Examens) {
			if (exam.getAnnee().equals(anneeChoosed)) {
				extemp[i2] = exam.getIntitule();
				i2++;
			}
		}

		cbExamen = new JComboBox(extemp);
		cbExamen.setPreferredSize(dim2);
		cbExamen.setName("comboExamen");
		cbExamen.addActionListener(this);

		panExamen.removeAll();
		panExamen.add(new JLabel("Examen"));
		panExamen.add(cbExamen);
		panExamen.revalidate();

		// Intialisation
		anneeChoosed = (String) cbAnnee.getSelectedItem();
		examenChoosed = (String) cbExamen.getSelectedItem();
		System.out.println("jE SUIS exécuté");

		setComboEts();
	}

	public void setComboEts() {
		conf = new ConfigExamen(getExamen());
		ArrayList<String> listeEts = conf.relConfig.getEtsPerso();

		// combo ets
		String[] tabEts = new String[listeEts.size() + 1];
		tabEts[0] = "- -";

		for (int i = 0; i < listeEts.size(); i++) {
			tabEts[i + 1] = listeEts.get(i);
		}

		cbEts = new JComboBox(tabEts);
		cbEts.setPreferredSize(dim2);

		panEts.removeAll();
		panEts.add(new JLabel("Etablissement"));
		panEts.add(cbEts);
		panEts.revalidate();

		// Intialisation
		anneeChoosed = (String) cbAnnee.getSelectedItem();
		examenChoosed = (String) cbExamen.getSelectedItem();
		etsChoosed = (String) cbEts.getSelectedItem();
	}

	// Ecouteurs
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == cbAnnee) {
			anneeChoosed = (String) cbAnnee.getSelectedItem();
			setComboExamen();
		}

		if (source == cbExamen) {
			examenChoosed = (String) cbExamen.getSelectedItem();
			setComboEts();
		}

		examenChoosed = (String) cbExamen.getSelectedItem();
	}

	public String getAnnee() {
		return anneeChoosed;
	}

	public String getExamen() {
		return examenChoosed;
	}

	public void setAction(ActionListener action) {
		butValider.removeActionListener(validerListener);
		this.validerListener = action;
		butValider.addActionListener(validerListener);
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

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public String getEts() {
		return (String) cbEts.getSelectedItem();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Classe getClasse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}

}
