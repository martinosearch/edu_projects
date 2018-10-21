package etablissement;

import java.util.ArrayList;

import abstractObject.AbstractPojo;

public class Etablissement extends AbstractPojo {

	// les diff�rentes variables
	private String intitule = "";
	private String nom = "";
	private String contact = "";

	private int type = 0;

	// les constructeurs
	public Etablissement(String intitule, String nom, String contact) {
		this.intitule = intitule;
		this.nom = nom;
		this.contact = contact;
	}

	public Etablissement(ArrayList listIns) {
		this.intitule = (String) listIns.get(0);
		this.nom = (String) listIns.get(1);
		this.contact = (String) listIns.get(2);
	}

	public Etablissement() {
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intit) {
		this.intitule = intit;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String dim) {
		this.contact = dim;
	}

	@Override
	public String getPrimaryKey() {
		return intitule + nom;
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

	public String define() {
		return intitule + " " + nom;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
