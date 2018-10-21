package eleve;

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

public class PanChooseEleve extends JPanel {
	private JPanel container;
	private Dimension dimTf = new Dimension(250, 30);
	private JTextField tfSearch;
	private DAO elvdao;
	private DAO versecolagedao;
	private MartList<Eleve> eleves;
	private TextAutoCompleter compEleve;
	private Font police = new Font("Tahoma", Font.BOLD, 12);
	private Eleve selectedItem;
	private MartButton bBien;

	public PanChooseEleve() {

		// ajout au conteneur
		container = new JPanel(new FlowLayout());

		// autocompleter
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvdao.load();
		eleves = elvdao.getListObt();

		bBien = new MartButton().getBien();

		setLayout(new BorderLayout());
		add(container, BorderLayout.CENTER);
		refresh();

	}

	private void refresh() {
		tfSearch = new JTextField();
		tfSearch.setPreferredSize(dimTf);
		tfSearch.setFont(police);

		compEleve = new TextAutoCompleter(tfSearch);
		for (Eleve elv : eleves) {
			compEleve.addItem(elv.decrisToi());
		}

		container.removeAll();
		container.add(new JLabel("Nom de l'Elève: "));
		container.add(tfSearch);
		container.add(bBien);
		container.revalidate();
	}

	public void reset() {
		tfSearch.setText("");
		selectedItem = new Eleve();
	}

	public Eleve getSelectedItem() {
		String item = (String) compEleve.getItemSelected();
		Eleve eleve = null;

		for (Eleve el : eleves) {
			if (el.decrisToi().equals(item)) {
				eleve = el;
				break;
			}
		}
		selectedItem = eleve;

		return selectedItem;
	}

	public void setSelectedItem(Eleve selectedItem) {
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

	public void setListe(MartList<Eleve> liste) {
		eleves = liste;
		refresh();
	}
}
