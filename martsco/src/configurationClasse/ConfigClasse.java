package configurationClasse;

import function.Constance;
import graphicsModel.FrameIcon;
import graphicsModel.MartFrame;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import progress.Avancer;
import classe.ChooserClasse;
import classe.Classe;
import configurationAppli.AbstractConfig;

/**
 * La classe qui gère les configurations générales
 * 
 * @author Administrateur
 *
 */

public class ConfigClasse extends AbstractConfig implements Observer {
	public OptionSettingClasse persoClasse;

	// constructeur
	public ConfigClasse(Classe classe2, String an) {
		annee = an;
		classe = classe2.getIntitule();

		this.setTitle("Personnalisation de la classe de: " + annee);
		setIcone(new FrameIcon().getReglage(classe));

		initComponent();
		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	public ConfigClasse(String exam) {
		annee = exam;

		initComponent();
		this.setTitle("Personnalisation de:" + exam);
		setIcone(new FrameIcon().getReglage(exam));

		this.getContentPane().add(container, BorderLayout.CENTER);
	}

	/**
	 * Methode qui instantie les différents composants
	 * 
	 */
	public void initComponent() {
		this.setSize(MEDIUM_FRAME);
		this.setLocation(INTERNAL_FRAME_LOCATION);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setPub();
		setToolBar();
		setToolBarVertical();

		// les boutons
		barreOutilsV.add(bSave);
		barreOutilsV.add(bRecondure);
		addListeners();

		tp = new JTabbedPane();
		container = new JPanel();
		container.setLayout(new BorderLayout());
		// Listes des cases à cocher
		persoClasse = new OptionSettingClasse();
		persoClasse.setAnnee(annee);
		persoClasse.setClasse(classe);

		persoClasse.set();

		addConfig(persoClasse);

		// ajout au conteneurs
		container.add(tp, BorderLayout.CENTER);
		refresh();
	}

	public static void main(String[] args) {
		Constance.initialize();

		final ChooserClasse chooser = ChooserClasse.getInstance();
		chooser.setTrimestreChoosing(false);
		chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);
		chooser.setAction(new ActionListener() {
			private ConfigClasse fr;

			public void actionPerformed(ActionEvent arg0) {
				fr = new ConfigClasse(chooser.getClasse(), chooser.getAnnee());

				fr.setVisible(true);
				chooser.close();

			}
		});
		chooser.setVisible(true);
	}

	// LORS DE LA VALIDATION
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();
		final ChooserClasse chooser = ChooserClasse.getInstance();
		chooser.setTrimestreChoosing(false);
		chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

		if (source == bSave) {
			save();
		}

		if (source == bRecondure) {
			chooser.setAction(new ActionListener() {
				private ConfigClasse fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = new ConfigClasse(chooser.getClasse(), chooser
							.getAnnee());
					fr.setAnnee(annee);
					fr.setClasse(classe);
					fr.setVisible(true);
					chooser.close();
					ConfigClasse.this.close();
				}
			});
			chooser.setVisible(true);
		}

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

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
