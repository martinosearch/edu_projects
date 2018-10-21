package configurationExamen;

public class Salle {
	private int effectif;
	private int numero;

	public Salle(int num, int eff) {
		effectif = eff;
		numero = num;
	}

	public int getEffectif() {
		return effectif;
	}

	public void setEffectif(int effectif) {
		this.effectif = effectif;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
