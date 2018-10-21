package abstractObject;

import interfacePerso.MartRangeable;

public class MartObjet implements MartRangeable {

	private String identifient;
	private Object passenger;
	private double value;
	private int rang;

	public MartObjet(Object obj, String id) {
		passenger = obj;
		identifient = id;
	}

	@Override
	public String getId() {
		return identifient;
	}

	@Override
	public void setId(String id) {
		identifient = id;
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
		return rang;
	}

	@Override
	public void setRang(int rg) {
		rang = rg;
	}

	public Object getObject() {
		return passenger;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(double val) {
		value = val;
	}
}
