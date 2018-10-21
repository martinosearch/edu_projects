package note;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import abstractObject.Rapport;
import connection.DAO;
import connection.DAOFactory;
import examen.Examen;

public class NoteExamControler extends AbstractControler {

	private DAO examdao;

	public NoteExamControler(AbstractModel model) {
		super(model);

		examdao = DAOFactory.getDAO(DAO.EXAMEN);
		examdao.load();
	}

	// red�finit la methode control
	public void control() {
	}

	public void valider() {
		Note note = (Note) data;

		superExamen = (Examen) examdao.findObj(examen);

		if (superExamen.getModelRap() == Rapport.MODEL_PRIM) {
			if (note.getNote3() > 10 || note.getNote4() > 10) {
				done = false; // notification d'echec

				JOptionPane.showMessageDialog(null, "Verifiez votre entrée, "
						+ "vous avez peut- être saisi une "
						+ "note supérieur 10", "ERREUR!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				done = true;
				model.valider(model.CREATE_OBJECT);
			}
		} else {
			if (note.getNote1() > 20 || note.getNote2() > 20
					|| note.getNote3() > 20 || note.getNote4() > 20) {
				done = false; // notification d'echec

				JOptionPane.showMessageDialog(null, "Verifiez votre entrée, "
						+ "vous avez peut- être saisi une "
						+ "note supérieur 20", "ERREUR!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				done = true;
				model.valider(model.CREATE_OBJECT);
			}
		}

	}

	@Override
	public void supprimer(int deleteOption) {
		// TODO Auto-generated method stub

	}
}
