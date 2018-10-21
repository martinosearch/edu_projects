package planning;

import interfacePerso.MartRangeable;

public class Associateur implements MartRangeable {

	private String id;
	private String jour, heure, classe;

	public Associateur(String j, String h, String cl) {
		jour = j;
		heure = h;
		classe = cl;
	}

	public String getJour() {
		return jour;
	}

	public void setJour(String jour) {
		this.jour = jour;
	}

	public String getHeur() {
		return heure;
	}

	public void setHeur(String heur) {
		this.heure = heur;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String ident) {
		id = ident;
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

}
