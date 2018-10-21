package agent;

import function.MatriGEN;

import javax.swing.JOptionPane;

import tableComponent.MartTabModel;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class AgentControler extends AbstractControler {

	protected MatriGEN codeGen;

	public AgentControler(AbstractModel model) {
		super(model);
	}

	@Override
	// redéfinit la methode control
	public void valider() {
		Agent enseignant = (Agent) data;
		/*
		 * System.out.println("================>>Ens: " + enseignant.getNom() +
		 * enseignant.getPrenom());
		 */

		if (enseignant.getNom().equals("") || enseignant.getSexe().equals("")) {
			JOptionPane
					.showMessageDialog(
							null,
							"Vous devez définir au moins le nom et le sexe de l'Enseignant!",
							"ERREUR!", JOptionPane.ERROR_MESSAGE);
		} else {
			model.valider(model.CREATE_OBJECT);
		}
	}

	@Override
	public void supprimer(int deleteOption) {
		// lorsque le bouton est appuyer
		Agent obj = (Agent) data;
		JOptionPane jop = new JOptionPane();
		int option = jop.showConfirmDialog(null,
				"Etes vous sûr de vouloir supprimer\n" + obj.decrisToi(),
				"CONFIRMATION", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		model.supprimer(deleteOption);
	}

}
