package note;

import abstractObject.Rapport;
import configurationEcole.ConfigEcole;
import function.MartConverter;
import interfacePerso.MartRangeable;
import lombok.Data;

@Data
public class Moyenne implements MartRangeable {
	double total = 0;
	double moyenne = 0;
	double moyennePassage;
	double totalCoef = 0;
	String id = "", rgstr = "";
	Moyenne lit;
	Moyenne scien;
	Moyenne fac;
	int rang = 0;
	boolean hasComposeGen = true;
	boolean succed = false;
	private int model;
	private Moyenne moyAppr;
	private boolean isEndOfYear;
	private int trimestre;
	private String annee;
	private ConfigEcole conf;
	private boolean decisionAuto;
	private Object niveau;

	public Moyenne(int mod) {
		model = mod;
	}

	public Moyenne(double tot, double totCoef, double moy, int mod) {
		this.total = tot;
		this.totalCoef = totCoef;
		this.moyenne = moy;
		model = mod;
	}

	public void setHasComposeGen(boolean hcomp) {
		hasComposeGen = hcomp;
	}

	public boolean hasCompose() {
		return hasComposeGen;
	}

	public double getGrdTotal() {
		return total;
	}

	public double getMoyGen() {
		return moyenne;
	}

	public double getTotCoef() {
		return totalCoef;
	}

	public double getSurTotal() {
		double surtot = 0;

		if (model == Rapport.MODEL_PRIM) {
			surtot = getTotCoef() * 10;
		}

		if (model == Rapport.MODEL_SECOND) {
			surtot = getTotCoef() * 20;
		}

		return surtot;
	}

	@Override
	public String toString() {
		System.out.println("la moyenne est:" + getMoyGen());
		return MartConverter.convertLetters(getMoyGen());
	}

	public String getMention() {
		String mention = "";
		double moy = getMoyGen();
		if (moy < 10)
			mention = "Insuffisant";
		else if (moy < 12)
			mention = "Passable";
		else if (moy < 14)
			mention = "Assez-bien";
		else if (moy < 17)
			mention = "Bien";
		else
			mention = "Très-bien";

		return mention;
	}

	// ***********les moyenne partielle****************
	public void setLit(Moyenne moy) {
		this.lit = moy;
	}

	public Moyenne getLit() {
		return this.lit;
	}

	public void setScien(Moyenne moy) {
		this.scien = moy;
	}

	public Moyenne getScien() {
		return this.scien;
	}

	public void setFac(Moyenne moy) {
		this.fac = moy;
	}

	public Moyenne getFac() {
		return this.fac;
	}

	// ***************fin partielle*****************

	@Override
	public String getIntitule() {
		return this.id;
	}

	@Override
	public void setIntitule(String inti) {
		this.id = inti;
	}

	public boolean hasSucced() {
		if (model == Rapport.MODEL_PRIM) {
			double moyennePass = 5;
			if (trimestre == 3) {
				moyennePass = moyennePassage;
			}

			if (moyenne >= moyennePass) {
				succed = true;
			} else {
				succed = false;
			}

			System.out.println("c'est moi PRIM=========================>>" + moyenne);
		}

		if (model == Rapport.MODEL_SECOND) {
			if (moyenne >= 10) {
				succed = true;
			} else {
				succed = false;
			}

			System.out.println("c'est moi COL=========================>>" + moyenne);
		}
		return succed;
	}

	@Override
	public int getRang() {
		return this.rang;
	}

	@Override
	public void setRang(int rg) {
		this.rang = rg;
	}

	@Override
	public double getValue() {
		return getMoyGen();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id2) {
		this.id = id2;
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	public String getObs(double d) {
		String obs = "";

		if (d == 0) {
			obs = "<div>Travail nul</div>";
		} else if (d < 3) {
			obs = "<div>Travail presque nul</div>";
		} else if (d < 5) {
			obs = "<div>Travail très- faible</div>";
		} else if (d < 7) {
			obs = "<div>Travail faible</div>";
		} else if (d < 8) {
			obs = "<div>Travail médiocre</div>";
		} else if (d < 9) {
			obs = "<div>Travail très- insuffisant</div>";
		} else if (d < 10) {
			obs = "<div>Travail &#160; insuffisant</div>";
		} else if (d < 12) {
			obs = "<div>Travail acceptable</div>";
		} else if (d < 14) {
			obs = "<div>Travail assez- bien</div>";
		} else if (d < 16) {
			obs = "<div>Bon travail</div>";
		} else {
			obs = "<div>Très- bon travail</div>";
		}

		System.out.println("==========================>> trimestre= " + trimestre + " Annee= " + annee);
		if (isEndOfYear) {
			boolean isExamClass = false;
			if (niveau.equals("CM2") || niveau.equals("3ème") || niveau.equals("1ère A4") || niveau.equals("1ère D")
					|| niveau.equals("1ère C")) {
				isExamClass = true;
			}

			if (decisionAuto && !isExamClass) {
				if (d >= 10) {
					obs += "<div>Passe en classe supérieure</div>";
				} else if (d >= moyennePassage) {
					obs += "<div>Passe en classe supérieure par indulgence du conseil.</div>";
				} else {
					obs += "<div>Redouble sa classe</div>";
				}
			} else {
				obs += "<div></div>";
			}

		}
		return obs;
	}

	public void setEndOfYear(boolean b) {
		isEndOfYear = true;
	}

	// TODO
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public void setMoyennePassage(double moyPass) {
		// TODO Auto-generated method stub
		this.moyennePassage = moyPass;
	}

	public void setDecisionAuto(boolean decisionAuto) {
		// TODO Auto-generated method stub
		this.decisionAuto = decisionAuto;
	}

	public void setTrimestre(int trimestre) {
		// TODO Auto-generated method stub
		this.trimestre = trimestre;
	}

	public void setAnnee(String annee) {
		// TODO Auto-generated method stub
		this.annee = annee;
	}
}
