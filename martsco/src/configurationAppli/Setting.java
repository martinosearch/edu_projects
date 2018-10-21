package configurationAppli;

import java.util.ArrayList;

import function.Constance;
import abstractObject.AbstractPojo;

public class Setting extends AbstractPojo {

	// les différentes variables
	private String intitule = "";
	private Object attribut = null;
	private double value = 0;
	private int rang = 0;

	// les constructeurs
	public Setting(String intitule, Object attribut) {
		this.intitule = intitule;
		this.attribut = attribut;
	}

	public Setting(ArrayList listIns) {
		this.intitule = (String) listIns.get(0);
		this.attribut = listIns.get(1);
	}

	public Setting() {
	}

	public Setting(String intitule2, int value2) {
		this.intitule = intitule2;
		this.value = value2;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public Object getAttribut() {
		return this.attribut;
	}

	public double getValue() {
		return this.value;
	}

	public void setAnnee(String attribut) {
		this.attribut = attribut;
	}

	@Override
	public String getPrimaryKey() {
		return intitule;
	}

	@Override
	public String getId() {
		return intitule;
	}

	@Override
	public void setId(String id) {
		intitule = id;
	}

	@Override
	public int getRang() {
		return rang;
	}

	@Override
	public void setRang(int rg) {
		rang = rg;
	}

	@Override
	public void setValue(double val) {
		value = val;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAttribut(Object attr) {
		attribut = attr;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
