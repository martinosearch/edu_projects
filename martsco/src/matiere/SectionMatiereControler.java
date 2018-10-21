package matiere;

import org.w3c.dom.views.AbstractView;

import abstractObject.AbstractControler;
import abstractObject.AbstractModel;

public class SectionMatiereControler extends AbstractControler {

	public SectionMatiereControler(AbstractModel model) {
		super(model);
	}

	@Override
	public void valider() {
		model.valider(AbstractModel.UPDATE_CHOISE);
	}

	@Override
	public void supprimer(int deleteOption) {
		// TODO Auto-generated method stub

	}

}
