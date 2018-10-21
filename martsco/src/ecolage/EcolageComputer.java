package ecolage;

import graphicsModel.MartList;

import javax.swing.JOptionPane;

import agent.Agent;
import classe.Classe;
import configurationAppli.Setting;
import connection.DAO;
import connection.DAOFactory;

public class EcolageComputer {
	protected String annee;
	protected MartList<Operation> listeOperation;
	private EleveEcolage eleve;
	private int nbreMasculin = 0;
	private int nbreFeminin = 0;
	private MartList<Operation> listeOperationAgent;
	private double totalEco = 0;
	private double totalIns = 0;
	private String classe;
	private DAO setdao;
	private boolean avertissement = false;
	private DAO clsdao;
	private Classe superClasse;

	public EcolageComputer() {
		nbreMasculin = 0;
		nbreFeminin = 0;
		setdao = DAOFactory.getDAO(DAO.SETTING);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		clsdao.load();
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public MartList<Operation> getListeOperation() {
		return listeOperation;
	}

	public void setEleveEcolage(EleveEcolage elv) {
		eleve = elv;
		try {
			classe = eleve.getClasse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		listeOperation = new MartList<Operation>();

		try {
			listeOperation = eleve.getlisteOperation();
			if (eleve.getSexe().equals("M") || eleve.getSexe().equals("G")) {
				nbreMasculin++;
			}

			if (eleve.getSexe().equals("F")) {
				nbreFeminin++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		totalEco += getRegle();
		totalIns += getInscription();
	}

	public double getRegle() {
		double regle = 0;
		for (Operation op : listeOperation) {
			if (op.getJustification().equals(Operation.ECOLAGE)) {
				regle = regle + op.getMontant();
			}

			if (op.getJustification().equals(Operation.ANNULATION_ECO)) {
				regle = regle - op.getMontant();
			}
		}

		return regle;
	}

	public double getInscription() {
		double regle = 0;

		for (Operation op : listeOperation) {
			if (op.getJustification().equals(Operation.INSCRIPTION)) {
				regle = regle + op.getMontant();
			}

			if (op.getJustification().equals(Operation.ANNULATION_INS)) {
				regle = regle - op.getMontant();
			}
		}

		return regle;
	}

	public double getReste() {
		double ecolage = 0;
		setdao.load(null, null, 0, annee);
		superClasse = (Classe) clsdao.findObj(classe);

		System.out.println("=================>" + superClasse.getNiveau() + " "
				+ classe);

		try {
			ecolage = Double.parseDouble((String) ((Setting) setdao
					.findObj("ecolage_" + superClasse.getNiveau()))
					.getAttribut());

			System.out.println("=================>" + ecolage);
		} catch (Exception e) {
			e.printStackTrace();

			if (avertissement == false) {
				JOptionPane.showMessageDialog(null,
						"L'écolage de la classe en cours n'est pas défini.");
				avertissement = true;
			}
		}

		return ecolage - getRegle();
	}

	public int getEff() {
		return nbreMasculin + nbreFeminin;
	}

	public int getMasculin() {
		return nbreMasculin;
	}

	public int getFeminin() {
		return nbreFeminin;
	}

	public void setAgent(Agent agent) {
		listeOperationAgent = agent.getListeOperation();
	}

	public double getTotalEncaisseAgent() {
		return getInscriptionEncaisseAgent() + getEcolageEncaisseAgent();
	}

	public double getEcolageEncaisseAgent() {
		double encaisse = 0;
		for (Operation op : listeOperationAgent) {
			if (op.getJustification().equals(Operation.ECOLAGE))
				encaisse = encaisse + op.getMontant();
		}
		return encaisse;
	}

	public double getInscriptionEncaisseAgent() {
		double encaisse = 0;
		for (Operation op : listeOperationAgent) {
			if (op.getJustification().equals(Operation.INSCRIPTION))
				encaisse = encaisse + op.getMontant();
		}
		return encaisse;
	}

	public double getTotalEcolage() {
		// TODO Auto-generated method stub
		return totalEco;
	}

	public double getTotalInsription() {
		// TODO Auto-generated method stub
		return totalIns;
	}

	public double getTotalEncaisse() {
		return totalIns + totalEco;
	}

	public void dontConsider(Operation op) {
		if (op.getJustification().equals(Operation.INSCRIPTION))
			totalIns -= op.getMontant();
		if (op.getJustification().equals(Operation.ECOLAGE))
			totalEco -= op.getMontant();
	}

	public String getRegleEnLettres() {
		return "-";
	}
}
