package salaire;

import ecolage.Operation;
import graphicsModel.ChooserMois;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;
import images.RessourceFinder;
import interfacePerso.Observable;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import progress.Avancer;
import agent.Agent;

import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class PayerSalaire extends OptionEditorFrame implements ChangeListener,
		Observable {
	private Font police = new Font("courrier new", Font.BOLD, 20);
	private Agent agent;
	private DAO payersaldao, saldao;
	private String annee;
	private MartList<AgentSalaire> listSalaireAgent;
	private AgentSalaire salAgent;
	private ChooserMois chooserMois;
	private SalaireComputer computer;
	private PayerSalaireModel model;
	private PayerSalaireControler controler;
	private MartList<Observer> listeObserver;
	private MartButton bSave = new MartButton().getSauvegarder();
	private MartButton bQuitter = new MartButton().getQuitter();
	private JPanel panSituation;
	private JPanel thisContainer;

	public PayerSalaire(Agent sal, String an) {
		setSize(MartFrame.MEDIUM_FRAME);
		setTitle("Payement de Salaire");
		setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		new Dimension(getWidth() - 20, 40);
		agent = sal;
		annee = an;
		setToolBar();
		setIcone(new FrameIcon().getSalaire());
		setToolBarVertical();

		initComponent();

		setVisible(true);
	}

	public static void main(String[] args) {
		Agent ag = new Agent();
		ag.setCodeInfo("A0005");

		new PayerSalaire(ag, "2017-2018");
	}

	private void initComponent() {
		listeObserver = new MartList<Observer>();
		barreOutilsV.add(bSave);
		barreOutilsV.add(bQuitter);

		addListeners();

		model = new PayerSalaireModel();
		controler = new PayerSalaireControler(model);

		JLabel lbNom = new JLabel();
		try {
			lbNom.setText(agent.decrisToi());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aucun agent n'est choisi.");
		}

		lbNom.setPreferredSize(new Dimension(450, 30));
		lbNom.setFont(police);
		barreOutils.add(lbNom);

		chooserMois = new ChooserMois();
		chooserMois.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		chooserMois.setPreferredSize(new Dimension(300, 50));
		chooserMois.addChangeListener(this);

		JPanel panMois = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panMois.setPreferredSize(new Dimension(625, 55));
		panMois.add(chooserMois);

		thisContainer = new JPanel(new BorderLayout());
		thisContainer.add(panMois, BorderLayout.CENTER);

		load();

		panSituation = new JPanel(new BorderLayout());
		panSituation.setBackground(Color.BLUE);

		afficherAgent();
	}

	public void afficherAgent() {
		// refreshItems();
		// affichage des operation antérieures
		saldao.load("", "", 0, annee);
		payersaldao.load("", "", 0, annee);
		listSalaireAgent = saldao.getListObt();

		salAgent = listSalaireAgent.find(agent.getCodeInfo());

		try {
			if (salAgent == null) {
				salAgent = new AgentSalaire(agent.getCodeInfo());
			}
		} catch (Exception e) {
			e.printStackTrace();
			salAgent = new AgentSalaire(agent.getCodeInfo());
		}

		try {
			salAgent.setSexe(agent.getSexe());
			// l'affichage n'est faite que si l'élève existe
			setSalaire(salAgent);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"L'agent choisi n'existe pas cette année");
		}

		// on reintialise salAgent pour corriger le nombre de versement en son
		// compte
		salAgent = new AgentSalaire();
		try {
			salAgent = (AgentSalaire) payersaldao.findObj(agent.getCodeInfo());
			String nom = salAgent.getCodeInfo();
			// System.out.println("================>>" + nom);
		} catch (Exception e) {
			e.getStackTrace();
			salAgent = new AgentSalaire();
			// System.out.println("==============hhhhhh==>>");
		}

		container.add(thisContainer, BorderLayout.NORTH);
	}

	// la methode qui crée le tableau des opération antérieures
	private void setSalaire(AgentSalaire salAgent) {
		refreshItems();

		MartList<Operation> listOp = new MartList<Operation>();
		try {
			listOp = salAgent.getlisteOperation();
			// System.out.println("===========>>Code eleve: "+
			// salAgent.getCodeInfo() + " Size op= " + listOp.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// calculs
		computer = new SalaireComputer();
		computer.setAnnee(annee);
		computer.setSalaireAgent(salAgent);

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

			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		JLabel lbAnterieur = new JLabel("Détails du Salaire");
		JLabel lbSituation = new JLabel();

		lbAnterieur.setFont(police);
		lbSituation.setFont(police);
		lbSituation.setForeground(Color.WHITE);

		lbSituation.setText("<html><table><tr><td>Salaire Brut: "
				+ computer.getSalaireBrut() + "</td><td> "
				+ "</td></tr><tr><td>Déduction: " + computer.getPrelevement()
				+ "</td><td></td></tr>" + "<tr><td>Salaire Net: "
				+ computer.getSalaireNet() + "</td><td></td></tr>"
				+ "</table></html>");

		lbSituation.revalidate();

		panSituation.removeAll();
		panSituation.add(lbSituation, BorderLayout.CENTER);

		container.add(panSituation, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			Operation vers = new Salaire(agent.getCodeInfo(),
					computer.getSalaireNet());

			vers.setJustification(AgentSalaire.getJustificationPaye(chooserMois
					.getMois()));

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

			notifyObserver();
			dispose();
		}

		if (source == bQuitter) {
			close();
		}
	}

	@Override
	public void load() {
		saldao = DAOFactory.getDAO(DAO.SALAIRE);
		payersaldao = DAOFactory.getDAO(DAO.PAYEMENT_SALAIRE);
		DAOFactory.getDAO(DAO.AGENT);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Component source = (Component) e.getSource();

		if (source == chooserMois.spinner) {
			afficherAgent();
		}
	}

	@Override
	public void addObserver(Observer obs) {
		listeObserver.add(obs);
	}

	@Override
	public void removeObserver() {
		listeObserver = new MartList<Observer>();
	}

	@Override
	public void notifyObserver() {
		for (Observer obs : listeObserver) {
			obs.update();
		}
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

}
