package annee;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class AnneeControler extends AbstractControler {

	public AnneeControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		model.valider(model.CREATE_OBJECT);
	}

	@Override
	public void supprimer(int deleteOption) {
		// lorsque le bouton est appuyer
		JOptionPane jop = new JOptionPane();
		Annee annee = (Annee) data;
		int option = jop.showConfirmDialog(null,
				"Etes vous sûr de vouloir supprimer\n" + annee.getIntitule(),
				"CONFIRMATION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}

}
