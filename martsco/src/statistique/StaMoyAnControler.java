package statistique;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import rapportBulletin.BullWriter;
import tableComponent.MartTabModel;

public class StaMoyAnControler extends AbstractControler {

	public StaMoyAnControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		model.valider(model.CREATE_OBJECT);

	}

	@Override
	public void supprimer(int deleteOption) {
		// TODO Auto-generated method stub

	}

}
