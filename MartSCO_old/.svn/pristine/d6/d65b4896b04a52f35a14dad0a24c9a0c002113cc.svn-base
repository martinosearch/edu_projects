package candidat;

import javax.swing.JOptionPane;

import tableComponent.MartTabModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;
import eleve.Eleve;
import eleve.EleveClasse;

public class CdtControler extends AbstractControler {

	private DAO elvclsdao;

	public CdtControler(AbstractModel model) {
		super(model);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
	}

	// red�finit la methode control
	public void valider() {
		Eleve eleve = (Eleve) data;

		if (eleve.getNom().trim().isEmpty()
				|| eleve.getPrenom().trim().isEmpty()
				|| eleve.getSexe().trim().isEmpty()
				|| eleve.getEts().trim().isEmpty()) {

			System.out.println("gggggggggggggggggg" + eleve.getNom().isEmpty()
					+ " " + eleve.getPrenom().isEmpty() + " "
					+ eleve.getSexe().isEmpty() + " "
					+ eleve.getClasse().isEmpty());

			JOptionPane.showMessageDialog(null, "Nom ou prénoms incorrects",
					"ALERT!", JOptionPane.ERROR_MESSAGE);
		} else {
			boolean process = true;

			if (process == true) {
				model.setData(eleve);
				model.valider(model.CREATE_OBJECT);
			}
		}

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
