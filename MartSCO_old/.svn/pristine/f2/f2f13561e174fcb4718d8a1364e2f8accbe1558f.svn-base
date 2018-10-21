package classe;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import eleve.Scolarite;
import graphicsModel.MartList;
import abstractObject.AbstractPojo;

public class Niveau extends AbstractPojo {

	// les différentes variables
	private String intitule;
	private String typeEns;
	private int rang = 0;

	private MartList<Scolarite> Scolarites;

	private MartList<String> cursusPrimaire = new MartList<String>();
	private MartList<String> cursusCollege = new MartList<String>();
	private MartList<String> cursusSerie_A4 = new MartList<String>();
	private MartList<String> cursusSerie_D = new MartList<String>();
	private MartList<String> cursusSerie_C4 = new MartList<String>();
	private MartList<String> cursusSerie_G1 = new MartList<String>();
	private MartList<String> cursusSerie_G2 = new MartList<String>();
	private MartList<String> cursusSerie_G3 = new MartList<String>();
	private MartList<String> cursusSerie_F1 = new MartList<String>();
	private MartList<String> cursusSerie_F2 = new MartList<String>();
	private MartList<String> cursusSerie_F3 = new MartList<String>();
	private MartList<String> cursusSerie_F4 = new MartList<String>();

	private MartList<MartList<String>> listCursus = new MartList<MartList<String>>();

	// les constructeurs
	public Niveau(String niveau, String tpeEns) {
		try {
			this.intitule = niveau;
			this.typeEns = tpeEns;
		} catch (Exception e) {
			e.printStackTrace();
		}
		setCursus();
	}

	public Niveau(ArrayList listIns) {
		try {
			this.intitule = (String) listIns.get(0);
			this.typeEns = (String) listIns.get(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Niveau() {
		setCursus();
	}

	public Niveau(String niveau) {
		intitule = niveau;
		setCursus();
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String niv) {
		this.intitule = niv;
	}

	public String getTypeEns() {
		return this.typeEns;
	}

	public void setTypeEns(String tpe) {
		this.typeEns = tpe;
	}

	public String getPrimaryKey() {
		return intitule;
	}

	public String getId() {
		return intitule;
	}

	public void setId(String id) {
		intitule = id;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rg) {
		rang = rg;
	}

	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	public void setCursus() {
		// définition du cursus primaire
		cursusPrimaire.add("Maternelle 1");
		cursusPrimaire.add("Maternelle 2");
		cursusPrimaire.add("Maternelle 3");
		cursusPrimaire.add("CP1");
		cursusPrimaire.add("CP2");
		cursusPrimaire.add("CE1");
		cursusPrimaire.add("CE2");
		cursusPrimaire.add("CM1");
		cursusPrimaire.add("CM2");

		// definition du cursus collège
		cursusCollege.add("6ème");
		cursusCollege.add("5ème");
		cursusCollege.add("4ème");
		cursusCollege.add("3ème");

		// definition du cursus série A4
		cursusSerie_A4.add("2nde A4");
		cursusSerie_A4.add("1ère A4");
		cursusSerie_A4.add("Tle A4");

		// definition du cursus série D
		cursusSerie_D.add("2nde CD");
		cursusSerie_D.add("1ère D");
		cursusSerie_D.add("Tle D");

		// definition du cursus série C
		cursusSerie_C4.add("2nde CD");
		cursusSerie_C4.add("1ère C4");
		cursusSerie_C4.add("Tle C4");

		// definition du cursus série G1
		cursusSerie_G1.add("2nde G1");
		cursusSerie_G1.add("1ère G1");
		cursusSerie_G1.add("Tle G1");

		// definition du cursus série G2
		cursusSerie_G2.add("2nde G2");
		cursusSerie_G2.add("1ère G2");
		cursusSerie_G2.add("Tle G2");

		// definition du cursus série G3
		cursusSerie_G3.add("2nde G3");
		cursusSerie_G3.add("1ère G3");
		cursusSerie_G3.add("Tle G3");

		// definition du cursus série F1
		cursusSerie_F1.add("2nde F1");
		cursusSerie_F1.add("1ère F1");
		cursusSerie_F1.add("Tle F1");

		// definition du cursus série F2
		cursusSerie_F2.add("2nde F2");
		cursusSerie_F2.add("1ère F2");
		cursusSerie_F2.add("Tle F2");

		// definition du cursus série F3
		cursusSerie_F3.add("2nde F3");
		cursusSerie_F3.add("1ère F3");
		cursusSerie_F3.add("Tle F3");

		// definition du cursus série F3
		cursusSerie_F4.add("2nde F4");
		cursusSerie_F4.add("1ère F4");
		cursusSerie_F4.add("Tle F4");

		listCursus.add(cursusPrimaire);
		listCursus.add(cursusCollege);
		listCursus.add(cursusSerie_A4);
		listCursus.add(cursusSerie_C4);
		listCursus.add(cursusSerie_D);
		listCursus.add(cursusSerie_G1);
		listCursus.add(cursusSerie_G2);
		listCursus.add(cursusSerie_G3);
		listCursus.add(cursusSerie_F1);
		listCursus.add(cursusSerie_F2);
		listCursus.add(cursusSerie_F3);
		listCursus.add(cursusSerie_F4);
	}

	public String getNiveauInf() {
		String nivInf = "";
		int founded = 0;

		if (intitule.equals("6ème")) {
			nivInf = "CM2";
		} else if (intitule.equals("2nde A4") || intitule.equals("2nde CD")
				|| intitule.equals("2nde G1") || intitule.equals("2nde G2")
				|| intitule.equals("2nde G3") || intitule.equals("2nde F1")
				|| intitule.equals("2nde F2") || intitule.equals("2nde F3")
				|| intitule.equals("2nde F4")) {
			nivInf = "3ème";
		} else {
			for (MartList<String> cursus : listCursus) {
				int i = 0;
				for (String str : cursus) {
					if (str.equals(intitule)) {
						if (i == 0) {
							nivInf = str;
						} else {
							nivInf = cursus.get(i - 1);
						}

						founded = 1;
						break;
					}

					i++;
				}
				if (founded == 1)
					break;
			}
		}
		return nivInf;
	}

	public String getNiveauSup() {
		String nivSup = "";
		int founded = 0;

		if (intitule.equals("Tle A4") || intitule.equals("Tle D")
				|| intitule.equals("Tle G1") || intitule.equals("Tle G2")
				|| intitule.equals("Tle G3") || intitule.equals("Tle F1")
				|| intitule.equals("Tle F2") || intitule.equals("Tle F3")
				|| intitule.equals("Tle F4")) {
			nivSup = "none";

		} else {
			for (MartList<String> cursus : listCursus) {
				int i = 0;
				for (String str : cursus) {
					if (str.equals(intitule)) {
						try {
							nivSup = cursus.get(i + 1);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,
									"Désolé, vous ne disposez pas de niveau \\"
											+ "plus élevé que : " + intitule);
						}
						founded = 1;
						break;
					}

					i++;
				}
				if (founded == 1)
					break;
			}
		}
		return nivSup;
	}

	public String getCor() {
		return null;
	}

	public static void main(String[] args) {
		Niveau niveau = new Niveau();
		niveau.setIntitule("1ère F4");

		System.out.println("La demande renvoi: " + niveau.getNiveauSup());
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
