package note;

import abstractObject.Rapport;
import annee.Decoupage;
import classe.Classe;
import classe.Niveau;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;
import etablissement.FilterEts;
import examen.Examen;
import function.Constance;
import function.Statistician;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import matiere.MatiereProg;

public class NoteViewerExam extends NoteViewer {

    private ConfigClasse confClasse;
    private Decoupage decoupage;

    /**
     * la méthode qui fait les chargement nécessaire pour l'examen
     * 
     * @param exam
     */
    @Override
    public void load(String exam) {
	type = RELEVE;
	SeriesNotes = new MartList<>();
	Classeurs = new MartList<>();

	notedao = DAOFactory.getDAO(DAO.NOTE_EXAM);
	clsdao = DAOFactory.getDAO(DAO.CLASSE);
	matdao = DAOFactory.getDAO(DAO.MATIERE);
	matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG_EXAM);
	elvdao = DAOFactory.getDAO(DAO.CANDIDAT);
	elvclsdao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
	respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
	rapmoydao = DAOFactory.getDAO(DAO.RAPMOY);
	nivdao = DAOFactory.getDAO(DAO.NIVEAU);
	examdao = DAOFactory.getDAO(DAO.EXAMEN);

	matdao.load();
	nivdao.load();
	clsdao.load();
	elvdao.load();
	examdao.load();

	Examen supExamen = (Examen) examdao.findObj(exam);
	model = supExamen.getModelRap();
	// System.out.println("Le modeeeeel
	// ==============================================>>>>>"
	// + model);

	titre = "Résultat du " + supExamen.decrisToi();
	examen = supExamen.getIntitule();

	elvclsdao.loadExam(examen);
	matpdao.loadExam(examen);
	matieres = matpdao.getListObt();

	FilterEts filter = new FilterEts(etablissement);
	elevesC = filter.getListe(elvclsdao.getListObt());

	staman = new Statistician();
	staman.setModel(supExamen.getModelRap());

	staman.addClasse(examen);
	eleves = elvdao.getListObt();

	// ************on fait une mise à jour pour toute les matière
	// afin d'obtenir les rang
	for (MatiereProg mat : matieres) {
	    loadInfoMat(mat.getIntitule());

	    // ajout des matière au statisticien
	    // ce qui permettra plus tard de faire les statistique de ces
	    // matière
	    staman.addMatiere(mat.getIntitule());

	    try {
		incrementAvancers();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    // ***********************FIN*******************************
	}

	loadInfoClasse();
    }

    @Override
    public void load(int eval, String cl, int trim, String an) {
	evaluation = eval;
	classe = cl;
	trimestre = trim;
	annee = an;

	notedao = DAOFactory.getDAO(DAO.NOTE);
	clsdao = DAOFactory.getDAO(DAO.CLASSE);
	matdao = DAOFactory.getDAO(DAO.MATIERE);
	matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
	elvdao = DAOFactory.getDAO(DAO.ELEVE);
	elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
	respdao = DAOFactory.getDAO(DAO.RESPONSABLE);
	nivdao = DAOFactory.getDAO(DAO.NIVEAU);

	matdao.load();
	nivdao.load();
	clsdao.load();
	elvdao.load();

	matpdao.load(null, classe, trimestre, annee);
	elvclsdao.load(null, classe, trimestre, annee);

	matieres = matpdao.getListObt();

	niveau = (Niveau) nivdao.findObj(cl);
	if (niveau.getTypeEns().equals("PRIM")) {
	    model = Rapport.MODEL_PRIM;
	}

	FilterEts filter = new FilterEts(etablissement);
	elevesC = filter.getListe(elvclsdao.getListObt());
	superClasse = (Classe) clsdao.findObj(classe);
	confClasse = new ConfigClasse(superClasse, annee);
	decoupage = confClasse.persoClasse.getDecoupage();
	decoupage.setSection(trimestre);

	type = RELEVE;
	typeRapport = Rapport.EVAL_TRIMESTRIELLE;
	SeriesNotes = new MartList<>();
	Classeurs = new MartList<>();

	// pour le titre
	if (evaluation == Rapport.INTERRO1) {
	    titre = ("de l'évaluation 1 du " + decoupage.toString()).toUpperCase();
	} else if (evaluation == Rapport.INTERRO2) {
	    titre = ("de l'évaluation 2 du " + decoupage.toString()).toUpperCase();
	} else if (evaluation == Rapport.DEVOIR) {
	    titre = ("du devoir du " + decoupage.toString()).toUpperCase();
	} else {
	    titre = ("de la composition du " + decoupage.toString()).toUpperCase();
	}

	staman = new Statistician();
	staman.setModel(model);

	// Ajout des différentes statistiques
	staman.addClasse(classe + 1);
	staman.addClasse(classe + 2);
	staman.addClasse(classe + "dev");
	staman.addClasse(classe + "compo");
	staman.addClasse(classe);

	eleves = elvdao.getListObt();

	// ************on fait une mise à jour pour toute les matière
	// afin d'obtenir les rang
	for (MatiereProg mat : matieres) {
	    loadInfoMat(mat.getIntitule());

	    staman.addMatiere(mat.getIntitule() + 1);
	    staman.addMatiere(mat.getIntitule() + 2);
	    staman.addMatiere(mat.getIntitule() + "dev");
	    staman.addMatiere(mat.getIntitule() + "compo");
	    staman.addMatiere(mat.getIntitule());

	    // ************pour la barre de progression ****************
	    try {
		incrementAvancers();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    // ***********************FIN*******************************
	}

	loadInfoClasse();
    }

    public static void main(String[] args) {
	Constance.initialize();
	long time1 = System.currentTimeMillis();
	NoteViewer nView = new NoteViewerExam();
	nView.load("6ème");
	long time2 = System.currentTimeMillis();

	System.out.println("###########################################" + "La durée de l'exécution est:"
		+ (time2 - time1) / 1000 + " s");
    }

    @Override
    public void load(String string, int i, String string2) {
	// TODO Auto-generated method stub

    }
}
