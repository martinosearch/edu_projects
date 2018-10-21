package agent;

import graphicsModel.MartFocusManager;
import graphicsModel.MartFrame;
import graphicsModel.MartLabel;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
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
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Avancer;
import abstractObject.AbstractControler;
import annee.ChooserDate;

import com.mxrck.autocompleter.TextAutoCompleter;

import componentFactory.MartButton;

public class AjouterAgent extends MartFrame {
	private static AjouterAgent instance;
	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Font police2 = new Font("courrier new", Font.BOLD, 14);

	private JPanel panEtatCivil = new JPanel();
	private JPanel panInscription = new JPanel();

	// des pane vide
	private JPanel panContact = new JPanel();
	private JTabbedPane tabPan = new JTabbedPane();
	private JLabel lbNom = new JLabel("Nom *");
	private JLabel lbPrenom = new JLabel("Prénom *");
	private JLabel lbSexe = new JLabel("Sexe *");
	private JLabel lbDateNais = new JLabel("Date de Naissance");
	private JLabel lbDateEntree = new JLabel("Date d'Entrée");
	private JLabel lbDateSortie = new JLabel("Date de Sortie");
	private JLabel lbTelEns = new JLabel("Téléphone");
	private JLabel lbEmail = new JLabel("E-mail");
	private JLabel lbPersonnePrev = new JLabel("Personne à prévenir");
	private JLabel lbTelPersonnePrev = new JLabel("Téléphone de la personne");

	public JTextField tfNom = new JTextField();
	public JTextField tfPrenom = new JTextField();
	public ChooserDate tfDateNais = new ChooserDate();
	public ChooserDate tfDateEntree = new ChooserDate();
	public ChooserDate tfDateSortie = new ChooserDate();
	public JTextField tfTelEns = new JTextField();
	public JTextField tfEmailEns = new JTextField();
	public JTextField tfPersoPrev = new JTextField();
	public JTextField tfTelPerso = new JTextField();

	public JComboBox cbSexe = new JComboBox();
	public JComboBox cbClasse = new JComboBox();

