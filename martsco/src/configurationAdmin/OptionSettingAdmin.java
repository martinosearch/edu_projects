package configurationAdmin;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.AbstractOptionSetting;
import function.Constance;
import graphicsModel.MartList;

public class OptionSettingAdmin extends AbstractOptionSetting {
    private ConfAutresInfos confAutresInfos;
    private ConfResponsable confResponsable;
    private ConfReferences confReferences;

    public OptionSettingAdmin() {

    }

    @Override
    public MartList<AbstractConfigPanel> set() {
	confAutresInfos = new ConfAutresInfos("Info utiles");
	confAutresInfos.setAnnee(annee);
	confAutresInfos.refresh();

	confResponsable = new ConfResponsable("Responsables");
	confResponsable.setAnnee(annee);
	confResponsable.refresh();

	confReferences = new ConfReferences("Reférences de l'Etablissement");

	this.add(confReferences);
	this.add(confAutresInfos);
	this.add(confResponsable);

	return this;
    }

    public Object findSetting(String str) {
	Object set = null;
	try {
	    set = confAutresInfos.find(str);
	} catch (Exception e) {

	}
	return set;
    }

    public double getMoyPass(String niveau) {
	niveau = Constance.getCor(niveau);
	double d = 10;
	try {
	    d = Double.parseDouble((String) findSetting("moyenne_passage_" + niveau));
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return d;
    }
}
