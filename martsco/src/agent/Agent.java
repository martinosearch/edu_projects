package agent;

import ecolage.Operation;
import graphicsModel.MartList;
import interfacePerso.Operateur;

import java.util.ArrayList;

import org.joda.time.DateTime;

import abstractObject.AbstractPojo;

public class Agent extends AbstractPojo implements Operateur {

	// les différentes variables
	MartList<Operation> listeOperateur = new MartList<Operation>();

	private String nom = "", prenom = "", sexe = "", tel = "", email = "",
			persoPrev = "", telPersoPrev = "", codeInfo = "";

	DateTime dateSortie;
	DateTime dateNais;
	DateTime dateEntree;

	public static int NORMAL = 0, REVERSE = 1;

	// les constructeurs
	public Agent(String nom_prof, String prenom_prof, String sexe_prof,
			DateTime date_nais_prof, DateTime date_entree_prof,
			DateTime date_sortie_prof, String tel_prof, String email_prof,
			String perso_prev_prof, String tel_perso_prof, String code_info_prof) {

		this.nom = nom_prof;
		this.prenom = prenom_prof;
		this.sexe = sexe_prof;
		this.dateNais = date_nais_prof;
		this.dateEntree = date_entree_prof;
		this.dateSortie = date_sortie_prof;
		this.tel = tel_prof;
		this.email = email_prof;
		this.persoPrev = perso_prev_prof;
		this.telPersoPrev = tel_perso_prof;
		this.codeInfo = code_info_prof;
		primaryKey = nom + prenom;
	}

	public Agent(ArrayList listIns) {
		this.nom = (String) listIns.get(0);
		this.prenom = (String) listIns.get(1);
		this.sexe = (String) listIns.get(2);
		try {
			this.dateNais = new DateTime(listIns.get(3));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		try {
			this.dateEntree = new DateTime(listIns.get(4));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		try {
			this.dateSortie = new DateTime(listIns.get(5));
		} catch (Exception e) {
			// e.printStackTrace();
		}

		this.tel = (String) listIns.get(6);
		this.email = (String) listIns.get(7);
		this.persoPrev = (String) listIns.get(8);
		this.telPersoPrev = (String) listIns.get(9);
		this.codeInfo = (String) listIns.get(10);
		primaryKey = nom + prenom;
	}

	public Agent() {
	}

	// les accesseurs
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sex) {
		sexe = sex;
	}

	public DateTime getDateNais() {
		return this.dateNais;
	}

	public void setDateNais(DateTime date) {
		this.dateNais = date;
	}

	public DateTime getDateEntree() {
		return this.dateEntree;
	}

	public void setDateEntree(DateTime date) {
		this.dateEntree = date;
	}

	public DateTime getDateSortie() {
		return this.dateSortie;
	}

	public void setDateSortie(DateTime date) {
		this.dateSortie = date;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPersoPrev() {
		return this.persoPrev;
	}

	public void setPerso(String perso) {
		this.tel = perso;
	}

	public String getTelPerso() {
		return this.telPersoPrev;
	}

	public void setTelPerso(String tel) {
		this.telPersoPrev = tel;
	}

	public String getCodeInfo() {
		return this.codeInfo;
	}

	public void setCodeInfo(String code) {
		this.codeInfo = code;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return codeInfo;
	}

	@Override
	public void setId(String id) {
		codeInfo = id;
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
	}

	public String decrisToi() {
		return nom + " " + prenom;
	}

	public String decrisToi(int type) {
		String desc = nom + " " + prenom;

		if (type == 1) {
			desc = prenom + " " + nom;
		}
		return desc;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addOperation(Operation op) {
		listeOperateur.add(op);
	}

	@Override
	public MartList<Operation> getListeOperation() {
		return listeOperateur;
	}
}
