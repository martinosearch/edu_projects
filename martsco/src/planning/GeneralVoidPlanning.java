package planning;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import annee.Annee;
import annee.ChooserAnnee;
import configurationPlanning.ConfigPlanning;
import connection.DAO;
import connection.DAOFactory;

public class GeneralVoidPlanning {
	protected ConfigPlanning conf;
	private DAO andao;
	private String annee;
	private Frame trimestre;

	public void configPlanning() {
		ChooserAnnee chooser = ChooserAnnee.getInstance();
		chooser.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conf = new ConfigPlanning(chooser.getAnnee());
				conf.setVisible(true);
			}
		});

		chooser.setVisible(true);
	}

	public static void main(String[] args) {
		new GeneralVoidPlanning().nouveauActivite();
	}

	public void nouveauActivite() {
		ChooserAnnee chooser = ChooserAnnee.getInstance();
		chooser.setAction(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new CreateActivite(chooser.getAnnee());
			}
		});

		chooser.setVisible(true);
	}
}
