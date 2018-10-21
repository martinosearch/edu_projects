package classe;

import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.Decoupage;
import matiere.MatiereProg;
import note.Note;
import configurationClasse.ConfigClasse;
import connection.DAO;
import connection.DAOFactory;
import eleve.Eleve;
import eleve.EleveClasse;
import eleve.EleveControler;
import eleve.EleveModel;

public class FusionClasseModel extends AbstractModel {
	private FusionClasse observer;
	private int nbreClasse;
	private String intitule;
	private int max;
	private Classe superClasse;
	private ConfigClasse conf;

	@Override
	public void valider(int type) {
		System.out.println("Fusion de classe");
		for (Observer obs : listObserver) {
			if (obs instanceof FusionClasse) {
				observer = (FusionClasse) obs;
				break;
			}
		}

		tableChoix = observer.getTable();

		intitule = observer.getIntitule();

		if (intitule.equals("")) {
			JOptionPane.showMessageDialog(null,
					"Veuillez définir le nom de la fusion!");
		}

		else {
			// On crée la nouvelle classe
			clsdao = DAOFactory.getDAO(DAO.CLASSE);
			clsdao.load();
			try {
				Classe indiv = (Classe) clsdao.findObj((String) tableChoix
						.getValueAt(0, 1));
				niveau = indiv.getNiveau();
				Classe nouvClasse = new Classe(intitule, niveau);
				clsdao.update_create(nouvClasse);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Aucune classe n'a été choisie");
			}

			nbreClasse = tableChoix.getRowCount();

			elvdao = DAOFactory.getDAO(DAO.ELEVE);
			elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
			matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
			notedao = DAOFactory.getDAO(DAO.NOTE);

			elvdao.load();

			AbstractModel eleveModel = new EleveModel();
			AbstractControler eleveControler = new EleveControler(eleveModel);

			// on ajoute les élèves
			for (int i = 0; i < nbreClasse; i++) {
				String classe = (String) tableChoix.getValueAt(i, 1);

				elvclsdao.load("", classe, trimestre, annee);
				MartList<EleveClasse> eleves = elvclsdao.getListObt();

				for (EleveClasse el : eleves) {
					Eleve eleve = (Eleve) elvdao.findObj(el.getCodeInfo());
					eleve.setClasse(classe);

					eleveModel.setData(eleve);
					eleveControler.setData(eleve);

					eleveModel.setAnnee(annee);
					eleveControler.setAnnee(annee);

					eleveModel.setClasse(intitule);
					eleveControler.setClasse(intitule);

					eleveControler.valider();
				}
			}

			// on ajoute les matières au programme
			int size = 0;
			boolean process = false;
			MartList<MatiereProg> matieres = new MartList<MatiereProg>();

			for (int i = 0; i < nbreClasse; i++) {
				String classe = (String) tableChoix.getValueAt(i, 1);

				matpdao.load("", classe, trimestre, annee);
				matieres = matpdao.getListObt();

				if (i == 0) {
					size = matieres.size();
				} else if (size != matieres.size()) {
					JOptionPane.showMessageDialog(null, "La classe: " + classe
							+ " n'a pas les mêmes matières que les autres!");
				} else {

					System.out.println("Liste de la classe " + i + " : " + size
							+ "éléments");

					process = true;
				}
			}

			if (process == true) {
				matpdao.load("", intitule, trimestre, annee);
				for (MatiereProg mat : matieres) {
					matpdao.update_create(mat);
				}
			}

			// on remet les notes des élèves
			Decoupage dec = new Decoupage();
			for (int i = 0; i < nbreClasse; i++) {
				String classe = (String) tableChoix.getValueAt(i, 1);
				superClasse = (Classe) clsdao.findObj(classe);

				conf = new ConfigClasse(superClasse, annee);
				dec = conf.persoClasse.getDecoupage();

				max = dec.getMax();
				// System.out.println("==============================>>" + max);
				elvclsdao.load("", classe, trimestre, annee);
				MartList<EleveClasse> eleves = elvclsdao.getListObt();

				for (int n = 0; n < max; n++) {

					for (MatiereProg mat : matieres) {
						MartList<Note> notes = new MartList<Note>();
						notedao.load(mat.getIntitule(), classe, (n + 1), annee);

						for (EleveClasse el : eleves) {
							Note note = (Note) notedao
									.findObj(el.getCodeInfo());
							note.setMatiere(mat.getIntitule());
							notes.add(note);
						}

						notedao.load(mat.getIntitule(), intitule, (n + 1),
								annee);

						for (Note note : notes) {
							notedao.updateObj(note);
							// System.out.println("Je suis executé"
							// + note.getCodeInfo() + note.getMatiere());
						}
					}
				}
			}
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
