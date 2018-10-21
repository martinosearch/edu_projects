package security;

import java.util.ArrayList;

import abstractObject.AbstractPojo;

public class User extends AbstractPojo {

	// les différentes variables
	protected static int DISCONNECTED = 0, CONNECTED = 1;
	protected String login;
	protected String passWord;
	protected String type;
	protected int niveau;
	protected int etat = 0;
	protected String matricule;

	// les constructeurs
	public User(String login, String pass, String type, int niveau, String code) {
		this.login = login;
		this.passWord = pass;
		this.type = type;
		this.niveau = niveau;
		matricule = code;
	}

	public User(ArrayList listIns) {
		this.login = (String) listIns.get(0);
		this.passWord = (String) listIns.get(1);
		this.type = (String) listIns.get(2);
		this.niveau = (int) listIns.get(3);
		this.matricule = (String) listIns.get(4);
		this.etat = (int) listIns.get(5);
	}

	public User() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return this.passWord;
	}

	public void setPass(String login) {
		this.passWord = login;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String login) {
		this.type = login;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public String getCodeInfo() {
		return matricule;
	}

	public void setCodeInfo(String matricule) {
		this.matricule = matricule;
	}

	@Override
	public String getPrimaryKey() {
		return login;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return login;
	}

	@Override
	public void setId(String id) {
		login = id;
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
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

}
