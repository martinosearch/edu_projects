package function;

import graphicsModel.MartList;
import interfacePerso.DataMat;
import interfacePerso.DataMoy;
import interfacePerso.MartRangeable;
import abstractObject.Rapport;
import configurationEcole.ConfigEcole;

public class Statistician implements DataMoy, DataMat {
	MartList<MatSta> listeMatSta = new MartList<MatSta>();
	int indexMatSta = 0;
	MartList<MoySta> listeMoySta = new MartList<MoySta>();
	int indexMoySta = 0;

	private int tsup18 = 0;
	private int tpersup18 = 0;
	private int tsup18g = 0;
	private int tpersup18g = 0;
	private int tsup18f = 0;
	private int tpersup18f = 0;

	private int tsup10 = 0;
	private int tsup10g = 0;
	private int tpersup10g = 0;
	private int tsup10f = 0;
	private int tpersup10f = 0;

	private int tinf18 = 0;
	private int tperinf18 = 0;
	private int tinf18g = 0;
	private int tperinf18g = 0;
	private int tinf18f = 0;
	private int tperinf18f = 0;

	private int tinf16 = 0;
	private int tperinf16 = 0;
	private int tinf16g = 0;
	private int tperinf16g = 0;
	private int tinf16f = 0;
	private int tperinf16f = 0;

	private int tinf14 = 0;
	private int tperinf14 = 0;
	private int tinf14g = 0;
	private int tperinf14g = 0;
	private int tinf14f = 0;
	private int tperinf14f = 0;

	private int tinf12 = 0;
	private int tperinf12 = 0;
	private int tinf12g = 0;
	private int tperinf12g = 0;
	private int tinf12f = 0;
	private int tperinf12f = 0;

	private int tinf10 = 0;
	private int tperinf10 = 0;
	private int tinf10g = 0;
	private int tperinf10g = 0;
	private int tinf10f = 0;
	private int tperinf10f = 0;

	private int tinf8 = 0;
	private int tperinf8 = 0;
	private int tinf8g = 0;
	private int tperinf8g = 0;
	private int tinf8f = 0;
	private int tperinf8f = 0;

	private int tinf6 = 0;
	private int tperinf6 = 0;
	private int tinf6g = 0;
	private int tperinf6g = 0;
	private int tinf6f = 0;
	private int tperinf6f = 0;

	private int tinf4 = 0;
	private int tperinf4 = 0;
	private int tinf4g = 0;
	private int tperinf4g = 0;
	private int tinf4f = 0;
	private int tperinf4f = 0;

	private int tinf2 = 0;
	private int tperinf2 = 0;
	private int tinf2g = 0;
	private int tperinf2g = 0;
	private int tinf2f = 0;
	private int tperinf2f = 0;

	private int tmoycls = 0;
	private int tpresent = 0;
	private int tinscritf = 0;
	private int tmoyclsg = 0;
	private int tpresentg = 0;
	private int tinscritg = 0;
	private int tmoyclsf = 0;
	private int tpresentf = 0;
	private int tinscrit = 0;
	ConfigEcole conf;
	private double mpass = 10;
	private int model = 0;
	private int div = 1;

	public Statistician() {
		listeMatSta = new MartList<MatSta>();
		listeMoySta = new MartList<MoySta>();
	}

	public void setModel(int mod) {
		model = mod;
		if (model == Rapport.MODEL_PRIM) {
			div = 2;
		} else {
			div = 1;
		}
	}

	public void addMatiere(String matiere) {
		int search = 0;

		for (MatSta msta : listeMatSta) {
			if (msta.getIntitule().equals(matiere)) {
				search++;
			}
		}
		if (search == 0) {
			listeMatSta.add(new MatSta(matiere));
			indexMatSta++;
		}
	}

	@Override
	public void addClasse(String classe) {
		int search = 0;
		for (MoySta msta : listeMoySta) {
			if (msta.getIntitule().equals(classe)) {
				search++;
			}
		}
		if (search == 0) {
			listeMoySta.add(new MoySta(classe));
			indexMoySta++;
		}
	}

	public MatSta getCurrentMatSta() {
		return listeMatSta.get(indexMatSta);
	}

