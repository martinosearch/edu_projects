package connection;

import java.sql.Connection;
import java.sql.Statement;

import abstractObject.AbstractPojo;
import classe.Classe;
import examen.Examen;
import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartOrdonneur;
import interfacePerso.MartRangeable;

public abstract class DAO<T> implements MartOrdonneur {
	protected T objet;
	protected Connection connect = null;
	protected int type = 0;
	protected int trimestre = 0;

	protected Object[][] data;
	protected String[] title;
	protected MartList<T> listT;
	protected MartList<Runnable> listeTraitement;
	protected String tableBull, tablePerso, tableArch, tableSet, tableCoef, tableEcolage, tableSalaire, tablePromo = "",
			tableExam = "", tablePersoExam = "", tableOperation, tableActivite, tablePayementSalaire, tableSerieNote;

	public String getTableCoef() {
		return tableCoef;
	}

	protected String matiere = "", matiereCor = "", annee = "", classe = "", niveau = "", anneeCor = "", clscor = "",
			moiscor = "", anactcor = "", typecor = "";
	private String order;

	protected int pAnnee, gAnnee;
	private String anneePre, anneePreCor;
	protected DAO clsdao, coefdao, promoelvdao, elvdao, examdao, matdao, serialdao, versecolagedao, operationdao;
	private Classe supClasse;
	protected String examen;
	private Examen superExamen;
	protected int typeUpdate = SIMPLE_UPDATE;

	// type de DAO
	public static int SIMPLE_UPDATE = 1, GENERAL_UPDATE = 0;
	public static int ELEVE = 0, CLASSE = 1, ANNEE = 2, MATIERE = 3, MATIERE_PROG = 4, NOTE = 5, ELEVE_CLASSE = 6,
			CURSUS_ELEVE = 8, AGENT = 9, REFERENCE = 10, NIVEAU = 11, COEFFICIENT = 12, USER = 13, SETTING = 14,
			RESPONSABLE = 15, RAPMOY = 16, HISTORIQUE = 17, EXAMEN = 18, MATIERE_PROG_EXAM = 19, ETABLISSEMENT = 20,
			CANDIDAT = 21, PROMO_ELEVE = 23, SERIAL = 24, CANDIDAT_PERSO = 25, NOTE_EXAM = 26, VERSEMENT_ECOLAGE = 28,
			OPERATION = 29, SALAIRE = 30, ACTIVITE = 31, PAYEMENT_SALAIRE = 32, SERIE_NOTE = 33,

			ALL = 1000;

	public DAO(Connection conn) {
		connect = conn;
	}

	public abstract boolean createObj(T obj);

	public abstract boolean updateObj(T obj);

	public abstract boolean update_create(T obj);

	public abstract boolean deleteObj(T obj);

	public abstract T findObj(String intitule);

	public abstract MartList<T> getList();

