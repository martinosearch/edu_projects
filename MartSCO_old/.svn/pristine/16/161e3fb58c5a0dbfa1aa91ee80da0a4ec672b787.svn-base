package classe;

import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;
import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class PanChooseNiveau extends JPanel {
	private JPanel container;
	private Dimension dimTf = new Dimension(250, 30);
	private JTextField tfSearch;
	@SuppressWarnings("rawtypes")
	private DAO dao;
	private MartList<Niveau> liste;
	private TextAutoCompleter compEleve;
	private Font police = new Font("Tahoma", Font.BOLD, 12);
	private Niveau selectedItem;
	private MartButton bBien;

	public PanChooseNiveau() {
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(dimTf);
		tfSearch.setFont(police);

		// autocompleter
		dao = DAOFactory.getDAO(DAO.NIVEAU);
		dao.load();
		liste = dao.getListObt();
		compEleve = new TextAutoCompleter(tfSearch);

		for (Niveau elv : liste) {
			compEleve.addItem(elv.getIntitule());
		}

		// ajout au conteneur
		container = new JPanel(new FlowLayout());
		container.add(new JLabel("Niveau: "));
		container.add(tfSearch);

		bBien = new MartButton().getBien();
		container.add(bBien);

		setLayout(new BorderLayout());
		add(container, BorderLayout.CENTER);
	}

	public void reset() {
		tfSearch.setText("");
		selectedItem = new Niveau();
	}

	public Niveau getSelectedItem() {
		String item = (String) compEleve.getItemSelected();
		Niveau matiere = null;

		for (Niveau obj : liste) {
			if (obj.getIntitule().equals(item)) {
				matiere = obj;
				break;
			}
		}
		selectedItem = matiere;

		return selectedItem;
	}

	public void setSelectedItem(Niveau selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Component getComponent() {
		return tfSearch;
	}

	public boolean itemExists() {
		return compEleve.itemExists(tfSearch.getText());
	}

	public MartButton getValider() {
		return bBien;
	}

	public void addListener(Object listener) {
		bBien.addActionListener((ActionListener) listener);
		tfSearch.addKeyListener((KeyListener) listener);
	}
}
