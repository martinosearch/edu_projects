package rapportBulletin;

import java.awt.print.PageFormat;

import java.util.ArrayList;

import org.joda.time.DateTime;

import abstractObject.AbstractPojo;

public class Histo extends AbstractPojo {

	// les différentes variables
	private String intitule = "";
	private String value = "";
	private int rang;
	private DateTime date;
	private int orientation = PageFormat.PORTRAIT;

	// les constructeurs
	public Histo(String inti, String val, DateTime dt) {
		this.intitule = inti;
		this.value = val;
		date = dt;
	}

	public Histo(ArrayList listIns) {
		try {
			this.intitule = (String) listIns.get(0);
			this.value = (String) listIns.get(1);
			this.date = new DateTime(Long.parseLong((String) listIns.get(2)));
		} catch (Exception e) {

		}
	}

	public Histo() {
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getHisto() {
		return this.value;
	}

	public void setAnnee(String val) {
		this.value = val;
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
	public int getRang() {
		return rang;
	}

	@Override
	public void setRang(int rg) {
		this.rang = rg;
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

	public void setDate(DateTime dt) {
		date = dt;
	}

	public DateTime getDate() {
		return date;
	}

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return intitule;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setOrientation(int orient) {
		orientation = orient;
	}

	public int getOrientation() {
		return orientation;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
