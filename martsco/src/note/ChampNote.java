package note;

import eleve.EleveClasse;
import graphicsModel.FrameIcon;
import graphicsModel.MartFocusManager;
import graphicsModel.MartFrame;
import graphicsModel.MartLabel;
import graphicsModel.MartList;
import graphicsModel.MyFrame;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import componentFactory.MartButton;
import matiere.MatiereProg;
import progress.Avancer;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Decoupage;
import classe.Classe;
import classe.PanChooseEleveClasse;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;

public class ChampNote extends MartFrame implements Observer {

	private JPanel container = new JPanel();
	private JPanel panReference = new JPanel();
	private JPanel panReferenceMatiere = new JPanel();
	private JPanel panChamp = new JPanel();
	private JPanel panInfo = new JPanel();
	private JPanel panNum = new JPanel();

	private JLabel lbNom = new JLabel("Nom et Prénoms");
	private JLabel lbSexe = new JLabel("Classe");
	private JLabel lbInterro1 = new JLabel("Intérro1:");
	private JLabel lbInterro2 = new JLabel("Intérro2:");
	private JLabel lbDevoir = new JLabel("Devoir:");
	private JLabel lbCompo = new JLabel("Compo:");
	private JLabel lbNum = new JLabel("1");

	protected JTextField tfNote1 = new JTextField();
	protected JTextField tfNote2 = new JTextField();
	protected JTextField tfNote3 = new JTextField();
	protected JTextField tfNote4 = new JTextField();

	private Dimension dim1 = new Dimension(100, 30);
	private Dimension dimTf = new Dimension(250, 30);

	private int trimestre = 1;
	private String note1str, note2str, note3str, note4str, matstr = "";
	public int num = 0, max = 0, max2 = 0;

	Font police1 = new Font("Times new Romam", Font.BOLD, 26);
	Font police2 = new Font("Times new Romam", Font.BOLD, 20);

	protected DAO elvclsdao, notedao;
	protected MartList<EleveClasse> eleves;
	protected MartList<Note> notes;
	protected boolean isInterro1, isInterro2, isDevoir, isCompo;
	Classe classe;
	protected String annee;
	MatiereProg matiere;
	protected MartButton bSave = new MartButton().getSauvegarder();
	protected MartButton bSuivant = new MartButton().getSuivant();
	protected MartButton bPrecedent = new MartButton().getPrecedent();
	protected MartButton bQuitter = new MartButton().getQuitter();
	private PanChooseEleveClasse panSearch;
	private EleveClasse eleve;
	private JLabel lbInfoMatiere;
	private ConfigClasse confClasse;
	private AbstractModel model;
	private AbstractControler controler;

