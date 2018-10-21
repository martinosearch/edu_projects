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
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import matiere.MatiereProg;
import note.ChampNoteExam;
import note.NoteExamControler;
import note.NoteExamModel;
import progress.Avancer;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import abstractObject.MartObjet;
import annee.Annee;
import classe.Classe;
import connection.DAO;
import connection.DAOFactory;

public class ChooserNoteExam extends AbstractChooser {

	private JPanel panAnnee, panRadio, panButton, panExamen, panMatiere;
	private JPanel container;
	private static ChooserNoteExam instance;
	private Dimension dim2 = new Dimension(300, 30);
	DAO examdao, andao, matProgDao, elvdao, elvclsdao, decdao;
	String ins = "", eName = "";
	MartList<Annee> Annees;
	MartList<Examen> Examens;

	private JComboBox cbAnnee;
	private JComboBox cbExamen;
	private JComboBox cbMatiere;
	private MartFormatter decomposer;
	private MartArranger ma = new MartArranger();
	private JCheckBox ckNoteClasse;
	private JCheckBox ckNoteCompo;
	private MartList<MatiereProg> matieres;

	public ChooserNoteExam() {
		this.setSize(400, 260);
		this.setLocationRelativeTo(null);
		this.setModal(true);

		initComponent();
	}

	private void initComponent() {
		panAnnee = new JPanel();
		panRadio = new JPanel();
		panButton = new JPanel();
		panExamen = new JPanel();
		panMatiere = new JPanel();
		container = new JPanel();

		panButton.add(bValider);

		load();

		panAnnee.setLayout(new FlowLayout());
		panButton.setLayout(new FlowLayout());
		panRadio.setLayout(new GridLayout(2, 2));
		panExamen.setLayout(new FlowLayout());
		panMatiere.setLayout(new FlowLayout());

		panAnnee.removeAll();
		panAnnee.add(new JLabel("Année"));
		panAnnee.add(cbAnnee);

		ckNoteClasse = new JCheckBox("Note de Classe");
		ckNoteCompo = new JCheckBox("Note Examen");
		ckNoteCompo.setSelected(true);

		panRadio.add(ckNoteClasse);
		panRadio.add(ckNoteCompo);

		container.setLayout(new GridLayout(5, 1));
		container.add(panAnnee);
		container.add(panExamen);
		container.add(panMatiere);
		container.add(panRadio);
		container.add(panButton);

		this.getContentPane().add(container);
	}

	public static ChooserNoteExam getInstance() {
		if (instance == null)
			instance = new ChooserNoteExam();
		return instance;
	}

	public void load() {
		decomposer = new MartFormatter();

		andao = DAOFactory.getDAO(DAO.ANNEE);
		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		matProgDao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
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
		MartFormatter.decomposer(Annees.get(0).getIntitule(), '-');
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
		String[] extemp = new String[Examens.size()];

		int i2 = 0;
		for (Examen exam : Examens) {
			if (exam.getAnnee().equals(getAnnee())) {
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

		setComboMatiere();
	}

	private void setComboMatiere() {
		matProgDao.loadExam(getExamen());
		matieres = matProgDao.getListObt();
		String[] tempMatieres = new String[matieres.size()];

		try {
			int i2 = 0;
			for (MatiereProg mat : matieres) {
				tempMatieres[i2] = mat.getIntitule();
				i2++;
			}

			cbMatiere = new JComboBox(tempMatieres);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cbMatiere.setPreferredSize(dim2);
		cbMatiere.setName("comboExamen");
		cbMatiere.addActionListener(this);

		panMatiere.removeAll();
		panMatiere.add(new JLabel("Matières"));
		panMatiere.add(cbMatiere);
		panMatiere.revalidate();
	}

	// Ecouteurs
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == cbAnnee) {
			setComboExamen();
		}

		if (source == cbExamen) {
			setComboMatiere();
		}

		if (source == bValider) {
			if (!(getExamen() == (String) null)) {
				AbstractModel model = new NoteExamModel();
				AbstractControler controler = new NoteExamControler(model);
				ChampNoteExam chp = new ChampNoteExam(controler, this);

				// SOndage
				// Sondage sond = new Sondage();
				// sond.createResutat(getExamen());
				// sond.setVisible(true);
				this.dispose();
				chp.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null,
						"L'année que vous avez choisi \n" + "n'a pas d'examen");
			}
		}

	}

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	public String getExamen() {
		return (String) cbExamen.getSelectedItem();
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

	public boolean getNoteClasse() {
		return ckNoteClasse.isSelected();
	}

	public boolean getNoteCompo() {
		return ckNoteCompo.isSelected();
	}

	public String getMatiere() {
		return (String) cbMatiere.getSelectedItem();
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
