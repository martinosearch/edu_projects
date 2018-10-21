package configurationExamen;

import function.MartComputer;
import function.MartConverter;
import graphicsModel.MartList;
import configurationAdmin.ConfAutresInfos;
import configurationAppli.AbstractConfigPanel;
import configurationAppli.AbstractOptionSetting;
import configurationEcole.ConfCoefficient;
import configurationEcole.ConfPolice;
import configurationEcole.ConfStaMoyTrim;

public class OptionSettingReleve extends AbstractOptionSetting {
	private ConfPolice confPolice;
	private ConfCoefficient confCoef;
	private ConfReleve confReleve;
	private ConfAutresInfosExam confAutresInfos;
	private ConfStaMoyTrim confStaMoyTrim;
	private ConfEtablissement confEts;
	private ConfMatiereProgExam confMatProg;
	private ConfRepEnSalle confRepEnSalle;
	private ConfOptionSaisie confOptionSaisie;

	public String getAnnee() {
		return annee;
	}

	public OptionSettingReleve() {

	}

	public MartList<AbstractConfigPanel> set() {
		confReleve = new ConfReleve("Format du Releve");
		confReleve.setAnnee(annee);
		confReleve.refresh();

		confPolice = new ConfPolice("Polices");
		confPolice.setAnnee(annee);
		confPolice.refresh();

		confCoef = new ConfCoefficient("Coefficient");
		confCoef.setAnnee(annee);
		confCoef.refresh();

		confStaMoyTrim = new ConfStaMoyTrim("Statistiques des moyennes");
		confStaMoyTrim.setAnnee(annee);
		confStaMoyTrim.refresh();

		confAutresInfos = new ConfAutresInfosExam("Autres Infos");
		confAutresInfos.setAnnee(annee);
		confAutresInfos.refresh();

		confEts = new ConfEtablissement("Etablissement convoqués");
		confEts.setAnnee(annee);
		confEts.refresh();

		confMatProg = new ConfMatiereProgExam("Matière au programme");
		confMatProg.setAnnee(annee);
		confMatProg.refresh();

		confRepEnSalle = new ConfRepEnSalle("Repartition en salles");
		confRepEnSalle.setAnnee(annee);
		confRepEnSalle.refresh();

		confOptionSaisie = new ConfOptionSaisie("Options de saisie");
		confOptionSaisie.setAnnee(annee);
		confOptionSaisie.refresh();

		this.add(confReleve);
		this.add(confCoef);
		this.add(confMatProg);
		this.add(confPolice);
		this.add(confEts);
		this.add(confAutresInfos);
		this.add(confStaMoyTrim);
		this.add(confRepEnSalle);
		this.add(confOptionSaisie);

		return this;
	}

	// Pour recuppérer le coefficient
	public double getCoef(String mat, String niveau) {
		return confCoef.getCoef(mat, niveau);
	}

	public String getNotesFont() {
		return (String) confPolice.find("policeNote");
	}

	public boolean numOrdre() {
		boolean b = (boolean) confReleve.find("numOrdre");
		return b;
	}

	public boolean matiere() {
		boolean b = (boolean) confReleve.find("matiere");
		return b;
	}

	public boolean noteClasse() {
		boolean b = (boolean) confReleve.find("noteClasse");
		return b;
	}

	public boolean moyClasse() {
		boolean b = (boolean) confReleve.find("moyClasse");
		return b;
	}

	public boolean noteCompo() {
		boolean b = (boolean) confReleve.find("noteCompo");
		return b;
	}

	public boolean moyGenerale() {
		boolean b = (boolean) confReleve.find("moyGen");
		return b;
	}

	public boolean ptObtenus() {
		boolean b = (boolean) confReleve.find("ptObtenus");
		return b;
	}

	public boolean prof() {
		boolean b = (boolean) confReleve.find("prof");
		return b;
	}

	public boolean signature() {
		boolean b = (boolean) confReleve.find("signature");
		return b;
	}

	public boolean logoEtt() {
		boolean b = (boolean) confReleve.find("logoEntete");
		System.out.println("Resultat" + b);
		return b;
	}

	public boolean logoFond() {
		boolean b = (boolean) confReleve.find("logoFond");
		return b;
	}

	public boolean filigranne() {
		boolean b = (boolean) (confReleve.find("filigrane"));
		return b;
	}

	public boolean photoEleve() {
		boolean b = (boolean) confReleve.find("photoEleve");
		return b;
	}

	public boolean bullBloc() {
		boolean b = (boolean) confReleve.find("bullBloc");
		return b;
	}

	public boolean dirNom() {
		boolean b = (boolean) confReleve.find("nomChef");
		return b;
	}

	public boolean titNom() {
		boolean b = (boolean) confReleve.find("nomTit");
		return b;
	}

	public boolean dirSign() {
		boolean b = (boolean) confReleve.find("signChef");
		return b;
	}

	public boolean titSign() {
		boolean b = (boolean) confReleve.find("signTit");
		return b;
	}

	public Object findSetting(String str) {
		Object set = null;
		try {
			set = confAutresInfos.find(str);
		} catch (Exception e) {

		}
		return set;
	}

	public int getStaMoyTrimRowH() {
		int pt = 0;
		double cm = (double) confStaMoyTrim.find("hCellule");
		pt = MartConverter.getPtValue(cm);

		return pt;
	}

	public int getTypeStaMoyTrim() {
		return (int) confStaMoyTrim.find("typeSta");
	}

	public int getTypeReleve() {
		int type = 1;
		if ((boolean) confReleve.find("typeReleve1") == true) {
			type = 1;
		}
		if ((boolean) confReleve.find("typeReleve2") == true) {
			type = 2;
		}
		return type;
	}

	public String getCentre() {
		return confEts.getCentre();
	}

	public MartList<String> getEtsPerso() {
		return confEts.getEtsPerso();
	}

	public int getNbreSalle() {
		// TODO Auto-generated method stub
		return confRepEnSalle.getNbreSalle();
	}

	public int getEffSalle(int i) {
		// return 20;
		return confRepEnSalle.getEffSalle(i - 1);
	}

	public boolean getAfficherNom() {
		return confOptionSaisie.getAfficherNom();

	}

	public int getStaMoyHRow() {
		// TODO Auto-generated method stub
		int val = 1;
		try {
			val = MartConverter.getPtValue((double) confStaMoyTrim
					.find("hCellule"));
		} catch (Exception e) {

		}
		return val;
	}
}
