package configurationEcolage;

import graphicsModel.MartList;

import java.awt.Dimension;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.AbstractOptionSetting;

@SuppressWarnings("serial")
public class OptionSettingEcolage extends AbstractOptionSetting {
	private Dimension dimPanes = new Dimension(480, 80);
	private ConfEcolage confEcolage;
	private ConfFraisInscription confFraisInscription;

	public OptionSettingEcolage() {

	}

	public MartList<AbstractConfigPanel> set() {
		confEcolage = new ConfEcolage("ECOLAGE");
		confEcolage.setAnnee(annee);
		confEcolage.refresh();

		confFraisInscription = new ConfFraisInscription("FRAIS D'INSCRIPTION");
		confFraisInscription.setAnnee(annee);
		confFraisInscription.refresh();

		confFraisInscription.setAnnee(annee);
		confFraisInscription.refresh();

		confEcolage.setPreferredSize(dimPanes);
		confFraisInscription.setPreferredSize(dimPanes);

		this.add(confEcolage);
		this.add(confFraisInscription);

		return this;
	}

	public Object findEcolage(String str) {
		Object set = null;
		try {
			set = confEcolage.find(str);
		} catch (Exception e) {

		}
		return set;
	}

	public Object findFraisInscription(String str) {
		Object set = null;
		try {
			set = confFraisInscription.find(str);
		} catch (Exception e) {

		}
		return set;
	}

	public Object findSalaire(String str) {
		Object set = null;
		try {
			set = confEcolage.find(str);
		} catch (Exception e) {

		}
		return set;
	}
}
