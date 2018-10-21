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
import abstractObject.Rapport;

public class OptionGestionRapport extends OptionEditorFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1306983716386655225L;

	private static OptionGestionRapport instance;
	private OptionItem rapportTrim;
	private OptionItem sta;
	private OptionItem rapportEval;

	public OptionGestionRapport() {
		setIcone(new FrameIcon().getBilan());
		setTitle("GESTION DES RAPPORTS");

		rapportTrim = new OptionItem(
				"img_rapport.png",
				"<div>Rapports trimestriels/ semestriels</div><div id='explication'>Bulletin de notes/ Procès/ Statistiques</div>");
		sta = new OptionItem(
				"img_sta.png",
				"<div>Statistiques</div><div id='explication'>Fiches statistiques officielles et d'autres</div>");
		rapportEval = new OptionItem(
				"img_rapport.png",
				"<div>Rapports par Evaluation</div><div id='explication'>Résultats par évaluation</div>");

		addItem(rapportTrim);
		addItem(sta);
		addItem(rapportEval);

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

		if (source == rapportTrim) {
			OptionRapportNote.getInstance().setVisible(true);
		}

		if (source == rapportEval) {
			// on demande la même chose que pour les examens en spécifiant que
			// c'est pour la gestion des evaluations mensuelles
			OptionRapportNoteExam rapport = OptionRapportNoteExam.getInstance();
			rapport.setVisible(true);
			rapport.setTypeRapport(Rapport.EVAL_TRIMESTRIELLE);
		}

		if (source == sta) {
			OptionStatistique.getInstance().setVisible(true);
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
			instance = new OptionGestionRapport();
		}
		return instance;
	}

}
