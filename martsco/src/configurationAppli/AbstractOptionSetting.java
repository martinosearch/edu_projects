package configurationAppli;

import graphicsModel.MartList;

public class AbstractOptionSetting extends MartList<AbstractConfigPanel> {
	protected String classe;
	protected String annee;

	// les sauvegarde se font directement dans le conteneur de config
	// AbstractConfig

	public void setClasse(String classe2) {
		classe = classe2;
	}

	public void setAnnee(String an) {
		annee = an;
	}

	public String getClasse() {
		return classe;
	}

	public String getAnnee() {
		return annee;
	}

}
