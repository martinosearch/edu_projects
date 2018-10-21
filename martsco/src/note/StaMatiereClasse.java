package note;

public class StaMatiereClasse {
	int eff, ontComp, nMoy;
	double Hnote;
	double Lnote;
	double Moy_Cls;
	String percent;

	public void setPercent(String str) {
		this.percent = str;
	}

	public void setNMoy(int nbre) {
		this.nMoy = nbre;
	}

	public void setOntComp(int Comp) {
		this.ontComp = Comp;
	}

	public void setLMoy(double lmoy) {
		this.Lnote = lmoy;

	}

	public void setMoyCls(double moyCls) {
		this.Moy_Cls = moyCls;
	}

	public void setHMoy(double hmoy) {
		this.Hnote = hmoy;
	}

	public void setEff(int effectif) {
		this.eff = effectif;
	}

	public int getEff() {
		return this.eff;
	}

	public int getOntComp() {
		return this.ontComp;
	}

	public int getNMoy() {
		return this.nMoy;
	}

	public String getPercent() {
		return this.percent;
	}

	public double getHNote() {
		return this.Hnote;
	}

	public double getLnote() {
		return this.Lnote;
	}

	public double getMoy_Cls() {
		return this.Moy_Cls;
	}

}
