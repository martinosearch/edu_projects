package accueil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import agent.Agent;
import componentFactory.ItemsAdmin;
import componentFactory.ItemsApplication;
import componentFactory.ItemsApropos;
import componentFactory.ItemsClasse;
import componentFactory.ItemsCompta;
import componentFactory.ItemsEcole;
import componentFactory.ItemsExamen;
import componentFactory.MartButton;
import componentFactory.MartMenu;
import componentFactory.MenuFactory;
import connection.DAO;
import connection.DAOFactory;
import connection.MartConnection;
import function.Constance;
import function.GeneralVoid;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartLabel;
import graphicsModel.PanPub;
import help.Help;
import progress.Avancer;
import progress.GreatUser;
import rapportBulletin.HistoManager;
import security.CurrentUser;
import security.Login;
import security.Register;
import security.Soldier;
import security.User;

public class AccueilSCO extends MartFrame {
    public static AccueilSCO instance;

    private Soldier sold;

    private String annee = "";
    private GeneralVoid generalVoid = new GeneralVoid();

    private MartButton bQuitter = new MartButton().getQuitter();
    private MartButton bHelp = new MartButton().getHelp();
    private MartButton bRestart = new MartButton().getRedemarrer();
    private MartButton bCalculatrice = new MartButton().getCalculatrice();
    private JMenu ecole = new MenuFactory().getEcole();
    private JMenu classe = new MenuFactory().getClasse();
    private JMenu examen = new MenuFactory().getExamen();
    private JMenu compta = new MenuFactory().getCompta();
    private JMenu admin = new MenuFactory().getAdmin();
    private JMenu application = new MenuFactory().getApplication();
    private JMenu apropos = new MenuFactory().getApropos();

    static DAO andao;
    private DAO agentdao;

    private JPanel panInfoBase;

    private MartLabel lbInfoAnnee;

    private MartLabel lbInfoBase;

    private PanPub panInfo;

    // le constructeur
    public AccueilSCO() {
	this.setSize(1000, 600);
	this.setTitle("LOGICIEL MartSCO");
	this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setResizable(true);
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	setPub();
	setToolBar();
	setIcone(new FrameIcon().getAccueil());

	andao = DAOFactory.getDAO(DAO.ANNEE);

	new HistoManager();
	sold = Soldier.getInstance();

	// on initialise le conteneur ainsi que tous les composants
	initComponent();

	// System.out.println("#######################################"
	// + sold.isfistTime());

	if (sold.isfistTime() == true) {
	    askForResgister();
	    // System.out.println("Nous somme ici plutôt");
	}

	else {
	    // System.out.println("Nous somme là: " + sold.isRegister());

	    if (sold.isRegister() == true) {
		Login login = Login.getInstance();

		login.setAction(new Thread(new Runnable() {
		    @Override
		    public void run() {
			getInstance().setVisible(true);

			// creation de dossiers importants
			new GeneralVoid().makeDir();
		    }
		}));

		login.setVisible(true);
	    } else {
		JOptionPane.showMessageDialog(null, "La clé d'enrégistrement n'est pas valide.",
			"ERREUR D'ENREGISTREMENT", JOptionPane.ERROR_MESSAGE);
		sold.setFirstTime(true);
		getInstance().setVisible(true);
	    }
	}

	this.setJMenuBar(menuBar);
	this.getContentPane().add(container, BorderLayout.CENTER);
    }

