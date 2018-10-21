package eleve;

import tableComponent.MartTabModel;
import interfacePerso.MartRangeable;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;
import function.Constance;
import function.MatriGEN;
import graphicsModel.MartList;

public class EleveModel extends AbstractModel {
	private MatriGEN codeGen;
	private MartList<Eleve> eleves;
	private boolean first;
	private String previousClasse = "";
	private DAO instancePromoElvDao = null;

	public EleveModel() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		cursusdao = DAOFactory.getDAO(DAO.CURSUS_ELEVE);
	}

	public void valider(int tpe) {
		long tm1 = System.currentTimeMillis();
		this.type = tpe;
		if (type == 1) {
			codeGen = new MatriGEN(MatriGEN.ELEVE);

			// inscription de l'élève dans la base générale
			Eleve eleve = (Eleve) data;

			// System.out.println("*****************Eleve recu "
			// + eleve.getPrimaryKey() + "code info="
			// + eleve.getCodeInfo());

			// System.out.println("***************** " + previousClasse + "  "
			// + instancePromoElvDao + "Eleve= " + eleve.getPrimaryKey()
			// + eleve.getCodeInfo());

			if (!(previousClasse.equals(classe)) || instancePromoElvDao == null) {
				cursusdao.load(new String(), "", 1, annee);
				elvdao.load(new String(), "", 1, annee);// juste
														// pour
														// l'annee
				elvclsdao.load(new String(), classe, trimestre, annee);
				System.out
						.println("Chargement dans modelEleve=================================>>"
								+ annee
								+ "puis corrige:"
								+ Constance.getCor(annee));
			}

			// sauvegarde de la classe actuelle
			try {
				previousClasse = eleve.getClasse();
			} catch (Exception e) {

			}

			// Vérification de correspondance
			eleves = elvdao.getListObt();
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
					oldEleve = (Eleve) elvdao.findObj(eleve.getCodeInfo());
				} catch (Exception e) {

				}

				eleve.setPrimaryKey(oldEleve.getPrimaryKey());
			}

			eleve.setCodeInfo(matricule);

			elvdao.update_create(eleve);

			// attribution de classe à l'élève
			EleveClasse elvcls = new EleveClasse(eleve.getNom(),
					eleve.getPrenom(), eleve.getCodeInfo());

			elvclsdao.update_create(elvcls);

			// Mise à jour des informations sur la scolarité de l'élève
			Scolarite sco = new Scolarite(eleve.getCodeInfo(), niveau);
			if (instancePromoElvDao == null) {
				instancePromoElvDao = DAOFactory
						.getDAO(DAO.PROMO_ELEVE);
				instancePromoElvDao
						.load(new String(), classe, trimestre, annee);

				// System.out.println("==================================>> appel d'ici");
			}

			instancePromoElvDao.update_create(sco);

			// mise à jour du cursus de l'élève (table_classes_eleve)
			Cursus cur = new Cursus(eleve.getCodeInfo(), annee, classe);
			cursusdao.update_create(cur);

			notifyObserver();

			long tm2 = System.currentTimeMillis();
			System.out.println("**************temp:" + (double) (tm2 - tm1)
					/ 1000 + "************");
		}
	}

	@Override
	public void supprimer(int mode2) {
		MartTabModel mod;
		this.mode = mode2;

		if (this.mode == DELETE_COMPLETELY) {
			// ligne à supprimer
			String matricule = ((Eleve) data).getCodeInfo();

			// Suppression dans la base générale
			DAO dao = DAOFactory.getDAO(DAO.ELEVE);
			dao.load();
			Eleve eleve = (Eleve) dao.findObj(matricule);

			classe = eleve.getClasseAnnee(annee);
			System.out.println("=========================>>eleve: "
					+ eleve.decrisToi() + "classe: " + classe
					+ "pourtant l'année: " + annee);

			// suppression dans la classe
			elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
			elvclsdao.load(new String(), classe, trimestre, annee);

			elvclsdao.deleteObj(new EleveClasse(eleve.getCodeInfo()));

			dao.deleteObj(eleve);

			notifyObserver();
		}

		// methode pour supprimer l'élève seulement dans la classe choisi
		if (this.mode == DELETE_IN_CURRENTLIST) {
			// ligne à supprimer
			String matricule = ((Eleve) data).getCodeInfo();

			// suppression dans la classe
			elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
			elvclsdao.load(new String(), classe, trimestre, annee);

			elvclsdao.deleteObj(new EleveClasse(matricule));

			notifyObserver();
		}

		System.out.println("Je suis appelé******************************"
				+ mode2);
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
