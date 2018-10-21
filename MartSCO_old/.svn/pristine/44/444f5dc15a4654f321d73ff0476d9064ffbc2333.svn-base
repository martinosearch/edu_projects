package annee;

import java.util.ArrayList;

import function.Constance;
import abstractObject.AbstractPojo;

public class Annee extends AbstractPojo {
	private String intitule = "";
	private String responsable = "";
	private int type_decoupage = 0;
	private Object[][] annee1;

	// les constructeurs
	public Annee(String annee) {
		this.intitule = annee;
	}

	public Annee(ArrayList listIns) {
		this.intitule = (String) listIns.get(0);
	}

	public Annee() {
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String an) {
		this.intitule = an;
	}

	@Override
	public String getPrimaryKey() {
		return intitule;
	}

	@Override
	public String getCor() {
		return Constance.getCor(intitule);
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return getPrimaryKey();
	}

	@Override
	public void setId(String id) {
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

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
