package eleve;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Annee;
import annee.ChooserDate;
import classe.Classe;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import graphicsModel.MartFrame;
import images.PictureFinder;
import images.PictureManager;
import images.ShowPicture;
import interfacePerso.Observer;
import progress.Avancer;

public class AjouterEleve extends MartFrame implements Observer {
    private static AjouterEleve instance;
    private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
    private Font police2 = new Font("courrier new", Font.BOLD, 14);

    private JTabbedPane tabPan = new JTabbedPane();
    private JPanel panEtatCivil = new JPanel();
    private JPanel panInscription = new JPanel();
    private JPanel panContact = new JPanel();

    private JLabel lbAnSco = new JLabel("Année- scolaire *");
    private JLabel lbNom = new JLabel("Nom *");
    private JLabel lbPrenom = new JLabel("Prénom *");
    private JLabel lbSexe = new JLabel("Sexe *");
    private JLabel lbClasse = new JLabel("Classe *");
    private JLabel lbDateNais = new JLabel("Date de naissance");
    private JLabel lbDateEntree = new JLabel("Date d'inscription");
    private JLabel lbDateSortie = new JLabel("Date de sortie");
    private JLabel lbPersoPrev = new JLabel("Pers. à Prévenir");
    private JLabel lbProfessionPersoPrev = new JLabel("Profession");
    private JLabel lbTelPersoPrev = new JLabel("Tel");

    private JTextField tfNom = new ThisTextField();
    private JTextField tfPrenom = new ThisTextField();
    private JTextField tfPersoPrev = new ThisTextField();
    private JTextField tfProfessionPersoPrev = new ThisTextField();
    private JTextField tfTelPersoPrev = new ThisTextField();

    private JComboBox cbSexe = new ThisComboBox();
    private JComboBox cbClasse = new ThisComboBox();
    private JComboBox cbAnnee = new ThisComboBox();

    private ThisChooserDate tfDateNais = new ThisChooserDate();
    private ThisChooserDate tfDateEntree = new ThisChooserDate();
    private ThisChooserDate tfDateSortie = new ThisChooserDate();

    private MartButton bSave = new MartButton().getSauvegarder();
    private MartButton bPhoto = new MartButton().getPhoto();
    private Eleve eleve;
    private DAO elvdao;
    private DAO clsdao;
    private DAO andao;
    public ShowPicture show;

    public AjouterEleve() {
	setSize(SMALL_FRAME);
	setTitle("Eleve");
	setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
	setToolBarVertical();

	initComponent();

	getContentPane().add(container, BorderLayout.CENTER);
    }

    private void initComponent() {
	// pour la barre d'outils
	barreOutilsV.add(bSave);
	barreOutilsV.add(bPhoto);
	addListeners();

	// pour le sexe
	cbSexe.addItem("M");
	cbSexe.addItem("F");

	// on remplit les cambobox
	load();
	refresh();

	// ajout aux conteneur
	panEtatCivil.setLayout(new FlowLayout(FlowLayout.LEFT));
	panEtatCivil.add(lbAnSco);
	panEtatCivil.add(cbAnnee);
	panEtatCivil.add(lbNom);
	panEtatCivil.add(tfNom);
	panEtatCivil.add(lbPrenom);
	panEtatCivil.add(tfPrenom);
	panEtatCivil.add(lbSexe);
	panEtatCivil.add(cbSexe);
	panEtatCivil.add(lbClasse);
	panEtatCivil.add(cbClasse);
	panEtatCivil.add(lbDateNais);
	panEtatCivil.add(tfDateNais);

	panInscription.setLayout(new FlowLayout(FlowLayout.LEFT));
	panInscription.add(lbDateEntree);
	panInscription.add(tfDateEntree);
	panInscription.add(lbDateSortie);
	panInscription.add(tfDateSortie);

	panContact.setLayout(new FlowLayout(FlowLayout.LEFT));
	panContact.add(lbPersoPrev);
	panContact.add(tfPersoPrev);
	panContact.add(lbProfessionPersoPrev);
	panContact.add(tfProfessionPersoPrev);
	panContact.add(lbTelPersoPrev);
	panContact.add(tfTelPersoPrev);

	tabPan.add("Etat Civil", panEtatCivil);
	tabPan.add("Inscription", panInscription);
	tabPan.add("Contact", panContact);

	container = new JPanel();
	container.setLayout(new BorderLayout());
	container.add(tabPan, BorderLayout.CENTER);
	container.revalidate();
    }

