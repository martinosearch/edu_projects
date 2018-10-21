package note;

import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import matiere.MatiereProg;
import progress.Avancer;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Annee;
import classe.Classe;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class ReporterNote extends AbstractChooser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6345862417507901467L;

	private JPanel panTrimestre = new JPanel();
	private JPanel panAnnee = new JPanel();
	private JPanel panOption = new JPanel();

	private JLabel lbAnnee = new JLabel("Année- Scolaire:");
	private JLabel lbClasse = new JLabel("Classe:");
	private JLabel lbMatiereDepart = new JLabel("Matière de Départ:");
	private JLabel lbMatiereDestination = new JLabel("Matière de Destination:");

	public JComboBox cbMatiereDepart = new JComboBox();
	public JComboBox cbMatiereDestination = new JComboBox();

	private Dimension dim1 = new Dimension(100, 30);

	private static AbstractModel model = new NoteModel();
	private static AbstractControler controler = new NoteControler(model);

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);

	private MartList<Note> listeDepart;
	private MartButton bSuivant = new MartButton().getSuivant();

	private JCheckBox ckTrimestreArrive1;

	private JCheckBox ckTrimestreArrive2;

	private JCheckBox ckTrimestreArrive3;

	private JCheckBox ckCutOld = new JCheckBox("Annuler les notes de départ");

	private static ReporterNote instance;

	// constructeur
	public ReporterNote() {
		super();
		this.setTitle("Report de notes");
		this.setSize(MartFrame.MEDIUM_FRAME);
		this.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		this.setResizable(true);

		initComponent();

		this.getContentPane().add(container, BorderLayout.CENTER);
		this.setModal(true);
	}

	// initialisaton des contenus
	public void initComponent() {
		// champ de choix
		cbAnnee.setPreferredSize(dim1);
		cbClasse.setPreferredSize(dim1);
		cbMatiereDepart.setPreferredSize(dim1);
		cbMatiereDestination.setPreferredSize(dim1);

		cbAnnee.setFont(police1);
		cbClasse.setFont(police1);
		cbClasse.setFont(police1);

		// on remplit les box
		// Remplissage des combobox
		// permet de remplir la combobox annee scolaire
		andao = DAOFactory.getDAO(DAO.ANNEE);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		notedao = DAOFactory.getDAO(DAO.NOTE);

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

		cbAnnee.addActionListener(this);
		cbClasse.addActionListener(this);

		setComboMatiere();

		// on ajoute les box aux panels
		panAnnee.setBorder(BorderFactory.createTitledBorder("Année et classe"));
		panAnnee.setLayout(new GridLayout(4, 2, 10, 10));

		// pour le choix du trimestre
		ckTrimestreArrive1 = new JCheckBox("1er Trimestre");
		ckTrimestreArrive2 = new JCheckBox("2è Trimestre");
		ckTrimestreArrive3 = new JCheckBox("3è Trimestre");

		panTrimestre.setBorder(BorderFactory.createTitledBorder("La section"));
		panTrimestre.setLayout(new GridLayout(3, 2, 10, 10));
		panTrimestre.add(ckTrimestre1);
		panTrimestre.add(ckTrimestreArrive1);
		panTrimestre.add(ckTrimestre2);
		panTrimestre.add(ckTrimestreArrive2);
		panTrimestre.add(ckTrimestre3);
		panTrimestre.add(ckTrimestreArrive3);

		panOption.add(ckCutOld);

		// ajout des écouteurs
		ckTrimestre1.addActionListener(this);
		ckTrimestre2.addActionListener(this);
		ckTrimestre3.addActionListener(this);

		// conteneurs des conteneurs
		JPanel panSelector = new JPanel();
		panSelector.setLayout(new GridLayout(3, 1));
		panSelector.add(panTrimestre);
		panSelector.add(panTypeNote);
		panSelector.add(panOption);

		panButton = new JPanel(new FlowLayout());
		panButton.add(bSuivant);
		bSuivant.addActionListener(this);

		container = new JPanel(new BorderLayout());
		container.add(panAnnee, BorderLayout.NORTH);
		container.add(panSelector, BorderLayout.CENTER);
		container.add(panButton, BorderLayout.SOUTH);
		container.revalidate();

		control();
	}

	// Mise ajour
	public void update() {
		this.revalidate();
	}

	// retourne le type de découpage
	public int getTrimestre() {
		int trimestre = 1;
		if (ckTrimestre1.isSelected()) {
			trimestre = 1;
		}

		else if (ckTrimestre2.isSelected()) {
			trimestre = 2;
		}

		else if (ckTrimestre3.isSelected()) {
			trimestre = 3;
		}
		return trimestre;
	}

	public int getTrimestreArrive() {
		int trimestre = 1;
		if (ckTrimestreArrive1.isSelected()) {
			trimestre = 1;
		}

		else if (ckTrimestreArrive2.isSelected()) {
			trimestre = 2;
		}

		else if (ckTrimestreArrive3.isSelected()) {
			trimestre = 3;
		}
		return trimestre;
	}

	public Classe getClasse() {
		return classes.find((String) cbClasse.getSelectedItem());
	}

	public String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	public String getMatiereDepart() {
		return (String) cbMatiereDepart.getSelectedItem();
	}

	public String getMatiereDestination() {
		return (String) cbMatiereDestination.getSelectedItem();
	}

	// pour les séries de notes
	public boolean doInterro1() {
		return (ckInterro1.isSelected()) ? true : false;
	}

	public boolean doInterro2() {
		return (ckInterro2.isSelected()) ? true : false;
	}

	public boolean doDevoir() {
		return (ckDevoir.isSelected()) ? true : false;
	}

	public boolean doCompo() {
		return (ckCompo.isSelected()) ? true : false;
	}

	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		control();

		if (source == bSuivant) {
			model.setAnnee(getAnnee());
			model.setTrimestre(getTrimestre());
			model.setMatiere(getMatiereDestination());
			model.setClasse(getClasse().getIntitule());

			notedao.load(getMatiereDepart(), getClasse().getIntitule(),
					getTrimestre(), getAnnee());

			listeDepart = notedao.getListObt();

			// report des notes
			notedao.load(getMatiereDestination(), getClasse().getIntitule(),
					getTrimestreArrive(), getAnnee());

			for (Note note : listeDepart) {
				notedao.update_create(note);
			}

			// on efface les note dans la liste de départ
			if (isCutOld()) {
				notedao.load(getMatiereDepart(), getClasse().getIntitule(),
						getTrimestre(), getAnnee());
				for (Note note : listeDepart) {
					note.setNote1str("");
					note.setNote2str("");
					note.setNote3str("");
					note.setNote4str("");
					notedao.update_create(note);
				}
			}

			showMessage("Report effectué avec succès");
		}

		if (source == cbClasse) {
			setComboMatiere();
		}

		if (source == cbAnnee) {
			setComboMatiere();
		}
	}

	private boolean isCutOld() {
		// TODO Auto-generated method stub
		return ckCutOld.isSelected();
	}

	public void setComboMatiere() {
		// permet de remplir la combobox matiere
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		matpdao.load(new String(), getClasse().getIntitule(), getTrimestre(),
				getAnnee());

		MartList<MatiereProg> listMat = matpdao.getListObt();
		String[] mattemp = new String[listMat.size()];
		int i3 = 1;
		for (MatiereProg cl : listMat) {
			mattemp[i3 - 1] = cl.getIntitule();
			i3++;
		}
		cbMatiereDepart = new JComboBox(mattemp);
		cbMatiereDestination = new JComboBox(mattemp);

		// matiere

		// mise ajour de l'affichage
		panAnnee.removeAll();
		panAnnee.add(lbAnnee);
		panAnnee.add(cbAnnee);
		panAnnee.add(lbClasse);
		panAnnee.add(cbClasse);
		panAnnee.add(lbMatiereDepart);
		panAnnee.add(cbMatiereDepart);
		panAnnee.add(lbMatiereDestination);
		panAnnee.add(cbMatiereDestination);

		panAnnee.revalidate();
	}

	public static void main(String[] args) {
		Constance.initialize();
		new ReporterNote().setVisible(true);
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

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}
}