    private void askForResgister() {
	JPanel panReg = new JPanel();
	JButton butReg = new JButton("Enrégistrer le logiciel maintenant");

	panReg.add(butReg);
	butReg.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		Register rg = new Register();
		rg.setVisible(true);
	    }
	});

	this.getContentPane().add(panReg, BorderLayout.SOUTH);
    }

    // methode permettant l'initialisation des composant
    public void initComponent() {
	// pour la barre d'outils
	barreOutils.add(bQuitter);
	barreOutils.add(bHelp);
	barreOutils.add(bRestart);
	barreOutils.add(bCalculatrice);

	addListeners();

	// définition des ménus
	menuBar.add(ecole);
	menuBar.add(classe);
	menuBar.add(examen);
	menuBar.add(compta);
	menuBar.add(admin);
	menuBar.add(application);
	menuBar.add(apropos);

	panInfoBase = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	panInfoBase.setBackground(MartFrame.MENU_COLOR);

	lbInfoBase = new MartLabel();
	lbInfoBase.setText("[] BD: " + MartConnection.getDataBase());
	lbInfoBase.setForeground(MartMenu.FORE_COLOR);

	lbInfoAnnee = new MartLabel();
	lbInfoAnnee.setText(Constance.getAnnee());
	lbInfoAnnee.setForeground(MartMenu.FORE_COLOR);

	panInfoBase.add(lbInfoBase);
	panInfoBase.add(new JLabel());// juste à titre d'espace
	panInfoBase.add(lbInfoAnnee);
	menuBar.add(panInfoBase);

	// init elements de menus
	ecole.add(new ItemsEcole());
	classe.add(new ItemsClasse());
	examen.add(new ItemsExamen());
	compta.add(new ItemsCompta());
	admin.add(new ItemsAdmin());
	application.add(new ItemsApplication());
	apropos.add(new ItemsApropos());

	// les conteneurs
	container = new JPanel();
	container.setLayout(new BorderLayout());
	container.setBackground(new Color(65, 143, 241));

	panInfo = new PanPub();
	panInfo.setPreferredSize(new Dimension(getSize().width, getSize().height / 4 + 30));

	panInfo.setMyLogo();
	panInfo.setMyContact();
	panInfo.setAvis();

	container.add(panInfo, BorderLayout.SOUTH);
	container.revalidate();
    }

    /*
     * private void setHistorique() { if (sold.isfistTime() != true) {
     * ArrayList<Histo> histos = new ArrayList<Histo>(); try { histos =
     * histoMng.getList();
     * 
     * for (final Histo his : histos) { DateTime date = his.getDate(); int annee =
     * date.getYear(); int mois = date.getMonthOfYear(); int jour =
     * date.getDayOfMonth(); int hour = date.getHourOfDay(); int min =
     * date.getMinuteOfHour(); int sec = date.getSecondOfMinute();
     * 
     * JMenuItem jmi = new JMenuItem(his.getIntitule() + "( " + jour + "/" + mois +
     * "/" + annee + " " + hour + ":" + min + ":" + sec + ")");
     * 
     * final String htmlHisto = his.getHisto(); jmi.addActionListener(new
     * ActionListener() {
     * 
     * public void actionPerformed(ActionEvent arg0) { MartEditorPane editor = null;
     * if (his.getIntitule().charAt(0) == ('B')) { editor = new MartEditorPane();
     * editor.setStyle(MartStyle.BULL); } else { editor = new MartEditorPane(
     * MartEditorPane.MATHS_DOCUMENT); editor.setStyle(MartStyle.DOCUMENTS); }
     * 
     * editor.setOrientation(his.getOrientation()); editor.setHtml(htmlHisto);
     * 
     * // ******************************** // on fait appelle à l'editeur Preview
     * bsh = new Preview(editor); bsh.setHtmlExport(htmlHisto); }
     * 
     * }); historique.add(jmi); } } catch (Exception e) { e.printStackTrace(); } }//
     * FIN HISTORIQUE }
     */

    public static AccueilSCO getInstance() {
	if (instance == null) {
	    instance = new AccueilSCO();
	}

	return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	Component source = (Component) e.getSource();

	if (source == bCalculatrice) {
	    new GeneralVoid().calculatrice();
	}

	/*
	 * if (source == bSectionDesign) { try {
	 * Runtime.getRuntime().exec(Constance.getPhotoFiltre()); } catch (IOException
	 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); } }
	 * 
	 * if (source == bSectionEmploiDuTemps) { try {
	 * Runtime.getRuntime().exec(Constance.getFet()); } catch (IOException e1) {
	 * e1.printStackTrace(); } }
	 */

	if (source == bQuitter) {
	    close();
	}

	if (source == bHelp) {
	    Help help = Help.getInstance();
	    help.getType(help.USER_HELP);
	    help.setVisible(true);
	}

	if (source == bRestart) {
	    generalVoid = new GeneralVoid();
	    generalVoid.setDoRestart(true);
	    close();
	}
    }

    public String getAnnee() {
	return annee;
    }

    public void update() {
    }

    public static void main(String[] args) {
	Constance.initialize();
	getInstance();
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
	generalVoid.doSmartClosing();
    }

    public GeneralVoid getGeneralVoid() {
	return generalVoid;
    }

    public void setGeneralVoid(GeneralVoid generalVoid) {
	this.generalVoid = generalVoid;
    }

    @Override
    public void windowClosing(WindowEvent arg0) {

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
    public void windowOpened(WindowEvent e) {
	User user = CurrentUser.getInstance();
	agentdao = DAOFactory.getDAO(DAO.AGENT);

	String username;
	if (user.getLogin().equals("adm")) {
	    username = "Martin";
	} else {
	    agentdao.load();
	    username = ((Agent) agentdao.findObj(user.getCodeInfo())).decrisToi();
	}

	// System.out.println("le nom de l'utilisateur: " + username + "code: "
	// + user.getCodeInfo());

	new GreatUser(username);
	// new GeneralVoid().doSauvegardeBD();
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
    public Avancer getAvancer() {
	// TODO Auto-generated method stub
	return null;
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
    public void close() {
	dispose();
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }
}
