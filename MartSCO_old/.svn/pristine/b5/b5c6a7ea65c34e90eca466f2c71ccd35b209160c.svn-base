package OptionPane;

import graphicsModel.FrameIcon;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import progress.Avancer;
import statistique.StaMoyAn;
import statistique.StaMoyTrim;
import statistique.StaNoteCompo;

public class OptionStatistique extends OptionEditorFrame {

	private static OptionStatistique instance;
	private OptionItem staNoteCompo;
	private OptionItem staMoyTrim;
	private OptionItem staMoyAn;

	public OptionStatistique() {
		setTitle("DOCUMENTS");
		staMoyTrim = new OptionItem("img_rapport.png",
				"Statistique des moyennes trimestrielles");
		staNoteCompo = new OptionItem("img_rapport.png",
				"Statistique des notes de composition");
		staMoyAn = new OptionItem("img_rapport.png",
				"Statistique des moyennes annuelles");

		addItem(staNoteCompo);
		addItem(staMoyAn);
		addItem(staMoyTrim);

		setIcone(new FrameIcon().getBilan());
	}

	public static void main(String[] args) {
		new OptionStatistique().setVisible(true);

	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();

		// ecoute sta
		if (source == staNoteCompo) {
			StaNoteCompo writer = new StaNoteCompo(
					"STATISTIQUE DES NOTES DE COMPOSITION");
			writer.set();
		}

		if (source == staMoyTrim) {
			StaMoyTrim writer = new StaMoyTrim(
					"STATISTIQUE DES MOYENNES TRIMESTRIELLES");
			writer.set();
		}

		if (source == staMoyAn) {
			StaMoyAn writer = new StaMoyAn("STATISTIQUE DES MOYENNES ANNUELLES");
			writer.isTrimestreSetting(false);
			writer.set();
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

	public static OptionStatistique getInstance() {
		if (instance == null) {
			instance = new OptionStatistique();
		}
		return instance;
	}
}
