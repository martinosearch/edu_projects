package ecolage;

import interfacePerso.MartRangeable;
import eleve.EleveClasse;

public class InfoEcoEleve implements MartRangeable {
	double regle = 0, reste;
	EleveClasse eleve;
	private EcolageComputer computer;

	public InfoEcoEleve(EleveClasse el, EcolageComputer comp) {
		eleve = el;
		computer = comp;
		regle = computer.getRegle();
		reste = computer.getReste();
	}

	public double getRegle() {
		return regle;
	}

	public void setRegle(double regle) {
		this.regle = regle;
	}

	public double getReste() {
		return reste;
	}

	public void setReste(double reste) {
		this.reste = reste;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return eleve.getCodeInfo();
	}

	public EleveClasse getEleve() {
		return eleve;
	}

	public void setEleve(EleveClasse eleve) {
		this.eleve = eleve;
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

}
