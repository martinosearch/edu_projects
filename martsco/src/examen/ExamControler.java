package examen;

import etablissement.Etablissement;
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
import matiere.NouvelleMatiere;

public class ExamControler extends AbstractControler {

	public ExamControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		Examen exam = (Examen) data;

		if (exam.getIntitule().trim().equals(""))
			JOptionPane.showMessageDialog(null,
					Constance.getCreateError("l'Examen"), "ERREUR",
					JOptionPane.ERROR_MESSAGE);
		else {
			model.valider(model.CREATE_OBJECT);
		}
	}

	@Override
	public void supprimer(int deleteOption) {
		Examen ets = (Examen) data;
		int option = JOptionPane.showConfirmDialog(null,
				"Etes vous sûr de vouloir supprimer\n" + ets.getIntitule(),
				"CONFIRMATION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.YES_OPTION) {
			model.supprimer(deleteOption);
		}
	}

}
