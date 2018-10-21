package matiere;

import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import javax.swing.JOptionPane;

import note.Note;
import progress.Progress;
import abstractObject.AbstractModel;
import annee.Decoupage;
import connection.DAO;
import connection.DAOFactory;
import eleve.EleveClasse;
import function.MartComputer;
import graphicsModel.MartList;

public class SectionMatiereModel extends AbstractModel {
	private SectionMatiere pObserver;
	private int nbreClasse;
	private String matiere, section1, section2;
	private int max;
	private MartComputer mc;
	private Progress progress;
	private Thread treat;
	private MartList<MatiereProg> matieres;

	@Override
	public void valider(int type) {
		System.out.println("Fusion de classe");
		for (Observer obs : listObserver) {
			if (obs instanceof SectionMatiere) {
				pObserver = (SectionMatiere) obs;
				break;
			}
		}

		tableChoix = pObserver.getTable();

		matiere = pObserver.getMatiere();
		section1 = pObserver.getSection1();
		section2 = pObserver.getSection2();

		if (matiere.equals("") || section1.equals("") || section2.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Veuillez remplir tous les champs!");
		}

		else {

			nbreClasse = tableChoix.getRowCount();

			elvdao = DAOFactory.getDAO(DAO.ELEVE);
			elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
			notedao = DAOFactory.getDAO(DAO.NOTE);

			elvdao.load();

			boolean process = false;
			matieres = new MartList<MatiereProg>();

			treat = new Thread(new Runnable() {

				public void run() {

					for (int i = 0; i < nbreClasse; i++) {

						try {
							String classe = (String) tableChoix
									.getValueAt(i, 1);

							matpdao.load("", classe, trimestre, annee);
							matieres = matpdao.getListObt();

							MatiereProg mat = (MatiereProg) matpdao
									.findObj(matiere);

							// ajout de la première section
							matpdao.load(section1, classe, trimestre, annee);

							MatiereProg sect1 = new MatiereProg(section1, mat
									.getCoef(), mat.getCharge());

							matpdao.update_create(sect1);

							// ajout de la deuxième section
							matpdao.load(section2, classe, trimestre, annee);

							MatiereProg sect2 = new MatiereProg(section2, mat
									.getCoef(), mat.getCharge());
							matpdao.update_create(sect2);

							// on supprime l'ancienne matière
							matpdao.deleteObj(mat);

						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					// on met les nouvelles notes des élèves
					Decoupage dec = new Decoupage();
					mc = new MartComputer();

					for (int i = 0; i < nbreClasse; i++) {
						String classe = (String) tableChoix.getValueAt(i, 1);

						decdao.load("", classe, trimestre, annee);
						dec = (Decoupage) decdao.findObj(classe);

						max = dec.getMax();

						elvclsdao.load("", classe, trimestre, annee);
						MartList<EleveClasse> eleves = elvclsdao.getListObt();

						for (int n = 0; n < max; n++) {
							MartList<Note> notes1 = new MartList<Note>();
							MartList<Note> notes2 = new MartList<Note>();
							trimestre = n + 1;

							notedao.load(matiere, classe, trimestre, annee);
							MartList<Note> notes = notedao.getListObt();

							// La barre de progression
							progress = new Progress();

							int progressMax = notes.size();
							// pour la barre de progression
							progress.setText("Traitement des données vague: "
									+ trimestre + " de la classe: " + classe);
							progress.getLoading(pObserver,
									"Chargement en Cours");
							progress.getProgress(pObserver, 0, progressMax);

							for (Note note : notes) {
								new Thread(new Runnable() {
									public void run() {
										// System.out.println("initialisation note1: "
										// + note.getNote1());
										MartList<Note> noteSect = mc
												.getNoteDivided(note);

										// ajout de la 1ère note
										notedao.load(section1, classe,
												trimestre, annee);
										System.out
												.println("note1: "
														+ noteSect.get(0)
																.getNote1()
														+ "et on a"
														+ noteSect.get(0)
																.getNote1str());

										notedao.update_create(noteSect.get(0));

										// ajout de la 2è note
										notedao.load(section2, classe,
												trimestre, annee);
										notedao.update_create(noteSect.get(1));

										// pour la barre de progression
										progress.increment();
									}
								}).start();

								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}

					notifyObserver();
				}
			});

			treat.start();
		}
	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

}