	// **********************************************************************
	// Methodes concraites
	public void setTableBull(String cls, String an) {
		try {
			if (!(cls.trim().isEmpty())) {
				this.clscor = Constance.getCor(cls);
			}

			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {

		}

		this.tableBull = "bull_" + clscor + "_" + anneeCor;
	}

	public void setTableSerieNote(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {

		}

		this.tableSerieNote = "table_serie_note_" + anneeCor;
	}

	public void setTableSet(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {

		}

		this.tableSet = "table_set_" + anneeCor;
	}

	public void setTableCoef(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {

		}

		this.tableCoef = "table_coef_" + anneeCor;
	}

	public void setTableEcolage(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.tableEcolage = "table_ecolage_" + anneeCor;
	}

	public void setTableSalaire(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.tableSalaire = "table_salaire_" + anneeCor;
	}

	public void setTablePayementSalaire(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.tablePayementSalaire = "table_payement_salaire_" + anneeCor;
	}

	public void setTableOperation(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.tableOperation = "table_operation_" + anneeCor;
	}

	public void setTableActivite(String an) {
		try {
			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.tableActivite = "table_activite_" + anneeCor;
	}

	public String getTableBull() {
		return this.tableBull;
	}

	public void setTablePerso(String cls, String an) {
		try {
			if (!(cls.trim().isEmpty())) {
				this.clscor = Constance.getCor(cls);
			}

			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {

		}

		this.tablePerso = "perso_" + clscor + "_" + anneeCor;
	}

	public String getTablePerso() {
		return this.tablePerso;
	}

	public void setTablePersoExam(String type1) {
		try {
			if (!(type1.trim().isEmpty())) {
				this.typecor = Constance.getCor(type1);
			}
		} catch (Exception e) {

		}

		this.tableExam = "exam_" + typecor;
		this.tablePersoExam = "perso_exam_" + typecor;
	}

	public String getTableExam() {
		return this.tableExam;
	}

	public String getTablePersoExam() {
		return this.tablePersoExam;
	}

	public void setTableArch(String cls, String an) {
		try {
			if (!(cls.trim().isEmpty())) {
				this.clscor = Constance.getCor(cls);
			}

			if (!(an.trim().isEmpty())) {
				this.anneeCor = Constance.getCor(an);
			}
		} catch (Exception e) {

		}

		this.tableArch = "archive_" + clscor + "_" + anneeCor;
	}

	public String getTableArch() {
		tableArch = "archive_" + clscor + "_" + anneeCor;
		return this.tableArch;
	}

	// executer une mise ajour dans la basse de données
	public boolean updateDB(String qry) {
		try {
			Statement state = MartConnection.getInstance().createStatement();
			state.executeUpdate(qry);
			System.out.println(qry);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean exists(T obj) {
		boolean found = false;
		String str = ((AbstractPojo) obj).getPrimaryKey();

		try {
			System.out.println("le code testé==================================>>" + str
					+ " Taille de la liste de recherche = " + listT.size() + "Eleve: " + ((MartRangeable) obj).getId());

			found = contains(str);
		} catch (Exception e) {
		}

		System.out.println("L'INSERTION EXISTE DEJA:" + found);
		return found;
	}

	private boolean contains(String str) {
		boolean bool = false;
		for (T obj : listT) {
			if (((AbstractPojo) obj).getPrimaryKey().equals(str)) {
				bool = true;

				break;
			}
		}

		return bool;
	}

	public void load(String mat, String cls, int trim, String an) {
		annee = an;
		classe = cls;
		matiere = mat;
		trimestre = trim;
		matiereCor = Constance.getCor(matiere);
		anneeCor = Constance.getCor(annee);

		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		clsdao.load();
		supClasse = (Classe) clsdao.findObj(classe);
		niveau = supClasse.getNiveau();

		try {
			if (!(annee.equals(""))) {
				pAnnee = Constance.getAnPetite(annee);
				gAnnee = Constance.getAnGrande(annee);
				anneePre = Constance.getAnneePrec(annee);
				anneePreCor = Constance.getCor(anneePre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		setTablePerso(cls, annee);
		setTableBull(cls, annee);
		setTableArch(cls, annee);
		setTableSet(annee);
		setTableCoef(annee);
		setTableEcolage(annee);
		setTableOperation(annee);
		setTableSalaire(annee);
		setTableActivite(annee);
		setTablePayementSalaire(annee);
		setTableSerieNote(an);

		getList();
	}

	public void load() {
		getList();
	}

	public void loadExam(String inti) {
		examen = inti;

		setTablePersoExam(inti);
		setTableSet(examen);
		setTableCoef(examen);

		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		examdao.load();

		superExamen = (Examen) examdao.findObj(examen);
		niveau = superExamen.getNiveau();

		getList();
	}

	public void loadExam(String mat, String inti) {
		matiere = mat;
		examen = inti;
		matiereCor = Constance.getCor(matiere);

		setTablePersoExam(inti);
		setTableSet(examen);
		setTableCoef(examen);

		getList();
	}

	public MartList<T> getListObt() {
		return listT;
	}

	public DAO getPromoElvDao() {
		return promoelvdao;
	}

	public void setTypeUpdate(int typ) {
		typeUpdate = typ;
	}

	public synchronized void launch(Runnable run) {
		try {
			Thread t = new Thread(run);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}