package salaire;

import javax.swing.JOptionPane;

import ecolage.Operation;
import graphicsModel.MartList;
import connection.DAO;
import connection.DAOFactory;

public class SalaireComputer {
	protected String annee;
	protected MartList<Operation> listeOperation;
	private double salaire;
	private double prime;

	public SalaireComputer() {
		DAOFactory.getDAO(DAO.SETTING);
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public void setSalaireAgent(AgentSalaire elv) {
		listeOperation = elv.getlisteOperation();

		salaire = getSalaire();
		prime = getPrime();
	}

	public double getSalaire() {
		double regle = 0;
		for (Operation op : listeOperation) {
			System.out.println("Just: " + op.getJustification() + " egale: "
					+ op.getJustification().equals(AgentSalaire.SAL));

			if (op.getJustification().equals(AgentSalaire.SAL)) {
				regle = regle + op.getMontant();
			}

			if (op.getJustification().equals(AgentSalaire.SAL_HEURE_COL)) {
				String k = JOptionPane.showInputDialog(null,
						"Combien d'heure au Collège?");
				Double kk = Double.valueOf(k);

				regle = regle + (op.getMontant() * kk);
			}

			if (op.getJustification().equals(AgentSalaire.SAL_HEURE_LEG)) {
				String k = JOptionPane.showInputDialog(null,
						"Combien d'heure au LEG?");
				Double kk = Double.valueOf(k);

				regle = regle + (op.getMontant() * kk);
			}

			if (op.getJustification().equals(AgentSalaire.SAL_HEURE_LET)) {
				String k = JOptionPane.showInputDialog(null,
						"Combien d'heure au LET?");
				Double kk = Double.valueOf(k);

				regle = regle + (op.getMontant() * kk);
			}
		}

		return regle;
	}

	public double getPrime() {
		double regle = 0;

		for (Operation op : listeOperation) {
			if (!op.getJustification().equals(AgentSalaire.SAL)
					&& !op.getJustification()
							.equals(AgentSalaire.SAL_HEURE_COL)
					&& !op.getJustification()
							.equals(AgentSalaire.SAL_HEURE_LEG)
					&& !op.getJustification()
							.equals(AgentSalaire.SAL_HEURE_LET)) {
				regle = regle + op.getMontant();
			}
		}

		return regle;
	}

	public double getSalaireBrut() {
		return prime + salaire;
	}

	public double getSalaireNet() {
		return getSalaireBrut() - getPrelevement();
	}

	double getPrelevement() {
		return 0;
	}

	public double getSalaireAnnee() {
		double salaire = 0;
		for (Operation op : listeOperation) {
			salaire = salaire + op.getMontant();
		}

		return salaire;
	}

	public String getMontantEnLettres() {
		// TODO Auto-generated method stub
		return null;
	}
}
