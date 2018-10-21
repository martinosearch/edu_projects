package annee;

import java.util.ArrayList;

import function.MartArranger;
import abstractObject.AbstractPojo;

public class Decoupage extends AbstractPojo {
	public static int TRIMESTRE = 1, SEMESTRE = 2;
	private String classe = "";
	private int typeDec = TRIMESTRE;
	private String decStr;
	private String adjF;
	private String adjM;
	private int section = 1;

	// les constructeurs
	public Decoupage(String classe, int type) {
		this.classe = classe;
		this.typeDec = type;
	}

	public Decoupage(ArrayList listIns) {
		this.classe = (String) listIns.get(0);
		try {
			this.typeDec = (int) listIns.get(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Decoupage() {
	}

	public String getClasse() {
		return this.classe;
	}

	public void setClasse(String cl) {
		this.classe = cl;
	}

	public int getTypeDec() {
		return typeDec;
	}

	public void setTypeDec(int typ) {
		typeDec = typ;
	}

	public String getAdjFem() {
		int dec = getTypeDec();

		if (dec == 2)
			adjF = "Semestrielle";
		else
			adjF = "Trimestrielle";
		return adjF;
	}

	public String getAdjMasc() {
		int dec = getTypeDec();

		if (dec == 2)
			adjM = "Semestriel";
		else
			adjM = "Trimestriel";
		return adjM;
	}

	public String getAdjFemP() {
		int dec = getTypeDec();

		if (dec == 2)
			adjF = "Semestrielles";
		else
			adjF = "Trimestrielles";
		return adjF;
	}

	public String getAdjMascP() {
		int dec = getTypeDec();

		if (dec == 2)
			adjM = "Semestriels";
		else
			adjM = "Trimestriels";
		return adjM;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return classe;
	}

	public void setSection(int trimestre) {
		this.section = trimestre;
	}

	public int getSection() {
		return section;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getPrimaryKey();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIntitule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIntitule(String inti) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRang(int rg) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	public int getMax() {
		int max = 3;
		if (typeDec == 2)
			max = 2;
		return max;
	}

	@Override
	public String getInfo(int i) {
		return null;
	}

	public String toString() {
		MartArranger arger = new MartArranger();
		String str = arger.getOrderStr(getSection(), "M") + " "
				+ sectionToString();
		return str;
	}

	public String sectionToString() {
		int dec = getTypeDec();

		if (dec == 2)
			decStr = "Semestre";
		else
			decStr = "Trimestre";
		return decStr;
	}
}
