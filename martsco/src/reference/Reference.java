package reference;

import java.util.ArrayList;

import abstractObject.AbstractPojo;

public class Reference extends AbstractPojo {

	// les diff�rentes variables

	private String reference = "";
	private String value = "";

	// les constructeurs
	public Reference(String ref, String val) {
		this.reference = ref;
		this.value = val;
	}

	public Reference(ArrayList listIns) {
		this.reference = (String) listIns.get(0);
		this.value = (String) listIns.get(1);
	}

	public Reference() {
	}

	public String getRef() {
		return this.reference;
	}

	public void setRef(String str) {
		this.reference = str;
	}

	public String getValueRef() {
		return this.value;
	}

	public void setValueRef(String val) {
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

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return reference;
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
