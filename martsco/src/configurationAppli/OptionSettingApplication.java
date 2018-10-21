package configurationAppli;

import graphicsModel.MartList;

public class OptionSettingApplication extends AbstractOptionSetting {
	private ConfCommandes confCommandes;

	public OptionSettingApplication() {
		confCommandes = new ConfCommandes("Complements");
	}

	public MartList<AbstractConfigPanel> set() {
		this.add(confCommandes);
		return this;
	}

}
