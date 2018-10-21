package salaire;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import ecolage.Operation;

public class PayerSalaireControler extends AbstractControler {

	public PayerSalaireControler(AbstractModel model) {
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
		Operation operation = (Operation) data;
		int option = jop.showConfirmDialog(
				null,
				"Etes vous sûr de vouloir supprimer\n"
						+ operation.getMatricule(), "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}

}