	private Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);
	private Agent agent;
	private MartButton bSave = new MartButton().getSauvegarder();
	private MartButton bPhoto = new MartButton().getPhoto();

	public AjouterAgent() {
		setSize(SMALL_FRAME);
		setTitle("Enseigant");
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
		setToolBarVertical();

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	public static AjouterAgent getInstance() {
		if (instance == null) {
			instance = new AjouterAgent();
		}
		return instance;
	}

	private void initComponent() {
		// pour la barre d'outils
		barreOutilsV.add(bSave);
		barreOutilsV.add(bPhoto);
		addListeners();

		// pour le sexe
		cbSexe.addItem("M");
		cbSexe.addItem("F");

		try {
			tfDateNais.setEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			tfDateSortie.setEmpty();
		} catch (Exception e) {
			e.printStackTrace();
		}

		tfNom.setPreferredSize(dim1);
		tfPrenom.setPreferredSize(dim1);
		tfDateNais.setPreferredSize(dim1);
		tfDateEntree.setPreferredSize(dim1);
		tfDateSortie.setPreferredSize(dim1);
		tfTelEns.setPreferredSize(dim1);
		tfEmailEns.setPreferredSize(dim1);
		tfPersoPrev.setPreferredSize(dim1);
		tfTelPerso.setPreferredSize(dim1);

		cbSexe.setPreferredSize(dim1);
		cbClasse.setPreferredSize(dim1);

		tfNom.setFont(police1);
		tfPrenom.setFont(police1);
		tfDateNais.setFont(police1);
		tfDateEntree.setFont(police1);
		tfDateSortie.setFont(police1);
		tfTelEns.setFont(police1);
		tfEmailEns.setFont(police1);
		tfPersoPrev.setFont(police1);
		tfTelPerso.setFont(police1);

		cbSexe.setFont(police1);
		cbClasse.setFont(police1);

		// on remplit les cambobox
		load();
		refresh();

		// ajout aux conteneur
		panEtatCivil.setLayout(new FlowLayout(FlowLayout.LEFT));
		panEtatCivil.add(lbNom);
		panEtatCivil.add(tfNom);
		panEtatCivil.add(lbPrenom);
		panEtatCivil.add(tfPrenom);
		panEtatCivil.add(lbSexe);
		panEtatCivil.add(cbSexe);
		panEtatCivil.add(lbDateNais);
		panEtatCivil.add(tfDateNais);

		panInscription.setLayout(new FlowLayout(FlowLayout.LEFT));
		panInscription.add(lbDateEntree);
		panInscription.add(tfDateEntree);
		panInscription.add(lbDateSortie);
		panInscription.add(tfDateSortie);

		panContact.setLayout(new FlowLayout(FlowLayout.LEFT));
		panContact.add(lbTelEns);
		panContact.add(tfTelEns);
		panContact.add(lbEmail);
		panContact.add(tfEmailEns);
		panContact.add(lbPersonnePrev);
		panContact.add(tfPersoPrev);
		panContact.add(lbTelPersonnePrev);
		panContact.add(tfTelPerso);

		// le conteneur des rangés
		tabPan.add("Etat Civil", panEtatCivil);
		tabPan.add("Inscription", panInscription);
		tabPan.add("Contact", panContact);

		// gestion de l'ordre de tabulation
		MartFocusManager fmanager = new MartFocusManager();
		fmanager.addComponent(tfNom, 1);
		fmanager.addComponent(tfPrenom, 2);
		fmanager.addComponent(cbSexe, 3);
		fmanager.addComponent(tfDateNais, 4);

		MartFocusManager fmanager2 = new MartFocusManager();
		fmanager2.addComponent(tfTelEns, 7);
		fmanager2.addComponent(tfEmailEns, 8);
		fmanager2.addComponent(tfPersoPrev, 9);
		fmanager2.addComponent(tfTelPerso, 10);

		setFocusTraversalPolicy(fmanager);

		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.add(tabPan, BorderLayout.CENTER);
		container.revalidate();
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			doValider();
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
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			doValider();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void doValider() {
		if (agent == null) {
			agent = new Agent();
		}

		agent.setNom(getNom());
		agent.setPrenom(getPrenom());
		agent.setSexe(getSexe());
		agent.setDateNais(getDateNais());
		agent.setDateEntree(getDateEntree());
		agent.setDateSortie(getDateSortie());
		agent.setTel(getTelEns());
		agent.setEmail(getEmailEns());
		agent.setPerso(getPersoPrev());
		agent.setTelPerso(getTelPerso());

		agent.setPrimaryKey(agent.getNom() + agent.getPrenom());

		AgentModel model = new AgentModel();
		AbstractControler controler = new AgentControler(model);
		model.setData(agent);
		controler.setData(agent);
		model.addObserver((Observer) getParent());

		controler.valider();
		close();
	}

	// les accesseurs
	public String getNom() {
		return tfNom.getText();
	}

	public String getPrenom() {
		return tfPrenom.getText();
	}

	public String getSexe() {
		return (String) cbSexe.getSelectedItem();
	}

	public DateTime getDateNais() {
		return tfDateNais.getDate();

	}

	public DateTime getDateEntree() {
		return tfDateEntree.getDate();
	}

	public DateTime getDateSortie() {
		return tfDateSortie.getDate();
	}

	public String getTelEns() {
		return tfTelEns.getText();
	}

	public String getEmailEns() {
		return tfEmailEns.getText();
	}

	public String getPersoPrev() {
		return tfPersoPrev.getText();
	}

	public String getTelPerso() {
		return tfTelPerso.getText();
	}

	public void loadAgent(Agent ag) {
		agent = ag;
		// on affiche les références de l'élève
		tfNom.setText(agent.getNom());
		tfPrenom.setText(agent.getPrenom());
		tfDateNais.setDate(agent.getDateNais());
		tfDateEntree.setDate(agent.getDateEntree());
		tfDateSortie.setDate(agent.getDateSortie());

		tfTelEns.setText(agent.getTel());
		tfPersoPrev.setText(agent.getPersoPrev());
		tfTelPerso.setText(agent.getTelPerso());
		cbSexe.setSelectedItem(agent.getSexe());
	}

	public void reset() {
		loadAgent(new Agent());
	}
}
