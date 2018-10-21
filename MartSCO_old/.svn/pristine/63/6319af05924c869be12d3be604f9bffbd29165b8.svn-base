package ecolage;

public class InfoEcoEts {
	private double totalIns, totalEco;

	public void add(Operation op) {
		if (op.getJustification().equals(Operation.ECOLAGE)) {
			totalEco = totalEco + op.getMontant();
		} else if (op.getJustification().equals(Operation.INSCRIPTION)) {
			totalIns = totalIns + op.getMontant();
		} else if (op.getJustification().equals(Operation.ANNULATION_ECO)) {
			totalEco = totalEco - op.getMontant();
		} else if (op.getJustification().equals(Operation.ANNULATION_INS)) {
			totalIns = totalIns - op.getMontant();
		}
	}

	public double getTotalIns() {
		return totalIns;
	}

	public void setTotalIns(double totalIns) {
		this.totalIns = totalIns;
	}

	public double getTotalEco() {
		return totalEco;
	}

	public void setTotalEco(double totalEco) {
		this.totalEco = totalEco;
	}

	public double getTotalEnCaisse() {
		return getTotalEco() + getTotalIns();
	}

}
