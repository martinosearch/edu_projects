package connection;

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
import accueil.AccueilSCO;
import annee.ChooserAnnee;

public class OptionDataBase extends OptionEditorFrame {

	private static OptionDataBase instance;
	private OptionItem miseAjour;
	private OptionItem restauration;
	private AccueilSCO accueil;
	private OptionItem supprClasse;
	private DataChooser dChooser;
	private ChooserAnnee anChooser;

	public OptionDataBase() {
		setTitle("Statistique");
		restauration = new OptionItem(
				"img_db.png",
				"<div>Restauration de la base de données</div><div id='explication'>Permet de revenir à une version antérieure de la base de données</div>");
		miseAjour = new OptionItem(
				"img_db.png",
				"<div>Mise à jour </div><div id='explication'>Permet de faire des correction nécessaires à la base de données</div>");
		supprClasse = new OptionItem(
				"img_delete.png",
				"<div>Supprimer des classes </div><div id='explication'>Permet de supprimer des classes de la base de données.</div>");

		addItem(miseAjour);
		addItem(restauration);
		addItem(supprClasse);

		setIcone(new FrameIcon().getDB());
	}

	public static void main(String[] args) {
		new OptionDataBase().setVisible(true);

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
		if (source == miseAjour) {
			AccueilSCO.getInstance().getGeneralVoid().setDoMiseAjour(true);
			AccueilSCO.getInstance().close();
			close();
		}

		if (source == restauration) {
			AccueilSCO.getInstance().getGeneralVoid().setDoRestore(true);
			AccueilSCO.getInstance().close();
			close();
		}

		if (source == supprClasse) {
			dChooser = DataChooser.getInstance();
			anChooser = ChooserAnnee.getInstance();
			anChooser.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dChooser.setAnnee(anChooser.getAnnee());
					dChooser.setVisible(true);
					anChooser.close();

					dChooser.set();
				}
			});

			anChooser.setVisible(true);
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

	public static OptionDataBase getInstance() {
		if (instance == null) {
			instance = new OptionDataBase();
		}
		return instance;
	}
}