	// constructeur
	public ChampNote(MatiereProg mat, Classe cl, int trim, String an) {
		classe = cl;
		trimestre = trim;
		annee = an;
		matiere = mat;
		load();

		this.setSize(MEDIUM_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		this.setResizable(false);
		this.setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getNotes(classe.getIntitule() + "/ "
				+ getTrimestreStr()));

		initComponent();
		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	// initialisaton des contenus
	public void initComponent() {
		model = new NoteModel();
		controler = new NoteControler(model);

		model.setAnnee(annee);
		model.setMatiere(matiere.getIntitule());
		model.setTrimestre(trimestre);
		model.setClasse(classe.getIntitule());

		controler.setClasse(classe.getIntitule());

		// pour la barre d'outils
		barreOutilsV.add(bSave);
		barreOutilsV.add(bPrecedent);
		barreOutilsV.add(bSuivant);
		barreOutilsV.add(bQuitter);

		panSearch = new PanChooseEleveClasse(classe.getIntitule(), annee);
		panSearch.addListener(this);

		barreOutils.add(panSearch);
		addListeners();

		lbNom.setFont(police1);
		lbSexe.setFont(police2);
		lbInterro1.setFont(police2);
		lbInterro2.setFont(police2);
		lbDevoir.setFont(police2);
		lbCompo.setFont(police2);

		tfNote1.setFont(police2);
		tfNote2.setFont(police2);
		tfNote3.setFont(police2);
		tfNote4.setFont(police2);

		lbNom.setForeground(Color.BLACK);
		lbSexe.setForeground(Color.black);
		tfNote3.setForeground(Color.BLUE);
		tfNote4.setForeground(Color.RED);

		tfNote1.addFocusListener(this);
		tfNote2.addFocusListener(this);
		tfNote3.addFocusListener(this);
		tfNote4.addFocusListener(this);

		tfNote1.addKeyListener(this);
		tfNote2.addKeyListener(this);
		tfNote3.addKeyListener(this);
		tfNote4.addKeyListener(this);

		// Références de la matière
		panReferenceMatiere.setBorder(BorderFactory
				.createTitledBorder("Références Matière"));
		panReferenceMatiere.setPreferredSize(new Dimension(
				getSize().width - 120, 100));
		panReferenceMatiere.setLayout(new BorderLayout());
		lbInfoMatiere = new MartLabel();
		lbInfoMatiere.setText("<table><tr><td>Matiere: </td><td>"
				+ matiere.getIntitule() + "</td></tr>"
				+ "<tr><td>Type: </td><td>" + matiere.getType() + "</td></tr>"
				+ "<tr><td>Chargé: </td><td>" + matiere.getCharge()
				+ "</td></tr></table>");
		panReferenceMatiere.add(lbInfoMatiere, BorderLayout.CENTER);

		panInfo.setLayout(new GridLayout(3, 1, 10, 10));
		panInfo.add(lbNom);
		panInfo.add(lbSexe);
		panNum.setLayout(new GridLayout(1, 1));

		// Références de l'élève
		panReference.setBorder(BorderFactory
				.createTitledBorder("Références Elève"));

		lbNum.setFont(new Font("Times new Romam", Font.BOLD, 40));
		lbNum.setForeground(Color.blue);
		panNum.add(lbNum);

		panReference.setLayout(new BorderLayout());
		panReference
				.setPreferredSize(new Dimension(getSize().width - 120, 100));
		panReference.add(panInfo, BorderLayout.CENTER);
		panReference.add(panNum, BorderLayout.EAST);

		// ajout des champs de saisi
		panChamp.setBorder(BorderFactory.createTitledBorder("Notes de l'élève"));
		panChamp.setLayout(new GridLayout(4, 2, 10, 10));
		panChamp.setPreferredSize(new Dimension(200, 200));
		panChamp.add(lbInterro1);
		panChamp.add(tfNote1);
		panChamp.add(lbInterro2);
		panChamp.add(tfNote2);
		panChamp.add(lbDevoir);
		panChamp.add(tfNote3);
		panChamp.add(lbCompo);
		panChamp.add(tfNote4);

		// conteneurs des conteneurs
		container.setLayout(new FlowLayout(FlowLayout.LEFT));
		container.add(panReferenceMatiere);
		container.add(panReference);
		container.add(panChamp);

		/*
		 * MartFocusManager mng = new MartFocusManager();
		 * mng.addComponent(tfNote1, 1); mng.addComponent(tfNote2, 2);
		 * mng.addComponent(tfNote3, 3); mng.addComponent(tfNote4, 4);
		 * 
		 * setFocusTraversalPolicy(mng);
		 */
	}

	// exécute la requête
	public void update() {
		System.out.println("Je suis mis ajour loool");
		try {
			Thread.sleep(500);
			refresh();

			if (tfNote1.isVisible() == true) {
				tfNote1.requestFocusInWindow();
			} else if (tfNote2.isVisible() == true) {
				tfNote2.requestFocusInWindow();
			} else if (tfNote3.isVisible() == true) {
				tfNote3.requestFocusInWindow();
			} else {
				tfNote4.requestFocusInWindow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void increment() {
		if (num < max - 1)
			num++;
		EleveClasse elv = eleves.get(num);
		setEleve(elv);
		update();
	}

	public void decrement() {
		if (num > 0)
			num--;
		EleveClasse elv = eleves.get(num);
		setEleve(elv);
		update();
	}

	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (num == 0) {
			bPrecedent.setEnabled(false);
		}

		if (num == max - 2) {
			bSuivant.setEnabled(false);
		}

		if (source == bSuivant) {
			bPrecedent.setEnabled(true);
			increment();
			refresh();
		}

		if (source == bSave) {
			doValider();
		}

		if (source == bPrecedent) {
			bSuivant.setEnabled(true);
			decrement();
			refresh();
		}

		if (source == bQuitter) {
			num = 0;
			this.dispose();
		}

		if (source == panSearch.getValider()) {
			setResultSearch();
		}
	}

	private void doValider() {
		bPrecedent.setEnabled(true);

		Note note = null;
		try {
			note = new Note(eleve.getCodeInfo(), getNote1(), getNote2(),
					getNote3(), getNote4());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.setData(note);
		controler.setData(note);
		controler.valider();

		if (controler.getWellDonne()) {
			increment();
		}
	}

	@Override
	public void load() {
		// On recupére les listes d'élèves et de note
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		notedao = DAOFactory.getDAO(DAO.NOTE);

		elvclsdao.load(new String(), classe.getIntitule(), trimestre, annee);
		eleves = elvclsdao.getList();
		max = eleves.size();
		eleve = eleves.get(0);
		setEleve(eleve);
	}

	public void refresh() {
		control();
		// affichage du titre
		matstr = matiere.getIntitule() + "  " + getTrimestreStr();
		lbSexe.setText("Sexe: " + eleve.getSexe());
		afficherNum();

		// on définit le titre de la fenêtre
		this.setTitle(matstr);
		// rapport de saisie
	}

	private void afficherNum() {
		lbNum.setText(String.valueOf(num + 1).toString() + "/" + max);
	}

	private void setEleve(EleveClasse el) {
		eleve = el;
		String nom = eleve.getNom();
		String prenom = eleve.getPrenom();

		lbNom.setText(nom + "   " + prenom);

		// permet d'afficher les notes de l'élève courant
		notedao.load(matiere.getIntitule(), classe.getIntitule(), trimestre,
				annee);
		notes = notedao.getListObt();
		Note note = notes.find(eleve.getCodeInfo());
		tfNote1.setText(note.getNote1str());
		tfNote2.setText(note.getNote2str());
		tfNote3.setText(note.getNote3str());
		tfNote4.setText(note.getNote4str());
	}

	private void control() {
		// *******************************************************
		// afficher le champs
		if (isInterro1() == true) {
			tfNote1.setVisible(true);
			lbInterro1.setVisible(true);
		} else {
			tfNote1.setVisible(false);
			lbInterro1.setVisible(false);
		}

		if (isInterro2() == true) {
			tfNote2.setVisible(true);
			lbInterro2.setVisible(true);
		} else {
			tfNote2.setVisible(false);
			lbInterro2.setVisible(false);
		}

		if (isDevoir() == true) {
			tfNote3.setVisible(true);
			lbDevoir.setVisible(true);
		} else {
			tfNote3.setVisible(false);
			lbDevoir.setVisible(false);
		}

		if (isCompo() == true) {
			tfNote4.setVisible(true);
			lbCompo.setVisible(true);
		} else {
			tfNote4.setVisible(false);
			lbCompo.setVisible(false);
		}
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	private String getTrimestreStr() {
		String trim = null;
		try {
			confClasse = new ConfigClasse(classe, annee);
			Decoupage dec = confClasse.persoClasse.getDecoupage();
			dec.setSection(trimestre);
			trim = dec.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(trim);
		return trim;
	}

	private boolean isCompo() {
		return isCompo;
	}

	private boolean isDevoir() {
		// TODO Auto-generated method stub
		return isDevoir;
	}

	private boolean isInterro2() {
		// TODO Auto-generated method stub
		return isInterro2;
	}

	private boolean isInterro1() {
		// TODO Auto-generated method stub
		return isInterro1;
	}

	public void setInterro1(boolean isInterro1) {
		this.isInterro1 = isInterro1;
		control();
	}

	public void setInterro2(boolean isInterro2) {
		this.isInterro2 = isInterro2;
		control();
	}

	public void setDevoir(boolean isDevoir) {
		this.isDevoir = isDevoir;
		control();
	}

	public void setCompo(boolean isCompo) {
		this.isCompo = isCompo;
		control();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Component source = (Component) e.getSource();

		if (e.getKeyCode() == 10) {
			if (source == panSearch.getComponent()) {
				if (panSearch.itemExists()) {
					setResultSearch();
				}
			} else {
				doValider();
			}

		}

	}

	private void setResultSearch() {
		eleve = panSearch.getSelectedItem();
		int i = 0;
		for (EleveClasse elv : eleves) {
			if (elv.decrisToi().equals(eleve.decrisToi())) {
				setEleve(eleve);
				break;
			}
			i++;
		}
		num = i;
		afficherNum();
	}

	// les accesseurs
	public EleveClasse getCurrentEleve() {
		return eleve;
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

		System.out.println("la note1**********************" + not1);
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

		System.out.println("la note2**********************" + not2);
		return note2str;
	}

	public String getNote3() {
		String not3 = tfNote3.getText();
		try {
			Double.valueOf(not3).doubleValue();
			note3str = not3;
		} catch (Exception e) {
			note3str = new String("");
			e.printStackTrace();
		}

		System.out.println("la note3**********************" + not3);
		return note3str;
	}

	public String getNote4() {
		String not4 = tfNote4.getText();
		try {
			Double.valueOf(not4).doubleValue();
			note4str = not4;
		} catch (Exception e) {
			note4str = new String("");
			e.printStackTrace();
		}

		System.out.println("la note4**********************" + not4);
		return note4str;
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

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

	public void windowActivated(WindowEvent arg0) {
		tfNote1.requestFocusInWindow();
		bPrecedent.setEnabled(false);
		bSuivant.setEnabled(true);
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
	public void focusGained(FocusEvent e) {
		Component source = (Component) e.getSource();
		if (source instanceof JTextField) {
			((JTextField) source).selectAll();
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Component source = (Component) e.getSource();
		if (source == tfNote4) {
			tfNote1.requestFocusInWindow();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
