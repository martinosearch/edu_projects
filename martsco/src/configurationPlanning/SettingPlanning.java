package configurationPlanning;

import graphicsModel.MartList;

import java.awt.Dimension;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.AbstractOptionSetting;

@SuppressWarnings("serial")
public class SettingPlanning extends AbstractOptionSetting {
	private Dimension dimPanes = new Dimension(480, 80);
	private ConfHeur confHeur;
	private ConfJour confJour;
	private String annee;
	private ConfContrainte confContrainte;

	public SettingPlanning() {

	}

	public MartList<AbstractConfigPanel> set() {
		confHeur = new ConfHeur("HEURES DE LA JOURNEE");
		confHeur.setAnnee(annee);
		confHeur.refresh();

		confJour = new ConfJour("JOURS DE LA SEMAINE");
		confJour.setAnnee(annee);
		confJour.refresh();

		confContrainte = new ConfContrainte("CONTRAINTES");
		confJour.setAnnee(annee);
		confJour.refresh();

		confHeur.setPreferredSize(dimPanes);
		confJour.setPreferredSize(dimPanes);

		this.add(confJour);
		this.add(confHeur);
		this.add(confContrainte);

		return this;
	}

	public void setAnnee(String an) {
		annee = an;
	}

	public Object findEcolage(String str) {
		Object set = null;
		try {
			set = confHeur.find(str);
		} catch (Exception e) {

		}
		return set;
	}

	public Object findFraisInscription(String str) {
		Object set = null;
		try {
			set = confJour.find(str);
		} catch (Exception e) {

		}
		return set;
	}

	public Object findSalaire(String str) {
		Object set = null;
		try {
			set = confHeur.find(str);
		} catch (Exception e) {

		}
		return set;
	}
}
