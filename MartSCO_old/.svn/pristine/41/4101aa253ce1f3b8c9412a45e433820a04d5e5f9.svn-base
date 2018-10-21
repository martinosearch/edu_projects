package note;

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
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import componentFactory.MartButton;
import matiere.MatiereProg;
import progress.Avancer;
import abstractObject.AbstractChooser;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Annee;
import classe.Classe;
import connection.DAO;
import connection.DAOFactory;

public class ChooserNote extends AbstractChooser implements Observer {
	private JLabel lb1 = new JLabel("Année- Scolaire:");
	private JLabel lb2 = new JLabel("Classe:");
	private JLabel lb3 = new JLabel("Matière:");

	private Dimension dim1 = new Dimension(100, 30);

	private static AbstractModel model = new NoteModel();
	private static AbstractControler controler = new NoteControler(model);
	private static ChooserNote instance;

	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Container container;
	private DAO andao, clsdao, matpdao;
	private String currentMatiere;
	private ChampNote champ;
	private MartButton bSuivant = new MartButton().getSuivant();
	private MartList<MatiereProg> matieres;
	protected MartList<Classe> classes;

	// constructeur
	public ChooserNote() {
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
		// on remplit les box
		// Remplissage des combobox
		// permet de remplir la combobox annee scolaire
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

		setComboMatiere();

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
		panButton.add(bSuivant);
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

	public MatiereProg getMatiere() {
		MatiereProg matiere = (MatiereProg) matieres.find((String) cbMatiere
				.getSelectedItem());
		return matiere;
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

		if (source == bSuivant) {
			champ = new ChampNote(getMatiere(), getClasse(), getTrimestre(),
					getAnnee());

			champ.setInterro1(isInterro1());
			champ.setInterro2(isInterro2());
			champ.setDevoir(isDevoir());
			champ.setCompo(isCompo());

			try {
				currentMatiere = getMatiere().getIntitule();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			champ.setVisible(true);
			dispose();// idée de ne pas perdre les choix précedents
		}

		if (source == cbClasse) {
			try {
				currentMatiere = getMatiere().getIntitule();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			setComboMatiere();
		}

		if (source == cbAnnee) {
			try {
				currentMatiere = getMatiere().getIntitule();
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			setComboMatiere();
		}
	}

	public void setComboMatiere() {
		// permet de remplir la combobox matiere
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		matpdao.load(new String(), getClasse().getIntitule(), getTrimestre(),
				getAnnee());
		matieres = matpdao.getListObt();
		String[] mattemp = new String[matieres.size()];
		int i3 = 1;
		for (MatiereProg cl : matieres) {
			mattemp[i3 - 1] = cl.getIntitule();
			i3++;
		}
		cbMatiere = new JComboBox(mattemp);

		// reccupération de la précedente selection de matière
		if (currentMatiere != null) {
			try {
				cbMatiere.setSelectedItem(currentMatiere);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// mise ajour de l'affichage
		panReference.removeAll();
		panReference.add(lb1);
		panReference.add(cbAnnee);
		panReference.add(lb2);
		panReference.add(cbClasse);
		panReference.add(lb3);
		panReference.add(cbMatiere);

		panReference.revalidate();
	}

	public static void main(String[] args) {
		Constance.initialize();
		new ChooserNote().setVisible(true);
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

	public static ChooserNote getInstance() {
		if (instance == null) {
			instance = new ChooserNote();
		}
		return instance;
	}

}
