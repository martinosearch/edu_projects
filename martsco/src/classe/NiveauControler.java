package classe;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class NiveauControler extends AbstractControler {

	public NiveauControler(AbstractModel model) {
		super(model);
	}

	@Override
	// redéfinit la methode control
	public void valider() {
		Niveau niveau = (Niveau) data;

		if (niveau.getIntitule().equals("") || niveau.getTypeEns().equals("")) {

			JOptionPane.showMessageDialog(null,
					"Un attribut du niveau est mal défini", "ERREUR! ",
					JOptionPane.ERROR_MESSAGE);
		} else {
			model.valider(model.CREATE_OBJECT);
		}

	}

	@Override
	public void supprimer(int deleteOption) {
		int option = JOptionPane.showConfirmDialog(
				null,
				"Etes vous sur de vouloir supprimer\n"
						+ ((Niveau) data).getIntitule() + "?", "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}
}
