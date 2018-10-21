package candidat;

import tableComponent.MartTabModel;
import abstractObject.AbstractModel;
import function.MatriGEN;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import connection.DAO;
import connection.DAOFactory;
import eleve.Eleve;
import eleve.EleveClasse;

public class CdtModel extends AbstractModel {

	private MatriGEN codeGen;
	private MartList<Eleve> eleves;
	private boolean first;
	private String previousExamen = "";
	private DAO cdtDao;
	private DAO cdtExamDao;

	public CdtModel() {
		cdtDao = DAOFactory.getDAO(DAO.CANDIDAT);
		cdtExamDao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
	}

	public void valider(int tpe) {
		long tm1 = System.currentTimeMillis();
		this.type = tpe;
		if (type == 1) {
			codeGen = new MatriGEN(MatriGEN.CANDIDAT);

			// inscription de l'élève dans la base générale
			Eleve eleve = (Eleve) data;

			// System.out.println("*****************Eleve recu "
			// + eleve.getPrimaryKey() + "code info="
			// + eleve.getCodeInfo());

			// System.out.println("***************** " + previousClasse + "  "
			// + instancePromoElvDao + "Eleve= " + eleve.getPrimaryKey()
			// + eleve.getCodeInfo());

			if (!(previousExamen.equals(examen))) {
				cdtDao.load();// juste pour l'annee
				cdtExamDao.loadExam(examen);
				System.out
						.println("Chargement dans modelCdt=================================>>");
			}

			// sauvegarde de la classe actuelle
			try {
				previousExamen = examen;
			} catch (Exception e) {

			}

			// Vérification de correspondance
			eleves = cdtDao.getListObt();
			System.out
					.println("Taille" + eleves.size() + eleve.getPrimaryKey());
			for (Eleve el : eleves) {
				if (el.getPrimaryKey().equals(eleve.getPrimaryKey())) {
					eleve.setCodeInfo(el.getCodeInfo());
				}
			}

			// attribution de numéro matricule en cas de défaut
			String matricule = eleve.getCodeInfo();
			System.out
					.println("###################**************ArrivéMatricule"
							+ matricule);

			if (matricule.equals("")) {
				matricule = codeGen.getMatri();
				System.out.println("###################**************Générer"
						+ matricule);

			} else {
				Eleve oldEleve = new Eleve();
				try {
					oldEleve = (Eleve) cdtDao.findObj(eleve.getCodeInfo());
				} catch (Exception e) {

				}

				eleve.setPrimaryKey(oldEleve.getPrimaryKey());
			}

			eleve.setCodeInfo(matricule);

			cdtDao.update_create(eleve);

			// inscription dans la table des notes
			EleveClasse elvcls = new EleveClasse(eleve.getNom(),
					eleve.getPrenom(), eleve.getCodeInfo());

			cdtExamDao.update_create(elvcls);

			notifyObserver();

			long tm2 = System.currentTimeMillis();
			System.out.println("**************temp:" + (double) (tm2 - tm1)
					/ 1000 + "**********************");
		}
	}

	@Override
	public void supprimer(int mode2) {
		MartTabModel mod;
		this.mode = mode2;

		// methode pour supprimer une ligne
		if (this.mode == DELETE_COMPLETELY) {
			// ligne à supprimer
			String matricule = ((Eleve) data).getCodeInfo();

			// Suppression dans la base générale
			DAO dao = DAOFactory.getDAO(DAO.ELEVE);
			dao.load();
			Eleve eleve = (Eleve) dao.findObj(matricule);
			dao.deleteObj(eleve);

			classe = eleve.getClasseAnnee(annee);

			// suppression dans la classe
			cdtExamDao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
			cdtExamDao.load(new String(), classe, trimestre, annee);
			cdtExamDao.deleteObj(new EleveClasse(eleve.getCodeInfo()));

			notifyObserver();
		}

		// methode pour supprimer l'élève seulement dans la classe choisi
		if (this.mode == DELETE_IN_CURRENTLIST) {
			// ligne à supprimer
			String matricule = ((Eleve) data).getCodeInfo();

			// suppression dans la classe
			cdtExamDao = DAOFactory.getDAO(DAO.CANDIDAT_PERSO);
			cdtExamDao.loadExam(examen);

			cdtExamDao.deleteObj(new EleveClasse(matricule));

			notifyObserver();
		}
	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

}
