package configurationEcole;

import configurationAppli.AbstractConfigPanel;
import configurationAppli.AbstractOptionSetting;
import function.MartConverter;
import graphicsModel.MartList;

public class OptionSettingBulletin extends AbstractOptionSetting {
    private ConfBulletin confBulletin;
    private ConfPolice confPolice;
    private ConfCoefficient confCoef;
    private ConfStaNoteCompo confStaNoteCompo;
    private ConfStaMoyTrim confStaMoyTrim;
    private ConfStaMoyAn confStaMoyAn;
    private ConfOrdreMatieres confMatieres;
    private ConfModelBulletin confModelBull;

    public OptionSettingBulletin() {

    }

    @Override
    public MartList<AbstractConfigPanel> set() {
	confBulletin = new ConfBulletin("Format du Bulletin");
	confBulletin.setAnnee(annee);
	confBulletin.refresh();

	confPolice = new ConfPolice("Police sur le Bulletin");
	confPolice.setAnnee(annee);
	confPolice.refresh();

	confStaNoteCompo = new ConfStaNoteCompo("Sta. Note Compo");
	confStaNoteCompo.setAnnee(annee);
	confStaNoteCompo.refresh();

	confStaMoyTrim = new ConfStaMoyTrim("Sta Moy. Trimestrielles");
	confStaMoyTrim.setAnnee(annee);
	confStaMoyTrim.refresh();

	confStaMoyAn = new ConfStaMoyAn("Sta Moy. Annuelles");
	confStaMoyAn.setAnnee(annee);
	confStaMoyAn.refresh();

	confCoef = new ConfCoefficient("Coefficient");
	confCoef.setAnnee(annee);
	confCoef.refresh();

	confMatieres = new ConfOrdreMatieres("Ordre des Matières");

	confModelBull = new ConfModelBulletin("Model de bulletin");
	confModelBull.setAnnee(annee);
	confModelBull.refresh();

	this.add(confBulletin);
	this.add(confCoef);
	this.add(confStaNoteCompo);
	this.add(confStaMoyTrim);
	this.add(confStaMoyAn);
	this.add(confPolice);
	this.add(confMatieres);
	this.add(confModelBull);

	return this;
    }

    public boolean numOrdre() {
	boolean b = (boolean) confBulletin.find("numOrdre");
	return b;
    }

    public boolean matiere() {
	boolean b = (boolean) confBulletin.find("matiere");
	return b;
    }

    public boolean noteClasse() {
	boolean b = (boolean) confBulletin.find("noteClasse");
	return b;
    }

    public boolean moyClasse() {
	boolean b = (boolean) confBulletin.find("moyClasse");
	return b;
    }

    public boolean noteCompo() {
	boolean b = (boolean) confBulletin.find("noteCompo");
	return b;
    }

    public boolean moyGenerale() {
	boolean b = (boolean) confBulletin.find("moyGen");
	return b;
    }

    public boolean ptObtenus() {
	boolean b = (boolean) confBulletin.find("ptObtenus");
	return b;
    }

    public boolean prof() {
	boolean b = (boolean) confBulletin.find("prof");
	return b;
    }

    public boolean signature() {
	boolean b = (boolean) confBulletin.find("signature");
	return b;
    }

    public boolean logoEtt() {
	boolean b = (boolean) confBulletin.find("logoEntete");
	return b;
    }

    public boolean logoFond() {
	boolean b = (boolean) confBulletin.find("logoFond");
	return b;
    }

    public boolean filigranne() {
	boolean b = (boolean) (confBulletin.find("filigrane"));
	return b;
    }

    public boolean photoEleve() {
	return (boolean) confBulletin.find("photoEleve");
    }

    public boolean bullBloc() {
	return (boolean) confBulletin.find("bullBloc");
    }

    public boolean decisionAuto() {
	boolean b = (boolean) confBulletin.find("decisionAuto");
	return b;
    }

    public boolean dirNom() {
	boolean b = (boolean) confBulletin.find("nomChef");
	return b;
    }

    public boolean titNom() {
	boolean b = (boolean) confBulletin.find("nomTit");
	return b;
    }

    public boolean dirSign() {
	boolean b = (boolean) confBulletin.find("signChef");
	return b;
    }

    public boolean titSign() {
	boolean b = (boolean) confBulletin.find("signTit");
	return b;
    }

    // Pour recuppérer le coefficient
    public double getCoef(String mat, String niveau) {
	return confCoef.getCoef(mat, niveau);
    }

    public String getNotesFont() {
	return (String) confPolice.find("policeNote");
    }

    public int getStaNoteCompoRowH() {
	int pt = 0;
	double cm = (double) confStaNoteCompo.find("hCellule");
	pt = MartConverter.getPtValue(cm);

	return pt;
    }

    public int getStaMoyTrimRowH() {
	int pt = 0;
	double cm = (double) confStaMoyTrim.find("hCellule");
	pt = MartConverter.getPtValue(cm);

	return pt;
    }

    public int getStaMoyAnRowH() {
	int pt = 0;
	double cm = (double) confStaMoyAn.find("hCellule");
	pt = MartConverter.getPtValue(cm);
	return pt;
    }

    public int getTypeStaNoteCompo() {
	return (int) confStaNoteCompo.find("typeSta");
    }

    public int getTypeStaMoyTrim() {
	return (int) confStaMoyTrim.find("typeSta");
    }

    public int getTypeStaMoyAn() {
	return (int) confStaMoyAn.find("typeSta");
    }

    public int getModelBull() {
	return confModelBull.getModel();
    }
}
