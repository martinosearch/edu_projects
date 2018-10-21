package statistique;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class StaMoyTrimControler extends AbstractControler {

	public StaMoyTrimControler(AbstractModel model) {
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
