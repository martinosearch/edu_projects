package componentFactory;

import graphicsModel.Design;
import graphicsModel.MartFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import configurationAppli.Setting;

public class MenuFactory {

	public MartMenu getLookAndFeelsManager(final MartFrame frame) {
		MartMenu menu = new MartMenu("Affichage");
		ButtonGroup bg = new ButtonGroup();
		Map<String, String> map = Design.getLookAndFeelsMap();

		for (String clef : map.keySet()) {
			final String classe = map.get(clef);
			String look = Design.getCurrentLookAndFeel();
			boolean isTheCurrent = classe.equals(look);

			JRadioButtonMenuItem item = new JRadioButtonMenuItem(clef,
					isTheCurrent);

			item.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					try {
						UIManager.setLookAndFeel(classe);
						Design.savePreference(new Setting("lookAndFeel", classe));
					} catch (Exception e) {
						e.printStackTrace();
					}

					SwingUtilities.updateComponentTreeUI(frame);
				}
			});

			bg.add(item);
			menu.add(item);
		}
		return menu;
	}

	public MartMenu getEcole() {
		MartMenu menu = new MartMenu("Ecole");
		return menu;
	}

	public JMenu getClasse() {
		MartMenu menu = new MartMenu("Classe");
		return menu;
	}

	public JMenu getApplication() {
		MartMenu menu = new MartMenu("Application");
		return menu;
	}

	public JMenu getApropos() {
		MartMenu menu = new MartMenu("Apropos");
		return menu;
	}

	public JMenu getAdmin() {
		MartMenu menu = new MartMenu("Administration");
		return menu;
	}

	public JMenu getCompta() {
		MartMenu menu = new MartMenu("Comptabilité");
		return menu;
	}

	public JMenu getExamen() {
		MartMenu menu = new MartMenu("Examen");
		return menu;
	}
}
