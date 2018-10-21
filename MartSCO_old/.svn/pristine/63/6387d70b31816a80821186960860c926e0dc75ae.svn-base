package salaire;

import ecolage.AjouterSco;
import ecolage.Operation;
import graphicsModel.FrameIcon;
import graphicsModel.InfoPanel;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import images.RessourceFinder;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Avancer;
import tableComponent.MartTable;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import agent.Agent;
import agent.PanChooseAgent;

import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class DefinirSalaire extends OptionEditorFrame implements Observer {
	private DAO virsaldao;
	private InfoPanel panIdAgent;

	private String annee;
	private Agent agent;
	private Font police = new Font("courrier new", Font.BOLD, 16);
	private AbstractControler controler;
	private AbstractModel model;
	private MartList<AgentSalaire> listSalaireAgent;
	private MartTable tableRappel;
	private Color BACK_COLOR = Color.WHITE;
	private AgentSalaire salAgent;
	private PanChooseAgent panSearch;

	private MartButton bReinitialiser = new MartButton().getReinitialiser();
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bQuitter = new MartButton().getQuitter();
	private Component bModifier = new MartButton().getModifier();
	private Component bSupprimer = new MartButton().getSupprimer();
	private JPanel panSituation;

	public DefinirSalaire() {
		this.setTitle("DEFINIR LE SALAIRE");
		this.setSize(MartFrame.MEDIUM_FRAME);
		this.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getSalaire());

		initComponent();
	}

	private void initComponent() {
		panSearch = new PanChooseAgent();
		panSearch.addListener(this);

		barreOutils.add(panSearch);

		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bModifier);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bReinitialiser);

		addListeners();

		model = new SalaireModel();
		controler = new SalaireControler(model);
		model.addObserver(this);

		load();

		panSituation = new JPanel(new BorderLayout());
		panSituation.setBackground(Color.BLUE);
	}

	public static void main(String[] args) {
		DefinirSalaire n = new DefinirSalaire();
		n.setAnnee("2016-2017");
		n.setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAjouter) {
			if (agent != null) {
				String[] just = { AgentSalaire.SAL, AgentSalaire.SAL_HEURE_COL,
						AgentSalaire.SAL_HEURE_LEG, AgentSalaire.SAL_HEURE_LET,
						AgentSalaire.PRIM_ANC, AgentSalaire.PRIM_RESP,
						AgentSalaire.PRIM_HEURE_SUP, AgentSalaire.PRIM_LOG,
						AgentSalaire.AUTRES };

				final AjouterSco chooser = new AjouterSco(AjouterSco.VERSEMENT,
						just);

				chooser.setAction(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Operation vers = new Salaire(agent.getCodeInfo(),
								chooser.getSomme());
						vers.setJustification(chooser.getJustification());

						// salAgent est déja initialiser au moment de
						// l'affichage
						salAgent.setCodeInfo(agent.getCodeInfo());
						salAgent.setNom(agent.getNom());
						salAgent.setPrenom(agent.getPrenom());
						salAgent.setSexe(agent.getSexe());
						salAgent.setOperationActuel(vers);

						controler.setData(salAgent);
						model.setData(salAgent);
						model.setAnnee(annee);

						controler.valider();

						chooser.close();
					}
				});

				chooser.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "Aucun agent n'est chargé");
			}
		}

		if (source == bReinitialiser) {
			panIdAgent.reset();
		}

		if (source == panSearch.getValider()) {
			refresh();
		}

		if (source == bQuitter) {
			close();
		}
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() {
		virsaldao = DAOFactory.getDAO(DAO.SALAIRE);
	}

	@Override
	public void refresh() {
		agent = panSearch.getSelectedItem();

		afficherAgent();
	}

	public void afficherAgent() {
		refreshItems();

		panIdAgent = new InfoPanel();
		panIdAgent.setAgent(agent);
		container.add(panIdAgent, BorderLayout.NORTH);

		// affichage des operation antérieures
		virsaldao.load("", "", 0, annee);
		listSalaireAgent = virsaldao.getListObt();

		salAgent = listSalaireAgent.find(agent.getCodeInfo());
		if (salAgent == null) {
			salAgent = new AgentSalaire(agent.getCodeInfo());
		}

		try {
			salAgent.setSexe(agent.getSexe());

			// l'affichage n'est faite que si l'élève existe
			setSalaire(salAgent);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"L'élève choisi n'est pas inscrit cette année");

		}
	}

	// la methode qui crée le tableau des opération antérieures
	private void setSalaire(AgentSalaire salAgent) {
		MartList<Operation> listOp = new MartList<Operation>();
		try {

			listOp = salAgent.getlisteOperation();
			System.out.println("===========>>Code eleve: "
					+ salAgent.getCodeInfo() + " Size op= " + listOp.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// calculs
		SalaireComputer computer = new SalaireComputer();
		computer.setAnnee(annee);
		computer.setSalaireAgent(salAgent);

		Object[][] data = new Object[listOp.size()][3];
		String[] title = new String[3];

		int i = 0;
		for (Operation op : listOp) {
			DateTime date = op.getDate();
			System.out.println("la date: " + date);
			DateTimeFormatter formatter = DateTimeFormat.mediumDateTime();
			formatter.withZoneUTC();

			OptionItem item = new OptionItem(new RessourceFinder().getBillet(),
					"<html>" + "<div>Date: " + formatter.print(date) + "</div>"
							+ "<div>Montant: " + op.getMontant() + "</div>"
							+ "<div>Justification: " + op.getJustification()
							+ "</div>" + "</html>");

			addItem(item);

			i++;
		}

		JLabel lbAnterieur = new JLabel("Détails du Salaire");
		JLabel lbSituation = new JLabel();

		lbAnterieur.setFont(police);
		lbSituation.setFont(police);

		lbSituation.setForeground(Color.WHITE);

		lbSituation.setText("<html><table><tr><td>Salaire: "
				+ computer.getSalaire() + "</td><td> "
				+ "</td></tr><tr><td>Total Prime: " + computer.getPrime()
				+ "</td><td></td></tr>" + "<tr><td>Salaire Brut: "
				+ computer.getSalaireBrut() + "</td><td></td></tr>"
				+ "</table></html>");

		lbSituation.revalidate();

		panSituation.removeAll();
		panSituation.add(lbSituation, BorderLayout.CENTER);

		container.add(panSituation, BorderLayout.SOUTH);
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		showMessage("Versement effectué");
		refresh();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Component source = (Component) e.getSource();
		if (source == panSearch.getComponent()) {
			if (panSearch.itemExists()) {
				refresh();
			}
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

}
