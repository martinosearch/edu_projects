package agent;

import java.util.ArrayList;

import abstractObject.AbstractPojo;
import connection.DAO;
import connection.DAOFactory;

public class Responsable extends AbstractPojo {

	private DAO profdao;
	public static int NORMAL = 0, REVERSE = 1;

	// les differentes variables
	private String intitule = "", matricule = "", sexe = "", titrecor = "",
			fonction = "";

	// les constructeurs
	public Responsable(String inti, String matri, String sexe) {
		this.intitule = inti;
		this.matricule = matri;
		this.sexe = sexe;
	}

	public Responsable(ArrayList listIns) {
		this.intitule = (String) listIns.get(0);
		this.matricule = (String) listIns.get(1);
		this.sexe = (String) listIns.get(2);
	}

	public Responsable() {
	}

	// les accesseurs
	public String getIntitule() {
		return this.intitule;
	}

	public String getCodeInfo() {
		return this.matricule;
	}

	public String getSexe() {
		return this.sexe;
	}

	public String getFonction() {
		return this.fonction;
	}

	public void setFonction(String fonct) {
		this.fonction = fonct;
	}

	public void setSexe(String sex) {
		this.sexe = sex;
	}

	public String getPrimaryKey() {
		return intitule;
	}

	public String getTitreCor() {
		String t = this.intitule;
		String s = this.sexe;

		if (intitule.equals("Directeur")) {
			if (s.equals("F"))
				t = "Directrice";
		}
		return t;
	}

	public String decrisToi() {
		profdao = DAOFactory.getDAO(DAO.AGENT);
		profdao.load();
		Agent prof = (Agent) profdao.findObj(matricule);
		return prof.getNom() + " " + prof.getPrenom();
	}

	public String decrisToi(int type) {
		profdao = DAOFactory.getDAO(DAO.AGENT);
		profdao.load();
		Agent prof = (Agent) profdao.findObj(matricule);

		String desc;
		if (type == 1) {
			desc = prof.getPrenom() + " " + prof.getNom();
		} else {
			desc = decrisToi();
		}

		return desc;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return intitule;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub

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
