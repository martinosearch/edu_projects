package matiere;

import javax.swing.JOptionPane;

import tableComponent.MartTabModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class MatiereProgControler extends AbstractControler {

	public MatiereProgControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		model.valider(model.UPDATE_CHOISE);
	}

	@Override
	public void supprimer(int deleteOption) {
		// lorsque le bouton est appuyer
		JOptionPane jop = new JOptionPane();
		int option = jop.showConfirmDialog(null,
				"Etes vous sur de vouloir supprimer\n" + deleteData,
				"CONFIRMATION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}

}
