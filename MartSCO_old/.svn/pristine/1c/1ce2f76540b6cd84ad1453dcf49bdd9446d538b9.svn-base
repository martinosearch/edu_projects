package OptionPane;

import ecolage.GeneralVoidEcolage;
import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import progress.Avancer;

public class OptionRapportEco extends OptionEditorFrame {

	private static OptionRapportEco instance;
	private OptionItem rapportEco;
	private OptionItem rapportAll;
	private OptionItem rapportClasseEco;
	private OptionItem rapportClasseIns;

	public OptionRapportEco() {
		rapportEco = new OptionItem("img_rapport.png",
				"<div>Entrées de Frais de Scolarité (Agent)</div>"
						+ "<div id='explication'>Ecolage+ Inscription</div>");
		rapportAll = new OptionItem("img_rapport.png",
				"Toutes les Entrées (Agent)");
		rapportClasseEco = new OptionItem("img_rapport.png",
				"Entrées Ecolage (Classe)");
		rapportClasseIns = new OptionItem("img_rapport.png",
				"Entrées Incription (Classe)");

		addItem(rapportClasseIns);
		addItem(rapportClasseEco);
		addItem(rapportEco);
		addItem(rapportAll);

		setIcone(new FrameIcon().getBilan());
	}

	public static void main(String[] args) {
		Constance.initialize();
		getInstance().setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		if (source == rapportEco) {
			new GeneralVoidEcolage().listeEntreeEcolage();
		}

		if (source == rapportClasseIns) {
			new GeneralVoidEcolage().listeFraisInscriptionClasse();
		}

		if (source == rapportClasseEco) {
			new GeneralVoidEcolage().listeEcolageClasse();
		}

		if (source == rapportAll) {
			new GeneralVoidEcolage().listeCompteEntree();
		}

		reset();
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

	public static OptionEditorFrame getInstance() {
		if (instance == null) {
			instance = new OptionRapportEco();
		}
		return instance;
	}

}
