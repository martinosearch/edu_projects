package note;

import java.util.ArrayList;

import abstractObject.AbstractPojo;

public class RapMoyenne extends AbstractPojo {

	// les diff�rentes variables
	private String matricule = "", rang1 = "", rang2 = "", rang3 = "",
			rangann = "";
	private double moyenne1, moyenne2, moyenne3, moyann;
	private int rang = 0;

	// les constructeurs
	public RapMoyenne(String matri, double moy1, String rg1, double moy2,
			String rg2, double moy3, String rg3, double moyann, String rgann) {
		this.matricule = matri;

		this.moyenne1 = moy1;
		this.rang1 = rg1;
		this.moyenne2 = moy2;
		this.rang2 = rg2;
		this.moyenne3 = moy3;
		this.rang3 = rg3;
		this.moyann = moyann;
		this.rangann = rgann;
	}

	public RapMoyenne(ArrayList listIns) {
		this.matricule = (String) listIns.get(0);
		this.moyenne1 = (double) listIns.get(1);
		this.rang1 = (String) listIns.get(2);
		this.moyenne2 = (double) listIns.get(3);
		this.rang2 = (String) listIns.get(4);
		this.moyenne3 = (double) listIns.get(5);
		this.rang3 = (String) listIns.get(6);
		this.moyann = (double) listIns.get(7);
		this.rangann = (String) listIns.get(8);
	}

	public RapMoyenne() {

	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matri) {
		this.matricule = matri;
	}

	public double getMoyenne1() {
		return this.moyenne1;
	}

	public void setMoyenne1(double moy) {
		this.moyenne1 = moy;
	}

	public String getRang1() {
		return this.rang1;
	}

	public void setRang1(String rg) {
		this.rang1 = rg;
	}

	public double getMoyenne2() {
		return this.moyenne2;
	}

	public void setMoyenne2(double moy) {
		this.moyenne2 = moy;
	}

	public String getRang2() {
		return this.rang2;
	}

	public void setRang2(String rg) {
		this.rang2 = rg;
	}

	public double getMoyenne3() {
		return this.moyenne3;
	}

	public void setMoyenne3(double moy) {
		this.moyenne3 = moy;
	}

	public String getRang3() {
		return this.rang3;
	}

	public void setRang3(String rg) {
		this.rang3 = rg;
	}

	public String getRangAnn() {
		return this.rangann;
	}

	public void setRangAnn(String rg) {
		this.rangann = rg;
	}

	public double getMoyAnn() {
		return this.moyann;
	}

	public void setRang3(double moy) {
		this.moyann = moy;
	}

	@Override
	public String getPrimaryKey() {
		return matricule;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return matricule;
	}

	@Override
	public void setId(String id2) {
		matricule = id2;
	}

	@Override
	public String getIntitule() {
		// TODO Auto-generated method stub
		return matricule;
	}

	@Override
	public void setIntitule(String inti) {
		matricule = inti;
	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return rang;
	}

	@Override
	public void setRang(int rg) {
		rang = rg;
	}

	@Override
	public double getValue() {
		return moyann;
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
