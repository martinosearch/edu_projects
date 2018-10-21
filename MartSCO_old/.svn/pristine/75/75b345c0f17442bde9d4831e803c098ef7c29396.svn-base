package componentFactory;

import ecolage.CaisseOption;
import eleve.AjouterEleveClasse;
import graphicsModel.MartFrame;
import graphicsModel.OptionEditorFrame;
import graphicsModel.OptionItem;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.tools.OptionChecker;

import configurationAdmin.ConfigAdmin;
import configurationClasse.ConfigClasse;
import configurationEcolage.ConfigEcolage;
import OptionPane.OptionSaisieNote;
import OptionPane.OptionRapportEco;
import annee.ChooserAnnee;
import progress.Avancer;
import rapportBulletin.DocumentAdmin;
import salaire.OptionSalaire;
import classe.ChooserClasse;
import classe.ListeEleveClasse;

public class ItemsCompta extends ItemsEditorPanel {

	private OptionItem caisse;
	private OptionItem gestionSal;
	private OptionItem config;
	private OptionItem rapport;

	public ItemsCompta() {
		setTitre("Comptabilité");
		caisse = new OptionItem("img_caisse.png",
				"<div>Caisse<div><div id='explication'>Versement de fonds</div>");
		gestionSal = new OptionItem(
				"img_salaire.png",
				"<div>Gestion salaire</div><div id='explication'>Payer le salaire/ Définir le salaire</div>");
		rapport = new OptionItem(
				"img_rapport.png",
				"<div>Journal des Entrées</div><div id='explication'>Différents rapport relatifs à la comptabilité.</div>");
		config = new OptionItem(
				"img_reglage.png",
				"<div>Configurations</div><div id='explication'>Les configurations relatives à la Comptabilité</div>");

		addElement(caisse);
		addElement(gestionSal);
		addElement(rapport);
		addElement(config);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Component source = (Component) e.getSource();
		final ChooserAnnee chooser = ChooserAnnee.getInstance();
		chooser.setTrimestreChoosing(false);
		chooser.setLocation(MartFrame.INTERNAL_FRAME_LOCATION);

		if (source == caisse) {
			CaisseOption.getInstance().setVisible(true);
		}

		if (source == rapport) {
			OptionRapportEco.getInstance().setVisible(true);
		}

		if (source == gestionSal) {
			chooser.setAction(new ActionListener() {
				private OptionEditorFrame fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = OptionSalaire.getInstance();
					fr.setVisible(true);
					chooser.close();
				}
			});
			chooser.setVisible(true);
		}

		if (source == config) {
			chooser.setAction(new ActionListener() {
				private ConfigEcolage fr;

				public void actionPerformed(ActionEvent arg0) {
					fr = new ConfigEcolage(chooser.getAnnee());
					fr.setVisible(true);
					chooser.close();
				}
			});
			chooser.setVisible(true);
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
}
