package note;

import javax.swing.JOptionPane;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import connection.DAO;
import connection.DAOFactory;

public class NoteControler extends AbstractControler {
	private int max = 20;

	public NoteControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		try {
			if (superClasse.getTypeEns().equals("PRIM")) {
				max = 10;
			} else {
				max = 20;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Note note = (Note) data;

		if (note.getNote1() > max || note.getNote2() > max
				|| note.getNote3() > max || note.getNote4() > max) {
			done = false; // notification d'echec

			JOptionPane.showMessageDialog(null, "Verifiez votre entrée, "
					+ "vous avez peut- être saisi une " + "note supérieur "
					+ max, "ERREUR!", JOptionPane.ERROR_MESSAGE);
		} else {
			done = true;
			model.valider(model.CREATE_OBJECT);
		}
	}

	@Override
	public void supprimer(int deleteOption) {
		// TODO Auto-generated method stub

	}

}
