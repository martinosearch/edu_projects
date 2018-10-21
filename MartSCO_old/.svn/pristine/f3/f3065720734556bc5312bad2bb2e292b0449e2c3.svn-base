package note;

import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import function.Statistician;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import matiere.MatiereProg;

public class NoteViewerBull extends NoteViewer {

    /**
     * La méthode qui fait les chargement nécessaire pour les bulletins
     * 
     * @param cls
     * @param trim
     * @param an
     */
    @Override
    public void load(String cl, int trim, String an) {
	// initialisation de variables
	type = BULLETIN;
	this.classe = cl;
	this.annee = an;
	this.trimestre = trim;

	SeriesNotes = new MartList<>();
	Classeurs = new MartList<>();

	long time1 = System.currentTimeMillis();// traceur de temps

	staman = new Statistician();
	staman.addClasse(classe);

	notedao = DAOFactory.getDAO(DAO.NOTE);
	clsdao = DAOFactory.getDAO(DAO.CLASSE);
	matdao = DAOFactory.getDAO(DAO.MATIERE);
	matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
	elvdao = DAOFactory.getDAO(DAO.ELEVE);
	elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
	respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
	rapmoydao = DAOFactory.getDAO(DAO.RAPMOY);
	nivdao = DAOFactory.getDAO(DAO.NIVEAU);

	matdao.load();
	nivdao.load();
	clsdao.load();
	elvdao.load();
	elvclsdao.load(new String(), classe, trimestre, annee);
	respdao.load(new String(), classe, trimestre, annee);
	matpdao.load(new String(), classe, trimestre, annee);

	matieres = matpdao.getListObt();
	elevesC = elvclsdao.getListObt();

	eleves = elvdao.getListObt();

	long time2 = System.currentTimeMillis(); // traceur de temps

	// ************on fait une mise à jour pour toute les matière
	// afin d'obtenir les rang
	try {
	    for (MatiereProg mat : matieres) {
		loadInfoMat(mat.getIntitule());// traitement#################"

		staman.addMatiere(mat.getIntitule() + 1);
		staman.addMatiere(mat.getIntitule() + 2);
		staman.addMatiere(mat.getIntitule() + "dev");
		staman.addMatiere(mat.getIntitule() + "compo");
		staman.addMatiere(mat.getIntitule());

		try {
		    incrementAvancers();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

	long time3 = System.currentTimeMillis(); // traceur de temps

	loadInfoClasse();// traitement#################

    }

    public static void main(String[] args) {
	Constance.initialize();
	long time1 = System.currentTimeMillis();
	NoteViewer nView = new NoteViewerBull();
	nView.load("6ème", 3, "2015-2016");
	long time2 = System.currentTimeMillis();
	System.out.println("###########################################" + "La durée de l'exécution est:"
		+ (time2 - time1) / 1000 + " s");
    }

    @Override
    public void load(String string) {
	// TODO Auto-generated method stub

    }

    @Override
    public void load(int evaluation, String classe2, int trimestre2, String annee2) {
	// TODO Auto-generated method stub

    }

}
