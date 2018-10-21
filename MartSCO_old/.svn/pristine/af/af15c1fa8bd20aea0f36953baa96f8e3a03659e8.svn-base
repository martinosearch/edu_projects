package etablissement;

import javax.swing.JOptionPane;

import tableComponent.MartTabModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class EtsControler extends AbstractControler {

	public EtsControler(AbstractModel model) {
		super(model);
	}

	// red�finit la methode control
	public void valider() {
		Etablissement ets = (Etablissement) data;
		if (ets.getNom().trim().equals(""))
			JOptionPane.showMessageDialog(null, "Il se peut qu'une"
					+ " info sur l'Etablissement soit \n" + "incorrecte",
					"ERREUR", JOptionPane.ERROR_MESSAGE);
		else {
			model.valider(model.CREATE_OBJECT);
		}
	}

	@Override
	public void supprimer(int deleteOption) {
		// lorsque le bouton est appuyer
		Etablissement ets = (Etablissement) data;
		int option = JOptionPane.showConfirmDialog(null,
				"Etes vous sûr de vouloir supprimer\n" + ets.define(),
				"CONFIRMATION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}

	}

}
