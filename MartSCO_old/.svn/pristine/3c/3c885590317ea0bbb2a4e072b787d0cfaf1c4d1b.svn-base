package OptionPane;

import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import note.ChooserNote;
import note.ReporterNote;
import note.SerieNoteFrame;
import progress.Avancer;
import abstractObject.AbstractChooser;

public class OptionGestionNote extends OptionEditorFrame {

	private static OptionGestionNote instance;
	private OptionItem saisieNote;
	private OptionItem rapport;

	public OptionGestionNote() {
		setIcone(new FrameIcon().getGestionNote());
		setTitle("GESTION DES NOTES");
		saisieNote = new OptionItem(
				"img_editer.png",
				"<div>Saisie de notes de classe</div>"
						+ "<div id='explication'>On saisie ici les notes définives</div>");
		rapport = new OptionItem(
				"img_rapport.png",
				"<div>Bilans des notes de classe</div><div id='explication'>Cumul des notes trimestrielle / semestrielle</div>");

		addItem(saisieNote);
		addItem(rapport);

		addListeners();

	}

	public static void main(String[] args) {
		Constance.initialize();
		getInstance().setVisible(true);
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

		if (source == saisieNote) {
			OptionSaisieNote.getInstance().setVisible(true);
		}

		if (source == rapport) {
			OptionGestionRapport.getInstance().setVisible(true);
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
			instance = new OptionGestionNote();
		}
		return instance;
	}

}
