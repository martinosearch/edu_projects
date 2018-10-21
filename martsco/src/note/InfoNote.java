package note;

import abstractObject.Rapport;
import eleve.Eleve;

public class InfoNote {
	Eleve eleve;
	String note1 = "", note2 = "", dev = "", compo = "", coefCons = "";
	double moyInt, moyCls, moy, ptObt;
	String moyClsStr, moyStr, ptObtStr;
	String rang;
	boolean hasCompose = false;
	private int model;
	private double surTotal;

	public InfoNote() {

	}

	public void setInfo(String not1, String not2, String d, String c,
			double moyClasse, double moy, double ptObt, String rang,
			boolean hascomp) {
		note1 = not1;
		note2 = not2;
		dev = d;
		compo = c;

		this.moyCls = moyClasse;
		this.moy = moy;
		this.ptObt = ptObt;
		this.rang = rang;
		this.hasCompose = hascomp;

		update();
	}

	private void update() {

	}

	public String getNote1() {
		return note1;
	}

	public String getNote2() {
		return note2;
	}

	public String getDev() {
		return dev;
	}

	public String getCompo() {
		return compo;
	}

	public double getmoyCls() {
		return moyCls;
	}

	public double getmoy() {
		return moy;
	}

	public double getPtObt() {
		return ptObt;
	}

	public String getRang() {
		return rang;
	}

	public String getmoyClsStr() {
		return this.moyClsStr;
	}

	public String getmoyStr() {
		return this.moyStr;
	}

	public String getPtObtStr() {
		return this.ptObtStr;
	}

	public String getCoefConsidered() {
		return coefCons;
	}

	public void setCoefCons(String cfc) {
		this.coefCons = cfc;
	}

	public void setMoyStr(String moycls, String moy, String ptObt) {
		this.moyClsStr = moycls;
		this.moyStr = moy;
		this.ptObtStr = ptObt;

		update();
	}

	public boolean hasCompose() {
		return this.hasCompose;
	}

	public void setModel(int mod) {
		model = mod;
	}

	public String getSurTotal() {
		String str = "";
		double coef = Double.parseDouble(coefCons);
		if (coef > 0) {
			if (model == Rapport.MODEL_PRIM) {
				surTotal = coef * 10;
			} else {
				surTotal = Double.parseDouble(coefCons) * 20;
			}
		}
		// System.out.println("=======================================>> "
		// + coefCons + " " + surTotal);

		str = String.valueOf(surTotal);
		return str;
	}

	public void setSurTotal(double surTotal) {
		this.surTotal = surTotal;
	}
}
