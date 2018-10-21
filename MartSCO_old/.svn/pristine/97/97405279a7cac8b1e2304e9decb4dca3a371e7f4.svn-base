package salaire;

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
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Avancer;
import rapportCompta.BullPayeModel;
import rapportCompta.RecuModel;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import agent.Agent;
import agent.PanChooseAgent;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class ChooserSalaireAgent extends OptionEditorFrame implements Observer {
	private DAO elvdao, payesaldao;
	private String annee;
	private Font police = new Font("courrier new", Font.BOLD, 16);
	private MartList<AgentSalaire> listAgentSalaire;
	private AgentSalaire agentSal;
	private PanChooseAgent panSearch;
	private String[] liste;
	private Agent agent;
	private PayerSalaire chooser;
	private InfoPanel panIdAgent;
	private MartButton bAnnulerPayement = new MartButton().getAnnulerPayement();
	private MartButton bImprimer = new MartButton().getImprimer();
	private MartButton bReinitialiser = new MartButton().getReinitialiser();
	private Component bAjouter = new MartButton().getAjouter();
	private Component bQuitter = new MartButton().getQuitter();
	private JPanel panSituation;
	private Operation operation;

	public ChooserSalaireAgent() {
		this.setTitle("Salaire");
		this.setSize(MartFrame.MEDIUM_FRAME);
		this.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		setResizable(false);
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getAgent());

		initComponent();
	}

	private void initComponent() {
		panSearch = new PanChooseAgent();
		panSearch.addListener(this);

		barreOutils.add(panSearch);

		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bAnnulerPayement);
		barreOutilsV.add(bImprimer);
		barreOutilsV.add(bReinitialiser);
		barreOutilsV.add(bQuitter);

		addListeners();

		load();

		panSituation = new JPanel(new BorderLayout());
		panSituation.setBackground(Color.BLUE);
	}

	public static void main(String[] args) {
		ChooserSalaireAgent n = new ChooserSalaireAgent();
		n.setVisible(true);
		n.setAnnee("2016-2017");
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
			liste = new String[2];
			liste[0] = "ECOLAGE";
			liste[1] = "INSCRIPTION";
			chooser = new PayerSalaire(agent, annee);
			chooser.addObserver(this);
		}

		if (source == bAnnulerPayement) {
			liste = new String[2];
			liste[0] = "ANNULATION ECO.";
			liste[1] = "ANNULATION INS.";
		}

		if (source == bReinitialiser) {
			agent = null;
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					System.out.println("ok");
					panIdAgent.reset();
					panIdAgent.repaint();
				}
			});

		}

		if (source == bImprimer) {
			try {
				operation.setAgentSal(agentSal);
				doImprimer();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null,
						"Aucune opération n'est choisie");
			}
		}

		if (source == panSearch.getValider()) {
			refresh();
		}
	}

	private void doImprimer() {
		if (operation != null) {
			BullPayeModel writer = new BullPayeModel();
			writer.setAnnee(annee);
			writer.writeRecu(operation);

		} else {
			JOptionPane.showMessageDialog(null,
					"Aucune opération n'est choisie");
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
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		payesaldao = DAOFactory.getDAO(DAO.PAYEMENT_SALAIRE);
		elvdao.load();
	}

	@Override
	public void refresh() {
		agent = panSearch.getSelectedItem();

		afficher();
	}

	public void afficher() {
		panIdAgent = new InfoPanel();
		panIdAgent.setAgent(agent);
		container.add(panIdAgent, BorderLayout.NORTH);

		// affichage des operation antérieures
		payesaldao.load("", "", 0, annee);
		listAgentSalaire = payesaldao.getListObt();

		agentSal = listAgentSalaire.find(agent.getCodeInfo());

		if (agentSal == null) {
			agentSal = new AgentSalaire(agent.getCodeInfo());
		}

		try {
			agentSal.setSexe(agent.getSexe());
			// l'affichage n'est faite que si l'élève existe
			setOperationAnterieures(agentSal);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "L'agent Choisi n'existe pas.");
			// l'affichage n'est faite que si l'élève existe
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					panIdAgent.reset();
				}
			});

		}

		// System.out.println("=================>>" + eleveEco.getSexe());
		panSearch.reset();
	}

	// la methode qui crée le tableau des opération antérieures
	private void setOperationAnterieures(AgentSalaire agentSal) {
		MartList<Operation> listOp = new MartList<Operation>();

		try {
			System.out.println("Code eleve: " + agentSal.getCodeInfo());
			listOp = agentSal.getlisteOperation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// calculs
		SalaireComputer computer = new SalaireComputer();
		computer.setAnnee(annee);
		computer.setSalaireAgent(agentSal);

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

			item.setInfo(op);
			addItem(item);

			i++;
		}

		JLabel lbAnterieur = new JLabel("Opérations antérieures");
		JLabel lbSituation = new JLabel();

		lbAnterieur.setFont(police);
		lbSituation.setFont(police);

		lbSituation.setForeground(Color.WHITE);

		lbSituation.setText("<html><table><tr><td>Total Annuel: </td><td> "
				+ computer.getSalaireAnnee() + "</td></tr></table></html>");

		lbSituation.revalidate();

		panSituation.removeAll();
		panSituation.add(lbSituation, BorderLayout.CENTER);
		container.add(panSituation, BorderLayout.SOUTH);
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
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		refresh();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		operation = (Operation) ((OptionItem) source).getInfo();
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
	public void mousePressed(MouseEvent e) {
		Component source = (Component) e.getSource();
		operation = (Operation) ((OptionItem) source).getInfo();
		System.out.println("I am listening you");
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
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

}
