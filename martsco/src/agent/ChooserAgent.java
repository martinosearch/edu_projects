package agent;

import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progress.Avancer;

import com.mxrck.autocompleter.TextAutoCompleter;

import connection.DAO;
import connection.DAOFactory;

public class ChooserAgent extends MartFrame {

	private JTextField tfSearch;
	private Dimension dimTf = new Dimension(250, 30);
	private TextAutoCompleter compEleve;
	private DAO agentdao;
	private MartList<Agent> agents;
	private Thread treat;
	private Agent agent;
	private Font police = new Font("Arial", Font.BOLD, 14);

	public Thread getTreat() {
		return treat;
	}

	public void setTreat(Thread treat) {
		this.treat = treat;
	}

	public ChooserAgent() {
		setTitle("Choix de l'élève");
		setSize(310, 150);
		setLocationRelativeTo(null);

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
		setVisible(true);
	}

	private void initComponent() {
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(dimTf);
		tfSearch.addKeyListener(this);
		tfSearch.setFont(police);

		load();

		container = new JPanel();
		container.add(new JLabel(
				"Nom de l'élève puis valider avec la touche ENTRER"));
		container.add(tfSearch);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Component source = (Component) e.getSource();

		if (source == tfSearch) {
			if (compEleve.itemExists(tfSearch.getText()) == true) {
				refresh();
			}
		}
	}

	public static void main(String[] args) {
		new ChooserAgent();
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

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
		agentdao = DAOFactory.getDAO(DAO.AGENT);

		agentdao.load();
		agents = agentdao.getListObt();
		compEleve = new TextAutoCompleter(tfSearch);

		for (Agent agent : agents) {
			compEleve.addItem(agent.decrisToi());
		}
	}

	@Override
	public void refresh() {
		treat.start();
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

	public Agent getSelectedItem() {
		for (Agent ag : agents) {
			if (ag.decrisToi().equals(compEleve.getItemSelected())) {
				agent = ag;
			}
		}

		return agent;
	}

}
