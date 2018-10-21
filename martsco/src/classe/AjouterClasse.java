package classe;

import graphicsModel.MartFrame;
import graphicsModel.MartLabel;
import graphicsModel.MartList;
import graphicsModel.TablePanel;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mxrck.autocompleter.TextAutoCompleter;
import componentFactory.MartButton;

import connection.DAO;
import connection.DAOFactory;
import progress.Avancer;

public class AjouterClasse extends MartFrame {
	private MartButton bValider = new MartButton().getSauvegarder();
	protected JLabel lbClasse = new MartLabel();
	protected JTextField tfClasse = new JTextField();
	protected JLabel lbNiveau = new MartLabel();
	protected JTextField tfNiveau = new JTextField();
	protected Dimension dim1 = new Dimension(120, 30);
	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private TextAutoCompleter niveauAutoCompleter;
	private DAO nivdao;

	public AjouterClasse() {
		setSize(SMALL_FRAME);
		setTitle("Ajouter année scolaire");
		setLocation(SMALL_FRAME_CHOOSER_LOCATION);
		setToolBarVertical();
		barreOutilsV.add(bValider);

		// champ de saisie
		lbClasse.setText("<div>Classe:* </div><div id='explication'>(Exemple: 6ème A)</div>");
		lbClasse.setFont(police1);

		lbNiveau.setText("<div>Niveau:* </div><div id='explication'>(Exemple: 6ème A ---> 6ème)</div>");
		lbNiveau.setFont(police1);

		tfClasse.setPreferredSize(dim1);
		tfClasse.setFont(police1);

		tfNiveau.setPreferredSize(dim1);
		tfNiveau.setFont(police1);

		// ajout des écouteurs
		tfClasse.addKeyListener(this);
		addListeners();

		// autocompléteur
		load();
		nivdao.load();
		MartList<Niveau> listeNiv = nivdao.getListObt();
		niveauAutoCompleter = new TextAutoCompleter(tfNiveau);

		for (Niveau niv : listeNiv) {
			niveauAutoCompleter.addItem(niv.getIntitule());
		}

		container = new JPanel();
		container.setLayout(new FlowLayout());

		container.add(lbClasse);
		container.add(tfClasse);
		container.add(lbNiveau);
		container.add(tfNiveau);

		getContentPane().add(container, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new AjouterClasse().setVisible(true);
	}

	// les accesseurs
	public String getClasse() {
		return tfClasse.getText();
	}

	// les accesseurs
	public String getNiveau() {
		return tfNiveau.getText();
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bValider) {
			doValider();
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

	@Override
	public void load() {
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
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
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			doValider();
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

	private void doValider() {
		Classe classe = new Classe(getClasse(), getNiveau());
		ClasseModel model = new ClasseModel();
		ClasseControler controler = new ClasseControler(model);
		model.addObserver((Observer) this.getParent());

		model.setData(classe);
		controler.setData(classe);

		controler.valider();
		dispose();
	}
}
