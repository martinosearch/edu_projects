package note;

import abstractObject.AbstractPojo;

public class RgValue extends AbstractPojo {
	String matiere, intitule;
	double value;
	int rang = 0;

	public RgValue(String matiere, String intitule, double value) {
		this.matiere = matiere;
		this.intitule = intitule;
		this.value = value;
	}

	public RgValue(String matricule, double value) {
		this.intitule = matricule;
		this.value = value;
	}

	public RgValue() {

	}

	public String getMatricule() {
		return this.intitule;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public double getValue() {
		return this.value;
	}

	@Override
	public int getRang() {
		return rang;
	}

	@Override
	public String getPrimaryKey() {
		return intitule;
	}

	public void setRang(int rg) {
		this.rang = rg;
	}

	@Override
	public void setIntitule(String inti) {
		this.intitule = inti;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return intitule;
	}

	@Override
	public void setId(String id) {
		intitule = id;
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
