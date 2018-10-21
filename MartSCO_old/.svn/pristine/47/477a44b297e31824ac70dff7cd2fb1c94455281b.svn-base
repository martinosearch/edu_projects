package abstractObject;

import classe.Classe;
import connection.DAO;
import connection.DAOFactory;
import examen.Examen;

public abstract class AbstractControler {
	protected AbstractModel model;
	protected Object data;
	protected String annee, classe;
	protected int trimestre;

	protected String deleteData = " la selection ";
	protected boolean done = true;
	protected String examen;
	protected Examen superExamen;
	private boolean askBefore = true;
	private DAO clsdao = DAOFactory.getDAO(DAO.CLASSE);
	protected Classe superClasse;

	public AbstractControler(AbstractModel model) {
		this.model = model;
		clsdao.load();
	}

	// methode abstraite demandant un contrôle
	// methode à redéfinir dans les classes filles
	public abstract void valider();

	public abstract void supprimer(int deleteOption);

	public AbstractModel getModel() {
		return model;
	}

	public void setData(Object dt) {
		data = dt;
	}

	public void setAnnee(String an) {
		annee = an;
	}

	public void setTrimestre(int trim) {
		trimestre = trim;
	}

	public void setClasse(String cl) {
		classe = cl;
		superClasse = (Classe) clsdao.findObj(classe);
		/*
		 * System.out
		 * .println("###########################################################"
		 * + superClasse + "Classe: " + superClasse.getIntitule() + " type: " +
		 * superClasse.getTypeEns());
		 */
	}

	public boolean getWellDonne() {
		return done;
	}

	public void setExamen(String exam) {
		examen = exam;
	}

	public void setEts(String ets) {
	}

	public boolean isAskBefore() {
		return askBefore;
	}

	public void setAskBefore(boolean askBefore) {
		this.askBefore = askBefore;
	}
}
