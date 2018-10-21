package eleve;

import images.PictureFinder;
import interfacePerso.StatutSco;

import java.io.File;
import java.util.ArrayList;

import eleve.Scolarite.Statut;
import function.Constance;
import abstractObject.AbstractPojo;

public class EleveClasse extends AbstractPojo implements StatutSco {

	// les différentes variables
	private String classe = "";
	private Scolarite scolarite;
	private String niveau;
	private int rang = 0;
	private String nom;
	private String prenom;
	private String codeInfo;
	private String sexe;
	private String ets;
	private String numTable = " - ";
	private int salle = 1;
	private int numOrdre;
	private int effSalle;

	public String getNumTable() {
		return numTable;
	}

	public String getEts() {
		return ets;
	}

	// les constructeurs
	public EleveClasse(String nom, String prenom, String code) {
		this.nom = nom;
		this.prenom = prenom;
		this.codeInfo = code;
	}

	public EleveClasse(ArrayList listIns) {
		this.nom = (String) listIns.get(0);
		this.prenom = (String) listIns.get(1);
		this.codeInfo = (String) listIns.get(2);
	}

	public EleveClasse() {
	}

	public EleveClasse(String matricule) {
		this.codeInfo = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom_eleve) {
		this.nom = nom_eleve;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom_eleve) {
		this.prenom = prenom_eleve;
	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public void setSco(Scolarite scolarite) {
		this.scolarite = scolarite;
	}

	@Override
	public String getClasse() {
		return classe;
	}

	public String getCodeInfo() {
		return codeInfo;
	}

	@Override
	public Statut getStatut() {
		Statut st = new Scolarite().getStatut(niveau);
		try {
			st = scolarite.getStatut(niveau);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println("****************" + define() + "sco:"
		// + st.toStringComplete() + " grade:" + st.intValue());
		return st;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public String getNiveau() {
		return this.niveau;
	}

	@Override
	public void setId(String id) {
		codeInfo = id;
	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return rang;
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
	public void setClasse(String cls) {
		classe = cls;
	}

	public String getPrimaryKey() {
		return codeInfo;
	}

	@Override
	public String getInfo(int i) {
		System.out.println("c'est plutôt moi EleveClasse");
		String info = "";
		if (i == 2)
			info = getNom();
		if (i == 3)
			info = getPrenom();
		if (i == 4)
			info = getSexe();
		return info;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return codeInfo;
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
	public void setRang(int rg) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCor() {
		return null;
	}

	public String decrisToi() {
		return nom + "  " + prenom;
	}

	public void setEts(String ets) {
		this.ets = ets;
	}

	public String isPhoto() {
		String photo = "Non";

		File file = new PictureFinder().getPhotoEleve(getCodeInfo());

		if (file.exists()) {
			photo = "Oui";
		}

		return photo;
	}

	public void setNumTable(String num) {
		numTable = num;
	}

	public int getSalle() {
		// TODO Auto-generated method stub
		return salle;
	}

	public void setSalle(int sal) {
		salle = sal;
	}

	public void setNumOrdre(int count) {
		numOrdre = count;
	}

	public void setEffSalle(int eff) {
		effSalle = eff;
	}

	public int getNumOrdre() {
		return numOrdre;
	}

	public int getEffSalle() {
		return effSalle;
	}
}
