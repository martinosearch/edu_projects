package classe;

import java.util.ArrayList;

import abstractObject.AbstractPojo;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;

public class Classe extends AbstractPojo {

	// les différentes variables
	private String intitule = "";
	private String niveau = "";
	private DAO nivdao;
	private int effectif;

	// les constructeurs
	public Classe(String intitule, String niveau) {
		this.intitule = intitule;
		this.niveau = niveau;
	}

	public Classe(ArrayList listIns) {
		this.intitule = (String) listIns.get(0);
		this.niveau = (String) listIns.get(1);
	}

	public Classe() {
	}

	public String getIntitule() {
		return this.intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getNiveau() {
		return this.niveau;
	}

	public void setAnnee(String niveau) {
		this.niveau = niveau;
	}

	@Override
	public String getPrimaryKey() {
		return intitule;
	}

	public String getTypeEns() {
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		nivdao.load();

		Niveau niv = (Niveau) nivdao.findObj(niveau);

		return niv.getTypeEns();
	}

	public String findNiveau() {
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		nivdao.load();

		Niveau niv = (Niveau) nivdao.findObj(niveau);

		return niv.getIntitule();
	}

	@Override
	public String getCor() {
		return Constance.getCor(intitule);
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

	@Override
	public String getInfo(int i) {
		String info = null;
		if (i == 1)
			info = intitule;
		if (i == 2)
			info = niveau;
		return info;
	}

	public void setEffectif(int eff) {
		effectif = eff;
	}

	public int getEffectif() {
		return effectif;
	}

	/*
	 * public static void main(String[] args){ Classe cl=new
	 * Classe("3�me A","3�me"); System.out.println(cl.getTypeEns());
	 * 
	 * }
	 */

}
