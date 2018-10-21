package ecolage;

import graphicsModel.MartFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import progress.Avancer;
import abstractObject.AbstractChooser;
import classe.Classe;

public class AjouterSco extends AbstractChooser {

	public static final int VERSEMENT = 0, ANNULATION = 1;
	private int type;
	private String[] liste;
	private JLabel lbVersement;
	private JTextField tfVersement;
	private Font police = new Font("Arial", Font.BOLD, 14);
	private JPanel panChamp;
	private JComboBox cbJustification;
	private JLabel lbJustification;

	public AjouterSco(int type, String[] listeJustification) {
		setSize(320, 200);
		this.type = type;
		this.liste = listeJustification;
		setLocation(MartFrame.MEDIUM_FRAME_CHOOSER_LOCATION);

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		if (type == VERSEMENT) {
			setTitle("VERSEMENT AU COMPTE");
			lbVersement = new JLabel("Somme à ajouter: ");
		} else {
			setTitle("ANNULATION DE VERSEMENT");
			lbVersement = new JLabel("Somme à retirer: ");
		}

		tfVersement = new JTextField();

		tfVersement.setFont(police);
		lbVersement.setFont(police);

		tfVersement.setPreferredSize(new Dimension(150, 30));
		tfVersement.addKeyListener(this);

		lbJustification = new JLabel("Justification");
		cbJustification = new JComboBox<String>(liste);
		lbJustification.setFont(police);
		cbJustification.setFont(police);
		cbJustification.setPreferredSize(new Dimension(200, 30));
		cbJustification.setForeground(Color.BLUE);

		panChamp = new JPanel();
		panButton = new JPanel();

		panChamp.add(lbJustification);
		panChamp.add(cbJustification);
		panChamp.add(lbVersement);
		panChamp.add(tfVersement);

		panButton.add(bValider);
		panButton.add(bAnnuler);

		container = new JPanel();
		container.setLayout(new BorderLayout());

		container.add(panChamp, BorderLayout.CENTER);
		container.add(panButton, BorderLayout.SOUTH);
	}

	public static void main(String[] args) {
		String[] liste = { "ECOLAGE", "INSCRIPTION" };
		AjouterSco chooser = new AjouterSco(VERSEMENT, liste);
		chooser.setVisible(true);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bAnnuler) {
			close();
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
			((NouveauEcolage) getParent()).valider();
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
	public Classe getClasse() {
		return null;
	}

	@Override
	public String getAnnee() {
		return null;
	}

	public String getJustification() {
		return (String) cbJustification.getSelectedItem();
	}

	public double getSomme() {
		return Double.parseDouble(tfVersement.getText());
	}

	@Override
	public boolean getFirstOption() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setEditable(boolean b) {
		tfVersement.setEnabled(b);
		cbJustification.setEnabled(b);
	}

	public void setOperation(Operation op) {
		tfVersement.setText(String.valueOf(op.getMontant()));
		// cbJustification.setSelectedItem(op.getJustification());
	}

}
