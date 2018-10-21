package configurationAdmin;

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

import annee.ChooserAnnee;
import componentFactory.MartButton;
import configurationAppli.AbstractConfig;
import configurationAppli.Setting;
import progress.Avancer;

/**
 * La classe qui gère les configurations générales
 * 
 * @author Administrateur
 *
 */

public class ConfigAdmin extends AbstractConfig {
	public OptionSettingAdmin adminConfig;

	// constructeur
	public ConfigAdmin(int trim, String an) {
		this.setTitle("Configurations administratives");
		this.setSize(MEDIUM_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		this.setResizable(false);
		this.addWindowListener(this);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setPub();
		setToolBar();
		setToolBarVertical();
		setIcone(new FrameIcon().getConfig());

		annee = an;
		trimestre = trim;

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

		adminConfig = new OptionSettingAdmin();
		adminConfig.setAnnee(annee);
		adminConfig.set();
		addConfig(adminConfig);

		// les boutons
		barreOutilsV.add(bSave);
		barreOutilsV.add(bRecondure);

		addListeners();

		// ajout au conteneurs
		container.add(tp, BorderLayout.CENTER);

		refresh();
	}

	public static void main(String[] args) {
		Constance.initialize();
		new ConfigAdmin(1, "2016-2017").setVisible(true);
	}

	// LORS DE LA VALIDATION
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			save();
		}

		if (source == bRecondure) {
			final ChooserAnnee chooser = ChooserAnnee.getInstance();
			chooser.setTrimestreChoosing(false);
			chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

			chooser.setAction(new ActionListener() {
				private ConfigAdmin confN;

				public void actionPerformed(ActionEvent arg0) {
					confN = new ConfigAdmin(trimestre, chooser.getAnnee());
					confN.setAnnee(annee);// on réajuste l'année
					confN.setVisible(true);
					chooser.dispose();
					ConfigAdmin.this.close();
				}
			});

			chooser.setVisible(true);
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
