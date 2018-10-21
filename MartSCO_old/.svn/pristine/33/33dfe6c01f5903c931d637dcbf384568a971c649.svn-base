package ecolage;

import org.joda.time.DateTime;

import salaire.AgentSalaire;
import abstractObject.AbstractPojo;
import agent.Agent;
import connection.DAO;
import connection.DAOFactory;

public class Operation extends AbstractPojo {

	public static final String ECOLAGE = "ECOLAGE",
			INSCRIPTION = "INSCRIPTION", ANNULATION_ECO = "ANNULATION ECO.",
			ANNULATION_INS = "ANNULATION INS.";
	private String matricule;
	private double somme;
	private String numOperation;
	private DateTime date = new DateTime();
	private String charge;
	private String justification;
	private EleveEcolage eleveEco;
	private EcolageComputer computer;
	private DAO agentdao;
	private Agent superCharger;
	private AgentSalaire agentSal;
	private String classeEleve, nomEleve, prenomEleve, sexeEleve;

	public Operation(String m, double s) {
		matricule = m;
		somme = s;
	}

	public String getNumOperation() {
		return numOperation;
	}

	public String getNomEleve() {
		return nomEleve;
	}

	public void setNomEleve(String nomEleve) {
		this.nomEleve = nomEleve;
	}

	public String getPrenomEleve() {
		return prenomEleve;
	}

	public void setPrenomEleve(String prenomEleve) {
		this.prenomEleve = prenomEleve;
	}

	public String getSexeEleve() {
		return sexeEleve;
	}

	public void setSexeEleve(String sexeEleve) {
		this.sexeEleve = sexeEleve;
	}

	public void setNumOperation(String numOperation) {
		this.numOperation = numOperation;
	}

	public double getMontant() {
		return somme;
	}

	public void setMontant(double somme) {
		this.somme = somme;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Operation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getId() {
		return numOperation;
	}

	@Override
	public void setId(String id) {
		numOperation = id;
	}

	@Override
	public String getIntitule() {
		return null;
	}

	@Override
	public void setIntitule(String inti) {

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
		return numOperation;
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

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getCharge() {
		return charge;
	}

	public void setCodeInfoCharge(String ch) {

		charge = ch;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public void setEleveEco(EleveEcolage el) {
		eleveEco = el;
	}

	public EleveEcolage getEleveEco() {
		return eleveEco;
	}

	public void setComputer(EcolageComputer comp) {
		computer = comp;
	}

	public EcolageComputer getComputer() {
		return computer;
	}

	public Agent getSuperCharger() {
		agentdao = DAOFactory.getDAO(DAO.AGENT);
		agentdao.load();

		superCharger = (Agent) agentdao.findObj(getCharge());

		return superCharger;
	}

	public void setAgentSal(AgentSalaire ag) {
		agentSal = ag;
	}

	public AgentSalaire getAgentSalaire() {
		return agentSal;
	}

	public String getClasseEleve() {
		// TODO Auto-generated method stub
		return classeEleve;
	}

	public void setClasseEleve(String classe) {
		this.classeEleve = classe;
	}
}
