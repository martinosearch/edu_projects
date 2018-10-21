package annee;

import graphicsModel.MartFrame;
import graphicsModel.MartLabel;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
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

import componentFactory.MartButton;

import progress.Avancer;

public class AjouterAnnee extends MartFrame {
	private MartButton bValider = new MartButton().getSauvegarder();
	protected JLabel lbIntitule = new MartLabel();
	protected JTextField tfIntitule = new JTextField();
	protected Dimension dim1 = new Dimension(150, 30);
	Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);

	public AjouterAnnee() {
		setSize(SMALL_FRAME);
		setTitle("Ajouter année scolaire");
		setLocation(SMALL_FRAME_CHOOSER_LOCATION);
		setToolBarVertical();
		barreOutilsV.add(bValider);

		// champ de saisie
		lbIntitule
				.setText("<div>Année- Scolaire</div><div id='explication'>(Exemple: 2016-2017)</div>");
		tfIntitule.setPreferredSize(dim1);
		tfIntitule.setFont(police1);
		lbIntitule.setFont(police1);

		// ajout des écouteurs
		tfIntitule.addKeyListener(this);
		addListeners();

		container = new JPanel();
		container.setLayout(new FlowLayout());

		container.add(lbIntitule);
		container.add(tfIntitule);

		getContentPane().add(container, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new AjouterAnnee().setVisible(true);
	}

	// les accesseurs
	public String getAnnee() {
		return tfIntitule.getText();
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
		Annee annee = new Annee(getAnnee());
		AnneeModel model = new AnneeModel();
		AnneeControler controler = new AnneeControler(model);
		model.addObserver((Observer) getParent());

		model.setData(annee);
		controler.setData(annee);
		controler.valider();
		dispose();
	}
}
