package eleve;

import java.util.ArrayList;

import org.joda.time.DateTime;

import abstractObject.AbstractPojo;
import connection.DAO;
import connection.DAOFactory;

public class Eleve extends AbstractPojo {
	// les différentes variables
	protected String nom = "";
	protected String prenom = "";
	protected String sexe = "M";
	protected String classe = "";
	protected String codeInfo = "";
	protected DateTime datenais;
	protected DateTime dateins;
	protected DateTime datesortie;
	protected String nomparent = "";
	protected String profession = "";
	protected String telPersoPrev = "";
	protected String ets = "";
	protected Object[][] eleve;
	protected Object eleve1;
	protected int type = 0;
	protected double value = 0;
	private int rang;
	private double moy1;
	private double moy2;
	private double moy3;
	private static DAO cursusdao = DAOFactory
			.getDAO(DAO.CURSUS_ELEVE);
	private static String annee = "";

	// les constructeurs
	public Eleve(String n, String pren, String sx, String cls, String code,
			DateTime dateNais, DateTime dEntree, DateTime dSortie,
			String pPrev, String profPerso, String telPerso) {

		nom = n;
		prenom = pren;
		sexe = sx;
		classe = cls;
		codeInfo = code;
		datenais = dateNais;
		dateins = dEntree;
		datesortie = dSortie;
		nomparent = pPrev;
		profession = profPerso;
		telPersoPrev = telPerso;
		primaryKey = nom + prenom;
	}

	public Eleve(ArrayList listIns) {
		nom = (String) listIns.get(0);
		prenom = (String) listIns.get(1);
		sexe = (String) listIns.get(2);
		classe = (String) listIns.get(3);
		codeInfo = (String) listIns.get(4);

		try {
			datenais = new DateTime(listIns.get(5));
		} catch (Exception e) {
			// e.printStackTrace();
		}
		try {
			dateins = new DateTime(listIns.get(6));
		} catch (Exception e) {
			// e.printStackTrace();
		}

		try {
			datesortie = new DateTime(listIns.get(7));
		} catch (Exception e) {
			// e.printStackTrace();
		}

		nomparent = (String) listIns.get(8);
		profession = (String) listIns.get(9);
		telPersoPrev = (String) listIns.get(10);
		primaryKey = nom + prenom;
	}

	public Eleve() {
	}

	// les accesseurs
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom_eleve) {
		this.nom = nom_eleve;
		primaryKey = nom + prenom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom_eleve) {
		this.prenom = prenom_eleve;
		primaryKey = nom + prenom;

	}

	public String getSexe() {
		return this.sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getClasse() {
		return this.classe;
	}

	public void setClasse(String classe_eleve) {
		this.classe = classe_eleve;
	}

	public String getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(String code) {
		codeInfo = code;
	}

	public DateTime getDateNais() {
		return new DateTime(datenais);
	}

	public void setDateNais(DateTime dateTime) {
		this.datenais = dateTime;
	}

	public DateTime getDateEntree() {
		return new DateTime(dateins);
	}

	public void setDateEntree(DateTime date) {
		dateins = date;
	}

	public DateTime getDateSortie() {
		return new DateTime(datesortie);
	}

	public void setDateSortie(DateTime date) {
		datesortie = date;
	}

	public String getPersoPrev() {
		return this.nomparent;
	}

	public void setPersoPrev(String nom) {
		this.nomparent = nom;
	}

	public String getProfessionPersoPrev() {
		return profession;
	}

	public void setProfessionPersoPrev(String pro) {
		this.profession = pro;
	}

	public String getTelPersoPrev() {
		return telPersoPrev;
	}

	public void setAddressParent(String tel) {
		this.telPersoPrev = tel;
	}

	public String getEts() {
		return this.ets;
	}

	public void setEts(String ets) {
		this.ets = ets;
	}

	@Override
	public String getPrimaryKey() {
		return primaryKey;
	}

	public String decrisToi() {
		return nom + "  " + prenom;
	}

	@Override
	public String getCor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		return codeInfo;
	}

	@Override
	public void setId(String id) {
		codeInfo = id;
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
		return rang;
	}

	@Override
	public void setRang(int rg) {
		rang = rg;
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub
		value = val;
	}

	public String getClasseAnnee(String an) {
		String classe = "";
		try {
			if (!(an.equals(annee))) {
				cursusdao.load(new String(), new String(), 1, an);
			}

			annee = an;

			Cursus c = (Cursus) cursusdao.findObj(codeInfo);
			classe = c.getClasse();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return classe;
	}

	public static void main(String[] arg) {
		String matricule = "ELVA0471";
		DAO dao = DAOFactory.getDAO(DAO.ELEVE);
		dao.load();
		Eleve eleve = (Eleve) dao.findObj(matricule);

		String classe = eleve.getClasseAnnee("2017-2018");
		System.out.println("L'eleve" + eleve.decrisToi() + " La classe: "
				+ classe);
	}

	public void initPrimaryKey() {
		primaryKey = nom + prenom;
	}

	@Override
	public String getInfo(int i) {
		System.out.println("c'est moi qui suis appelé");
		String info = "";
		if (i == 2)
			info = getNom();
		if (i == 3)
			info = getPrenom();
		if (i == 4)
			info = getSexe();
		return info;
	}

	// les méthode suivantes son utilisés pour stocker les moyennes.
	public double getMoyenne1() {
		return moy1;
	}

	public double getMoyenne2() {
		return moy2;
	}

	public double getMoyenne3() {
		return moy3;
	}

	public void setMoyenne1(double moy) {
		moy1 = moy;
	}

	public void setMoyenne2(double moy) {
		moy2 = moy;
	}

	public void setMoyenne3(double moy) {
		moy3 = moy;
	}
}
