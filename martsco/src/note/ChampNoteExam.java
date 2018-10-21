package note;

import eleve.EleveClasse;
import examen.ChooserNoteExam;
import examen.SalleGenerator;
import graphicsModel.FrameIcon;
import graphicsModel.MartDialog;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classe.PanChooseEleveClasse;
import componentFactory.MartButton;
import progress.Avancer;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;

public class ChampNoteExam extends MartFrame implements Observer {
	private JPanel rContainer = new JPanel();
	private JPanel nContainer = new JPanel();

	protected MartButton bSave = new MartButton().getSauvegarder();
	protected MartButton bPrecedent = new MartButton().getPrecedent();
	protected MartButton bSuivant = new MartButton().getSuivant();
	protected MartButton bQuitter = new MartButton().getQuitter();

	private JLabel lbNom = new JLabel("Nom et Prénoms");
	private JLabel lbIntEx = new JLabel("Classe");
	private JLabel lbMatiere = new JLabel("Notes de Mathématique");
	private JLabel lbDevoir = new JLabel("Note de Classe:");
	private JLabel lbExamen = new JLabel("Note d'Examen:");
	private JLabel lbNum = new JLabel("1");
	private JLabel lbSalle = new JLabel("Salle 1");

	protected JTextField tfNote1 = new JTextField();
	protected JTextField tfNote2 = new JTextField();
	private String matiere = "";
	private String note1str, note2str;

	private AbstractModel model;
	private AbstractControler controler;
	public EleveClasse currentEleve;
	public int num = 0, max = 0, maxNote = 0;

	Font police1 = new Font("Times new Romam", Font.BOLD, 16);
	Font police2 = new Font("Times new Romam", Font.BOLD, 25);
	Font police3 = new Font("Times new Romam", Font.BOLD, 12);
	Font police4 = new Font("Times new Romam", Font.BOLD, 20);

	DAO cdtPersodao, notedao;
	String ins = "";
	String annee = "";
	String examen = "";
	String matstr = "";
	MartList<EleveClasse> eleves;
	MartList<Note> notes;
	private boolean setNoteClasse;
	private boolean setNoteCompo;
	private ChooserNoteExam chooser;
	private ConfigExamen conf;

	// constructeur
	public ChampNoteExam(AbstractControler ctrl, ChooserNoteExam choos) {
		this.setSize(MEDIUM_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getNotes(examen));

		// le controleur
		controler = ctrl;
		model = controler.getModel();

		chooser = choos;
		examen = chooser.getExamen();
		setNoteClasse = chooser.getNoteClasse();
		setNoteCompo = chooser.getNoteCompo();
		matiere = chooser.getMatiere();

		initComponent();

		// on lance l'affichage du sondage
		/*
		 * Sondage sond = new Sondage(); sond.createResutat(examen);
		 * sond.setVisible(true);
		 */

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	// initialisaton des contenus
	public void initComponent() {
		// pour la barre d'outils
		barreOutilsV.add(bSave);
		barreOutilsV.add(bPrecedent);
		barreOutilsV.add(bSuivant);
		barreOutilsV.add(bQuitter);
		addListeners();

		// champ de choix
		lbNom.setFont(police4);
		lbIntEx.setFont(police1);
		lbMatiere.setFont(police1);
		lbDevoir.setFont(police3);
		lbExamen.setFont(police3);

		tfNote1.setFont(police2);
		tfNote2.setFont(police2);

		lbNom.setForeground(Color.BLUE);
		lbIntEx.setForeground(Color.BLUE);
		lbMatiere.setForeground(Color.DARK_GRAY);
		tfNote1.setForeground(Color.BLUE);
		tfNote2.setForeground(Color.DARK_GRAY);

		// ajout des controleurs
		tfNote1.addFocusListener(this);
		tfNote2.addFocusListener(this);

		tfNote1.addKeyListener(this);
		tfNote2.addKeyListener(this);

		rContainer.setBorder(BorderFactory.createTitledBorder("Références"));
		JPanel rpan1 = new JPanel();
		JPanel rpan2 = new JPanel();
		rpan1.setPreferredSize(new Dimension(getSize().width - 300, 100));
		rpan1.setLayout(new GridLayout(3, 1, 10, 10));
		rpan1.add(lbNom);
		rpan1.add(lbIntEx);
		rpan1.add(lbMatiere);

		rpan2.setLayout(new GridLayout(2, 1));

		lbSalle.setFont(new Font("Times new Romam", Font.BOLD, 40));
		lbSalle.setForeground(Color.RED);
		lbNum.setFont(new Font("Times new Romam", Font.BOLD, 40));
		lbNum.setForeground(Color.darkGray);
		rpan2.add(lbSalle);
		rpan2.add(lbNum);

		rContainer.setLayout(new BorderLayout());
		nContainer.setPreferredSize(new Dimension(getSize().width - 120, 100));

		rContainer.add(rpan1, BorderLayout.CENTER);
		rContainer.add(rpan2, BorderLayout.EAST);

		// ajout des champs de saisi
		nContainer.setBorder(BorderFactory
				.createTitledBorder("Notes de l'élève"));
		nContainer.setLayout(new GridLayout(2, 2, 10, 10));
		nContainer.setPreferredSize(new Dimension(300, 150));
		nContainer.add(lbDevoir);
		nContainer.add(tfNote1);
		nContainer.add(lbExamen);
		nContainer.add(tfNote2);

		container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.LEFT));
		container.add(rContainer);
		container.add(nContainer);

