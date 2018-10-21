package classe;

import eleve.EleveClasse;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;
import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;

public class PanChooseEleveClasse extends JPanel {
	private JPanel container;
	private Dimension dimTf = new Dimension(250, 30);
	private JTextField tfSearch;
	private DAO dao;
	private MartList<EleveClasse> eleves;
	private TextAutoCompleter compEleve;
	private Font police = new Font("Tahoma", Font.BOLD, 12);
	private EleveClasse selectedItem;
	private MartButton bBien;
	private String classe;
	private String annee;

	public PanChooseEleveClasse(String cl, String an) {
		classe = cl;
		annee = an;

		// autocompleter
		dao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		dao.load(null, classe, 0, annee);
		eleves = dao.getListObt();

		// ajout au conteneur
		container = new JPanel(new FlowLayout());
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

		for (EleveClasse elv : eleves) {
			compEleve.addItem(elv.decrisToi());
		}

		container.removeAll();
		container.add(new JLabel("Nom de l'Elève: "));
		container.add(tfSearch);
		container.add(bBien);
	}

	public void reset() {
		tfSearch.setText("");
		selectedItem = new EleveClasse();
	}

	public EleveClasse getSelectedItem() {
		String item = (String) compEleve.getItemSelected();
		EleveClasse eleve = null;

		for (EleveClasse el : eleves) {
			if (el.decrisToi().equals(item)) {
				eleve = el;
				break;
			}
		}
		selectedItem = eleve;

		return selectedItem;
	}

	public void setSelectedItem(EleveClasse selectedItem) {
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
		tfSearch.addMouseListener((MouseListener) listener);
	}

	public void setThisFocusable(boolean b) {
		tfSearch.setFocusable(b);
	}

	public void setListe(MartList<EleveClasse> liste) {
		eleves = liste;
		refresh();
	}
}