	public MartList<MatSta> getResultMat() {
		MartList<MatSta> temp = new MartList<MatSta>();
		for (MatSta mat1 : listeMatSta) {
			if (mat1.getPresent() != 0) {
				temp.add(mat1);
			}
		}
		listeMatSta = temp;
		return listeMatSta;
	}

	public MartList<MoySta> getResultMoy() {
		MartList<MoySta> temp = new MartList<MoySta>();
		for (MoySta moySta : listeMoySta) {
			System.out
					.println("########################################### sta:"
							+ moySta.getIntitule() + " present: "
							+ moySta.getPresent());
			if (moySta.getPresent() != 0) {
				temp.add(moySta);
			}
		}

		listeMoySta = temp;
		return listeMoySta;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setDataMat(StaData data) {
		for (MatSta mat : listeMatSta) {
			if (mat.getIntitule().equals(data.getIntitule())) {
				mat.addData(data.getMoyenne(), data.getSexe(),
						data.hasCompose());
			}
		}
	}

	public void setDataMoy(StaData data) {
		for (MoySta moy2 : listeMoySta) {
			if (moy2.getIntitule().equals(data.getIntitule())) {
				moy2.addData(data.getMoyenne(), data.getSexe(),
						data.hasCompose());
			}
		}
	}

	@Override
	public MoySta getCurrentMoySta() {
		return listeMoySta.get(indexMoySta);
	}

	public class MatSta implements MartRangeable {
		// initialisation des variables pour mati�re
		private int inf2 = 0;
		private int inf4 = 0;
		private int inf6 = 0;
		private int inf8 = 0;
		private int inf10 = 0;
		private int inf12 = 0;
		private int inf14 = 0;
		private int inf16 = 0;
		private int inf18 = 0;
		private int sup18 = 0;
		private int sup10 = 0;
		private int present = 0;
		private double moycls = 0.0;
		private double lnote = 0.0;
		private double hnote = 0.0;
		private double total = 0.0;

		// variables pour garcon
		private int inf2g = 0;
		private int inf4g = 0;
		private int inf6g = 0;
		private int inf8g = 0;
		private int inf10g = 0;
		private int inf12g = 0;
		private int inf14g = 0;
		private int inf16g = 0;
		private int inf18g = 0;
		private int sup18g = 0;
		private int sup10g = 0;
		private int presentg = 0;
		private double moyclsg = 0.0;
		private double lnoteg = 0.0;
		private double hnoteg = 0.0;
		private double totalg = 0.0;

		// variables pour filles
		private int inf2f = 0;
		private int inf4f = 0;
		private int inf6f = 0;
		private int inf8f = 0;
		private int inf10f = 0;
		private int inf12f = 0;
		private int inf14f = 0;
		private int inf16f = 0;
		private int inf18f = 0;
		private int sup18f = 0;
		private int sup10f = 0;
		private int presentf = 0;
		private double moyclsf = 0.0;
		private double lnotef = 0.0;
		private double hnotef = 0.0;
		private double totalf = 0.0;

		private String intitule = "Nom_defaut";
		private int rang = 0;

		public MatSta(String inti) {
			this.intitule = inti;
		}

		public void addData(double moy, String sexe, boolean hcomp) {
			if (moy != 0.001) {
				if (present == 0) {
					lnote = moy;
					hnote = moy;
				} else {
					lnote = Math.min(lnote, moy);
					hnote = Math.max(hnote, moy);
				}

				if (hcomp == true) {
					total = total + moy;
					present++;
				}

				// *********dispatcher selon le sexe********
				if (sexe.equals("M") || sexe.equals("G")) {
					presentg++;
				} else {
					presentf++;
				}
				// **********fin de la manoeuvre************

				if (moy < 2 / div) {
					inf2++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf2g++;
					} else {
						inf2f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 4 / div) {
					inf4++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf4g++;
					} else {
						inf4f++;
					}
					// **********fin de la manoeuvre************
				}

				else if (moy < 6 / div) {
					inf6++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf6g++;
					} else {
						inf6f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 8 / div) {
					inf8++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf8g++;
					} else {
						inf8f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 10 / div) {
					inf10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf10g++;
					} else {
						inf10f++;
					}
					// **********fin de la manoeuvre************
				}

				else if (moy < 12 / div) {
					inf12++;
					sup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf12g++;
						sup10g++;
					} else {
						inf12f++;
						sup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 14 / div) {
					inf14++;
					sup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf14g++;
						sup10g++;
					} else {
						inf14f++;
						sup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 16 / div) {
					inf16++;
					sup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf16g++;
						sup10g++;
					} else {
						inf16f++;
						sup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 18 / div) {
					inf18++;
					sup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf18g++;
						sup10g++;
					} else {
						inf18f++;
						sup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy >= 18 / div) {
					sup18++;
					sup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						sup18g++;
						sup10g++;
					} else {
						sup18f++;
						sup10f++;
					}
					// **********fin de la manoeuvre************
				}

			}
		}

		public int getInf2() {
			return inf2;
		}

		public int getInf4() {
			return inf4;
		}

		public int getInf6() {
			return inf6;
		}

		public int getInf8() {
			return inf8;
		}

		public int getInf10() {
			return inf10;
		}

		public int getInf12() {
			return inf12;
		}

		public int getInf14() {
			return inf14;
		}

		public int getInf16() {
			return inf16;
		}

		public int getInf18() {
			return inf18;
		}

		public int getSup18() {
			return sup18;
		}

		public int getSup10() {
			return sup10;
		}

		public int getPresent() {
			return present;
		}

		public double getLNote() {
			return lnote;
		}

		public double getHNote() {
			return hnote;
		}

		public double getMoyCls() {
			double moycls = new Double(total / present);
			return moycls;
		}

		public String getIntitule() {
			return this.intitule;
		}

		public double getPerSup10() {
			double per;
			try {

				per = ((double) sup10 / (double) present) * 100;

			} catch (Exception e) {
				e.printStackTrace();
				per = 0;
			}
			return per;
		}

		// ascensseur pour gar�on******************************************
		public int getInf2g() {
			return inf2g;
		}

		public int getInf4g() {
			return inf4g;
		}

		public int getInf6g() {
			return inf6g;
		}

		public int getInf8g() {
			return inf8g;
		}

		public int getInf10g() {
			return inf10g;
		}

		public int getInf12g() {
			return inf12g;
		}

		public int getInf14g() {
			return inf14g;
		}

		public int getInf16g() {
			return inf16g;
		}

		public int getInf18g() {
			return inf18g;
		}

		public int getSup18g() {
			return sup18g;
		}

		public int getSup10g() {
			return sup10g;
		}

		public int getPresentg() {
			return presentg;
		}

		public double getLNoteg() {
			return lnoteg;
		}

		public double getHNoteg() {
			return hnoteg;
		}

		public double getMoyClsg() {
			double moycls = new Double(totalg / presentg);
			return moycls;
		}

		public double getPerSup10g() {
			double per;
			try {

				per = ((double) sup10g / (double) presentg) * 100;

			} catch (Exception e) {
				e.printStackTrace();
				per = 0;
			}
			return per;
		}

		// ascensseur pour fille******************************************
		public int getInf2f() {
			return inf2f;
		}

		public int getInf4f() {
			return inf4f;
		}

		public int getInf6f() {
			return inf6f;
		}

		public int getInf8f() {
			return inf8f;
		}

		public int getInf10f() {
			return inf10f;
		}

		public int getInf12f() {
			return inf12f;
		}

		public int getInf14f() {
			return inf14f;
		}

		public int getInf16f() {
			return inf16f;
		}

		public int getInf18f() {
			return inf18f;
		}

		public int getSup18f() {
			return sup18f;
		}

		public int getSup10f() {
			return sup10f;
		}

		public int getPresentf() {
			return presentf;
		}

		public double getLNotef() {
			return lnotef;
		}

		public double getHNotef() {
			return hnotef;
		}

		public double getMoyClsf() {
			double moycls = new Double(totalf / presentf);
			return moycls;
		}

		public double getPerSup10f() {
			double per;
			try {

				per = ((double) sup10f / (double) presentf) * 100;

			} catch (Exception e) {
				e.printStackTrace();
				per = 0;
			}
			return per;
		}

		@Override
		public void setIntitule(String inti) {
			this.intitule = inti;
		}

		@Override
		public String getId() {
			return this.intitule;
		}

		@Override
		public void setId(String id) {
			this.intitule = id;
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
			return 0;
		}

		@Override
		public void setValue(double val) {
			// TODO Auto-generated method stub

		}

	}

	/**
	 * classe pour les moyennes trimestrielles
	 * 
	 * @author Administrateur
	 *
	 */
	public class MoySta implements MartRangeable {
		// initialisation des variables pour mati�re
		private int inscrit = 0;
		private int inf2 = 0;
		private int inf4 = 0;
		private int inf6 = 0;
		private int inf8 = 0;
		private int inf10 = 0;
		private int inf12 = 0;
		private int inf14 = 0;
		private int inf16 = 0;
		private int inf18 = 0;
		private int sup18 = 0;
		private int sup10 = 0;
		private int present = 0;
		private double moycls = 0.0;
		private double lnote = 0.0;
		private double hnote = 0.0;
		private double total = 0.0;

		// variables pour garcon
		private int inscritg = 0;
		private int inf2g = 0;
		private int inf4g = 0;
		private int inf6g = 0;
		private int inf8g = 0;
		private int inf10g = 0;
		private int inf12g = 0;
		private int inf14g = 0;
		private int inf16g = 0;
		private int inf18g = 0;
		private int sup18g = 0;
		private int sup10g = 0;
		private int presentg = 0;
		private double moyclsg = 0.0;
		private double lnoteg = 0.0;
		private double hnoteg = 0.0;
		private double totalg = 0.0;

		// variables pour filles
		private int inscritf = 0;
		private int inf2f = 0;
		private int inf4f = 0;
		private int inf6f = 0;
		private int inf8f = 0;
		private int inf10f = 0;
		private int inf12f = 0;
		private int inf14f = 0;
		private int inf16f = 0;
		private int inf18f = 0;
		private int sup18f = 0;
		private int sup10f = 0;
		private int presentf = 0;
		private double moyclsf = 0.0;
		private double lnotef = 0.0;
		private double hnotef = 0.0;
		private double totalf = 0.0;

		private double mpassClasse = 10;
		private int nAdmis = 0;
		private int nAdmisg = 0;
		private int nAdmisf = 0;

		private String intitule = "Nom_defaut";
		private int rang = 0;

		public MoySta(String inti) {
			this.intitule = inti;
			mpassClasse = mpass;
		}

		public MoySta() {
			// TODO Auto-generated constructor stub
		}

		public void addData(double moy, String sexe, boolean hcomp) {
			inscrit++;
			tinscrit++;

			if (moy > mpassClasse) {
				nAdmis++;
				if (sexe.equals("M") || sexe.equals("G")) {
					nAdmisg++;
				} else {
					nAdmisf++;
				}
			}

			if (sexe.equals("M") || sexe.equals("G")) {
				inscritg++;
				tinscritg++;
			} else {
				inscritf++;
				tinscritf++;
			}

			if (moy != 0.001) {
				if (present == 0) {
					lnote = moy;
					hnote = moy;
				} else {
					if (moy > 0) {// pour ne pas consid�rer la moyenne 0 comme
									// la
						// plus faible.
						lnote = Math.min(lnote, moy);
						hnote = Math.max(hnote, moy);
					}
				}

				if (hcomp == true) {
					total = total + moy;
					present++;
					tpresent++;
				}

				// *********dispatcher selon le sexe********
				if (sexe.equals("M") || sexe.equals("G")) {
					presentg++;
					tpresentg++;
				} else {
					presentf++;
					tpresentf++;
				}
				// **********fin de la manoeuvre************

				if (moy < 2 / div) {
					inf2++;
					tinf2++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf2g++;
						tinf2g++;
					} else {
						inf2f++;
						tinf2f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 4 / div) {
					inf4++;
					tinf4++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf4g++;
						tinf4g++;
					} else {
						inf4f++;
						tinf4f++;
					}
					// **********fin de la manoeuvre************
				}

				else if (moy < 6 / div) {
					inf6++;
					tinf6++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf6g++;
						tinf6g++;
					} else {
						inf6f++;
						tinf6f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 8 / div) {
					inf8++;
					tinf8++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf8g++;
						tinf8g++;
					} else {
						inf8f++;
						tinf8f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 10 / div) {
					inf10++;
					tinf10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf10g++;
						tinf10g++;
					} else {
						inf10f++;
						tinf10f++;
					}
					// **********fin de la manoeuvre************
				}

				else if (moy < 12 / div) {
					inf12++;
					sup10++;
					tinf12++;
					tsup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf12g++;
						sup10g++;
						tinf12g++;
						tsup10g++;
					} else {
						inf12f++;
						sup10f++;
						tinf12f++;
						tsup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 14 / div) {
					inf14++;
					sup10++;
					tinf14++;
					tsup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf14g++;
						sup10g++;
						tinf14g++;
						tsup10g++;
					} else {
						inf14f++;
						sup10f++;
						tinf14f++;
						tsup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 16 / div) {
					inf16++;
					sup10++;
					tinf16++;
					tsup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf16g++;
						sup10g++;
						tinf16g++;
						tsup10g++;
					} else {
						inf16f++;
						sup10f++;
						tinf16f++;
						tsup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy < 18 / div) {
					inf18++;
					sup10++;
					tinf18++;
					tsup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						inf18g++;
						sup10g++;
						tinf18g++;
						tsup10g++;
					} else {
						inf18f++;
						sup10f++;
						tinf18f++;
						tsup10f++;
					}
					// **********fin de la manoeuvre************
				} else if (moy >= 18 / div) {
					sup18++;
					sup10++;
					tsup18++;
					tsup10++;
					// *********dispatcher selon le sexe********
					if (sexe.equals("M") || sexe.equals("G")) {
						sup18g++;
						sup10g++;
						tsup18g++;
						tsup10g++;
					} else {
						sup18f++;
						sup10f++;
						tsup18f++;
						tsup10f++;
					}
					// **********fin de la manoeuvre************
				}

			}
		}

		public int getInf2() {
			return inf2;
		}

		public double getPerInf2() {
			double per = ((double) inf2 / (double) present) * 100;
			return per;
		}

		public int getInf4() {
			return inf4;
		}

		public double getPerInf4() {
			double per = ((double) inf4 / (double) present) * 100;
			return per;
		}

		public int getInf6() {
			return inf6;
		}

		public double getPerInf6() {
			double per = ((double) inf6 / (double) present) * 100;
			return per;
		}

		public int getInf8() {
			return inf8;
		}

		public double getPerInf8() {
			double per = ((double) inf8 / (double) present) * 100;
			return per;
		}

		public int getInf10() {
			return inf10;
		}

		public double getPerInf10() {
			double per = ((double) inf10 / (double) present) * 100;
			return per;
		}

		public int getInf12() {
			return inf12;
		}

		public double getPerInf12() {
			double per = ((double) inf12 / (double) present) * 100;
			return per;
		}

		public int getInf14() {
			return inf14;
		}

		public double getPerInf14() {
			double per = ((double) inf14 / (double) present) * 100;
			return per;
		}

		public int getInf16() {
			return inf16;
		}

		public double getPerInf16() {
			double per = ((double) inf16 / (double) present) * 100;
			return per;
		}

		public int getInf18() {
			return inf18;
		}

		public double getPerInf18() {
			double per = ((double) inf18 / (double) present) * 100;
			return per;
		}

		public int getSup18() {
			return sup18;
		}

		public double getPerSup18() {
			double per = ((double) sup18 / (double) present) * 100;
			return per;
		}

		public int getSup10() {
			return sup10;
		}

		public double getPerSup10() {
			double per = ((double) sup10 / (double) present) * 100;
			return per;
		}

		public int getPresent() {
			return present;
		}

		public double getPerPresent() {
			double per = ((double) present / (double) inscrit) * 100;
			return per;
		}

		public double getLNote() {
			return lnote;
		}

		public double getHNote() {
			return hnote;
		}

		public double getMoyCls() {
			double moycls = new Double(total / present);
			return moycls;
		}

		public String getIntitule() {
			return this.intitule;
		}

		public int getAdmis() {
			return nAdmis;
		}

		public double getPerAdmis() {
			double per = ((double) nAdmis / (double) present) * 100;
			return per;
		}

		public double getMPass() {
			return mpassClasse;
		}

		// ascensseur pour gar�on******************************************
		public int getInf2g() {
			return inf2g;
		}

		public int getInf4g() {
			return inf4g;
		}

		public int getInf6g() {
			return inf6g;
		}

		public int getInf8g() {
			return inf8g;
		}

		public int getInf10g() {
			return inf10g;
		}

		public int getInf12g() {
			return inf12g;
		}

		public int getInf14g() {
			return inf14g;
		}

		public int getInf16g() {
			return inf16g;
		}

		public int getInf18g() {
			return inf18g;
		}

		public int getSup18g() {
			return sup18g;
		}

		public int getSup10g() {
			return sup10g;
		}

		public int getPresentg() {
			return presentg;
		}

		public double getLNoteg() {
			return lnoteg;
		}

		public double getHNoteg() {
			return hnoteg;
		}

		public double getMoyClsg() {
			double moycls = new Double(totalg / presentg);
			return moycls;
		}

		public double getPerSup10g() {
			double per;
			try {

				per = ((double) sup10g / (double) presentg) * 100;

			} catch (Exception e) {
				e.printStackTrace();
				per = 0;
			}
			return per;
		}

		public int getAdmisg() {
			return nAdmisg;
		}

		public double getPerAdmisg() {
			double per = ((double) nAdmisg / (double) presentg) * 100;
			return per;
		}

		// ascensseur pour fille******************************************
		public int getInf2f() {
			return inf2f;
		}

		public int getInf4f() {
			return inf4f;
		}

		public int getInf6f() {
			return inf6f;
		}

		public int getInf8f() {
			return inf8f;
		}

		public int getInf10f() {
			return inf10f;
		}

		public int getInf12f() {
			return inf12f;
		}

		public int getInf14f() {
			return inf14f;
		}

		public int getInf16f() {
			return inf16f;
		}

		public int getInf18f() {
			return inf18f;
		}

		public int getSup18f() {
			return sup18f;
		}

		public int getSup10f() {
			return sup10f;
		}

		public int getPresentf() {
			return presentf;
		}

		public double getLNotef() {
			return lnotef;
		}

		public double getHNotef() {
			return hnotef;
		}

		public double getMoyClsf() {
			double moycls = new Double(totalf / presentf);
			return moycls;
		}

		public double getPerSup10f() {
			double per;
			try {

				per = ((double) sup10f / (double) presentf) * 100;

			} catch (Exception e) {
				e.printStackTrace();
				per = 0;
			}
			return per;
		}

		public int getInscrit() {
			return inscrit;
		}

		public int getInscritf() {
			return inscritf;
		}

		public int getInscritg() {
			return inscritg;
		}

		@Override
		public void setIntitule(String inti) {
			this.intitule = inti;
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
			return 0;
		}

		@Override
		public String getId() {
			return this.intitule;
		}

		@Override
		public void setId(String id) {
			this.intitule = id;
		}

		@Override
		public void setValue(double val) {
			// TODO Auto-generated method stub

		}

		public int getAdmisf() {
			return nAdmisf;
		}

		public double getPerAdmisf() {
			double per = ((double) nAdmisf / (double) presentf) * 100;
			return per;
		}

	}

	// LES ACCESSEURS STATIQUES DANS LA CLASSE MERE##########################
	public int getTSup18() {
		return tsup18;
	}

	public double getPerTSup18() {
		double per = ((double) tsup18 / (double) tpresent) * 100;
		return per;
	}

	public double getPerTSup10() {
		double per = ((double) tsup10 / (double) tpresent) * 100;
		return per;
	}

	public int getTSup10() {
		return tsup10;
	}

	public int getTInf18() {
		return tinf18;
	}

	public double getPerTInf18() {
		double per = ((double) tinf18 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf16() {
		return tinf16;
	}

	public double getPerTInf16() {
		double per = ((double) tinf16 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf14() {
		return tinf14;
	}

	public double getPerTInf14() {
		double per = ((double) tinf14 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf12() {
		return tinf12;
	}

	public double getPerTInf12() {
		double per = ((double) tinf12 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf10() {
		return tinf10;
	}

	public double getPerTInf10() {
		double per = ((double) tinf10 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf8() {
		return tinf8;
	}

	public double getPerTInf8() {
		double per = ((double) tinf8 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf6() {
		return tinf6;
	}

	public double getPerTInf6() {
		double per = ((double) tinf6 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf4() {
		return tinf4;
	}

	public double getPerTInf4() {
		double per = ((double) tinf4 / (double) tpresent) * 100;
		return per;
	}

	public int getTInf2() {
		return tinf2;
	}

	public double getPerTInf2() {
		double per = ((double) tinf2 / (double) tpresent) * 100;
		return per;
	}

	public double getTMoyCls() {
		// TODO Auto-generated method stub
		return tmoycls;
	}

	public int getTPresent() {
		return tpresent;
	}

	public double getPerTPresent() {
		double per = ((double) tpresent / (double) tinscrit) * 100;
		return per;
	}

	public int getTInscrit() {
		return tinscrit;
	}

	// pour les garcon*******************************************
	public int getTSup18g() {
		return tsup18g;
	}

	public double getPerTSup18g() {
		double per = ((double) tsup18g / (double) tpresentg) * 100;
		return per;
	}

	public double getPerTSup10g() {
		double per = ((double) tsup10g / (double) tpresentg) * 100;
		return per;
	}

	public int getTSup10g() {
		return tsup10g;
	}

	public int getTInf18g() {
		return tinf18g;
	}

	public double getPerTInf18g() {
		double per = ((double) tinf18g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf16g() {
		return tinf16g;
	}

	public double getPerTInf16g() {
		double per = ((double) tinf16g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf14g() {
		return tinf14g;
	}

	public double getPerTInf14g() {
		double per = ((double) tinf14g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf12g() {
		return tinf12g;
	}

	public double getPerTInf12g() {
		double per = ((double) tinf12g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf10g() {
		return tinf10g;
	}

	public double getPerTInf10g() {
		double per = ((double) tinf10g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf8g() {
		return tinf8g;
	}

	public double getPerTInf8g() {
		double per = ((double) tinf8g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf6g() {
		return tinf6g;
	}

	public double getPerTInf6g() {
		double per = ((double) tinf6g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf4g() {
		return tinf4g;
	}

	public double getPerTInf4g() {
		double per = ((double) tinf4g / (double) tpresentg) * 100;
		return per;
	}

	public int getTInf2g() {
		return tinf2g;
	}

	public double getPerTInf2g() {
		double per = ((double) tinf2g / (double) tpresentg) * 100;
		return per;
	}

	public double getTMoyClsg() {
		// TODO Auto-generated method stub
		return tmoyclsg;
	}

	public int getTPresentg() {
		return tpresent;
	}

	public double getPerTPresentg() {
		double per = ((double) tpresentg / (double) tinscritg) * 100;
		return per;
	}

	public int getTInscritg() {
		return tinscritg;
	}

	// Pour nos mamans***************************************************
	public int getTSup18f() {
		return tsup18f;
	}

	public double getPerTSup18f() {
		double per = ((double) tsup18f / (double) tpresentf) * 100;
		return per;
	}

	public double getPerTSup10f() {
		double per = ((double) tsup10f / (double) tpresentf) * 100;
		return per;
	}

	public int getTSup10f() {
		return tsup10f;
	}

	public int getTInf18f() {
		return tinf18f;
	}

	public double getPerTInf18f() {
		double per = ((double) tinf18f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf16f() {
		return tinf16f;
	}

	public double getPerTInf16f() {
		double per = ((double) tinf16f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf14f() {
		return tinf14f;
	}

	public double getPerTInf14f() {
		double per = ((double) tinf14f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf12f() {
		return tinf12f;
	}

	public double getPerTInf12f() {
		double per = ((double) tinf12f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf10f() {
		return tinf10f;
	}

	public double getPerTInf10f() {
		double per = ((double) tinf10f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf8f() {
		return tinf8f;
	}

	public double getPerTInf8f() {
		double per = ((double) tinf8f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf6f() {
		return tinf6f;
	}

	public double getPerTInf6f() {
		double per = ((double) tinf6f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf4f() {
		return tinf4f;
	}

	public double getPerTInf4f() {
		double per = ((double) tinf4f / (double) tpresentf) * 100;
		return per;
	}

	public int getTInf2f() {
		return tinf2f;
	}

	public double getPerTInf2f() {
		double per = ((double) tinf2f / (double) tpresentf) * 100;
		return per;
	}

	public double getTMoyClsf() {
		// TODO Auto-generated method stub
		return tmoyclsf;
	}

	public int getTPresentf() {
		return tpresentf;
	}

	public double getPerTPresentf() {
		double per = ((double) tpresentf / (double) tinscritf) * 100;
		return per;
	}

	public int getTInscritf() {
		return tinscritf;
	}

	public void setMPass(double mpass2) {
		mpass = mpass2;
	}
}
