package note;

import java.util.ArrayList;

import function.MartComputer;
import function.MartFormatter;
import graphicsModel.MartList;
import abstractObject.AbstractPojo;

public class Note extends AbstractPojo {

	// les différentes variables
	private String matricule;
	private String matiere = "";
	private double note1;
	private double note2;
	private double note3;
	private double note4;
	private String note1str = "";
	private String note2str = "";
	private String note3str = "";
	private String note4str = "";
	private int rang = 0;

	// les constructeurs
	public Note(String matri, String not1, String not2, String not3, String not4)
			throws Exception {
		this.matricule = matri;
		this.note1str = not1;
		this.note2str = not2;
		this.note3str = not3;
		this.note4str = not4;

		setCorrection();
	}

	public Note(ArrayList<String> listIns) {
		try {
			this.matricule = (String) listIns.get(0);
			this.note1str = (String) listIns.get(1);
			this.note2str = (String) listIns.get(2);
			this.note3str = (String) listIns.get(3);
			this.note4str = (String) listIns.get(4);

		} catch (Exception e) {
			e.printStackTrace();
		}

		setCorrection();
	}

	public Note() {
	}

	public Note(String matri, double not1, double not2, double not3, double not4)
			throws Exception {
		this.matricule = matri;
		this.note1str = String.valueOf(not1);
		this.note2str = String.valueOf(not2);
		this.note3str = String.valueOf(not3);
		this.note4str = String.valueOf(not4);

		setCorrection();
	}

	public void setMatiere(String mat) {
		this.matiere = mat;
	}

	public String getMatiere() {
		return this.matiere;
	}

	public String getCodeInfo() {
		return this.matricule;
	}

	public void setCodeInfo(String str) {
		this.matricule = str;
	}

	// la premiere note
	public String getNote1str() {
		return this.note1str;
	}

	public void setNote1str(String not) {
		this.note1str = not;
		setCorrection();
	}

	public double getNote1() {
		try {
			note1 = Double.valueOf(this.note1str).doubleValue();
		} catch (Exception e) {
			note1 = new Double(0.001);
		}
		return note1;
	}

	public void setNote1(double not) {
		this.note1 = not;
	}

	// la deuxi�me note
	public String getNote2str() {
		return this.note2str;
	}

	public void setNote2str(String not) {
		this.note2str = not;
		setCorrection();
	}

	public double getNote2() {
		try {
			note2 = Double.valueOf(this.note2str).doubleValue();
		} catch (Exception e) {
			note2 = new Double(0.001);
		}
		return note2;
	}

	public void setNote2(double not) {
		this.note2 = not;
	}

	// la troisième note
	public String getNote3str() {
		return this.note3str;
	}

	public void setNote3str(String not) {
		this.note3str = not;
		setCorrection();
	}

	public double getNote3() {
		try {
			note3 = Double.valueOf(this.note3str).doubleValue();
		} catch (Exception e) {
			note3 = new Double(0.001);
		}
		return note3;
	}

	public void setNote3(double not) {
		this.note3 = not;
	}

	// la quatrième note
	public String getNote4str() {
		return this.note4str;
	}

	public void setNote4str(String not) {
		this.note4str = not;
		setCorrection();
	}

	public double getNote4() {
		try {
			note4 = Double.valueOf(this.note4str).doubleValue();
		} catch (Exception e) {
			note4 = new Double(0.001);
		}
		return note4;
	}

	public void setNote4(double not) {
		this.note4 = not;
	}

	private void setCorrection() {
		getNote1();
		getNote2();
		getNote3();
		getNote4();

		if (note1 == 0.001) {
			note1str = "";
		}
		if (note2 == 0.001) {
			note2str = "";
		}
		if (note3 == 0.001) {
			note3str = "";
		}
		if (note4 == 0.001) {
			note4str = "";
		}

		note1str = MartFormatter.correctDecimal(note1str);
		note2str = MartFormatter.correctDecimal(note2str);
		note3str = MartFormatter.correctDecimal(note3str);
		note4str = MartFormatter.correctDecimal(note4str);
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return matricule;
	}

	public int getRang() {
		return this.rang;
	}

	public void setRang(int rg) {
		this.rang = rg;
	}

	@Override
	public String getIntitule() {
		return this.matricule;
	}

	@Override
	public void setIntitule(String inti) {
		this.matricule = inti;
	}

	@Override
	public double getValue() {
		MartComputer mc = new MartComputer();
		mc.setNote(this);
		return mc.getMoyp();
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return matricule;
	}

	@Override
	public void setId(String id) {
		matricule = id;
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
