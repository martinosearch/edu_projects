package matiere;

import java.util.ArrayList;

import abstractObject.AbstractPojo;
import accueil.AccueilSCO;

public class Matiere extends AbstractPojo {

	// les diff�rentes variables
	private String intitule = "";
	private String type_mat = "";
	private String dim_mat = "";

	private int type = 0;
	private int ordre = 1;

	// les constructeurs
	public Matiere(String intitule, String type, String dim1) {
		this.intitule = intitule;
		this.type_mat = type;
		this.dim_mat = dim1;

		primaryKey = intitule;
	}

	public Matiere(ArrayList listIns) {
		this.intitule = (String) listIns.get(0);
		this.type_mat = (String) listIns.get(1);
		this.dim_mat = (String) listIns.get(2);

		try {
			this.ordre = (int) listIns.get(3);
		} catch (Exception e) {
			e.printStackTrace();
			this.ordre = 0;

			(AccueilSCO.instance)
					.showMessage("Veuillez définir l'ordre des matières!");
		}

		primaryKey = intitule;
	}

	public Matiere() {
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intit) {
		this.intitule = intit;
		primaryKey = intitule;
	}

	public String getType() {
		return this.type_mat;
	}

	public void setType(String typ) {
		this.type_mat = typ;
	}

	public String getDim() {
		return this.dim_mat;
	}

	public void setDim(String dim) {
		this.dim_mat = dim;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setRang(int rang) {
		this.ordre = rang;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return primaryKey;
	}

	@Override
	public void setId(String id) {
		intitule = id;
	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(double val) {

	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPrimaryKey(String key) {
		primaryKey = key;
	}

	public void setOrder(int ord) {
		ordre = ord;
	}

}
