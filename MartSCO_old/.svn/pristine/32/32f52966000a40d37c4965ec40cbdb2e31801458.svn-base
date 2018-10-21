package agent;

import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;
import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class PanChooseAgent extends JPanel {
	private JPanel container;
	private Dimension dimTf = new Dimension(250, 30);
	private JTextField tfSearch;
	private DAO agentdao;
	private MartList<Agent> agents;
	private TextAutoCompleter compItem;
	private Font police = new Font("Tahoma", Font.BOLD, 12);
	private Agent selectedItem;
	private MartButton bBien;

	public PanChooseAgent() {
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(dimTf);
		tfSearch.setFont(police);

		// autocompleter
		agentdao = DAOFactory.getDAO(DAO.AGENT);
		agentdao.load();
		agents = agentdao.getListObt();
		compItem = new TextAutoCompleter(tfSearch);

		for (Agent elv : agents) {
			compItem.addItem(elv.decrisToi());
		}

		// ajout au conteneur
		container = new JPanel(new FlowLayout());
		container.add(new JLabel("Nom de l'Agent: "));
		container.add(tfSearch);

		bBien = new MartButton().getBien();
		container.add(bBien);

		setLayout(new BorderLayout());
		add(container, BorderLayout.CENTER);
	}

	public void reset() {
		tfSearch.setText("");
		selectedItem = new Agent();
	}

	public Agent getSelectedItem() {
		String item = (String) compItem.getItemSelected();
		Agent eleve = null;

		for (Agent el : agents) {
			if (el.decrisToi().equals(item)) {
				eleve = el;
				break;
			}
		}
		selectedItem = eleve;

		return selectedItem;
	}

	public void setSelectedItem(Agent selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Component getComponent() {
		return tfSearch;
	}

	public boolean itemExists() {
		return compItem.itemExists(tfSearch.getText());
	}

	public MartButton getValider() {
		return bBien;
	}

	public void addListener(Object listener) {
		bBien.addActionListener((ActionListener) listener);
		tfSearch.addKeyListener((KeyListener) listener);
	}
}
