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

import note.ChooserNote;
import note.ReporterNote;
import note.SerieNoteFrame;
import progress.Avancer;
import abstractObject.AbstractChooser;

public class OptionSaisieNote extends OptionEditorFrame {

	private static OptionSaisieNote instance;
	private OptionItem noteDef;
	private OptionItem annulerNote;
	private OptionItem reporterNote;
	private OptionItem brouillonNote;

	public OptionSaisieNote() {
		setIcone(new FrameIcon().getGestionNote());
		setTitle("SAISIE DES NOTES");
		noteDef = new OptionItem(
				"img_editer.png",
				"<div>Saisie de notes</div>"
						+ "<div id='explication'>On saisie ici les notes définives</div>");
		brouillonNote = new OptionItem(
				"img_draft.png",
				"<div>Carnet de notes</div>"
						+ "<div id='explication'>On saisie ici les notes d'interrogation qui ne sont pas définitives à titre de brouillon</div>");
		reporterNote = new OptionItem(
				"img_reporter.png",
				"<div>Déplacer une série de notes</div><div id='explication'>Ceci lorsqu'on a saisi une série "
						+ "de notes à la place d'une autre</div>");
		annulerNote = new OptionItem(
				"img_delete.png",
				"<div>Annuler une saisie de notes</div><div id='explication'>Réinitialiser la série de notes</div>");

		addItem(noteDef);
		addItem(brouillonNote);
		addItem(annulerNote);
		addItem(reporterNote);

		addListeners();

	}

	public static void main(String[] args) {
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

		if (source == noteDef) {
			ChooserNote.getInstance().setVisible(true);
		}

		if (source == brouillonNote) {
			SerieNoteFrame.getInstance().setVisible(true);
		}

		if (source == reporterNote) {
			new ReporterNote().setVisible(true);
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
			instance = new OptionSaisieNote();
		}
		return instance;
	}

}
