package salaire;

import ecolage.Operation;
import graphicsModel.MartList;
import abstractObject.AbstractPojo;

public class AgentSalaire extends AbstractPojo {
	private String matricule;
	private Operation operationActuel;
	private MartList<Operation> operations = new MartList<Operation>();
	private int serial;
	private String sexe;
	private String nom;
	private String prenom;
	public static String PAYE_SAL = "SALAIRE", SAL = "SALAIRE MENSUEL",
			SAL_HEURE_COL = "SALAIRE HEURE_COL",
			SAL_HEURE_LEG = "SALAIRE HEURE_LEG",
			SAL_HEURE_LET = "SALAIRE HEURE_LET", PRIM_ANC = "PRIME ANCIENNETE",
			PRIM_RESP = "PRIME RESPONSABILITE",
			PRIM_HEURE_SUP = "PRIME HEURES SUPPLEMENTAIRES",
			PRIM_LOG = "PRIME LOGEMENT", AUTRES = "AUTRES PRIME";

	public AgentSalaire() {
	}

	public AgentSalaire(String m) {
		matricule = m;
	}

	public String getCodeInfo() {
		return matricule;
	}

	public void setCodeInfo(String matricule) {
		this.matricule = matricule;
	}

	public Operation getCurrentOperation() {
		return operationActuel;
	}

	public void setOperationActuel(Operation operationActuel) {
		this.operationActuel = operationActuel;
	}

	public void addOperation(Operation op) {
		operations.add(op);
	}

	@Override
	public String getId() {
		return matricule;
	}

	@Override
	public void setId(String id) {
		matricule = id;
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
	public String getPrimaryKey() {
		return matricule;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	public MartList<Operation> getlisteOperation() {
		return operations;
	}

	public void setOperations(MartList<Operation> operations) {
		this.operations = operations;
	}

	public int getSerieVersement() {
		return serial;
	}

	public void setSerieVersement(int serie) {
		serial = serie;
	}

	public void incrementSerial() {
		serial++;
	}

	public void setNumOperation(String numOp) {
		operationActuel.setNumOperation(numOp);
	}

	public void decrementSerial() {
		serial--;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setNom(String n) {
		nom = n;
	}

	public void setPrenom(String p) {
		prenom = p;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public static String getJustificationPaye(String str) {
		return PAYE_SAL + " " + str.toUpperCase();
	}

	public String decrisToi() {
		return nom + "" + prenom;
	}
}
