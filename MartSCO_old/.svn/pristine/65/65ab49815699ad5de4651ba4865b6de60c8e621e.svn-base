package configurationPlanning;

import function.Constance;
import graphicsModel.MartDialog;
import graphicsModel.MartList;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import progress.Avancer;
import configurationAppli.AbstractConfig;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.Setting;
import configurationExamen.OptionSettingReleve;

/**
 * La classe qui gère les configurations générales
 * 
 * @author Administrateur
 *
 */

public class ConfigPlanning extends AbstractConfig {

	public SettingPlanning planningConfig;

	// constructeur
	public ConfigPlanning(String an) {
		super();
		annee = an;
	}

	/**
	 * Methode qui instantie les différents composants
	 * 
	 */
	public void initComponent() {
		tp = new JTabbedPane();

		container = new JPanel();
		container.setLayout(new BorderLayout());

		planningConfig = new SettingPlanning();
		planningConfig.setAnnee(annee);
		planningConfig.set();
		addConfig(planningConfig);

		// les boutons
		barreOutilsV.add(bSave);

		// ajout au conteneurs
		container.add(tp, BorderLayout.CENTER);

		refresh();
	}

	public static void main(String[] args) {
		Constance.initialize();
		new ConfigPlanning("2016-2017").setVisible(true);
	}

	// LORS DE LA VALIDATION
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			save();

			this.showMessage("Mise à jour efféctué");
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

	@SuppressWarnings("unchecked")
	@Override
	/**Cette methode met à jour les configurations affectuées
	 * Elle recuperre les configurations récentes de la base de données
	 * et les affiche
	 */
	// les accesseurs des setting
	public void close() {
		this.dispose();
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee2) {
		annee = annee2;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
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

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}
}
