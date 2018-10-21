package matiere;

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

public class PanChooseMatiere extends JPanel {
	private JPanel container;
	private Dimension dimTf = new Dimension(250, 30);
	private JTextField tfSearch;
	@SuppressWarnings("rawtypes")
	private DAO dao;
	private MartList<Matiere> matieres;
	private TextAutoCompleter compEleve;
	private Font police = new Font("Tahoma", Font.BOLD, 12);
	private Matiere selectedItem;
	private MartButton bBien;

	public PanChooseMatiere() {
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(dimTf);
		tfSearch.setFont(police);

		// autocompleter
		dao = DAOFactory.getDAO(DAO.MATIERE);
		dao.load();
		matieres = dao.getListObt();
		compEleve = new TextAutoCompleter(tfSearch);

		for (Matiere elv : matieres) {
			compEleve.addItem(elv.getIntitule());
		}

		// ajout au conteneur
		container = new JPanel(new FlowLayout());
		container.add(new JLabel("Matière: "));
		container.add(tfSearch);

		bBien = new MartButton().getBien();
		container.add(bBien);

		setLayout(new BorderLayout());
		add(container, BorderLayout.CENTER);
	}

	public void reset() {
		tfSearch.setText("");
		selectedItem = new Matiere();
	}

	public Matiere getSelectedItem() {
		String item = (String) compEleve.getItemSelected();
		Matiere matiere = null;

		for (Matiere obj : matieres) {
			if (obj.getIntitule().equals(item)) {
				matiere = obj;
				break;
			}
		}
		selectedItem = matiere;

		return selectedItem;
	}

	public void setSelectedItem(Matiere selectedItem) {
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
