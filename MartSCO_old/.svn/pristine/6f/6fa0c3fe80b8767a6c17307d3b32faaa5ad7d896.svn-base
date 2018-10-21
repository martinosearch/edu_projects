package eleve;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class EleveControler extends AbstractControler {

	private DAO elvclsdao;
	private String oldClasse;

	public EleveControler(AbstractModel model) {
		super(model);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
	}

	// redéfinit la methode control
	public void valider() {
		Eleve eleve = (Eleve) data;
		oldClasse = null;

		try {
			oldClasse = eleve.getClasseAnnee(annee);
		} catch (Exception e) {

		}

		System.out.println("*****************L'ancienne classe " + oldClasse);

		try {
			if (eleve.getNom().trim().isEmpty()
					|| eleve.getPrenom().trim().isEmpty()
					|| eleve.getSexe().trim().isEmpty()
					|| eleve.getClasse().trim().isEmpty()) {
				System.out.println("gggggggggggggggggg"
						+ eleve.getNom().isEmpty() + " "
						+ eleve.getPrenom().isEmpty() + " "
						+ eleve.getSexe().isEmpty() + " "
						+ eleve.getClasse().isEmpty());

				JOptionPane.showMessageDialog(null,
						"Nom ou prénoms incorrects", "ALERT!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				boolean process = true;

				if (oldClasse != null) {
					if (!(eleve.getClasse().equals(oldClasse))
							&& !(oldClasse.equals(""))) {

						if (isAskBefore() == true) {
							int option = JOptionPane
									.showConfirmDialog(
											null,
											"Voulez- vous vraiment modifier la classe de: \n"
													+ eleve.decrisToi()
													+ "? \n"
													+ "En effet cet élève est déjà inscrit en "
													+ oldClasse,
											"CONFIRMATION",
											JOptionPane.YES_NO_OPTION);

							if (option == JOptionPane.NO_OPTION) {
								process = false;
							} else {
								doMiseAjour(eleve);
							}
						} else {
							doMiseAjour(eleve);
						}
					}
				}

				if (process == true) {
					model.valider(model.CREATE_OBJECT);
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Nom ou prénoms incorrects",
					"ALERT!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void doMiseAjour(Eleve eleve) {
		// on supprime le nom de l'élève dans l'ancienne classe
		elvclsdao.load(new String(), oldClasse, trimestre, annee);
		elvclsdao.deleteObj(new EleveClasse(eleve.getCodeInfo()));
	}

	@Override
	public void supprimer(int deleteOption) {
		Eleve eleve = ((Eleve) data);
		// lorsque le bouton est appuyer
		int option = JOptionPane.showConfirmDialog(null,
				"Etes vous sûr de vouloir supprimer\n" + eleve.decrisToi(),
				"CONFIRMATION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}

}