    public static void main(String[] args) {
	new AjouterEleve().setVisible(true);
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

	if (source == bPhoto) {
	    PictureManager mng = new PictureManager();
	    mng.setPhotosEleve();
	    mng.setEleve(eleve);
	    mng.setParent(this);
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

    }

    @Override
    public void windowClosing(WindowEvent arg0) {
	try {
	    show.dispose();
	} catch (Exception e) {
	    e.printStackTrace();
	}
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
	elvdao = DAOFactory.getDAO(DAO.ELEVE);
	clsdao = DAOFactory.getDAO(DAO.CLASSE);
	andao = DAOFactory.getDAO(DAO.ANNEE);

	clsdao.load();
	elvdao.load();
	andao.load();

	// ajout des Item à nos combo
	ArrayList<Classe> listClasse = clsdao.getListObt();
	String[] tabClasse = new String[listClasse.size() + 1];
	tabClasse[0] = "- -";

	for (int i = 0; i < listClasse.size(); i++) {
	    tabClasse[i + 1] = (listClasse.get(i)).getIntitule();
	}

	ArrayList<Annee> listAnnee = andao.getListObt();
	String[] tabAnnee = new String[listAnnee.size()];

	for (int i = 0; i < listAnnee.size(); i++) {
	    tabAnnee[i] = (listAnnee.get(i)).getIntitule();
	}

	String[] sexe = { "", "M", "F" };

	cbSexe = new ThisComboBox(sexe);
	cbClasse = new ThisComboBox(tabClasse);
	cbAnnee = new ThisComboBox(tabAnnee);
	cbAnnee.setSelectedIndex(0);
	cbAnnee.addActionListener(this);

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
	if (eleve == null) {
	    eleve = new Eleve();
	}

	eleve.setNom(getNom());
	eleve.setPrenom(getPrenom());
	eleve.setSexe(getSexe());
	eleve.setClasse(getClasse());
	eleve.setDateNais(getDateNais());
	eleve.setDateEntree(getDateEntree());
	eleve.setDateSortie(getDateSortie());
	eleve.setPersoPrev(getPersoPrev());
	eleve.setProfessionPersoPrev(getProfessionPersoPrev());
	eleve.setAddressParent(getTelPersoPrev());

	// initialisation du primary key
	eleve.initPrimaryKey();

	AbstractModel model = new EleveModel();
	AbstractControler controler = new EleveControler(model);
	model.addObserver((Observer) getParent());

	model.setData(eleve);
	model.setAnnee(getAnnee());
	model.setClasse(getClasse());

	controler.setData(eleve);
	controler.setAnnee(getAnnee());
	model.setClasse(getClasse());

	controler.valider();
	reset();
	close();
    }

    public void reset() {
	loadEleve(new Eleve());
    }

    // les accesseurs
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

    public String getClasse() {
	return (String) cbClasse.getSelectedItem();
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

    public String getPersoPrev() {
	return tfPersoPrev.getText();
    }

    public String getProfessionPersoPrev() {
	return tfProfessionPersoPrev.getText();
    }

    public String getTelPersoPrev() {
	return tfTelPersoPrev.getText();
    }

    public String getAnnee() {
	return (String) cbAnnee.getSelectedItem();
    }

    public void loadEleve(Eleve elv) {
	eleve = elv;
	tfNom.setText(eleve.getNom());
	tfPrenom.setText(eleve.getPrenom());
	tfDateNais.setDate(eleve.getDateNais());
	tfDateEntree.setDate(eleve.getDateEntree());
	tfDateSortie.setDate(eleve.getDateSortie());
	tfPersoPrev.setText(eleve.getPersoPrev());
	tfProfessionPersoPrev.setText(eleve.getProfessionPersoPrev());
	tfTelPersoPrev.setText(eleve.getTelPersoPrev());
	cbClasse.setSelectedItem(eleve.getClasseAnnee(Constance.getAnnee()));
	cbSexe.setSelectedItem(eleve.getSexe());

	// on affiche la photo
	setPhoto();
    }

    private void setPhoto() {
	try {
	    File file = new PictureFinder().getPhotoEleve(eleve.getCodeInfo());
	    show = ShowPicture.getInstance();
	    show.setFile(file);
	    show.setVisible(true);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // classe interne
    class ThisTextField extends JTextField {
	protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);

	public ThisTextField() {
	    super();
	    setPreferredSize(dim1);
	    setFont(police1);
	}
    }

    class ThisComboBox extends JComboBox {
	protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);

	public ThisComboBox() {
	    super();
	    setPreferredSize(dim1);
	    setFont(police1);
	}

	public ThisComboBox(Object[] obj) {
	    super(obj);
	    setPreferredSize(dim1);
	    setFont(police1);
	}
    }

    class ThisChooserDate extends ChooserDate {
	protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 150, 30);

	public ThisChooserDate() {
	    super();
	    setPreferredSize(dim1);
	    setFont(police1);
	    setEmpty();
	}
    }

    public static AjouterEleve getInstance() {
	if (instance == null) {
	    instance = new AjouterEleve();
	}
	return instance;
    }

    @Override
    public void update() {
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    Thread.sleep(1000);
		    System.out.println("Je suis mis à jour§§§§§");
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	});
    }
}
