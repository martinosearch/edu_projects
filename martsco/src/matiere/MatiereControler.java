package matiere;

import function.Constance;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tableComponent.MartTabModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class MatiereControler extends AbstractControler {

	public MatiereControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		Matiere matiere = (Matiere) data;
		if (matiere.getIntitule().equals(""))
			JOptionPane.showMessageDialog(null,
					Constance.getCreateError("La matière"), "ERREUR",
					JOptionPane.ERROR_MESSAGE);
		else {

			model.setData(data);
			model.valider(model.CREATE_OBJECT);

		}
	}

	@Override
	public void supprimer(int deleteOption) {
		// lorsque le bouton est appuyer
		int option = JOptionPane.showConfirmDialog(
				null,
				"Etes vous sur de vouloir supprimer\n"
						+ ((Matiere) data).getIntitule(), "CONFIRMATION",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}
}
