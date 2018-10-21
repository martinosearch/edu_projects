package classe;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class ClasseControler extends AbstractControler {

	public ClasseControler(AbstractModel model) {
		super(model);
	}

	@Override
	// redéfinit la methode control
	public void valider() {
		Classe classe = (Classe) data;

		if (classe.getIntitule().equals("") || classe.getNiveau().equals("")) {

			JOptionPane.showMessageDialog(null,
					"Un attribut de la classe est mal défini", "ERREUR! ",
					JOptionPane.ERROR_MESSAGE);
		} else {
			model.valider(model.CREATE_OBJECT);
		}

	}

	@Override
	public void supprimer(int deleteOption) {
		// lorsque le bouton est appuyer
		JOptionPane jop = new JOptionPane();
		int option = jop.showConfirmDialog(
				null,
				"Etes vous sur de vouloir supprimer\n"
						+ ((Classe) data).getIntitule() + "?", "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}
}
