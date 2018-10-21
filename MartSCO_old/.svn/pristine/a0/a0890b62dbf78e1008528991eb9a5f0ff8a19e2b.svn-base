package abstractObject;

import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observable;
import interfacePerso.Observer;
import interfacePerso.UpdateTools;

import javax.swing.JButton;

import org.joda.time.DateTime;

import tableComponent.MartTable;
import classe.Classe;
import classe.Niveau;
import connection.DAO;
import connection.DAOFactory;

public abstract class AbstractModel implements Observable, UpdateTools {
	public static int DELETE_ROW = 0;
	public static int DELETE_COMPLETELY = 0, DELETE_IN_CURRENTLIST = 1;
	public static int CREATE = 1, UPDATE = 2;

	protected DAO elvdao, clsdao, promoelvdao, elvclsdao, matdao, matpdao,
			andao, respdao, ensdao, promodao, notedao, rapmoydao, cursusdao,
			examdao, versecolagedao, operationdao, payersaldao, virsaldao,
			nivdao;
	protected Classe superClasse;
	protected String classe, annee, niveau, examen, matiere;
	protected String classeCor, anneeCor;
	protected int trimestre;
	protected MartTable tableChoix;
	protected MartList<Runnable> listeTraitement;

	public MartTable getTableChoix() {
		return tableChoix;
	}

	public void setTableChoix(MartTable tableChoix) {
		this.tableChoix = tableChoix;
	}

	protected JButton button;
	protected int type = 0, mode = 0;
	protected static AbstractModel instance;

	protected MartList<Observer> listObserver = new MartList<Observer>();
	protected Object data;

	// variable static
	public static int CREATE_LIST = 0, CREATE_OBJECT = 1;
	public static int UPDATE_CHOISE = 1, DEFAULT = 0;

	protected MartList<MartRangeable> ressources = new MartList<MartRangeable>();
	protected String deleteData;
	protected String etablissement;
	protected int typeUpdate = DAO.SIMPLE_UPDATE;
	protected DateTime date1;
	protected DateTime date2;
	protected int evaluation;
	protected int typeRapport;
	protected Niveau superNiveau;
	private boolean isPeriodSelection;

	// les methodes abstraites
	public abstract MartList<MartRangeable> getRessources();

	// methodes concraites
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void removeObserver() {
		this.listObserver = new MartList<Observer>();
	}

	public void notifyObserver() {
		// la mise ajour des observateur dans l'EDT
		for (Observer obs : listObserver) {
			try {
				obs.update();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out
				.println("Je les ai mis ajour tous; mieux encore dans l'EDT\n"
						+ listObserver.toString() + "\n Qui dit quoi?");
	}

	public void setData(Object data) {
		this.data = data;
		System.out.println("Le model comfirme la réception des données: "
				+ data.getClass());
	}

	public void setAnnee(String annee2) {
		annee = annee2;
		anneeCor = Constance.getCor(annee);
	}

	public void setClasse(String cl) {
		classe = cl;
		classeCor = Constance.getCor(classe);

		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		clsdao.load();
		superClasse = (Classe) clsdao.findObj(classe);
		niveau = superClasse.getNiveau();

		nivdao = DAOFactory.getDAO(DAO.NIVEAU);
		nivdao.load();
		superNiveau = (Niveau) nivdao.findObj(niveau);
	}

	public void setTrimestre(int trim) {
		trimestre = trim;
	}

	public String getAnnee() {
		return annee;
	}

	public String getClasse() {
		return classe;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setMatiere(String mat) {
		matiere = mat;
	}

	public String getMatiere() {
		return matiere;
	}

	public String getExamen() {
		return examen;
	}

	public void setExamen(String ex) {
		examen = ex;
	}

	public void setEts(String ets) {
		etablissement = ets;
	}

	public void setTypeUpdate(int typ) {
		typeUpdate = typ;
	}

	public void setDate1(DateTime date) {
		date1 = date;
	}

	public void setDate2(DateTime date) {
		date2 = date;
	}

	public DateTime getDate1() {
		return date1;
	}

	public DateTime getDate2() {
		return date2;
	}

	public synchronized void Launch(Runnable run) {
		try {
			new Thread(run).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEvaluation(int eval) {
		evaluation = eval;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setTypeRapport(int type) {
		typeRapport = type;
	}

	public int getTypeRapport() {
		// TODO Auto-generated method stub
		return typeRapport;
	}

	public boolean isPeriodSelection() {
		return isPeriodSelection;
	}

	public void setPeriodSelection(boolean isPeriodSelection) {
		this.isPeriodSelection = isPeriodSelection;
	}

}