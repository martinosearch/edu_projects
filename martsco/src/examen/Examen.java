package examen;

import java.util.ArrayList;

import abstractObject.AbstractPojo;
import abstractObject.Rapport;
import classe.Niveau;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;

public class Examen extends AbstractPojo {

	// les différentes variables
	private String intitule = "";
	private String mois = "";
	private String annee = "";
	private String niveau = "";
	private String type = "";
	DAO petsdao;
	private DAO nivdao;
	private Niveau superNiveau;

	// les constructeurs
	public Examen(String mois1, String an, String niv, String typ) {
		this.mois = mois1;
		this.annee = an;
		this.niveau = niv;
		this.type = typ;
		this.intitule = type + " " + niveau + " " + mois + " " + annee;
	}

	public Examen(ArrayList listIns) {
		this.mois = (String) listIns.get(0);
		this.annee = (String) listIns.get(1);
		this.niveau = (String) listIns.get(2);
		this.type = (String) listIns.get(3);
		this.intitule = type + " " + niveau + " " + mois + " " + annee;
	}

	public Examen() {
	}

	public Examen(String str) {
		this.intitule = str;
	}

	public String getIntitule() {
		return this.intitule;
	}

	public String getType() {
		return this.type;
	}

	public String getMois() {
		return this.mois;
	}

	public String getAnnee() {
		return this.annee;
	}

	public String getNiveau() {
		return this.niveau;
	}

	public void setType(String typ) {
		this.type = typ;
	}

	public void setMois(String m) {
		this.mois = m;
	}

	public void setAnnee(String an) {
		this.annee = an;
	}

	@Override
	public String getPrimaryKey() {
		return intitule;
	}

	public String getCentre() {
		ConfigExamen conf = new ConfigExamen(getIntitule());
		return conf.relConfig.getCentre();
	}

	public int getModelRap() {
		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		nivdao.load();

		superNiveau = (Niveau) nivdao.findObj(niveau);

		int model = Rapport.MODEL_SECOND;

		if (superNiveau.getTypeEns().equals("PRIM")) {
			model = Rapport.MODEL_PRIM;
		}

		return model;
	}

	public void setIntitule(String str) {
		this.intitule = str;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
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

	public String decrisToi() {
		return type + " " + mois + " " + annee;
	}

}
