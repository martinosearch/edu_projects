package note;

import java.util.ArrayList;

import abstractObject.AbstractPojo;

public class Coefficient extends AbstractPojo {

	// les diff�rentes variables

	private String matiere = "", niveau = "";
	private double coef = 0;
	private String intitule = "";

	// les constructeurs
	public Coefficient(double d, String mat, String niv) {
		this.coef = d;
		matiere = mat;
		niveau = niv;

		intitule = matiere + niveau;
		primaryKey = matiere;
	}

	public Coefficient(ArrayList listIns) {
		this.coef = (int) listIns.get(0);
		this.matiere = (String) listIns.get(1);
		this.niveau = (String) listIns.get(2);

		intitule = matiere + niveau;
		primaryKey = intitule;
	}

	public Coefficient() {
		// TODO Auto-generated constructor stub
	}

	public Coefficient(double co, String key) {
		coef = co;
		intitule = key;
		primaryKey = key;
	}

	public double getCoef() {
		return this.coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public String getMatiere() {
		return this.matiere;
	}

	public void setMatiere(String mat) {
		this.matiere = mat;

	}

	public String getNiveau() {
		return this.niveau;
	}

	public void setNiveau(String niv) {
		this.niveau = niv;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return getPrimaryKey();
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIntitule() {
		return intitule;
	}

	@Override
	public void setIntitule(String inti) {
		intitule = inti;
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
