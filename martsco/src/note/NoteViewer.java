package note;

import abstractObject.Rapport;
import annee.Decoupage;
import classe.Classe;
import classe.Niveau;
import configurationClasse.ConfigClasse;
import connection.DAO;
import eleve.Eleve;
import eleve.EleveClasse;
import function.MartComputer;
import function.MartConverter;
import function.Statistician;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import matiere.MatiereProg;
import progress.Avancer;

public abstract class NoteViewer {
    public int evaluation = Rapport.COMPO;
    protected String moyClsStr, moyStr, ptObtStr;
    protected String classe, annee, etablissement;
    protected Classe superClasse;
    protected Niveau niveau;

    protected int trimestre;
    public int typeRapport;

    protected DAO clsdao, matpdao, matdao, elvdao, elvclsdao, rapmoydao, notedao, respdao, nivdao, examdao, decdao;

    protected String table;
    protected InfoClasse infocls;
    protected int ontComp = 0, nMoy = 0;

    protected MartConverter mconv = new MartConverter();

    protected MartList<Avancer> Avancers;
    protected static int BULLETIN = 0, RELEVE = 1;

    protected MartList<MatiereProg> matieres;
    protected MartList<EleveClasse> elevesC;

    protected MartList<Eleve> eleves;

    protected MartList<MartList<MartRangeable>> SeriesNotes;
    protected MartList<MartList<MartRangeable>> Classeurs;

    protected MartList<MartRangeable> Moyennes;
    protected MartList<MartRangeable> MoyennesAn;
    protected Statistician staman;
    protected String examen;
    protected int type;
    protected int model = Rapport.MODEL_SECOND;

    protected EleveClasse eleve;
    protected MatiereProg matProg;
    protected Moyenne moyenneAn;
    protected String id;
    protected String titre;
    protected MartComputer mc;
    private ConfigClasse conf;
    private ConfigClasse confClasse;
    private Decoupage decoupage;

    public NoteViewer() {
	Avancers = new MartList<>();
    }

    public void incrementAvancers() {
	for (Avancer avancer : Avancers) {
	    avancer.increment();
	}
    }

    public void loadInfoClasse() {
	new InfoClasseLoader().treat(this);
    }

    public void loadInfoMat(String mat) {
	new InfoMatiereLoader().treat(this, mat);
    }

    public InfoNote getNotes(MatiereProg matiere, EleveClasse elv) {
	InfoEleveFinder finder = new InfoEleveFinder(this);
	return finder.get(matiere, elv);
    }

    public Moyenne getMoyenne(EleveClasse elv) {
	Moyenne moy = null;

	try {
	    moy = (Moyenne) Moyennes.find(elv.getCodeInfo());
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return moy;
    }

    public void doArchive(EleveClasse elv, Moyenne moyenne) {
	new Archiver().treat(this, elv, moyenne);
    }

    public InfoClasse getInfoClasse() {
	return infocls;
    }

    public StaMatiereClasse getStaMatiereClasse(String matiere) {
	StaMatiereClasseFinder finder = new StaMatiereClasseFinder(this);
	return finder.find(matiere);
    }

    // renvoi le rang général
    public String getRang(MatiereProg mat, EleveClasse elv) {
	RangFinder finder = new RangFinder(this);
	return finder.find(mat, elv);
    }

    // Trouve l'élève pour un rang donn�
    public Eleve getElvAuRg(String mat, int rg) {
	RangFinder finder = new RangFinder(this);
	return finder.find(mat, rg);
    }

    public void setProgressAvancer(Avancer avancer2) {
	Avancers.add(avancer2);
    }

    public abstract void load(String string, int i, String string2);

    public abstract void load(String string);

    public abstract void load(int evaluation, String classe2, int trimestre2, String annee2);

    public String getTitreRapport() {
	return titre;
    }

    public void setEtablissement(String ets) {
	etablissement = ets;
    }

}