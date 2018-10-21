package configurationExamen;

import examen.ChooserExam;
import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.MartDialog;
import graphicsModel.MartFrame;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import componentFactory.MartButton;
import configurationAppli.AbstractConfig;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import configurationClasse.ConfigClasse;
import configurationEcole.OptionSettingBulletin;
import progress.Avancer;

/**
 * La classe qui gère les configurations générales
 * 
 * @author Administrateur
 *
 */

public class ConfigExamen extends AbstractConfig {
	public OptionSettingReleve relConfig;
	private ChooserExam chooser;

	// constructeur
	public ConfigExamen(String exam) {
		this.setTitle("Configurations Relevés");
		annee = exam;

		initComponent();
		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	/**
	 * Methode qui instantie les différents composants
	 * 
	 */
	public void initComponent() {
		tp = new JTabbedPane();
		container = new JPanel();
		container.setLayout(new BorderLayout());
		new MartList<Setting>();

		relConfig = new OptionSettingReleve();
		relConfig.setAnnee(annee);// l'examen prend les fonction de l'annee
		relConfig.set();
		addConfig(relConfig);

		// les boutons
		barreOutilsV.add(bSave);
		barreOutilsV.add(bRecondure);

		addListeners();

		// ajout au conteneurs
		container.add(tp, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		Constance.initialize();
		new ConfigExamen("BEPC- BLANC 3ème AVRIL 2018").setVisible(true);
	}

	// LORS DE LA VALIDATION
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			save();
			this.showMessage("Mise à jour efféctué");
		}

		if (source == bRecondure) {
			chooser = ChooserExam.getInstance();
			chooser.setAction(new ActionListener() {
				private ConfigExamen fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = new ConfigExamen(chooser.getExamen());
					fr.setAnnee(annee);
					fr.setVisible(true);
					chooser.close();
					ConfigExamen.this.close();
				}
			});
			chooser.setVisible(true);
		}
	}

	// les accesseurs des setting
	public void close() {
		this.dispose();
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
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
}