		// affichage ou non du nom des élèves
		conf = new ConfigExamen(examen);
		lbNom.setVisible(conf.relConfig.getAfficherNom());

		load();
		refresh();
		bPrecedent.setEnabled(true);
	}

	public void increment() {
		if (num < max - 1)
			num++;
		if (num == max)
			num = 0;
		refresh();
	}

	public void decrement() {
		if (num > 0)
			num--;
		refresh();
	}

	public void load() {
		cdtPersodao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
		notedao = DAOFactory.getDAO(DAO.NOTE_EXAM);

		cdtPersodao.loadExam(examen);
		// On recupère les listes d'élèves et de note
		SalleGenerator gen = new SalleGenerator(examen);
		eleves = gen.getList(cdtPersodao.getListObt());

		if (setNoteClasse == true) {
			tfNote1.setVisible(true);
			lbDevoir.setVisible(true);
		} else {
			tfNote1.setVisible(false);
			lbDevoir.setVisible(false);
		}

		if (setNoteCompo == true) {
			tfNote2.setVisible(true);
			lbExamen.setVisible(true);
		} else {
			tfNote2.setVisible(false);
			lbExamen.setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (num == 0) {
			bPrecedent.setEnabled(false);
		}

		if (num == max) {
			bSuivant.setEnabled(false);
		}

		if (source == bSuivant) {
			bPrecedent.setEnabled(true);
			increment();
			refresh();
		}

		if (source == bPrecedent) {
			bPrecedent.setEnabled(true);
			decrement();
			refresh();
		}

		if (source == bSave) {
			doValider();
		}

		if (source == bQuitter) {
			close();
		}
	}

	private void doValider() {
		bPrecedent.setEnabled(true);

		Note note = null;
		try {
			note = new Note(currentEleve.getCodeInfo(), "", "", getNote1(),
					getNote2());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.setExamen(examen);
		model.setMatiere(matiere);

		model.setData(note);
		controler.setExamen(examen);

		controler.setData(note);

		controler.valider();

		if (controler.getWellDonne()) {
			increment();
			refresh();
		}
	}

	public void windowActivated(WindowEvent arg0) {
		num = 0;
		bPrecedent.setEnabled(false);
		bSuivant.setEnabled(true);
	}

	public void update() {
		System.out.println("Je suis mis ajour loool");
		try {
			Thread.sleep(500);
			refresh();
			if (tfNote1.isVisible() == true) {
				tfNote1.requestFocusInWindow();
			} else {
				tfNote2.requestFocusInWindow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		Component obj = (Component) e.getSource();
		if (obj instanceof JTextField) {
			((JTextField) obj).selectAll();
		}
	}

	public String getNote1() {
		String not1 = tfNote1.getText();
		try {
			Double.valueOf(not1).doubleValue();
			note1str = not1;
		} catch (Exception e) {
			note1str = new String("");
			e.printStackTrace();
		}
		return note1str;
	}

	public String getNote2() {
		String not2 = tfNote2.getText();
		try {
			Double.valueOf(not2).doubleValue();
			note2str = not2;
		} catch (Exception e) {
			note2str = new String("");
			e.printStackTrace();
		}

		return note2str;
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
		notedao.loadExam(matiere, examen);
		notes = notedao.getListObt();
		max = eleves.size();

		// System.out.println("====================>>>" + eleves.size());

		this.setTitle("Notes de: " + matiere);

		if (num < max)
			currentEleve = eleves.get(num);

		String nom = currentEleve.getNom();
		String prenom = currentEleve.getPrenom();

		//
		lbNom.setText(nom + "   " + prenom);
		lbIntEx.setText(examen);
		lbMatiere.setText(matiere);
		lbSalle.setText("Salle: " + currentEleve.getSalle());
		lbNum.setText(currentEleve.getNumOrdre() + "/"
				+ currentEleve.getEffSalle());

		// permet d'afficher les notes de l'élève courant
		maxNote = notes.size();
		if (num < maxNote) {
			Note note = notes.get(num);

			String valeur1 = note.getNote3str();
			String valeur2 = note.getNote4str();

			tfNote1.setText(valeur1);
			tfNote2.setText(valeur2);
		}

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10)
			doValider();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
