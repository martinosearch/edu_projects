package OptionPane;

import examen.ChooserNoteExam;
import graphicsModel.FrameIcon;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import abstractObject.Rapport;
import progress.Avancer;

public class OptionNoteExam extends OptionEditorFrame {

	private static OptionNoteExam instance;
	private OptionItem note;
	private OptionItem rapport;
	private OptionItem sta;

	public OptionNoteExam() {
		setTitle("GESTION DES NOTES");
		note = new OptionItem(
				"img_editer.png",
				"<div>Saisie de notes</div>"
						+ "<div id='explication'>On saisie ici les notes définives</div>");

		rapport = new OptionItem(
				"img_rapport.png",
				"<div>Rapport</div><div id='explication'>Relevés de notes/ Procès/ Résultats</div>");

		sta = new OptionItem(
				"img_sta.png",
				"<div>Statistiques</div><div id='explication'>Fiches statistiques officielles et d'autres</div>");

		addItem(note);
		addItem(rapport);
		addItem(sta);

		addListeners();

		setIcone(new FrameIcon().getGestionNote());
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

		if (source == note) {
			ChooserNoteExam.getInstance().setVisible(true);
		}

		if (source == rapport) {
			OptionRapportNoteExam option = OptionRapportNoteExam.getInstance();
			option.setVisible(true);
			option.setTypeRapport(Rapport.EXAMEN);
		}

		if (source == sta) {
			OptionStatistiqueExam.getInstance().setVisible(true);
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
			instance = new OptionNoteExam();
		}
		return instance;
	}

}
