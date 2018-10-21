package configurationClasse;

import graphicsModel.MartList;
import interfacePerso.Observer;
import annee.Decoupage;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.AbstractOptionSetting;

public class OptionSettingClasse extends AbstractOptionSetting implements
		Observer {
	public ConfMatiereProg confMatiere;
	public ConfAutresInfosClasse confAutresInfo;

	public OptionSettingClasse() {

	}

	public MartList<AbstractConfigPanel> set() {
		confMatiere = new ConfMatiereProg("Matières au programme");
		confMatiere.setClasse(classe);
		confMatiere.setAnnee(annee);
		confMatiere.refresh();

		confAutresInfo = new ConfAutresInfosClasse("Autres Infos");
		confAutresInfo.setClasse(classe);
		confAutresInfo.setAnnee(annee);
		confAutresInfo.refresh();

		this.add(confMatiere);
		this.add(confAutresInfo);

		return this;
	}

	public String getNotesFont() {
		return (String) confMatiere.find("policeNote");
	}

	public Object findSetting(String str) {
		Object set = null;
		try {
			set = confMatiere.find(str);
		} catch (Exception e) {

		}
		return set;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	public Decoupage getDecoupage() {
		return confAutresInfo.getDecoupage();
	}
}
