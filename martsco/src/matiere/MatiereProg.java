package matiere;

import java.util.ArrayList;

import abstractObject.AbstractPojo;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;

public class MatiereProg extends AbstractPojo {

	// les différentes variables
	private String intitule = "";
	private double coef = 0;
	private String charge;
	private String type;

	// les constructeurs
	public MatiereProg(String intitule, double d, String charge) {
		this.intitule = intitule;
		this.coef = d;
		this.charge = charge;

		primaryKey = intitule;
	}

	public MatiereProg(ArrayList listIns) {
		try {
			this.intitule = (String) listIns.get(0);
			this.charge = (String) listIns.get(1);

			primaryKey = intitule;
		} catch (Exception e) {

		}
	}

	public MatiereProg() {
	}

	public MatiereProg(String str) {
		this.intitule = str;

		primaryKey = intitule;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public double getCoef() {
		return this.coef;
	}

	public void setCoef(double coef2) {
		this.coef = coef2;
	}

	public String getCharge() {
		return this.charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public void setPrimaryKey(String str) {
		primaryKey = str;
	}

	@Override
	public int getRang() {
		DAO matdao = DAOFactory.getDAO(DAO.MATIERE);
		matdao.load();
		int rg = matdao.getOrdre(intitule);
		return rg;
	}

	public String getIntCor() {
		String str = "";
		str = Constance.getCor(intitule);
		return str;
	}

	@Override
	public void setRang(int rg) {
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getId() {
		return primaryKey;
	}

	@Override
	public void setId(String id) {
		primaryKey = id;
	}

	@Override
	public void setValue(double val) {

	}

	@Override
	public String getCor() {
		return Constance.getCor(intitule);
	}

	@Override
	public String getInfo(int i) {
		String info = "";
		if (i == 2)
			info = String.valueOf(getCoef());
		if (i == 3)
			info = getCharge();
		return info;
	}

	public String getType() {
		return type;
	}

	public void setType(String tpe) {
		type = tpe;
	}

}
